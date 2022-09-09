package pages;

import components.Product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MoisturizersPage {

    WebDriver driver;
    private By moisturizersHeading = By.xpath("//h2[text()=\"Moisturizers\"]");
    private By moisturizerDiv = By.xpath("//div[@class=\"text-center col-4\"]");
    private By cartLabel = By.id("cart");
    private By cartLink = By.xpath("//button[@class='thin-text nav-link']");

    private List<Product> moisturizersInCart = new ArrayList<>();


    public MoisturizersPage(WebDriver driver) {
        this.driver = driver;
    }


    public String getCartText(){
        return driver.findElement(cartLabel).getText();
    }

    public int sumProductPrice(List<Product> productList){
        int sum = 0;
        for (int i = 0; i < productList.size(); i++){
            sum += productList.get(i).getPrice();
        }
        return sum;
    }



    public List<Product> getMoisturizersInCart() {
        return moisturizersInCart;
    }

    //In case of most expensive product case
    public void clickOnMostExpensiveMoisturizer(List<Product> moisturizers, String moisturizerCategory){

            List<Product>  categorizedMoisturizers = moisturizers.stream().filter(moisturizer -> moisturizer.getHeader().matches("(?i).*" + moisturizerCategory + ".*")).
                    collect(Collectors.toList());
        Product mostExpensiveMoisturizer =  categorizedMoisturizers.stream().max(Comparator.comparing(Product::getPrice)).get();

            for (int i = 0; i < moisturizers.size(); i++){
                if (moisturizers.get(i).getPrice() == mostExpensiveMoisturizer.getPrice()
                        && moisturizers.get(i).getHeader().equals(mostExpensiveMoisturizer.getHeader())){
                    moisturizers.get(i).getAddButton().click();
                }
            }
    }

    public void clickOnLeastExpensiveMoisturizer(List<Product> moisturizers, String moisturizerCategory){

            List<Product>  categorizedMoisturizers = moisturizers.stream().filter(moisturizer -> moisturizer.getHeader().matches("(?i).*" + moisturizerCategory + ".*")).
                    collect(Collectors.toList());

        Product leastExpensiveMoisturizer =  categorizedMoisturizers.stream().min(Comparator.comparing(Product::getPrice)).get();

        for (Product moisturizer : moisturizers) {
            if (moisturizer.getPrice() == leastExpensiveMoisturizer.getPrice()
                    && moisturizer.getHeader().equals(leastExpensiveMoisturizer.getHeader())) {
                Product resultMoisturizer = moisturizer;
                resultMoisturizer.getAddButton().click();
                moisturizersInCart.add(resultMoisturizer);
            }
        }
    }

    public CartPage clickOnCartPage(){
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }


    public List<Product> createMoisturizersList(){

        List<WebElement> moisturizerDivList = driver.findElements(moisturizerDiv);
        List<Product> moisturizersList = new ArrayList<>();

        for (WebElement webElement : moisturizerDivList) {
            Product moisturizer = new Product(webElement);
            moisturizer.setHeader();
            moisturizer.setButton();
            moisturizer.setPrice();
            moisturizersList.add(moisturizer);
        }
        return moisturizersList;
    }

    public boolean moisturizersPageIsDisplayed() {
        return driver.findElement(moisturizersHeading).isDisplayed()
                && driver.getTitle().equals("The Best Moisturizers in the World!");
    }
}