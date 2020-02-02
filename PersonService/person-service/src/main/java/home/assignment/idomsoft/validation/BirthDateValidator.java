package home.assignment.idomsoft.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthDateValidator implements ConstraintValidator<BirthDate, String> {

	private static final int OLDEST = 120;
	private static final int YOUNGEST = 18;
	private String birthDate;

	@Override
	public void initialize(BirthDate constraintAnnotation) {
			this.birthDate = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String birthDate, ConstraintValidatorContext context) {
		if (isDateFormatValid(birthDate)) {
			return LocalDate.now().minusYears(YOUNGEST).isAfter(LocalDate.parse(birthDate)) && 
					LocalDate.now().minusYears(OLDEST).isBefore(LocalDate.parse(birthDate));
		}
		return false;
	}

	private boolean isDateFormatValid(String date) {
		try {
			LocalDate.parse(date);
			return true;
		} catch (DateTimeParseException exception) {
			return false;
		}
	}

}
