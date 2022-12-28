CREATE TABLE roles_privileges (
  role_id BIGINT NOT NULL,
   privileges_id BIGINT NOT NULL
);

ALTER TABLE roles_privileges ADD CONSTRAINT fk_rolpri_on_privilege FOREIGN KEY (privileges_id) REFERENCES privileges_tbl (id);

ALTER TABLE roles_privileges ADD CONSTRAINT fk_rolpri_on_role FOREIGN KEY (role_id) REFERENCES roles_tbl (id);