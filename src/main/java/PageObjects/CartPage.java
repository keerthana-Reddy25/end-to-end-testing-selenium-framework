package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProductsList;

    @FindBy(css = ".totalRow button")
    WebElement checkoutButtonElement;
    public Boolean verifyProductAddedToTheCart(String productName){
        Boolean match = (cartProductsList.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName)));
        return match;
    }

    public PaymentPage checkOut(){
        checkoutButtonElement.click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;

    }
}
