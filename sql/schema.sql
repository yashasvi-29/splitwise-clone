-- Basic schema for Splitwise clone
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  email VARCHAR(200) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL
);

CREATE TABLE expense_groups (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255),
  owner_id BIGINT,
  FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE group_members (
  group_id BIGINT,
  user_id BIGINT,
  PRIMARY KEY (group_id, user_id),
  FOREIGN KEY (group_id) REFERENCES expense_groups(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE expenses (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  description VARCHAR(500),
  amount DOUBLE,
  payer_id BIGINT,
  group_id BIGINT,
  FOREIGN KEY (payer_id) REFERENCES users(id),
  FOREIGN KEY (group_id) REFERENCES expense_groups(id)
);

CREATE TABLE expense_shares (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  expense_id BIGINT,
  user_id BIGINT,
  amount DOUBLE,
  settled BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (expense_id) REFERENCES expenses(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
