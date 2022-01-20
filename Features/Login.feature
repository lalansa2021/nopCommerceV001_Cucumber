Feature: Login

  @sanity
  Scenario: Successful login with valid credentials
    Given User launch chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And User clicks on Login button
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title should be "Your store. Login"
    And Close the browser

    @regression
  Scenario Outline: Login Data Driven
    Given User launch chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "<email>" and Password as "<password>"
    And User clicks on Login button
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Logout link
    Then Page Title should be "Your store. Login"
    And Close the browser

    Examples:
      | email               | password |
      | admin@yourstore.com | admin    |
      | admin@yourstore.com | admin123 |
