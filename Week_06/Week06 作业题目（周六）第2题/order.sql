CREATE TABLE `order_detail` (
  `order_detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单表ID',
  `product_sku_id` bigint(20) unsigned NOT NULL COMMENT '订单商品ID',
  `product_name` varchar(50) NOT NULL COMMENT '商品名称',
  `product_count` int(11) NOT NULL DEFAULT '1' COMMENT '购买商品数量',
  `product_price` bigint(20) NOT NULL COMMENT '购买商品单价',
  `amount` bigint(20) NOT NULL COMMENT '金额',
  `district_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '优惠分摊金额',
  `created_tiem` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单详情表';

CREATE TABLE `order_master` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_sn` bigint(20) unsigned NOT NULL COMMENT '订单编号 yyyymmddnnnnnnnn',
  `user_id` int(10) unsigned NOT NULL COMMENT '下单人ID',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单金额',
  `district_amount` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `payment_amount` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '支付金额',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `last_modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单主表';
