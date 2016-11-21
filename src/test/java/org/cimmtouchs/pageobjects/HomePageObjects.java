package org.cimmtouchs.pageobjects;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.cimmtouchs.initializer.PageFactoryInitializer;

import org.framework.controller.MainController;

/**
 * Created by hemanthsridhar on 9/23/16.
 */
public class HomePageObjects extends PageFactoryInitializer{

    MainController mainController = new MainController();

    @FindBy(how= How.XPATH, using = "//img[@alt='Logo']")
    private WebElement logo;

    @FindBy(how= How.XPATH, using = "//div[contains(@class,'header')]/descendant::ul[@class='cimm_signWrap cimm_signWrapSpace']/descendant::a[text()='Login']")
    private WebElement loginLink;

    @FindBy(how= How.XPATH, using = "//a[@class='cimm_myaccountDropDown']")
    private WebElement nameOfTheUser;

    @FindBy(how= How.XPATH, using = "//ul[contains(@class,'myAccountMenu')]/descendant::a[text()='Log Out']")
    private WebElement logout;

    @FindBy(how= How.ID, using = "txtSearch")
    private WebElement searchTextbox;

    @FindBy(how= How.ID, using = "performSearchBtn")
    private WebElement searchGoButton;

    public HomePageObjects verifyDisplayOfLogo() {
    	Assert.assertTrue("Logo is not displayed",logo.isDisplayed());
        return this;
    }

    public LoginPopUpPageObjects clickOnLoginLink() {
        loginLink.click();
        return loginPopUp();
    }


    public HomePageObjects verifyWelcomeMessage(String verifyWelcomeMsg) {
        WebDriverWait wait = new WebDriverWait(mainController.getDriver(),5);
        wait.until(ExpectedConditions.visibilityOf(nameOfTheUser));
        Assert.assertEquals("Verifying Welcome Message",verifyWelcomeMsg,nameOfTheUser.getText().trim());
        return this;
    }

    public HomePageObjects clickOnLogout() {
        ((JavascriptExecutor)mainController.getDriver()).executeScript("arguments[0].click();",logout);
        return this;
    }

    public HomePageObjects enterSearchText(String searchText) {
        searchTextbox.sendKeys(searchText);
        return this;
    }

    public HomePageObjects clickOnGoButton() {
        searchGoButton.click();
        return this;
    }
}
