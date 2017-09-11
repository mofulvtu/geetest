/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-11-21 21:27:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '用户编码',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `user_pwd` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_phone` varchar(255) DEFAULT NULL COMMENT '用户手机号',
  `user_email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
  `user_note` varchar(255) DEFAULT NULL COMMENT '用户备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'admin', '15512345678', '123@126.com', '管理员');
