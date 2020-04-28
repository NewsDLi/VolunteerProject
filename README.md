# VolunteerProject
义工管理系统
demo


建表SQL：

CREATE TABLE `article_message_board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `community_articles_id` bigint(20) NOT NULL,
  `message` mediumtext COLLATE utf8mb4_bin NOT NULL COMMENT '留言板内容',
  `version` timestamp NULL DEFAULT NULL COMMENT '留言时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户姓名',
  `sort` int(100) DEFAULT NULL COMMENT '排序字段',
  `pid` int(20) DEFAULT NULL COMMENT '评论Id（用于子留言）',
  `type` int(10) DEFAULT NULL COMMENT '来源类型（论坛）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;





CREATE TABLE `bind_role_power` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(16) DEFAULT NULL,
  `power_id` bigint(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;



CREATE TABLE `community_articles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `publication_time` timestamp NULL DEFAULT NULL COMMENT '发表时间',
  `subheading` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '副标题',
  `author` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '作者',
  `content` longtext COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  `type` int(11) NOT NULL COMMENT '类型',
  `lifecycle` int(11) NOT NULL COMMENT '生命周期',
  `sort` int(100) DEFAULT NULL COMMENT '排序字段',
  `image` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '保存图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;





CREATE TABLE `honer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gray` varchar(200) NOT NULL,
  `light` varchar(200) NOT NULL,
  `title` varchar(50) NOT NULL,
  `is_light` tinyint(1) DEFAULT '0' COMMENT '是否点亮，预留字段，不做逻辑处理',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `version` timestamp NULL DEFAULT NULL COMMENT '时间',
  `range` int(11) DEFAULT NULL COMMENT '范围',
  `is_click_send` tinyint(1) DEFAULT '0' COMMENT '是否点击发放勋章，否的情况下，range字段不能为空',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;





CREATE TABLE `message_board` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` bigint(16) NOT NULL,
  `content` varchar(255) NOT NULL,
  `lifecycle` int(1) NOT NULL,
  `version` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `message_board_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=196 DEFAULT CHARSET=utf8;




CREATE TABLE `user` (
  `id` varchar(50) NOT NULL COMMENT '员工编号',
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `type` int(11) NOT NULL COMMENT '0为root，1为普通员工',
  `tel` varchar(11) NOT NULL COMMENT '电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `user_info` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `name` varchar(80) NOT NULL COMMENT '用户姓名',
  `sex` int(1) NOT NULL,
  `id_card` varchar(255) NOT NULL,
  `worker` varchar(100) DEFAULT NULL,
  `login_phone` varchar(11) NOT NULL,
  `descption` varchar(255) DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `user_pic` varchar(255) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL,
  `update_by` bigint(16) DEFAULT NULL,
  `lifecycle` int(1) DEFAULT NULL,
  `role_id` bigint(16) DEFAULT NULL,
  `group_team` int(2) DEFAULT NULL COMMENT '组别',
  `is_group_leader` tinyint(1) DEFAULT NULL COMMENT '是否组长',
  `birthplace` varchar(100) DEFAULT NULL,
  `nation` varchar(10) DEFAULT NULL,
  `is_message_board` tinyint(1) DEFAULT NULL COMMENT '是否拥有查看留言板功能',
  `honer_id` varchar(100) DEFAULT NULL COMMENT '点亮的勋章墙id,格式：1,2,3',
  PRIMARY KEY (`id`),
  KEY `user_info_id` (`id`),
  KEY `user_info` (`login_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=2493 DEFAULT CHARSET=utf8;





CREATE TABLE `user_info_bind` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` bigint(16) NOT NULL,
  `wechat_id` bigint(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_info_bind_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8;




CREATE TABLE `user_info_tag` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` bigint(16) NOT NULL,
  `tag_name` varchar(18) NOT NULL,
  `tag_count` int(4) NOT NULL,
  `type` int(1) NOT NULL COMMENT '勋章墙、义工课程、义工岗位',
  PRIMARY KEY (`id`),
  KEY `user_info_tag_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12914 DEFAULT CHARSET=utf8;




CREATE TABLE `user_power` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `power_name` varchar(80) NOT NULL,
  `power_url` varchar(255) NOT NULL,
  `power_pic_url` varchar(255) NOT NULL,
  `version` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_power_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;




CREATE TABLE `user_role` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `role_name` varchar(80) NOT NULL,
  `role_type` int(3) NOT NULL,
  `version` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;




CREATE TABLE `wechat_info` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(255) NOT NULL,
  `nick_name` varchar(255) NOT NULL,
  `sex` int(1) DEFAULT NULL,
  `province` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `wechat_info_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=309 DEFAULT CHARSET=utf8;




CREATE TABLE `wheel_planting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pic` varchar(200) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `link_address` varchar(100) DEFAULT NULL,
  `version` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
