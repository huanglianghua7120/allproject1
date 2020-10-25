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
-- Table structure for table `saaf_webservice_param_info`
--

DROP TABLE IF EXISTS `saaf_webservice_param_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saaf_webservice_param_info` (
  `param_id` int(11) NOT NULL AUTO_INCREMENT,
  `webservice_code` varchar(100) NOT NULL COMMENT '服务code',
  `param_code` varchar(80) NOT NULL,
  `param_name` varchar(100) DEFAULT NULL,
  `param_desc` varchar(500) DEFAULT NULL,
  `param_type` varchar(50) DEFAULT NULL,
  `required_flag` varchar(10) DEFAULT 'Y' COMMENT 'Y/N',
  `version_num` int(11) DEFAULT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATED_BY` int(11) NOT NULL DEFAULT '0',
  `LAST_UPDATED_BY` int(11) NOT NULL DEFAULT '0',
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_LOGIN` int(11) DEFAULT '0',
  PRIMARY KEY (`param_id`),
  UNIQUE KEY `param_id_UNIQUE` (`param_id`),
  UNIQUE KEY `webservice_code` (`webservice_code`,`param_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='服务参数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saaf_webservice_param_info`
--

LOCK TABLES `saaf_webservice_param_info` WRITE;
/*!40000 ALTER TABLE `saaf_webservice_param_info` DISABLE KEYS */;
INSERT INTO `saaf_webservice_param_info` VALUES (3,'order_query','sex','性别','描述','','Y',0,'2017-07-05 16:37:01',-1,-1,'2017-07-05 16:37:01',-1),(4,'order_query3','sss','年龄','desc','string','Y',NULL,'2017-07-07 16:44:49',-1,-1,'2017-07-07 16:44:49',-1),(6,'fsd','dfdf','aaa','vff','INT','N',2,'2017-07-10 14:53:37',-1,-1,'2017-07-10 14:55:29',-1);
/*!40000 ALTER TABLE `saaf_webservice_param_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-15 16:38:17
