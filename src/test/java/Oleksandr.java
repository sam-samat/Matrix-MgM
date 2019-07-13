import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Oleksandr {
    static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //Test 1;

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void test1(){
        Assert.assertTrue(image1(driver));
    }

    @Test
    public void test2(){
        Assert.assertTrue(image2(driver));
    }

    @Test
    public void test3(){
        Assert.assertTrue(image3(driver));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    public static boolean image1(WebDriver driver) {
        driver.get("https://www.mgmresorts.com/en.html");
        driver.findElement(By.id("nav-casino-4")).click();
        WebElement img1 = driver.findElement(By.xpath("(//*[@class = 'comparison-image'] //img)[2]"));
        String src1 = img1.getAttribute("src");
        WebElement img2 = driver.findElement(By.xpath("(//*[@class = 'comparison-image'] //img)[3]"));
        String src2 = img2.getAttribute("src");
        if (src1.equals(src2)) {
            return false;
        }
        return true;
    }
    public static boolean image2(WebDriver driver) {
        driver.get("https://www.mgmresorts.com/en.html");
        driver.findElement(By.id("nav-casino-4")).click();
        WebElement img1 = driver.findElement(By.xpath("(//*[@class = 'comparison-image'] //img)[3]"));
        String src1 = img1.getAttribute("src");
        WebElement img2 = driver.findElement(By.xpath("(//*[@class = 'comparison-image'] //img)[4]"));
        String src2 = img2.getAttribute("src");
        if (src1.equals(src2)) {
            return false;
        }
        return true;
    }

    public static boolean image3(WebDriver driver) {
        driver.get("https://www.mgmresorts.com/en.html");
        driver.findElement(By.id("nav-casino-4")).click();
        WebElement img1 = driver.findElement(By.xpath("(//*[@class = 'comparison-image'] //img)[4]"));
        String src1 = img1.getAttribute("src");
        WebElement img2 = driver.findElement(By.xpath("(//*[@class = 'comparison-image'] //img)[5]"));
        String src2 = img2.getAttribute("src");
        if (src1.equals(src2)) {
            return false;
        }
        return true;

    }
}
