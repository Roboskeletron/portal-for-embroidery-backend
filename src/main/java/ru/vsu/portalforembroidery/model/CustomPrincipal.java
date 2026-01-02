package ru.vsu.portalforembroidery.model;

import java.util.UUID;

public record CustomPrincipal(
        Integer id,
        UUID externalId,
        Role role
) {
}
