/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80016
Source Host           : localhost:3333
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2019-05-27 14:23:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'wzg168', '1');
