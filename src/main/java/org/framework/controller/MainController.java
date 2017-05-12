package org.framework.controller;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import cucumber.api.Scenario;

import cucumber.api.java.After;
import cucumber.api.java.Before;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.SessionId;
import ru.yandex.qatools.allure.annotations.Attachment;
import org.openqa.selenium.remote.HttpCommandExecutor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by hemanthsridhar on 9/22/16.
 */
public class MainController {

	private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

	public String getApplicationURL() {

		return System.getProperty("applicationURL");
	}

	@Attachment(value = "Screenshot of {0}", type = "image/jpg")
	private byte[] attachFailed(String nameOfTheScenario, WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	@Before
	public void setUp() throws Exception {

		DesiredCapabilities dc = new DesiredCapabilities();
		if (System.getProperty("browser").equals("InternetExplorer")) {
			dc.setBrowserName("internet explorer");
		} else {
			dc.setBrowserName(System.getProperty("browser"));
		}
		dc.setJavascriptEnabled(true);
		driver.set(new RemoteWebDriver(new URL(System.getProperty("remoteURL")), dc));
		getDriver().manage().window().maximize();
		getDriver().get(getApplicationURL());

	}

	public WebDriver getDriver() {
		return driver.get();
	}

	private String getVideoURL() throws Exception {
		URL remoteServer = ((HttpCommandExecutor) ((RemoteWebDriver) getDriver()).getCommandExecutor())
				.getAddressOfRemoteServer();

		URL videoUrl = new URL(remoteServer,
				"/grid/admin/HubVideoDownloadServlet/?sessionId=" + ((RemoteWebDriver) getDriver()).getSessionId());
		return videoUrl.toString();
	}

	@After
	public void captureScreenshotIfFailed(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			attachFailed(scenario.getName(), getDriver());
		}
        SessionId sessionId = ((RemoteWebDriver) getDriver()).getSessionId();
		getDriver().quit();
        URL remoteServer = ((HttpCommandExecutor)((RemoteWebDriver) getDriver()).getCommandExecutor()).getAddressOfRemoteServer();
        try {
			saveVideo(scenario.getName(),remoteServer,getDriver(),sessionId);
		} catch (Exception e) {
		}
	}

	public void saveVideo(String testCaseName,URL remoteServer, WebDriver driver,SessionId sessionId) throws Exception {
		URL videoUrl = new URL(remoteServer, "/grid/admin/HubVideoInfoServlet/?sessionId=" + sessionId);
		Response response = RestAssured.given().when().get(videoUrl);
        String pathOfTheFile = JsonPath.read(response.asString(), "$.additional.path");
		attachVideo(testCaseName,pathOfTheFile);
	}

	private byte[] getFile(String fileName) throws Exception {
		File file = new File(fileName);
		return Files.readAllBytes(Paths.get(file.getAbsolutePath()));

	}

	@Attachment(value = "Video of {0}",type="video/webm")
	public byte[] attachVideo(String testCaseName,String path) throws Exception {
		return getFile(path);
	}

	@Attachment(value = "json {0} attachment", type = "text/json")
	private byte[] attachJSONFile(String attachmentName, String file) {
		return file.getBytes();
	}

	@Attachment(value = " csv attachment", type = "text/csv")
	public byte[] saveCsvAttachment(String filePath) throws Exception {
		return getFile(filePath);
	}

	@Attachment(value = "xlsx attachment")
	public byte[] saveXlsxAttachment(String filePath) throws Exception {
		return getFile(filePath);
	}

	@Attachment(value = " text attachment", type = "text/plain")
	public byte[] saveTabDelimitedFileAttachment(String filePath) throws Exception {
		return getFile(filePath);
	}

	@Attachment(value = "text attachment", type = "text/xml")
	public byte[] saveXMLFileAttachment(String filePath) throws Exception {
		return getFile(filePath);
	}

	@Attachment(value = "xlsx {0} attachment")
	private byte[] attachXLSXFile(String attachmentName, String file) {
		return file.getBytes();
	}

	@Attachment(value = "xml {0} attachment", type = "text/xml")
	private byte[] attachXMLFile(String attachmentName, String file) {
		return file.getBytes();
	}

	@Attachment(value = "text {0} attachment", type = "text/plain")
	private byte[] attachTextFile(String attachmentName, String file) {
		return file.getBytes();
	}

	@Attachment(type = "text/html")
	private byte[] videoURL(String file) {
		return file.getBytes();
	}

	@Attachment(value = "csv {0} attachment", type = "text/csv")
	private byte[] attachCSVFile(String attachmentName, String file) {
		return file.getBytes();
	}

	public void attachFile(String fileName, String attachmentName, String format) throws Exception {
		if (format.equals("xlsx")) {
			attachXLSXFile(attachmentName, fileName);
		} else if (format.equals("xml")) {
			attachXMLFile(attachmentName, fileName);
		} else if (format.equals("txt")) {
			attachTextFile(attachmentName, fileName);
		} else if (format.equals("csv")) {
			attachCSVFile(attachmentName, fileName);
		} else if (format.equals("json")) {
			attachJSONFile(attachmentName, fileName);
		}
	}

}
