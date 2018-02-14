CREATE SCHEMA IF NOT EXISTS social;
USE social ;

CREATE TABLE IF NOT EXISTS social.account (
  account_id INT(11) NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL,
  password CHAR(60) NOT NULL,
  PRIMARY KEY (account_id),
  UNIQUE INDEX email_UNIQUE (email ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS social.profile (
  profile_id INT(11) NOT NULL AUTO_INCREMENT,
  account_id INT(11) NOT NULL,
  online_status TINYINT(4) NULL DEFAULT 0,
  PRIMARY KEY (profile_id, account_id),
  INDEX fk_profile_account_idx (account_id ASC),
  CONSTRAINT fk_profile_account
    FOREIGN KEY (account_id)
    REFERENCES social.account (account_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS social.conversation (
  conversation_id INT(11) NOT NULL AUTO_INCREMENT,
  companion_id INT(11) NOT NULL,
  profile_id INT(11) NOT NULL,
  PRIMARY KEY (conversation_id, profile_id),
  INDEX fk_conversations_profile1_idx (profile_id ASC),
  CONSTRAINT fk_conversations_profile1
    FOREIGN KEY (profile_id)
    REFERENCES social.profile (profile_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS social.friends_list (
  id INT(11) NOT NULL AUTO_INCREMENT,
  profile_id INT(11) NOT NULL,
  friend_profile_id INT(11) NOT NULL,
  PRIMARY KEY (id, profile_id, friend_profile_id),
  INDEX fk_profile_has_profile_profile1_idx (profile_id ASC),
  CONSTRAINT fk_profile_has_profile_profile1
    FOREIGN KEY (profile_id)
    REFERENCES social.profile (profile_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;

CREATE TABLE IF NOT EXISTS social.message (
  message_id INT(11) NOT NULL AUTO_INCREMENT,
  message_content TEXT NULL DEFAULT NULL,
  message_date TIMESTAMP NOT NULL,
  conversation_id INT(11) NOT NULL,
  PRIMARY KEY (message_id, conversation_id),
  INDEX fk_messages_conversations1 (conversation_id ASC),
  CONSTRAINT fk_messages_conversations1
    FOREIGN KEY (conversation_id)
    REFERENCES social.conversation (conversation_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS social.post (
  post_id INT(11) NOT NULL AUTO_INCREMENT,
  post_date TIMESTAMP NOT NULL,
  wall_id INT(11) NOT NULL,
  post_content TEXT NULL DEFAULT NULL,
  audio VARCHAR(255) NULL DEFAULT NULL,
  video VARCHAR(255) NULL,
  photo VARCHAR(255) NULL,
  creater_id INT(11) NOT NULL,
  PRIMARY KEY (post_id, creater_id, wall_id),
  INDEX fk_post_profile1_idx (creater_id ASC),
  INDEX wall_id_idx (wall_id ASC),
  CONSTRAINT fk_post_profile1
    FOREIGN KEY (creater_id)
    REFERENCES social.profile (profile_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_wall_id
    FOREIGN KEY (wall_id)
    REFERENCES social.profile (profile_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS social.profile_details (
  profile_details_id INT(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  sex ENUM('male', 'female') NOT NULL,
  birthday DATE NOT NULL,
  country VARCHAR(45) NULL,
  profile_id INT(11) NOT NULL,
  PRIMARY KEY (profile_details_id, profile_id),
  INDEX fk_profile_details_profile1_idx (profile_id ASC),
  CONSTRAINT fk_profile_details_profile1
    FOREIGN KEY (profile_id)
    REFERENCES social.profile (profile_id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1;
