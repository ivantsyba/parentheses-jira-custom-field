package com.idalko.plugins.jira.customfields;

import java.util.Deque;
import java.util.LinkedList;

import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;

@Scanned
public class ParenthesesCustomField extends AbstractSingleFieldType<String> {
	
	@ComponentImport
	CustomFieldValuePersister customFieldValuePersister;	
	@ComponentImport
	GenericConfigManager genericConfigManager;

	public ParenthesesCustomField(
			CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager) {
		super(customFieldValuePersister, genericConfigManager);
		this.customFieldValuePersister = customFieldValuePersister;
		this.genericConfigManager = genericConfigManager;
	}

	@Override
	public String getSingularObjectFromString(String string)
			throws FieldValidationException {
		if (string == null) return null;
		if (!validateParentheses(string)) throw new FieldValidationException("Parentheses not balanced!");
		return string;
	}

	@Override
	public String getStringFromSingularObject(String stringSingularObject) {
		if (stringSingularObject == null) {
			return null; 
		} else {
			return stringSingularObject.toString();
		}
	}

	@Override
	protected PersistenceFieldType getDatabaseType() {
		return PersistenceFieldType.TYPE_UNLIMITED_TEXT;
	}

	@Override
	protected Object getDbValueFromObject(String string) {
		return getStringFromSingularObject(string);
	}

	@Override
	protected String getObjectFromDbValue(Object dbValue)
			throws FieldValidationException {
		return getSingularObjectFromString((String) dbValue);
	}
    
    public static void main (String[] args) {
    	ParenthesesCustomField customField = new ParenthesesCustomField(null, null);
    	String[] inputStrings = {"()", ")()()()(", "()))((()", "(((()))))"};
    	for (String toValidate : inputStrings) {
    		System.out.println(customField.validateParentheses(toValidate));
    	}
    }
    
    public boolean validateParentheses (String toValidate) {
    	Deque<Character> parentheses = new LinkedList<>();
    	
    	for (int i = 0; i < toValidate.length(); i++) {
    		Character character = toValidate.charAt(i);
    		if (character == '(') {
    			parentheses.addLast(character);
    		} else if (character == ')') {
    			if (!parentheses.isEmpty()) {
    				parentheses.removeLast();
    			} else {
    				return false;
    			}
    		}    		
    	}
    	return parentheses.isEmpty();
    }
    
}