package com.sage.qa.businessObjects;

/**
 * @Description : Home Page DOM objects to be listed here
 * 
 * 
 */

public class ObjectivePageObjectRepo {

	public final String newButton = "//*[@operation-id='create']/button";
	public final String objectiveName = "//input[@type='text'][@name='name'][@ng-model='data.name']";
	public final String objectiveDescription = "//textarea[@name='description'][@ng-model='data.description']";
	public final String objectiveMeasure = "//textarea[@name='measure'][@ng-model='data.measure']";
	public final String strategicGoal = "//select[@ng-model='data.strategicObjective']/option[@value='People and Processes']";
	public final String contributesTo = "//select[@ng-model='data.contributesTo']/option[@label='Grow into Europe']";
	public final String descriptionSelectedCheck = "//div[@ng-if='data.contributesTo.additionalInformation.description']/div";
	public final String measureSelectedCheck = "//div[@ng-if='data.contributesTo.additionalInformation.measure']/div";
	public final String startDatePickListDropDown = "//datepicker-directive[@ng-model='data.startDate']//div[@class='arrow-container date-blur']";
	public final String selectStartQuater = "Start Q2";
	public final String endDatePickListDropDown = "//datepicker-directive[@ng-model='data.endDate']//div[@class='arrow-container date-blur']";
	public final String selectendDateQuater = "End Q2";
	public final String nextReviewPickListDropDown = "//datepicker-directive[@ng-model='data.nextReviewDate']//div[@class='arrow-container date-blur']";
	public final String selectnextReviewDateQuater = "End Q2";
	public final String weight = "//input[@name='weight'][@ng-model='data.weight']";
	public final String bonus = "//button[@ng-model='data.requiredForBonus']";
	public final String isPrivate = "//button[@ng-model='data.isPrivate']";
	public final String saveButton = "//button[@class='btn modal-save ng-binding'][@type='button'][text()='Save']";

}
