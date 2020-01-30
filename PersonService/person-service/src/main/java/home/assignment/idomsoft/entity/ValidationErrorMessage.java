package home.assignment.idomsoft.entity;

import java.util.List;

public class ValidationErrorMessage{
	
	private List<String> errors;
	
	public ValidationErrorMessage() {
	}

	public ValidationErrorMessage(List<String> errors) {
		this.errors = errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
}
