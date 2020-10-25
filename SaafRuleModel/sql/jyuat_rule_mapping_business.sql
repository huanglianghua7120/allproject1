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
-- Table structure for table `rule_mapping_business`
--

DROP TABLE IF EXISTS `rule_mapping_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rule_mapping_business` (
  `RULE_MAPP_BUS_ID` int(10) NOT NULL AUTO_INCREMENT,
  `RULE_BUS_dim` varchar(150) DEFAULT NULL,
  `rule_bus_dim_operator` varchar(255) DEFAULT NULL COMMENT '操作符 加减乘除 + - * /',
  `rule_bus_dim_value` varchar(15) DEFAULT NULL,
  `rule_bus_target_type` varchar(150) DEFAULT NULL,
  `rule_bus_target_source` varchar(150) DEFAULT NULL,
  `rule_bus_result_dim` varchar(3000) DEFAULT NULL,
  `rule_bus_param` varchar(3000) DEFAULT NULL,
  `rule_exc_code` varchar(100) DEFAULT NULL,
  `rule_bus_level` int(11) DEFAULT NULL,
  `EFFECT_DATE` datetime DEFAULT NULL,
  `EFFECT_END_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`RULE_MAPP_BUS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rule_mapping_business`
--

LOCK TABLES `rule_mapping_business` WRITE;
/*!40000 ALTER TABLE `rule_mapping_business` DISABLE KEYS */;
INSERT INTO `rule_mapping_business` VALUES (14,'age','+','111','confirmed','22','',NULL,'BUSINESSLINE201707170005_exp_01',1,NULL,NULL),(15,'sex','-','222','serviceURL','333','',NULL,'BUSINESSLINE201707170005_exp_01',22,NULL,NULL),(16,'age','/','222','serviceURL','223','',NULL,'BUSINESSLINE201707170005_exp_02',32,NULL,NULL),(17,'registerTime','*','222','serviceURL','222','',NULL,'BUSINESSLINE201707170005_exp_01',44,NULL,NULL),(18,'sex','=','234','serviceURL','45','',NULL,'BUSINESSLINE201707170005_exp_03',5,NULL,NULL),(19,NULL,'+',NULL,'confirmed',NULL,'',NULL,'BUSINESSLINE201707170005_exp_01',NULL,NULL,NULL),(20,'age','=','500','confirmed',NULL,'',NULL,'BUSINESSLINE201707170005_exp_03',NULL,NULL,NULL),(21,'age','/','18','serviceURL','age','',NULL,'BUSINESSLINE201707260002_exp_01',2,NULL,NULL),(22,'sex','=','20','confirmed',NULL,'',NULL,'BUSINESSLINE201707170005_exp_01',1,NULL,NULL),(23,'cinFilm','-','2','confirmed',NULL,'',NULL,'BUSINESSLINE201707170007_exp_01',NULL,NULL,NULL),(24,NULL,'+','11','confirmed','11','',NULL,'BUSINESSLINE201707280004_exp_01',0,NULL,NULL),(25,'sex','+','1','confirmed','1','',NULL,'BUSINESSLINE201707170005_exp_01',21,NULL,NULL),(26,'registerTime','=','1','confirmed','12','',NULL,'BUSINESSLINE201707170005_exp_01',111,NULL,NULL),(27,'registerTime','/','12121','confirmed','1212121','',NULL,'BUSINESSLINE201707170005_exp_01',12121,NULL,NULL),(29,'paymentAmount','-','5','confirmed',NULL,'',NULL,'BUSINESSLINE201708020004_exp_01',NULL,NULL,NULL),(30,'point','+','5','confirmed',NULL,'',NULL,'BUSINESSLINE201708020004_exp_01',NULL,NULL,NULL),(31,'age',NULL,NULL,'serviceURL','qweqwe','',NULL,'BUSINESSLINE201708020004_exp_01',NULL,NULL,NULL),(32,'cinFilm','-','1','confirmed','user',NULL,NULL,'BUSINESSLINE201707170007',9,NULL,NULL),(33,'cinFilm','+','11','serviceURL','121',NULL,NULL,'BUSINESSLINE201707170007',1,NULL,NULL),(34,'cinFilm','-','10','confirmed','user',NULL,NULL,'BUSINESSLINE201707170007',13,NULL,NULL),(35,'cinFilm','+','123','confirmed','11',NULL,NULL,'BUSINESSLINE201707170007',14,NULL,NULL),(36,'cinFilm','*','12','confirmed','14',NULL,NULL,'BUSINESSLINE201707170007_exp_01',1,NULL,NULL),(37,'cinScreen','+','1','serviceURL','2',NULL,NULL,'BUSINESSLINE201707170007_exp_01',3,NULL,NULL),(38,'cimalName','+','500','confirmed','45435',NULL,NULL,'BUSINESSLINE201707170006_exp_01',NULL,NULL,NULL),(39,NULL,'=','111','confirmed','1','',NULL,'BUSINESSLINE201707170005_exp_01',1,NULL,NULL),(40,'age','+','1','confirmed','积分+1','',NULL,'BUSINESSLINE201707170006_exp_01',1,NULL,NULL),(41,'sex','+','10000','confirmed',NULL,NULL,NULL,'BUSINESSLINE201707170005_exp_01',NULL,NULL,NULL),(42,'111','-','1','confirmed',NULL,NULL,NULL,'BUSINESSLINE201708070003_exp_01',NULL,NULL,NULL);
/*!40000 ALTER TABLE `rule_mapping_business` ENABLE KEYS */;
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
