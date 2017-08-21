package it.com.idalko.plugins.jira.customfields;

import com.atlassian.jira.pageobjects.form.FormUtils;
import com.atlassian.jira.pageobjects.framework.elements.ExtendedElementFinder;
import com.atlassian.jira.pageobjects.pages.AbstractJiraPage;
import com.atlassian.pageobjects.elements.ElementBy;
import com.atlassian.pageobjects.elements.PageElement;
import com.atlassian.pageobjects.elements.query.TimedCondition;

public class NewIssuePage extends AbstractJiraPage {
	
	@ElementBy(id="issue-create-submit")
    private PageElement createButton;
	
	@ElementBy(id="customfield_10000")
    private PageElement parentheseInputCustomField;
	
	@ElementBy(cssSelector="div.error")
    private PageElement errorMessage;

	public PageElement getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String getUrl() {
		return "/secure/CreateIssueDetails.jspa";
	}

	@Override
	public TimedCondition isAt() {
		return createButton.timed().isVisible();
	}
	
	public NewIssuePage submitIssueWithValidationError () {
		FormUtils.setElement(parentheseInputCustomField, "(()");
		createButton.click();
		return pageBinder.bind(NewIssuePage.class);
	}

}
