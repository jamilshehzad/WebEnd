CREATE DATABASE  IF NOT EXISTS `ixvdata` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ixvdata`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: ixvdata
-- ------------------------------------------------------
-- Server version	5.5.33

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
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companies` (
  `name` varchar(45) DEFAULT NULL,
  `creationDate` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `_id` varchar(45) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES ('Artevent Gmbh','16.04.2014','Renngasse 12','active','GDfXYyyeueAidNukn'),('Cin Consult Unternehmensberatungs Gmbh','12.04.2000','Beatrixgasse 32','active','k82aCMbQ6covW6bSS'),('Coca-Cola','12.04.2014','rtertrtwert','active','LoQZLPnonArP9dria'),('HTB Consult GmbH','14.09.2010','Am Heumarkt 13','active','vLzWd75j4LH6jT5WR');
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_persons`
--

DROP TABLE IF EXISTS `company_persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_persons` (
  `personId` varchar(45) DEFAULT NULL,
  `function` varchar(45) DEFAULT NULL,
  `since` varchar(45) DEFAULT NULL,
  `left` varchar(45) DEFAULT NULL,
  `companyId` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `_id` varchar(45) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_persons`
--

LOCK TABLES `company_persons` WRITE;
/*!40000 ALTER TABLE `company_persons` DISABLE KEYS */;
INSERT INTO `company_persons` VALUES ('vfg3SLoMWfeQmhh5m','Prokurist','20.03.1980',NULL,'k82aCMbQ6covW6bSS','active','8gG88L65WvJMb2BLN'),('FkueLhhfvB5jko62e','geschäftsführer','28.04.2014',NULL,'vLzWd75j4LH6jT5WR','active','B8mtakZo87kMbGFv6'),('4GRM3d5hCt7Tm2bKR','CEO','12.04.2014',NULL,'k82aCMbQ6covW6bSS','active','btbu67yJMiSo2eCPn'),('vfg3SLoMWfeQmhh5m','Prokurist','15.04.2014',NULL,'vLzWd75j4LH6jT5WR','active','HELowZnRWdAih6FZc'),('rP9acp4duvuRPcyjx','blutsauger','11.02.2014','28.05.2014','LoQZLPnonArP9dria','inactive','iPyXAoj73GF3o9j4N'),('jgfZMDhmHGJfhhsvT','it guy','23.04.2014',NULL,'GDfXYyyeueAidNukn','active','KQDCRpJEhN4MNjXw6'),('jgfZMDhmHGJfhhsvT','IT','05.03.2014','01.05.2014','k82aCMbQ6covW6bSS','inactive','mhiuaSnmBqaHzPfqS'),('4GRM3d5hCt7Tm2bKR','Geschäftsführer','14.09.2010',NULL,'vLzWd75j4LH6jT5WR','active','mzbqCrbKDRmu4Sjym'),('jgfZMDhmHGJfhhsvT','IT','16.04.2014',NULL,'vLzWd75j4LH6jT5WR','active','tFzmpKyThsh96LaFT'),('u6kmYBLhPMQZDNXxR','CEO','16.04.2014',NULL,'GDfXYyyeueAidNukn','active','tyc96HyQRkt4X9Jfb');
/*!40000 ALTER TABLE `company_persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_shares`
--

DROP TABLE IF EXISTS `company_shares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_shares` (
  `companyId` varchar(45) DEFAULT NULL,
  `share` varchar(45) DEFAULT NULL,
  `since` varchar(45) DEFAULT NULL,
  `personId` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `_id` varchar(45) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_shares`
--

LOCK TABLES `company_shares` WRITE;
/*!40000 ALTER TABLE `company_shares` DISABLE KEYS */;
INSERT INTO `company_shares` VALUES ('vLzWd75j4LH6jT5WR','20','15.04.2014','vfg3SLoMWfeQmhh5m','active','37FisuHwTgZjKrZ26'),('k82aCMbQ6covW6bSS','30','15.04.2014','FkueLhhfvB5jko62e','active','8386FEcbuuYx3ujbE'),('k82aCMbQ6covW6bSS','30','15.04.2014','vfg3SLoMWfeQmhh5m','active','fptTJz9tWqwpLDCgu'),('k82aCMbQ6covW6bSS','40','15.04.2014','4GRM3d5hCt7Tm2bKR','active','sASS8Fckvd6hWQYTv'),('vLzWd75j4LH6jT5WR','30','15.04.2014','FkueLhhfvB5jko62e','active','wF7XgjZjssLNkWPBu');
/*!40000 ALTER TABLE `company_shares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `name` varchar(45) DEFAULT NULL,
  `birthDate` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `_id` varchar(45) NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES ('Thomas Havernek',NULL,'Test Strasse','active','4GRM3d5hCt7Tm2bKR'),('Florian Kalchmair','01.02.1978','Teststrasse 3','active','FkueLhhfvB5jko62e'),('Sharik Reza','26.02.1987','Teststrasse 3','Left','jgfZMDhmHGJfhhsvT'),('Graf Dracula','02.03.1960',NULL,'active','rP9acp4duvuRPcyjx'),('Mag Robert Hofferer','06.04.1972','Teststrasse 5','active','u6kmYBLhPMQZDNXxR'),('Katja Schütz','04.03.1971','Test Strasse 2',NULL,'vfg3SLoMWfeQmhh5m');
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-24 22:44:53
