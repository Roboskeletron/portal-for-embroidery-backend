package ru.vsu.portalforembroidery.service;

import ru.vsu.portalforembroidery.model.dto.UserDetailsDto;
import ru.vsu.portalforembroidery.model.response.JwtResponse;
import ru.vsu.portalforembroidery.model.response.LoginResponse;

import jakarta.security.auth.message.AuthException;

public interface AuthService {

    LoginResponse login(UserDetailsDto userDetailsDto);

    JwtResponse getNewAccessToken(String refreshToken) throws AuthException;

    void logout(String email);

}
