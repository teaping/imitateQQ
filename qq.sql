/*
Navicat MySQL Data Transfer

Source Server         : mysqk
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : qq

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2021-03-17 09:19:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flocks
-- ----------------------------
DROP TABLE IF EXISTS `flocks`;
CREATE TABLE `flocks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ctreaName` varchar(255) NOT NULL,
  `ProNums` int(11) DEFAULT NULL,
  `ProName` varchar(255) DEFAULT NULL,
  `NameId` varchar(255) DEFAULT NULL,
  `zqid` varchar(255) DEFAULT NULL COMMENT '群主id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of flocks
-- ----------------------------
INSERT INTO `flocks` VALUES ('12', '吃鸡666', '4', '赵2', '333', '333');
INSERT INTO `flocks` VALUES ('13', '吃鸡666', '4', '关东升', '111', '333');
INSERT INTO `flocks` VALUES ('14', '吃鸡666', '4', '赵1', '222', '333');
INSERT INTO `flocks` VALUES ('15', '吃鸡666', '4', '赵3', '888', '333');
INSERT INTO `flocks` VALUES ('16', '尿裤子', '3', '牛', '12345', '12345');
INSERT INTO `flocks` VALUES ('17', '尿裤子', '3', '关东井', '111', '12345');
INSERT INTO `flocks` VALUES ('18', '尿裤子', '3', '赵3', '888', '12345');
INSERT INTO `flocks` VALUES ('19', '123', '5', '关东井', '111', '111');
INSERT INTO `flocks` VALUES ('20', '123', '5', '牛', '12345', '111');
INSERT INTO `flocks` VALUES ('21', '123', '5', '赵1', '222', '111');
INSERT INTO `flocks` VALUES ('22', '123', '5', '赵2', '333', '111');
INSERT INTO `flocks` VALUES ('23', '123', '5', '赵3', '888', '111');
INSERT INTO `flocks` VALUES ('24', '3435', '4', '啊是大', '111', '111');
INSERT INTO `flocks` VALUES ('25', '3435', '4', '牛2', '12345', '111');
INSERT INTO `flocks` VALUES ('26', '3435', '4', '赵1', '222', '111');
INSERT INTO `flocks` VALUES ('27', '3435', '4', '赵2', '333', '111');

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `user_id1` varchar(80) NOT NULL,
  `user_id2` varchar(80) NOT NULL,
  PRIMARY KEY (`user_id1`,`user_id2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('111', '333');
INSERT INTO `friend` VALUES ('12345', '111');
INSERT INTO `friend` VALUES ('12345', '222');
INSERT INTO `friend` VALUES ('12345', '888');
INSERT INTO `friend` VALUES ('222', '111');
INSERT INTO `friend` VALUES ('222', '333');
INSERT INTO `friend` VALUES ('222', '888');
INSERT INTO `friend` VALUES ('333', '888');
INSERT INTO `friend` VALUES ('888', '111');

-- ----------------------------
-- Table structure for infouser
-- ----------------------------
DROP TABLE IF EXISTS `infouser`;
CREATE TABLE `infouser` (
  `Id` varchar(255) DEFAULT NULL,
  `NickName` varchar(255) DEFAULT NULL,
  `Autograph` varchar(255) DEFAULT NULL,
  `Gender` varchar(255) DEFAULT NULL,
  `Birthday` varchar(255) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `School` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `job` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of infouser
-- ----------------------------
INSERT INTO `infouser` VALUES ('111', '啊是大', '的法国队\n\n\n', '男', '2021-03-07', '上海', 'None', '腾讯', 'IT');
INSERT INTO `infouser` VALUES ('12345', '牛2', '大牛冲', '女', '2021-03-14', '上海', 'None', 'None', 'None');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(80) NOT NULL,
  `user_pwd` varchar(25) NOT NULL,
  `user_name` varchar(80) NOT NULL,
  `user_icon` varchar(100) NOT NULL,
  `state` int(2) NOT NULL,
  `ipone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('111', '111', '啊是大', '12', '0', '1581214123');
INSERT INTO `user` VALUES ('12345', '111', '牛2', '22', '0', null);
INSERT INTO `user` VALUES ('222', '123', '赵1', '30', '0', null);
INSERT INTO `user` VALUES ('333', '123', '赵2', '52', '0', null);
INSERT INTO `user` VALUES ('888', '123', '赵3', '53', '0', null);
