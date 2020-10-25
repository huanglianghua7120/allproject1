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
-- Table structure for table `rule_txn_head`
--

DROP TABLE IF EXISTS `rule_txn_head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_txn_head` (
  `rule_txn_id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_mapp_bus_id` int(11) NOT NULL,
  `scence_code` varchar(100) DEFAULT NULL,
  `rule_dim_code` varchar(150) DEFAULT NULL,
  `rule_business_line_code` varchar(100) DEFAULT NULL COMMENT '业务线code',
  `scence` varchar(100) DEFAULT NULL COMMENT '业务场景',
  `redis_flag` varchar(45) DEFAULT 'Y' COMMENT '是否将标识存入redis,Y/N',
  `calculation_mode` varchar(45) DEFAULT NULL COMMENT 'redis值标记方式(累加/直接赋值)',
  `initial_value` varchar(45) DEFAULT NULL COMMENT '增量/redis值',
  `cycle` int(11) DEFAULT NULL COMMENT '周期(递增到指定值时清零)',
  PRIMARY KEY (`rule_txn_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_txn_head`
--

LOCK TABLES `rule_txn_head` WRITE;
/*!40000 ALTER TABLE `rule_txn_head` DISABLE KEYS */;
/*!40000 ALTER TABLE `rule_txn_head` ENABLE KEYS */;
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
