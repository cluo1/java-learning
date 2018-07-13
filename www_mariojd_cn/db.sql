-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `comment` int(11) DEFAULT NULL,
  `content` text,
  `is_delete` int(11) DEFAULT NULL,
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `praise` int(11) DEFAULT NULL,
  `view` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `aid` tinyint(3) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(15) DEFAULT NULL COMMENT '昵称',
  `account` varchar(10) NOT NULL COMMENT '账户',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `role` varchar(10) NOT NULL COMMENT '角色',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `mid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` smallint(5) unsigned NOT NULL COMMENT '外键',
  `content` text NOT NULL COMMENT '互动(留言)内容',
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发表时间',
  PRIMARY KEY (`mid`),
  KEY `FKbkjuq9k8cl512e0kr44a91cxi` (`uid`),
  CONSTRAINT `FK4a721u1j8ujy46g69y364g53c` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=441 DEFAULT CHARSET=utf8 COMMENT='互动(留言)表';

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `nid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(30) NOT NULL COMMENT '标题',
  `content` varchar(500) NOT NULL COMMENT '内容',
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `aid` tinyint(3) unsigned NOT NULL,
  `visit` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '查看次数',
  PRIMARY KEY (`nid`),
  KEY `FKpm0dreuqibnrj0mq09u9aqbu5` (`aid`),
  CONSTRAINT `FKpm0dreuqibnrj0mq09u9aqbu5` FOREIGN KEY (`aid`) REFERENCES `tb_admin` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=114 DEFAULT CHARSET=utf8 COMMENT='公告表';

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `oid` char(20) NOT NULL COMMENT '订单编号',
  `uid` smallint(5) unsigned NOT NULL COMMENT '用户id',
  `sid` smallint(5) unsigned NOT NULL COMMENT '商品id',
  `sname` varchar(50) NOT NULL COMMENT '商品名称',
  `sprice` int(6) unsigned NOT NULL COMMENT '商品价格',
  `state` tinyint(4) NOT NULL DEFAULT '-1' COMMENT '订单状态：-1表示作废，0表示未付款， 1表示已付款，2表示已发货',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  PRIMARY KEY (`sid`,`uid`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Table structure for tb_read
-- ----------------------------
DROP TABLE IF EXISTS `tb_read`;
CREATE TABLE `tb_read` (
  `rid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(15) NOT NULL COMMENT '书名',
  `second_title` varchar(10) DEFAULT NULL COMMENT '二级标题(书名)',
  `author` varchar(30) NOT NULL COMMENT '作者',
  `url` varchar(180) NOT NULL COMMENT '图片地址',
  `review` varchar(1500) NOT NULL COMMENT '书评、读后感',
  `aid` tinyint(3) unsigned NOT NULL COMMENT '外键',
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`rid`),
  KEY `FKhjna60l44xyik1eai6tyk0n3m` (`aid`),
  CONSTRAINT `FKhjna60l44xyik1eai6tyk0n3m` FOREIGN KEY (`aid`) REFERENCES `tb_admin` (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='阅读记录表';

-- ----------------------------
-- Table structure for tb_seckill
-- ----------------------------
DROP TABLE IF EXISTS `tb_seckill`;
CREATE TABLE `tb_seckill` (
  `sid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `sname` varchar(50) NOT NULL COMMENT '商品名称',
  `snumber` int(10) unsigned NOT NULL COMMENT '商品数量',
  `sprice` int(6) unsigned NOT NULL COMMENT '商品价格',
  `scategory` varchar(10) DEFAULT NULL COMMENT '商品类别',
  `surl` varchar(150) DEFAULT NULL COMMENT '商品图片地址',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开抢时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`sid`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8 COMMENT='抢购商品表';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `uid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nickname` varchar(30) NOT NULL COMMENT '昵称',
  `telephone` char(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `salt` varchar(32) NOT NULL COMMENT '盐值',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `icon` varchar(150) DEFAULT NULL COMMENT '头像(地址)',
  `count` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '互动(留言)数量',
  `state` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态：默认0（未激活）、1（正常）、2（禁用）',
  `code` varchar(32) DEFAULT NULL COMMENT '激活码(16位UUID随机)、验证码（6位Random随机）',
  `openid` varchar(32) DEFAULT NULL COMMENT '微信用户标识',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Procedure structure for seckill_procedure
-- ----------------------------
DROP PROCEDURE IF EXISTS `seckill_procedure`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `seckill_procedure`(IN o_oid CHAR(20), IN o_uid SMALLINT,IN o_sid SMALLINT,IN o_sname VARCHAR(50),IN o_sprice INT,
   IN o_kill_time TIMESTAMP, OUT o_result INT)
BEGIN
    DECLARE insert_count INT DEFAULT 0;
    # 开启事务
    START TRANSACTION;
    #insert ignore会忽略数据库中已经存在的数据
    INSERT IGNORE INTO tb_order(oid, uid, sid, sname, sprice, state, create_time) VALUES(o_oid, o_uid, o_sid, o_sname, o_sprice, 0, o_kill_time);
    # row_count()：返回上一条修改类型sql(delete，insert，update)的影响行数
    # row_count()：等于0，表示未修改数据；大于0,表示修改数据的行数；小于0,表示sql错误或未执行该sql
    SELECT row_count()
    INTO insert_count;
    IF (insert_count = 0)
    #等于0，表示未修改数据,o_result=-1(重复抢购)
    THEN
      ROLLBACK;
      SET o_result = -1;
    ELSEIF (insert_count < 0)
      #小于0,表示sql错误或未执行该sql,o_result=-2(系统异常)
      THEN
        ROLLBACK;
        SET o_result = -2;
    ELSE
      UPDATE tb_seckill
      SET snumber = snumber - 1
      WHERE sid = o_sid
            AND start_time <= o_kill_time AND end_time >= o_kill_time AND snumber > 0;
      SELECT row_count()
      INTO insert_count;
      IF (insert_count = 0)
      #等于0，表示未修改数据,o_result=0(最可能是snumber小于0导致的活动结束)
      THEN
        ROLLBACK;
        SET o_result = 0;
      ELSEIF (insert_count < 0)
        #小于0,表示sql错误或未执行该sql,o_result=-2(系统异常)
        THEN
          ROLLBACK;
          SET o_result = -2;
      ELSE
        #大于0,表示修改数据的行数,o_result=1(执行成功)
        COMMIT;
        SET o_result = 1;
      END IF;
    END IF;
  END
;;
DELIMITER ;
