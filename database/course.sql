-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 20, 2025 at 07:57 AM
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
  `subject_No` int NOT NULL AUTO_INCREMENT,
  `subject` varchar(50) DEFAULT NULL,
  `per_week` varchar(50) DEFAULT NULL,
  `per_semester` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`subject_No`)
);

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`subject_No`, `subject`, `per_week`, `per_semester`) VALUES
(1, 'Math', '4Hrs', '36Hrs'),
(2, 'Physic', '3Hrs', '28Hrs'),
(3, 'Chemistry', '3Hrs', '28Hrs'),
(4, 'Khmer', '2Hrs', '20Hrs'),
(5, 'History', '1Hrs', '10Hrs');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
