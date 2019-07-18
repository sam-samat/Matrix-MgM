import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Samat {





        WebDriver driver;

        @BeforeMethod
        public void setUp(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.mgmresorts.com/en.html");

        }

        @AfterMethod
        public void tearDown() {
            driver.close();
        }

        @Test
        /* User should be able to:
         * navigated to the provided url
         * click "Restaurants
         * pick "asian cuisine" restaurants
         * click "Book Table" for "Morimoto" rstaurant
         * reserve table for 8, for July 20
         * get "confirmation" message on the screen
         */

        public void bookTable()throws InterruptedException{
            String expectedPage = "mgmresorts";
            String actualPage = driver.getCurrentUrl();
            Assert.assertTrue(actualPage.contains(expectedPage),"The landing page does not contain " + expectedPage);
            WebElement restaurant = driver.findElement(By.id("nav-restaurants-3"));
            Thread.sleep(3000);
            restaurant.click();
            String expectedUrl = "restaurant";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrl),"The actual URL does not contain "+ expectedUrl);
            WebElement restrButton = driver.findElement(By.xpath("//button[@id=\"tagsFilter-0-entertainment-btn\"]"));
            Thread.sleep(3000);
            restrButton.click();
            WebElement asian = driver.findElement(By.xpath("//*[@id=\"tagsFilter\"]/div/ul/li[3]/a"));
            asian.click();
            String expectedURL = "asian";
            String actualURL = driver.getCurrentUrl();
            Assert.assertTrue(actualURL.contains(expectedURL), "The actual Url does not contain " + expectedURL);

            WebElement morimoto = driver.findElement(By.xpath("//div[@id='cta-dmp-en-properties-66964e2b-2550-4476-84c3-" +
                    "1a4c0c5c067f-restaurants-9a09a36d-77a4-4cbd-966e-08dea25a5df9']/a"));
            morimoto.click();


            Thread.sleep(4000);
            WebElement otherDates = driver.findElement(By.xpath("//button[@id=\"d-chageDate-btn\"]"));
            Thread.sleep(3000);
            otherDates.click();
            Thread.sleep(4000);

            WebElement date = driver.findElement(By.xpath("//*[@id=\"c-20-6-2019\"]/button/span[2]"));
            Thread.sleep(4000);
            date.click();
            date.click();
            Thread.sleep(3000);
            WebElement numGuests = driver.findElement(By.id("rest-resrv-guests"));
            Select selectGuests = new Select(numGuests);
            Thread.sleep(3000);
            selectGuests.selectByVisibleText("8 Guests");
            Thread.sleep(3000);
            WebElement timeOfMeal = driver.findElement(By.id("rest-resrv-hours"));
            Select selectHours = new Select(timeOfMeal);
            Thread.sleep(3000);
            selectHours.selectByIndex(4);
            Thread.sleep(3000);
            driver.findElement(By.xpath("//h4[@id='d-special-req-hdr']")).click();
            driver.findElement(By.xpath("//textarea[@id='special-reqs']")).sendKeys("Can I get something to eat please");
            driver.findElement(By.xpath("//button[@id='d-step2-btn']")).click();
            Faker faker = new Faker();
            driver.findElement(By.id("rest-resrv-email"))
                    .sendKeys(faker.internet().emailAddress());
            driver.findElement(By.id("rest-resrv-first-name")).sendKeys(faker.name().firstName());
            driver.findElement(By.id("rest-resrv-last-name")).sendKeys(faker.name().lastName());
            driver.findElement(By.id("rest-resrv-phone")).sendKeys(faker.phoneNumber().cellPhone().replace(".","").replace("(","").replace(")",""));
            driver.findElement(By.id("confirmTable-btn")).click();
            Thread.sleep(6000);
            String expectedConfirmation = "confirmation";
            String actualConfirmation = driver.getCurrentUrl();

            Assert.assertTrue(actualConfirmation.contains(expectedConfirmation),"Actual Url does not contain " + expectedConfirmation);

            Thread.sleep(3000);
            WebElement cancelDineButton = driver.findElement(By.xpath("//a[@class='reservation-action-btn btn-medium']"));
            cancelDineButton.click();

        }

        @Test

        /* User should be able to:
         * navigated to the provided url
         * click to 'Restaurants'
         * see all the filter buttons for the restaurants(Regions,Cuisine,Price,Meal)
         */
        public void restaurantFiltersDisplayed() throws InterruptedException{

            String expectedPage = "mgmresorts";
            String actualPage = driver.getCurrentUrl();
            Assert.assertTrue(actualPage.contains(expectedPage),"The landing page does not contain " + expectedPage);
            WebElement restaurant = driver.findElement(By.id("nav-restaurants-3"));
            Thread.sleep(3000);
            restaurant.click();
            String expectedUrl = "restaurant";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrl),"The actual URL does not contain "+ expectedUrl);
            WebElement allRegions = driver.findElement(By.id("filter-1-btn"));
            Assert.assertTrue(allRegions.isDisplayed(),"'All Regions' button is not displayed");
            String allRegionsButton = allRegions.getText();
            System.out.println(allRegionsButton);

            WebElement cuisineButton = driver.findElement(By.id("tagsFilter-0-entertainment-btn"));
            Assert.assertTrue(cuisineButton.isDisplayed(),"'Cuisine' button is not displayed");
            String cuisineButtonText = cuisineButton.getText();
            System.out.println(cuisineButtonText);

            WebElement priceButton = driver.findElement(By.id("tagsFilter1-1-entertainment-btn"));
            Assert.assertTrue(priceButton.isDisplayed(),"'Price' button is not displayed");
            String priceButtonText = priceButton.getText();
            System.out.println(priceButtonText);

            WebElement mealFilterButton = driver.findElement(By.id("tagsFilter2-2-entertainment-btn"));
            Assert.assertTrue(mealFilterButton.isDisplayed(), "'Meal' button is not displayed");
            String mealsButtonText = mealFilterButton.getText();
            System.out.println(mealsButtonText);

        }

        @Test
        /* User should be able to:
         * navigated to the provided url
         * click on "Restaurants"
         * see and click "Discover more" button
         * see and click "Steakhouses" button
         * scrolled down to the "Steakhouses" section on the page
         */
        public void displaySelectedRestaurantType() throws InterruptedException{

            String expectedPage = "mgmresorts";
            String actualPage = driver.getCurrentUrl();
            Assert.assertTrue(actualPage.contains(expectedPage),"The landing page does not contain " + expectedPage);
            WebElement restaurant = driver.findElement(By.id("nav-restaurants-3"));
            Thread.sleep(3000);
            restaurant.click();
            String expectedUrl = "restaurant";
            String actualUrl = driver.getCurrentUrl();
            Assert.assertTrue(actualUrl.contains(expectedUrl),"The actual URL does not contain "+ expectedUrl);

            WebElement moreRestaurantsButton = driver.findElement(By.linkText("Discover more"));
            moreRestaurantsButton.click();
            Thread.sleep(6000);
            String expectedTitle = "Best Restaurants";
            String actualTitle = driver.getTitle();
            Assert.assertTrue(actualTitle.contains(expectedTitle),"Page title does not contain " + expectedTitle);
            System.out.println(driver.getTitle());

            WebElement steakhouses = driver.findElement(By.linkText("Steakhouses"));
            steakhouses.click();
            Thread.sleep(6000);
            String expectedUrlSteak = "Steakhouses";
            String actualURl = driver.getCurrentUrl();
            Assert.assertTrue(actualURl.contains(expectedUrlSteak), "Page Url does not contain " + expectedUrlSteak);





        }

    }
