package it.com.idalko.plugins.jira.customfields;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.atlassian.jira.tests.TestBase;
import com.atlassian.plugins.osgi.test.AtlassianPluginsTestRunner;

@RunWith(AtlassianPluginsTestRunner.class)
public class ParenthesesCustomFieldWiredTest extends TestBase {
	
	@Test
	public void testCustomField() {
		NewIssuePage page = jira().gotoLoginPage().loginAsSysAdmin(NewIssuePage.class);
		NewIssuePage submittedPage = page.submitIssueWithValidationError();
		assertEquals("Parentheses not balanced!", submittedPage.getErrorMessage().getText());
	}
}
