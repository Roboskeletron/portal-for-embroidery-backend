#!/bin/bash

set -e

# Environment variables from docker-compose.yml 'postgres' service:
# PG_SUPER_USER will be the value of POSTGRES_USER (default: postgres)
PG_SUPER_USER="${POSTGRES_USER}"
# These are the custom variables you defined for Keycloak's DB:
KC_DB="${POSTGRES_KEYCLOAK_DB}"
KC_USER="${POSTGRES_KEYCLOAK_USER}"
KC_PASSWORD="${POSTGRES_KEYCLOAK_PASSWORD}"

echo "Creating Keycloak database: $KC_DB and user: $KC_USER"

# Connect as the superuser to the default 'postgres' database (which always exists)
# to create the new user and database.
psql -v ON_ERROR_STOP=1 --username "$PG_SUPER_USER" --dbname "postgres" <<-EOSQL
    -- Create the dedicated user for Keycloak
    CREATE USER $KC_USER WITH ENCRYPTED PASSWORD '$KC_PASSWORD';

    -- Create the database owned by the new user
    CREATE DATABASE $KC_DB WITH OWNER $KC_USER;

    -- Grant all privileges on the database to the new user
    GRANT ALL PRIVILEGES ON DATABASE $KC_DB TO $KC_USER;
EOSQL
