/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 8.0.13 : Database - ssh_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssh_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `ssh_test`;

/*Table structure for table `ssh_department` */

DROP TABLE IF EXISTS `ssh_department`;

CREATE TABLE `ssh_department` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPARTMENT_NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ssh_department` */

insert  into `ssh_department`(`ID`,`DEPARTMENT_NAME`) values 
(1,'销售部'),
(2,'研发部'),
(3,'人事部'),
(4,'行政部');

/*Table structure for table `ssh_employee` */

DROP TABLE IF EXISTS `ssh_employee`;

CREATE TABLE `ssh_employee` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `BIRTH` datetime DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `DEPARTMENT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_kfaoihyj5oll835mvidvgsxp` (`DEPARTMENT_ID`),
  CONSTRAINT `FK_kfaoihyj5oll835mvidvgsxp` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `ssh_department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `ssh_employee` */

insert  into `ssh_employee`(`ID`,`NAME`,`EMAIL`,`BIRTH`,`CREATE_TIME`,`DEPARTMENT_ID`) values 
(1,'AA','AA@qq.com','2022-02-18 04:53:31','2022-02-28 04:53:34',1),
(2,'BB','BB@qq.com','2022-02-28 03:26:45','2022-02-28 03:26:47',3),
(3,'CC','CC@qq.com','2022-02-28 04:13:13','2022-02-28 04:13:15',2),
(4,'DD','DD@qq.com','2000-11-25 00:00:00','2022-02-28 04:52:55',2),
(5,'EE','EE@qq.com','2000-11-15 00:00:00','2022-02-28 07:20:18',3),
(6,'FF','FF@qq.com','2022-02-10 07:20:57','2022-02-09 07:21:00',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
