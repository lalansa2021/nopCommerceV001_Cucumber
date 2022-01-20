Feature: Customers

  Background: Below are the common steps for every scenario
    Given User launch chrome browser
    Then User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters Email as "admin@yourstore.com" and Password as "admin"
    And User clicks on Login button
    Then User can view Dashboard

  @sanity
  Scenario: Add a customer
    When User click on Customers Menu
    And User clicks on Customers Menu item
    And User clicks on Add New button
    Then User can view the Add new customer page
    When User enters customer information
    And click on save button
    Then User can view the confirmation message "The new customer has been added successfully"
    And Close the browser

  @sanity
  Scenario: Search customer by EmailId
    When User click on Customers Menu
    And User clicks on Customers Menu item
    And User enters valid Email Id
    And User clicks on Search button
    Then User should found Email in the search table
    And Close the browser

  @regression
  Scenario: Search customer by First Name and Last Name
    When User click on Customers Menu
    And User clicks on Customers Menu item
    And User enters Customer First Name
    And User enters Customer Last Name
    When User clicks on Search button
    Then User should found Name in the search table
    And Close the browser


