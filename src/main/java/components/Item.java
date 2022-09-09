package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Item {

    private WebElement item;
    private By itemNameLabel = By.xpath("./td[1]");
    private By itemPriceLabel = By.xpath("./td[2]");

    private String name;
    private int price;

    public Item(WebElement item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        name = item.findElement(itemNameLabel).getText();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice() {
        price = Integer.parseInt(item.findElement(itemPriceLabel).getText());
    }

    @Override
    public String toString() {
        return "Item{" +
                "item=" + item +
                ", itemNameLabel=" + itemNameLabel +
                ", itemPriceLabel=" + itemPriceLabel +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
