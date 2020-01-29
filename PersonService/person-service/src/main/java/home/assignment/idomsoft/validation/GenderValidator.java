package home.assignment.idomsoft.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {

	private String gender;
	private String male = "f";
	private String female = "n";

	@Override
	public void initialize(Gender constraintAnnotation) {
		this.gender = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String genderInput, ConstraintValidatorContext context) {
		String gender = genderInput.toLowerCase();
		return gender.equals(male) ? true : gender.equals(female);
	}


}
