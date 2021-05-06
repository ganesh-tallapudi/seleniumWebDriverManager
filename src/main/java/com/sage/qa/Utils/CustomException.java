package com.sage.qa.Utils;

import com.relevantcodes.extentreports.LogStatus;
import com.sage.qa.base.ReportBean;

public class CustomException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomException(){
		super();
		ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.ERROR, "Exception occured due to");
	}
	
	public CustomException(Exception ex){
		super();
		ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.ERROR, "Exception occured due to"+ex.getMessage());
	}
	
	public CustomException(String errorMessage, Throwable err) {
        super(errorMessage, err);
        ReportBean.getInstance().getExtentTestReportObject().log(LogStatus.ERROR, "Exception occured due to-->", err.getMessage());
    }

	
}
