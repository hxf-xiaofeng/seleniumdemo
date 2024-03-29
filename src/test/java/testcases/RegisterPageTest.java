package testcases;


import commonobject.GetWebDriverUtil;
import weboperation.RegisterPage;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Epic("RegisterPage")
@Feature("Register Feature")
public class RegisterPageTest {
    RegisterPage page = new RegisterPage();
    GetWebDriverUtil browser = new GetWebDriverUtil();
    WebDriver driver ;

    //测试开始前设置启动浏览器及指向URL
    @BeforeTest(description = "初始化浏览器设置,设置指向url:https://www.finexbox.com/Reg/register.html")
    public void init(){
        driver=browser.setupChrome("https://www.finexbox.com/Reg/register.html",false);
    }

    @Step("点击邮箱输入框,输入邮箱,等待提示语显示,TestNG断言进行判断")
    @Story("邮箱输入框_邮箱已存在提示语")
    @Description("注册的反向用例")
    @Test()
    public void checkEmail_Exist() throws InterruptedException {
        page.input_Email(driver,"379506993@qq.com");
        page.click_Register(driver);
        String msg=driver.findElement(By.xpath(page.Email_error_msg_XPATH)).getText();
        if(msg.equals("error")){
            AssertJUnit.assertEquals("error", msg);
        }else {
            AssertJUnit.assertEquals("Email already exists", msg );
        }
    }

    @Step("点击邮箱输入框，输入非法邮箱及空邮箱,等待提示语显示,TestNG断言进行判断")
    @Story("邮箱输入框_邮箱非法提示语")
    @Description("注册的反向用例")
    @Test(dataProvider = "email",dataProviderClass = datasource.registerData.class)
    public void checkEmail_Format( String email) throws InterruptedException {
        if(email == ""){
            page.input_Email(driver,email);
            page.click_Register(driver);
            String msg = driver.findElement(By.xpath(page.Email_empty_XPATH)).getText();
            AssertJUnit.assertEquals("E-mail can not be empty",msg);
        } else {
            page.input_Email(driver,email);
            page.click_Register(driver);
            String msg=driver.findElement(By.xpath(page.Email_error_msg_XPATH)).getText();
            if(msg.equals("error")){
                AssertJUnit.assertEquals("error", msg);
            }else {
                AssertJUnit.assertEquals("Mailbox format not correct", msg);
            }
        }
    }

    @Step("点击密码输入框,输入非法密码和空密码，等待提示语显示，TestNG断言进行判断")
    @Story("密码输入框_邮箱非法提示语")
    @Description("注册的反向用例")
    @Test(dataProvider = "password",dataProviderClass = datasource.registerData.class)
    public void checkPass_Format(String password) throws InterruptedException {
        if(password == ""){
            page.input_Email(driver,"");
            page.click_Register(driver);
            AssertJUnit.assertEquals("Password cannot be blank",
                    driver.findElement(By.xpath(page.Password_Empty_XPATH)).getText());
        }else{
            page.input_Password(driver,password);
            page.click_Register(driver);
            AssertJUnit.assertEquals("Password length between 6-20 english characters or number",
                    driver.findElement(By.xpath(page.Password_Error_msg_XPATH)).getText());
        }
    }

    @Step("点击确认密码输入框,输入非法确认密码和空确认密码，等待提示语显示，TestNG断言进行判断")
    @Story("确认密码输入框_确认密码非法提示语")
    @Description("注册的反向用例")
    @Test(dataProvider = "repassword",dataProviderClass = datasource.registerData.class)
    public void checkConfirmPass_Format(String repassword) throws InterruptedException {
        if(repassword == ""){
            page.input_Password(driver,"");
            page.click_Register(driver);
            AssertJUnit.assertEquals("Please enter a confirmation password",
                    driver.findElement(By.xpath(page.RePassword_Empty_XPATH)).getText());
        }else {
            page.input_RePassword(driver,repassword);
            page.click_Register(driver);
            AssertJUnit.assertEquals("Password length between 6-20 english characters or number",
                    driver.findElement(By.xpath(page.RePassword_error_msg_XPATH)).getText());
        }
    }

    @Step("点击密码输入框并输入密码，点击确认密码输入框并输入错误密码，等待提示语显示,TestNG断言进行判断")
    @Story("确认密码输入框_两次输入密码不一致提示语")
    @Description("注册的反向用例")
    @Test(dataProvider = "pwd_compare",dataProviderClass = datasource.registerData.class)
    public void pwd_Compare(String pwd1,String pwd2)throws InterruptedException{
        page.input_Password(driver,pwd1);
        page.input_RePassword(driver,pwd2);
        page.click_Register(driver);
        AssertJUnit.assertEquals("The password is not consistent. Please enter again.",
                driver.findElement(By.xpath(page.RePassword_error_msg_XPATH)).getText());
    }

    @Step("点击各个输入框并依次输入合法的邮箱-密码-确认密码,点击推荐人ID输入框并输入非法ID,点击register按钮,等待提示语显示,TestNG断言进行判断")
    @Story("推荐ID输入框_ID非法提示语")
    @Description("注册的反向用例")
    @Test()
    public void checkReffalID()throws InterruptedException{
        page.input_Email(driver,"xiaofeng@qq.org");
        page.input_Password(driver,"123456");
        page.input_RePassword(driver,"123456");
        page.input_ReferralIDs(driver,"10000000");
        Thread.sleep(1000);
        page.click_Register(driver);
        Thread.sleep(1000);
        Assert.assertEquals("Please enter a value less than or equal to 9999999",
                driver.findElement(By.xpath(page.reffalIDs_error_msg_XPATH)).getText());
    }

    @Step("点击各个输入框并依次输入合法的邮箱-密码-确认密码-推荐人ID,无提示语显示即输入合法")
    @Story("注册的测试用例")
    @Description("注册的正向用例")
    @Test(dataProvider = "register",dataProviderClass = datasource.registerData.class)
    public void register(String email,String pwd)throws InterruptedException{
        page.input_Email(driver,email);
        page.input_Password(driver,pwd);
        page.input_RePassword(driver,pwd);
        page.input_ReferralIDs(driver,"113319");
    }

    @AfterTest(description = "关闭浏览器")
    public void close(){
        driver.quit();
    }
}
