package org.cimmtouchs.initializer;

import org.framework.controller.MainController;
import org.openqa.selenium.support.PageFactory;
import org.cimmtouchs.pageobjects.HomePageObjects;
import org.cimmtouchs.pageobjects.LoginPopUpPageObjects;
import org.cimmtouchs.pageobjects.ProductDetailsPageObjects;

/**
 * Created by hemanthsridhar on 9/22/16.
 */
public class PageFactoryInitializer {

   public MainController mainController = new MainController();
    public HomePageObjects homePage(){
        return PageFactory.initElements(mainController.getDriver(),HomePageObjects.class);
    }

    public LoginPopUpPageObjects loginPopUp(){
        return PageFactory.initElements(
                mainController.getDriver(),LoginPopUpPageObjects.class);
    }

    public ProductDetailsPageObjects productDetailsPage(){
        return PageFactory.initElements(
                mainController.getDriver(),ProductDetailsPageObjects.class);
    }
}
