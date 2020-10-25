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
-- Table structure for table `rule_business_line`
--

DROP TABLE IF EXISTS `rule_business_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_business_line` (
  `rule_business_line_id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_business_line_code` varchar(100) DEFAULT NULL COMMENT '业务线编码',
  `rule_business_line_type` varchar(100) DEFAULT NULL,
  `rule_business_line_name` varchar(500) DEFAULT NULL COMMENT '业务线名称',
  `rule_business_line_Desc` varchar(500) DEFAULT NULL COMMENT '业务线描述',
  `rule_business_line_parent_code` varchar(100) DEFAULT NULL COMMENT '业务线所属的业务线大类',
  `rule_business_line_mappType` varchar(100) DEFAULT NULL COMMENT '随机匹配 全部匹配 优选匹配 ',
  PRIMARY KEY (`rule_business_line_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_business_line`
--

LOCK TABLES `rule_business_line` WRITE;
/*!40000 ALTER TABLE `rule_business_line` DISABLE KEYS */;
INSERT INTO `rule_business_line` VALUES (13,'BUSINESSLINE201707170005','ACTIVITY','注册送积分','desc001',NULL,'all'),(14,'BUSINESSLINE201707170006','ACTIVITY','影票送积分','desc002',NULL,'dimSize'),(15,'BUSINESSLINE201707170007','ACTIVITY','活动促销','活动描述测试',NULL,'random'),(16,'BUSINESSLINE201707260001','LYT','生日送积分','生日送100积分',NULL,'all'),(18,'BUSINESSLINE201707280001','LYT','订单交易成功送积分','订单交易成功送积分',NULL,'all'),(19,'BUSINESSLINE201707280002','LYT','会员升降级','会员升降级',NULL,'all'),(22,'BUSINESSLINE201707280004','ACTIVITY','订单交易-经销商','',NULL,'all'),(23,'BUSINESSLINE201707310001','LYT','表单验证测试','测试',NULL,'random'),(24,'BUSINESSLINE201707310002','ACTIVITY','玉皇销售政策','玉皇销售政策',NULL,'all'),(28,'BUSINESSLINE201708020004','LYT','金逸活动',NULL,NULL,'all'),(29,'BUSINESSLINE201708020005','LYT','测试','1',NULL,'random'),(30,'BUSINESSLINE201708020006','LYT','测试2','12',NULL,'random'),(34,'BUSINESSLINE201708070003','ACTIVITY','观影券赠送-首映礼','会员月观影量达到一定数目后，当月赠送任意一部电影首映礼观影券',NULL,'dimSize');
/*!40000 ALTER TABLE `rule_business_line` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-14 17:04:36
