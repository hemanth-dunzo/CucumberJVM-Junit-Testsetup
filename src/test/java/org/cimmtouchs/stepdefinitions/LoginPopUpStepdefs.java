package org.cimmtouchs.stepdefinitions;

import cucumber.api.java.en.And;
import org.apache.commons.lang3.StringUtils;
import org.cimmtouchs.initializer.PageFactoryInitializer;

/**
 * Created by hemanthsridhar on 9/23/16.
 */
public class LoginPopUpStepdefs extends PageFactoryInitializer {

    @And("^I enter username as \"(.*?)\"$")
    public void iEnterUsernameAs(String userName) throws Throwable {
        loginPopUp().enterUsername(userName);
    }

    @And("^I enter password as \"(.*?)\"$")
    public void iEnterPasswordAs(String password) throws Throwable {
        loginPopUp().enterPassword(password);
    }

    @And("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        loginPopUp().clickOnLoginButton();
    }
}
