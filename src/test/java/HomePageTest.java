import org.testng.annotations.Test;
import pages.HomePage;
import pages.MoisturizersPage;
import pages.SunscreensPage;

import static org.testng.Assert.*;

public class HomePageTest extends BaseTest{

    // test case based on task description -
    // Shop for moisturizers if the weather is below 19 degrees.
    // Shop for suncreens if the weather is above 34 degrees.

    @Test
    public void chooseCorrectProductCategory(){
        homepage = new HomePage(driver);
        int temperature = homepage.getCurrentTemperature();
        System.err.println(temperature);
        if (temperature < 19) {
            MoisturizersPage moisturizersPage = homepage.clickBuyMoisturizers();
            assertTrue(moisturizersPage.moisturizersPageIsDisplayed(), "Moisturizers page is not displayed");
        } else if(temperature > 34){
            SunscreensPage sunscreensPage = homepage.clickBuySunscreens();
            assertTrue(sunscreensPage.sunscreensPageIsDisplayed(), "Sunscreens page is not displayed");
        } else {
            assertTrue(homepage.homepageIsDisplayed(), "Homepage is not displayed");
        }

    }

}
