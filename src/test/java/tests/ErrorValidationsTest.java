package tests;

import PageObjects.CartPage;
import PageObjects.OrderPage;
import PageObjects.PaymentPage;
import org.openqa.selenium.WebElement;
import testComponents.BaseTest;
import PageObjects.ProductCatalogPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import testComponents.Retry;

import java.io.IOException;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {
    @Test(groups={"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() throws InterruptedException, IOException {
        String productName = "ADIDAS ORIGINAL";
        landingPage.loginApplication("keerthana@gmail.com", "Keerth453545345ana@3");
        Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
    }

    String productName = "ADIDAS ORIGINAL";
    @Test
    public void ProductErrorValidation() throws InterruptedException, IOException {

        ProductCatalogPage productCatalog = landingPage.loginApplication("keerthana@gmail.com","Keerthana@3");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        Assert.assertTrue(cartPage.verifyProductAddedToTheCart(productName));


    }
}
