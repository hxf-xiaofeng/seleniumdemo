package PageObjectOperation;

import PageElementPath.RegisterPageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends RegisterPageElement {
    //点击邮箱输入框
    public void click_Email_Box(WebDriver driver){
        driver.findElement(By.id(Email_id)).click();
    }
    //输入邮箱
    public void input_Email(WebDriver driver,String email){
        driver.findElement(By.id(Email_id)).clear();
        driver.findElement(By.id(Email_id)).sendKeys(email);
        System.out.println("email:"+email);
    }
    //点击密码输入框
    public void click_Password_Box(WebDriver driver){
        driver.findElement(By.id(Password_id)).click();
    }
    //输入密码
    public void input_Password(WebDriver driver ,String password){
        driver.findElement(By.id(Password_id)).clear();
        driver.findElement(By.id(Password_id)).sendKeys(password);
        System.out.println("password:"+password);
    }
    //点击重复密码输入框
    public void click_RePassword_Box(WebDriver driver){
        driver.findElement(By.id(Repassword_id));
    }
    //输入重复密码
    public void input_RePassword(WebDriver driver,String repassword){
        driver.findElement(By.id(Repassword_id)).clear();
        driver.findElement(By.id(Repassword_id)).sendKeys(repassword);
        System.out.println("repassword:"+repassword);
    }
    //点击推荐人ID输入框
    public void click_ReferralIDs(WebDriver driver){
        driver.findElement(By.name(ReferralIDs_name)).click();
    }
    //输入推荐人ID
    public void input_ReferralIDs(WebDriver driver,String referralids){
        driver.findElement(By.name(ReferralIDs_name)).clear();
        driver.findElement(By.name(ReferralIDs_name)).sendKeys(referralids);
        System.out.println("refferalids:"+referralids);
    }
    //点击register，提交表单
    public void click_Register(WebDriver driver){
        driver.findElement(By.id(Register_button_id)).click();
    }
    //跳转至登录页面
    public void click_Login_label(WebDriver driver){
        driver.findElement(By.xpath(login_label_XPATH)).click();
    }


}

