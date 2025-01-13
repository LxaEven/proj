-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 13, 2025 at 04:24 PM
-- Server version: 9.1.0
-- PHP Version: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student`
--

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_firstname` varchar(50) NOT NULL,
  `student_lastname` varchar(50) NOT NULL,
  `gender` enum('Male','Female','Other') NOT NULL,
  `student_birth` date NOT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `student_score` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `student_firstname`, `student_lastname`, `gender`, `student_birth`, `phone_number`, `email`, `student_score`) VALUES
(1, 'John', 'Doe', 'Male', '2000-05-15', '1234567890', 'john.doe@gmail.com', 85.50),
(2, 'Jane', 'Smith', 'Female', '1998-03-22', '2345678901', 'jane.smith@gmail.com', 92.30),
(3, 'Alice', 'Johnson', 'Female', '1999-07-10', '3456789012', 'alice.johnson@gmail.com', 88.00),
(4, 'Bob', 'Brown', 'Male', '2001-11-05', '4567890123', 'bob.brown@gmail.com', 75.40),
(5, 'Charlie', 'Davis', 'Male', '2002-01-30', '5678901234', 'charlie.davis@gmail.com', 80.00),
(6, 'Diana', 'Miller', 'Female', '2003-09-18', '6789012345', 'diana.miller@gmail.com', 90.20),
(7, 'Edward', 'Wilson', 'Male', '1997-12-12', '7890123456', 'edward.wilson@gmail.com', 78.50),
(8, 'Fiona', 'Taylor', 'Female', '2004-04-25', '8901234567', 'fiona.taylor@gmail.com', 95.00),
(9, 'George', 'Anderson', 'Male', '2000-02-16', '9012345678', 'george.anderson@gmail.com', 83.75),
(10, 'Hannah', 'Thomas', 'Female', '1996-06-06', '1238904567', 'hannah.thomas@gmail.com', 89.10),
(11, 'Liam', 'Martinez', 'Male', '2002-02-25', '1122334455', 'liam.martinez@gmail.com', 80.50),
(12, 'Mia', 'Roberts', 'Female', '2001-04-10', '2233445566', 'mia.roberts@gmail.com', 91.75),
(13, 'Noah', 'Walker', 'Male', '2003-06-14', '3344556677', 'noah.walker@gmail.com', 78.60),
(14, 'Olivia', 'Harris', 'Female', '2000-08-19', '4455667788', 'olivia.harris@gmail.com', 89.40),
(15, 'Ethan', 'Lewis', 'Male', '2004-09-22', '5566778899', 'ethan.lewis@gmail.com', 85.30),
(16, 'Sophia', 'Young', 'Female', '2002-11-30', '6677889900', 'sophia.young@gmail.com', 92.10),
(17, 'Mason', 'King', 'Male', '2003-12-01', '7788990011', 'mason.king@gmail.com', 87.80),
(18, 'Isabella', 'Scott', 'Female', '2000-01-12', '8899001122', 'isabella.scott@gmail.com', 91.20),
(19, 'James', 'Green', 'Male', '2001-05-05', '9900112233', 'james.green@gmail.com', 80.90),
(20, 'Amelia', 'Adams', 'Female', '2004-07-18', '1001122334', 'amelia.adams@gmail.com', 94.00),
(21, 'Benjamin', 'Baker', 'Male', '1999-03-10', '1122334455', 'benjamin.baker@gmail.com', 82.70),
(22, 'Charlotte', 'Nelson', 'Female', '2000-10-16', '2233445566', 'charlotte.nelson@gmail.com', 88.50),
(23, 'Henry', 'Carter', 'Male', '2002-02-23', '3344556677', 'henry.carter@gmail.com', 84.40),
(24, 'Aria', 'Mitchell', 'Female', '2001-11-01', '4455667788', 'aria.mitchell@gmail.com', 90.30),
(25, 'Alexander', 'Perez', 'Male', '2003-09-08', '5566778899', 'alexander.perez@gmail.com', 76.90),
(26, 'Victoria', 'Robinson', 'Female', '2000-12-05', '6677889900', 'victoria.robinson@gmail.com', 92.50),
(27, 'William', 'Clark', 'Male', '2002-07-29', '7788990011', 'william.clark@gmail.com', 79.80),
(28, 'Madison', 'Lewis', 'Female', '2003-05-14', '8899001122', 'madison.lewis@gmail.com', 85.00),
(29, 'Samuel', 'Lopez', 'Male', '2001-08-27', '1001122334', 'samuel.lopez@gmail.com', 82.60),
(30, 'Ella', 'Walker', 'Female', '2000-04-13', '1122334455', 'ella.walker@gmail.com', 88.20);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
