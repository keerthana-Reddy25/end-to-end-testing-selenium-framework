package AbstractComponents;

import PageObjects.VerifyOrderPage;
import PageObjects.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AbstractComponents {
    WebDriver driver;
    public AbstractComponents(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css = "[routerlink *= 'cart']")
    WebElement cartHeader;
    @FindBy(css = "[routerlink *= 'myorders']")
    WebElement orderHeader;

    public CartPage goToCartPage(){
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public VerifyOrderPage goToOrdersPage(){
        orderHeader.click();
        VerifyOrderPage orderPage = new VerifyOrderPage(driver);
        return orderPage;
    }

    public void waitForElementToBeAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated((findBy)));
    }
    public void waitForWebElementToBeAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf((findBy)));
    }
    public void waitForDynamicElementToBeAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf((element)));
    }

    public void waitForElementToBeDisappear(WebElement element) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
//        wait.until(ExpectedConditions.invisibilityOf(element));
        Thread.sleep(1000);
    }




}

