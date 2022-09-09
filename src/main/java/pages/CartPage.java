package pages;

import components.Item;
import components.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {

    private WebDriver driver;
    private By itemTr = By.xpath("//tbody/tr");
    private By cartHeading = By.xpath("//h2[text()='Checkout']");
    private By payWithCardButton = By.xpath("//button[@class = 'stripe-button-el']");
    private By checkoutTable = By.xpath("//table[@class = 'table table-striped']");
    private By valueLabel = By.id("total");

    private String stripeFrameName = "stripe_checkout_app";

    private By emailField = By.id("email");
    private By cardNumberField = By.id("card_number");
    private By expDateField = By.id("cc-exp");
    private By cvcField = By.id("cc-csc");
    private By zipCodeField = By.id("billing-zip");
    private By submitCartDetailsButton = By.id("submitButton");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPayWithCartButton() {
        driver.findElement(payWithCardButton).click();

        driver.switchTo().frame(stripeFrameName);

        FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(1))
               .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(cvcField));
    }

    public void enterEmail(String email){
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    //Card details could be saved as Class too, but this window is used only in this place
    public void enterCardDetails (String cardNumber4digs1, String cardNumber4digs2, String cardNumber4digs3,
                                   String cardNumber4digs4, String expDateMonth, String expDateYear, String cvc){

        //workaround
        driver.findElement(cardNumberField).clear();
        driver.findElement(cardNumberField).sendKeys(cardNumber4digs1);
        driver.findElement(cardNumberField).sendKeys(cardNumber4digs2);
        driver.findElement(cardNumberField).sendKeys(cardNumber4digs3);
        driver.findElement(cardNumberField).sendKeys(cardNumber4digs4);

        driver.findElement(expDateField).clear();
        driver.findElement(expDateField).sendKeys(expDateMonth);
        driver.findElement(expDateField).sendKeys(expDateYear);

        driver.findElement(cvcField).clear();
        driver.findElement(cvcField).sendKeys(cvc);

    }

    public void enderZipCode(String zipCode){

        driver.findElement(zipCodeField).clear();
        driver.findElement(zipCodeField).sendKeys(zipCode);
    }

    public ConfirmationPage clickOnConfirmationPage(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(submitCartDetailsButton));

        driver.findElement(submitCartDetailsButton).sendKeys(Keys.ENTER);

        return new ConfirmationPage(driver);
    }

    public int getTotalCartValue(){
        return Integer.parseInt(driver.findElement(valueLabel).getText().replaceAll("\\D+",""));
    }

    public boolean compareAll(List<Product> productList, List<Item> itemList){

        boolean result = false;

        for (Product product : productList){
            for (Item item : itemList){
                result = product.getHeader().equals(item.getName()) && product.getPrice() == item.getPrice();
            }
        }
        return result;
    }

    public List<Item> createItemsList(){

        List<WebElement> itemTrList = driver.findElements(itemTr);
        List<Item> itemList = new ArrayList<>();

        for (WebElement webElement : itemTrList) {
            Item item = new Item(webElement);
            item.setName();
            item.setPrice();
            itemList.add(item);
        }
        return itemList;
    }

    public int sumItemPrice(List<Item> itemList){
        int sum = 0;
        for (Item item : itemList) {
            sum += item.getPrice();
        }
        return sum;
    }

    public boolean compareSum(int productsPrices, int itemsInCartPrice){
        return productsPrices == itemsInCartPrice;
    }

    public boolean cartPageIsDisplayed() {
        return driver.findElement(cartHeading).isDisplayed()
        && driver.findElement(payWithCardButton).isDisplayed()
        && driver.findElement(checkoutTable).isDisplayed();
    }
    }