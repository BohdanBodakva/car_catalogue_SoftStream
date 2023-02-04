CREATE DATABASE IF NOT EXISTS `car_catalogue_softstream`;
USE `car_catalogue_softstream` ;


DROP TABLE IF EXISTS `car_catalogue_softstream`.`car_price`;
DROP TABLE IF EXISTS `car_catalogue_softstream`.`car`;
DROP TABLE IF EXISTS `car_catalogue_softstream`.`brand`;


-- -----------------------------------------------------
-- Table `car_catalogue_softstream`.`brand`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_catalogue_softstream`.`brand` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL UNIQUE);


-- -----------------------------------------------------
-- Table `car_catalogue_softstream`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_catalogue_softstream`.`car` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `model` VARCHAR(80) NOT NULL,
  `color` VARCHAR(80) NOT NULL,
  `engine_capacity_in_liters` INT NOT NULL,
  `description` TEXT,
  `brand_id` INT NOT NULL,
  `current_price` INT NOT NULL,
  INDEX `fk_car_brand1_idx` (`brand_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_brand1`
    FOREIGN KEY (`brand_id`)
    REFERENCES `car_catalogue_softstream`.`brand` (`id`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `car_catalogue_softstream`.`car_price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `car_catalogue_softstream`.`car_price` (
  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `car_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `price_in_dollars` INT NOT NULL,
  INDEX `fk_car_price_car1_idx` (`car_id` ASC) VISIBLE,
  CONSTRAINT `fk_car_price_car1`
    FOREIGN KEY (`car_id`)
    REFERENCES `car_catalogue_softstream`.`car` (`id`)
    ON DELETE CASCADE 
    ON UPDATE CASCADE);
    
    
    
    
    