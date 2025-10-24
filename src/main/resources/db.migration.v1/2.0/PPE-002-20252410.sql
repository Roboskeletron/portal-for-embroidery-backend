-- liquibase formatted sql

-- changeset ilya-t:1761335902265-56
ALTER TABLE embroidery.designers_designs DROP CONSTRAINT designers_designs_permission_id_fkey;

-- changeset ilya-t:1761335902265-59
ALTER TABLE embroidery.jwt_tokens DROP CONSTRAINT jwt_tokens_user_id_fkey;

-- changeset ilya-t:1761335902265-62
ALTER TABLE embroidery.users DROP CONSTRAINT users_role_id_fkey;

-- changeset ilya-t:1761335902265-51
ALTER TABLE embroidery.users
    ADD external_id UUID;

-- changeset ilya-t:1761335902265-65
DROP TABLE embroidery.jwt_tokens CASCADE;

-- changeset ilya-t:1761335902265-66
DROP TABLE embroidery.permissions CASCADE;

-- changeset ilya-t:1761335902265-67
DROP TABLE embroidery.roles CASCADE;

-- changeset ilya-t:1761335902265-73
ALTER TABLE embroidery.users DROP COLUMN password;
ALTER TABLE embroidery.users DROP COLUMN provider;

