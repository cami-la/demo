CREATE TABLE users_tbl (
  id BIGINT AUTO_INCREMENT NOT NULL,
   first_name VARCHAR(255),
   last_name VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255),
   is_enable BOOLEAN NOT NULL,
   CONSTRAINT pk_users_tbl PRIMARY KEY (id)
);
