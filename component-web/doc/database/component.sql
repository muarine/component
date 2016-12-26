# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 57848db91dc88.bj.cdb.myqcloud.com (MySQL 5.6.28-log)
# Database: component_prod
# Generation Time: 2016-12-26 08:23:35 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table u_auth_menu
# ------------------------------------------------------------

CREATE TABLE `u_auth_menu` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `fk_auth_id` int(9) NOT NULL COMMENT '角色主键ID',
  `fk_menu_id` int(9) NOT NULL COMMENT '用户主键ID',
  `create_time` datetime NOT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色-用户';



# Dump of table u_authorizer
# ------------------------------------------------------------

CREATE TABLE `u_authorizer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `appid` char(50) NOT NULL COMMENT '授权方appid',
  `refresh_token` char(255) NOT NULL COMMENT '刷新令牌（在授权的公众号具备API权限时，才有此返回值），刷新令牌主要用于公众号第三方平台获取和刷新已授权用户的access_token，只会在授权时刻提供，请妥善保存。 一旦丢失，只能让用户重新授权，才能再次拿到新的刷新令牌',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '授权方昵称',
  `head_img` varchar(500) DEFAULT NULL COMMENT '授权方头像',
  `service_type_info` tinyint(1) DEFAULT NULL COMMENT '授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号',
  `verify_type_info` tinyint(1) DEFAULT NULL COMMENT '授权方认证类型，-1代表未认证，0代表微信认证，1代表新浪微博认证，2代表腾讯微博认证，3代表已资质认证通过但还未通过名称认证，4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证，5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证',
  `user_name` char(50) DEFAULT NULL COMMENT '授权方公众号的原始ID',
  `alias` varchar(50) DEFAULT NULL COMMENT '授权方公众号所设置的微信号，可能为空',
  `qrcode_url` varchar(500) DEFAULT NULL COMMENT '二维码图片的URL',
  `func_info` char(50) NOT NULL COMMENT '公众号授权给开发者的权限集列表（请注意，当出现用户已经将消息与菜单权限集授权给了某个第三方，再授权给另一个第三方时，由于该权限集是互斥的，后一个第三方的授权将去除此权限集，开发者可以在返回的func_info信息中验证这一点，避免信息遗漏），1到8分别代表：\r\n            消息与菜单权限集\r\n            用户管理权限集\r\n            帐号管理权限集\r\n            网页授权权限集\r\n            微信小店权限集\r\n            多客服权限集',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '授权状态：1:已授权 2：取消授权',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_appid` (`appid`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公众号授权表';



# Dump of table u_func_info
# ------------------------------------------------------------

CREATE TABLE `u_func_info` (
  `id` int(3) NOT NULL AUTO_INCREMENT COMMENT '主键ID,同时也代表微信权限集编号',
  `name` varchar(20) NOT NULL COMMENT '权限集名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;



# Dump of table u_menu
# ------------------------------------------------------------

CREATE TABLE `u_menu` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `parent_id` int(9) NOT NULL DEFAULT '0' COMMENT '父类菜单，0：一级菜单 ',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `sort` int(2) NOT NULL COMMENT '排序',
  `href` varchar(255) NOT NULL COMMENT '菜单跳转链接',
  `is_show` tinyint(1) NOT NULL COMMENT '是否在菜单中显示:0. 显示 1.不显示',
  `creator` varchar(50) NOT NULL DEFAULT '' COMMENT '创建者',
  `comment` varchar(100) NOT NULL DEFAULT '' COMMENT '备注信息',
  `del_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '删除标记 1:正常 2:已删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='菜单';



# Dump of table wx_card
# ------------------------------------------------------------

CREATE TABLE `wx_card` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `fk_promo_id` int(9) NOT NULL COMMENT '营销平台活动id',
  `fk_prize_id` int(9) NOT NULL COMMENT '营销平台奖品id',
  `fk_auth_id` int(9) NOT NULL DEFAULT '0' COMMENT '外键关联授权方id',
  `card_id` char(50) NOT NULL COMMENT '卡券ID',
  `card_type` char(24) NOT NULL COMMENT '券类型',
  `base_info` text NOT NULL COMMENT '卡券基础信息，json',
  `advanced_info` text COMMENT '卡券高级信息，高级字段为商户额外展示信息字段，非必填',
  `ext_info` text NOT NULL COMMENT '补充json，根据券类型不同，字段个数和内容也不一样',
  `stock_value` int(7) NOT NULL DEFAULT '0' COMMENT '库存量',
  `state` tinyint(1) NOT NULL COMMENT '1.待审核 2. 审核失败 3. 审核通过 4. 删除 ',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信卡券';



# Dump of table wx_card_code
# ------------------------------------------------------------

CREATE TABLE `wx_card_code` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `fk_card_id` int(9) NOT NULL COMMENT '外键关联卡券id',
  `code` char(20) NOT NULL COMMENT '自定义code',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1.有效 2.已核销 3. 已失效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_code` (`code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信自定义code';



# Dump of table wx_material
# ------------------------------------------------------------

CREATE TABLE `wx_material` (
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `fk_auth_id` int(9) NOT NULL COMMENT '外键授权方ID',
  `type` char(10) NOT NULL COMMENT '素材类型（图片，视频，语音',
  `media_id` char(50) NOT NULL COMMENT '素材ID，该字段为微信生成',
  `url` varchar(255) NOT NULL COMMENT '素材链接',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信素材';



# Dump of table wx_msg_news
# ------------------------------------------------------------

CREATE TABLE `wx_msg_news` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `fk_auth_id` int(9) NOT NULL COMMENT '外键授权方id',
  `sort` int(3) NOT NULL DEFAULT '999' COMMENT '排序字段，数值越小排名越靠前',
  `description` varchar(100) NOT NULL COMMENT '简介',
  `pic_url` varchar(255) NOT NULL COMMENT '图片地址',
  `url` varchar(255) NOT NULL COMMENT '跳转地址',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信图文消息';



# Dump of table wx_msg_text
# ------------------------------------------------------------

CREATE TABLE `wx_msg_text` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `fk_auth_id` int(9) NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '消息事件类型 1.文本 2.图片 3.音频 4.视频',
  `content` text COMMENT '文本回复内容',
  `media_id` char(50) DEFAULT NULL COMMENT '资源ID，素材上传后返回media_id',
  `title` varchar(25) DEFAULT NULL COMMENT '该字段只支持Video,视频标题',
  `description` varchar(100) DEFAULT NULL COMMENT '该字段只支持Video,视频简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信消息回复';



# Dump of table wx_poi
# ------------------------------------------------------------

CREATE TABLE `wx_poi` (
  `id` int(7) NOT NULL AUTO_INCREMENT,
  `fk_auth_id` int(9) NOT NULL COMMENT '外键关联授权方id',
  `sid` bigint(9) NOT NULL COMMENT '商户自己的id，用于后续审核通过收到poi_id 的通知时，做对应关系。请商户自己保证唯一识别性',
  `poi_id` char(32) DEFAULT NULL COMMENT 'POI ID',
  `business_name` varchar(50) NOT NULL COMMENT '门店名称（仅为商户名，如：国美、麦当劳，不应包含地区、地址、分店名等信息，错误示例：北京国美）',
  `branch_name` varchar(50) DEFAULT NULL COMMENT '分店名称（不应包含地区信息，不应与门店名有重复，错误示例：北京王府井店）',
  `province` varchar(20) NOT NULL COMMENT '门店所在的省份（直辖市填城市名,如：北京市）',
  `city` varchar(20) NOT NULL COMMENT '门店所在的城市',
  `district` varchar(20) NOT NULL COMMENT '门店所在地区',
  `address` varchar(255) NOT NULL COMMENT '门店所在的详细街道地址（不要填写省市信息）',
  `telephone` char(12) NOT NULL COMMENT '门店的电话（纯数字，区号、分机号均由“-”隔开）',
  `categories` varchar(255) NOT NULL COMMENT '门店的类型（不同级分类用“,”隔开，如：美食，川菜，火锅。详细分类参见附件：微信门店类目表）',
  `offset_type` int(1) NOT NULL DEFAULT '1' COMMENT '坐标类型，1 为火星坐标（目前只能选1）',
  `longitude` double(9,5) NOT NULL COMMENT '门店所在地理位置的经度',
  `latitude` double(9,5) NOT NULL COMMENT '门店所在地理位置的纬度',
  `photo_list` text COMMENT '图片列表，url 形式，可以有多张图片，尺寸为\n640*340px。必须为上一接口生成的url。图片内容不允许与门店不相关，不允许为二维码、员工合照（或模特肖像）、营业执照、无门店正门的街景、地图截图、公交地铁站牌、菜单截图等',
  `recommend` varchar(255) DEFAULT NULL COMMENT '推荐品，餐厅可为推荐菜；酒店为推荐套房；景点为推荐游玩景点等，针对自己行业的推荐内容',
  `special` varchar(255) DEFAULT NULL COMMENT '特色服务，如免费wifi，免费停车，送货上门等商户能提供的特色功能或服务',
  `introduction` varchar(255) DEFAULT NULL COMMENT '商户简介，主要介绍商户信息等',
  `open_time` varchar(50) DEFAULT NULL COMMENT '营业时间，24 小时制表示，用“-”连接，如 8:00-20:00',
  `avg_price` int(5) DEFAULT NULL COMMENT '人均价格，大于0 的整数',
  `available_state` tinyint(1) DEFAULT '2' COMMENT '门店是否可用状态。1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。当该字段为1、2、4 状态时，poi_id 为空',
  `update_status` tinyint(1) DEFAULT '0' COMMENT '扩展字段是否正在更新中。1 表示扩展字段正在更新中，尚未生效，不允许再次更新； 0 表示扩展字段没有在更新中或更新已生效，可以再次更新',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_sid` (`sid`) USING HASH,
  UNIQUE KEY `index_poiid` (`poi_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信门店表';



# Dump of table wx_template_msg
# ------------------------------------------------------------

CREATE TABLE `wx_template_msg` (
  `id` int(5) NOT NULL,
  `fk_auth_id` int(9) NOT NULL,
  `code` char(25) NOT NULL,
  `template_id` char(50) NOT NULL DEFAULT '1',
  `title` varchar(25) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='微信消息模板推送';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
