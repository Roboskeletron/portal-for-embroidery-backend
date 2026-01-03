package ru.vsu.portalforembroidery.model.dto;

import ru.vsu.portalforembroidery.annotation.DatetimeValid;

import jakarta.validation.constraints.*;


public record BecomeDesignerDto(@DatetimeValid String experiencedSince,
                                @NotNull(message = "Description is null.") @NotBlank(message = "Description is blank.") String description) {

}
