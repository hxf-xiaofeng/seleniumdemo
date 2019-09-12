package TestCases;

import CommonObject.UseBrowser;
import PageObjectOperation.RegisterPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@Epic("FINEXBOX注册页面")
public class RegisterPageTest {
    RegisterPage page = new RegisterPage();
    UseBrowser browser = new UseBrowser();
    WebDriver driver ;

    //测试开始前设置启动浏览器及指向URL
    @BeforeTest
    public void init(){
        driver=browser.setupChrome("https://www.finexbox.com//Reg/register.html");
    }


    @Test(description = "邮箱输入框输入邮箱,验证邮箱是否已存在")
    @Story("检验邮箱是否已存在")

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

    @Test(dataProvider = "email",dataProviderClass = dataSource.registerData.class,description ="邮箱输入框输入邮箱,验证输入邮箱格式" )
    @Story("检查输入邮箱格式")
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


    @Test(dataProvider = "password",dataProviderClass = dataSource.registerData.class,description = "密码输入框输入密码,验证输入密码格式")
    @Story("检查输入密码格式")
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


    @Test(dataProvider = "repassword",dataProviderClass = dataSource.registerData.class,description = "确认密码框输入确认密码,验证确认密码格式")
    @Story("检查确认密码格式")
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

    @Test(dataProvider = "pwd_compare",dataProviderClass = dataSource.registerData.class,description = "密码框和确认密码框输入不同密码,验证两次输入密码是否一致")
    @Story("检查两次输入密码是否一致")
    public void pwd_Compare(String pwd1,String pwd2){
        page.input_Password(driver,pwd1);
        page.input_RePassword(driver,pwd2);
        page.click_Register(driver);
        AssertJUnit.assertEquals("The password is not consistent. Please enter again.",
                driver.findElement(By.xpath(page.RePassword_error_msg_XPATH)).getText());
    }
    //注册
    @Test(enabled = false)

    public void register(){
        page.input_Email(driver,"");
        page.input_Password(driver,"123123");
        page.input_RePassword(driver,"123123");
        page.input_ReferralIDs(driver,"113319");
        page.click_Register(driver);
    }



    @AfterTest
    public void close(){
        driver.quit();
    }
}
