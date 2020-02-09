/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : elong_shield

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2019-12-23 11:59:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for member_info
-- ----------------------------
DROP TABLE IF EXISTS `member_info`;
CREATE TABLE `member_info` (
  `id` bigint(20) NOT NULL,
  `cardNo` bigint(20) DEFAULT NULL,
  `userId` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `faceUrl` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `mobileType` tinyint(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `proxyId` varchar(255) DEFAULT NULL,
  `sex` tinyint(11) DEFAULT NULL,
  `status` tinyint(11) DEFAULT NULL,
  `memType` tinyint(11) DEFAULT NULL,
  `createTime` varchar(255) DEFAULT NULL,
  `opName` varchar(255) DEFAULT NULL,
  `_timestamp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_info
-- ----------------------------
INSERT INTO `member_info` VALUES ('123277403', '240000000095597608', 'ABC', '', '张阳', 'http://pavo.elongstatic.com/i/ori/000g5oLa.jpg', '821fa6f9700e84d61', '0', '13718935893', 'lhl@elong.com', 'AP0011893', '1', '0', '0', '2016-10-28 18:52:47', '172.21.34.16', null);
INSERT INTO `member_info` VALUES ('123277404', '240000000095597609', 'DEF', '', '张阳', 'http://pavo.elongstatic.com/i/ori/000g5oLa.jpg', '821fa6f9700e84d61', '0', '13718935893', '123456@qq.com', 'AP0011893', '1', '0', '0', '2016-10-28 18:52:47', '172.21.34.17', null);
INSERT INTO `member_info` VALUES ('123277405', '240000000095597609', 'DEF', '', '张三', 'http://pavo.elongstatic.com/i/ori/000g5oLa.jpg', '821fa6f9700e84d61', '0', '13718935899', '123456@qq.com', 'AP0011893', '1', '0', '0', '2016-10-28 18:52:47', '172.21.34.17', null);
