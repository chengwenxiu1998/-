/*
Navicat MySQL Data Transfer

Source Server         : 106.12.151.7_3306
Source Server Version : 50562
Source Host           : 106.12.151.7:3306
Source Database       : timebank

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-01-02 09:55:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `chat_record`
-- ----------------------------
DROP TABLE IF EXISTS `chat_record`;
CREATE TABLE `chat_record` (
  `u_id_1` int(11) NOT NULL,
  `u_id_2` int(11) NOT NULL,
  `cr_record_text_url` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`u_id_1`,`u_id_2`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chat_record
-- ----------------------------

-- ----------------------------
-- Table structure for `coin_trage_record`
-- ----------------------------
DROP TABLE IF EXISTS `coin_trage_record`;
CREATE TABLE `coin_trage_record` (
  `u_id` int(11) NOT NULL,
  `ctr_finish_time` datetime NOT NULL,
  `ctr_count` int(11) DEFAULT NULL,
  `ctr_is_add_or_reduce` bit(1) DEFAULT NULL,
  PRIMARY KEY (`u_id`,`ctr_finish_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coin_trage_record
-- ----------------------------
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-05 11:51:26', '30', '');
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-06 12:53:24', '40', '');
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-29 07:56:40', '30', '');
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-29 10:50:21', '20', '');
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-29 12:53:24', '40', '');
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-29 12:54:06', '20', '');
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-29 12:56:40', '30', '');
INSERT INTO `coin_trage_record` VALUES ('1', '2018-12-29 13:54:06', '20', '');
INSERT INTO `coin_trage_record` VALUES ('2', '2018-12-29 10:50:21', '20', '');
INSERT INTO `coin_trage_record` VALUES ('3', '2018-12-29 11:51:26', '30', '');

-- ----------------------------
-- Table structure for `discuss`
-- ----------------------------
DROP TABLE IF EXISTS `discuss`;
CREATE TABLE `discuss` (
  `d_id` int(11) NOT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `u_id_louzhu` int(11) DEFAULT NULL,
  `d_topic_content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss
-- ----------------------------
INSERT INTO `discuss` VALUES ('1', '1', '1', '代取快递完成成效怎么样？');
INSERT INTO `discuss` VALUES ('2', '2', '2', '代买饭送的准时吗？');
INSERT INTO `discuss` VALUES ('3', '3', '3', '约网球感受怎么样？');
INSERT INTO `discuss` VALUES ('4', '7', '1', '神经');

-- ----------------------------
-- Table structure for `discuss_reply`
-- ----------------------------
DROP TABLE IF EXISTS `discuss_reply`;
CREATE TABLE `discuss_reply` (
  `d_id` int(11) NOT NULL DEFAULT '0',
  `u_id_reply` int(11) NOT NULL DEFAULT '0',
  `dr_reply_content` varchar(1000) DEFAULT NULL,
  `dr_reply_time` datetime NOT NULL,
  PRIMARY KEY (`d_id`,`u_id_reply`,`dr_reply_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discuss_reply
-- ----------------------------
INSERT INTO `discuss_reply` VALUES ('1', '2', '是挺好的', '2018-12-25 10:31:11');
INSERT INTO `discuss_reply` VALUES ('1', '2', '很准时送到！', '2018-12-29 11:08:24');
INSERT INTO `discuss_reply` VALUES ('2', '1', '我是', '2018-12-27 03:05:06');
INSERT INTO `discuss_reply` VALUES ('2', '1', '我是', '2018-12-27 03:05:13');
INSERT INTO `discuss_reply` VALUES ('2', '3', '按要求时间内送达，很好。', '2018-12-29 11:09:06');
INSERT INTO `discuss_reply` VALUES ('3', '1', '我是女神', '2018-12-27 02:57:25');
INSERT INTO `discuss_reply` VALUES ('3', '1', '刘满', '2018-12-27 02:59:07');
INSERT INTO `discuss_reply` VALUES ('3', '1', '没', '2018-12-27 05:17:17');
INSERT INTO `discuss_reply` VALUES ('3', '1', '送应然', '2018-12-27 05:21:18');
INSERT INTO `discuss_reply` VALUES ('3', '1', '送饭很快！', '2018-12-29 13:09:31');
INSERT INTO `discuss_reply` VALUES ('4', '3', '饭还热着，很好！', '2018-12-29 13:12:02');
INSERT INTO `discuss_reply` VALUES ('5', '1', '对方很专业！', '2018-12-30 11:10:30');

-- ----------------------------
-- Table structure for `shaishai`
-- ----------------------------
DROP TABLE IF EXISTS `shaishai`;
CREATE TABLE `shaishai` (
  `s_id` int(4) NOT NULL,
  `u_id` int(11) NOT NULL,
  `s_text` char(100) NOT NULL,
  `s_time` datetime NOT NULL,
  `s_count` int(4) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shaishai
-- ----------------------------
INSERT INTO `shaishai` VALUES ('1', '1', '能充分利用自己的时间！', '2018-12-20 09:28:00', '182');
INSERT INTO `shaishai` VALUES ('2', '2', '准时送达，超棒', '2018-12-19 12:00:00', '82');
INSERT INTO `shaishai` VALUES ('3', '3', 'Good', '2018-12-20 08:30:00', '66');

-- ----------------------------
-- Table structure for `shaishai_comment`
-- ----------------------------
DROP TABLE IF EXISTS `shaishai_comment`;
CREATE TABLE `shaishai_comment` (
  `s_id` int(11) NOT NULL DEFAULT '0',
  `u_id_comment` int(11) NOT NULL DEFAULT '0',
  `c_comment_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `c_comment_content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`s_id`,`u_id_comment`,`c_comment_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shaishai_comment
-- ----------------------------
INSERT INTO `shaishai_comment` VALUES ('1', '1', '2018-12-27 05:16:52', '成文秀');
INSERT INTO `shaishai_comment` VALUES ('1', '1', '2018-12-29 13:55:51', '是在规定时间送到的吗？');
INSERT INTO `shaishai_comment` VALUES ('2', '1', '2018-12-30 21:43:11', '估计');
INSERT INTO `shaishai_comment` VALUES ('2', '2', '2018-12-29 14:56:28', '使用体验怎么样？');
INSERT INTO `shaishai_comment` VALUES ('3', '3', '2018-12-30 13:57:08', '是按要求做的吗？');

-- ----------------------------
-- Table structure for `sign_in`
-- ----------------------------
DROP TABLE IF EXISTS `sign_in`;
CREATE TABLE `sign_in` (
  `u_id` int(11) NOT NULL,
  `sign_day_count` int(11) DEFAULT NULL,
  `if_sign_in` bit(1) DEFAULT NULL,
  `finish_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_in
-- ----------------------------
INSERT INTO `sign_in` VALUES ('1', '3', '', '2');
INSERT INTO `sign_in` VALUES ('2', '4', '', '3');
INSERT INTO `sign_in` VALUES ('3', '6', '', '4');

-- ----------------------------
-- Table structure for `tag`
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_text` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES ('1', '代取快递');
INSERT INTO `tag` VALUES ('2', '代买饭');
INSERT INTO `tag` VALUES ('3', '代接水');
INSERT INTO `tag` VALUES ('4', '约逛街');
INSERT INTO `tag` VALUES ('5', '约运动');
INSERT INTO `tag` VALUES ('6', '约学习');
INSERT INTO `tag` VALUES ('7', '其他');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id_send` int(11) NOT NULL,
  `u_time` datetime DEFAULT NULL,
  `tc_id` int(11) DEFAULT NULL,
  `t_desc` varchar(1000) DEFAULT NULL,
  `t_coin_count` int(11) DEFAULT NULL,
  `t_state` varchar(10) DEFAULT NULL,
  `u_id_accept` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `t_endtime` datetime DEFAULT NULL,
  `t_accept_time` datetime DEFAULT NULL,
  `t_finish_time` datetime DEFAULT NULL,
  `t_imgurl` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '1', '2018-12-13 10:14:04', '1', '有没有小伙伴去一食堂取快递呀', '20', '进行中', '2', '1', '2018-12-20 10:15:14', '2018-12-27 13:10:17', '2018-12-27 13:10:48', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('2', '1', '2018-12-28 15:25:10', '2', '有没有小伙伴可以帮忙带饭', '10', '进行中', '2', '2', '2018-12-28 16:26:04', '2018-12-28 16:00:19', '2018-12-27 13:10:51', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('3', '1', '2018-12-29 15:26:55', '1', '有人需要陪练网球吗', '15', '已完成', '3', '1', '2019-01-02 08:38:08', '2019-01-02 08:37:59', '2018-12-29 17:26:22', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('4', '1', '2018-12-07 21:52:05', '2', '哈哈哈', '5', '待接收', '2', '2', '2018-12-22 21:52:59', '2018-12-27 13:10:14', '2018-12-27 13:10:54', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('5', '1', '2018-12-17 00:00:00', '1', '多大的方法', '15', '待接收', '2', '2', '2019-01-01 00:00:00', '2018-12-19 00:00:00', '2018-12-19 00:00:00', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('6', '1', '2018-12-17 00:00:00', '1', '多大的方法', '15', '已完成', '2', '2', '2019-01-01 00:00:00', '2018-12-19 00:00:00', '2018-12-19 00:00:00', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('7', '1', '2018-12-17 00:00:00', '1', '电饭锅和', '10', '已完成', '2', '2', '2019-01-01 00:00:00', '2018-12-19 00:00:00', '2018-12-19 00:00:00', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('8', '2', '2018-12-17 00:00:00', '1', '富贵花好', '15', '已完成', '1', '2', '2019-01-01 00:00:00', '2018-12-19 00:00:00', '2018-12-19 00:00:00', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('9', '2', '2018-12-21 00:00:00', '1', '哈哈哈哈', '1', '已完成', '1', '3', '2018-07-08 00:00:00', '2018-12-27 13:10:20', '2018-12-27 13:10:57', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('27', '2', '2018-12-22 11:32:51', '2', '我爱李易峰', '10', '已完成', '1', '3', '2018-12-25 12:12:00', '2018-12-27 13:10:23', '2018-12-27 13:11:00', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('28', '2', '2018-12-22 11:32:52', '2', '我爱李易峰', '10', '已完成', '1', '5', '2018-12-25 12:12:00', '2018-12-27 13:10:25', '2018-12-27 13:11:03', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('29', '2', '2018-12-22 11:48:39', '1', '程文秀', '10', '已完成', '1', '4', '2018-12-24 11:10:00', '2018-12-27 13:10:31', '2018-12-27 13:11:06', '/img/headimg.jpg');
INSERT INTO `task` VALUES ('130', '3', '2019-01-02 08:57:29', '1', '下午去万达', '1', '待接收', '0', '4', '2019-01-03 12:00:00', '2019-01-02 08:59:50', '2019-01-20 08:59:53', 'aaaaa.jpg');

-- ----------------------------
-- Table structure for `task_category`
-- ----------------------------
DROP TABLE IF EXISTS `task_category`;
CREATE TABLE `task_category` (
  `tc_id` int(11) NOT NULL AUTO_INCREMENT,
  `tc_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of task_category
-- ----------------------------
INSERT INTO `task_category` VALUES ('1', '买时间');
INSERT INTO `task_category` VALUES ('2', '卖时间');

-- ----------------------------
-- Table structure for `task_image`
-- ----------------------------
DROP TABLE IF EXISTS `task_image`;
CREATE TABLE `task_image` (
  `ti_id` int(11) NOT NULL AUTO_INCREMENT,
  `it_image` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`ti_id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_image
-- ----------------------------
INSERT INTO `task_image` VALUES ('8', '/GetImageServlet?id=2018-11-26_20-17-54-579-1545800566868.BMP');
INSERT INTO `task_image` VALUES ('11', 'GetImageServlet?id=u=2567785361,1919163931&fm=26&gp=0-1545801880312.jpg');
INSERT INTO `task_image` VALUES ('12', 'GetImageServlet?id=u=2567785361,1919163931&fm=26&gp=0-1545801914283.jpg');
INSERT INTO `task_image` VALUES ('13', 'GetImageServlet?id=u=2567785361,1919163931&fm=26&gp=0-1545802133003.jpg');
INSERT INTO `task_image` VALUES ('14', 'GetImageServlet?id=u-1545802228794.jpg');
INSERT INTO `task_image` VALUES ('15', 'GetImageServlet?id=u-1545802311298.jpg');
INSERT INTO `task_image` VALUES ('16', 'GetImageServlet?id=u-1545802389228.jpg');
INSERT INTO `task_image` VALUES ('17', 'GetImageServlet?id=u-1545802694312.jpg');
INSERT INTO `task_image` VALUES ('18', 'GetImageServlet?id=u-1545802696060.jpg');
INSERT INTO `task_image` VALUES ('19', 'GetImageServlet?id=u-1545803114237.jpg');
INSERT INTO `task_image` VALUES ('20', 'GetImageServlet?id=u-1545803124597.jpg');
INSERT INTO `task_image` VALUES ('56', 'GetImageServlet?id=u-1546010115783.jpg');
INSERT INTO `task_image` VALUES ('88', 'GetImageServlet?id=timebank-1546066186373.png');
INSERT INTO `task_image` VALUES ('92', 'GetImageServlet?id=timebank-1546074152980.png');
INSERT INTO `task_image` VALUES ('93', 'GetImageServlet?id=timebank-1546075565297.png');
INSERT INTO `task_image` VALUES ('94', 'GetImageServlet?id=u-1546076949992.jpg');
INSERT INTO `task_image` VALUES ('95', 'GetImageServlet?id=GetImageServlet (2)-1546077000092.png');
INSERT INTO `task_image` VALUES ('96', 'GetImageServlet?id=u-1546077174650.jpg');
INSERT INTO `task_image` VALUES ('97', 'GetImageServlet?id=GetImageServlet (2)-1546077033583.png');
INSERT INTO `task_image` VALUES ('98', 'GetImageServlet?id=GetImageServlet (4)-1546077210811.png');
INSERT INTO `task_image` VALUES ('99', 'GetImageServlet?id=GetImageServlet (2)-1546077062442.png');
INSERT INTO `task_image` VALUES ('100', 'GetImageServlet?id=GetImageServlet (4)-1546077543205.png');
INSERT INTO `task_image` VALUES ('101', 'GetImageServlet?id=timebank-1546077600820.png');
INSERT INTO `task_image` VALUES ('102', 'GetImageServlet?id=timebank-1546077787307.png');
INSERT INTO `task_image` VALUES ('103', 'GetImageServlet?id=timebank-1546077796311.png');
INSERT INTO `task_image` VALUES ('104', 'GetImageServlet?id=u-1546078038867.jpg');
INSERT INTO `task_image` VALUES ('115', 'GetImageServlet?id=timebank-1546145996652.png');
INSERT INTO `task_image` VALUES ('116', 'GetImageServlet?id=timebank-1546146112480.png');
INSERT INTO `task_image` VALUES ('117', 'GetImageServlet?id=timebank-1546146762123.png');
INSERT INTO `task_image` VALUES ('118', 'GetImageServlet?id=timebank-1546146834509.png');
INSERT INTO `task_image` VALUES ('119', 'GetImageServlet?id=timebank-1546146914419.png');
INSERT INTO `task_image` VALUES ('120', 'GetImageServlet?id=timebank-1546147215634.png');
INSERT INTO `task_image` VALUES ('121', 'GetImageServlet?id=timebank-1546147317137.png');
INSERT INTO `task_image` VALUES ('122', 'GetImageServlet?id=timebank-1546147355511.png');
INSERT INTO `task_image` VALUES ('123', 'GetImageServlet?id=timebank-1546147381559.png');
INSERT INTO `task_image` VALUES ('124', 'GetImageServlet?id=timebank-1546161446618.png');
INSERT INTO `task_image` VALUES ('125', 'GetImageServlet?id=timebank-1546161836301.png');
INSERT INTO `task_image` VALUES ('126', 'GetImageServlet?id=timebank-1546162959083.png');
INSERT INTO `task_image` VALUES ('127', 'GetImageServlet?id=timebank-1546165023635.png');
INSERT INTO `task_image` VALUES ('128', 'GetImageServlet?id=timebank-1546174126661.png');
INSERT INTO `task_image` VALUES ('129', 'GetImageServlet?id=timebank-1546175489485.png');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(20) DEFAULT NULL,
  `u_phone` varchar(11) DEFAULT NULL,
  `u_sex` bit(1) DEFAULT NULL,
  `u_area` varchar(60) DEFAULT NULL,
  `u_nickname` varchar(20) DEFAULT NULL,
  `u_image` mediumtext,
  `u_password` varchar(20) DEFAULT NULL,
  `u_id_card` char(18) DEFAULT NULL,
  `u_coin` int(11) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '程文秀', '18032762013', '', '河北师范大学', '满满', '/img/headimg.jpg', '12', '123123', '300');
INSERT INTO `users` VALUES ('2', '刘曼', '18033873961', '', '河北省石家庄市裕华区裕翔街道', '遇见', '/img/headimg.jpg', '456', '234567', '100');
INSERT INTO `users` VALUES ('3', '张帅', '15226515859', '', '河北省石家庄市河北师范大学', '帅帅', '/img/headimg.jpg', '123', '345678', '200');
