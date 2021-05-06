package com.sage.qa.businessComponents;


import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;
import com.sage.qa.Utils.CustomException;
import com.sage.qa.base.InitializeBase;
import com.sage.qa.base.ReportBean;
import com.sage.qa.businessObjects.ObjectivePageObjectRepo;
import com.sage.qa.commonComponents.ResuableComponents;

public class PerformancePage {

	WebDriver driver;

	ResuableComponents reuseComponent = new ResuableComponents();
	ObjectivePageObjectRepo objectivePageRepo = new ObjectivePageObjectRepo();
	
	/**
	 * @Description : Allow user to create the objective by providing all details needed - happy path
	 *              
	 * 
	 */
	public void createNewObjective() {

		ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, "Create Objective",
				"Started to create New Objective");
		driver = InitializeBase.appBean.getDriver();
		try {
			// to click on New Button
			reuseComponent.clickForElementByXPATH(driver, objectivePageRepo.newButton, "New Button");

			// Objective Name
			reuseComponent.typeForElementByXPATH(driver, objectivePageRepo.objectiveName, "Ganesh Demo",
					"Enter Objective Name");

			// Objective Description
			reuseComponent.typeForElementByXPATH(driver, objectivePageRepo.objectiveDescription, "New User description",
					"Enter Objective Description");

			// Objective Measure
			reuseComponent.typeForElementByXPATH(driver, objectivePageRepo.objectiveMeasure,
					"Demo Measure with Test Automation", "Enter Objective Measure");

			// Strategic goal
			reuseComponent.selectElementByClick(driver, objectivePageRepo.strategicGoal);

			// Contributes To
			reuseComponent.selectElementByClick(driver, objectivePageRepo.contributesTo);

			// Verify the Description and Measure Selected
			reuseComponent.verifyTextByXpath(driver, objectivePageRepo.descriptionSelectedCheck,
					"Grow into Europe through aquisition");

			reuseComponent.verifyTextByXpath(driver, objectivePageRepo.measureSelectedCheck,
					"Identify and purchase company during first half of year");

			// Select Start date
			reuseComponent.clickForElementByXPATH(driver, objectivePageRepo.startDatePickListDropDown,
					"Start Date PickList Dropdown");

			reuseComponent.findAndClickByLinkText(driver, objectivePageRepo.selectStartQuater,
					objectivePageRepo.selectStartQuater);

			reuseComponent.clickForElementByXPATH(driver, objectivePageRepo.endDatePickListDropDown,
					"End Date PickList Dropdown");
			reuseComponent.findAndClickByLinkText(driver, objectivePageRepo.selectendDateQuater,
					objectivePageRepo.selectendDateQuater);
			// Select Next Review date
			reuseComponent.clickForElementByXPATH(driver, objectivePageRepo.nextReviewPickListDropDown,
					"Next Review Date PickList Dropdown");

			reuseComponent.findAndClickByLinkText(driver, objectivePageRepo.selectnextReviewDateQuater,
					objectivePageRepo.selectnextReviewDateQuater);

			// Enter Weight Attribute
			reuseComponent.typeForElementByXPATH(driver, objectivePageRepo.weight, "10", "Enter Weight");

			// Required for Bonus
			reuseComponent.clickElementFromList(driver, objectivePageRepo.bonus, "No", "Select Bonus as");

			// IsPrivate
			reuseComponent.clickElementFromList(driver, objectivePageRepo.isPrivate, "Yes", "Select IsPrivate as");

			// Save Objective Button
			reuseComponent.clickForElementByXPATH(driver, objectivePageRepo.saveButton, "Save Button");
			reuseComponent.waitTillPageLoadCompleted(driver);

		} catch (Exception exception) {
			throw new CustomException("Exception occured due to ", exception);

		}

	}

}
