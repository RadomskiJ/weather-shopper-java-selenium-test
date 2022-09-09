import org.testng.annotations.Test;
import pages.HomePage;
import pages.SunscreensPage;

import static org.testng.Assert.assertEquals;

public class SunscreensPageTest extends BaseTest{

    // test case based on task description -
    // select the least expensive SPF-50 sunscreen
    // for your second sunscreen, select the least expensive SPF-30 sunscreen

    @Test
    public void addingSunscreensTest(){
        homepage = new HomePage(driver);
        SunscreensPage sunscreensPage = homepage.clickBuySunscreens();
        var sunscreens = sunscreensPage.createSunscreensList();
        sunscreensPage.clickOnLeastExpensiveSunscreen(sunscreens, "SPF-50");
        sunscreensPage.clickOnLeastExpensiveSunscreen(sunscreens, "SPF-30");
        assertEquals(
                sunscreensPage.getCartValue(),
                "2 item(s)", "Incorrect number of items added to cart");
    }

}
