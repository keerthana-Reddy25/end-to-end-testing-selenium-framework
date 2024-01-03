package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage extends AbstractComponents {
    WebDriver driver;
    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//input[@class='input txt text-validated'])[2]")
    WebElement countryInputBox;

    @FindBy(xpath = "//section/button")
    List<WebElement> resultedCountryOptions;

    @FindBy(xpath = "/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[2]/div[2]/div/div[2]/a" )
    WebElement placeOrderButton;

    public void selectCountry(String countryName){
        countryInputBox.sendKeys("ind");
        //waitForDynamicElementToBeAppear((WebElement) resultedCountryOptions);
        for (WebElement option:resultedCountryOptions
        ) {
            if(option.getText().trim().equals(countryName)){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
                option.click();
                break;
            }

        }

    }

    public OrderPage placeOrder() throws InterruptedException {
        Thread.sleep(2000);
        placeOrderButton.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;

    }
}