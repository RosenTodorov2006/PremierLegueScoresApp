package bg.soft_uni.premierlegueapp.validation.annotations;

import bg.soft_uni.premierlegueapp.validation.validators.ValidatePasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ValidatePasswordValidator.class)
public @interface ValidPasswords {
    String message() default "Password and confirm password must be equals!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
