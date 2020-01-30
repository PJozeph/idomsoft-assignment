package home.assignment.idomsoft.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = BirthDateValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDate {
	
	public String value() default "";

	public String message() default "Person has to be between 18 and 120 years old";

	public Class<?>[] groups() default {};

	public Class<? extends Payload>[] payload() default {};

}
