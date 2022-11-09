/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 8.0.13 : Database - springdata_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springdata_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `springdata_test`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `address` */

insert  into `address`(`id`,`city`,`province`) values 
(1,'aa','AA'),
(2,'bb','BB');

/*Table structure for table `persons` */

DROP TABLE IF EXISTS `persons`;

CREATE TABLE `persons` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pgc67pertaq9r5tkqeerxephp` (`address_id`),
  CONSTRAINT `FK_pgc67pertaq9r5tkqeerxephp` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `persons` */

insert  into `persons`(`id`,`name`,`email`,`birth`,`address_id`) values 
(1,'AA','XX','2022-03-12',1),
(2,'BB','BB@163.com','2022-03-13',2),
(3,'CC','CC@163.com','2022-03-13',1),
(4,'CD','DD@163.com','2022-03-13',2),
(5,'EE','EE@163.com','2022-03-13',1),
(6,'FE','FF@163.com','2022-03-13',1),
(7,'GG','GG@163.com','2022-03-13',1),
(8,'HH','HH@163.com','2022-03-13',2),
(9,'II','II@163.com','2022-03-13',1),
(10,'JJ','JJ@163.com','2022-03-13',2),
(11,'KK','KK@163.com','2022-03-13',2),
(12,'aa','aa@163.com','2022-03-13',NULL),
(13,'bb','bb@163.com','2022-03-13',NULL),
(14,'cc','cc@163.com','2022-03-13',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
