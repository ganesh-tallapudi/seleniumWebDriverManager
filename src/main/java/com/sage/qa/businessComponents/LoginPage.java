package com.sage.qa.businessComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.LogStatus;
import com.sage.qa.Utils.CustomException;
import com.sage.qa.base.InitializeBase;
import com.sage.qa.base.ReportBean;
import com.sage.qa.businessObjects.LoginPageObjectRepo;
import com.sage.qa.commonComponents.ResuableComponents;

/**
 * @Description : To perform operation in Login Page
 * 
 *
 */
public class LoginPage {

	private String applicationUserName;
	private String applicationUserPassword;
	//WebDriver driver;
	RemoteWebDriver driver;
	ResuableComponents reuseComponent = new ResuableComponents();
	LoginPageObjectRepo loginPageRepo = new LoginPageObjectRepo();

	/**
	 * @Description : Allow user to login into application successfully - happy
	 *              path
	 * 
	 * 
	 */
	public void loginIntoApplication() {

		System.out.println("> Login Page Validation..");
		ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, "Application Login",
				"To login into application");
		driver = InitializeBase.appBean.getDriver();
		applicationUserName = InitializeBase.appBean.getApplicationUserName();
		applicationUserPassword = InitializeBase.appBean.getApplicationPassword();
		try {
			System.out.println("loginPageRepo.userName-->" + loginPageRepo.userName);
			reuseComponent.typeIfElementIdPresent(driver, loginPageRepo.userName, applicationUserName,
					"Enter Application UserName");
			reuseComponent.typeIfElementIdPresentPwd(driver, loginPageRepo.password, applicationUserPassword,
					"Enter Application Password");
			reuseComponent.clickIfElementIdPresent(driver, loginPageRepo.loginButton);
		} catch (Exception exception) {
			throw new CustomException("Exception occured due to ", exception);
		}

	}

	/**
	 * @Description : Allow user to logout of application successfully - happy
	 *              path
	 * 
	 * 
	 */
	public void logOutOfApplication() throws CustomException {
		ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, "Application LogOut",
				"To logout of application");
		boolean isLogoutSuccess = false;
		try {
			reuseComponent.highWebElement(driver, loginPageRepo.logoutToggle);
			reuseComponent.clickForElementByXPATH(driver, loginPageRepo.logoutToggle,
					"Toggle dropdown clicked for Log Out");
			reuseComponent.clickForElementByXPATH(driver, loginPageRepo.logoutLink, "Logout Link Clicked");
			isLogoutSuccess = verifyIfUserInLoginPage();
			if (isLogoutSuccess)
				ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.INFO, "Application LogOut",
						"Successfully logged out of application");
		} catch (Exception exception) {
			throw new CustomException("Exception occured due to ", exception);
		}

	}

	/**
	 * @Description : To check if user back to login page after he logs out
	 *              succesfully
	 * 
	 * 
	 */
	public boolean verifyIfUserInLoginPage() {

		boolean isLoginPage = false;
		try {
			WebElement element = reuseComponent.findElementById(driver, loginPageRepo.loginLogo);
			if (element != null) {
				isLoginPage = true;
			}
		} catch (Exception exception) {
			throw new CustomException("Exception occured due to ", exception);
		}
		return isLoginPage;
	}

}
