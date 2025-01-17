-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 14, 2025 at 07:53 AM
-- Server version: 11.3.2-MariaDB
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `all_sts`
--

DROP TABLE IF EXISTS `all_sts`;
CREATE TABLE IF NOT EXISTS `all_sts` (
  `ID` varchar(200) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `dob` varchar(20) NOT NULL,
  `email` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone_number` int(200) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `all_sts`
--

INSERT INTO `all_sts` (`ID`, `first_name`, `last_name`, `gender`, `dob`, `email`, `address`, `phone_number`) VALUES
('1', 'Sarith', 'Seyla', 'M', '2004-02-10', 'sarithseyla9999@gmail.com', 'Phnom Penh', 962022272),
('2', 'Vin', 'Channy', 'F', '2003-02-10', 'channy@gmail.com', 'Phnom Penh', 976797328);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
