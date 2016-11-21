package org.cimmtouchs.stepdefinitions;
import cucumber.api.java.en.And;

import org.framework.controller.MainController;
import org.cimmtouchs.initializer.PageFactoryInitializer;

/**
 * Created by hemanthsridhar on 9/23/16.
 */
public class AttachmentStepdefs extends PageFactoryInitializer {
	MainController main = new MainController();
	@And("^I attach \"([^\"]*)\" files$")
	public void i_attach_files(String fileExt) throws Exception {
		switch(fileExt)
		{
		case "csv" : main.saveCsvAttachment("src/test/resources/TestData/sample.csv");
		break;
		case "xlsx" : main.saveXlsxAttachment("src/test/resources/TestData/cartFileUploadEmptyTemplate.xlsx");
		break;
		case "txt":main.saveTabDelimitedFileAttachment("src/test/resources/TestData/tabDelimited.txt");
		break;
		default: throw new Exception("Invalid file format");
		}
		
    	
    	
	}
}
