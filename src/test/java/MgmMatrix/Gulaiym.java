package MgmMatrix;

import com.github.javafaker.Faker;
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

public class Gulaiym {


    static WebDriver driver;
    @BeforeMethod

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);driver.get("https://www.mgmresorts.com");
        WebElement groupsWeddings = driver.findElement(By.linkText("Groups & Weddings"));
        groupsWeddings.click();
    }

    @AfterMethod
    public void tearDown(){
        driver.close();

    }

    @Test(priority = 1)
    public void checkingAllButtonsWeddingGroups()throws InterruptedException{


        WebElement ConventionMeeting =driver.findElement(By.linkText("Convention & Meeting Venues"));
        Assert.assertTrue(ConventionMeeting.isDisplayed(),"Convention & Meeting Venues is not displayed " );
        Thread.sleep(5000);
        WebElement Weddings = driver.findElement(By.linkText("Weddings"));
        Assert.assertTrue(Weddings.isDisplayed(),"Weddings button is not displayed");
        Thread.sleep(5000);
        WebElement SocialGroups = driver.findElement(By.linkText("Social Groups"));
        Assert.assertTrue(SocialGroups.isDisplayed(),"Social Groups is not displayed");
        Thread.sleep(5000);
        WebElement EventCenters = driver.findElement(By.linkText("Event Centers"));
        Assert.assertTrue(EventCenters.isDisplayed(),"Event Centers is not displayed");
        Thread.sleep(5000);
        WebElement MGMResort = driver.findElement(By.linkText("MGM Resorts Productions"));
        Assert.assertTrue(MGMResort.isDisplayed(),"Mgm Resort Productions is not displayed");
        Thread.sleep(5000);
        WebElement MgmEvent = driver.findElement(By.linkText("MGM Resorts Event Services"));
        Assert.assertTrue(MgmEvent.isDisplayed(),"MgmResorts Event is not displayed");

    }

    @Test(priority = 2)

    public void SocialGroupsRequest(){

        WebElement Weddings = driver.findElement(By.linkText("Weddings"));
        Weddings.click();

        Assert.assertTrue(driver.getTitle().contains("Weddings"));


    }

    @Test(priority = 3)

    public void booking(){

        //driver.findElement(By.xpath("//div[@class = 'rte']//a[.='Bellagio']")).click();

        String mainHandle = driver.getWindowHandle();
        WebElement bookRoomLink = driver.findElement(By.linkText("Bellagio"));
        bookRoomLink.click();
        for(String handle: driver.getWindowHandles()){
            if(!handle.equals(mainHandle)){
                driver.switchTo().window(handle);
                break;
            }
        }
        WebElement bookRoom = driver.findElement(By.xpath("//a[@id='nav-book']"));
        bookRoom.click();
        Faker faker = new Faker();
        WebElement FirstName = driver.findElement(By.id("guest-info-cont-first-name"));
        FirstName.sendKeys(faker.name().name());
        WebElement LastName = driver.findElement(By.id("guest-info-cont-last-name"));
        LastName.sendKeys(faker.name().lastName());
        WebElement PhoneNumber = driver.findElement(By.id("guest-info-cont-phone"));
        PhoneNumber.sendKeys(faker.phoneNumber().cellPhone());
        WebElement Email = driver.findElement(By.id("guest-info-login-email-address"));
        Email.sendKeys(faker.internet().emailAddress());
        WebElement NameOnCard = driver.findElement(By.id("guest-info-cont-last-name"));
        NameOnCard.sendKeys(faker.name().fullName());
        WebElement CardNumber = driver.findElement(By.id("bill-card-num"));
        CardNumber.sendKeys(faker.finance().creditCard());
        WebElement inputExpMonth = driver.findElement(By.id("bill-card-exp-month"));
        Email.sendKeys("06");
        WebElement ExpYear = driver.findElement(By.id("bill-card-exp-year"));
        ExpYear.sendKeys("2019");
        WebElement Cvv = driver.findElement(By.id("bill-card-cvv"));
        Cvv.sendKeys("978");
        WebElement Address = driver.findElement(By.id("bill-address1"));
        Address.sendKeys(faker.address().streetAddress());
        WebElement City = driver.findElement(By.id("bill-city"));
        City.sendKeys(faker.address().cityName());
        WebElement State = driver.findElement(By.id("bill-us-state"));
        State.sendKeys(faker.address().state());
        WebElement ZipCode = driver.findElement(By.id("bill-zip"));
        ZipCode.sendKeys(faker.address().zipCode());
        WebElement TermAndConditions = driver.findElement(By.id("terms-label"));
        TermAndConditions.click();
        WebElement confirm = driver.findElement(By.id("review-final"));
        confirm.click();

    }





}













