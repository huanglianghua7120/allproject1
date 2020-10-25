-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 192.168.173.22    Database: jyuat
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `rule_dim`
--

DROP TABLE IF EXISTS `rule_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_dim` (
  `RULE_DIM_ID` int(10) NOT NULL AUTO_INCREMENT,
  `rule_business_line_code` varchar(100) DEFAULT NULL COMMENT '维度所属的业务线',
  `RULE_view_TYPE` varchar(30) DEFAULT NULL COMMENT '维度展示的类型 inputText inputArea inputDate listOfValue selectOneChoice selectManyBox....',
  `RULE_DIM_NAME` varchar(150) DEFAULT NULL,
  `RULE_DIM_DATA_TYPE` varchar(15) DEFAULT NULL,
  `RULE_DIM_default_VALUE` varchar(150) DEFAULT NULL COMMENT '维度默认的值',
  `RULE_DIM_CODE` varchar(150) DEFAULT NULL,
  `RULE_DIM_DESC` varchar(500) DEFAULT NULL,
  `rule_DIM_value_from` varchar(100) DEFAULT NULL COMMENT '维度的值来源于 webservice sql redis ....',
  `rule_dim_target_source` varchar(500) DEFAULT NULL COMMENT '目标源头 webservice地址 redis的key sql的语句',
  `EFFECT_DATE` datetime DEFAULT NULL,
  `EFFECT_END_DATE` datetime DEFAULT NULL,
  `rule_dim_reference_from` varchar(100) DEFAULT NULL COMMENT '参考值来自于webservice/lookup表',
  `rule_dim_reference_code` varchar(100) DEFAULT NULL COMMENT '参考值对应的编码，如果来自于lookup需指定code，如果来自webservice指定服务的code',
  PRIMARY KEY (`RULE_DIM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_dim`
--

LOCK TABLES `rule_dim` WRITE;
/*!40000 ALTER TABLE `rule_dim` DISABLE KEYS */;
INSERT INTO `rule_dim` VALUES (75,'BUSINESSLINE201707170007','selectManyBox','选择影片','String',NULL,'cinFilm','选择影片',NULL,NULL,'2017-07-03 00:00:00',NULL,'webservice','SERVICE_SEQ_00009'),(76,'BUSINESSLINE201707170007','selectManyBox','查询影厅','String','','cinScreen','查询影厅','webservice',NULL,'2017-07-04 00:00:00',NULL,'webservice','SERVICE_SEQ_00010'),(79,'BUSINESSLINE201707260001','listOfValue','生日','String','12','birthday','生日','sql','select DATE_FORMAT(birth_day,\'%m%d\') from member_info where member_id = #mmb_id#','2017-07-01 00:00:00','2017-08-02 00:00:00','webservice','qweqwe'),(87,'BUSINESSLINE201707170005','inputText','年龄','Date','100','age','年龄描述','redis','200','2017-07-01 00:00:00',NULL,'webservice','SERVICE_SEQ_00015'),(94,'BUSINESSLINE201707280004','inputText','客户类别','String',NULL,NULL,NULL,'sql','select customer_type_name from customer_type_info','2017-07-01 00:00:00',NULL,NULL,NULL),(95,'BUSINESSLINE201707280004','inputText','商品类别','String',NULL,NULL,NULL,'sql','select item_type_name from item_type_info','2017-07-01 00:00:00',NULL,NULL,NULL),(96,'BUSINESSLINE201707280004',NULL,'商品单价','Integer',NULL,NULL,'商品单价',NULL,NULL,'2017-07-01 00:00:00',NULL,NULL,NULL),(98,'BUSINESSLINE201707310002','inputText','客户大类','String',NULL,NULL,NULL,'sql',NULL,NULL,NULL,'webservice',NULL),(99,'BUSINESSLINE201708020004','inputText','支付金额','Double',NULL,'paymentAmount',NULL,NULL,NULL,'2017-08-01 00:00:00',NULL,NULL,NULL),(100,'BUSINESSLINE201708020004','inputText','会员等级','String',NULL,'memberLevel','会员等级',NULL,NULL,'2017-08-01 00:00:00',NULL,NULL,NULL),(101,'BUSINESSLINE201708020004','inputDate','支付时间','Date',NULL,'payTime','支付时间',NULL,NULL,'2017-08-01 00:00:00',NULL,NULL,NULL),(102,'BUSINESSLINE201708020004','inputArea','会员积分','Integer','5','point','会员积分',NULL,NULL,NULL,NULL,NULL,NULL),(107,'BUSINESSLINE201707280001',NULL,'PARTNER_ID','Integer',NULL,'partnerId',NULL,'sql','SELECT lt.`PARTNER_ID`  FROM lyt_txn lt  WHERE lt.`TXN_ID`=#txnId#','2017-08-01 00:00:00',NULL,NULL,NULL),(108,'BUSINESSLINE201707280001',NULL,'PRODUCT_ID','Integer',NULL,'productId',NULL,'sql','SELECT lt.`PRODUCT_ID` FROM lyt_txn lt WHERE lt.`TXN_ID`=#txnId#','2017-08-01 00:00:00',NULL,NULL,NULL),(109,'BUSINESSLINE201707280001',NULL,'交易积分','Integer',NULL,'point2','交易积分','sql','select point_type_id from lyt_point_type where name=\'交易积分\'','2017-08-01 00:00:00',NULL,NULL,NULL),(110,'BUSINESSLINE201707170006','inputText','影院名称','String','珠江新城','fileNameCode','影院名称','redis','fileName','2017-08-04 00:00:00','2017-08-25 00:00:00','webservice','SERVICE_SEQ_00004'),(111,'BUSINESSLINE201707170006','inputText','影片名称','String','战狼2','cimalName','影片名称','webservice','se','2017-08-04 00:00:00','2017-08-31 00:00:00','webservice','qweqwe'),(112,'BUSINESSLINE201707280001',NULL,'金额','Double',NULL,'amount',NULL,'sql','SELECT amount FROM lyt_txn WHERE txn_id=#txnId#','2017-08-01 00:00:00',NULL,NULL,NULL),(114,'BUSINESSLINE201707170006','inputText','观影人数','String','1','ordernumber','观影人数','webservice','>=1','2017-08-01 00:00:00','2017-08-01 00:00:00','webservice','order_query'),(118,'BUSINESSLINE201707280002',NULL,'会员等级','String',NULL,'level','会员等级','sql','select LEVEL from member_info where member_id = #MMB_ID#','2017-08-01 00:00:00',NULL,NULL,NULL),(119,'BUSINESSLINE201707280002',NULL,'会员经验值','Integer',NULL,'point1','会员经验值','sql','select point1 from lyt_mmb_point_value where member_id = #MMB_ID#','2017-08-01 00:00:00',NULL,NULL,NULL);
/*!40000 ALTER TABLE `rule_dim` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-14 17:04:35
