package CommonObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GetDriverUtil {
    public WebDriver driver;

    //启动谷歌浏览器-windows版
    public WebDriver setupChrome(String url){

        //System.setProperty("webdriver.chrome.driver","./src/main/resources/chromedriver.exe"); //window下IntelliJ IDEA运行

        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");//linux下jenkins上运行
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        addArguments("--headless");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.get(url);
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
