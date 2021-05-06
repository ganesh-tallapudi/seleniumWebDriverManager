package com.sage.qa.commonComponents;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.LogStatus;
import com.sage.qa.Utils.CustomException;
import com.sage.qa.base.ReportBean;

/**
 * This class contains all the common methods that could be used by any other
 * class method
 *
 * 
 */
public class ResuableComponents {

	public static String projectPath = System.getProperty("user.dir");

	/**
	 * @returns : to find an element by ID
	 */

	public WebElement findElementById(WebDriver drive, String elementID) throws Exception {
		WebElement element = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(drive).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementID)));
			return drive.findElement(By.id(elementID));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}

	}

	/**
	 * @returns : to find an element by XPATH
	 */

	public static WebElement findElementByXpath(WebDriver drive, String elementID) throws Exception {
		WebElement element = null;
		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(drive).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementID)));
			return drive.findElement(By.xpath(elementID));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(ex);
		}

	}

	/**
	 * @return : to wait till a given element arrives on the page
	 */

	public void waitForElementByID(final WebDriver driver, final String elementID) throws Exception {
		try {

			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementID)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementID)));
		} catch (Exception e) {

		}

	}

	/**
	 * @return : to perform click operation using JavaScript- generally helps when clicking from backEnd
	 */

	public static void jsClick(final WebDriver driver, final String elementXPATH) {

		try {

			((JavascriptExecutor) driver).executeScript("arguments[0].click();",
					driver.findElement(By.xpath(elementXPATH)));

		} catch (Exception e) {
			throw new CustomException(e);
		}

	}

	/**
	 * @return : to wait till a given element arrives on the page
	 */

	public static void waitForElementByXPATH(final WebDriver driver, final String elementXPATH) throws Exception {

		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXPATH)));
		} catch (Exception e) {
			throw new CustomException(e);
		}

		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementXPATH)));

		} catch (Exception e) {
		}

		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXPATH)));

	}

	
	public void waitForElementPresentByXPATH(final WebDriver driver, final String elementXPATH) {

		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXPATH)));

		} catch (Exception exception) {
			throw new CustomException("Exception occured due to ", exception);

		}
	}

	/**
	 * @return : to click till a given element arrives on the page
	 */

	public void clickForElementByXPATH(final WebDriver driver, final String elementXPATH, final String infoDesc)
			throws Exception {
		boolean ElementEnabled = false;
		boolean Elementdisplayed = false;
		boolean pageloaded = false;
		try {

			waitForElementByXPATH(driver, elementXPATH);

			ElementEnabled = driver.findElement(By.xpath(elementXPATH)).isEnabled();
			Elementdisplayed = driver.findElement(By.xpath(elementXPATH)).isDisplayed();

			WebElement element = driver.findElement(By.xpath(elementXPATH));
			if (ElementEnabled && Elementdisplayed) {
				Actions a = new Actions(driver);
				a.moveToElement(driver.findElement(By.xpath(elementXPATH)))
						.click(driver.findElement(By.xpath(elementXPATH))).build().perform();
				ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, infoDesc,
						"Clicked on " + infoDesc);

			}
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	// Creating a custom function to highlight on webElement
	public void highWebElement(WebDriver driver, String elementXpath) {

		WebElement element = driver.findElement(By.xpath(elementXpath));
		if (element.isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 2px solid red;');",
					element);
		}
	}

	/**
	 * @return : to wait till page load is completed
	 */

	public void waitTillPageLoadCompleted(final WebDriver driver) {
		if (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"))
			sleepTime(5);
		else
			sleepTime(10);
	}

	/**
	 * @return : to type till a given element arrives on the page
	 */

	public void typeForElementByXPATH(final WebDriver driver, final String elementXPATH, final String valueToType,
			String infoDesc) {
		boolean Elementdisplayed = false;
		boolean pageloaded = false;

		try {

			waitForElementByXPATH(driver, elementXPATH);

			Elementdisplayed = driver.findElement(By.xpath(elementXPATH)).isDisplayed();

			if (Elementdisplayed) {
				driver.findElement(By.xpath(elementXPATH)).clear();
				driver.findElement(By.xpath(elementXPATH)).sendKeys(valueToType);
				driver.findElement(By.xpath(elementXPATH)).sendKeys(Keys.TAB);

				ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, infoDesc, valueToType);
			}
		} catch (Exception exception) {

			throw new CustomException("Exception occured due to ", exception);
		}

	}

	/**
	 * @return : to wait till a given element arrives on the page and then
	 *         verify if its enabled or not
	 */

	public boolean verifyIfElementEnabled(final WebDriver driver, final String elementID) throws Exception {
		// ExecuteTestSuite.writeToLog("verifyIfElementEnabled method", true);
		waitForElementByID(driver, elementID);
		return (findElementById(driver, elementID).isEnabled());

	}

	/**
	 * @return : to wait till a given element arrives on the page and then click
	 *         on it
	 * @throws Exception
	 */

	public void clickIfElementIdPresent(final WebDriver driver, final String elementID) throws Exception {

		waitForElementByID(driver, elementID);
		driver.findElement(By.id(elementID)).click();
	}

	/**
	 * @return : to wait till a given element arrives on the page and then type
	 *         the provided value in it
	 */

	public void typeIfElementIdPresent(final WebDriver driver, final String elementID, String value, String details)
			throws Exception {
		boolean pageloaded = false;
		try {

			while (!pageloaded) {
				pageloaded = ((JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete");
			}
			waitForElementByID(driver, elementID);
			driver.findElement(By.id(elementID)).sendKeys(value);
			System.out.println("Entered value->" + value);
			ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, details, value);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.ERROR, details,
					"Exception occured due to ->" + e.getMessage());
		}

	}

	public void typeIfElementIdPresentPwd(final WebDriver driver, final String elementID, String value, String details)
			throws Exception {
		boolean pageloaded = false;
		try {

			while (!pageloaded) {
				pageloaded = ((JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete");
			}
			waitForElementByID(driver, elementID);
			driver.findElement(By.id(elementID)).sendKeys(value);
			System.out.println("Entered value->" + value);
			ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, details, "**********************");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.ERROR, details,
					"Exception occured due to ->" + e.getMessage());
		}

	}

	public static void typeIfElementxPATHPresent(final WebDriver driver, final String xpathExpression, String value)
			throws Exception {
		try {
		} catch (Exception e) {

		}
		driver.findElement(By.xpath(xpathExpression)).sendKeys(value);

	}

	public void selectValByEleXpath(final WebDriver driver, final String elementXpath, String value) throws Exception {
		waitForElementByXPATH(driver, elementXpath);
		WebElement selectElement = driver.findElement(By.xpath(elementXpath));
		Select select = new Select(selectElement);
		List<WebElement> options = select.getOptions();
		for (WebElement we : options) {
			System.out.println("Option list: " + we.getText());
			if (we.getText().equals(value)) {
				we.click();
				break;
			}
		}

	}

	public void selectElementByClick(final WebDriver driver, final String elementXpath) throws Exception {
		waitForElementByXPATH(driver, elementXpath);
		WebElement selectElement = driver.findElement(By.xpath(elementXpath));
		if (selectElement != null)
			selectElement.click();

	}

	/**
	 * TO verify the text
	 * 
	 * @throws Exception
	 */
	public void verifyTextByXpath(WebDriver driver, String xpath, String expectedText) throws Exception {
		WebElement element = findElementByXpath(driver, xpath);
		if (element.getText().equals(expectedText)) {
			ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.PASS, "Verify Text",
					"Expected text [" + expectedText + "] exists in page");
		} else
			ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.FAIL, "Verify Text",
					"Expected text [" + expectedText + "] does not exists in page");
	}

	public void sleepTime(int timerInSec) {
		try {
			Thread.sleep(timerInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String generateRandomNumber() {
		DateFormat dateFormat = new SimpleDateFormat("ddHHmmssSSS");
		Date date = new Date();
		String newDate = dateFormat.format(date);
		System.out.println("uniquenumber: " + newDate);
		return newDate;

	}

	public List<WebElement> getObjectsListbyXpath(final WebDriver driver, String value) throws Exception {

		waitForElementPresentByXPATH(driver, value);
		// Get a list of the options
		List<WebElement> webElementList = driver.findElements(By.xpath(value));
		return webElementList;
	}

	public void clickElementFromList(final WebDriver driver, String xpath, String valueToSelect, String infoDesc)
			throws Exception {

		List<WebElement> elementList = getObjectsListbyXpath(driver, xpath);

		for (WebElement we : elementList) {
			if (we.getText().equals(valueToSelect)) {
				try {
					we.click();
					ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, infoDesc, valueToSelect);
					break;
				} catch (Exception exception) {
					throw new CustomException("Exception occured due to ", exception);
				}
			}

		}

	}

	/**
	 * @Desc : To wait for Element
	 * @param driver
	 * @param elementXPATH
	 */
	public static void waitForElementPresentByLinkText(final WebDriver driver, final String elementXPATH) {

		try {
			@SuppressWarnings("deprecation")
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(elementXPATH)));

		} catch (Exception exception) {
			throw new CustomException("Exception occured due to ", exception);

		}

	}

	/**
	 * @Desc : TO perform click operation By Link Text
	 * @param driver 
	 * @param linkText
	 * @param infoDesc
	 */
	public void findAndClickByLinkText(final WebDriver driver, String linkText, String infoDesc) {
		waitForElementPresentByLinkText(driver, linkText);
		WebElement element = driver.findElement(By.linkText(linkText));
		element.click();
		ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, infoDesc, "Clicked on " + infoDesc);
	}

}
