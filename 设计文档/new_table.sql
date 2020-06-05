CREATE TABLE `car_produceOrder_details` (
  `id` int(11) NOT NULL,
  `goods_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '物品名称',
  `tot_num` int(20) DEFAULT NULL COMMENT '物品数量',
  `produce` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '生产商',
  `tot_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `order_id` int(20) DEFAULT NULL COMMENT '订单外建',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;