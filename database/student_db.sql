-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 30, 2025 at 07:03 AM
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
-- Table structure for table `admin_account`
--

DROP TABLE IF EXISTS `admin_account`;
CREATE TABLE IF NOT EXISTS `admin_account` (
  `admin_id` varchar(100) NOT NULL,
  `admin_name` varchar(100) NOT NULL,
  `admin_gender` varchar(10) NOT NULL,
  `admin_email` varchar(100) NOT NULL,
  `admin_password` varchar(200) NOT NULL,
  `profile_image` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `admin_account`
--

INSERT INTO `admin_account` (`admin_id`, `admin_name`, `admin_gender`, `admin_email`, `admin_password`, `profile_image`) VALUES
('A001', 'Sarith Seyla', 'Male', 'seyla@gmail.com', 'seyla@99', 'C:\\Users\\User\\OneDrive\\Pictures\\hacker_wallpaper_by_manthan003_dga8tnf-fullview.jpg'),
('A002', 'Ka Minea', 'Female', 'minea@gmail.com', 'minea@99', 'C:\\Users\\User\\OneDrive\\Pictures\\447261415_3912611302329809_8109512853108057139_n.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `subject_ID` varchar(20) NOT NULL,
  `subject` varchar(200) DEFAULT NULL,
  `hour_per_week` int(11) NOT NULL,
  `hour_per_semester` int(11) NOT NULL,
  PRIMARY KEY (`subject_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`subject_ID`, `subject`, `hour_per_week`, `hour_per_semester`) VALUES
('S002', 'Statistic', 4, 32),
('S001', 'Network', 4, 32),
('S003', 'Analyse and Design', 16, 32),
('S004', 'OOP', 4, 32),
('S005', 'Electronic', 4, 16);

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_name`, `department_head`, `Dep_head_ph`) VALUES
('D001', 'GIC', 'Sarith Seyla', '012269887'),
('D002', 'GTR', 'Rom Tola', '096202236'),
('D003', 'AMS', 'Peng Seyha', '097845623'),
('D004', 'GGG', 'Sar Vichada', '06886239'),
('D005', 'GEE', 'Tek ChanSetha', '078963302'),
('D006', 'GIR', 'Khanha Ly', '0122656695'),
('D007', 'GIE', 'Mana Lina', '0265926353'),
('D008', 'KIT', 'fewsf', '2656556'),
('D010', 'GIM', 'Lim Bopha', '065897772');

-- --------------------------------------------------------

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
CREATE TABLE IF NOT EXISTS `score` (
  `student_id` int(11) NOT NULL,
  `department_id` varchar(100) NOT NULL,
  `subject_id` varchar(100) NOT NULL,
  `student_score` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `score`
--

INSERT INTO `score` (`student_id`, `department_id`, `subject_id`, `student_score`) VALUES
(1, 'D001', 'S002', 86),
(2, 'D001', 'S004', 90.02),
(1, 'D001', 'S005', 75.9),
(5, 'D003', 'S004', 83.67),
(9, 'D005', 'S003', 97.5);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_firstname` varchar(50) DEFAULT NULL,
  `student_lastname` varchar(50) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `student_birth` date DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `student_email` varchar(255) DEFAULT NULL,
  `student_address` varchar(200) NOT NULL,
  `department` varchar(50) NOT NULL,
  `student_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `student_firstname`, `student_lastname`, `gender`, `student_birth`, `phone_number`, `student_email`, `student_address`, `department`, `student_password`) VALUES
(1, 'Tola', 'Rom', 'Male', '2006-10-16', '0123456789', 'tola.rom@gmail.com', 'Pailin', 'D001', 'tola@9999'),
(2, 'Nita', 'Rom', 'Female', '2008-10-16', '0123456788', 'nita.rom@gmail.com', 'Koh Kong', 'D001', '12345'),
(3, 'Dara', 'Rom', 'Male', '2014-09-16', '0123456768', 'dara.rom@gmail.com', 'Battambang', 'D004', '12345'),
(4, 'Toek', 'Tak', 'Male', '2008-10-16', '0123212788', 'toek.tak@gmail.com', 'Phnom Penh', 'D001', '12345'),
(5, 'Nakry', 'Tang', 'Male', '2008-10-16', '0124546788', 'nakry.tang@gmail.com', 'Kampong Thom', 'D001', '12345'),
(6, 'Setha', 'Chan', 'Male', '2004-04-07', '0123456789', 'setha.chan@gmail.com', 'Phnom Penh', 'D003', '12345'),
(7, 'John', 'Doe', 'Male', '2004-05-08', '0987654322', 'john.doe@gmail.com', 'Battambang', 'D004', '12345'),
(8, 'Jane', 'Smith', 'Female', '2004-06-09', '0987654323', 'jane.smith@gmail.com', 'Phnom Penh', 'D001', '12345'),
(9, 'David', 'Brown', 'Male', '2004-07-10', '0987654324', 'david.brown@gmail.com', 'Koh Kong', 'D004', '12345'),
(10, 'Emily', 'Taylor', 'Female', '2004-08-11', '0987654325', 'emily.taylor@gmail.com', 'Siem Reap', 'D001', '12345'),
(11, 'Michael', 'Williams', 'Male', '2004-09-12', '0987654326', 'michael.williams@gmail.com', 'Siem Reap', 'D001', '12345'),
(12, 'Sarah', 'Jones', 'Female', '2004-10-13', '0987654327', 'sarah.jones@gmail.com', 'Battambang', 'D002', '12345'),
(13, 'Daniel', 'Davis', 'Male', '2004-11-14', '0987654328', 'daniel.davis@gmail.com', 'Phnom Penh', 'D003', '12345'),
(14, 'Sophie', 'Miller', 'Female', '2004-12-15', '0987654329', 'sophie.miller@gmail.com', 'Siem Reap', 'D005', '12345'),
(15, 'Lucas', 'Wilson', 'Male', '2005-01-16', '0987654330', 'lucas.wilson@gmail.com', 'Battambang', 'D001', '12345'),
(16, 'Olivia', 'Moore', 'Female', '2005-02-17', '0987654331', 'olivia.moore@gmail.com', 'Siem Reap', 'D005', '12345'),
(17, 'William', 'Taylor', 'Male', '2005-03-18', '0987654332', 'william.taylor@gmail.com', 'Phnom Penh', 'D002', '12345'),
(18, 'Ava', 'Anderson', 'Female', '2005-04-19', '0987654333', 'ava.anderson@gmail.com', 'Takeo', 'D004', '12345'),
(19, 'James', 'Thomas', 'Male', '2005-05-20', '0987654334', 'james.thomas@gmail.com', 'Koh Kong', 'D001', '12345'),
(20, 'Charlotte', 'Jackson', 'Female', '2005-06-21', '0987654335', 'charlotte.jackson@gmail.com', 'Phnom Penh', 'D002', '12345'),
(21, 'Benjamin', 'White', 'Male', '2005-07-22', '0987654336', 'benjamin.white@gmail.com', 'Takeo', 'D005', '12345'),
(22, 'Amelia', 'Harris', 'Female', '2005-08-23', '0987654337', 'amelia.harris@gmail.com', 'Battambang', 'D004', '12345'),
(23, 'Jacob', 'Martin', 'Male', '2005-09-24', '0987654338', 'jacob.martin@gmail.com', 'Phnom Penh', 'D001', '12345'),
(24, 'Mia', 'Garcia', 'Female', '2005-10-25', '0987654339', 'mia.garcia@gmail.com', 'Battambang', 'D004', '12345'),
(25, 'Ethan', 'Martinez', 'Male', '2005-11-26', '0987654340', 'ethan.martinez@gmail.com', 'Siem Reap', 'D001', '12345'),
(26, 'Isabella', 'Rodriguez', 'Female', '2005-12-27', '0987654341', 'isabella.rodriguez@gmail.com', '', 'D002', '12345'),
(27, 'Logan', 'Lee', 'Male', '2006-01-28', '0987654342', 'logan.lee@gmail.com', '', 'D003', '12345'),
(28, 'Sophia', 'Gonzalez', 'Female', '2006-02-28', '0987654343', 'sophia.gonzalez@gmail.com', '', 'D001', '12345'),
(29, 'Jackson', 'Perez', 'Male', '2006-03-01', '0987654344', 'jackson.perez@gmail.com', '', 'D004', '12345'),
(30, 'Harper', 'Wilson', 'Female', '2006-04-02', '0987654345', 'harper.wilson@gmail.com', 'Svay Rieng', 'D002', '12345'),
(31, 'Aiden', 'Anderson', 'Male', '2006-05-03', '0987654346', 'aiden.anderson@gmail.com', '', 'D005', '12345'),
(32, 'Mason', 'Thomas', 'Male', '2006-06-04', '0987654347', 'mason.thomas@gmail.com', '', 'D001', '12345'),
(33, 'Ella', 'Taylor', 'Female', '2006-07-05', '0987654348', 'ella.taylor@gmail.com', '', 'D004', '12345'),
(34, 'Oliver', 'White', 'Male', '2006-08-06', '0987654349', 'oliver.white@gmail.com', '', 'D003', '12345'),
(35, 'Liam', 'Clark', 'Male', '2006-09-07', '0987654350', 'liam.clark@gmail.com', '', 'D002', '12345'),
(36, 'Zoe', 'Lewis', 'Female', '2006-10-08', '0987654351', 'zoe.lewis@gmail.com', '', 'D001', '12345'),
(37, 'James', 'Walker', 'Male', '2006-11-09', '0987654352', 'james.walker@gmail.com', '', 'D004', '12345'),
(38, 'Lily', 'Young', 'Female', '2006-12-10', '0987654353', 'lily.young@gmail.com', '', 'D005', '12345'),
(44, 'Lina', 'Kanha', 'Female', '2004-02-10', '03265656565', 'kaka@gmail.com', 'Phnom penh', 'D001', '12345');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
