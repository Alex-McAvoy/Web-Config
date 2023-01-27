/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : mp

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 25/10/2022 21:30:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_employee`;
CREATE TABLE `tbl_employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_employee
-- ----------------------------
INSERT INTO `tbl_employee` VALUES (1, 'Tom', 'tom@123.com', '1', 22);
INSERT INTO `tbl_employee` VALUES (2, 'Jerry', 'jerry@123.com', '0', 25);
INSERT INTO `tbl_employee` VALUES (3, 'Black', 'black@123.com', '1', 30);
INSERT INTO `tbl_employee` VALUES (4, 'White', 'white@123.com', '0', 35);
INSERT INTO `tbl_employee` VALUES (5, 'MP', 'mp@123.com', '1', 22);
INSERT INTO `tbl_employee` VALUES (6, 'MP', 'mp@123.com', '1', 22);
INSERT INTO `tbl_employee` VALUES (7, 'aaa', 'aaa@qq.com', '0', 33);
INSERT INTO `tbl_employee` VALUES (8, 'aaa', 'aaa@aaa.com', '0', 22);

-- ----------------------------
-- Table structure for tbl_man
-- ----------------------------
DROP TABLE IF EXISTS `tbl_man`;
CREATE TABLE `tbl_man`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL,
  `age` int(8) NULL DEFAULT NULL,
  `version` int(8) NULL DEFAULT NULL COMMENT '用于乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_man
-- ----------------------------
INSERT INTO `tbl_man` VALUES (1, 'AA', 'AA@AA.com', 1, 22, 4);
INSERT INTO `tbl_man` VALUES (2, 'bb', 'bb', 0, 12, 1);
INSERT INTO `tbl_man` VALUES (3, 'cc', 'cc', 1, 45, 1);

-- ----------------------------
-- Table structure for tbl_woman
-- ----------------------------
DROP TABLE IF EXISTS `tbl_woman`;
CREATE TABLE `tbl_woman`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL,
  `age` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_woman
-- ----------------------------
INSERT INTO `tbl_woman` VALUES (1, 'a', 'a', 1, 11);
INSERT INTO `tbl_woman` VALUES (2, 'b', 'b', 0, 22);
INSERT INTO `tbl_woman` VALUES (3, 'c', 'c', 1, 11);

-- ----------------------------
-- Table structure for tbl_worker
-- ----------------------------
DROP TABLE IF EXISTS `tbl_worker`;
CREATE TABLE `tbl_worker`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` int(1) NULL DEFAULT NULL,
  `age` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_worker
-- ----------------------------
INSERT INTO `tbl_worker` VALUES (1, 'a', 'a', 1, 11);
INSERT INTO `tbl_worker` VALUES (2, 'b', 'b', 1, 11);
INSERT INTO `tbl_worker` VALUES (3, 'c', 'c', 0, 51);
INSERT INTO `tbl_worker` VALUES (4, '456', '456@456.com', 1, 36);

SET FOREIGN_KEY_CHECKS = 1;
