-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema plpr
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema plpr
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `plpr` DEFAULT CHARACTER SET utf8 ;
USE `plpr` ;

-- -----------------------------------------------------
-- Table `plpr`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` VARCHAR(50) NULL,
  `password` VARCHAR(50) NULL,
  `email` VARCHAR(500) NULL,
  `rol` VARCHAR(45) NULL DEFAULT 'USER',
  `active` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`insumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`insumo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(200) NULL,
  `relacion` INT NULL,
  `costo` DECIMAL(20,4) NULL,
  `fiscalizado` BINARY NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`formula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`formula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(50) NULL,
  `nombre` VARCHAR(200) NULL,
  `fecha` TIMESTAMP NULL,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `idFormula` INT NULL,
  `fiscalizado` BINARY NULL,
  PRIMARY KEY (`id`),
  INDEX `productoIdFormula_idx` (`idFormula` ASC),
  CONSTRAINT `productoIdFormula`
    FOREIGN KEY (`idFormula`)
    REFERENCES `plpr`.`formula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `razonsocial` VARCHAR(500) NULL,
  `ruc` VARCHAR(45) NULL,
  `direccion` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`periodo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`periodo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ano` INT NULL,
  `mes` INT NULL,
  `estado` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `fecha` TIMESTAMP NULL,
  `idProducto` INT NULL,
  `idCliente` INT NULL,
  `cantidad` DECIMAL(20,4) NULL,
  `idPeriodo` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pedidoCodigoProducto_idx` (`idProducto` ASC),
  INDEX `pedidoIdCliente_idx` (`idCliente` ASC),
  INDEX `pedidoIdPeriodo_idx` (`idPeriodo` ASC),
  CONSTRAINT `pedidoIdProducto`
    FOREIGN KEY (`idProducto`)
    REFERENCES `plpr`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pedidoIdCliente`
    FOREIGN KEY (`idCliente`)
    REFERENCES `plpr`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pedidoIdPeriodo`
    FOREIGN KEY (`idPeriodo`)
    REFERENCES `plpr`.`periodo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`plan` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'TABLA AHORA ES PARTE DE PRODUCCION',
  `codigo` VARCHAR(50) NULL,
  `idPedido` INT NULL,
  `fecha` TIMESTAMP NULL,
  `estado` VARCHAR(100) NULL,
  `solicitud` INT NULL,
  `lote` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `planCodigoPedido_idx` (`idPedido` ASC),
  INDEX `planLote` (`lote` ASC),
  CONSTRAINT `planIdPedido`
    FOREIGN KEY (`idPedido`)
    REFERENCES `plpr`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`maquina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`maquina` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `costo` DECIMAL(20,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`actividad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`actividad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(500) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`parteproduccion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`parteproduccion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `fecha` TIMESTAMP NULL,
  `inicio` TIMESTAMP NULL,
  `fin` TIMESTAMP NULL,
  `idMaquina` INT NULL,
  `idActividad` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `parteproduccionIdActividad_idx` (`idActividad` ASC),
  INDEX `parteproduccionIdMaquina_idx` (`idMaquina` ASC),
  CONSTRAINT `parteproduccionIdMaquina`
    FOREIGN KEY (`idMaquina`)
    REFERENCES `plpr`.`maquina` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `parteproduccionIdActividad`
    FOREIGN KEY (`idActividad`)
    REFERENCES `plpr`.`actividad` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`merma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`merma` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cantidad` DECIMAL(20,4) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`pruebaorden`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`pruebaorden` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(100) NULL,
  `observaciones` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`ordentrabajo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`ordentrabajo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idParteproduccion` INT NULL,
  `idPlan` INT NULL,
  `codigo` VARCHAR(50) NULL,
  `horas` INT NULL DEFAULT 0,
  `cantidad` DECIMAL(20,4) NULL DEFAULT 0,
  `comentarios` VARCHAR(500) NULL,
  `incidencias` VARCHAR(200) NULL,
  `merma` DECIMAL(20,4) NULL DEFAULT 0,
  `idMerma` INT NULL,
  `idPruebaorden` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `trabajoIdTarjeta_idx` (`idParteproduccion` ASC),
  INDEX `trabajoIdPlan_idx` (`idPlan` ASC),
  INDEX `ordentrabajoIdMerma_idx` (`idMerma` ASC),
  INDEX `ordentrabajoIdPruebaorden_idx` (`idPruebaorden` ASC),
  CONSTRAINT `ordentrabajoIdParteproduccion`
    FOREIGN KEY (`idParteproduccion`)
    REFERENCES `plpr`.`parteproduccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ordentrabajoIdPlan`
    FOREIGN KEY (`idPlan`)
    REFERENCES `plpr`.`plan` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ordentrabajoIdMerma`
    FOREIGN KEY (`idMerma`)
    REFERENCES `plpr`.`merma` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ordentrabajoIdPruebaorden`
    FOREIGN KEY (`idPruebaorden`)
    REFERENCES `plpr`.`pruebaorden` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`solicitudinsumo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`solicitudinsumo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idInsumo` INT NULL,
  `idPlan` INT NULL,
  `cantidad` DECIMAL(20,4) NULL,
  `codigo` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL DEFAULT 'generado',
  `fecha` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `solicitudIdInsumo_idx` (`idInsumo` ASC),
  CONSTRAINT `solicitudinsumoIdInsumo`
    FOREIGN KEY (`idInsumo`)
    REFERENCES `plpr`.`insumo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`preparado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`preparado` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPedido` INT NULL,
  `idInsumo` INT NULL,
  `descripcion` VARCHAR(200) NULL,
  `cantidad` DECIMAL(20,4) NULL DEFAULT 0,
  `costo` DECIMAL(20,4) NULL DEFAULT 0,
  `costoTotal` DECIMAL(20,4) NULL DEFAULT 0,
  `relacion` INT NULL,
  `item` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `preparadoIdPedido_idx` (`idPedido` ASC),
  CONSTRAINT `preparadoIdPedido`
    FOREIGN KEY (`idPedido`)
    REFERENCES `plpr`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`ingrediente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`ingrediente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idFormula` INT NULL,
  `idInsumo` INT NULL,
  `cantidad` DECIMAL(20,4) NULL,
  `item` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `ingredienteIdFormula_idx` (`idFormula` ASC),
  INDEX `ingredienteIdInsumo_idx` (`idInsumo` ASC),
  CONSTRAINT `ingredienteIdFormula`
    FOREIGN KEY (`idFormula`)
    REFERENCES `plpr`.`formula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ingredienteIdInsumo`
    FOREIGN KEY (`idInsumo`)
    REFERENCES `plpr`.`insumo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`personal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`personal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `nombres` VARCHAR(45) NULL,
  `apellidos` VARCHAR(45) NULL,
  `dni` VARCHAR(45) NULL,
  `costo` DECIMAL(20,2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`tarjetahorario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`tarjetahorario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idParteproduccion` INT NULL,
  `idPersonal` INT NULL,
  `horas` DECIMAL(20,2) NULL,
  `total` DECIMAL(20,2) NULL,
  `item` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `horasIdPersonal_idx` (`idPersonal` ASC),
  INDEX `tarjetahorarioIdParteproduccion_idx` (`idParteproduccion` ASC),
  CONSTRAINT `producidoIdPersonal`
    FOREIGN KEY (`idPersonal`)
    REFERENCES `plpr`.`personal` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tarjetahorarioIdParteproduccion`
    FOREIGN KEY (`idParteproduccion`)
    REFERENCES `plpr`.`parteproduccion` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`kardex`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`kardex` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idInsumo` INT NULL,
  `stock` DECIMAL(20,4) NULL DEFAULT 0,
  `relacion` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `kardexIdInsumo_idx` (`idInsumo` ASC),
  CONSTRAINT `kardexIdInsumo`
    FOREIGN KEY (`idInsumo`)
    REFERENCES `plpr`.`insumo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`movimientoalmacen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`movimientoalmacen` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `fecha` DATETIME NULL,
  `idKardex` INT NULL,
  `tipo` TINYINT(1) NULL DEFAULT 1,
  `cantidad` DECIMAL(20,4) NULL,
  PRIMARY KEY (`id`),
  INDEX `movimientoIdKardex_idx` (`idKardex` ASC),
  CONSTRAINT `movimientoIdKardex`
    FOREIGN KEY (`idKardex`)
    REFERENCES `plpr`.`kardex` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`pruebaformula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`pruebaformula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigo` VARCHAR(45) NULL,
  `estado` VARCHAR(45) NULL,
  `observaciones` VARCHAR(45) NULL,
  `sugerencias` VARCHAR(45) NULL,
  `merma` DECIMAL(20,4) NULL,
  `idFormula` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pruebaIdFormula_idx` (`idFormula` ASC),
  CONSTRAINT `pruebaIdFormula`
    FOREIGN KEY (`idFormula`)
    REFERENCES `plpr`.`formula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`control`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`control` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idProducto` INT NULL,
  `estado` VARCHAR(45) NULL,
  `observaciones` VARCHAR(45) NULL,
  `sugerencias` VARCHAR(45) NULL,
  `viscosidad` INT NULL,
  `rendimiento` INT NULL,
  `color` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `controlIdProducto_idx` (`idProducto` ASC),
  CONSTRAINT `controlIdProducto`
    FOREIGN KEY (`idProducto`)
    REFERENCES `plpr`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`muestra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`muestra` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NULL,
  `resultado` VARCHAR(100) NULL,
  `viscocidad` VARCHAR(45) NULL,
  `rendimiento` VARCHAR(45) NULL,
  `recipiente` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`solicitudformula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`solicitudformula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NULL,
  `observacion` VARCHAR(45) NULL,
  `idMuestra` INT NULL,
  `idPrueba` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `solicitudformulaIdMuestra_idx` (`idMuestra` ASC),
  INDEX `solicitudformulaIdPrueba_idx` (`idPrueba` ASC),
  CONSTRAINT `solicitudformulaIdMuestra`
    FOREIGN KEY (`idMuestra`)
    REFERENCES `plpr`.`muestra` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `solicitudformulaIdPrueba`
    FOREIGN KEY (`idPrueba`)
    REFERENCES `plpr`.`pruebaformula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `plpr`.`conformidadrecepcion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `plpr`.`conformidadrecepcion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NULL,
  `idMovimientoAlmacen` INT NULL,
  `idSolicitudInsumo` INT NULL,
  `observaciones` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `conformidadrecepcionIdSolicitudInsumo_idx` (`idSolicitudInsumo` ASC),
  INDEX `conformidadrecepcionIdMovimientoAlmacen_idx` (`idMovimientoAlmacen` ASC),
  CONSTRAINT `conformidadrecepcionIdMovimientoAlmacen`
    FOREIGN KEY (`idMovimientoAlmacen`)
    REFERENCES `plpr`.`movimientoalmacen` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `conformidadrecepcionIdSolicitudInsumo`
    FOREIGN KEY (`idSolicitudInsumo`)
    REFERENCES `plpr`.`solicitudinsumo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `plpr`.`usuario`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`usuario` (`id`, `user`, `password`, `email`, `rol`, `active`) VALUES (1, 'admin', 'admin', 'admin@admin.com', 'ADMIN', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`insumo`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (1, 'Insumo A', 1, 2.5, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (2, 'Insumo B', 2, 3.2, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (3, 'Insumo C', 1, 2.5, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (4, 'Insumo D', 2, 4, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (5, 'Insumo E', 1, 2, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (6, 'Insumo F', 2, 3, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (7, 'Insumo G', 1, 4, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (8, 'Insumo H', 2, 2.2, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (9, 'Insumo C 1', 3, 7, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (10, 'Insumo C 2', 4, 6, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (11, 'Insumo C 3', 5, 4.5, NULL);
INSERT INTO `plpr`.`insumo` (`id`, `descripcion`, `relacion`, `costo`, `fiscalizado`) VALUES (12, 'Insumo C 4', 6, 5.9, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`formula`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`formula` (`id`, `codigo`, `nombre`, `fecha`, `estado`) VALUES (1, 'FOR001', 'Formula - Producto A', '2016-10-02 14:13:59', NULL);
INSERT INTO `plpr`.`formula` (`id`, `codigo`, `nombre`, `fecha`, `estado`) VALUES (2, 'FOR002', 'Formula - Producto B', '2016-10-02 14:13:59', NULL);
INSERT INTO `plpr`.`formula` (`id`, `codigo`, `nombre`, `fecha`, `estado`) VALUES (3, 'FOR003', 'Formula - Producto C', '2016-10-02 14:13:59', NULL);
INSERT INTO `plpr`.`formula` (`id`, `codigo`, `nombre`, `fecha`, `estado`) VALUES (4, 'FOR004', 'Formula - Producto D', '2016-10-02 14:13:59', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`producto`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`producto` (`id`, `descripcion`, `idFormula`, `fiscalizado`) VALUES (1, 'Producto A', 1, NULL);
INSERT INTO `plpr`.`producto` (`id`, `descripcion`, `idFormula`, `fiscalizado`) VALUES (2, 'Producto B', 2, NULL);
INSERT INTO `plpr`.`producto` (`id`, `descripcion`, `idFormula`, `fiscalizado`) VALUES (3, 'Producto C', 3, NULL);
INSERT INTO `plpr`.`producto` (`id`, `descripcion`, `idFormula`, `fiscalizado`) VALUES (4, 'Producto D', 4, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`cliente`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (1, 'Cliente 1', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (2, 'Cliente 2', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (3, 'Cliente 3', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (4, 'Cliente 4', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (5, 'Cliente 5', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (6, 'Cliente 6', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (7, 'Cliente 7', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (8, 'Cliente 8', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (9, 'Cliente 9', NULL, NULL);
INSERT INTO `plpr`.`cliente` (`id`, `razonsocial`, `ruc`, `direccion`) VALUES (10, 'Cliente 10', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`periodo`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (1, 2016, 1, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (2, 2016, 2, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (3, 2016, 3, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (4, 2016, 4, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (5, 2016, 5, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (6, 2016, 6, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (7, 2016, 7, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (8, 2016, 8, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (9, 2016, 9, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (10, 2016, 10, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (11, 2016, 11, 'pendiente');
INSERT INTO `plpr`.`periodo` (`id`, `ano`, `mes`, `estado`) VALUES (12, 2016, 12, 'pendiente');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`pedido`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (1, 'PED-2017090001', '2016-10-01 14:13:59', 1, 1, 1000, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (2, 'PED-2017090002', '2016-10-02 14:13:59', 1, 1, 2000, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (3, 'PED-2017090003', '2016-10-02 14:13:59', 1, 1, 100, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (4, 'PED-2017090004', '2016-10-02 14:13:59', 2, 1, 1000, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (5, 'PED-2017090005', '2016-10-02 14:13:59', 3, 1, 2000, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (6, 'PED-2017090006', '2016-10-03 14:13:59', 1, 1, 100, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (7, 'PED-2017090007', '2016-10-03 14:13:59', 2, 1, 1000, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (8, 'PED-2017090008', '2016-10-03 14:13:59', 1, 1, 200, NULL);
INSERT INTO `plpr`.`pedido` (`id`, `codigo`, `fecha`, `idProducto`, `idCliente`, `cantidad`, `idPeriodo`) VALUES (9, 'PED-2017090009', '2016-10-03 14:13:59', 2, 1, 200, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`plan`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`plan` (`id`, `codigo`, `idPedido`, `fecha`, `estado`, `solicitud`, `lote`) VALUES (1, 'PLP-2017090001', 1, '2016-10-02 14:13:59', 'No validado', NULL, NULL);
INSERT INTO `plpr`.`plan` (`id`, `codigo`, `idPedido`, `fecha`, `estado`, `solicitud`, `lote`) VALUES (2, 'PLP-2017090002', 2, '2016-10-02 14:13:59', 'No validado', NULL, NULL);
INSERT INTO `plpr`.`plan` (`id`, `codigo`, `idPedido`, `fecha`, `estado`, `solicitud`, `lote`) VALUES (3, 'PLP-2017090003', 3, '2016-10-03 14:13:59', 'No validado', NULL, NULL);
INSERT INTO `plpr`.`plan` (`id`, `codigo`, `idPedido`, `fecha`, `estado`, `solicitud`, `lote`) VALUES (4, 'PLP-2017090004', 4, '2016-11-03 14:13:59', 'Rechazado', NULL, NULL);
INSERT INTO `plpr`.`plan` (`id`, `codigo`, `idPedido`, `fecha`, `estado`, `solicitud`, `lote`) VALUES (5, 'PLP-2017090005', 5, '2016-11-03 14:13:59', 'No validado', NULL, NULL);
INSERT INTO `plpr`.`plan` (`id`, `codigo`, `idPedido`, `fecha`, `estado`, `solicitud`, `lote`) VALUES (6, 'PLP-2017090006', 6, '2016-11-04 14:13:59', 'No validado', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`maquina`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`maquina` (`id`, `descripcion`, `costo`) VALUES (1, 'Maquina 01', 2);
INSERT INTO `plpr`.`maquina` (`id`, `descripcion`, `costo`) VALUES (2, 'Maquina 02', 3);
INSERT INTO `plpr`.`maquina` (`id`, `descripcion`, `costo`) VALUES (3, 'Maquina 03', 2);
INSERT INTO `plpr`.`maquina` (`id`, `descripcion`, `costo`) VALUES (4, 'Maquina 04', 2);
INSERT INTO `plpr`.`maquina` (`id`, `descripcion`, `costo`) VALUES (5, 'Maquina 05', 3);
INSERT INTO `plpr`.`maquina` (`id`, `descripcion`, `costo`) VALUES (6, 'Maquina 06', 1);
INSERT INTO `plpr`.`maquina` (`id`, `descripcion`, `costo`) VALUES (7, 'Maquina 07', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`actividad`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`actividad` (`id`, `descripcion`) VALUES (1, 'Actividad 01');
INSERT INTO `plpr`.`actividad` (`id`, `descripcion`) VALUES (2, 'Actividad 02');
INSERT INTO `plpr`.`actividad` (`id`, `descripcion`) VALUES (3, 'Actividad 03');
INSERT INTO `plpr`.`actividad` (`id`, `descripcion`) VALUES (4, 'Actividad 04');
INSERT INTO `plpr`.`actividad` (`id`, `descripcion`) VALUES (5, 'Actividad 05');
INSERT INTO `plpr`.`actividad` (`id`, `descripcion`) VALUES (6, 'Actividad 06');

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`parteproduccion`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`parteproduccion` (`id`, `codigo`, `fecha`, `inicio`, `fin`, `idMaquina`, `idActividad`) VALUES (1, 'PED-2017090001', '2017-09-24 10:13:59', '2017-09-24 10:13:59', '2017-09-24 10:13:59', 1, 1);
INSERT INTO `plpr`.`parteproduccion` (`id`, `codigo`, `fecha`, `inicio`, `fin`, `idMaquina`, `idActividad`) VALUES (2, 'PED-2017090002', '2017-09-24 11:13:59', '2017-09-24 11:13:59', '2017-09-24 11:13:59', 1, 1);
INSERT INTO `plpr`.`parteproduccion` (`id`, `codigo`, `fecha`, `inicio`, `fin`, `idMaquina`, `idActividad`) VALUES (3, 'PED-2017090003', '2017-09-24 12:13:59', '2017-09-24 12:13:59', '2017-09-24 12:13:59', 1, 1);
INSERT INTO `plpr`.`parteproduccion` (`id`, `codigo`, `fecha`, `inicio`, `fin`, `idMaquina`, `idActividad`) VALUES (4, 'PED-2017090004', '2017-09-24 13:13:59', '2017-09-24 13:13:59', '2017-09-24 13:13:59', 1, 1);
INSERT INTO `plpr`.`parteproduccion` (`id`, `codigo`, `fecha`, `inicio`, `fin`, `idMaquina`, `idActividad`) VALUES (5, 'PED-2017090005', '2017-09-24 14:13:59', '2017-09-24 14:13:59', '2017-09-24 14:13:59', 1, 1);
INSERT INTO `plpr`.`parteproduccion` (`id`, `codigo`, `fecha`, `inicio`, `fin`, `idMaquina`, `idActividad`) VALUES (6, 'PED-2017090006', '2017-09-24 15:13:59', '2017-09-24 15:13:59', '2017-09-24 15:13:59', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`pruebaorden`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`pruebaorden` (`id`, `descripcion`, `observaciones`) VALUES (1, NULL, NULL);
INSERT INTO `plpr`.`pruebaorden` (`id`, `descripcion`, `observaciones`) VALUES (2, NULL, NULL);
INSERT INTO `plpr`.`pruebaorden` (`id`, `descripcion`, `observaciones`) VALUES (3, NULL, NULL);
INSERT INTO `plpr`.`pruebaorden` (`id`, `descripcion`, `observaciones`) VALUES (4, NULL, NULL);
INSERT INTO `plpr`.`pruebaorden` (`id`, `descripcion`, `observaciones`) VALUES (5, NULL, NULL);
INSERT INTO `plpr`.`pruebaorden` (`id`, `descripcion`, `observaciones`) VALUES (6, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`ordentrabajo`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`ordentrabajo` (`id`, `idParteproduccion`, `idPlan`, `codigo`, `horas`, `cantidad`, `comentarios`, `incidencias`, `merma`, `idMerma`, `idPruebaorden`) VALUES (1, 1, 1, 'OTR-2017090001', 10, 0, ' ', 'maquinas no disponibles', 0, NULL, 1);
INSERT INTO `plpr`.`ordentrabajo` (`id`, `idParteproduccion`, `idPlan`, `codigo`, `horas`, `cantidad`, `comentarios`, `incidencias`, `merma`, `idMerma`, `idPruebaorden`) VALUES (2, 1, 2, 'OTR-2017090004', 2, 0, ' ', NULL, 0, NULL, 2);
INSERT INTO `plpr`.`ordentrabajo` (`id`, `idParteproduccion`, `idPlan`, `codigo`, `horas`, `cantidad`, `comentarios`, `incidencias`, `merma`, `idMerma`, `idPruebaorden`) VALUES (3, 2, 3, 'OTR-2017090002', 7, 0, ' ', '', 2, NULL, 3);
INSERT INTO `plpr`.`ordentrabajo` (`id`, `idParteproduccion`, `idPlan`, `codigo`, `horas`, `cantidad`, `comentarios`, `incidencias`, `merma`, `idMerma`, `idPruebaorden`) VALUES (4, 2, 4, 'OTR-2017090005', 4, 0, ' ', '', 5, NULL, 4);
INSERT INTO `plpr`.`ordentrabajo` (`id`, `idParteproduccion`, `idPlan`, `codigo`, `horas`, `cantidad`, `comentarios`, `incidencias`, `merma`, `idMerma`, `idPruebaorden`) VALUES (5, 3, 5, 'OTR-2017090003', 12, 0, ' ', '', 0, NULL, 5);
INSERT INTO `plpr`.`ordentrabajo` (`id`, `idParteproduccion`, `idPlan`, `codigo`, `horas`, `cantidad`, `comentarios`, `incidencias`, `merma`, `idMerma`, `idPruebaorden`) VALUES (6, 3, 6, 'OTR-2017090006', 6, 0, ' ', '', 1.2, NULL, 6);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`ingrediente`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (1, 1, 1, 2, 1);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (2, 1, 2, 1, 2);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (3, 1, 9, 2, 3);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (4, 2, 3, 2, 1);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (5, 2, 4, 2, 2);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (6, 2, 10, 1, 3);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (7, 3, 5, 1, 1);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (8, 3, 6, 1, 2);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (9, 3, 11, 2, 3);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (10, 4, 7, 2, 1);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (11, 4, 8, 1, 2);
INSERT INTO `plpr`.`ingrediente` (`id`, `idFormula`, `idInsumo`, `cantidad`, `item`) VALUES (12, 4, 12, 2, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`personal`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (1, 'P001', 'Personal 1', 'Trabajador', NULL, 3);
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (2, 'P002', 'Personal 1', 'Trabajador', NULL, 3);
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (3, 'P003', 'Personal 1', 'Trabajador', NULL, 2.7);
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (4, 'P004', 'Personal 1', 'Trabajador', NULL, 3);
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (5, 'P005', 'Personal 1', 'Trabajador', NULL, 2.5);
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (6, 'P006', 'Personal 1', 'Trabajador', NULL, 2.2);
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (7, 'P007', 'Personal 1', 'Trabajador', NULL, 3);
INSERT INTO `plpr`.`personal` (`id`, `codigo`, `nombres`, `apellidos`, `dni`, `costo`) VALUES (8, 'P008', 'Personal 1', 'Trabajador', NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`tarjetahorario`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`tarjetahorario` (`id`, `idParteproduccion`, `idPersonal`, `horas`, `total`, `item`) VALUES (1, 1, 1, 10, 30, 1);
INSERT INTO `plpr`.`tarjetahorario` (`id`, `idParteproduccion`, `idPersonal`, `horas`, `total`, `item`) VALUES (2, 1, 2, 10, 30, 2);
INSERT INTO `plpr`.`tarjetahorario` (`id`, `idParteproduccion`, `idPersonal`, `horas`, `total`, `item`) VALUES (3, 2, 1, 10, 30, 1);
INSERT INTO `plpr`.`tarjetahorario` (`id`, `idParteproduccion`, `idPersonal`, `horas`, `total`, `item`) VALUES (4, 2, 2, 10, 30, 2);
INSERT INTO `plpr`.`tarjetahorario` (`id`, `idParteproduccion`, `idPersonal`, `horas`, `total`, `item`) VALUES (5, 3, 3, 10, 27, 1);
INSERT INTO `plpr`.`tarjetahorario` (`id`, `idParteproduccion`, `idPersonal`, `horas`, `total`, `item`) VALUES (6, 3, 4, 10, 30, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`kardex`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`kardex` (`id`, `idInsumo`, `stock`, `relacion`) VALUES (1, 1, 1000, 1);
INSERT INTO `plpr`.`kardex` (`id`, `idInsumo`, `stock`, `relacion`) VALUES (2, 2, 199, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`movimientoalmacen`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`movimientoalmacen` (`id`, `codigo`, `fecha`, `idKardex`, `tipo`, `cantidad`) VALUES (1, '1', '2017-10-13 14:13:59', 1, 1, 100);
INSERT INTO `plpr`.`movimientoalmacen` (`id`, `codigo`, `fecha`, `idKardex`, `tipo`, `cantidad`) VALUES (2, '2', '2017-10-13 14:13:59', 2, 0, 90);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`pruebaformula`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`pruebaformula` (`id`, `codigo`, `estado`, `observaciones`, `sugerencias`, `merma`, `idFormula`) VALUES (1, '1', 'registrado', 'test1', NULL, NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`muestra`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`muestra` (`id`, `descripcion`, `resultado`, `viscocidad`, `rendimiento`, `recipiente`) VALUES (1, 'test 1', NULL, NULL, NULL, NULL);
INSERT INTO `plpr`.`muestra` (`id`, `descripcion`, `resultado`, `viscocidad`, `rendimiento`, `recipiente`) VALUES (2, 'test 2', NULL, NULL, NULL, NULL);
INSERT INTO `plpr`.`muestra` (`id`, `descripcion`, `resultado`, `viscocidad`, `rendimiento`, `recipiente`) VALUES (3, 'test 3', NULL, NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `plpr`.`solicitudformula`
-- -----------------------------------------------------
START TRANSACTION;
USE `plpr`;
INSERT INTO `plpr`.`solicitudformula` (`id`, `fecha`, `observacion`, `idMuestra`, `idPrueba`) VALUES (1, '2016-10-02 14:13:59', 'test', 1, 1);

COMMIT;

