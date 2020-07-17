/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : ssm

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 22/04/2020 12:01:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickName` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phoneNum` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '张三', '小三', '1388888888', 'zhangsan@QQ.com');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `orderTime` timestamp(0) NULL DEFAULT NULL,
  `peopleCount` int(11) NULL DEFAULT NULL,
  `orderDesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payType` int(11) NULL DEFAULT NULL,
  `orderStatus` int(11) NULL DEFAULT NULL,
  `productId` int(32) NULL DEFAULT NULL,
  `memberId` int(32) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_o_p`(`productId`) USING BTREE,
  INDEX `fk_o_m`(`memberId`) USING BTREE,
  CONSTRAINT `fk_o_m` FOREIGN KEY (`memberId`) REFERENCES `member` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_o_p` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '12345', '2018-02-03 00:00:00', 2, '没什么', 0, 1, 1, 1);
INSERT INTO `orders` VALUES ('2', '12346', '2020-04-15 11:15:17', 2, '没什么', 0, 1, 1, 1);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL,
  `permissionName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (107, '角色管理', '/role/findAll.do');
INSERT INTO `permission` VALUES (1943, '资源权限管理', '/permission/findAll.do');
INSERT INTO `permission` VALUES (23527, '超级管理员', '/user/findAll.do');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(32) NOT NULL,
  `productNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `productName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cityName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `DepartureTime` date NULL DEFAULT NULL,
  `productPrice` double(11, 0) NULL DEFAULT NULL,
  `productDesc` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `productStatus` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '001', '云南一日游', '云南', '2020-04-07', 2100, '云南欢迎你', 0);
INSERT INTO `product` VALUES (2, '002', '昆明三日游', '昆明', '2020-06-06', 1800, '昆明欢迎你', 1);
INSERT INTO `product` VALUES (3, '003', '上海一日游', '上海', '2020-05-08', 3800, '魔都欢迎你', 1);
INSERT INTO `product` VALUES (4, '004', '北京三日游', '北京', '2020-05-09', 5800, '北京我来了', 1);
INSERT INTO `product` VALUES (5, '005', '深圳七日游', '昆明', '2020-04-07', 18000, '深圳欢迎你', 1);
INSERT INTO `product` VALUES (6, '006', '昭通一日游', '昭通', '2020-05-08', 1200, '昭通大山包', 0);
INSERT INTO `product` VALUES (7, '007', '丽江一日游', '昆明', '2020-06-04', 1500, '丽江古镇', 0);
INSERT INTO `product` VALUES (8, 'aaa', 'aaaaa', 'aaaaa', '2020-04-07', 18000, 'aaaa', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL,
  `roleName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'ADMIN', '系统管理员');
