
-- -----------------------------------------------------
-- Schema social
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema social
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `social` DEFAULT CHARACTER SET utf8 ;
USE `social` ;

-- -----------------------------------------------------
-- Table `social`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`account` (
  `account_id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(50) NOT NULL,
  `password` CHAR(60) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `social`.`profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`profile` (
  `profile_id` INT(11) NOT NULL AUTO_INCREMENT,
  `account_id` INT(11) NOT NULL,
  `online_status` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`profile_id`, `account_id`),
  INDEX `fk_profile_account_idx` (`account_id` ASC),
  CONSTRAINT `fk_profile_account`
    FOREIGN KEY (`account_id`)
    REFERENCES `social`.`account` (`account_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `social`.`album`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`album` (
  `album_id` INT(11) NOT NULL AUTO_INCREMENT,
  `album_name` VARCHAR(45) NOT NULL,
  `profile_id` INT(11) NOT NULL,
  PRIMARY KEY (`album_id`, `profile_id`),
  INDEX `fk_album_profile1_idx` (`profile_id` ASC),
  CONSTRAINT `fk_album_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `social`.`profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `social`.`conversation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`conversation` (
  `conversation_id` INT(11) NOT NULL AUTO_INCREMENT,
  `companion_id` INT(11) NOT NULL,
  `profile_id` INT(11) NOT NULL,
  PRIMARY KEY (`conversation_id`, `profile_id`),
  INDEX `fk_conversations_profile1_idx` (`profile_id` ASC),
  CONSTRAINT `fk_conversations_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `social`.`profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `social`.`friends_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`friends_list` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `profile_id` INT(11) NOT NULL,
  `friend_profile_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `profile_id`, `friend_profile_id`),
  INDEX `fk_profile_has_profile_profile1_idx` (`profile_id` ASC),
  CONSTRAINT `fk_profile_has_profile_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `social`.`profile` (`profile_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `social`.`message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`message` (
  `message_id` INT(11) NOT NULL AUTO_INCREMENT,
  `message_content` TEXT NULL DEFAULT NULL,
  `message_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `conversation_id` INT(11) NOT NULL,
  PRIMARY KEY (`message_id`, `conversation_id`),
  INDEX `fk_messages_conversations1_idx` (`conversation_id` ASC),
  CONSTRAINT `fk_messages_conversations1`
    FOREIGN KEY (`conversation_id`)
    REFERENCES `social`.`conversation` (`conversation_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `social`.`photo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`photo` (
  `photo_id` INT(11) NOT NULL AUTO_INCREMENT,
  `photo_data` BLOB NULL DEFAULT NULL,
  `album_id` INT(11) NOT NULL,
  `photo_name` VARCHAR(45) NOT NULL,
  `description` TEXT(1000) NULL,
  `avatar` INT NULL,
  PRIMARY KEY (`photo_id`, `album_id`),
  INDEX `fk_photos_album1_idx` (`album_id` ASC),
  CONSTRAINT `fk_photos_album1`
    FOREIGN KEY (`album_id`)
    REFERENCES `social`.`album` (`album_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `social`.`profile_details`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `social`.`profile_details` (
  `profile_details_id` INT(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `sex` ENUM('male', 'female') NOT NULL,
  `age` INT(11) NOT NULL,
  `profile_id` INT(11) NOT NULL,
  PRIMARY KEY (`profile_details_id`, `profile_id`),
  INDEX `fk_profile_details_profile1_idx` (`profile_id` ASC),
  CONSTRAINT `fk_profile_details_profile1`
    FOREIGN KEY (`profile_id`)
    REFERENCES `social`.`profile` (`profile_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



