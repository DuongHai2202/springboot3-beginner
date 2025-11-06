package vn.duonghai.springboot3_beginner.util.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.duonghai.springboot3_beginner.util.Gender;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Constraint(validatedBy = GenderSubSetValidator.class)
public @interface GenderSubSet {
    Gender[] anyOf();
    String message() default "must be any of {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
