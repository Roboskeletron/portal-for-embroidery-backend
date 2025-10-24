package ru.vsu.portalforembroidery.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import ru.vsu.portalforembroidery.model.Role;
import ru.vsu.portalforembroidery.model.entity.UserEntity;
import ru.vsu.portalforembroidery.repository.UserRepository;

import java.util.Collections;
import java.util.UUID;


@RequiredArgsConstructor
public class UserProvisioningJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static final String FIRST_NAME_CLAIM = "given_name";
    private static final String LAST_NAME_CLAIM = "family_name";
    private static final String EMAIL_NAME_CLAIM = "email";
    private static final String PHONE_NAME_CLAIM = "phone";
    private static final String USERNAME_NAME_CLAIM = "name";

    private final UserRepository userRepository;
    private final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        var principalId = UUID.fromString(jwt.getSubject());

        var c = userRepository.findByExternalId(principalId);
        var user = userRepository.findByExternalId(principalId)
                .orElseGet(() -> createUser(principalId, jwt));

        var authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

        return new JwtAuthenticationToken(jwt, authorities, principalId.toString());
    }

    private UserEntity createUser(UUID externalId, Jwt jwt) {
        var user = UserEntity.builder()
                .externalId(externalId)
                .firstName(jwt.getClaimAsString(FIRST_NAME_CLAIM))
                .lastName(jwt.getClaimAsString(LAST_NAME_CLAIM))
                .email(jwt.getClaimAsString(EMAIL_NAME_CLAIM))
                .phoneNumber(jwt.getClaimAsString(PHONE_NAME_CLAIM))
                .username(jwt.getClaimAsString(USERNAME_NAME_CLAIM))
                .role(Role.USER)
                .image(new byte[0])
                .build();

        return userRepository.save(user);
    }
}
