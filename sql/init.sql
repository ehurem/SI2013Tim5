SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hajskontacemo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hajskontacemo` DEFAULT CHARACTER SET utf8 ;
USE `hajskontacemo` ;

-- -----------------------------------------------------
-- Table `hajskontacemo`.`tblklijenti`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hajskontacemo`.`tblklijenti` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `imeIPrezime` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(45) NOT NULL,
  `telefon` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hajskontacemo`.`tblzaposlenici`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hajskontacemo`.`tblzaposlenici` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `imeIPrezime` VARCHAR(45) NOT NULL,
  `telefon` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `adresa` VARCHAR(45) NOT NULL,
  `korisnickoIme` VARCHAR(45) NOT NULL,
  `sifra` VARCHAR(80) NOT NULL,
  `privilegija` VARCHAR(45) NOT NULL,
  `datumRodjenja` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `korisnickoIme_UNIQUE` (`korisnickoIme` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hajskontacemo`.`tblzahtjevi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hajskontacemo`.`tblzahtjevi` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipUredjaja` VARCHAR(45) NOT NULL,
  `garancija` TINYINT(4) NOT NULL,
  `komentar` TEXT NOT NULL,
  `datumZatvaranja` DATE NULL DEFAULT NULL,
  `datumOtvaranja` DATE NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `prioritet` INT(11) NOT NULL,
  `cijena` DOUBLE NULL DEFAULT NULL,
  `klijent_id` INT(10) UNSIGNED NOT NULL,
  `zaposlenik_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tblZahtjevi_tblKlijenti_idx` (`klijent_id` ASC),
  INDEX `fk_tblZahtjevi_tblZaposlenici1_idx` (`zaposlenik_id` ASC),
  CONSTRAINT `fk_tblZahtjevi_tblKlijenti`
    FOREIGN KEY (`klijent_id`)
    REFERENCES `hajskontacemo`.`tblklijenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblZahtjevi_tblZaposlenici1`
    FOREIGN KEY (`zaposlenik_id`)
    REFERENCES `hajskontacemo`.`tblzaposlenici` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `hajskontacemo`.`tblzalbe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hajskontacemo`.`tblzalbe` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `komentar` TEXT NOT NULL,
  `datumPodnosenja` DATE NOT NULL,
  `klijent_id` INT(10) UNSIGNED NOT NULL,
  `zaposlenik_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_tblZalbe_tblKlijenti1_idx` (`klijent_id` ASC),
  INDEX `fk_tblZalbe_tblZaposlenici1_idx` (`zaposlenik_id` ASC),
  CONSTRAINT `fk_tblZalbe_tblKlijenti1`
    FOREIGN KEY (`klijent_id`)
    REFERENCES `hajskontacemo`.`tblklijenti` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tblZalbe_tblZaposlenici1`
    FOREIGN KEY (`zaposlenik_id`)
    REFERENCES `hajskontacemo`.`tblzaposlenici` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- Data for table `HaJskontacemo`.`tblZaposlenici`
-- -----------------------------------------------------
START TRANSACTION;
USE `HaJskontacemo`;
INSERT INTO `HaJskontacemo`.`tblZaposlenici` (`id`, `imeIPrezime`, `telefon`, `email`, `adresa`, `korisnickoIme`, `sifra`, `privilegija`) VALUES (1, 'Administrator', '/', 'admin@HaJskontacemo.com', 'HaJskontacemo', 'admin', '4259031dc85f451a2b7731e8f5ea93193dad63ad', 'Administrator');
INSERT INTO `HaJskontacemo`.`tblZaposlenici` (`id`, `imeIPrezime`, `telefon`, `email`, `adresa`, `korisnickoIme`, `sifra`, `privilegija`) VALUES (2, 'Operater', '/', 'operater@HaJskontacemo.com', 'HaJskontacemo', 'operater', '4259031dc85f451a2b7731e8f5ea93193dad63ad', 'Operater');
INSERT INTO `HaJskontacemo`.`tblZaposlenici` (`id`, `imeIPrezime`, `telefon`, `email`, `adresa`, `korisnickoIme`, `sifra`, `privilegija`) VALUES (3, 'Servviser', '/', 'serviser@HaJskontacemo.com', 'HaJskontacemo', 'serviser', '4259031dc85f451a2b7731e8f5ea93193dad63ad', 'Serviser');

COMMIT;

