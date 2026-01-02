package ru.vsu.portalforembroidery.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import ru.vsu.portalforembroidery.annotation.DatetimeValid;

import jakarta.validation.constraints.*;


@JsonDeserialize(builder = BecomeDesignerDto.BecomeDesignerDtoPOJOBuilder.class)
public record BecomeDesignerDto(@DatetimeValid String experiencedSince,
                                @NotNull(message = "Description is null.") @NotBlank(message = "Description is blank.") String description) {
    @JsonPOJOBuilder(withPrefix = "")
    public static class BecomeDesignerDtoPOJOBuilder {

    }

}
