/*
SQLyog Ultimate v12.14 (64 bit)
MySQL - 8.0.13 : Database - ssm_crud_test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm_crud_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `ssm_crud_test`;

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `department` */

insert  into `department`(`dept_id`,`dept_name`) values 
(1,'开发部'),
(2,'测试部'),
(3,'后勤部'),
(4,'财务部');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `emp_dept` (`d_id`),
  CONSTRAINT `emp_dept` FOREIGN KEY (`d_id`) REFERENCES `department` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `employee` */

insert  into `employee`(`emp_id`,`emp_name`,`gender`,`email`,`d_id`) values 
(1,'1','M','11@163.com',2),
(2,'AA','M','22@163.com',1),
(3,'BB','F','BB@163.com',2),
(4,'CC','M','CC@163.com',1),
(5,'DD','F','DD@163.com',3),
(6,'4a3530','M','4a3530@163.com',4),
(7,'25a641','M','25a641@163.com',4),
(8,'74ace2','M','74ace2@163.com',4),
(9,'0d3673','M','0d3673@163.com',4),
(10,'afb6c4','M','afb6c4@163.com',4),
(11,'990980','M','990980@163.com',4),
(12,'7482f1','M','7482f1@163.com',4),
(13,'c7a252','M','c7a252@163.com',4),
(14,'31c133','M','31c133@163.com',4),
(15,'28b104','M','28b104@163.com',4),
(16,'406c05','M','406c05@163.com',4),
(17,'955026','M','955026@163.com',4),
(18,'1968a7','M','1968a7@163.com',4),
(19,'4bb808','M','4bb808@163.com',4),
(20,'656929','M','656929@163.com',4),
(21,'23b8d10','M','23b8d10@163.com',4),
(22,'67f6011','M','67f6011@163.com',4),
(23,'4c4de12','M','4c4de12@163.com',4),
(24,'fd1c013','M','fd1c013@163.com',4),
(25,'dfc6214','M','dfc6214@163.com',4),
(26,'fb21715','M','fb21715@163.com',4),
(27,'f986616','M','f986616@163.com',4),
(28,'5513a17','M','5513a17@163.com',4),
(29,'44c4018','M','44c4018@163.com',4),
(30,'7a6a819','M','7a6a819@163.com',4),
(31,'5c30d20','M','5c30d20@163.com',4),
(32,'94eed21','M','94eed21@163.com',4),
(33,'db54622','M','db54622@163.com',4),
(34,'9f5b723','M','9f5b723@163.com',4),
(35,'edd4a24','M','edd4a24@163.com',4),
(36,'d7fb925','M','d7fb925@163.com',4),
(37,'6d2c026','M','6d2c026@163.com',4),
(38,'9d48e27','M','9d48e27@163.com',4),
(39,'65dd128','M','65dd128@163.com',4),
(40,'de5ed29','M','de5ed29@163.com',4),
(41,'25b8730','M','25b8730@163.com',4),
(42,'1334f31','M','1334f31@163.com',4),
(43,'3708732','M','3708732@163.com',4),
(44,'0b48c33','M','0b48c33@163.com',4),
(45,'59aaf34','M','59aaf34@163.com',4),
(46,'3fce935','M','3fce935@163.com',4),
(47,'7924e36','M','7924e36@163.com',4),
(48,'d9a6037','M','d9a6037@163.com',4),
(49,'ebd0638','M','ebd0638@163.com',4),
(50,'6e17b39','M','6e17b39@163.com',4),
(51,'0189040','M','0189040@163.com',4),
(52,'443be41','M','443be41@163.com',4),
(53,'62fb142','M','62fb142@163.com',4),
(54,'9774f43','M','9774f43@163.com',4),
(55,'c15e544','M','c15e544@163.com',4),
(56,'c56b145','M','c56b145@163.com',4),
(57,'128a446','M','128a446@163.com',4),
(58,'6640647','M','6640647@163.com',4),
(59,'617bc48','M','617bc48@163.com',4),
(60,'b7c0c49','M','b7c0c49@163.com',4),
(61,'qwe123456','M','qwe@qq.com',1),
(62,'aaaaaaaaaa','F','123@qq.com',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
