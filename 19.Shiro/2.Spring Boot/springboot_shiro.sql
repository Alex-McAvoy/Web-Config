/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : springboot_shiro

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 29/10/2022 14:58:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名',
  `info` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限信息',
  `desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------
INSERT INTO `permissions` VALUES (1, '删除用户', 'user:delete', '删除用户');
INSERT INTO `permissions` VALUES (2, '新增用户', 'user:add', '新增用户');
INSERT INTO `permissions` VALUES (3, '修改用户', 'user:edit', '修改用户');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色显示名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '所有权限', '管理员');
INSERT INTO `role` VALUES (2, 'serMag', '用户管理权限', '用户管理');

-- ----------------------------
-- Table structure for role_ps
-- ----------------------------
DROP TABLE IF EXISTS `role_ps`;
CREATE TABLE `role_ps`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色 id',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '权限 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限映射\r\n表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_ps
-- ----------------------------
INSERT INTO `role_ps` VALUES (1, 1, 1);
INSERT INTO `role_ps` VALUES (2, 1, 2);
INSERT INTO `role_ps` VALUES (3, 1, 3);

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `uid` bigint(20) NULL DEFAULT NULL COMMENT '用户 id',
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色 id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色用户映射\r\n表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES (1, 1, 1);
INSERT INTO `role_user` VALUES (2, 1, 2);
INSERT INTO `role_user` VALUES (3, 2, 2);
INSERT INTO `role_user` VALUES (4, NULL, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'AA', 'd1b129656359e35e95ebd56a63d7b9e0', 1);
INSERT INTO `user` VALUES (2, 'BB', 'd1b129656359e35e95ebd56a63d7b9e0', 2);

SET FOREIGN_KEY_CHECKS = 1;
