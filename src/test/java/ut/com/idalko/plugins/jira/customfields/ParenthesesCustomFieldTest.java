package ut.com.idalko.plugins.jira.customfields;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.idalko.plugins.jira.customfields.ParenthesesCustomField;

/**
 * @since 3.5
 */
public class ParenthesesCustomFieldTest {
	
	ParenthesesCustomField customField;
	String[] invalidInputStrings = {"(", ")", "(()", "())", "()(", "())(()"};
	String[] validInputStrings = {"()", "()()", "(()())", "(((()))())"};
	
    @Before
    public void setup() {    	
    	customField = new ParenthesesCustomField(null, null);
    }
    
    @Test
    public void testValidParentheses() {    	
    	for (String toValidate : validInputStrings) {
    		assertTrue(customField.validateParentheses(toValidate));
    	}
    }
    
    @Test
    public void testInValidParentheses() {    	
    	for (String toValidate : invalidInputStrings) {
    		assertFalse(customField.validateParentheses(toValidate));
    	}
    }


    @Test(expected=FieldValidationException.class)
    public void testValidationException() throws Exception {
    	for (String toValidate : invalidInputStrings) {
    		customField.getSingularObjectFromString(toValidate);
    	}

    }
    
    @Test
    public void testValidation() throws Exception {
    	for (String toValidate : validInputStrings) {
    		assertEquals(toValidate, customField.getSingularObjectFromString(toValidate));
    	}

    }
    
    @Test
    public void testNullReturn() throws Exception {    	
    	assertNull(customField.getSingularObjectFromString(null));
    	assertNull(customField.getStringFromSingularObject(null));
    }

}
