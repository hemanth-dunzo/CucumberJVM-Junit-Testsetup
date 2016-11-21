package org.cimmtouchs.stepdefinitions;
 
import cucumber.api.java.en.Then;
import org.cimmtouchs.initializer.PageFactoryInitializer;
import org.junit.Assert;

/**
 * Created by hemanthsridhar on 9/25/16.
 */
public class ProductDetailsPageStepDefs extends PageFactoryInitializer {
	
    @Then("^\"([^\"]*)\" should be displayed with value \"([^\"]*)\"$")
    public void shouldBeDisplayedWithValue(String attribute, String textSearched) throws Throwable {
    	
        switch (attribute) {
            case "partNumber":
                productDetailsPage().verifyPartNumber(textSearched);
                break;
            case "mpn":
                productDetailsPage().verifyMPN(textSearched);
        }
    }
    
   
}
