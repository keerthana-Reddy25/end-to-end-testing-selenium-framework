@tag
  Feature:Purchase the order form the e-commerce website

    Background:
      Given I landed on E-commerce Page

    @Regression
    Scenario Outline: Positive test of submitting the order
      Given Logged in with username <email> and password <password>
      When I want to add the product <productName> to the cart
      And Checkout <productName> and submit the order
      Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

      Examples:
        |email                    |password     |productName      |
          |keerthana@gmail.com    |Keerthana@3  |ZARA COAT 3      |
          |keerthi123@gmail.com   |Keerthana@3  |ADIDAS ORIGINAL  |