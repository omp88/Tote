-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: totalizator
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `admin_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1,'admin','admin@gmail.com','$2a$10$5xyaERFD3UCOxAi8u.0bROeMxzj9c9xtXd0jaUVzFTEEzivrMn6Fa'),(2,'ad','ad@mail.com','$2a$10$2sHzIo8ixX0IefqUixQ9SOCHnqoF9U0.XNk001k4AJIY.VWYtrhFu');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bet`
--

DROP TABLE IF EXISTS `bet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bet` (
  `bet_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Football bet ID',
  `event_id` int(10) unsigned NOT NULL COMMENT 'Football event ID',
  `client_id` int(10) unsigned NOT NULL,
  `bet_condition` enum('first','second','draw','score') NOT NULL COMMENT 'Bet condition of football event (can be ''first'', ''second'', ''draw'',''score'')',
  `bet_amount` decimal(20,2) NOT NULL COMMENT 'Amount of money bet on this condition',
  `exp_team1_score` tinyint(3) DEFAULT NULL COMMENT 'Expected score of team 1',
  `exp_team2_score` tinyint(3) DEFAULT NULL COMMENT 'Expected score of team 2',
  `coeff` decimal(5,2) NOT NULL,
  `state` enum('stand','win','lose') NOT NULL,
  PRIMARY KEY (`bet_id`,`event_id`,`client_id`),
  KEY `fk_Football_bet_Football_event1_idx` (`event_id`),
  KEY `fk_Bet_Client2_idx` (`client_id`),
  CONSTRAINT `fk_Bet_Client2` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Football_bet_Football_event1` FOREIGN KEY (`event_id`) REFERENCES `football_event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='Information about bet on football event';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bet`
--

LOCK TABLES `bet` WRITE;
/*!40000 ALTER TABLE `bet` DISABLE KEYS */;
INSERT INTO `bet` VALUES (1,4,4,'draw',80.00,NULL,NULL,0.00,'stand'),(2,3,5,'draw',45.00,NULL,NULL,0.00,'lose'),(3,2,1,'first',100.00,NULL,NULL,6.80,'lose'),(4,6,5,'first',25.00,NULL,NULL,0.00,'stand'),(5,1,3,'second',40.00,NULL,NULL,0.00,'lose'),(6,12,2,'second',200.00,NULL,NULL,0.00,'stand'),(7,10,4,'first',300.00,NULL,NULL,0.00,'stand'),(8,6,1,'draw',190.00,NULL,NULL,0.00,'stand'),(9,4,5,'second',35.00,NULL,NULL,0.00,'stand'),(10,2,2,'first',70.00,NULL,NULL,6.80,'lose'),(11,1,6,'first',210.00,NULL,NULL,0.00,'win'),(12,8,3,'draw',300.00,NULL,NULL,0.00,'stand'),(13,6,4,'first',100.00,NULL,NULL,0.00,'stand'),(14,11,1,'first',50.00,NULL,NULL,0.00,'stand'),(15,5,1,'first',12.00,NULL,NULL,0.00,'stand'),(16,8,1,'score',44.00,2,4,0.00,'stand'),(20,2,4,'score',40.00,3,1,11.80,'lose'),(21,2,5,'second',50.00,NULL,NULL,3.65,'win');
/*!40000 ALTER TABLE `bet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Client ID',
  `first_name` varchar(45) NOT NULL COMMENT 'Client first name',
  `last_name` varchar(45) NOT NULL COMMENT 'Client last name',
  `email` varchar(45) NOT NULL COMMENT 'Client email',
  `login` varchar(45) NOT NULL COMMENT 'Client login',
  `password` varchar(255) NOT NULL COMMENT 'Client password',
  `passport_id` varchar(45) NOT NULL COMMENT 'Client passport ID',
  `money_amount` decimal(20,2) NOT NULL DEFAULT '0.00' COMMENT 'Clients balance of money',
  `client_efficiency` double NOT NULL DEFAULT '0' COMMENT 'Winning efficiency of clients bets ',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='Information about client of totalizator';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Burton','Cline','dui.nec@dictum.ca','Elit','$2a$10$ZA8njnTUGG.R6HEiBPh/recJdIYD7E9TgL0HqJ.RsJe1KQzuOTudq','16500410',46413.34,0),(2,'Boris','Wells','vestibulum.neque@nislQuisque.net','Ligula','$2a$10$8Fa6H5Hn7D0CLlX4ogozXOwFoj2vMWcoLKGAjzFQXMpUarEoLg9tK','16190113',31904.00,0),(3,'Colby','Young','blandit@quispede.ca','Libero','$2a$10$LmfvSnzo0p4E6apE70gUS.3qfCA12bs4GYXZUr/01KKcX6lJVqMG.','16650320',4800.00,0),(4,'Cade','Burton','ornare.elit@semelit.net','Martin','$2a$10$VNPxgdxEBLTCTOdKoTtmK.KsQwlGtF7awjPQze2GpP8afCwDzpnvW','16730620',2088.00,0),(5,'Kevin','Rosales','tempor.diam.dictum@amalesuadaid.co.uk','Euismod','$2a$10$Qg.xK4UFcgdT7ZeavcSILOqUGV31zuSG3bJEx5VxxV5Bzqs94uGnq','16871122',10182.50,0),(6,'Castor','Hines','sagittis.Duis.gravida@mollis.co.uk','Barber','$2a$10$543YC0p4MIKybZ3pWJiBROtXycTaluMChyghM0p/Zsp1JIFmzmxze','16531121',7000.00,0),(137,'Niki','za','za@gmail.com','login','$2a$10$NvWezCHAItUazWxTmQEX1egYMpbjNb.pKHsfCqy0czsPwYN4JfJ.m','1111',0.00,0);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `football_event`
--

DROP TABLE IF EXISTS `football_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_event` (
  `event_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Football event ID',
  `name` varchar(45) NOT NULL COMMENT 'Football event name',
  `start` datetime NOT NULL,
  `team1_name` varchar(45) NOT NULL COMMENT 'Team 1 name',
  `team2_name` varchar(45) NOT NULL COMMENT 'Team 2 name',
  `team1_win_coef` double(5,2) NOT NULL COMMENT 'Team 1 winning coefficient',
  `team2_win_coef` double(5,2) NOT NULL COMMENT 'Team 2 winning coefficient',
  `draw_coef` double(5,2) NOT NULL COMMENT 'Draw coefficient',
  `state` enum('stand','done') NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='Information about football event';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `football_event`
--

LOCK TABLES `football_event` WRITE;
/*!40000 ALTER TABLE `football_event` DISABLE KEYS */;
INSERT INTO `football_event` VALUES (1,'WC Group A','2018-06-13 11:30:00','Russia','Saudi Arabia',1.45,8.60,4.30,'done'),(2,'WC Group A','2018-06-13 15:00:00','Egypt','Uruguay',6.80,1.63,3.65,'done'),(3,'WC Group B','2018-06-14 12:00:00','Morocco','Iran',2.31,3.70,3.00,'done'),(4,'WC Group B','2018-06-14 16:00:00','Portugal','Spain',4.20,1.97,3.40,'stand'),(5,'WC Group C','2018-06-14 19:00:00','France','Australia',1.25,14.00,6.10,'stand'),(6,'WC Group D','2018-06-15 10:00:00','Argentina','Iceland',1.33,11.50,5.10,'stand'),(7,'WC Group C','2018-06-15 14:00:00','Peru','Denmark',3.15,2.25,3.20,'stand'),(8,'WC Group D','2018-06-17 11:30:00','Croatia','Nigeria',1.70,5.60,3.70,'stand'),(9,'WC Group E','2018-09-18 15:00:00','Costa Rica','Serbia',4.50,1.95,3.30,'stand'),(10,'WC Group F','2018-09-19 10:00:00','Germany','Mexico',1.48,7.70,4.30,'stand'),(11,'WC Group E','2018-09-20 12:00:00','Brazil','Switzerland',1.41,9.20,4.50,'stand'),(12,'WC Group F','2018-09-21 16:00:00','Sweden','Korea Republic',2.06,4.10,3.25,'stand');
/*!40000 ALTER TABLE `football_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `football_result`
--

DROP TABLE IF EXISTS `football_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `football_result` (
  `football_event_id` int(10) unsigned NOT NULL COMMENT 'Football event ID',
  `football_result` enum('first','second','draw') NOT NULL COMMENT 'Result of footbal event (can be ''first'', ''second'', ''draw'')',
  `team1_score` tinyint(3) unsigned NOT NULL COMMENT 'Team 1 score',
  `team2_score` tinyint(3) unsigned NOT NULL COMMENT 'Team 2 score',
  PRIMARY KEY (`football_event_id`),
  CONSTRAINT `fk_Football_result4_Football_event1` FOREIGN KEY (`football_event_id`) REFERENCES `football_event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Information about football event result';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `football_result`
--

LOCK TABLES `football_result` WRITE;
/*!40000 ALTER TABLE `football_result` DISABLE KEYS */;
INSERT INTO `football_result` VALUES (1,'first',3,1),(2,'second',1,2),(3,'first',4,2);
/*!40000 ALTER TABLE `football_result` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-13  8:44:00
