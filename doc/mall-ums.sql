/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

/*
 * Copyright (c) 2023 - 2024 yingtingxu(徐应庭). All rights reserved.
 */

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES ('1', 'admin', '$2a$10$TdYKoWGmS3pHl.j2dPfkHuwZGvgFaIyK02TCtCbpxvvtjPHoaQ2pG', 'http://mio.china-tt.cn/mall/atavar.png', '1350312428@qq.com', '系统管理员', '系统管理员', now(), now(), '1');

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8 COMMENT='后台用户登录日志表';

-- ----------------------------
-- Table structure for ums_admin_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_permission_relation`;
CREATE TABLE `ums_admin_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation` VALUES ('1', '1', '5');
INSERT INTO `ums_admin_role_relation` VALUES ('2', '1', '1');
INSERT INTO `ums_admin_role_relation` VALUES ('3', '1', '2');

-- ----------------------------
-- Table structure for ums_growth_change_history
-- ----------------------------
DROP TABLE IF EXISTS `ums_growth_change_history`;
CREATE TABLE `ums_growth_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人员',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='成长值变化历史记录表';

-- ----------------------------
-- Table structure for ums_integration_change_history
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_change_history`;
CREATE TABLE `ums_integration_change_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `change_type` int(1) DEFAULT NULL COMMENT '改变类型：0->增加；1->减少',
  `change_count` int(11) DEFAULT NULL COMMENT '积分改变数量',
  `operate_man` varchar(100) DEFAULT NULL COMMENT '操作人员',
  `operate_note` varchar(200) DEFAULT NULL COMMENT '操作备注',
  `source_type` int(1) DEFAULT NULL COMMENT '积分来源：0->购物；1->管理员修改',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分变化历史记录表';

-- ----------------------------
-- Table structure for ums_integration_consume_setting
-- ----------------------------
DROP TABLE IF EXISTS `ums_integration_consume_setting`;
CREATE TABLE `ums_integration_consume_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deduction_per_amount` int(11) DEFAULT NULL COMMENT '每一元需要抵扣的积分数量',
  `max_percent_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高抵用百分比',
  `use_unit` int(11) DEFAULT NULL COMMENT '每次使用积分最小单位100',
  `coupon_status` int(1) DEFAULT NULL COMMENT '是否可以和优惠券同用；0->不可以；1->可以',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='积分消费设置';

-- ----------------------------
-- Table structure for ums_member
-- ----------------------------
DROP TABLE IF EXISTS `ums_member`;
CREATE TABLE `ums_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `level_id` bigint(20) DEFAULT NULL,
  `openid` varchar(64) DEFAULT NULL COMMENT 'openid',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `gender` int(1) DEFAULT 0 COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `province` varchar(64) DEFAULT NULL COMMENT '省份',
  `country` varchar(64) DEFAULT NULL COMMENT '国家',
  `language` varchar(64) DEFAULT NULL COMMENT '语言',
  `job` varchar(100) DEFAULT NULL COMMENT '职业',
  `bio` varchar(200) DEFAULT NULL COMMENT '个性签名',
  `status` int(1) DEFAULT 1 COMMENT '帐号启用状态:0->禁用；1->启用',
  `source_type` int(1) DEFAULT NULL COMMENT '用户来源',
  `integration` int(11) DEFAULT 0 COMMENT '积分',
  `growth` int(11) DEFAULT 0 COMMENT '成长值',
  `lucky_count` int(11) DEFAULT 0 COMMENT '剩余抽奖次数',
  `history_integration` int(11) DEFAULT 0 COMMENT '历史积分数量',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_openid` (`openid`),
  UNIQUE KEY `idx_username` (`username`),
  UNIQUE KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='会员表';

-- ----------------------------
-- Table structure for ums_member_level
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_level`;
CREATE TABLE `ums_member_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth_point` int(11) DEFAULT NULL,
  `default_status` int(1) DEFAULT 0 COMMENT '是否为默认等级：0->不是；1->是',
  `free_freight_point` decimal(10,2) DEFAULT NULL COMMENT '免运费标准',
  `comment_growth_point` int(11) DEFAULT NULL COMMENT '每次评价获取的成长值',
  `privilege_free_freight` int(1) DEFAULT NULL COMMENT '是否有免邮特权',
  `privilege_sign_in` int(1) DEFAULT NULL COMMENT '是否有签到特权',
  `privilege_comment` int(1) DEFAULT NULL COMMENT '是否有评论获奖励特权',
  `privilege_promotion` int(1) DEFAULT NULL COMMENT '是否有专享活动特权',
  `privilege_member_price` int(1) DEFAULT NULL COMMENT '是否有会员价格特权',
  `privilege_birthday` int(1) DEFAULT NULL COMMENT '是否有生日特权',
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员等级表';