INSERT INTO `role` VALUES (2, 'USER', '用户');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `permissionId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`permissionId`, `roleId`) USING BTREE,
  INDEX `r_id`(`roleId`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (107, 1);
INSERT INTO `role_permission` VALUES (1943, 1);
INSERT INTO `role_permission` VALUES (23527, 1);
INSERT INTO `role_permission` VALUES (107, 2);
INSERT INTO `role_permission` VALUES (1943, 2);
INSERT INTO `role_permission` VALUES (23527, 2);

-- ----------------------------
-- Table structure for syslog
-- ----------------------------
DROP TABLE IF EXISTS `syslog`;
CREATE TABLE `syslog`  (
  `id` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `visitTime` datetime NULL DEFAULT NULL COMMENT '访问时间',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作者用户名',
  `ip` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问ip',
  `url` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问资源url',
  `executionTime` int(11) NULL DEFAULT NULL COMMENT '执行时长',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问方法',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of syslog
-- ----------------------------
INSERT INTO `syslog` VALUES ('1945daa5941849ada68f3d6cb4031510', '2020-04-16 16:57:45', 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 22, '[类名] com.hz.controller.UserController [方法名] findAll');
INSERT INTO `syslog` VALUES ('3004d6b5db9949ae9e5d7c3a61e35a31', '2020-04-22 03:34:22', 'admin', '0:0:0:0:0:0:0:1', '/orders/findAll.do', 7, '[类名] com.hz.controller.OrdersController [方法名] findAll');
INSERT INTO `syslog` VALUES ('5c5f1a0fdcd24c7bbfee6502fa166c65', '2020-04-22 03:20:43', 'admin', '0:0:0:0:0:0:0:1', '/user/findAll.do', 23, '[类名] com.hz.controller.UserController [方法名] findAll');
INSERT INTO `syslog` VALUES ('a2184cdd4c3f417d9d211d3d28a04d4b', '2020-04-22 03:48:32', 'admin', '0:0:0:0:0:0:0:1', '/orders/findAll.do', 23, '[类名] com.hz.controller.OrdersController [方法名] findAll');
INSERT INTO `syslog` VALUES ('da96c3577bab4e26949aadaa19ca75b3', '2020-04-22 03:34:15', 'admin', '0:0:0:0:0:0:0:1', '/orders/findAll.do', 22, '[类名] com.hz.controller.OrdersController [方法名] findAll');
INSERT INTO `syslog` VALUES ('e46c2915c47f413c9b4b7d3b1beee561', '2020-04-22 03:20:54', 'admin', '0:0:0:0:0:0:0:1', '/product/findAll.do', 16, '[类名] com.hz.controller.ProductController [方法名] findAll');
INSERT INTO `syslog` VALUES ('fbdd9303270e4e16921ca938652ac1ca', '2020-04-22 03:20:57', 'admin', '0:0:0:0:0:0:0:1', '/orders/findAll.do', 48, '[类名] com.hz.controller.OrdersController [方法名] findAll');

-- ----------------------------
-- Table structure for traveller
-- ----------------------------
DROP TABLE IF EXISTS `traveller`;
CREATE TABLE `traveller`  (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credentialsType` int(11) NULL DEFAULT NULL COMMENT '证件类型 0身份证 1护照 2军官证',
  `credentialsNum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `travellerType` int(11) NULL DEFAULT NULL COMMENT '旅客类型(人群) 0 成人 1 儿童',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of traveller
-- ----------------------------
INSERT INTO `traveller` VALUES (1, '赵龙', '男', '13888888888', 0, '123456789009876543', 0);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL COMMENT '状态0 未开启 1 开启',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20615 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'a@qq.com', 'admin', '$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe', '13888888888', 1);
INSERT INTO `users` VALUES (2, 'adfa', 'tom', '$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe', '546514684', 1);
INSERT INTO `users` VALUES (3, '1847481@QQ.com', 'wBekvam', '$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe', '15752250992', 1);
INSERT INTO `users` VALUES (4, '11948939@qq.com', '等灯', '$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe', '15752250992', 0);
INSERT INTO `users` VALUES (5, 'a@qq.com', 'user', '$2a$10$i7QFXNog.2TT3pCrekha1uJsw54fcBPqVK1ncWtW6HxaGkiMFCBw.', '54154151', 1);
INSERT INTO `users` VALUES (6, 'a@qq.com', '赵龙', '$2a$10$Ce8LB3jdYDZ2f6HB281zA.4eC7v6ziJdK8MMWg0Yu8ETMg5ToMpIe', '13888888888', 1);
INSERT INTO `users` VALUES (6862, '11919@qq.com', 'hz', '$2a$10$mEKoiccVd9lmBJh7czLgy.3bIzaGLiUmn1nsQ65mEvSlI7G3K.1J6', '15752250992', 1);
INSERT INTO `users` VALUES (20614, 'zhixing1010@163.com', 'root', '$2a$10$xmIe31HlXwR5xLKAzzEnju.CDiYZ.qFuCaO832.fFQB0mWYNc/xya', '15752250992', 0);

-- ----------------------------
-- Table structure for users_role
-- ----------------------------
DROP TABLE IF EXISTS `users_role`;
CREATE TABLE `users_role`  (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`, `roleId`) USING BTREE,
  INDEX `roleId`(`roleId`) USING BTREE,
  CONSTRAINT `users_role_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `users_role_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users_role
-- ----------------------------
INSERT INTO `users_role` VALUES (1, 1);
INSERT INTO `users_role` VALUES (5, 1);
INSERT INTO `users_role` VALUES (6, 1);
INSERT INTO `users_role` VALUES (6862, 1);
INSERT INTO `users_role` VALUES (1, 2);
INSERT INTO `users_role` VALUES (2, 2);
INSERT INTO `users_role` VALUES (3, 2);
INSERT INTO `users_role` VALUES (4, 2);
INSERT INTO `users_role` VALUES (5, 2);
INSERT INTO `users_role` VALUES (6, 2);
INSERT INTO `users_role` VALUES (6862, 2);

SET FOREIGN_KEY_CHECKS = 1;
