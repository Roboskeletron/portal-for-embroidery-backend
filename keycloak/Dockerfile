FROM quay.io/keycloak/keycloak:26.0.0 AS builder

ENV KC_DB=postgres

COPY --chown=keycloak:keycloak providers/ /opt/keycloak/providers/

COPY --chown=keycloak:keycloak themes/ /opt/keycloak/themes/

RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:26.0.0

COPY --from=builder /opt/keycloak/ /opt/keycloak/

EXPOSE 8080
EXPOSE 8443

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]

CMD ["start-dev"]