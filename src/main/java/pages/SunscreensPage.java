package pages;

import components.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SunscreensPage {

    WebDriver driver;
    By sunscreensHeading = By.xpath("//h2[text()=\"Sunscreens\"]");
    By sunscreenDiv =  By.xpath("//div[@class=\"text-center col-4\"]");
    By cartLabel = By.id("cart");
    By cartLink = By.xpath("//button[@class='thin-text nav-link']");

    public SunscreensPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCartValue(){
        return driver.findElement(cartLabel).getText();
    }

    public CartPage clickOnCart() {
        driver.findElement(cartLink).click();
        return new CartPage(driver);
    }


    public void clickOnLeastExpensiveSunscreen(List<Product> sunscreens, String productCategory){
        List<Product>  categorizedSunscreens = sunscreens.stream().filter(sunscreen -> sunscreen.getHeader().matches("(?i).*" + productCategory + ".*")).
                collect(Collectors.toList());

        Product leastExpensiveSunscreen =  categorizedSunscreens.stream().min(Comparator.comparing(Product::getPrice)).get();

        for (Product categorizedSunscreen : categorizedSunscreens) {
            if (categorizedSunscreen.getPrice() == leastExpensiveSunscreen.getPrice()
                    && categorizedSunscreen.getHeader().equals(leastExpensiveSunscreen.getHeader())) {
                categorizedSunscreen.getAddButton().click();
            }
        }
    }

    public List<Product> createSunscreensList(){
        List<WebElement> sunscreenDivList = driver.findElements(sunscreenDiv);
        List<Product> sunscreensList = new ArrayList<>();

        for (WebElement webElement : sunscreenDivList) {
            Product sunscreen = new Product(webElement);
            sunscreen.setHeader();
            sunscreen.setPrice();
            sunscreen.setButton();
            sunscreensList.add(sunscreen);
        }
        return sunscreensList;
    }

    public boolean sunscreensPageIsDisplayed() {
        return driver.findElement(sunscreensHeading).isDisplayed()
                && driver.getTitle().equals("The Best Sunscreens in the World!");
    }
}