-- ----------------------------
-- Records of ums_member_level
-- ----------------------------
INSERT INTO `ums_member_level` VALUES ('1', '黄金会员', '1000', '0', '199.00', '5', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `ums_member_level` VALUES ('2', '白金会员', '5000', '0', '99.00', '10', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `ums_member_level` VALUES ('3', '钻石会员', '15000', '0', '69.00', '15', '1', '1', '1', '1', '1', '1', null);
INSERT INTO `ums_member_level` VALUES ('4', '普通会员', '1', '1', '199.00', '20', '1', '1', '1', '1', '0', '0', null);

-- ----------------------------
-- Table structure for ums_member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_login_log`;
CREATE TABLE `ums_member_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `login_type` int(1) DEFAULT NULL COMMENT '登录类型：0->PC；1->android;2->ios;3->小程序',
  `province` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员登录记录';

-- ----------------------------
-- Table structure for ums_member_member_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_member_tag_relation`;
CREATE TABLE `ums_member_member_tag_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `tag_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和标签关系表';

-- ----------------------------
-- Table structure for ums_member_product_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_product_category_relation`;
CREATE TABLE `ums_member_product_category_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `product_category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员与产品分类关系表（用户喜欢的分类）';

-- ----------------------------
-- Table structure for ums_member_receive_address
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_receive_address`;
CREATE TABLE `ums_member_receive_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '收货人名称',
  `phone_number` varchar(64) DEFAULT NULL,
  `default_status` int(1) DEFAULT NULL COMMENT '是否为默认',
  `post_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `province` varchar(100) DEFAULT NULL COMMENT '省份/直辖市',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `region` varchar(100) DEFAULT NULL COMMENT '区',
  `detail_address` varchar(128) DEFAULT NULL COMMENT '详细地址(街道)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='会员收货地址表';

-- ----------------------------
-- Table structure for ums_member_rule_setting
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_rule_setting`;
CREATE TABLE `ums_member_rule_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `continue_sign_day` int(11) DEFAULT NULL COMMENT '连续签到天数',
  `continue_sign_point` int(11) DEFAULT NULL COMMENT '连续签到赠送数量',
  `consume_per_point` decimal(10,2) DEFAULT NULL COMMENT '每消费多少元获取1个点',
  `low_order_amount` decimal(10,2) DEFAULT NULL COMMENT '最低获取点数的订单金额',
  `max_point_per_order` int(11) DEFAULT NULL COMMENT '每笔订单最高获取点数',
  `type` int(1) DEFAULT NULL COMMENT '类型：0->积分规则；1->成长值规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员积分成长规则表';

-- ----------------------------
-- Table structure for ums_member_statistics_info
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_statistics_info`;
CREATE TABLE `ums_member_statistics_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `consume_amount` decimal(10,2) DEFAULT NULL COMMENT '累计消费金额',
  `order_count` int(11) DEFAULT NULL COMMENT '订单数量',
  `coupon_count` int(11) DEFAULT NULL COMMENT '优惠券数量',
  `comment_count` int(11) DEFAULT NULL COMMENT '评价数',
  `return_order_count` int(11) DEFAULT NULL COMMENT '退货数量',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `attend_count` int(11) DEFAULT NULL COMMENT '关注数量',
  `fans_count` int(11) DEFAULT NULL COMMENT '粉丝数量',
  `collect_product_count` int(11) DEFAULT NULL,
  `collect_subject_count` int(11) DEFAULT NULL,
  `collect_topic_count` int(11) DEFAULT NULL,
  `collect_comment_count` int(11) DEFAULT NULL,
  `invite_friend_count` int(11) DEFAULT NULL,
  `recent_order_time` datetime DEFAULT NULL COMMENT '最后一次下订单时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员统计信息';

-- ----------------------------
-- Table structure for ums_member_tag
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_tag`;
CREATE TABLE `ums_member_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `finish_order_count` int(11) DEFAULT NULL COMMENT '自动打标签完成订单数量',
  `finish_order_amount` decimal(10,2) DEFAULT NULL COMMENT '自动打标签完成订单金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户标签表';

-- ----------------------------
-- Table structure for ums_member_task
-- ----------------------------
DROP TABLE IF EXISTS `ums_member_task`;
CREATE TABLE `ums_member_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `growth` int(11) DEFAULT NULL COMMENT '赠送成长值',
  `integration` int(11) DEFAULT NULL COMMENT '赠送积分',
  `type` int(1) DEFAULT NULL COMMENT '任务类型：0->新手任务；1->日常任务',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员任务表';

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `level` int(4) DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) DEFAULT NULL COMMENT '前端隐藏',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES ('1', '0', '商品', '0', '0', 'pms', 'product', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('2', '1', '商品列表', '1', '0', 'product', 'product-list', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('3', '1', '添加商品', '1', '0', 'addProduct', 'product-add', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('4', '1', '商品分类', '1', '0', 'productCate', 'product-cate', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('5', '1', '商品类型', '1', '0', 'productAttr', 'product-attr', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('6', '1', '品牌管理', '1', '0', 'brand', 'product-brand', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('7', '0', '订单', '0', '0', 'oms', 'order', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('8', '7', '订单列表', '1', '0', 'order', 'product-list', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('9', '7', '订单设置', '1', '0', 'orderSetting', 'order-setting', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('10', '7', '退货申请处理', '1', '0', 'returnApply', 'order-return', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('11', '7', '退货原因设置', '1', '0', 'returnReason', 'order-return-reason', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('12', '0', '营销', '0', '0', 'sms', 'sms', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('13', '12', '秒杀活动列表', '1', '0', 'flash', 'sms-flash', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('14', '12', '优惠券列表', '1', '0', 'coupon', 'sms-coupon', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('16', '12', '品牌推荐', '1', '0', 'homeBrand', 'product-brand', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('17', '12', '新品推荐', '1', '0', 'homeNew', 'sms-new', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('18', '12', '人气推荐', '1', '0', 'homeHot', 'sms-hot', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('19', '12', '专题推荐', '1', '0', 'homeSubject', 'sms-subject', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('20', '12', '广告列表', '1', '0', 'homeAdvertise', 'sms-ad', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('21', '0', '权限', '0', '0', 'ums', 'ums', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('22', '21', '用户列表', '1', '0', 'admin', 'ums-admin', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('23', '21', '角色列表', '1', '0', 'role', 'ums-role', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('24', '21', '菜单列表', '1', '0', 'menu', 'ums-menu', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_menu` VALUES ('25', '21', '资源列表', '1', '0', 'resource', 'ums-resource', '0', '2021-03-13 11:19:36');

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='后台资源表';

-- ----------------------------
-- Records of ums_resource
-- ----------------------------
INSERT INTO `ums_resource` VALUES ('1', '商品品牌管理', '/brand/**', null, '1', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('2', '商品属性分类管理', '/productAttribute/**', null, '1', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('3', '商品属性管理', '/productAttribute/**', null, '1', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('4', '商品分类管理', '/productCategory/**', null, '1', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('5', '商品管理', '/product/**', null, '1', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('6', '商品库存管理', '/sku/**', null, '1', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('8', '订单管理', '/order/**', '', '2', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('9', ' 订单退货申请管理', '/returnApply/**', '', '2', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('10', '退货原因管理', '/returnReason/**', '', '2', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('11', '订单设置管理', '/orderSetting/**', '', '2', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('12', '收货地址管理', '/companyAddress/**', '', '2', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('13', '优惠券管理', '/coupon/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('14', '优惠券领取记录管理', '/couponHistory/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('15', '限时购活动管理', '/flash/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('16', '限时购商品关系管理', '/flashProductRelation/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('17', '限时购场次管理', '/flashSession/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('18', '首页轮播广告管理', '/home/advertise/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('19', '首页品牌管理', '/home/brand/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('20', '首页新品管理', '/home/newProduct/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('21', '首页人气推荐管理', '/home/recommendProduct/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('22', '首页专题推荐管理', '/home/recommendSubject/**', '', '3', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('23', ' 商品优选管理', '/prefrenceArea/**', '', '5', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('24', '商品专题管理', '/subject/**', '', '5', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('25', '后台用户管理', '/admin/**', '', '4', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('26', '后台用户角色管理', '/role/**', '', '4', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('27', '后台菜单管理', '/menu/**', '', '4', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('28', '后台资源分类管理', '/resourceCategory/**', '', '4', '2021-03-13 11:19:36');
INSERT INTO `ums_resource` VALUES ('29', '后台资源管理', '/resource/**', '', '4', '2021-03-13 11:19:36');

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='资源分类表';

-- ----------------------------
-- Records of ums_resource_category
-- ----------------------------
INSERT INTO `ums_resource_category` VALUES ('1', '商品模块', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_resource_category` VALUES ('2', '订单模块', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_resource_category` VALUES ('3', '营销模块', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_resource_category` VALUES ('4', '权限模块', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_resource_category` VALUES ('5', '内容模块', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_resource_category` VALUES ('6', '其他模块', '0', '2021-03-13 11:19:36');

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES ('1', '商品管理员', '只能查看及操作商品', '0', '1', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_role` VALUES ('2', '订单管理员', '只能查看及操作订单', '0', '1', '0', '2021-03-13 11:19:36');
INSERT INTO `ums_role` VALUES ('5', '超级管理员', '拥有所有查看和操作功能', '0', '1', '0', '2021-03-13 11:19:36');

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
INSERT INTO `ums_role_menu_relation` VALUES ('1', '1', '1');
INSERT INTO `ums_role_menu_relation` VALUES ('2', '1', '2');
INSERT INTO `ums_role_menu_relation` VALUES ('3', '1', '3');
INSERT INTO `ums_role_menu_relation` VALUES ('4', '1', '4');
INSERT INTO `ums_role_menu_relation` VALUES ('5', '1', '5');
INSERT INTO `ums_role_menu_relation` VALUES ('6', '1', '6');
INSERT INTO `ums_role_menu_relation` VALUES ('7', '2', '7');
INSERT INTO `ums_role_menu_relation` VALUES ('8', '2', '8');
INSERT INTO `ums_role_menu_relation` VALUES ('9', '2', '9');
INSERT INTO `ums_role_menu_relation` VALUES ('10', '2', '10');
INSERT INTO `ums_role_menu_relation` VALUES ('11', '2', '11');
INSERT INTO `ums_role_menu_relation` VALUES ('12', '5', '1');
INSERT INTO `ums_role_menu_relation` VALUES ('13', '5', '2');
INSERT INTO `ums_role_menu_relation` VALUES ('14', '5', '3');
INSERT INTO `ums_role_menu_relation` VALUES ('15', '5', '4');
INSERT INTO `ums_role_menu_relation` VALUES ('16', '5', '5');
INSERT INTO `ums_role_menu_relation` VALUES ('17', '5', '6');
INSERT INTO `ums_role_menu_relation` VALUES ('18', '5', '7');
INSERT INTO `ums_role_menu_relation` VALUES ('19', '5', '8');
INSERT INTO `ums_role_menu_relation` VALUES ('20', '5', '9');
INSERT INTO `ums_role_menu_relation` VALUES ('21', '5', '10');
INSERT INTO `ums_role_menu_relation` VALUES ('22', '5', '11');
INSERT INTO `ums_role_menu_relation` VALUES ('23', '5', '12');
INSERT INTO `ums_role_menu_relation` VALUES ('24', '5', '13');
INSERT INTO `ums_role_menu_relation` VALUES ('25', '5', '14');
INSERT INTO `ums_role_menu_relation` VALUES ('26', '5', '16');
INSERT INTO `ums_role_menu_relation` VALUES ('27', '5', '17');
INSERT INTO `ums_role_menu_relation` VALUES ('28', '5', '18');
INSERT INTO `ums_role_menu_relation` VALUES ('29', '5', '19');
INSERT INTO `ums_role_menu_relation` VALUES ('30', '5', '20');
INSERT INTO `ums_role_menu_relation` VALUES ('31', '5', '21');
INSERT INTO `ums_role_menu_relation` VALUES ('32', '5', '22');
INSERT INTO `ums_role_menu_relation` VALUES ('33', '5', '23');
INSERT INTO `ums_role_menu_relation` VALUES ('34', '5', '24');
INSERT INTO `ums_role_menu_relation` VALUES ('35', '5', '25');

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8 COMMENT='后台角色资源关系表';

-- ----------------------------
-- Records of ums_role_resource_relation
-- ----------------------------
INSERT INTO `ums_role_resource_relation` VALUES ('1', '2', '8');
INSERT INTO `ums_role_resource_relation` VALUES ('2', '2', '9');
INSERT INTO `ums_role_resource_relation` VALUES ('3', '2', '10');
INSERT INTO `ums_role_resource_relation` VALUES ('4', '2', '11');
INSERT INTO `ums_role_resource_relation` VALUES ('5', '2', '12');
INSERT INTO `ums_role_resource_relation` VALUES ('6', '5', '1');
INSERT INTO `ums_role_resource_relation` VALUES ('7', '5', '2');
INSERT INTO `ums_role_resource_relation` VALUES ('8', '5', '3');
INSERT INTO `ums_role_resource_relation` VALUES ('9', '5', '4');
INSERT INTO `ums_role_resource_relation` VALUES ('10', '5', '5');
INSERT INTO `ums_role_resource_relation` VALUES ('11', '5', '6');
INSERT INTO `ums_role_resource_relation` VALUES ('12', '5', '8');
INSERT INTO `ums_role_resource_relation` VALUES ('13', '5', '9');
INSERT INTO `ums_role_resource_relation` VALUES ('14', '5', '10');
INSERT INTO `ums_role_resource_relation` VALUES ('15', '5', '11');
INSERT INTO `ums_role_resource_relation` VALUES ('16', '5', '12');
INSERT INTO `ums_role_resource_relation` VALUES ('17', '5', '13');
INSERT INTO `ums_role_resource_relation` VALUES ('18', '5', '14');
INSERT INTO `ums_role_resource_relation` VALUES ('19', '5', '15');
INSERT INTO `ums_role_resource_relation` VALUES ('20', '5', '16');
INSERT INTO `ums_role_resource_relation` VALUES ('21', '5', '17');
INSERT INTO `ums_role_resource_relation` VALUES ('22', '5', '18');
INSERT INTO `ums_role_resource_relation` VALUES ('23', '5', '19');
INSERT INTO `ums_role_resource_relation` VALUES ('24', '5', '20');
INSERT INTO `ums_role_resource_relation` VALUES ('25', '5', '21');
INSERT INTO `ums_role_resource_relation` VALUES ('26', '5', '22');
INSERT INTO `ums_role_resource_relation` VALUES ('27', '5', '23');
INSERT INTO `ums_role_resource_relation` VALUES ('28', '5', '24');
INSERT INTO `ums_role_resource_relation` VALUES ('29', '5', '25');
INSERT INTO `ums_role_resource_relation` VALUES ('30', '5', '26');
INSERT INTO `ums_role_resource_relation` VALUES ('31', '5', '27');
INSERT INTO `ums_role_resource_relation` VALUES ('32', '5', '28');
INSERT INTO `ums_role_resource_relation` VALUES ('33', '5', '29');
INSERT INTO `ums_role_resource_relation` VALUES ('34', '1', '1');
INSERT INTO `ums_role_resource_relation` VALUES ('35', '1', '2');
INSERT INTO `ums_role_resource_relation` VALUES ('36', '1', '3');
INSERT INTO `ums_role_resource_relation` VALUES ('37', '1', '4');
INSERT INTO `ums_role_resource_relation` VALUES ('38', '1', '5');
INSERT INTO `ums_role_resource_relation` VALUES ('39', '1', '6');
INSERT INTO `ums_role_resource_relation` VALUES ('40', '1', '23');
INSERT INTO `ums_role_resource_relation` VALUES ('41', '1', '24');

DROP TABLE IF EXISTS `ums_permission`;
DROP TABLE IF EXISTS `ums_admin_permission_relation`;
DROP TABLE IF EXISTS `ums_role_permission_relation`;