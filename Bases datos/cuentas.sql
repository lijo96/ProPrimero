-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 16-09-2013 a las 19:06:31
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `cuentas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE IF NOT EXISTS `cuentas` (
  `codigo` varchar(50) NOT NULL DEFAULT '',
  `cliente` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `saldo` double DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`codigo`, `cliente`, `email`, `saldo`) VALUES
('123-12-a', 'Pepe', 'pepe@pe.pe', 2045),
('123-45-a', 'Luis', 'luis@as.as', 12485),
('123456789', 'Ana', 'ana@as.as', 6030.34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimientos`
--

CREATE TABLE IF NOT EXISTS `movimientos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  `cantidad` double DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `codigo` (`codigo`),
  KEY `tipo` (`tipo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Volcado de datos para la tabla `movimientos`
--

INSERT INTO `movimientos` (`id`, `codigo`, `tipo`, `cantidad`, `fecha`) VALUES
(15, '123456789', 1, -1500, '2013-09-15'),
(16, '123-45-a', 1, 1500, '2013-09-15'),
(17, '123-12-a', 2, 23, '2013-09-03'),
(18, '123-12-a', 2, 450, '2013-09-05'),
(19, '123-45-a', 5, 12, '2013-08-14'),
(20, '123-45-a', 5, 995.95, '2006-02-16'),
(21, '123-45-a', 1, -45, '2013-09-16'),
(22, '123-12-a', 1, 45, '2013-09-16'),
(28, '123-45-a', 1, 20000, '2013-09-16'),
(29, '123-12-a', 1, -20000, '2013-09-16'),
(30, '123-12-a', 1, 10000, '2013-09-16'),
(31, '123-12-a', 1, -10000, '2013-09-16'),
(32, '123-12-a', 1, 10000, '2013-09-16'),
(33, '123-45-a', 1, -10000, '2013-09-16'),
(34, '123-12-a', 1, -500, '2013-09-16'),
(35, '123-45-a', 1, 500, '2013-09-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_movimiento`
--

CREATE TABLE IF NOT EXISTS `tipos_movimiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `tipos_movimiento`
--

INSERT INTO `tipos_movimiento` (`id`, `tipo`) VALUES
(1, 'Transferencia'),
(2, 'Venta'),
(5, 'Compra');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movimientos`
--
ALTER TABLE `movimientos`
  ADD CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`codigo`) REFERENCES `cuentas` (`codigo`),
  ADD CONSTRAINT `movimientos_ibfk_2` FOREIGN KEY (`tipo`) REFERENCES `tipos_movimiento` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
