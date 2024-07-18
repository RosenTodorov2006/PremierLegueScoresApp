package bg.soft_uni.premierlegueapp.validation.validators;

import bg.soft_uni.premierlegueapp.services.UserService;
import bg.soft_uni.premierlegueapp.validation.annotations.UniqueEmail;
import bg.soft_uni.premierlegueapp.validation.annotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if(username==null || username.isEmpty()){
            return true;
        }
        return this.userService.isValidUsername(username);
    }

}
