/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2021-01-12 16:59:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `name_age`
-- ----------------------------
DROP TABLE IF EXISTS `name_age`;
CREATE TABLE `name_age` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(255) NOT NULL,
  `age` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of name_age
-- ----------------------------
INSERT INTO `name_age` VALUES ('1', 'alex', '50');
INSERT INTO `name_age` VALUES ('2', 'tom', '22');
