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
	String sameStringMessage = "Method didn't return same string!";
	String notNullMessage = "Method didn't return null with passed null argument!";
	
    @Before
    public void setup() {    	
    	customField = new ParenthesesCustomField(null, null);
    }
    
    @Test
    public void testValidParentheses() {    	
    	for (String toValidate : validInputStrings) {
    		assertTrue("Balanced parentheses not passed validation!", customField.validateParentheses(toValidate));
    	}
    }
    
    @Test
    public void testInValidParentheses() {    	
    	for (String toValidate : invalidInputStrings) {
    		assertFalse("Unbalanced parentheses passed validation!", customField.validateParentheses(toValidate));
    	}
    }


    @Test(expected=FieldValidationException.class)
    public void testValidationException() throws Exception {
    	for (String toValidate : invalidInputStrings) {
    		customField.getSingularObjectFromString(toValidate);
    	}

    }
    
    @Test
    public void testGetSingularObjectFromString() throws Exception {
    	for (String toValidate : validInputStrings) {
    		assertEquals(sameStringMessage, toValidate, customField.getSingularObjectFromString(toValidate));
    	}

    }
    
    @Test
    public void testGetStringFromSingularObject() throws Exception {
    	for (String toValidate : validInputStrings) {
    		assertEquals(sameStringMessage, toValidate, customField.getStringFromSingularObject(toValidate));
    	}

    }
    
    @Test
    public void testGetSingularObjectFromStringNullReturn() throws Exception {    	
    	assertNull(notNullMessage, customField.getSingularObjectFromString(null));
    }
    
    @Test
    public void testGetStringFromSingularObjectNullReturn() throws Exception {
    	assertNull(notNullMessage, customField.getStringFromSingularObject(null));
    }

}
