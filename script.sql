SET FOREIGN_KEY_CHECKS = 0;

CREATE SCHEMA IF NOT EXISTS `races` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `races` ;


CREATE TABLE IF NOT EXISTS `races`.`horse_racing` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `date` DATETIME NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`rate_line` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(45) NOT NULL COMMENT '',
  `description` TEXT(1000) NOT NULL COMMENT '',
  `positions` VARCHAR(20) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  UNIQUE INDEX `name_UNIQUE` (`title` ASC)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`horse` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`jockey` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `first_name` VARCHAR(45) NOT NULL COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`participant` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `horse_id` INT NOT NULL COMMENT '',
  `jockey_id` INT NOT NULL COMMENT '',
  INDEX `fk_hourses_has_jockeys_jockeys1_idx` (`jockey_id` ASC)  COMMENT '',
  INDEX `fk_hourses_has_jockeys_hourses1_idx` (`horse_id` ASC)  COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  CONSTRAINT `fk_hourses_has_jockeys_hourses1`
    FOREIGN KEY (`horse_id`)
    REFERENCES `races`.`horse` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_hourses_has_jockeys_jockeys1`
    FOREIGN KEY (`jockey_id`)
    REFERENCES `races`.`jockey` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`racing_line` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `horse_racing_id` INT NOT NULL COMMENT '',
  `participant_id` INT NOT NULL COMMENT '',
  `result` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  INDEX `fk_racing_line_horse_racing1_idx` (`horse_racing_id` ASC)  COMMENT '',
  INDEX `fk_racing_line_hourse_2_jockey1_idx` (`participant_id` ASC)  COMMENT '',
  CONSTRAINT `fk_racing_line_horse_racing1`
    FOREIGN KEY (`horse_racing_id`)
    REFERENCES `races`.`horse_racing` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_racing_line_hourse_2_jockey1`
    FOREIGN KEY (`participant_id`)
    REFERENCES `races`.`participant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`coefficient` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `value` DOUBLE NOT NULL COMMENT '',
  `rate_line_id` INT NOT NULL COMMENT '',
  `racing_line_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  INDEX `fk_first_variant_rate1_idx` (`rate_line_id` ASC)  COMMENT '',
  INDEX `fk_coefficients_racing_line1_idx` (`racing_line_id` ASC)  COMMENT '',
  CONSTRAINT `fk_first_variant_rate1`
    FOREIGN KEY (`rate_line_id`)
    REFERENCES `races`.`rate_line` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_coefficients_racing_line1`
    FOREIGN KEY (`racing_line_id`)
    REFERENCES `races`.`racing_line` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `login` VARCHAR(20) NOT NULL COMMENT '',
  `password` VARCHAR(40) NOT NULL COMMENT '',
  `first_name` VARCHAR(45) NOT NULL COMMENT '',
  `last_name` VARCHAR(45) NOT NULL COMMENT '',
  `email` VARCHAR(45) NOT NULL COMMENT '',
  `role` VARCHAR(20) NOT NULL COMMENT '',
  `balance` DOUBLE NOT NULL DEFAULT 0.0 COMMENT '',
  `create_date` DATETIME NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `login_UNIQUE` (`login` ASC)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '')
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `races`.`rate` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `value` DOUBLE NOT NULL COMMENT '',
  `coefficient_value` DOUBLE NOT NULL COMMENT '',
  `coefficient_id` INT NOT NULL COMMENT '',
  `user_id` INT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `id_UNIQUE` (`id` ASC)  COMMENT '',
  INDEX `fk_rate_coefficient1_idx` (`coefficient_id` ASC)  COMMENT '',
  INDEX `fk_rate_user1_idx` (`user_id` ASC)  COMMENT '',
  CONSTRAINT `fk_rate_coefficient1`
    FOREIGN KEY (`coefficient_id`)
    REFERENCES `races`.`coefficient` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rate_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `races`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET FOREIGN_KEY_CHECKS = 1;

insert into races.user(login,password,first_name,last_name,email,role,balance,create_date) values('admin','21232f297a57a5a743894a0e4a801fc3','admin','admin','admin@admin.com','admin',0,'2016.01.01');