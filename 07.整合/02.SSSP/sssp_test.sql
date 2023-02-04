/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 8.0.13 : Database - sssp_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sssp_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `sssp_test`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `department` */

insert  into `department`(`id`,`department_name`) values 
(1,'开发部'),
(2,'行政部'),
(3,'后勤部'),
(4,'公关部');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birth` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hr5ovw667hkx0jl5cmyo66wb8` (`department_id`),
  CONSTRAINT `FK_hr5ovw667hkx0jl5cmyo66wb8` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employee` */

insert  into `employee`(`id`,`birth`,`create_time`,`email`,`name`,`department_id`) values 
(1,'2022-03-18','2022-03-15 16:42:54','AA@163.com','AA',1),
(2,'2022-03-03','2022-03-15 16:42:58','BB@163.com','BB',2),
(3,'2022-03-03','2022-03-15 16:42:55','CC@163.com','CC',1),
(4,'2022-03-02','2022-03-15 16:43:05','DD@163.com','DD',3),
(5,'2022-03-11','2022-03-15 16:43:01','EE@163.com','EE',4),
(6,'2022-03-04','2022-03-15 16:43:07','FF@163.com','FF',2),
(7,'2022-03-13','2022-03-15 16:43:10','GG@163.com','GG',3),
(8,'2022-11-15','2022-03-15 21:12:07','AAA@qq.com','AAA',1),
(9,'2022-03-18','2022-03-16 16:56:37','AA@163.com','BBB',2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
