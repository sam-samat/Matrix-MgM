
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Mirzat {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mgmresorts.com");
    }


    @Test
    public void firstTest() {

        //User should be able to locate the title
        WebElement offerButton = driver.findElement(By.id("nav-offers-8"));
        offerButton.click();
        //User should be able to verify the title "Offer"
        Assert.assertTrue(driver.getTitle().contains("Offers"),"Offers button verification failed" );
        
        //count number of links on landing page
        List<WebElement> listOfLinks = driver.findElements(By.xpath("//body//a"));
        System.out.println("The number of links on this page: " + listOfLinks.size());

        //verify the text "offer"
        WebElement text = driver.findElement(By.id("tagsFilter-0-entertainment-btn"));
        String actual = text.getText();
        String expected= "offers";
        Assert.assertTrue(actual.equals(expected), "Text verification failed");

    }


     @Test
     public void secondTest() {

        //locate and verify whether "filter by" button is displayed
        WebElement filterByButton = driver.findElement(By.linkText("FILTER BY"));
        filterByButton.click();
        Assert.assertTrue(filterByButton.isDisplayed(), "filterBy button is not displayed");

        //verify whether "offer type" button is displayed
        WebElement offerType=driver.findElement(By.id("tagsFilter}-0-entertainment-btn"));
        Assert.assertTrue(offerType.isDisplayed(), "OfferType button is not displayed");

        //verify whether "all regions" button is displayed
        WebElement allRegions=driver.findElement((By.linkText("All Regions")));
        Assert.assertTrue(allRegions.isDisplayed(), "allRegions button is not displayed");

        //verify whether below buttons are clickable inside "All regions" button
          driver.findElement(By.id("filter-1-btn")).click();
        driver.findElement(By.xpath("//span[@id=\'region-0\']/span")).click();
        System.out.println("Las Vegas button is working");
        driver.findElement(By.xpath("//*[@id=\'region-1\']/span")).click();
        System.out.println("Detroit button is working");
        driver.findElement(By.xpath("//*[@id=\'region-2\']/span")).click();
        System.out.println("Missisipi button is working");
        driver.findElement(By.xpath("//*[@id=\'region-3\']/span")).click();
        System.out.println("Massachusets button is working");
        driver.findElement(By.xpath("//*[@id=\'region-4\']/span")).click();
        System.out.println("Maryland button is working");
        driver.findElement(By.xpath("//*[@id=\'region-5\']/span")).click();
        System.out.println("Ohio button is working");

    }


    @Test
    public void ThirdTest() throws InterruptedException {

        String s = driver.findElement(By.linkText("forgot your password")).getAttribute("href");

        Assert.assertTrue(s.contains("/forgot-password.html\""), "link verification is failed");

        //    verify  whether below buttons are clickable inside of "Offer type" button
        driver.findElement(By.id("tagsFilter-0-entertainment-btn"));
        driver.findElement(By.xpath("//*[@id=\'tagsFilter\']/div/ul/li[1]/a")).click();
        System.out.println("All types button is clickable");
        driver.findElement(By.xpath("//*[@id=\'tagsFilter\']/div/ul/li[2]/a")).click();
        System.out.println("hotel button is clickable");
        driver.findElement((By.xpath("//*[@id=\'tagsFilter\']/div/ul/li[3]/a"))).click();
        System.out.println("Entertainment button is clickable ");

        //select from "Offer type" below

        WebElement offerType= driver.findElement(By.id("tagsFilter}-0-entertainment-btn"));
        Select options= new Select(offerType);
        options.selectByVisibleText("All types");
        Thread.sleep(3000);
        options.selectByVisibleText("Hotel");
        Thread.sleep(3000);
        options.selectByVisibleText("Entertainment");

    }

}

