package home.assignment.idomsoft.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
	
	public String value() default "";
	
	public String message() default  "Only Hungarian charaters and ä,-,', / are allowed ";
	
	public Class <?>[] groups() default {};
	
	public Class <? extends Payload> [] payload() default {}; 
}
