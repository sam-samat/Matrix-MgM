

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Mehmet {

    WebDriver driver;

    @BeforeMethod
    public void setUpps(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @Test
    public void mgmresortsSerch() throws InterruptedException{
        driver.get("https://www.mgmresorts.com/en.html");
        WebElement inputk=driver.findElement(By.id("nav-things-to-do-5"));
        inputk.click();
        String expectedUrl="https://www.mgmresorts.com/en/things-to-do.html";
        String actualUrl=driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl,"this message not here");
        driver.findElement(By.xpath("//*[@id=\"swipe-content\"]/div[2]/div[4]/h3")).click();
        driver.findElement(By.xpath("//*[@id=\"swipe-content\"]/div[2]/div[4]/span[1]/a")).click();
        WebElement tax=driver.findElement(By.xpath("/html/body/div[2]/article/header/h1"));

        String k= tax.getText();
        String msn="Shows & Concerts Coming to Las Vegas";
        Assert.assertTrue(k.equals(msn),"This message does not match");

    }
    @Test
    public void mgmresortsSerch2() throws InterruptedException{
        driver.get("https://www.mgmresorts.com/en.html");
        WebElement inputx=driver.findElement(By.id("nav-things-to-do-5"));
        inputx.click();
        WebElement inputy=driver.findElement(By.xpath("//*[@id=\"nav-detail-5\"]/a"));
        inputy.click();
        String expectedUrlx="https://www.mgmresorts.com/en/things-to-do.html#/Art%20&%20Culture";
        String actualUrlx=driver.getCurrentUrl();
        Assert.assertEquals(actualUrlx,expectedUrlx,"this message not here");


    }
    @Test
    public void mgmresortsSerch3() throws InterruptedException {
        driver.get("https://www.mgmresorts.com/en.html");
        WebElement inputz=driver.findElement(By.id("nav-things-to-do-5"));
        inputz.click();
        WebElement inputn=driver.findElement(By.xpath("//*[@id=\"nav-detail-4\"]/a"));
        inputn.click();

        WebElement tax2=driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[6]/section/div[1]/span"));

        String str= tax2.getText();
        String msn2="Pools & Cabanas";
        Assert.assertTrue(str.equals(msn2),"This message does not match");
    }

}