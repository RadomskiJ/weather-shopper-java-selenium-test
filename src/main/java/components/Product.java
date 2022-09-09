package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {

    private WebElement moisturizer;
    private By headerElement = By.xpath(".//p[@class=\"font-weight-bold top-space-10\"]");
    private By priceValueElement = By.xpath(".//p[contains(text(), 'Price')]");
    private By addButtonElement = By.xpath(".//button[@class=\"btn btn-primary\"]");

    private String header;
    //for price should be used float type, but I decided to use int, because
    //price in application is always not a floating point number
    private int price;
    private WebElement addButton;

    public Product(WebElement moisturizer) {
        this.moisturizer = moisturizer;
    }

    public void setHeader(){
        header = moisturizer.findElement(headerElement).getText();
    }
    public void setPrice(){
        price = Integer.parseInt(moisturizer.findElement(priceValueElement).getText().
                replaceAll("\\D+","").trim());
    }

    public void setButton(){
        addButton = moisturizer.findElement(addButtonElement);
    }

    public WebElement getMoisturizerElement() {
        return moisturizer;
    }

    public String getHeader() {
        return header;
    }

    public int getPrice() {
        return price;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    @Override
    public String toString() {
        return "Moisturizer{" +
                "header='" + header + '\'' +
                ", price=" + price +
                ", element=" + addButtonElement +
                '}';
    }
}
