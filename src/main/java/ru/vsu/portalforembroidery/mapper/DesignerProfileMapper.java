package ru.vsu.portalforembroidery.mapper;

import org.mapstruct.Mapper;
import ru.vsu.portalforembroidery.model.dto.BecomeDesignerDto;
import ru.vsu.portalforembroidery.model.entity.DesignerProfileEntity;

@Mapper(componentModel = "spring")
public interface DesignerProfileMapper {
    DesignerProfileEntity becomeDesignerDtoToDesignerProfileEntity(final BecomeDesignerDto becomeDesignerDto);
}
