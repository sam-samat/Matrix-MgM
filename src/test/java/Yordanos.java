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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class Yordanos {
   WebDriver driver;
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.mgmresorts.com/");
        WebElement RestaurantButton = driver.findElement(By.id("nav-restaurants-3"));
        RestaurantButton.click();
    }
    @Test
    public void titelVerificationTest() {
        //verification of the titel Restaurants
        WebElement RestaurantButton = driver.findElement(By.id("nav-restaurants-3"));
        RestaurantButton.click();
        String restaurantVerification = driver.getTitle();
        Assert.assertTrue(restaurantVerification.contains("Restaurants"));
    }
    @Test(priority = 1)
    public void buttonsVerificationTest() {
        //Locating the all regions button
        WebElement allRegionsText = driver.findElement(By.id("filter-1-btn"));
        //allRegionsText.getText();
        String actualAllRegionText = "All Regions";
        Assert.assertTrue(allRegionsText.getText().equals(actualAllRegionText));
        //Locating the Cuisine button
        WebElement expectedCuisineText = driver.findElement(By.id("tagsFilter-0-entertainment-btn"));
        String actualCuisineText = "Cuisine";
        Assert.assertTrue(expectedCuisineText.getText().equals(actualCuisineText));
        //Locating the Price button
        WebElement expectedPriceText = driver.findElement(By.id("tagsFilter1-1-entertainment-btn"));
        String actualPriceText = "Price";
        Assert.assertTrue(expectedPriceText.getText().equals(actualPriceText));
        //Locating the Meal button
        WebElement expectedMealText = driver.findElement(By.id("tagsFilter2-2-entertainment-btn"));
        String actualMealText = "Meal";
        Assert.assertTrue(expectedMealText.getText().equals(actualMealText));
    }
    @Test(priority = 2)
    public void dropDownVerificationTest() {
        //Locating all regions dropDown list
        WebElement allRegionsDropDown = driver.findElement(By.id("filter-1-btn"));
        allRegionsDropDown.click();
        List<WebElement> allRegionListOfLinks = driver.findElements(By.xpath("//button[@id='filter-1-btn']/../div/ul//li"));
        System.out.println("List of Links " + allRegionListOfLinks.size());
        ArrayList<String> allRegionsWebelementsText = new ArrayList<String>();
        for (WebElement allRegionsLink : allRegionListOfLinks) {
            if (!allRegionsLink.getText().isEmpty()) {
                allRegionsWebelementsText.add(allRegionsLink.getText());
                System.out.println("All Regions Link of Lists: " + allRegionsLink.getText());
            }
        }
        Assert.assertEquals(allRegionsWebelementsText.get(2), "Detroit", "City names verifications failed");

        //Locating cuisine dropDown list
        WebElement cuisineDropDown = driver.findElement(By.id("tagsFilter-0-entertainment-btn"));
        cuisineDropDown.click();
        List<WebElement> cuisineListOfLinks = driver.findElements(By.xpath("//button[@id='tagsFilter-0-entertainment-btn']/../div/ul//li"));
        System.out.println("List of Links " + cuisineListOfLinks.size());
        ArrayList<String> cuisineWebelementsText = new ArrayList<String>();
        for (WebElement cuisineLink : cuisineListOfLinks) {
            if (!cuisineLink.getText().isEmpty()) {
                cuisineWebelementsText.add(cuisineLink.getText());
                System.out.println("Cuisines Link of Lists: " + cuisineLink.getText());
            }
        }
        Assert.assertEquals(cuisineWebelementsText.get(3), "Buffet", "Cuisine names verifications failed");
        //Locating price dropDown list
        WebElement priceDropDown = driver.findElement(By.id("tagsFilter1-1-entertainment-btn"));
        priceDropDown.click();
        List<WebElement> priceListOfLinks = driver.findElements(By.xpath("//button[@id='tagsFilter1-1-entertainment-btn']/../div/ul//li"));
        System.out.println("List of Links " + priceListOfLinks.size());
        ArrayList<String> priceWebelementsText = new ArrayList<String>();
        for (WebElement priceLink : priceListOfLinks) {
            if (!priceLink.getText().isEmpty()) {
                priceWebelementsText.add(priceLink.getText());
                System.out.println("Price Link of Lists: " + priceLink.getText());
            }
        }
        Assert.assertEquals(priceWebelementsText.get(5), "$$$$", "Price names verifications failed");
        //Locating meal dropDown list
        WebElement mealDropDown = driver.findElement(By.id("tagsFilter2-2-entertainment-btn"));
        mealDropDown.click();
        List<WebElement> mealListOfLinks = driver.findElements(By.xpath("//button[@id='tagsFilter2-2-entertainment-btn']/../div/ul//li"));
        System.out.println("List of Links " + mealListOfLinks.size());
        ArrayList<String> mealWebelementsText = new ArrayList<String>();
        for (WebElement mealLink : mealListOfLinks) {
            if (!mealLink.getText().isEmpty()) {
                mealWebelementsText.add(mealLink.getText());
                System.out.println("Meal Link of Lists: " + mealLink.getText());
            }
        }
        Assert.assertEquals(mealWebelementsText.get(6), "Dessert", "Meal names verifications failed");
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
