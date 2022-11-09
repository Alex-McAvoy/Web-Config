/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : springboot_test

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 26/10/2022 18:22:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '部门1');
INSERT INTO `department` VALUES (2, '部门2');
INSERT INTO `department` VALUES (3, '部门3');
INSERT INTO `department` VALUES (4, '部门4');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL,
  `d_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `d_id`(`d_id`) USING BTREE,
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`d_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'AAAAA', 'aa@123.com', 1, 3);
INSERT INTO `employee` VALUES (2, 'bb', 'bb@163.com', 1, 2);
INSERT INTO `employee` VALUES (3, 'cc', 'cc', 0, 1);
INSERT INTO `employee` VALUES (4, 'dd', 'dd', 1, 3);
INSERT INTO `employee` VALUES (5, 'ee', 'ee', 1, 1);
INSERT INTO `employee` VALUES (6, 'ff', 'ff@qq.com', 1, 4);
INSERT INTO `employee` VALUES (7, 'qwe', 'qwe@11.com', 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
