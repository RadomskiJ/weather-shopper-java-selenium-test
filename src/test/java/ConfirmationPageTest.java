
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ConfirmationPage;
import pages.HomePage;
import pages.MoisturizersPage;

import static org.testng.Assert.*;

public class ConfirmationPageTest extends BaseTest{

    /*
    Fill out your payment details and submit the form.
    Verify if the payment was successful.
    The app is setup so there is a 5% chance that your payment failed.
     */



    @Test
    public void successfulConfirmationPageTest() {
        homepage = new HomePage(driver);
        MoisturizersPage moisturizersPage = homepage.clickBuyMoisturizers();
        var moisturizers = moisturizersPage.
                createMoisturizersList();
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "almond");
        moisturizersPage.clickOnLeastExpensiveMoisturizer(moisturizers, "aloe");
        CartPage cartPage = moisturizersPage.clickOnCartPage();
        cartPage.clickPayWithCartButton();
        cartPage.enterEmail("example@eu.pl");
        //5555 5555 5555 4444
        cartPage.enterCardDetails("5555", "5555", "5555", "4444",
                "07", "28", "333");
        cartPage.enderZipCode("33-333");
        ConfirmationPage confirmationPage = cartPage.clickOnConfirmationPage();
        assertEquals(confirmationPage.checkPaymentSuccess(), true, "Successful purchase confirmation is not displayed");

    }
}
