/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2017-06-06 09:55:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '123456', '17854247856', '21', '2017-06-05 11:03:45', '2017-06-05 11:04:27');
INSERT INTO `user` VALUES ('2', '李四', '456123', '14558745698', '22', '2017-06-05 11:04:08', '2017-06-05 11:04:27');
INSERT INTO `user` VALUES ('3', '王五', '321654', '15265898745', '23', '2017-06-05 11:04:27', '2017-06-05 11:04:27');
