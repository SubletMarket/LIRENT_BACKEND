-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lirent
-- ------------------------------------------------------
-- Server version	8.0.38

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `basket`
--

DROP TABLE IF EXISTS `basket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `basket` (
  `member_id` int NOT NULL,
  `sublease_id` int NOT NULL,
  PRIMARY KEY (`member_id`,`sublease_id`),
  KEY `basket_ibfk_2_idx` (`sublease_id`),
  CONSTRAINT `basket_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `basket_ibfk_2` FOREIGN KEY (`sublease_id`) REFERENCES `subleases` (`sublease_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `basket`
--

LOCK TABLES `basket` WRITE;
/*!40000 ALTER TABLE `basket` DISABLE KEYS */;
INSERT INTO `basket` VALUES (13,22),(14,22),(13,23);
/*!40000 ALTER TABLE `basket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chats`
--

DROP TABLE IF EXISTS `chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chats` (
  `sublease_id` int NOT NULL,
  `member_id` int NOT NULL,
  `is_owner` tinyint(1) NOT NULL,
  `message` text NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sublease_id`,`member_id`,`created`),
  KEY `member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chats`
--

LOCK TABLES `chats` WRITE;
/*!40000 ALTER TABLE `chats` DISABLE KEYS */;
INSERT INTO `chats` VALUES (22,12,1,'야 이 새기야.','2024-11-26 01:11:20'),(22,12,1,'너 뭐하는 새끼냐','2024-11-26 01:11:26'),(22,12,1,'맨유 우승 기원','2024-11-26 02:17:58'),(22,12,1,'ㅐㅣㅣㅐㅣ','2024-11-26 02:18:52'),(22,13,0,'뒤지고ㅓ싶냐?','2024-11-26 01:12:36'),(22,13,0,'어디서 반말이냐?','2024-11-26 01:12:39'),(22,13,0,'컹\'s','2024-11-26 01:14:14'),(22,13,0,'ㅇㅈ','2024-11-26 02:18:03');
/*!40000 ALTER TABLE `chats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` char(64) NOT NULL,
  `phone` char(13) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `park` tinyint(1) DEFAULT NULL,
  `building_elevator_num` int DEFAULT NULL,
  `floor` int DEFAULT NULL,
  `area` int DEFAULT NULL,
  `rooms` int DEFAULT NULL,
  `bathrooms` int DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `latitude` char(16) DEFAULT NULL,
  `longitude` char(16) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `members_pk` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES (12,'ceroopark@naver.com','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','010-9418-3625','충북 청주시 흥덕구 가로수로1131번길 11 청주 비하 1차 대광로제비앙','박건영',1,4,4,44,4,3,'2024-11-25 05:06:36','36.6280802438134','127.421224185879'),(13,'ceroopark2@naver.com','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','010-1234-5678','','이병조',0,0,0,0,0,0,'2024-11-25 05:07:45','',''),(14,'ceroopark3@naver.com','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','010-9417-1234','','송용인',0,4,4,44,4,4,'2024-11-25 05:12:37','',''),(15,'ceroopark4@naver.com','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','010-5678-1234','대전 유성구 한밭대로259번길 9 ','김태경',1,5,6,6,1,1,'2024-11-25 05:13:37','36.3673388045321','127.328783580337'),(16,'admin@naver.com','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','1234','','관리자',0,0,0,0,0,0,'2024-11-25 05:21:02','',''),(17,'test@test','9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08','010-6621-5676','경기 파주시 한빛로 70 한빛마을 5단지 캐슬앤칸타빌','김태영',1,1,1,1,1,1,'2024-11-25 05:27:48','37.7165892626908','126.756958390986'),(18,'22-11854@naver.com','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','1234','','박건영',0,0,0,0,0,0,'2024-11-25 06:06:38','','');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `title` varchar(30) NOT NULL,
  `context` text NOT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `category` varchar(30) NOT NULL DEFAULT 'General',
  PRIMARY KEY (`board_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (2,16,'관리자','필독_전대계약관련','사기당하지마세용','2024-11-25 05:21:25','[공지]');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sublease_deals`
--

DROP TABLE IF EXISTS `sublease_deals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sublease_deals` (
  `deal_id` int NOT NULL AUTO_INCREMENT,
  `sublease_id` int NOT NULL,
  `contractor_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `deposit` int DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `accepted` tinyint(1) NOT NULL DEFAULT '0',
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`deal_id`),
  KEY `sublease_id` (`sublease_id`),
  CONSTRAINT `sublease_deals_ibfk_1` FOREIGN KEY (`sublease_id`) REFERENCES `subleases` (`sublease_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sublease_deals`
--

LOCK TABLES `sublease_deals` WRITE;
/*!40000 ALTER TABLE `sublease_deals` DISABLE KEYS */;
INSERT INTO `sublease_deals` VALUES (13,22,12,'2024-11-26','2024-11-27',100000,2,1,'2024-11-25 05:40:12'),(18,22,13,'2024-11-25','2024-11-25',100000,1,0,'2024-11-26 01:12:27');
/*!40000 ALTER TABLE `sublease_deals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subleases`
--

DROP TABLE IF EXISTS `subleases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subleases` (
  `sublease_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `deposit` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `created` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sublease_id`),
  UNIQUE KEY `member_id_UNIQUE` (`member_id`),
  KEY `member_id` (`member_id`),
  CONSTRAINT `subleases_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subleases`
--

LOCK TABLES `subleases` WRITE;
/*!40000 ALTER TABLE `subleases` DISABLE KEYS */;
INSERT INTO `subleases` VALUES (22,12,'2024-11-25','2024-11-28',100000,1,'2024-11-25 05:16:34'),(23,15,'2024-11-25','2024-11-27',200000,20000,'2024-11-25 05:19:19');
/*!40000 ALTER TABLE `subleases` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26 17:02:33
