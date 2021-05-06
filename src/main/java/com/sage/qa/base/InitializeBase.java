package com.sage.qa.base;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.sage.qa.commonComponents.ResuableComponents;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

/**
 * 	@Description : Base Class to initialize application properties , Selenium WebDriver Instance , Test Reporting 
 * 
 */
public class InitializeBase {
	public static RemoteWebDriver driver = null;
	protected DesiredCapabilities capabilities;
	String userWorkingDirectory = System.getProperty("user.dir");
	public static Properties configProperties;
	private String browserToExecute;
	private String applicationUrl;
	private String applicationUserName;
	private String applicationUserPassword;
	public ExtentReports extentReport = null;
	public ExtentTest extentTest = null;
	public static ResuableComponents reuseComponent = new ResuableComponents();
	public static AppBean appBean = new AppBean();

	@BeforeSuite
	public void setUp() throws IOException, FileNotFoundException {
		configProperties = new Properties();
		FileInputStream ip = new FileInputStream(userWorkingDirectory + "/config.properties");
		configProperties.load(ip);
		browserToExecute = configProperties.get("browserName").toString().trim().toUpperCase();
		applicationUrl = configProperties.get("applicationURL").toString().trim();
		applicationUserName = configProperties.get("applicationUserName").toString().trim();
		applicationUserPassword = configProperties.get("applicationUserPassword").toString();
		appBean.setApplicationUserName(applicationUserName);
		appBean.setApplicationPassword(applicationUserPassword);
		System.out.println("applicationUrl=" + applicationUrl + "  applicationUserName=" + applicationUserName
				+ " applicationUserPassword=" + applicationUserPassword);

		// To set Reports
		extentReport = new ExtentReports(userWorkingDirectory + "/Reports/sage-test-report.html", true);
		ReportBean.getInstance().setExtentReportObject(extentReport);
	}

	@BeforeTest
	public void beforeTest() throws Exception {
		synchronized (this) {
			initalizeBrowser(browserToExecute);
			initalizeApplication();
			initializeTestReport();
		}
	}

	private void initializeTestReport() {

		extentTest = ReportBean.getInstance().getExtentReportObject().startTest(this.getClass().getName());
		ReportBean.getInstance().setExtentTestReportObject(extentTest);
	}
	/*
	 * //@Test public void sageApplicationDemoTest() throws Exception {
	 * 
	 * System.out.println("> Called Test Method sageApplicationDemoTest..");
	 * loginIntoApp(); verifyHomePage();
	 * 
	 * }
	 */
	/*
	 * @Test
	 * 
	 * @Parameters({ "myURL", "myTitle" }) public void testEasy(String myURL,
	 * String myTitle) { driver.get(myURL);
	 * System.out.println("Driver initialized and page is loaded!!!..."); String
	 * title = driver.getTitle();
	 * System.out.println("Title observed from UI ...." + title);
	 * Assert.assertTrue(title.contains(myTitle)); try { Thread.sleep(5000); }
	 * catch (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } // Assert.assertTrue(title //
	 * .contains("Home page Nguoi An Phu huyen An Phu tinh An Giang")); }
	 */

	private void initalizeBrowser(String browserName) {

		switch (browserName) {
		case "CHROME":
			invokeChromeBrowser();
		case "FIREFOX":
			invokeFirefoxBrowser();
		case "IE":
			invokeIEBrowser();

		default:
			// invokeChromeBrowser();
		}

	}

	/*
	 * private void invokeChromeBrowser() {
	 * 
	 * System.out.println(">Initialize Chrome Browser ...");
	 * System.setProperty("webdriver.chrome.driver", userWorkingDirectory +
	 * "/src/main/resources/drivers/chromedriver.exe"); capabilities =
	 * DesiredCapabilities.chrome(); //capabilities.setBrowserName("chrome"); //
	 * DesiredCapabilities capabilities = new DesiredCapabilities();
	 * ChromeOptions options = new ChromeOptions();
	 * //options.addArguments("disable-extensions");
	 * options.addArguments("start-maximized"); //
	 * options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
	 * options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
	 * UnexpectedAlertBehaviour.ACCEPT);
	 * options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	 * 
	 * driver = new ChromeDriver(options); appBean.setDriver(driver);
	 * System.out.println(">Chrome Browser Initialization Completed..."); }
	 */
	private void invokeChromeBrowser() {

		System.out.println(">Initialize Chrome Browser ...");
		WebDriverManager.chromedriver().setup();
		capabilities = DesiredCapabilities.chrome();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		driver = new ChromeDriver(options);
		appBean.setDriver(driver);
		System.out.println(">Chrome Browser Initialization Completed...");
		//driver.manage().
	}

	private void invokeFirefoxBrowser() {
		// Code pending for init of Firefox browser

		/*
		 * System.setProperty("webdriver.gecko.driver",
		 * userWorkingDirectory+"/src/main/resources/drivers/geckodriver.exe");
		 * //DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		 * //capabilities.setCapability("marionette", true); //driver = new
		 * FirefoxDriver(capabilities); FirefoxOptions firefoxOptions = new
		 * FirefoxOptions(); firefoxOptions.setCapability("marionette", true);
		 * driver = new FirefoxDriver(firefoxOptions);
		 */

	}

	private void invokeIEBrowser() {

		// Code pending for init of IE browser
	}

	private void initalizeApplication() {
		System.out.println(">Initialize Application ...");
		driver.get(applicationUrl);
		System.out.println("Driver initialized and page is loaded!!!...");
		String title = driver.getTitle();
		System.out.println("Title observed from UI ...." + title);

		Assert.assertTrue(title.contains("Login | Salesforce"));
		System.out.println(">Initialize Application Completed...");
	}

	@AfterTest
	public void tearDown() {
		// if(driver!=null)
		synchronized(this){
			ReportBean.getInstance().getExtentReportObject().endTest(extentTest);
			ReportBean.getInstance().getExtentReportObject().flush();
			driver.quit();
			
		}
		System.out.println("Closed Webdriver Instance");
	}

	@AfterSuite
	public void afterSuite(){
	ReportBean.getInstance().getExtentReportObject().close();
	System.out.println("Closing Report ...");
	}
}
