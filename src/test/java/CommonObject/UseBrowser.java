package CommonObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class UseBrowser {
    public WebDriver driver;

    //启动谷歌浏览器
    public WebDriver setupChrome(String test_url){
        System.setProperty("webdriver.chrome.driver","E:\\Develop\\IntelliJ\\java_selenium\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(test_url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        return driver;
    }
    //启动火狐浏览器
    public WebDriver setupFirefox(String test_url){
        return driver;
    }
    //启动IE
    public WebDriver setupIE(String test_url){
        return driver;
    }

    //关闭浏览器
    public void shutdownBrowser(){
        driver.close();
    }

}
