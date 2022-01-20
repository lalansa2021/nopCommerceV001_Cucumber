package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;
import org.apache.log4j.*;

import java.util.Properties;

public class BaseClass {

    public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage addCust;
    public SearchCustomerPage searchCust;
    public static Logger logger;
    public Properties configProp;


    // Created for generating random string for unique email id
    public static String randomString(){
        String generateString1= RandomStringUtils.randomAlphabetic(5);
        return (generateString1);
    }
}
