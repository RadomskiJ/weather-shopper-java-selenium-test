import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.MoisturizersPage;
import pages.SunscreensPage;

import static org.testng.Assert.*;

public class CartPageTest extends BaseTest{

    /*
    Verify that the shopping cart looks correct.
     */

    @Test
    public void moisturizerCartValueTest(){
        homepage = new HomePage(driver);
        MoisturizersPage moisturizersPage = homepage.clickBuyMoisturizers();
        var moisturizers = moisturizersPage.
                createMoisturizersList();
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "almond");
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "aloe");
        CartPage cartPage = moisturizersPage.clickOnCartPage();
        boolean isCartValEqualAddedItems = cartPage.compareSum(cartPage.getTotalCartValue(),
                cartPage.sumItemPrice(cartPage.createItemsList()));
        assertEquals(isCartValEqualAddedItems, true, "Values are not equal");
    }


    //Pole do refaktoryzacji - porówananie nazwy i ceny naraz (przy użyciu znaku &&)

    @Test
    public void moisturizerProductAddedAndItemCompTest(){
        homepage = new HomePage(driver);
        MoisturizersPage moisturizersPage = homepage.clickBuyMoisturizers();
        var moisturizers = moisturizersPage.
                createMoisturizersList();
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "almond");
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "aloe");
        CartPage cartPage = moisturizersPage.clickOnCartPage();
        boolean isItemAndProductSame = cartPage.compareAll
                (moisturizersPage.getMoisturizersInCart(), cartPage.createItemsList());
        assertEquals(isItemAndProductSame, true, "Items in cart are not the same");
    }

    @Test
    public void isMoisturizerCheckoutDisplayed(){
        homepage = new HomePage(driver);
        MoisturizersPage moisturizersPage = homepage.clickBuyMoisturizers();
        CartPage cartPage = moisturizersPage.clickOnCartPage();
        assertEquals(cartPage.cartPageIsDisplayed(), true, "Cart page is not displayed");
    }

    @Test
    public void isSunscreenCheckoutDisplayed(){
        homepage = new HomePage(driver);
        SunscreensPage sunscreensPage = homepage.clickBuySunscreens();
        CartPage cartPage = sunscreensPage.clickOnCart();
        assertEquals(cartPage.cartPageIsDisplayed(), true, "Cart page is not displayed");
    }
}
