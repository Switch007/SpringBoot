/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : spdumread

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2017-11-06 11:52:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_adminer
-- ----------------------------
DROP TABLE IF EXISTS `t_adminer`;
CREATE TABLE `t_adminer` (
  `id` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `nick_name` varchar(100) CHARACTER SET utf8mb4 DEFAULT '',
  `password` varchar(100) CHARACTER SET utf8mb4 DEFAULT '',
  `role` int(10) DEFAULT '0',
  `status` int(10) DEFAULT '0',
  `is_del` int(10) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(100) CHARACTER SET utf8mb4 DEFAULT '',
  `cover_img` varchar(255) CHARACTER SET utf8mb4 DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
