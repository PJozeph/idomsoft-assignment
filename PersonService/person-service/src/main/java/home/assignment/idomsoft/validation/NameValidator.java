package home.assignment.idomsoft.validation;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

	private String name;

	@Override
	public void initialize(Name constraintAnnotation) {
		this.name = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(String nameInput, ConstraintValidatorContext context) {
		String withoutDr = nameInput.replace("Dr.", "");
		if(!Pattern.matches("^[a-zA-Z\\äóüöőűáéÓŐÖÜŰÁÉ\\.\\\\\\s\\-\\']+$", withoutDr)) {
			return false;
		}
		
		List<Character> inputCharacters = withoutDr.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		Predicate<Character> predicate = e -> e.equals(' ');
		long spaceCount = inputCharacters.stream().filter(predicate).count();
		if (spaceCount < 1) {
			return false;
		}
		return true;
	}
}
