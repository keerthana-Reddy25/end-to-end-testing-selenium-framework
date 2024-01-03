package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponents {
    WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "label[class='ng-star-inserted']")
    WebElement orderIDElement;

    @FindBy(css = ".hero-primary")
    WebElement confirmMessageElement;


    public String getConfirmMessage(){
        waitForDynamicElementToBeAppear(orderIDElement);
        return confirmMessageElement.getText();


    }
}

