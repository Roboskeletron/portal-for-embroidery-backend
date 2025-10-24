package ru.vsu.portalforembroidery.service;

import org.springframework.data.domain.Pageable;
import ru.vsu.portalforembroidery.exception.EntityAlreadyExistsException;
import ru.vsu.portalforembroidery.model.Provider;
import ru.vsu.portalforembroidery.model.dto.UserDetailsDto;
import ru.vsu.portalforembroidery.model.dto.UserDto;
import ru.vsu.portalforembroidery.model.dto.UserRegistrationDto;
import ru.vsu.portalforembroidery.model.dto.view.*;
import ru.vsu.portalforembroidery.model.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

//    int createUser(UserRegistrationDto userRegistrationDto, Provider provider) throws EntityAlreadyExistsException;

    UserViewDto getUserViewById(int id);

    void updateUserById(int id, UserDto userDto);

    void deleteUserById(int id);

    ViewListPage<UserForListDto> getViewListPage(String page, String size);

    ViewListPage<UserForListDto> getDesignerViewListPage();

    ViewListPage<FolderViewDto> getFolderViewListPage(int id, String page, String size);

    FilteredViewListPage<PostForListDto> getFilteredPostViewListPage(int userId, String page, String size, String tagName);

    List<UserForListDto> listUsers(Pageable pageable);

    int numberOfUsers();

    List<UserForListDto> listDesigners();

    List<PostForListDto> listPosts(int userId, Pageable pageable, String tagName);
}
