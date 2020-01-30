package home.assignment.idomsoft.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import home.assignment.idomsoft.service.NationalityService;

public class NationalityValidator implements ConstraintValidator<Nationality, String> {
	
	@Autowired
	private NationalityService nationalityService;
	
	private String nationality;

	@Override
	public void initialize(Nationality constraintAnnotation) {
		this.nationality = constraintAnnotation.value();
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return nationalityService.isNationalityCodePresent(value);
	}
	

}
