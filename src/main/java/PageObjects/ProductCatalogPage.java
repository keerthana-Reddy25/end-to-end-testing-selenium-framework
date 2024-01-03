package PageObjects;

import AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogPage extends AbstractComponents {

    WebDriver driver;
    public ProductCatalogPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css = "div[class='card-body']")
    List<WebElement> products ;

    @FindBy(css = ".ng-animating")
    WebElement bufferElement;
    By productsElementLocator = By.cssSelector("div[class='card-body']");
    By addToCartButtonElement = By.cssSelector("button[class='btn w-10 rounded']");
    By toastMessageElement = By.id("toast-container");
    By cartButtonElement = By.cssSelector("[routerlink*='cart']");



    public List<WebElement> getProductList(){
        waitForElementToBeAppear(productsElementLocator);
        return products;
    }

    //products.stream().filter(prod -> prod.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null)
    public WebElement getProductByName(String productName){
        WebElement product = getProductList().stream().filter(prod -> prod.findElement(By.tagName("h5")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return product;

    }
    public void addProductToCart(String productName) throws InterruptedException {
        WebElement product = getProductByName(productName);
        product.findElement(addToCartButtonElement).click();
        waitForElementToBeAppear(toastMessageElement);
        waitForElementToBeDisappear(bufferElement);
        //driver.findElement(cartButtonElement).click();
        //goToCartPage();

    }
    public CartPage goToCartPage() {

        driver.findElement(cartButtonElement).click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;

    }
}

