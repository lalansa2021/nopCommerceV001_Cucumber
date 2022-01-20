package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Steps extends BaseClass{

    @Before
    public void setup() throws IOException {

        // Logger
        logger=Logger.getLogger("nopCommerce");  // Added logger
        PropertyConfigurator.configure("log4j.properties");  //Added logger

        FileInputStream configPropFile=new FileInputStream("config.properties");

        //Reading properties
        configProp=new Properties();
        configProp.load(configPropFile);

        String br=configProp.getProperty("browser");

        if(br.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromePath"));
            driver=new ChromeDriver();
        }
        else if(br.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxPath"));
            driver=new FirefoxDriver();
        }
        else if(br.equals("ie"))
        {
            System.setProperty("webdriver.ie.driver", configProp.getProperty("iePath"));
        }

        logger.info("*********launching Browser*********");
    }

    @Given("User launch chrome browser")
    public void user_launch_chrome_browser() {
        lp=new LoginPage(driver);
    }

    @When("User opens URL {string}")
    public void user_opens_url(String url) {
        logger.info("*********Opening Application*********");
        driver.get(url);
        driver.manage().window().maximize();
    }

    @When("User enters Email as {string} and Password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("*********Entering login details*********");
        lp.setUserName(email);
        lp.setPassword(password);
    }

    @When("User clicks on Login button")
    public void user_clicks_on_login_button() {
        logger.info("*********Started login*********");
        lp.clickLogin();
    }

    @Then("Page Title should be {string}")
    public void page_title_should_be(String title) {

        if (driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            logger.info("*********Login passed*********");
            Assert.assertTrue(false);
        }
        else {
            logger.info("*********Login Failed*********");
            Assert.assertEquals(title, driver.getTitle());
        }
    }

    @When("User click on Logout link")
    public void user_click_on_logout_link() throws InterruptedException {
        logger.info("*********Clicked on logout link*********");
        lp.clickLogout();
        Thread.sleep(3000);
    }

    @Then("Close the browser")
    public void close_the_browser() {
        logger.info("*********Closing Browser*********");
        driver.quit();
    }

    // Customers Feature step definition...

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCust=new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
    }
    @Then("User click on Customers Menu")
    public void user_click_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        logger.info("*********Clicked on Customer Menu*********");
        addCust.clickOnCustomerMenu();
    }
    @Then("User clicks on Customers Menu item")
    public void user_clicks_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(3000);
        logger.info("*********Clicked on Customer menu item*********");
        addCust.clickOnCustomerMenuItem();
    }
    @Then("User clicks on Add New button")
    public void user_clicks_on_add_new_button() throws InterruptedException {
        logger.info("*********Clicked on Add New button*********");
        addCust.clickOnAddNewButton();
        Thread.sleep(3000);
    }
    @Then("User can view the Add new customer page")
    public void user_can_view_the_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
        logger.info("*********Add new customer page is displayed*********");
    }
    @When("User enters customer information")
    public void user_enters_customer_information() throws InterruptedException {
        logger.info("*********Provding customer details*********");
        String email=randomString()+"@gmail.com";
        addCust.setEmail(email);
        addCust.setPassword("test123");
        addCust.setFirstName("Lalan");
        addCust.setLastName("kumar");
        addCust.selectGender("Male");
        addCust.setDOB("09/10/1990");
        addCust.setCompanyName("Google");
        addCust.selectIsTaxExempt();
        addCust.selectNewsLetter("Test store 2");

        //Registered - default
        // The customer can not be in both Guest and 'Registered' customer role
        // Add customer to 'Guests' or 'Registered' customer role
        addCust.setCustomerRoles("Registered");
        addCust.setManagerOfVendor("Vendor 2");
        addCust.setAdminComment("This is for testing purposes");
    }
    @When("click on save button")
    public void click_on_save_button() throws InterruptedException {
        logger.info("*********Saving customer data*********");
        addCust.clickOnSave();
        Thread.sleep(3000);
    }
    @Then("User can view the confirmation message {string}")
    public void user_can_view_the_confirmation_message(String confirmationMessage) {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
                .contains("The new customer has been added successfully"));
    }

    // Steps for searching a customer using Email Id......

    @When("User enters valid Email Id")
    public void user_enters_valid_email_id() {
        logger.info("*********Searching a customer by email id*********");
        searchCust=new SearchCustomerPage(driver);
        searchCust.setEmail("victoria_victoria@nopCommerce.com");
    }
    @When("User clicks on Search button")
    public void user_clicks_on_search_button() throws InterruptedException {
        logger.info("*********Clicked on search button*********");
        searchCust.clickOnSearchButton();
        Thread.sleep(3000);
    }
    @Then("User should found Email in the search table")
    public void user_should_found_email_in_the_search_table() {
        logger.info("*********Searching matched customer with email id in the result table*********");
        boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");

        Assert.assertEquals(true,status);
    }

    // Steps for searching a customer using First Name and Last Name......

    @When("User enters Customer First Name")
    public void user_enters_customer_first_name() {
        logger.info("*********Entered First Name*********");
        searchCust=new SearchCustomerPage(driver);
        searchCust.setFirstName("Lalan");
    }

    @When("User enters Customer Last Name")
    public void user_enters_customer_last_name() {
        logger.info("*********Entered Last Name*********");
        searchCust.setLastName("kumar");
    }

    @Then("User should found Name in the search table")
    public void user_should_found_name_in_the_search_table() {
        logger.info("*********Searching matched customer with Name in the result table*********");
        boolean status = searchCust.searchCustomerByName("Lalan kumar");
        Assert.assertEquals(true, status);
    }
}
