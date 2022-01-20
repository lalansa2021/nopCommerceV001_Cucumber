package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {

    public WebDriver ldriver;
    WaitHelper waithelper;

    public SearchCustomerPage(WebDriver rdriver) {
        ldriver=rdriver;
        PageFactory.initElements(ldriver, this);
        waithelper=new WaitHelper(ldriver);
    }

    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(how = How.ID, using = "SearchMonthOfBirth")
    @CacheLookup
    WebElement drpdobMonth;

    @FindBy(how = How.ID, using = "SearchDayOfBirth")
    @CacheLookup
    WebElement getDrpdobDay;

    @FindBy(how = How.ID, using = "SearchCompany")
    @CacheLookup
    WebElement txtCompany;

    @FindBy(how = How.ID, using = "SearchIpAddress")
    @CacheLookup
    WebElement txtIpAddress;

    @FindBy(how = How.XPATH, using = "//div[@class='k-multiselect-wrap k-floatwrap']")
    @CacheLookup
    WebElement txtCustomerRoles;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Administrators')]")
    @CacheLookup
    WebElement listItemAdministrators;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Forum Moderators')]")
    @CacheLookup
    WebElement listItemForumModerators;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Guests')]")
    @CacheLookup
    WebElement listItemGuests;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Vendors')]")
    @CacheLookup
    WebElement listItemVendors;

    @FindBy(how = How.ID, using = "search-customers")
    @CacheLookup
    WebElement btnSearchCustomers;

    @FindBy(how = How.XPATH, using = "//table[@role='grid']")
    @CacheLookup
    WebElement tblSearchResult;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
    @CacheLookup
    WebElement table;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
    List<WebElement> tableColumns;

    //Action method

    public void setEmail(String email)
    {
        waithelper.WaitForElement(txtEmail, 30);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void setFirstName(String fName)
    {
        waithelper.WaitForElement(txtFirstName, 30);
        txtFirstName.clear();
        txtFirstName.sendKeys(fName);
    }

    public void setLastName(String lName)
    {
        waithelper.WaitForElement(txtLastName, 30);
        txtLastName.clear();
        txtLastName.sendKeys(lName);
    }

    public void clickOnSearchButton()
    {
        waithelper.WaitForElement(btnSearchCustomers, 30);
        btnSearchCustomers.click();
    }

    public int getNoOfRows()
    {
        return(tableRows.size());
    }

    public int getNoOfColumn()
    {
        return(tableColumns.size());
    }

    // Method for Search Customer by Email
    public boolean searchCustomerByEmail(String email)
    {
        boolean flag=false;

        for(int i=1;i<=getNoOfRows();i++) {
            String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr["+i+"]/td[2]")).getText();
            System.out.println(emailid);

            if (emailid.equals(email)) {
                flag = true;
            }
        }
        return flag;
    }

    // Method for Search Customer by First Name and Last Name

    public boolean searchCustomerByName(String fullName)
    {
        boolean flag=false;
        int customerCount=0;

        for (int i=1; i<=getNoOfRows();i++) {
            String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[\"+i+\"]/td[3]")).getText();
            System.out.println(name);
            String names[]=name.split(" "); // Seperating First name and Last Name

            if (names[0].equals("Lalan") && names[1].equals("kumar")) {
                flag = true;
                customerCount=customerCount+1;
            }
        }
            System.out.println("No. of customer with Name " + fullName + " is " + customerCount);
            return flag;
    }
}
