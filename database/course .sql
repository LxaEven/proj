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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `subject_ID` varchar(20) NOT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `hour_per_week` int NOT NULL,
  `hour_per_semester` int NOT NULL,
  PRIMARY KEY (`subject_ID`)
);

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`subject_ID`, `subject`, `hour_per_week`, `hour_per_semester`) VALUES
('S001', 'Network', 16, 32),
('S002', 'Statistic', 16, 32),
('S003', 'Analyse and Design', 16, 32),
('S004', 'OOP', 16, 32),
('S005', 'Electronic', 4, 16);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
