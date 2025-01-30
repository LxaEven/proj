-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 30, 2025 at 04:16 AM
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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_firstname` varchar(50) DEFAULT NULL,
  `student_lastname` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `student_birth` date DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `student_address` varchar(200) NOT NULL,
  `department` varchar(50) NOT NULL,
  `student_password` varchar(255) DEFAULT NULL,
  `student_score` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
);

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `student_firstname`, `student_lastname`, `gender`, `student_birth`, `phone_number`, `student_email`, `student_address`, `department`, `student_password`, `student_score`) VALUES
(1, 'Tola', 'Rom', 'Male', '2006-10-16', '0123456789', 'tola.rom@gmail.com', 'Pailin', 'D001', '123', 85.50),
(2, 'Nita', 'Rom', 'Female', '2008-10-16', '0123456788', 'nita.rom@gmail.com', 'Koh Kong', 'D001', '12345', 90.25),
(3, 'Dara', 'Rom', 'Male', '2014-09-16', '0123456768', 'dara.rom@gmail.com', 'Battambang', 'D004', '12345', 78.75),
(4, 'Toek', 'Tak', 'Male', '2008-10-16', '0123212788', 'toek.tak@gmail.com', 'Phnom Penh', 'D001', '12345', 88.00),
(5, 'Nakry', 'Tang', 'Male', '2008-10-16', '0124546788', 'nakry.tang@gmail.com', 'Kampong Thom', 'D001', '12345', 92.50),
(6, 'Setha', 'Chan', 'Male', '2004-04-07', '0123456789', 'setha.chan@gmail.com', 'Phnom Penh', 'D003', '12345', 81.25),
(7, 'John', 'Doe', 'Male', '2004-05-08', '0987654322', 'john.doe@gmail.com', 'Battambang', 'D004', '12345', 76.40),
(8, 'Jane', 'Smith', 'Female', '2004-06-09', '0987654323', 'jane.smith@gmail.com', 'Phnom Penh', 'D001', '12345', 89.30),
(9, 'David', 'Brown', 'Male', '2004-07-10', '0987654324', 'david.brown@gmail.com', 'Koh Kong', 'D004', '12345', 95.75),
(10, 'Emily', 'Taylor', 'Female', '2004-08-11', '0987654325', 'emily.taylor@gmail.com', 'Siem Reap', 'D001', '12345', 82.20),
(11, 'Michael', 'Williams', 'Male', '2004-09-12', '0987654326', 'michael.williams@gmail.com', 'Siem Reap', 'D001', '12345', 77.50),
(12, 'Sarah', 'Jones', 'Female', '2004-10-13', '0987654327', 'sarah.jones@gmail.com', 'Battambang', 'D002', '12345', 84.90),
(13, 'Daniel', 'Davis', 'Male', '2004-11-14', '0987654328', 'daniel.davis@gmail.com', 'Phnom Penh', 'D003', '12345', 93.40),
(14, 'Sophie', 'Miller', 'Female', '2004-12-15', '0987654329', 'sophie.miller@gmail.com', 'Siem Reap', 'D005', '12345', 86.80),
(15, 'Lucas', 'Wilson', 'Male', '2005-01-16', '0987654330', 'lucas.wilson@gmail.com', 'Battambang', 'D001', '12345', 79.60),
(16, 'Olivia', 'Moore', 'Female', '2005-02-17', '0987654331', 'olivia.moore@gmail.com', 'Siem Reap', 'D005', '12345', 88.90),
(17, 'William', 'Taylor', 'Male', '2005-03-18', '0987654332', 'william.taylor@gmail.com', 'Phnom Penh', 'D002', '12345', 91.25),
(18, 'Ava', 'Anderson', 'Female', '2005-04-19', '0987654333', 'ava.anderson@gmail.com', 'Takeo', 'D004', '12345', 80.50),
(19, 'James', 'Thomas', 'Male', '2005-05-20', '0987654334', 'james.thomas@gmail.com', 'Koh Kong', 'D001', '12345', 94.20),
(20, 'Charlotte', 'Jackson', 'Female', '2005-06-21', '0987654335', 'charlotte.jackson@gmail.com', 'Phnom Penh', 'D002', '12345', 83.70),
(21, 'Benjamin', 'White', 'Male', '2005-07-22', '0987654336', 'benjamin.white@gmail.com', 'Takeo', 'D005', '12345', 78.30),
(22, 'Amelia', 'Harris', 'Female', '2005-08-23', '0987654337', 'amelia.harris@gmail.com', 'Battambang', 'D004', '12345', 87.25),
(23, 'Jacob', 'Martin', 'Male', '2005-09-24', '0987654338', 'jacob.martin@gmail.com', 'Phnom Penh', 'D001', '12345', 90.70),
(24, 'Mia', 'Garcia', 'Female', '2005-10-25', '0987654339', 'mia.garcia@gmail.com', 'Battambang', 'D004', '12345', 76.80),
(25, 'Ethan', 'Martinez', 'Male', '2005-11-26', '0987654340', 'ethan.martinez@gmail.com', 'Siem Reap', 'D001', '12345', 85.10),
(26, 'Isabella', 'Rodriguez', 'Female', '2005-12-27', '0987654341', 'isabella.rodriguez@gmail.com', '', 'D002', '12345', 92.60),
(27, 'Logan', 'Lee', 'Male', '2006-01-28', '0987654342', 'logan.lee@gmail.com', '', 'D003', '12345', 81.00),
(28, 'Sophia', 'Gonzalez', 'Female', '2006-02-28', '0987654343', 'sophia.gonzalez@gmail.com', '', 'D001', '12345', 78.95),
(29, 'Jackson', 'Perez', 'Male', '2006-03-01', '0987654344', 'jackson.perez@gmail.com', '', 'D004', '12345', 89.85),
(30, 'Harper', 'Wilson', 'Female', '2006-04-02', '0987654345', 'harper.wilson@gmail.com', 'Svay Rieng', 'D002', '12345', 94.50),
(31, 'Aiden', 'Anderson', 'Male', '2006-05-03', '0987654346', 'aiden.anderson@gmail.com', '', 'D005', '12345', 82.75),
(32, 'Mason', 'Thomas', 'Male', '2006-06-04', '0987654347', 'mason.thomas@gmail.com', '', 'D001', '12345', 79.10),
(33, 'Ella', 'Taylor', 'Female', '2006-07-05', '0987654348', 'ella.taylor@gmail.com', '', 'D004', '12345', 88.20),
(34, 'Oliver', 'White', 'Male', '2006-08-06', '0987654349', 'oliver.white@gmail.com', '', 'D003', '12345', 91.90),
(35, 'Liam', 'Clark', 'Male', '2006-09-07', '0987654350', 'liam.clark@gmail.com', '', 'D002', '12345', 80.25),
(36, 'Zoe', 'Lewis', 'Female', '2006-10-08', '0987654351', 'zoe.lewis@gmail.com', '', 'D001', '12345', 93.80),
(37, 'James', 'Walker', 'Male', '2006-11-09', '0987654352', 'james.walker@gmail.com', '', 'D004', '12345', 84.50),
(38, 'Lily', 'Young', 'Female', '2006-12-10', '0987654353', 'lily.young@gmail.com', '', 'D005', '12345', 77.85),
(45, 'too', 'tee', 'Male', '2001-08-04', '011111111', 'too@gmail.com', 'rrr', 'D003', '12345', NULL),
(47, 'ree', 'ra', 'Male', '2009-04-04', '012345666', 'ra.re@', 'kompot', 'D005', '12345', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
