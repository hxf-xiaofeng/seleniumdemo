package commonobject;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GetWebDriverUtil {

    public WebDriver driver;
    private static final String OS =System.getProperty("os.name").toLowerCase();//获取当前操作系统名称

    private void driverSettings(String url){
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(18,TimeUnit.SECONDS);
}

    private ChromeOptions driverSettingsForLinux(){
        ChromeOptions options= new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }
    //配置chrome浏览器的指向url及selenium的页面加载模式NONE: 当html下载完成之后，不等待解析完成，selenium会直接返回
    public WebDriver setupChrome(String url,boolean pageloadstrategy_none){
        if(OS.contains("windows")){
            System.setProperty("webdriver.chrome.driver","./src/main/resources/chromedriver.exe");//window构建路径
            if(!pageloadstrategy_none){
                driver = new ChromeDriver();//不使用PageLoadStrategy.NONE模式加载页面
                driverSettings(url);//提高代码重用整合进方法里进行webdriver设置
            }else {
                driver = new ChromeDriver(new ChromeOptions().setPageLoadStrategy(PageLoadStrategy.NONE));//使用PageLoadStrategy.NONE模式加载页面
                driverSettings(url);
            }

        }if(OS.contains("linux")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");//linux系统下的jenkins构建路径
            ChromeOptions options=driverSettingsForLinux();
            if (!pageloadstrategy_none){
                driver =new ChromeDriver(options);
                driverSettings(url);
            }else{
                options.setPageLoadStrategy(PageLoadStrategy.NONE);
                driver =new ChromeDriver(options);
                driverSettings(url);
            }
        }
        return driver;
    }



}
