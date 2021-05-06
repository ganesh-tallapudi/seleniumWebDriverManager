package com.sage.ui.qa.test;

import org.testng.annotations.Test;

import com.sage.qa.base.InitializeBase;
import com.sage.qa.businessComponents.HomePage;
import com.sage.qa.businessComponents.LoginPage;
import com.sage.qa.businessComponents.PerformancePage;

public class PeopleObjectiveTest extends InitializeBase {

	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();
	PerformancePage performancePage = new PerformancePage();

	/**
	 * @Description : Test to verify user to login into Salesforce SAGE
	 *              application and must able to create an objective - happy
	 *              path flow
	 * @throws Exception
	 */
	@Test
	public void create_people_objective_TC() {

		loginPage.loginIntoApplication();
		homePage.navigateInHomePage();
		performancePage.createNewObjective();
		loginPage.logOutOfApplication();
	}

	
}
