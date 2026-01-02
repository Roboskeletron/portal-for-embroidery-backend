package ru.vsu.portalforembroidery.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.vsu.portalforembroidery.annotation.DesignerProfilePropertiesRequired;
import ru.vsu.portalforembroidery.model.CustomPrincipal;
import ru.vsu.portalforembroidery.model.Role;
import ru.vsu.portalforembroidery.model.dto.UserDto;

@Component
public class DesignerProfilePropertiesRequiredValidator implements ConstraintValidator<DesignerProfilePropertiesRequired, UserDto> {

    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = (CustomPrincipal) authentication.getPrincipal();

        if (principal.role() != Role.DESIGNER) {
            return true;
        }

        var isValid = true;

        if (userDto.getExperiencedSince() == null) {
            isValid = false;
            addViolation(constraintValidatorContext, "experiencedSince", "Experienced since is required for designers.");
        }

        if (userDto.getDescription() == null || userDto.getDescription().isBlank()) {
            isValid = false;
            addViolation(constraintValidatorContext, "description", "Description is required for designers.");
        }

        return isValid;
    }

    private void addViolation(ConstraintValidatorContext context, String fieldName, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation();
    }
}
