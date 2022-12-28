CREATE TABLE users_roles (
  user_id BIGINT NOT NULL,
   roles_id BIGINT NOT NULL
);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users_tbl (id);

ALTER TABLE users_roles ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES roles_tbl (id);
