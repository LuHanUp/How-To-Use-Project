-- 服务1的库
CREATE DATABASE `hmily_bank1` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE hmily_bank1;
DROP TABLE IF EXISTS `account_info`; CREATE TABLE `account_info` (
                                                                     `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                                                     `account_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '户主姓名',
                                                                     `account_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '银行卡号',
                                                                     `account_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '帐户密码',
                                                                     `account_balance` double NULL DEFAULT NULL COMMENT '帐户余额', PRIMARY KEY (`id`) USING BTREE
                                     ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
INSERT INTO `account_info` VALUES (1, '张三', '1', '', 10000);

-- 服务2的库
CREATE DATABASE `hmily_bank2` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE hmily_bank2;
DROP TABLE IF EXISTS `account_info`; CREATE TABLE `account_info` (
                                                                     `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                                                     `account_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '户主姓名',
                                                                     `account_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '银行卡号',
                                                                     `account_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '帐户密码',
                                                                     `account_balance` double NULL DEFAULT NULL COMMENT '帐户余额', PRIMARY KEY (`id`) USING BTREE
                                     ) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;
INSERT INTO `account_info` VALUES (2, '李四', '2', NULL, 0);