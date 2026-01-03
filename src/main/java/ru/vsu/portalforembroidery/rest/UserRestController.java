package ru.vsu.portalforembroidery.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.vsu.portalforembroidery.model.CustomPrincipal;
import ru.vsu.portalforembroidery.model.dto.BecomeDesignerDto;
import ru.vsu.portalforembroidery.model.dto.UserDto;
import ru.vsu.portalforembroidery.model.dto.view.*;
import ru.vsu.portalforembroidery.service.UserService;

import jakarta.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/become-designer")
    public ResponseEntity<?> becomeDesigner(@Valid @RequestBody BecomeDesignerDto becomeDesignerDto, @AuthenticationPrincipal CustomPrincipal customPrincipal) {
        userService.becomeDesigner(customPrincipal.id(), becomeDesignerDto);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public UserViewDto getUser(@PathVariable final int id) {
        return userService.getUserViewById(id);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserViewDto> getUserProfile(@AuthenticationPrincipal final CustomPrincipal principal) {
        return ResponseEntity.ok(userService.getUserViewById(principal.id()));
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateUser(@RequestBody @Valid final UserDto userDto, @AuthenticationPrincipal final CustomPrincipal principal) {
        userService.updateUserById(principal.id(), userDto);
        return new ResponseEntity<>("User was updated!", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final int id) {
        userService.deleteUserById(id);
    }

    @GetMapping
    public ViewListPage<UserForListDto> getUsers(@RequestParam(required = false) final Map<String, String> allParams) {
        return userService.getViewListPage(allParams.get("page"), allParams.get("size"));
    }

    @GetMapping("/designers")
    public ViewListPage<UserForListDto> getDesigners() {
        return userService.getDesignerViewListPage();
    }

    @GetMapping("/{id}/posts")
    public FilteredViewListPage<PostForListDto> getUserPosts(@PathVariable final int id, @RequestParam(required = false) final Map<String, String> allParams) {
        return userService.getFilteredPostViewListPage(id, allParams.get("page"), allParams.get("size"), allParams.get("tagName"));
    }

    @GetMapping("/{id}/folders")
    public ViewListPage<FolderViewDto> getUserFolders(@PathVariable final int id, @RequestParam(required = false) final Map<String, String> allParams) {
        return userService.getFolderViewListPage(id, allParams.get("page"), allParams.get("size"));
    }

}
