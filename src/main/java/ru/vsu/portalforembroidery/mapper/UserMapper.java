package ru.vsu.portalforembroidery.mapper;

import org.mapstruct.*;
import ru.vsu.portalforembroidery.model.Role;
import ru.vsu.portalforembroidery.model.dto.UserDto;
import ru.vsu.portalforembroidery.model.dto.view.UserForListDto;
import ru.vsu.portalforembroidery.model.dto.view.UserViewDto;
import ru.vsu.portalforembroidery.model.entity.UserEntity;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "base64StringImage", source = "image", qualifiedByName = "bytesArrayImage")
    @Mapping(target = "experiencedSince", source = "designerProfile.experiencedSince")
    @Mapping(target = "description", source = "designerProfile.description")
    UserViewDto userEntityToUserViewDto(UserEntity entity);

    @Named(value = "bytesArrayImage")
    default String mapImage(byte[] image) {
        return Base64.getEncoder().encodeToString(image);
    }

    @Mapping(target = "image", source = "base64StringImage", qualifiedByName = "base64StringImage")
    @Mapping(target = "designerProfile", ignore = true)
    void mergeUserEntityAndUserDto(@MappingTarget UserEntity entity, UserDto dto);

    @Named(value = "base64StringImage")
    default byte[] mapBase64StringImage(String base64StringImage) {
        if (base64StringImage.isEmpty()) {
            return new byte[0];
        }

        return Base64.getDecoder().decode(base64StringImage);
    }

    List<UserForListDto> userEntitiesToUserForListDtoList(Iterable<UserEntity> entities);

    @AfterMapping
    default void handleDesignerProfile(@MappingTarget UserEntity userEntity, UserDto userDto) {
        if (userEntity.getRole() != Role.DESIGNER) {
            return;
        }

        userEntity.getDesignerProfile().setDescription(userDto.getDescription());
        userEntity.getDesignerProfile().setExperiencedSince(userDto.getExperiencedSince());
    }
}
