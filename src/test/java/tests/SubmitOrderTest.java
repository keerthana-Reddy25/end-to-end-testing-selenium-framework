package tests;

import PageObjects.*;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
    String productName = "ADIDAS ORIGINAL";
    @Test(dataProvider="getData", groups={"Purchase"})
    public void submitOrder(HashMap<String, String> dataset) throws InterruptedException, IOException {

        ProductCatalogPage productCatalog = landingPage.loginApplication(dataset.get("email"),dataset.get("password"));
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(dataset.get("productName"));
        CartPage cartPage = productCatalog.goToCartPage();

        Assert.assertTrue(cartPage.verifyProductAddedToTheCart(dataset.get("productName")));
        PaymentPage paymentPage  = cartPage.checkOut();
        String countryName = "India";
        paymentPage.selectCountry(countryName);

        OrderPage orderPage = paymentPage.placeOrder();
        String confirmMessage = orderPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.trim().contains("THANKYOU FOR THE ORDER."));

    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest(){
        ProductCatalogPage productCatalog = landingPage.loginApplication("keerthana@gmail.com","Keerthana@3");
        VerifyOrderPage orderPageNew = productCatalog.goToOrdersPage();
        Assert.assertTrue(orderPageNew.verifyOrderDisplay(productName));

    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> datasets = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//data//PurchaseOrder.json");
        return new Object[][] {{datasets.get(0)},{datasets.get(1)}};
    }



}