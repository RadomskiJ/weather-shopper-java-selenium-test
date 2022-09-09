import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.MoisturizersPage;

import static org.testng.Assert.*;

public class MoisturizersPageTest extends BaseTest{

    // test case based on task description -
    // select the least expensive moisturizer that contains Aloe
    // for your second moisturizer, select the least expensive moisturizer that contains almond

    @Test
    public void addingMoisturizersTest(){
        homepage = new HomePage(driver);
        MoisturizersPage moisturizersPage = homepage.clickBuyMoisturizers();
        var moisturizers = moisturizersPage.
                createMoisturizersList();
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "almond");
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "aloe");
        String cartText = moisturizersPage.getCartText();
        CartPage cartPage = moisturizersPage.clickOnCartPage();
        assertEquals(
                cartText,
            "2 item(s)", "Incorrect number of items added to cart");
    }


}
