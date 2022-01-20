package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

    public WebDriver ldriver;

    public AddCustomerPage(WebDriver rdriver){
        ldriver=rdriver;
        PageFactory.initElements(ldriver, this);
    }

    By linkCustomers_Menu=By.xpath("//body/div[3]/aside[1]/div[1]/div[4]/div[1]/div[1]/nav[1]/ul[1]/li[4]/a[1]/p[1]");
    By linkCustomers_Menuitem=By.xpath("//body[1]/div[3]/aside[1]/div[1]/div[4]/div[1]/div[1]/nav[1]/ul[1]/li[4]/ul[1]/li[1]/a[1]/p[1]");

    By btnAddNew=By.xpath("//a[@class='btn btn-primary']");  //Add new button
    By txtEmail=By.id("Email");
    By txtPassword=By.id("Password");

    By txtFirstName=By.id("FirstName");
    By txtLastName=By.id("LastName");

    By rdMaleGender= By.xpath("//label[contains(text(),'Male')]");
    By rdFemaleGender=By.xpath("//label[contains(text(),'Female')]");

    By txtDOB=By.id("DateOfBirth");
    By txtCompanyName=By.id("Company");

    By IsTaxExemptCheckbox=By.id("IsTaxExempt");

    By NewsLetterMultiSelect=By.xpath("//body/div[3]/div[1]/form[1]/section[1]/div[1]/div[1]/nop-cards[1]/nop-card[1]/div[1]/div[2]/div[9]/div[2]/div[1]/div[1]/div[1]/div[1]");
    By NewsLetterSelectOptions_YourStoreName=By.xpath("//li[contains(text(),'Your store name')]");
    By NewsLetterSelectOptions_TestStore2=By.xpath("//li[contains(text(),'Test store 2')]");

    By txtCustomerRoles=By.xpath("//body/div[3]/div[1]/form[1]/section[1]/div[1]/div[1]/nop-cards[1]/nop-card[1]/div[1]/div[2]/div[10]/div[2]/div[1]/div[1]/div[1]/div[1]");
    By CustomerRole_Administrators=By.xpath("//li[contains(text(),'Administrators')]");
    By CustomerRole_ForumModerators=By.xpath("//li[contains(text(),'Forum Moderators')]");
    By CustomerRole_Guest=By.xpath("//li[contains(text(),'Guests')]");
    By CustomerRole_Vendors=By.xpath("//li[contains(text(),'Vendors')]");
    By CustomerRole_Registered=By.xpath("//li[contains(text(),'Registered')]");

    By dropDown_mgrOfVendor=By.xpath("//select[@id='VendorId']");

    By txtAdminComment=By.id("AdminComment");

    By btnSave=By.xpath("//button[@name='save']");

    //Actions Methods

    public String getPageTitle()
    {
        return ldriver.getTitle();
    }

    public void clickOnCustomerMenu(){
        ldriver.findElement(linkCustomers_Menu).click();
    }

    public void clickOnCustomerMenuItem(){

        ldriver.findElement(linkCustomers_Menuitem).click();
    }

    public void clickOnAddNewButton(){

        ldriver.findElement(btnAddNew).click();
    }

    public void setEmail(String email){
        ldriver.findElement(txtEmail).sendKeys(email);
    }

    public void setPassword(String password){

        ldriver.findElement(txtPassword).sendKeys(password);
    }

    public void setFirstName(String firstName){
        ldriver.findElement(txtFirstName).sendKeys(firstName);
    }

    public void setLastName(String lastName){

        ldriver.findElement(txtLastName).sendKeys(lastName);
    }

    public void selectGender(String gender) {
        if (gender.equals("Male")) {
            ldriver.findElement(rdMaleGender).click();
        } else if (gender.equals("Female")) {
            ldriver.findElement(rdFemaleGender).click();
        } else {
            ldriver.findElement(rdMaleGender).click(); // Default value
        }
    }
    public void setDOB(String dob){
        ldriver.findElement(txtDOB).sendKeys(dob);
    }

    public void setCompanyName(String companyName){
        ldriver.findElement(txtCompanyName).sendKeys(companyName);
    }

    public void selectIsTaxExempt(){
        ldriver.findElement(IsTaxExemptCheckbox).click();
    }

    public void selectNewsLetter(String newsLetter) throws InterruptedException {
        ldriver.findElement(NewsLetterMultiSelect).click();
        WebElement Newsitem = null;
        Thread.sleep(3000);
        if (newsLetter.contains("Your store name")) {
            Newsitem = ldriver.findElement(NewsLetterSelectOptions_YourStoreName);
        } else if (newsLetter.contains("Test store 2")) {
            Newsitem = ldriver.findElement(NewsLetterSelectOptions_TestStore2);
        }

//        Newsitem.click();

        JavascriptExecutor js = (JavascriptExecutor)ldriver;
        js.executeScript("arguments[0].click();", Newsitem);
    }

    public void setCustomerRoles(String role) throws InterruptedException {
        if (!role.equals("Vendors")) //if role is vendors
        {
            ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
        }
        ldriver.findElement(txtCustomerRoles).click();

        WebElement listitem;

        Thread.sleep(3000);
        if (role.equals("Administrators")) {
            listitem = ldriver.findElement(CustomerRole_Administrators);
        } else if (role.equals("Forum Moderators")) {
            listitem = ldriver.findElement(CustomerRole_ForumModerators);
        } else if (role.equals("Guests")) {
            listitem = ldriver.findElement(CustomerRole_Guest);
        } else if (role.equals("Vendors")) {
            listitem = ldriver.findElement(CustomerRole_Vendors);
        } else {
            listitem = ldriver.findElement(CustomerRole_Registered);
        }

//        listitem.click();

        JavascriptExecutor js = (JavascriptExecutor) ldriver;
        js.executeScript("arguments[0].click();", listitem);
    }

    public void setManagerOfVendor(String value) {
        Select drp = new Select(ldriver.findElement(dropDown_mgrOfVendor));
        drp.selectByVisibleText(value);
    }

    public void setAdminComment(String comment) {
        ldriver.findElement(txtAdminComment).sendKeys(comment);
    }

    public void clickOnSave() {
        ldriver.findElement(btnSave).click();
    }
}
