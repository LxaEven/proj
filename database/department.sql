-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 30, 2025 at 04:19 AM
-- Server version: 8.0.40
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE IF NOT EXISTS `department` (
  `department_id` varchar(20) NOT NULL,
  `department_name` varchar(200) NOT NULL,
  `department_head` varchar(200) NOT NULL,
  `Dep_head_ph` varchar(20) NOT NULL,
  PRIMARY KEY (`department_id`)
);

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_name`, `department_head`, `Dep_head_ph`) VALUES
('D001', 'GIC', 'Sarith Seyla', '012269887'),
('D002', 'GTR', 'Rom Tola', '096202236'),
('D003', 'AMS', 'Peng Seyha', '097845623'),
('D004', 'GGG', 'Sar Vichada', '06886239'),
('D005', 'GEE', 'Tek ChanSetha', '078963302');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
