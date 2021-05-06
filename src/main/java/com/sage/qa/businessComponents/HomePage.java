package com.sage.qa.businessComponents;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;
import com.sage.qa.Utils.CustomException;
import com.sage.qa.base.InitializeBase;
import com.sage.qa.base.ReportBean;
import com.sage.qa.businessObjects.HomePageObjectRepo;
import com.sage.qa.commonComponents.ResuableComponents;

/**
 * @Description : To perform operations in home page once logs into application
 * 
 */
public class HomePage {

	ResuableComponents reuseComponent = InitializeBase.reuseComponent;
	private WebDriver driver;
	HomePageObjectRepo homepageRepo = new HomePageObjectRepo();

	/**
	 * @Description : To navigate from home page to Objectives window via Side
	 *              Menu Panel
	 * @Throws : Exception
	 */
	public void navigateInHomePage() throws CustomException {
		try {
			System.out.println("> Home Page navigation..");
			ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, "Home Page",
					"Navigation in Home Page");
			driver = InitializeBase.appBean.getDriver();
			reuseComponent.clickForElementByXPATH(driver, homepageRepo.sideMenuPanel, "Side Menu Panel");
			reuseComponent.clickForElementByXPATH(driver, homepageRepo.perfomanceLink,
					"Performance Link in Side Menu Panel");
			reuseComponent.clickForElementByXPATH(driver, homepageRepo.objectiveLink, "Objective Link");
		} catch (Exception exception) {
			throw new CustomException("Exception occured due to ", exception);
		}

	}

}
