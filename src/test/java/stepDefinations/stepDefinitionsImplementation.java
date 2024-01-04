package stepDefinations;

import PageObjects.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class stepDefinitionsImplementation extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogPage productCatalogPage;
    public OrderPage orderPage;
    @Given("I landed on E-commerce Page")
    public void I_landed_on_E_commerce_Page() throws IOException {
        landingPage = launchApplication();

    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String email, String password){
        productCatalogPage = landingPage.loginApplication(email,password);
    }

    @When("^I want to add the product (.+) to the cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalogPage.getProductList();
        productCatalogPage.addProductToCart(productName);
    }
    @And("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) throws InterruptedException {
        CartPage cartPage = productCatalogPage.goToCartPage();
        Assert.assertTrue(cartPage.verifyProductAddedToTheCart(productName));
        PaymentPage paymentPage  = cartPage.checkOut();
        String countryName = "India";
        paymentPage.selectCountry(countryName);
        orderPage = paymentPage.placeOrder();
    }

    @Then("{string} message is displayed on confirmation page")
    public void message_displayed_confirmation_page(String message){
        String confirmMessage = orderPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.trim().contains(message));
    }

    @Then("{string} message is displayed")
    public void login_error_validation_message_is_displayed(String errorMessage){
       Assert.assertEquals(landingPage.getErrorMessage(),errorMessage);
       driver.close();
    }

}
