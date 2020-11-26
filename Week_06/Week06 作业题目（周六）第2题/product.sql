CREATE TABLE `product_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) DEFAULT NULL COMMENT '类别名称',
  `category_code` varchar(45) DEFAULT NULL COMMENT '类别编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品类别';

CREATE TABLE `product_property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `property_name` varchar(255) NOT NULL COMMENT '属性名称',
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='属性表';

CREATE TABLE `product_property_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` varchar(45) DEFAULT NULL,
  `property_id` bigint(20) DEFAULT NULL COMMENT '关联表property',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='属性值';

CREATE TABLE `product_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_name` varchar(45) DEFAULT NULL COMMENT '产品名称',
  `price` bigint(20) DEFAULT NULL COMMENT '价格，默认是2位小数，存储时需乘以100存储，取数时再除以100\n',
  `description` varchar(1000) DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Stock Keeping Unit(库存单元)';

CREATE TABLE `product_sku_property_value_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sku_id` bigint(20) DEFAULT NULL COMMENT '关联表sku',
  `property_value_id` bigint(20) DEFAULT NULL COMMENT '关联表property_value',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='sku属性选项关系表';

CREATE TABLE `product_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `spu_num` varchar(45) DEFAULT NULL COMMENT 'sp编码',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类id',
  `brief_introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `detail_introduction` text COMMENT '详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Standard Product Unit (标准化产品单元)';
