-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema crud_java
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema crud_java
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `crud_java` DEFAULT CHARACTER SET utf8 ;
USE `crud_java` ;

-- -----------------------------------------------------
-- Table `crud_java`.`tb_uf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crud_java`.`tb_uf` (
  `id_uf` INT(11) NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(2) NOT NULL,
  `descricao` VARCHAR(30) NOT NULL,
  `tb_ufcol` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL,
  PRIMARY KEY (`id_uf`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `crud_java`.`tb_cidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crud_java`.`tb_cidade` (
  `ID_CIDADE` INT(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(45) NOT NULL,
  `COD_ESTADO` INT(11) NOT NULL,
  PRIMARY KEY (`ID_CIDADE`),
  INDEX `FK_UF_CIDADE_idx` (`COD_ESTADO` ASC),
  CONSTRAINT `FK_UF_CIDADE`
    FOREIGN KEY (`COD_ESTADO`)
    REFERENCES `crud_java`.`tb_uf` (`id_uf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `crud_java`.`tb_endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crud_java`.`tb_endereco` (
  `id_endereco` INT(11) NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(45) NOT NULL,
  `codigo_cidade` INT(11) NOT NULL,
  PRIMARY KEY (`id_endereco`),
  INDEX `fk_tb_endereco_tb_uf1_idx` (`codigo_cidade` ASC),
  CONSTRAINT `fk_tb_endereco_tb_uf1`
    FOREIGN KEY (`codigo_cidade`)
    REFERENCES `crud_java`.`tb_uf` (`id_uf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `crud_java`.`tb_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crud_java`.`tb_pessoa` (
  `id_pessoa` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` FLOAT NOT NULL,
  `dt_nasc` DATE NULL DEFAULT NULL,
  `sexo` CHAR(1) NOT NULL,
  `mini_bio` VARCHAR(100) NULL DEFAULT NULL,
  `cod_endereco` INT(11) NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  INDEX `fk_tb_pessoa_tb_endereco1_idx` (`cod_endereco` ASC),
  CONSTRAINT `fk_tb_pessoa_tb_endereco1`
    FOREIGN KEY (`cod_endereco`)
    REFERENCES `crud_java`.`tb_endereco` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `crud_java`.`tb_preferencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crud_java`.`tb_preferencia` (
  `id_preferencia` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_preferencia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `crud_java`.`tb_preferencia_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crud_java`.`tb_preferencia_pessoa` (
  `cod_preferencia` INT(11) NOT NULL,
  `cod_pessoa` INT(11) NOT NULL,
  PRIMARY KEY (`cod_preferencia`, `cod_pessoa`),
  INDEX `fk_tb_preferencia_has_tb_pessoa_tb_pessoa1_idx` (`cod_pessoa` ASC),
  INDEX `fk_tb_preferencia_has_tb_pessoa_tb_preferencia1_idx` (`cod_preferencia` ASC),
  CONSTRAINT `fk_tb_preferencia_has_tb_pessoa_tb_preferencia1`
    FOREIGN KEY (`cod_preferencia`)
    REFERENCES `crud_java`.`tb_preferencia` (`id_preferencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_preferencia_has_tb_pessoa_tb_pessoa1`
    FOREIGN KEY (`cod_pessoa`)
    REFERENCES `crud_java`.`tb_pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;


-- -----------------------------------------------------
-- Table `crud_java`.`tb_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `crud_java`.`tb_usuario` (
  `ID_USUARIO` INT(11) NOT NULL AUTO_INCREMENT,
  `USUARIO` VARCHAR(45) NOT NULL,
  `SENHA` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_USUARIO`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO TB_USUARIO(USUARIO, SENHA)
	VALUES('paulo', '123'),
    ('admin','admin');
    
    
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AC', 'Acre');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AL', 'Alagoas');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AP', 'Amapá');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AM', 'Amazonas');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('BA', 'Bahia');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('CE', 'Ceará');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('DF', 'Distrito Federal');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('ES', 'Espírito Santo');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('GO', 'Goiás');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MA', 'Maranhão');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MT', 'Mato Grosso');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MS', 'Mato Grosso do Sul');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MG', 'Minas Gerais');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PA', 'Pará');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PB', 'Paraíba');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PR', 'Paraná');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PE', 'Pernambuco');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PI', 'Piauí');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RJ', 'Rio de Janeiro');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RN', 'Rio Grande do Norte');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RS', 'Rio Grande do Sul');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RO', 'Rondônia');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RR', 'Roraima');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SC', 'Santa Catarina');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SP', 'São Paulo');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SE', 'Sergipe');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('TO', 'Tocantins');

INSERT INTO TB_CIDADE (`descricao`, `cod_estado`) 
	VALUES ('Rio Branco', 1),
		   ('Cruzeiro do Sul', 1),
		   ('Maceió', 2),
		   ('Macapá', 3),
		   ('Manaus', 4),
		   ('Salvador', 5);
           
INSERT INTO TB_PREFERENCIA (`descricao`) 
	VALUES ('Jazz'), ('Blues'), ('MPB'), ('Pop'), ('Rock');
