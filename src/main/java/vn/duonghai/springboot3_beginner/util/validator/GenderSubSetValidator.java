package vn.duonghai.springboot3_beginner.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.duonghai.springboot3_beginner.util.Gender;

import java.util.Arrays;

public class GenderSubSetValidator implements ConstraintValidator<GenderSubSet, Gender> {
    private Gender[] genders;

    @Override
    public void initialize(GenderSubSet constraint) {
        this.genders = constraint.anyOf();
    }

    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(genders).contains(value);
    }
}
