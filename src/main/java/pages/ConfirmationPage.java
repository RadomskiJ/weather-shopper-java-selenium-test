package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ConfirmationPage {

    WebDriver driver;

    By paymentSuccessHeader = By.cssSelector("h2");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }


    public boolean checkPaymentSuccess() {

        driver.switchTo().parentFrame();

        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(9))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchFieldException.class);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(paymentSuccessHeader)));

        return driver.findElement(paymentSuccessHeader).getText().equals("PAYMENT SUCCESS");
    }
}
