CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL COMMENT '姓名',
  `mobile_phone` varchar(45) DEFAULT NULL COMMENT '手机号',
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `login_name` varchar(20) NOT NULL COMMENT '用户登录名',
  `identity_card_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '证件类型 1身份证，2军官证，3护照',
  `identity_card_num` varchar(30) NOT NULL COMMENT '证件号',
  `gender` char(1) DEFAULT NULL COMMENT '性别',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `birthday` datetime DEFAULT NULL COMMENT '会员生日',
  `last_modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';