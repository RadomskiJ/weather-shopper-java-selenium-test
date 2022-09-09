package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private By buyMoisturizersButton = By.xpath("//a[@href=\"/moisturizer\"]");
    private By buySunscreensButton = By.xpath("//a[@href=\"/sunscreen\"]");
    private By currentTemperatureLabel = By.id("temperature");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public MoisturizersPage clickBuyMoisturizers(){
        clickLink(buyMoisturizersButton);
        return new MoisturizersPage(driver);
    }

    public SunscreensPage clickBuySunscreens(){
        clickLink(buySunscreensButton);

        return new SunscreensPage(driver);
    }

    public boolean homepageIsDisplayed() {
        if (driver.findElement(buyMoisturizersButton).isDisplayed()
                && driver.findElement(buySunscreensButton).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    public int getCurrentTemperature(){
        int currentTemperature = Integer.parseInt(driver.findElement(currentTemperatureLabel).
                getText().substring(0, 2).trim());
        return currentTemperature;
    }

    //helper method
    private void clickLink(By elementBypath){
        driver.findElement(elementBypath).click();
    }

}