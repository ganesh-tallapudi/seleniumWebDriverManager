package com.sage.qa.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * 	@Description : To set the instance for Report
 * 
 */
public class ReportBean {

	public ExtentReports extentReportObject;
	public ExtentTest extentTestReportObject;
	public static ReportBean INSTANCE;
	
	public static ReportBean getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new ReportBean();
		}

		return INSTANCE;
	}

	public static void setINSTANCE(ReportBean iNSTANCE) {
		INSTANCE = iNSTANCE;
	}

	public ExtentReports getExtentReportObject() {
		return extentReportObject;
	}

	public void setExtentReportObject(ExtentReports extentReportObject) {
		this.extentReportObject = extentReportObject;
	}

	public ExtentTest getExtentTestReportObject() {
		return extentTestReportObject;
	}

	public void setExtentTestReportObject(ExtentTest extentTestReportObject) {
		this.extentTestReportObject = extentTestReportObject;
	}

	

}
