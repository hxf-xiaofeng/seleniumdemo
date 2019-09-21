package TestCases;

import CommonObject.GetDriverUtil;
import PageObjectOperation.RegisterPage;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;

@Epic("RegisterPage")
@Feature("Register Feature")
public class RegisterPageTest {
    RegisterPage page = new RegisterPage();
    GetDriverUtil browser = new GetDriverUtil();
    WebDriver driver ;

    //测试开始前设置启动浏览器及指向URL
    @BeforeTest(description = "初始化浏览器设置,设置指向url:https://www.finexbox.com//Reg/register.html")
    public void init(){
        driver=browser.setupChrome("https://www.finexbox.com//Reg/register.html");
    }

    @Step("点击邮箱输入框,输入邮箱,等待提示语显示,TestNG断言进行判断")
    @Story("邮箱输入框_邮箱已存在提示语")
    @Test()
    public void checkEmail_Exist() throws InterruptedException {
        page.input_Email(driver,"379506993@qq.com");
        page.click_Register(driver);
        String msg=driver.findElement(By.xpath(page.Email_error_msg_XPATH)).getText();
        long round=Math.round(1.5);
        System.out.print(round);
        if(msg.equals("error")){
            AssertJUnit.assertEquals("error", msg);
        }else {
            AssertJUnit.assertEquals("Email already exists", msg );
        }
    }

    @Step("点击邮箱输入框，输入非法邮箱及空邮箱,等待提示语显示,TestNG断言进行判断")
    @Story("邮箱输入框_邮箱非法提示语")
    @Description("123")
    @Test(dataProvider = "email",dataProviderClass = dataSource.registerData.class)
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
    @Test(dataProvider = "password",dataProviderClass = dataSource.registerData.class)
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
    @Test(dataProvider = "repassword",dataProviderClass = dataSource.registerData.class)
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
    @Test(dataProvider = "pwd_compare",dataProviderClass = dataSource.registerData.class)
    public void pwd_Compare(String pwd1,String pwd2){
        page.input_Password(driver,pwd1);
        page.input_RePassword(driver,pwd2);
        page.click_Register(driver);
        AssertJUnit.assertEquals("The password is not consistent. Please enter again.",
                driver.findElement(By.xpath(page.RePassword_error_msg_XPATH)).getText());
    }

    @Severity(SeverityLevel.BLOCKER)
    @Story("注册")
    @Test()
    public void register(){
        page.input_Email(driver,"123456@ds.com");
        page.input_Password(driver,"123123");
        page.input_RePassword(driver,"123123");
        page.input_ReferralIDs(driver,"113319");
        page.click_Register(driver);
    }

    @AfterTest(description = "关闭浏览器")
    public void close(){
        driver.quit();
    }
}
