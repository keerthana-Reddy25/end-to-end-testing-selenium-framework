package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VerifyOrderPage extends AbstractComponents {
    WebDriver driver;
    @FindBy(css=".totalRow buttton")
    WebElement checkoutElement;

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public VerifyOrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean verifyOrderDisplay(String givenProductName){
        Boolean match = productNames.stream().anyMatch(productName -> productName.getText().equalsIgnoreCase(givenProductName));
        return match;
    }
}
