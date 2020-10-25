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
-- Table structure for table `saaf_webservice_info`
--

DROP TABLE IF EXISTS `saaf_webservice_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saaf_webservice_info` (
  `webservice_id` int(11) NOT NULL AUTO_INCREMENT,
  `business_line_code` varchar(100) DEFAULT NULL COMMENT '业务code',
  `webservice_code` varchar(100) NOT NULL COMMENT '服务code',
  `webservice_url` varchar(3000) NOT NULL COMMENT '服务请求地址',
  `webservice_name` varchar(100) DEFAULT NULL COMMENT '服务名',
  `webservice_desc` varchar(3000) DEFAULT NULL COMMENT '服务描述',
  `webserice_agreement` varchar(10) DEFAULT 'rest' COMMENT ' (soap/rest)',
  `webservice_type` varchar(10) DEFAULT 'post' COMMENT '(get/post)',
  `request_param_demo` varchar(3000) DEFAULT NULL COMMENT '请求参数样例',
  `response_param_demo` varchar(3000) DEFAULT NULL COMMENT '返回参数样例',
  `version_num` int(11) DEFAULT NULL COMMENT '版本',
  `CREATION_DATE` datetime DEFAULT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `LAST_UPDATED_BY` datetime DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`webservice_id`),
  UNIQUE KEY `webservice_id_UNIQUE` (`webservice_id`),
  UNIQUE KEY `webservice_code_unique` (`webservice_code`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saaf_webservice_info`
--

LOCK TABLES `saaf_webservice_info` WRITE;
/*!40000 ALTER TABLE `saaf_webservice_info` DISABLE KEYS */;
INSERT INTO `saaf_webservice_info` VALUES (1,'businessline201707040001','order_query','http://www.baidu.com','order query','order query','rest','post','\"{aaa:1}\"','\"{status:\"S\"}\"',0,'2017-07-05 16:17:14',-1,NULL,NULL),(4,'activity','order_query3','http://www.baidu.com','order query','order query','rest','post','dvdf','fds',0,'2017-07-05 16:19:28',-1,NULL,NULL),(6,'businessline201707040001','fsd','http://www.baidu.com','teweqwew','fssd','fs','ads','fdsea','fedsf',1,'2017-07-06 16:58:32',-1,NULL,NULL),(7,'BUSINESSLINE201708020004','qweqwe','http://www.baidu.com','规则引擎','规则引擎','规则引擎','规则引擎','\"{}\"','\"{}\"',1,'2017-07-07 17:50:36',-1,NULL,NULL),(8,'BUSINESSLINE201708020004','SERVICE_SEQ_00001','http://www.baidu.com','aaa','desc','REST','PUT','\"{a:1}\"','\"{status:\"s\"}\"',1,'2017-07-10 14:19:25',-1,NULL,NULL),(9,'businessline201707040001','SERVICE_SEQ_00002','http://www.163.com','bbb','descdddd','REST','GET','\"{}\"','\"{}\"',3,'2017-07-10 14:36:54',-1,NULL,NULL),(11,'addPoint_51','SERVICE_SEQ_00003','http://localhost:8080/restful4soa/service/helloRest/postEmployees1','afdsafdsa',NULL,'REST','POST','\"{fads:dfads}\"','\"{fadsfdsaf:fdsafdsa}\"',NULL,'2017-07-27 11:30:36',-1,NULL,NULL),(12,'BUSINESSLINE201707170005','SERVICE_SEQ_00004','https:\\\\192.168.1.1','测试服务01','获取用户信息','REST','POST','\"{user:\'join\'}\"','\"[\'user\',\'age\']\"',NULL,'2017-08-01 11:16:45',-1,NULL,NULL),(18,'BUSINESSLINE201707170005','SERVICE_SEQ_00010','https://baidu.com','测试服务2','test2','SOAP','POST','index','obj',NULL,'2017-08-06 09:34:40',-1,NULL,NULL);
/*!40000 ALTER TABLE `saaf_webservice_info` ENABLE KEYS */;
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
