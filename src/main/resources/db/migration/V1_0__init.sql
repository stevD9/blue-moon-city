create table if not exists users (
  id integer primary key AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  state JSON NOT NULL,

  unique key users_email_idx(email);
);