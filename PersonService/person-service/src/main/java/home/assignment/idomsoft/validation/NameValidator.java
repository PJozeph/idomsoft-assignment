package home.assignment.idomsoft.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

	private String name;
	List validCharacters = new ArrayList<>();

	@Override
	public void initialize(Name constraintAnnotation) {
		this.name = constraintAnnotation.value();
		getValidCharacters();
	}

	@Override
	public boolean isValid(String nameInput, ConstraintValidatorContext context) {
		String withoutDr = nameInput.replace("Dr.", "");
		List<Character> inputCharacters = withoutDr.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

		Optional<Character> validCharachter = inputCharacters.stream().filter(e -> validCharacters.contains(e))
				.findAny();
		if (!validCharachter.isPresent()) {
			return false;
		}

		Predicate<Character> predicate = e -> e.equals(' ');
		long spaceCount = inputCharacters.stream().filter(predicate).count();

		if (spaceCount < 1) {
			return false;
		}

		return true;
	}

	private void getValidCharacters() {
		for (char a = 'a'; a < 'z'; a++) {
			validCharacters.add(a);
		}
	}

}
