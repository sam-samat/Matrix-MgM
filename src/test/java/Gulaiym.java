
  

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Gulaiym {

WebDriver driver;

    @BeforeClass
                 public  void setup() {
             WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver();
             driver.manage().window().maximize();
             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

         }
         @BeforeMethod
         public void locatingResorts() {
            driver.get("https://www.mgmresorts.com");
            //User should able to locate and click on Resorts
            //User  should be able to choose of one of the four country/city options
             driver.findElement(By.id("nav-resorts-1")).click();
            String url = driver.getCurrentUrl();
            Assert.assertTrue(driver.getCurrentUrl().contains("Hotels"),"Locating resort url didn't pass");
            //Thread.sleep(3000);

        }

        @AfterMethod
        public void tearDown() {
             driver.close();
        }
        @Test
             public void locatingAllCities() {
             WebElement lasVegas = driver.findElement(By.linkText("Las Vegas"));
                 lasVegas.click();
                 Assert.assertTrue(driver.getCurrentUrl().contains("Vegas"),"Las Vegas url verification didn't pass");

                 //Thread.sleep(3000);


                 WebElement unitedStates = driver.findElement(By.linkText("United States"));
                 unitedStates.click();
                Assert.assertTrue(driver.getCurrentUrl().contains("United States"),"United States url verification " +
                        "didn't pass");
                 WebElement chinaButton = driver.findElement(By.linkText("China"));
                 chinaButton.click();
                Assert.assertTrue(driver.getCurrentUrl().contains("China"),"China url verification " +
                        "didn't pass");
                 //Thread.sleep(3000);
                 WebElement japanButton = driver.findElement(By.linkText("Japan"));
                 japanButton.click();
                Assert.assertTrue(driver.getCurrentUrl().contains("Japan"),"URL verification of Japan didn't" +
                        "pass");

             }
             @Test
                     public void bookResort() {

                 //User should be able to click on listed resort
                 //User should be able to choose the check in/checkout date


                 WebElement bookNow = driver.findElement(By.xpath("//a[@href='/en/booking/room-booking.html']"));
                 bookNow.click();


                 WebElement resortSelected = driver.findElement(By.xpath(".//*[contains(@class,'resort-item-container')]"));
                 resortSelected.click();
                 WebElement checkIn = driver.findElement(By.xpath("//a[@id='c-08-07-2019']"));
                 checkIn.click();
                 Assert.assertTrue(driver.getCurrentUrl().contains("2019-07-08"),"" +
                         "Verification of check in date didn't pass");
                 WebElement checkOut = driver.findElement(By.xpath("//a[@id='c-09-07-2019']"));
                 checkOut.click();

                 Assert.assertTrue(driver.getCurrentUrl().contains("2019-07-09"),"" +
                         "Verification of check out date didn't pass");

             }
             @Test
                     public void bookRoom() {
        //User should be able to choose one of the listed hotels
                 // User should be able to reserve a room,entering all information
                 driver.findElement(By.xpath("//div[@id='2ae59b7d-ae21-470d-a36f-2649ac87f7d3']//div[2]//div[2]//button")).click();
                 Faker faker = new Faker();
                 WebElement inputFirstName = driver.findElement(By.id("guest-info-cont-first-name"));
                 inputFirstName.sendKeys(faker.name().name());
                 WebElement inputLastName = driver.findElement(By.id("guest-info-cont-last-name"));
                 inputLastName.sendKeys(faker.name().lastName());
                 WebElement inputPhoneNumber = driver.findElement(By.id("guest-info-cont-phone"));
                 inputPhoneNumber.sendKeys(faker.phoneNumber().cellPhone());
                 WebElement inputEmail = driver.findElement(By.id("guest-info-login-email-address"));
                 inputEmail.sendKeys(faker.internet().emailAddress());
                 WebElement inputNameOnCard = driver.findElement(By.id("guest-info-cont-last-name"));
                 inputNameOnCard.sendKeys(faker.name().fullName());
                 WebElement inputCardNumber = driver.findElement(By.id("bill-card-num"));
                 inputCardNumber.sendKeys(faker.finance().creditCard());
                 WebElement inputExpMonth = driver.findElement(By.id("bill-card-exp-month"));
                 inputEmail.sendKeys("08");
                 WebElement inputExpYear = driver.findElement(By.id("bill-card-exp-year"));
                 inputExpYear.sendKeys("2020");
                 WebElement inputCvv = driver.findElement(By.id("bill-card-cvv"));
                 inputCvv.sendKeys("696");
                 WebElement inputAddress = driver.findElement(By.id("bill-address1"));
                 inputAddress.sendKeys(faker.address().streetAddress());
                 WebElement inputCity = driver.findElement(By.id("bill-city"));
                 inputCity.sendKeys(faker.address().cityName());
                 WebElement inputState = driver.findElement(By.id("bill-us-state"));
                 inputState.sendKeys(faker.address().state());
                 WebElement inputZipCode = driver.findElement(By.id("bill-zip"));
                 inputZipCode.sendKeys(faker.address().zipCode());
                 WebElement TermAndConditions = driver.findElement(By.id("terms-label"));
                 TermAndConditions.click();
                 WebElement confirm = driver.findElement(By.id("review-final"));
                 confirm.click();

    }
}

