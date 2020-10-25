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
-- Table structure for table `rule_lyt_mapping_business`
--

DROP TABLE IF EXISTS `rule_lyt_mapping_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_lyt_mapping_business` (
  `RULE_MAPP_BUS_ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL COMMENT '动作名称',
  `DESCRIPTION` varchar(500) DEFAULT NULL COMMENT '动作描述',
  `ACTION_TYPE` varchar(50) NOT NULL COMMENT '动作类型(级别升降、积分增减、服务调用、跟新属性标识等)',
  `RULE_BUS_target_dim` varchar(150) DEFAULT NULL COMMENT '目标维度',
  `RULE_BUS_dim` varchar(150) DEFAULT NULL COMMENT '来源维度',
  `rule_bus_dim_operator` varchar(255) DEFAULT NULL COMMENT '操作符 加减乘除 + - * /',
  `rule_bus_dim_value` varchar(15) DEFAULT NULL COMMENT '值',
  `rule_bus_target_source` varchar(150) DEFAULT NULL COMMENT '服务地址',
  `action_isolation` varchar(30) DEFAULT NULL COMMENT '动作隔离(Y/N)',
  `rule_exp_code` varchar(100) DEFAULT NULL,
  `rule_bus_level` int(11) DEFAULT NULL,
  `EFFECT_DATE` datetime DEFAULT NULL,
  `EFFECT_END_DATE` datetime DEFAULT NULL,
  `SEQ_NUM` int(11) NOT NULL COMMENT '执行顺序',
  PRIMARY KEY (`RULE_MAPP_BUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_lyt_mapping_business`
--

LOCK TABLES `rule_lyt_mapping_business` WRITE;
/*!40000 ALTER TABLE `rule_lyt_mapping_business` DISABLE KEYS */;
INSERT INTO `rule_lyt_mapping_business` VALUES (68,'升级金卡会员','升级金卡会员','LEVEL_ADJUST','','','','11','',NULL,NULL,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `rule_lyt_mapping_business` ENABLE KEYS */;
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
