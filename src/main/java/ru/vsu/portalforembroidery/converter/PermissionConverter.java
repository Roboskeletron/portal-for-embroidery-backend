package ru.vsu.portalforembroidery.converter;

import ru.vsu.portalforembroidery.model.Permission;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PermissionConverter implements AttributeConverter<Permission, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Permission attribute) {
        return attribute == null ? null : attribute.getId();
    }

    @Override
    public Permission convertToEntityAttribute(Integer dbData) {
        return Permission.of(dbData).orElse(null);
    }

}
