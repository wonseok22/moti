CREATE DATABASE  IF NOT EXISTS `moti` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `moti`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: babysteps.c6vpbkzcxdkw.ap-northeast-2.rds.amazonaws.com    Database: moti
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- GTID state at the beginning of the backup 
--


--
-- Table structure for table `achievement`
--

DROP TABLE IF EXISTS `achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `achievement` (
  `achievement_id` bigint NOT NULL AUTO_INCREMENT,
  `achievement_name` varchar(45) NOT NULL,
  `achievement_image_url` text,
  `achievement_desc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`achievement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `achievement`
--

LOCK TABLES `achievement` WRITE;
/*!40000 ALTER TABLE `achievement` DISABLE KEYS */;
INSERT INTO `achievement` VALUES (1,'시작이 반','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/e500b6e3-2802-48c4-a754-562bcadb5fd201.%20%EC%8B%9C%EC%9E%91%EC%9D%B4%20%EB%B0%98.png','미션 최초 완료'),(2,'드디어 입을 열다','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/b8c9c6fb-51e5-467f-aa02-1a76321aa8af02.%20%EB%93%9C%EB%94%94%EC%96%B4%20%EC%9E%85%EC%9D%84%20%EC%97%B4%EB%8B%A4.png','첫 댓글 남기기'),(3,'칭찬은 나도 춤추게 한다','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/a94de7a8-9def-45e9-9382-cba2d220eb6003.%20%EC%B9%AD%EC%B0%AC%EC%9D%80%20%EB%82%98%EB%8F%84%20%EC%B6%A4%EC%B6%94%EA%B2%8C%20%ED%95%9C%EB%8B%A4.png','본인 후기에 좋아요 클릭'),(4,'이게 여기 있었구나?','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/3303ac31-0548-4736-aac8-e49a2cdd590b04.%20%EC%9D%B4%EA%B2%8C%20%EC%97%AC%EA%B8%B0%20%EC%9E%88%EC%97%88%EA%B5%AC%EB%82%98.png','소소하고 확실한 운동 2번 이상 완수'),(5,'꾸준함이 최고','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/567fb021-3e8a-4168-9f4e-f195dd5e9fe505.%20%EA%BE%B8%EC%A4%80%ED%95%A8%EC%9D%B4%20%EC%B5%9C%EA%B3%A0.png','소소하고 확실한 운동 5번 이상 완수'),(6,'운동을 왜 하겠어, 날 위해 하지','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/ae145e6b-c3dd-450f-af23-22f39a0365c106.%20%EC%9A%B4%EB%8F%99%EC%9D%84%20%EC%99%9C%20%ED%95%98%EA%B2%A0%EC%96%B4%2C%20%EB%82%98%EB%A5%BC%20%EC%9C%84%ED%95%B4%20%ED%95%98%EC%A7%80.png','운동 습관 만들기 2번 이상 완수'),(7,'육체미 소동','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/8c15a629-ef14-4fd4-b536-2fd0d45e020607.%20%EC%9C%A1%EC%B2%B4%EB%AF%B8%20%EC%86%8C%EB%8F%99.png','운동 습관 만들기 5번 이상 완수'),(8,'운동은 습관','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/f115a2a9-41b3-48ee-bcd8-e1e5f6f60cf308.%20%EC%9A%B4%EB%8F%99%EC%9D%80%20%EC%8A%B5%EA%B4%80.png','건강 카테고리의 성장 플랜 5번 이상 완수'),(9,'가족애','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/d397a5aa-0cba-4e16-a1e3-64a7432401fd09.%20%EA%B0%80%EC%A1%B1%EC%95%A0.png','부모님께 한 발짝 2번 이상 완수'),(10,'엄마가 좋아 아빠가 좋아?','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/8933ef23-2fb9-425a-8282-97ba299af34910.%20%EC%97%84%EB%A7%88%EA%B0%80%20%EC%A2%8B%EC%95%84%20%EC%95%84%EB%B9%A0%EA%B0%80%20%EC%A2%8B%EC%95%84.png','부모님께 한 발짝 5번 이상 완수'),(11,'내외 안 하는 사이','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/70c804e1-8ebd-4be3-9afd-21a30b146fc711.%20%EB%82%B4%EC%99%B8%20%EC%95%88%20%ED%95%98%EB%8A%94%20%EC%82%AC%EC%9D%B4.png','관계 가꾸기 2번 이상 완수'),(12,'너, 내 동료가 돼라','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/bc235845-b1cf-4d01-9886-c0f83f6a75e212.%20%EB%84%88%2C%20%EB%82%B4%20%EB%8F%99%EB%A3%8C%EA%B0%80%20%EB%8F%BC%EB%9D%BC.png','관계 가꾸기 5번 이상 완수'),(13,'사랑꾼','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/11da06ee-550b-479e-a146-27ae678d93d313.%20%EC%82%AC%EB%9E%91%EA%BE%BC.png','관계 카테고리의 성장 플랜 5번 이상 완수'),(14,'행복 발굴단','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/5826f3a8-ace6-4018-8ebc-d3b270b38ca914.%20%ED%96%89%EB%B3%B5%20%EB%B0%9C%EA%B5%B4%EB%8B%A8.png','일상에서 행복 찾기 2번 이상 완수'),(15,'생각보다 가까이','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/1b4d9534-4fc2-4807-9a5e-0234b097e11015.%20%EC%83%9D%EA%B0%81%EB%B3%B4%EB%8B%A4%20%EA%B0%80%EA%B9%8C%EC%9D%B4.png','일상에서 행복 찾기 5번 이상 완수'),(16,'역사는 해석하기 나름','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/43c09479-b8f0-428e-a73c-4dee3672dbd116.%20%EC%97%AD%EC%82%AC%EB%8A%94%20%ED%95%B4%EC%84%9D%ED%95%98%EA%B8%B0%20%EB%82%98%EB%A6%84.png','나 돌아보기 2번 이상 완수'),(17,'그 모든 것이 현재 여기로','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/02c717e0-9242-4681-b3f9-322c7a71e72917.%20%EA%B7%B8%20%EB%AA%A8%EB%93%A0%20%EA%B2%83%EC%9D%B4%20%ED%98%84%EC%9E%AC%20%EC%97%AC%EA%B8%B0%EB%A1%9C.png','나 돌아보기 5번 이상 완수'),(18,'행복회로 발동','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/7dfc4e8e-fba2-4c98-9888-9457c26e2d6318.%20%ED%96%89%EB%B3%B5%ED%9A%8C%EB%A1%9C%20%EB%B0%9C%EB%8F%99.png','행복 카테고리의 성장 플랜 5번 이상 완수'),(19,'완벽주의','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/00188a0a-23ad-4d63-8f37-b022a4db68a019.%20%EC%99%84%EB%B2%BD%EC%A3%BC%EC%9D%98.png','각 카테고리의 성장 플랜 각각 1개씩 완수'),(20,'손에 손 잡고','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/e4ff16fc-3aed-4a8d-af75-a40c89e9daad20.%20%EC%86%90%EC%97%90%20%EC%86%90%20%EC%9E%A1%EA%B3%A0.png','팔로우, 팔로워 각각 10명 이상'),(21,'백점 만점','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/14b5a27a-242d-4246-99dc-2de18ae0c5ba21.%20%EB%B0%B1%EC%A0%90%20%EB%A7%8C%EC%A0%90.png','미션 100개 이상 완료'),(22,'최고의 응원 단장','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/f3c44202-2c36-4645-ab05-1225274cb6d122.%20%EC%B5%9C%EA%B3%A0%EC%9D%98%20%EC%9D%91%EC%9B%90%20%EB%8B%A8%EC%9E%A5.png','댓글 5개 이상 남기기'),(23,'응원 받아랏','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/d44f61d5-b83f-4593-9875-e2395eeaa6f023.%20%EC%9D%91%EC%9B%90%20%EB%B0%9B%EC%95%84%EB%9E%8F.png','다른 유저의 후기에 좋아요 남기기'),(24,'애정 부자','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/17b61bab-224a-400f-93a1-317d08e5075824.%20%EC%95%A0%EC%A0%95%20%EB%B6%80%EC%9E%90.png','다른 유저의 후기에 좋아요 10개 남기기'),(25,'모티는 사랑을 싣고','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/3510fd89-cab0-48d6-b58c-e53d332c999d25.%20%EB%AA%A8%ED%8B%B0%EB%8A%94%20%EC%82%AC%EB%9E%91%EC%9D%84%20%EC%8B%A3%EA%B3%A0.png','다른 유저의 후기에 좋아요 100개 남기기'),(26,'자라나라 식물식물','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/35215ecf-0dd9-4ead-b72c-95670682547626.%20%EC%9E%90%EB%9D%BC%EB%82%98%EB%9D%BC%20%EC%8B%9D%EB%AC%BC%EC%8B%9D%EB%AC%BC.png','실패한 성장 플랜 재시도'),(27,'중요한 건 꺾이지 않는 마음','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/56d274a8-af5a-41d2-8e99-457fa3f67afd27.%20%EC%A4%91%EC%9A%94%ED%95%9C%20%EA%B1%B4%20%EA%BA%BE%EC%9D%B4%EC%A7%80%20%EC%95%8A%EB%8A%94%20%EB%A7%88%EC%9D%8C.png','실패한 성장 플랜 재도전을 통해 완수'),(28,'명의','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/0091a96f-e70c-479e-9b95-b91e7180f8f328.%20%EB%AA%85%EC%9D%98.png','실패한 성장 플랜 재도전을 통해 10개 완수'),(29,'마지막까지 최선을!','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/8408af56-af0a-45e4-b782-82cd98b4e4a729.%20%EB%A7%88%EC%A7%80%EB%A7%89%EA%B9%8C%EC%A7%80%20%EC%B5%9C%EC%84%A0%EC%9D%84%21.png','7일 중 마지막 날에 성장 플랜 완수'),(30,'열쩡열쩡열쩡!','https://motiimages.s3.ap-northeast-2.amazonaws.com/achievementImages/5115c32f-12f2-4c26-8660-4972397a580a30.%20%EC%97%B4%EC%A9%A1%EC%97%B4%EC%A9%A1%EC%97%B4%EC%A9%A1%21.png','첫 5일에 성장 플랜 완수');
/*!40000 ALTER TABLE `achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  `category_image_url` text,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `CategoryName_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'건강','https://motiimages.s3.ap-northeast-2.amazonaws.com/categoryImages/e598b6c8-17b3-4df5-9fc1-ae9d38e37157%EA%B1%B4%EA%B0%95.png'),(2,'관계','https://motiimages.s3.ap-northeast-2.amazonaws.com/categoryImages/c18120f6-9d92-431c-80b0-2993f45173ab%EA%B4%80%EA%B3%84.png'),(3,'행복','https://motiimages.s3.ap-northeast-2.amazonaws.com/categoryImages/6488294a-e3f8-443f-be4c-64c246ef7473%ED%96%89%EB%B3%B5.png');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `feed_id` bigint NOT NULL,
  `content` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `user_writes_comment_idx` (`user_id`),
  KEY `feed_has_comment_idx` (`feed_id`),
  CONSTRAINT `feed_has_comment` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_writes_comment` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=411 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (204,'red4',92,'배고파요','2023-02-10 17:56:00'),(209,'david',105,'일단 감사하고 본다는 마인드... 좋네요?','2023-02-11 17:59:12'),(210,'david',100,'진심 먹고파요...','2023-02-11 17:59:32'),(211,'red4',106,'제발 치즈케이크','2023-02-11 21:57:09'),(212,'red4',108,'B형 화이팅~~','2023-02-11 22:08:38'),(213,'google114706080296583515621',108,'화이팅~~','2023-02-11 22:09:47'),(216,'kakao2659762741',100,'냉면 맛있겠다','2023-02-12 15:30:11'),(219,'sanghwa1103',92,'맛있겠다','2023-02-12 22:58:30'),(223,'red4',112,'다리가 기시네요..','2023-02-13 09:13:11'),(224,'red4',123,'프사가 귀엽네요','2023-02-13 10:04:36'),(227,'red4',124,'당신은 죽소.','2023-02-13 10:09:37'),(228,'google111229672003980040556',124,'역시 긍정적인 생각 너무 좋아요','2023-02-13 10:09:40'),(229,'red4',125,'기봉이 vs 초원이','2023-02-13 10:10:14'),(230,'red4',124,'긍정적으로 삽시다!!','2023-02-13 10:10:41'),(231,'google111229672003980040556',125,'저는 얼룩말을 좋아해요','2023-02-13 10:12:03'),(232,'red4',109,'변태 같아요!','2023-02-13 10:12:14'),(233,'google111229672003980040556',95,'조규성 보다 잘생긴 게 제일 부러워요','2023-02-13 10:12:26'),(234,'red4',125,'둘 중 하나만 고르세요','2023-02-13 10:12:40'),(235,'dnjstjr',123,'계단으로 올라가는 마음먹기 쉽지않은데 고생하셨네요 ㅠ','2023-02-13 10:13:43'),(236,'red4',100,'물냉이 근본이에요..','2023-02-13 10:35:19'),(237,'red4',95,'어허~ 그런 말 하면 안 돼요~','2023-02-13 11:02:44'),(238,'dnjstjr',124,'안녕하세여','2023-02-13 12:46:27'),(239,'red4',124,'안녕하십니까?','2023-02-13 13:27:47'),(240,'red4',110,'뛰어내리죠?','2023-02-13 13:29:24'),(241,'dnjstjr',128,'go back','2023-02-13 13:48:33'),(242,'dnjstjr',128,'안녕하세욤','2023-02-13 14:04:22'),(243,'dnjstjr',126,'군대에선 신중한게 좋죠','2023-02-13 15:16:04'),(244,'red4',129,'졸리면 산책하면 돼요!','2023-02-13 15:23:29'),(245,'red4',130,'커피를 다 마시면 옆으로 컵을 눕혀도 커피가 쏟아지지 않는다는 걸 알 수 있네요','2023-02-13 16:48:30'),(246,'red4',131,'침고이네;','2023-02-13 16:49:49'),(247,'red4',135,'수정하세요','2023-02-13 17:03:57'),(252,'sanghwa1103',124,'1','2023-02-13 18:13:46'),(253,'sanghwa1103',124,'2','2023-02-13 18:13:48'),(254,'sanghwa1103',124,'3','2023-02-13 18:13:50'),(255,'sanghwa1103',124,'4','2023-02-13 18:13:51'),(256,'sanghwa1103',124,'5','2023-02-13 18:13:53'),(257,'red4',136,'오늘도 고맙읍니다 고객님','2023-02-13 19:14:58'),(258,'red4',137,'먹죠? 저도 사주셈','2023-02-13 23:33:56'),(259,'david',135,'세리모니 해주세여','2023-02-14 10:11:03'),(260,'david',130,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2023-02-14 10:11:33'),(261,'david',137,'맹지니 님은 아이스크림을 좋아하시는 것 같아요','2023-02-14 10:12:28'),(262,'consultant01',138,'댓글 테스트','2023-02-14 10:58:58'),(263,'consultant01',135,'조규성 ㅋ','2023-02-14 11:00:05'),(264,'consultant01',137,'팀장님이 하나 사줍시다','2023-02-14 11:01:59'),(265,'red4',138,'테스트 완료','2023-02-14 11:05:47'),(266,'dnjstjr',137,'실례가 안된다면 저도 하나만,,','2023-02-14 11:05:56'),(267,'sanghwa1103',137,'이왕 사주시는거 제거도 사주시죠?','2023-02-14 11:06:27'),(268,'dnjstjr',138,'안녕하세용','2023-02-14 11:06:30'),(269,'red4',135,'오해에요','2023-02-14 11:16:41'),(270,'dnjstjr',135,'오예입니다.','2023-02-14 11:26:47'),(271,'sanghwa1103',136,'이제는 피드 수정기능에 기존 글 내용이 들어가도록 수정됐습니다. 감사합니다','2023-02-14 11:30:13'),(273,'david',142,'뜻개!','2023-02-14 12:14:46'),(274,'test',143,'굿굿!','2023-02-14 12:43:08'),(275,'red4',143,'십일조 안 내셔서 얼차려 드렸습니다. 앞으로 조심해주세요!','2023-02-14 12:52:01'),(276,'red4',147,'현금을 내놓으라고 합니다.','2023-02-14 14:23:03'),(277,'dnjstjr',147,'자바칩 프라푸치노에 휘핑크림 추가요~','2023-02-14 15:11:16'),(278,'kakao2546552508',147,'저도 커피는 못마셔서 매번 밀크쉐이크 마셔요!','2023-02-14 15:44:57'),(280,'kakao2546552508',152,'ㅎㅎ.. 응원합니다!','2023-02-14 17:43:20'),(281,'red4',152,'그게 문제였을까요!?','2023-02-14 17:44:33'),(282,'david',152,'무엇이든 열정을 가지고 하면 못 해낼 것이 없습니다!!! 응원합니다!!???','2023-02-14 17:51:13'),(283,'dnjstjr',152,'저도 운동을 하니까 여자친구가 생겼어요 화이팅 하십쇼!!!','2023-02-14 17:57:30'),(284,'red4',153,'카르페 디엠이 떠오릅니다 동지','2023-02-14 18:01:10'),(285,'kakao2665359733',152,'ㅎㅎㅎ 꿈은 원래 크게 잡는거죠!!','2023-02-14 18:02:00'),(286,'google105453065459048699210',152,'ㅋㅋㅋ 응원합니다!!','2023-02-14 18:09:58'),(287,'kakao2665359733',153,'열정불끈이 느껴집니다..','2023-02-14 20:22:59'),(288,'google113134987904705544342',152,'화이팅입니다','2023-02-14 21:33:20'),(289,'google113134987904705544342',153,'참... 열정님 같은 멘트','2023-02-14 21:34:41'),(290,'kakao2665359733',156,'제 모카도 곧 자랑 들어가겠습니다','2023-02-15 08:39:33'),(291,'david',156,'이미지에서 열정이 느껴집니다...!!!!','2023-02-15 09:09:21'),(292,'consultant01',138,'방가방가','2023-02-15 09:38:22'),(293,'consultant01',150,'좋아요 누를 때마다 숫자가 올라가요!\n','2023-02-15 09:39:50'),(294,'consultant01',143,'레드님은 사이비 교주인가요?','2023-02-15 09:41:31'),(295,'consultant01',141,'파쇼','2023-02-15 09:42:36'),(296,'consultant01',137,'다 돌리는걸로','2023-02-15 09:43:08'),(297,'consultant01',147,'오 명진님 저랑 같네요.. ㅎ','2023-02-15 09:43:39'),(298,'consultant01',134,'효자 원석님','2023-02-15 09:44:35'),(299,'consultant01',133,'배려모티','2023-02-15 09:45:02'),(300,'consultant01',130,'있었는데 없는','2023-02-15 09:45:34'),(301,'consultant01',128,'어느 대목이 성공인지 알려주믄 고맙겠','2023-02-15 09:46:12'),(302,'consultant01',127,'소감 준비','2023-02-15 09:46:35'),(303,'consultant01',124,'말이 없다구요???','2023-02-15 09:47:18'),(304,'red4',157,'표정이 넘 귀여워요','2023-02-15 09:47:34'),(305,'consultant01',123,'엘베 덕 강제 운동','2023-02-15 09:48:00'),(306,'consultant01',110,'대박쓰.. 팀원을 어떻게든 파괴시키려는','2023-02-15 09:48:48'),(307,'consultant01',109,'스마트 쇼퍼','2023-02-15 09:49:37'),(308,'consultant01',108,'짱짱','2023-02-15 09:50:01'),(309,'consultant01',105,'조으다 조으다','2023-02-15 09:50:49'),(310,'consultant01',104,'짱짱걸.. 걷다 보면 생각도 정리되고 좋아요! 에브리데이 만보 도전~','2023-02-15 09:51:36'),(311,'consultant01',100,'비냉은 근본이 없다는 건가요? ㅋ','2023-02-15 09:52:01'),(312,'consultant01',95,'곳곳에 남성 팬들 포진한 규성님','2023-02-15 09:52:31'),(313,'consultant01',92,'능력자','2023-02-15 09:52:45'),(314,'consultant01',92,'코딩도 잘하는데 빵도 잘굽다니.. 뤼스펙','2023-02-15 09:53:07'),(315,'consultant01',112,'오 잼나겠당','2023-02-15 09:53:36'),(316,'consultant01',126,'신중상화','2023-02-15 09:54:14'),(317,'consultant01',129,'무한루프?','2023-02-15 09:54:43'),(318,'consultant01',131,'시간이 되서 운동했는지 궁금하네요..','2023-02-15 09:55:21'),(319,'consultant01',144,'힘내요!','2023-02-15 09:55:56'),(321,'consultant01',152,'그 변화 꼭 있길','2023-02-15 09:57:18'),(322,'consultant01',153,'오 열정님 정말 멋지네요.. 저도 지금을 즐기는 게 참 어렵던데.. 노력해야겠어요','2023-02-15 09:58:19'),(323,'consultant01',154,'계단오르기 강추~','2023-02-15 09:58:45'),(324,'consultant01',155,'어디서라도 시작하는 게~!! ㅋ','2023-02-15 09:59:08'),(325,'consultant01',156,'오 정말 귀엽당','2023-02-15 09:59:22'),(326,'dnjstjr',150,'버그 수정중입니다.!!!!','2023-02-15 10:11:38'),(327,'red4',143,'네','2023-02-15 10:11:49'),(328,'kakao2665359733',159,'그죠 진짜 하면 별거 아닌데ㅋㅋㅋ','2023-02-15 10:40:14'),(329,'google117680526052635333924',159,'저도 방금 하고왔습니다!','2023-02-15 10:41:32'),(330,'david',159,'\"더 자주\"라는 말에서 ?열정?이 느껴집니다!! 임연수님이 자녀라서 부모님께서 얼마나 행복해하실까...!!!','2023-02-15 10:55:07'),(331,'red4',159,'새벽 3시에 전화했다가 맞았습니다 ㄱㅅ요','2023-02-15 10:56:52'),(332,'google105453065459048699210',159,'저도 아버지한테 전화 한번 드려야겠네요..!','2023-02-15 10:59:01'),(333,'sanghwa1103',159,'저도 부모님한테 못해도 3일에 한번은 연락드리고 있는데 너무 좋아하십니다','2023-02-15 11:04:53'),(334,'consultant01',160,'임재범님  누군지  궁금합니다','2023-02-15 11:17:12'),(336,'consultant01',150,'짱짱맨','2023-02-15 11:18:05'),(337,'consultant01',159,'서비스도 만들고 효도도 하고~~','2023-02-15 11:19:00'),(338,'red4',160,'어찌 합~~~~~니까~~~\n','2023-02-15 11:26:31'),(339,'dnjstjr',161,'그대의 노고에 치얼스','2023-02-15 11:33:06'),(340,'david',161,'오늘도 감사한 하루를 보내시는 red님 멋집니다','2023-02-15 11:44:34'),(341,'red4',162,'저게 태양이에요? 달인지 아럿내 ㅋ','2023-02-15 12:37:25'),(342,'dnjstjr',131,'그저께는 UCC 찍느라 못해서 어제부터 시작했습니다!!','2023-02-15 13:03:09'),(344,'google105453065459048699210',162,'오 저도 이거 점심시간에 소소한 행복이에요','2023-02-15 13:12:46'),(345,'google105453065459048699210',161,'취한다~','2023-02-15 13:13:24'),(346,'consultant01',163,'코미디언..ㅋ','2023-02-15 13:22:49'),(347,'consultant01',162,'갬성가득 9팀','2023-02-15 13:23:07'),(348,'consultant01',161,'화목하고 재미있게 프로젝트 하고 있는 9팀 모든 팀원에게 감사합니다','2023-02-15 13:24:05'),(349,'consultant01',157,'주인님과 닮은 거 같기도','2023-02-15 13:24:49'),(350,'red4',163,'사진이 눕네요? 수정했습니다','2023-02-15 13:44:02'),(351,'dnjstjr',166,'역시 배운사람이라 다르네','2023-02-15 15:10:28'),(353,'red4',176,'내려가서 찍은 거 가튼디요!?!','2023-02-15 16:27:18'),(355,'dnjstjr',178,'BTS 노래는 진짜 조은것같아여','2023-02-15 16:54:31'),(356,'kakao2667268530',178,'방탄방탄방방탄','2023-02-15 17:00:28'),(357,'kakao2665359733',176,'저랑도 한번 등반 가시져!!','2023-02-15 17:01:25'),(358,'kakao2665359733',178,'지민조아','2023-02-15 17:02:31'),(359,'red4',179,'좋네요','2023-02-15 17:03:05'),(362,'wjdtn',177,'사이트 디자인이 이쁩니다','2023-02-16 08:45:50'),(363,'kakao2668092198',161,'그만.','2023-02-16 08:49:24'),(364,'kakao2668092198',135,'캬','2023-02-16 08:50:15'),(365,'kakao2668092198',181,'자제하세요','2023-02-16 08:51:09'),(366,'Sangwha',181,'개발자로서 최고의 장점이네요','2023-02-16 09:25:05'),(367,'aaaa',182,'수많은 이상화들의 향욘','2023-02-16 10:26:04'),(368,'red4',190,'테스형입니다','2023-02-16 10:28:55'),(370,'dnjstjr',190,'아~ 테스형! 세상이 왜이래~','2023-02-16 10:31:52'),(371,'dnjstjr',181,'저도 자기전에 침대 누워서 쓸데없는 상상을 하곤 하는데 가끔 번뜩이는 아이디어가 생각날 때가 있습니다','2023-02-16 10:32:42'),(372,'aaaa',191,'1','2023-02-16 10:35:03'),(373,'aaaa',191,'2','2023-02-16 10:35:08'),(374,'aaaa',191,'3','2023-02-16 10:35:09'),(375,'aaaa',191,'4','2023-02-16 10:35:10'),(376,'aaaa',191,'5','2023-02-16 10:35:11'),(377,'aaaa',191,'6','2023-02-16 10:35:13'),(378,'aaaa',191,'7','2023-02-16 10:35:18'),(379,'aaaa',191,'8','2023-02-16 10:35:21'),(380,'aaaa',191,'9\n','2023-02-16 10:35:25'),(381,'aaaa',191,'1','2023-02-16 10:35:29'),(382,'aaaa',191,'asdasdasdasdasd','2023-02-16 10:35:31'),(383,'aaaa',191,'ㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇ','2023-02-16 10:35:42'),(384,'aaaa',191,'ㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇㅁ너ㅗㅁㄴ아ㅣㅓㅇ','2023-02-16 10:35:46'),(385,'aaaa',191,'1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890','2023-02-16 10:35:55'),(386,'aaaa',191,'gg','2023-02-16 10:37:21'),(387,'aaaa',191,'213','2023-02-16 10:37:28'),(388,'aaaa',191,'213','2023-02-16 10:37:29'),(389,'aaaa',191,'1','2023-02-16 10:37:31'),(390,'aaaa',191,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','2023-02-16 10:37:40'),(393,'david2',179,'다음에 저도 같이 해요!','2023-02-16 10:59:49'),(395,'red4',198,'ㅇㅈ합니다 하하하','2023-02-16 11:12:48'),(396,'red4',192,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2023-02-16 11:14:06'),(397,'red4',192,'원석아 술 줄여라','2023-02-16 11:14:16'),(398,'red4',199,'감사하비다','2023-02-16 11:14:29'),(399,'red4',191,'20번째 댓글','2023-02-16 11:20:38'),(400,'red4',200,'삿대질 하지 마세요','2023-02-16 11:31:38'),(401,'david',200,'오 누군지 알 것 같아요 축구선수 조규성님이죠?','2023-02-16 11:41:37'),(402,'consultant01',198,'개그맨 집단 9팀','2023-02-16 12:47:52'),(403,'kakao2668425628',200,'깔깔','2023-02-16 13:21:11'),(404,'najul',202,'안녕하세요 사랑합니다 좋아합니다 감사합니다 미워합니다 증오합니다 그래도 다시 고맙습니다<h2> 콘 ?\n','2023-02-16 14:02:00'),(405,'red4',202,'세기말 감성이네요','2023-02-16 14:54:07'),(406,'google103875718875945401236',200,'웃겨요 ㅋㅋ\n','2023-02-16 14:59:00'),(407,'google103875718875945401236',202,'낭만적이네요','2023-02-16 15:05:49'),(408,'google103875718875945401236',135,'ㅐ인정킹정요\n','2023-02-16 15:14:10'),(409,'google103875718875945401236',134,'부러워요 ㅠㅠ','2023-02-16 15:14:27'),(410,'red4',203,'사진으로 해산물 파티를 해놨네','2023-02-16 15:17:20');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/  /*!50003 TRIGGER `write_comment` AFTER INSERT ON `comment` FOR EACH ROW begin
	update current_stat set comment_cnt = comment_cnt+1 where user_id = new.user_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `current_stat`
--

DROP TABLE IF EXISTS `current_stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `current_stat` (
  `current_stat_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `feed_cnt` int NOT NULL DEFAULT '0',
  `relation_cnt` int NOT NULL DEFAULT '0',
  `health_cnt` int NOT NULL DEFAULT '0',
  `happy_cnt` int NOT NULL DEFAULT '0',
  `my_like_cnt` int NOT NULL DEFAULT '0',
  `other_like_cnt` int NOT NULL DEFAULT '0',
  `replay_cnt` int NOT NULL DEFAULT '0',
  `replay_complete_cnt` int NOT NULL DEFAULT '0',
  `comment_cnt` int NOT NULL DEFAULT '0',
  `p1_cnt` int NOT NULL DEFAULT '0',
  `p2_cnt` int NOT NULL DEFAULT '0',
  `p3_cnt` int NOT NULL DEFAULT '0',
  `p4_cnt` int NOT NULL DEFAULT '0',
  `p5_cnt` int NOT NULL DEFAULT '0',
  `p6_cnt` int NOT NULL DEFAULT '0',
  `lastday_complete_cnt` int NOT NULL DEFAULT '0',
  `fifthday_complete_cnt` int NOT NULL DEFAULT '0',
  `follower_cnt` int NOT NULL DEFAULT '0',
  `following_cnt` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`current_stat_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `user_has_stat` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `current_stat`
--

LOCK TABLES `current_stat` WRITE;
/*!40000 ALTER TABLE `current_stat` DISABLE KEYS */;
INSERT INTO `current_stat` VALUES (42,'red4',16,0,0,2,1,47,0,0,48,0,0,0,0,1,1,0,0,6,28),(43,'david',7,0,1,0,7,34,0,0,11,1,0,0,0,0,0,0,0,7,10),(44,'sanghwa1103',9,0,0,0,4,15,0,0,16,0,0,0,0,0,0,0,0,4,1),(46,'kakao2546552508',6,0,0,0,1,6,0,0,2,0,0,0,0,0,0,0,0,5,5),(47,'kakao2659762741',2,0,0,0,2,3,0,0,2,0,0,0,0,0,0,0,0,2,0),(48,'kakao2659764081',0,0,0,0,0,3,0,0,2,0,0,0,0,0,0,0,0,0,0),(49,'kakao2659766131',1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,0),(50,'m0jinaa',0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0),(51,'kakao2659956095',1,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,0),(52,'kakao2659962383',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(53,'google114706080296583515621',2,0,0,0,1,1,0,0,1,0,0,0,0,0,0,0,0,3,1),(54,'kakao2661445862',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(55,'dnjstjr',8,0,0,0,3,42,0,0,21,0,0,0,0,0,0,0,0,4,21),(56,'srlee',1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,2,0),(58,'google111229672003980040556',4,0,0,0,2,18,0,0,3,0,0,0,0,0,0,0,0,4,12),(59,'test',3,0,0,0,1,3,0,0,1,0,0,0,0,0,0,0,0,2,2),(61,'mjkoo',0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,1,1),(62,'consultant01',1,0,0,0,1,4,0,0,45,0,0,0,0,0,0,0,0,4,0),(63,'coachMoon',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(64,'google117680526052635333924',0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0),(72,'google101235305113581196343',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(73,'kakao2665359733',3,0,0,0,2,3,0,0,6,0,0,0,0,0,0,0,0,6,0),(74,'seokjin',2,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,4,1),(75,'google105453065459048699210',0,0,0,0,0,4,0,0,4,0,0,0,0,0,0,0,0,2,0),(76,'google113134987904705544342',2,0,0,0,1,4,0,0,2,0,0,0,0,0,0,0,0,4,0),(79,'kakao2666807704',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(85,'kakao2667268530',0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,2,0),(86,'kakao2667268719',1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0),(87,'david3',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(88,'kakao2667295149',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(89,'kakao2667544842',0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,2,1),(90,'kakao2667545004',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1),(91,'kakao2667556782',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(92,'kakao2667571000',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1),(93,'kakao2667571192',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(94,'kakao2667600914',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(95,'wjdtn',0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0),(96,'kakao2657457735',0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0),(97,'kakao2668092198',0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,2,0),(98,'google118419579175738030682',0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,1,1),(99,'google111763154843495368059',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(100,'hello',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(101,'kakao2668118733',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(102,'Sangwha',1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0),(103,'testtest',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(104,'joney0715',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(105,'aaaa',1,0,0,0,0,2,0,0,20,0,0,0,0,0,0,0,0,1,0),(106,'google117065706544617987334',1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0),(109,'david2',1,0,0,0,0,2,0,0,1,0,0,0,0,0,0,0,0,1,0),(111,'google101134343878013224607',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(112,'kakao2668226313',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(113,'kakao2658115440',1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0),(114,'kakao2668278626',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(116,'kakao2668425628',1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0),(117,'kakao2668434935',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(118,'najul',1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,0,1,0),(120,'google103875718875945401236',1,0,0,0,0,4,0,0,4,0,0,0,0,0,0,0,0,1,0),(121,'google113316588345529428078',0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1),(122,'sanghwa',0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
/*!40000 ALTER TABLE `current_stat` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;

DELIMITER ;;
/*!50003 CREATE*/  /*!50003 TRIGGER `update_current_stat` AFTER UPDATE ON `current_stat` FOR EACH ROW begin
    drop temporary table if exists my_achievement;
    
	create temporary table my_achievement
    select achievement_id from user_achievement
    where user_id = new.user_id;
# 첫 미션
	if 1 not in (select achievement_id from my_achievement) and new.feed_cnt = 1 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 1, now());
	end if;
    
# 첫 댓글
	if 2 not in (select achievement_id from my_achievement) and new.comment_cnt = 1 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 2, now());
    end if;

# 내 게시글에 처음 좋아요
	if 3 not in (select achievement_id from my_achievement) and new.my_like_cnt = 1 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 3, now());
    end if;
    
# 소소하고 확실한 운동 플레이리스트 2번
	if 4 not in (select achievement_id from my_achievement) and new.p5_cnt = 2 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 4, now());
	end if;

# 소소하고 확실한 운동 플레이리스트 5번
	if 5 not in (select achievement_id from my_achievement) and new.p5_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 5, now());
	end if;

# 운동습관 만들기 플레이리스트 2번
	if 6 not in (select achievement_id from my_achievement) and new.p6_cnt = 2 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 6, now());
	end if;

# 운동습관 만들기 플레이리스트 5번
	if 7 not in (select achievement_id from my_achievement) and new.p6_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 7, now());
	end if;    

# 건강 카테고리 5개
	if 8 not in (select achievement_id from my_achievement) and new.health_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 8, now());
    end if;
  
# 부모님과 더 가까워지기 플레이리스트 2번
	if 9 not in (select achievement_id from my_achievement) and new.p1_cnt = 2 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 9, now());
	end if;

# 부모님과 더 가까워지기 플레이리스트 5번
	if 10 not in (select achievement_id from my_achievement) and new.p1_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 10, now());
	end if;

# 관계가꾸기 플레이리스트 2번
	if 11 not in (select achievement_id from my_achievement) and new.p2_cnt = 2 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 11, now());
	end if;

# 관계가꾸기 플레이리스트 5번
	if 12 not in (select achievement_id from my_achievement) and new.p2_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 12, now());
	end if;

# 관계 카테고리 5개
	if 13 not in (select achievement_id from my_achievement) and new.relation_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 13, now());
    end if;

# 나 돌아보기 플레이리스트 2번
	if 14 not in (select achievement_id from my_achievement) and new.p3_cnt = 2 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 14, now());
	end if;

# 나 돌아보기 플레이리스트 10번
	if 15 not in (select achievement_id from my_achievement) and new.p3_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 15, now());
	end if;

# 일상에서 행복찾기 플레이리스트 2번
	if 16 not in (select achievement_id from my_achievement) and new.p4_cnt = 2 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 16, now());
	end if;

# 일상에서 행복찾기 플레이리스트 5번
	if 17 not in (select achievement_id from my_achievement) and new.p4_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 17, now());
	end if;
    
# 행복 카테고리 5개
	if 18 not in (select achievement_id from my_achievement) and new.happy_cnt = 5 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 18, now());
    end if;
  
# 각 카테고리 1개 이상
	if 19 not in (select achievement_id from my_achievement) and new.health_cnt >= 1 and new.relation_cnt >= 1 and new.happy_cnt >= 1 then
		insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 19, now());
    end if;

# 팔로우 팔로워 각각 10명 이상
	if 20 not in (select achievement_id from my_achievement) and new.follower_cnt >= 10 and new.following_cnt >= 10 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 20, now());
    end if;
    
# 피드 100개
	if 21 not in (select achievement_id from my_achievement) and new.feed_cnt = 100 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 21, now());
    end if;

    # 댓글 5개
	if 22 not in (select achievement_id from my_achievement) and new.comment_cnt = 5 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 22, now());
    end if;

# 다른 사람 게시글에 처음 좋아요
	if 23 not in (select achievement_id from my_achievement) and new.other_like_cnt = 1 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 23, now());
    end if;

# 다른 사람 게시글에 좋아요 10번
	if 24 not in (select achievement_id from my_achievement) and new.other_like_cnt = 10 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 24, now());
    end if;

# 다른 사람 게시글에 좋아요 100번
	if 25 not in (select achievement_id from my_achievement) and new.other_like_cnt = 100 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 25, now());
    end if;
    
# 실패한 플레이리스트 재시도
	if 26 not in (select achievement_id from my_achievement) and new.replay_cnt = 1 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 26, now());
    end if;
    
# 실패한 플레이리스트 첫 성공
	if 27 not in (select achievement_id from my_achievement) and  new.replay_complete_cnt = 1 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 27, now());
    end if;

# 실패한 플레이리스트 10번 성공
	if 28 not in (select achievement_id from my_achievement) and new.replay_complete_cnt = 10 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 28, now());
    end if;

# 첫 마지막날 플레이리스트 클리어
	if 29 not in (select achievement_id from my_achievement) and new.lastday_complete_cnt = 1 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 29, now());
    end if;

# 첫 5번째날 플레이리스트 클리어
	if 30 not in (select achievement_id from my_achievement) and new.fifthday_complete_cnt = 1 then
    insert into user_achievement(user_id, achievement_id, achieved_date) values (new.user_id, 30, now());
    end if;
    
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed` (
  `feed_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `user_playlist_id` bigint NOT NULL,
  `mission_id` bigint NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`feed_id`),
  KEY `feed_has_mission_name_idx` (`mission_id`),
  KEY `user_writes_feed_idx` (`user_id`),
  KEY `feed_has_playlist_id_idx` (`user_playlist_id`),
  KEY `feed_has_category_idx` (`category_id`),
  CONSTRAINT `feed_has_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `feed_has_mission_id` FOREIGN KEY (`mission_id`) REFERENCES `mission` (`mission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `feed_has_playlist_id` FOREIGN KEY (`user_playlist_id`) REFERENCES `user_playlist` (`user_playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_writes_feed` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (91,'red4',76,21,'잼잇는 움잘을 보면 잼잇거든요?','2023-02-10 17:45:13',3),(92,'sanghwa1103',77,30,'빵 맛있게 잘굽습니다','2023-02-10 17:53:25',3),(95,'red4',82,30,'아무도 몰랐겠지만 저는 키가 큽니다.\n그게 장점이에요;','2023-02-10 17:59:58',3),(99,'kakao2659762741',83,10,'다음주 월요일부터 6시에 일어나서 운동 다시 시작하기 \n30분 뛰기, 10분 스트레칭','2023-02-10 20:42:36',1),(100,'kakao2659766131',84,4,'비냉이 맛있네요','2023-02-10 20:44:50',1),(103,'kakao2659956095',85,7,'좋았다.','2023-02-10 22:58:50',1),(104,'kakao2546552508',86,4,'조깅은 아니고, 아침 저녁으로 출퇴근할 때 약 30분씩 걸어다니고있어요. 아침에 잠 깨우는 용도로 딱이에요??','2023-02-10 23:47:57',1),(105,'red4',82,29,'지금 B형 특강을 들으면서 피곤하고 졸리고 밥도 못 먹어서 배고프고 머리도 아픈데 그냥 일단 감사합니다!','2023-02-11 12:23:05',3),(106,'david',81,1,'오늘 좀 많이 걸었네요ㅎㅎ 소화가 너무 잘 되어 배고파서 저녁까지 못 참고 케잌 먹었습니다...?','2023-02-11 17:56:24',1),(107,'red4',76,24,'헤비메탈은 스트레스를 날려준답니다. \n피이이이쓰-!','2023-02-11 22:05:46',3),(108,'google114706080296583515621',87,21,'오늘은 b형 특강을 통해서 오랜만에 a형 시험 때 만났던 문제를 다시 만났다. 어떻게 예전에 풀었을까 생각하면서 다시 풀었는데 오히려 예전에 좀 더 기발하게 풀었다는 걸 알고 재미있었다! 예전에 나 좀 잘한 듯! ㅋㅋㅋ','2023-02-11 22:07:59',3),(109,'kakao2546552508',88,21,'냉장고에 자리가 없었는데 오늘 장 본 재료들을 넣기위해 다 꺼내서 테트리스처럼 끼워맞추기 하다가 문 닫았을 때 잘 닫혀서..?ㅋㅋㅋㅋㅋㅋㅋ 이젠 정말 냉장고 비우기 전까지는 쇼핑 금지❌❌','2023-02-11 23:58:27',3),(110,'kakao2546552508',86,5,'집이 4층인데 엘베 없어서 쓰레기 버리러 갈 때마다 강제 운동중','2023-02-11 23:59:50',1),(112,'kakao2659762741',83,9,'요즘 본디를 시작했는데 귀여워서 좋네요 ㅎㅎ','2023-02-12 15:32:59',1),(119,'dnjstjr',91,1,'10분은 너무 짧아요 30분 정도가 적당하네요 ^^','2023-02-12 15:46:13',1),(120,'dnjstjr',92,18,'오늘도 외쳐봅니다. 절절포!!','2023-02-12 15:46:47',2),(121,'david',81,4,'열정을 다 쏟기에 10분은 부족했습니다?\n그거 아시나요? 마냥 누워 쉬는 것보다 지쳐도 운동을 하고 나면 다음날 컨디션이 더 좋더라구요!\n건강 성장 플랜 실천하시는 모든 분들 화이팅입니다!!','2023-02-12 18:03:07',1),(122,'kakao2546552508',86,1,'밥 먹고 아이스크림이 먹고 싶어서 산책한다 생각하고 다녀왔어요.','2023-02-12 23:58:13',1),(123,'srlee',93,5,'8시 30분이 지나면 엘리베이터 잡기가 쉽지가 않아요 ? 오늘도 코치실에 두고 온 물건이 있어 열심히 다섯층을 걸어 올라갔습니다 아침부터 허벅지 파괴당해버리기~~~ ????','2023-02-13 09:14:29',1),(124,'red4',82,28,'그냥 낯을 가려서 말이 없는 건데 어른들은 과묵하다며 좋아하십니다. \n낯 가리는 건 단점이 아니게 돼버렸습니다.','2023-02-13 10:05:58',3),(125,'google111229672003980040556',94,23,'저는 힘들 때 밖에 나가 달리기를 합니다.\n친구들과 열심히 달리다보면 \n모든 걱정과 근심을 덜 수 있답니다.','2023-02-13 10:07:31',3),(126,'sanghwa1103',77,28,'군대 얘기라 좀 그렇긴 한데, 과할 정도로 걱정이 많은 성격이 군대에선 신중한 성격으로 받아들여져서 좋았었음','2023-02-13 10:27:09',3),(127,'red4',76,21,'가끔 혼자 망상을 하다 보면 나도 모르게 웃음이 새어 나오죠.. 오늘은 어떤 생각을 했을까요? 부자가 되면 하고 싶은 일들..? 프로젝트 우수상을 받는다면 어떤 말을 해야 할지?! 으흐흐..','2023-02-13 10:33:42',3),(128,'red4',95,18,'고백 성공','2023-02-13 13:02:51',2),(129,'dnjstjr',91,1,'밥먹고 산책하면 잠도 깨고 상쾌해서 기분이 좋아져요 근데 들어오면 졸리다는게 문제,,','2023-02-13 15:01:46',1),(130,'dnjstjr',92,19,'커피가 있었는데요. 예, 지금은 없습니다.','2023-02-13 16:47:31',2),(131,'dnjstjr',99,10,'오늘 저녁이 시간이 된다면 꼭 운동을 하도록 하겠습니다. ','2023-02-13 16:49:29',1),(133,'david',81,5,'오늘은 계단을 올라가봤습니다! 비상계단에 큼지막한 유리창들이 있어서 가슴도 뻥뚫리는 기분이었네요?\n계단 내려가기는 무릎에 좋지 않다고 하는데, 그것까지 고려해서 계단 올라가기 미션으로 정하신 것 같다는 생각이 드네요... 배려심도 짱입니다 모티!!!','2023-02-13 16:59:57',1),(134,'dnjstjr',100,14,'부모님 모시고 오마카세 한번 다녀가야겠습니다. 그리고 집밥은 항상 맛있습니다.','2023-02-13 17:00:39',2),(135,'dnjstjr',102,21,'싸피 조규성과 함께하는 프로젝트. 행복할 수 밖에 없지 않아요?','2023-02-13 17:03:18',3),(136,'google114706080296583515621',87,23,'맞춤법을 수정하려고 수정을 눌렀다가 원래 글이 기본으로 나타나지 않아 맞춤법을 고칠 수가 없었어요 흑흑 결국 원본 글은 다 날려먹고 다시 쓰는 중~~ 다른 팀 서비스를 사용해보고 사용 후기를 남기는 게 소소한 행복!','2023-02-13 18:22:48',3),(137,'kakao2546552508',86,1,'오랜만에 10000보 넘기고 집와서\n야밤에 먹는 요거트는 맛있다. \n사실 집 오다가 맥날 아이스크림? 먹을까하다가 손시려서 포기했는데 집 오니까 아쉽??','2023-02-13 23:20:09',1),(138,'consultant01',109,5,'계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다계단으로 올라가보니 확실한 운동효과가 있습니다','2023-02-14 10:58:40',1),(139,'red4',101,4,'역삼에서 자고 출근하는 길에 시원한 바람 맞으며 테헤란로를 걸으니 좋네요. 물론 집 가는 길도 아녔고 조깅도 아녔지만요! 으하하!','2023-02-14 11:19:57',1),(141,'red4',76,23,'사진에게는 자기 멋대로 돌아갈 권리가 없다','2023-02-14 11:40:30',3),(142,'dnjstjr',92,19,'바닐라 콜드브루를 마시고 싶었는데 실수로 돌체 콜드브루를 시켜버렸습니다. 근데 맛있네요. 뜻밖의 개이득?','2023-02-14 11:43:49',2),(143,'david',81,2,'안동찜닭을 먹으니 힘이 불끈 나서 힘을 주체하지 못했습니다... 후우\n강해진 팔힘에 키보드가 고장나지는 않을까 걱정이네요?','2023-02-14 12:18:12',1),(144,'test',112,5,'힘들었어요','2023-02-14 12:44:53',1),(147,'sanghwa1103',115,19,'사실\n저는 커피를\n잘 못마셔서\n누가 커피를 사준다하면\n항상 커피가 아니라 차를 얻어 마십니다\n여러분은 \n어떠하신가요?','2023-02-14 13:11:29',2),(149,'red4',95,20,'아이가 좋아하네요.','2023-02-14 13:57:02',2),(150,'google111229672003980040556',94,23,'버그를 찾아요','2023-02-14 16:23:19',3),(152,'seokjin',123,6,'운동을 하면 제 삶에 여자친구가 생기는 변화가 있지 않을지 ㅎㅎ','2023-02-14 17:34:31',1),(153,'david',120,23,'지금 이 순간을 충분히 누리는 것(enjoy). 그것이 제 행복의 비결 같습니다. 우리는 종종 내일, 일주일, 한 달, 일 년... 미래를 걱정하며 지금의 행복을 놓치곤 합니다. \n\n내일 일은 내일 염려하고, 지금 먹는 맛있는 점심, 따뜻한 햇살, 가족과의 대화를 즐겨보면 어떨까요?','2023-02-14 17:59:41',3),(154,'kakao2665359733',124,10,'내일 오후에 싸피계단 등반 한번 갑니다. 열쩡열쩡열쩡~~','2023-02-14 20:32:42',1),(155,'kakao2665359733',126,3,'방 바닥 가기도 귀찮아서 침대에서 했습니다ㅎㅎ; 그래도 진짜 오랜만에 코어 쓰네요ㅋㅋㅋ','2023-02-14 20:35:29',1),(156,'google113134987904705544342',127,21,'진짜 우리 정코코 보실래요..?\n아 너무 귀여워 사랑과 평화 피쓰~\n?','2023-02-14 21:56:10',3),(157,'google113134987904705544342',128,7,'저의 정코코님과 함께 운동~?','2023-02-14 21:59:35',1),(159,'seokjin',129,12,'생각해보면 진짜 별거 아닌데 부모님이 되게 좋아하시더라구요. 더 자주 해야겠어요..!','2023-02-15 10:36:07',2),(160,'sanghwa1103',115,18,'서울 5반 9팀 화이팅! ','2023-02-15 11:07:58',2),(161,'red4',82,29,'매일 사냥 실패에 따른 아사 걱정을 하지 않아도 되는 현대 문명에 감사합니다.\r\nSSAFY 교육을 받을 수 있음에 감사합니다.\r\n맛있는 점심을 만들어주시는 식당 직원분들께 감사합니다.\r\n컨설턴트님 프로님 코치님들 도와주셔서 감사합니다.\r\n프로젝트에 누구보다 열심히 임해주는 팀원들 감사합니다.','2023-02-15 11:30:34',3),(162,'david',120,22,'?1석2조 = 양치 + 광합성?','2023-02-15 12:27:35',3),(163,'red4',76,23,'바나프레소 오늘의 운세(마음의 소리) 보는 것!!','2023-02-15 12:51:04',3),(166,'google111229672003980040556',94,22,'햇빛은 비타민D 를 합성할 수 있게 해준다.\n비타민D는 칼슘과 함께 뼈를 강화해주는 대표적인 영양소이다.\n뼈는 치킨이다.','2023-02-15 15:08:40',3),(176,'david',81,5,'다섯 층으로는 부족하네요?','2023-02-15 16:20:18',1),(177,'sanghwa1103',98,5,'저도 해봤습니다, 시간도 얼마 안걸리고 잠도 깨서 좋아요!','2023-02-15 16:44:23',1),(178,'kakao2667268719',139,24,'방탄방탄방방탄 봄날에는 봄날 들을 때 행복해요?','2023-02-15 16:45:54',3),(179,'kakao2665359733',126,5,'어제 계획한 싸피 계단 산악회 완수!???','2023-02-15 17:00:07',1),(181,'red4',82,30,'남들이 생각하기에 쓸 데 없어 보이는 상상을 많이 하는데요, 가끔은 좋은 아이디어가 떠오르기도 합니다.','2023-02-16 08:40:29',3),(182,'Sangwha',144,21,'프로젝트 마지막 날이라 신난다~~','2023-02-16 09:24:31',3),(190,'test',112,4,'테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스트입니다테스','2023-02-16 10:28:15',1),(191,'aaaa',150,23,'와 어렵다','2023-02-16 10:30:40',3),(192,'google117065706544617987334',152,6,'나를 힘들게 만들었습니다','2023-02-16 10:46:05',1),(194,'david2',153,1,'점심을 먹고 산책하니 소화가 잘 되는군요!','2023-02-16 10:54:59',1),(198,'google111229672003980040556',94,21,'오늘은 버그를 한 번 찾아 보겠습니다.\n\n우주적인 관점에서 봤을 때 우리의 삶은 찰나에 불과합니다.\n\n걱정 근심 불안에 에너지를 낭비하기엔 시간이 너무 없습니다.\n\n다들 웃어봅시다\n하하하','2023-02-16 11:09:22',3),(199,'kakao2658115440',157,18,'모티 폼 미쳤다\n온보딩 보고 지렸다고 할 뻔\n이렇게 잘 만드시면 상대적 박탈감이 느껴집니다.\n자제 부탁합니다.','2023-02-16 11:12:54',2),(200,'kakao2546552508',88,21,'영상편집하다가 주연분 때문에 웃었습니다.\n얼굴은 모자이크 해드렸어요??','2023-02-16 11:30:03',3),(201,'kakao2668425628',159,19,'병아리~','2023-02-16 13:19:49',2),(202,'najul',160,22,'세상의 끝에서 사랑을 외치고 싶어요','2023-02-16 14:00:27',3),(203,'google103875718875945401236',162,29,'안녕하세요, 제 이름은 해피예요\n\n저는 평소에 감사함을 별로 느끼지 않는데요,\n일상의 소중함을 되새기고자 가입했어요\n\n생각해보면 주변에 감사할 것이 참 많아요\n저는 무너를 좋아하는데 무너도 귀엽고, 쭈꾸미는 맛있고,  오징어는 바다에 살아요\n\n뽀삐 초코 보고싶어요 이 글을 발견한다면 당장! 14층으로 오세요\n그럼 이만 ','2023-02-16 15:09:45',3);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `write_feed` AFTER INSERT ON `feed` FOR EACH ROW begin
	update user_playlist set done = done+1, today = 1 where user_id = new.user_id and user_playlist_id = new.user_playlist_id;
    if(select done from user_playlist where user_id = new.user_id and user_playlist_id = new.user_playlist_id) = 5 then
		update user_playlist set end_date = now() where user_id = new.user_id and user_playlist_id = new.user_playlist_id;
	end if;
    
	update current_stat set feed_cnt = feed_cnt+1 where user_id = new.user_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `feed_image`
--

DROP TABLE IF EXISTS `feed_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed_image` (
  `feed_image_id` bigint NOT NULL AUTO_INCREMENT,
  `feed_id` bigint NOT NULL,
  `feed_image_url` text,
  PRIMARY KEY (`feed_image_id`),
  KEY `feed_has_file_idx` (`feed_id`),
  CONSTRAINT `feed_has_file` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed_image`
--

LOCK TABLES `feed_image` WRITE;
/*!40000 ALTER TABLE `feed_image` DISABLE KEYS */;
INSERT INTO `feed_image` VALUES (57,91,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/d6f2c0a7-8233-489d-bc38-af0b336b25fa'),(58,92,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/0c4f28de-2bc0-460c-a9f4-ab76978e932f'),(70,100,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/95086760-c86b-4c27-93dc-a4bf5b932861'),(73,104,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/5e851b84-5b3b-4068-b479-e704d60c2689'),(74,106,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/d469a45f-3bb3-4088-b169-9ab8411f5795'),(75,107,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/34789166-584c-4d91-bd33-d99d4979cb5e'),(76,112,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/9628f7de-5f66-4533-b690-246c295b777f'),(105,119,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/1309b367-8584-4799-aa38-0c0e158f4996'),(106,120,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/4cc1c982-2468-4ba4-8729-40ada90b125c'),(107,121,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/98ab4088-8046-4b1e-a04a-0bc76aea67b8'),(108,122,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/d2ee7896-bd1c-4422-bb63-33534b301740'),(109,125,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/cd74385d-5cc5-4478-836c-3c89fbab8f73'),(110,128,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/4d658eff-4a17-42a8-9944-264f59b53bd7'),(111,130,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/b818404c-38bc-4665-8cac-fe4895f09fd6'),(112,131,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/9939e2fb-2594-4b8a-abf3-85bfae3d699a'),(114,133,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/4cdaec95-26bf-46a9-8bee-a12cdf205dc1'),(115,133,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/5cb997e6-0274-4df0-b960-27b050cb8b4f'),(116,134,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/b5d30a3e-a111-4f1d-a9ed-b3901d356548'),(117,134,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/cf1b71c3-122b-4e1e-91ca-896bdd22f5e7'),(118,134,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/581a3084-2f99-4d2f-8914-917e6f1776ba'),(119,134,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/f0bcd9e5-5657-49a6-aeca-40c2ef63a974'),(120,134,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/fd560f13-8a75-4798-9e17-7685c660a1e5'),(121,134,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/12ef9479-c790-4940-9383-9f8537621efa'),(122,135,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/bbc77182-0426-4c1f-b2c8-bc8f5dc3d373'),(123,137,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/83ea7973-e84d-4b77-bd97-6f8882786324'),(124,137,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/00d03e1b-0272-4a4b-b221-fee7eb8f965e'),(125,141,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/b99300d3-1015-4ba1-9f79-b1d3575c86c9'),(126,142,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/3c0081bc-9623-4d2e-9afc-88ee880bd3b9'),(127,143,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/368c293b-05c7-4325-b549-8e9f845fcf69'),(128,144,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/38895ef5-83ef-4b31-96c4-d2cf07ac7d95'),(129,149,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/baba9724-1b71-4c65-a661-2e9a6f26c71e'),(134,154,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/151f1202-836e-42d6-a9e3-529f9cee5f5c'),(135,156,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/c51acf4d-03ea-4ff3-a3e1-af6ad78bda9e'),(136,157,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/5faa3044-83ad-4b50-9240-66bf59812aac'),(137,162,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/92a30af0-ebac-4be3-bbe7-45665e987b63'),(138,163,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/496418cf-1531-40a6-aa76-5c243c5345c9'),(141,166,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/c3ba5f61-d0f8-4233-8c53-c15e7a53778b'),(182,176,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/ab690526-d4bd-40ab-ab60-c4a5e9a1ace7'),(183,176,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/06f5c8b8-7f98-40f0-9d2a-9942356f428f'),(184,177,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/c1a99b07-5735-4c40-a439-cf6b6ccd8462'),(185,178,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/27e8189c-bf26-4911-9bbd-1a641e1009b5'),(186,179,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/5fe66e55-38de-4408-96fa-992e460443d2'),(195,190,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/0f083c94-b818-4cee-aa5c-5ab89b39e099'),(196,191,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/cac2670b-41c1-4ecb-9a71-03df4bd4c973'),(198,194,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/b533feb3-9555-49a8-95cf-d9a02597cb71'),(200,198,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/7a4eafd5-7e19-4b00-b3d6-7a63063938e6'),(201,199,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/6c1d0f3f-e655-414d-8b7a-33f442766342'),(202,200,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/ae452a41-28a5-4f90-ac23-5ba0060bef83'),(203,201,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/229edae0-4e4e-4c32-8107-8d7f9fd96f4f'),(204,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/dd85d7de-c844-4e64-8989-5c08fba58dd4'),(205,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/3afb768b-795f-4b15-a745-c86ce9ad0ac4'),(206,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/7582174d-7e72-48be-b95e-fa5efe3734ec'),(207,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/8fa27470-9d21-4453-9f9c-9a7f0898e2e8'),(208,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/0b61bd0a-d3b5-4871-8fe6-19776ace7fde'),(209,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/051fea2d-06d8-4103-ace3-16512a1feebb'),(210,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/8e8dcb9a-b933-4cd0-9cb1-441f50216fe9'),(211,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/9b994dcc-5ace-483c-bcc0-89cfb674a069'),(212,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/0204288d-f172-42bb-b96d-164d11e2dce5'),(213,203,'https://motiimages.s3.ap-northeast-2.amazonaws.com/feedImages/00224f34-57c6-4371-8dfd-f77494d03301');
/*!40000 ALTER TABLE `feed_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flower`
--

DROP TABLE IF EXISTS `flower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flower` (
  `flower_id` bigint NOT NULL AUTO_INCREMENT,
  `flower_image_url` text,
  PRIMARY KEY (`flower_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flower`
--

LOCK TABLES `flower` WRITE;
/*!40000 ALTER TABLE `flower` DISABLE KEYS */;
INSERT INTO `flower` VALUES (1,'https://motiimages.s3.ap-northeast-2.amazonaws.com/flowerImages/1d0cd7b8-52ac-4631-8057-51983b5b1ae4snowdrop_%EA%B1%B4%EA%B0%95_A.png'),(2,'https://motiimages.s3.ap-northeast-2.amazonaws.com/flowerImages/1ae37d8f-a76f-43e6-84ea-eebef03eb22aixora_%EA%B1%B4%EA%B0%95_B.png'),(3,'https://motiimages.s3.ap-northeast-2.amazonaws.com/flowerImages/2804b93d-99bc-4908-8a26-4a6f986e0904lavender_%EA%B4%80%EA%B3%84_A.png'),(4,'https://motiimages.s3.ap-northeast-2.amazonaws.com/flowerImages/e052699a-0247-41b9-9530-a2dbdfa7f71etulip_%EA%B4%80%EA%B3%84_B.png'),(5,'https://motiimages.s3.ap-northeast-2.amazonaws.com/flowerImages/343835dc-1d7e-4975-b780-5b42814d6df5iris_%ED%96%89%EB%B3%B5_A.png'),(6,'https://motiimages.s3.ap-northeast-2.amazonaws.com/flowerImages/bc635197-9732-4d82-b5f6-b99eb126fba8sunflower_%ED%96%89%EB%B3%B5_B.png'),(7,'4');
/*!40000 ALTER TABLE `flower` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `follow_id` bigint NOT NULL AUTO_INCREMENT,
  `follower_id` varchar(30) NOT NULL,
  `follower_nickname` varchar(30) NOT NULL,
  `following_id` varchar(30) NOT NULL,
  `following_nickname` varchar(30) NOT NULL,
  PRIMARY KEY (`follow_id`),
  KEY `user_followed` (`follower_id`),
  KEY `user_follow` (`following_id`)
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (81,'red4','red','david','열정불끈'),(83,'red4','red','kakao2546552508','맹지니'),(84,'red4','red','kakao2659956095','배림'),(85,'kakao2546552508','맹지니','red4','red'),(86,'red4','red','google114706080296583515621','Sangwoo'),(87,'google114706080296583515621','Sangwoo','red4','red'),(88,'kakao2546552508','맹지니','google114706080296583515621','Sangwoo'),(89,'kakao2546552508','맹지니','david','열정불끈'),(117,'red4','red','kakao2659762741','호두우'),(118,'dnjstjr','wonseok2','kakao2659762741','호두우'),(119,'red4','red','srlee','바보카도'),(120,'dnjstjr','wonseok2','srlee','바보카도'),(121,'dnjstjr','wonseok2','google111229672003980040556','이봉주'),(122,'google111229672003980040556','이봉주','red4','red'),(123,'red4','red','google111229672003980040556','이봉주'),(124,'red4','red','dnjstjr','wonseok2'),(125,'red4','red','sanghwa1103','이상화123'),(126,'sanghwa1103','이상화123','red4','red'),(127,'red4','red','kakao2659766131','한원석'),(133,'dnjstjr','wonseok2','kakao2659766131','한원석'),(134,'david','열정불끈','red4','red'),(136,'david','열정불끈','dnjstjr','wonseok2'),(137,'red4','red','test','테스트계정'),(141,'mjkoo','깨굴','kakao2546552508','맹지니'),(142,'red4','red','consultant01','컨설턴트'),(144,'dnjstjr','wonseok2','consultant01','컨설턴트'),(145,'dnjstjr','wonseok2','mjkoo','깨굴'),(153,'dnjstjr','wonseok2','google114706080296583515621','Sangwoo'),(170,'dnjstjr','wonseok2','kakao2546552508','맹지니'),(171,'dnjstjr','wonseok2','red4','red'),(172,'seokjin','temptemp','kakao2546552508','맹지니'),(173,'red4','red','seokjin','임연수'),(174,'dnjstjr','wonseok2','kakao2659956095','배림'),(177,'dnjstjr','wonseok2','seokjin','임연수'),(178,'david','열정불끈','google113134987904705544342','❤️youngzero❤'),(179,'red4','red','google113134987904705544342','❤️youngzero❤'),(180,'red4','red','kakao2665359733','모카'),(181,'red4','red','google105453065459048699210','서나무'),(182,'dnjstjr','wonseok2','test','테스트계정'),(183,'dnjstjr','wonseok2','google113134987904705544342','❤️youngzero❤'),(184,'dnjstjr','wonseok2','kakao2665359733','밍밍이'),(185,'dnjstjr','wonseok2','google105453065459048699210','동길홍'),(186,'dnjstjr','wonseok2','google117680526052635333924','제주도귤왕'),(188,'dnjstjr','wonseok2','david','열정불끈'),(189,'red4','red','kakao2667268719','쥬엉'),(190,'dnjstjr','wonseok2','kakao2667268719','쥬엉'),(192,'red4','red','kakao2667268530','흑흑'),(193,'dnjstjr','wonseok2','kakao2667268530','흑흑'),(194,'david','열정불끈','kakao2665359733','밍밍이'),(195,'david','열정불끈','kakao2659766131','한원석'),(196,'david','열정불끈','consultant01','컨설턴트'),(197,'david','열정불끈','seokjin','임연수'),(198,'kakao2667545004','kakao2667545004','david','열정불끈'),(199,'kakao2667544842','kakao2667544842','david','열정불끈'),(200,'kakao2667571000','kakao2667571000','david','열정불끈'),(201,'david','열정불끈','kakao2667545004','똥띠'),(202,'david','열정불끈','kakao2667544842','이셕'),(204,'google118419579175738030682','이상한이상화','sanghwa1103','이상하지만은않은이상화'),(205,'red4','red','kakao2668092198','lys1325'),(206,'dnjstjr','wonseok2','kakao2668092198','lys1325'),(216,'dnjstjr','wonseok2','sanghwa1103','이상하지만은않은이상화'),(218,'red4','red','Sangwha','분식집고추튀김도둑이상화'),(219,'red4','red','aaaa','꼛땛췕씕꺏'),(232,'test','테스트계정','kakao2665359733','밍밍이'),(233,'test','테스트계정','kakao2665359733','밍밍이'),(235,'google111229672003980040556','이봉주','kakao2546552508','맹지니'),(236,'google111229672003980040556','이봉주','dnjstjr','wonseok2'),(237,'google111229672003980040556','이봉주','google113134987904705544342','❤️youngzero❤'),(238,'google111229672003980040556','이봉주','kakao2665359733','밍밍이'),(239,'google111229672003980040556','이봉주','kakao2659766131','한원석'),(240,'google111229672003980040556','이봉주','consultant01','컨설턴트'),(241,'google111229672003980040556','이봉주','seokjin','임연수'),(242,'google111229672003980040556','이봉주','kakao2667545004','똥띠'),(243,'google111229672003980040556','이봉주','kakao2667544842','이셕'),(244,'google111229672003980040556','이봉주','kakao2667571000','kakao2667571000'),(245,'google111229672003980040556','이봉주','david','열정불끈'),(246,'red4','red','kakao2658115440','kakao2658115440'),(247,'red4','red','david2','motimoti'),(248,'red4','red','google117065706544617987334','한원석술좀그만마셔라'),(249,'red4','red','google118419579175738030682','이상한이상화'),(251,'kakao2546552508','맹지니','dnjstjr','wonseok2'),(252,'kakao2546552508','맹지니','google111229672003980040556','이봉주'),(253,'david','열정불끈','google111229672003980040556','이봉주'),(254,'red4','red','kakao2668425628','kakao2668425628'),(255,'red4','red','najul','송지율이다'),(256,'google113316588345529428078','google113316588345529428078','sanghwa1103','이상하지만은않은이상화'),(257,'red4','red','google103875718875945401236','해피');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `insert_follow` AFTER INSERT ON `follow` FOR EACH ROW begin
	update current_stat set following_cnt= following_cnt+1 where user_id = new.follower_id;
	update current_stat set follower_cnt= follower_cnt+1 where user_id = new.following_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `delete_follow` AFTER DELETE ON `follow` FOR EACH ROW begin
	update current_stat set following_cnt= following_cnt-1 where user_id = old.follower_id;
	update current_stat set follower_cnt= follower_cnt-1 where user_id = old.following_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `feed_id` bigint NOT NULL,
  PRIMARY KEY (`like_id`),
  KEY `feed_has_likes_idx` (`feed_id`),
  KEY `user_likes` (`user_id`),
  CONSTRAINT `feed_has_likes` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_likes` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=829 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (235,'red4',92),(242,'kakao2659762741',95),(245,'kakao2659764081',95),(247,'kakao2659762741',99),(248,'kakao2659956095',92),(249,'kakao2659956095',91),(250,'red4',99),(251,'red4',104),(252,'david',105),(253,'david',104),(254,'david',103),(255,'david',100),(256,'david',99),(258,'david',95),(261,'david',92),(262,'david',91),(263,'david',106),(264,'red4',106),(265,'red4',108),(266,'google114706080296583515621',108),(267,'google114706080296583515621',107),(268,'kakao2546552508',108),(277,'kakao2659762741',100),(278,'kakao2659762741',112),(281,'dnjstjr',112),(282,'david',121),(283,'kakao2546552508',121),(284,'david',122),(285,'red4',122),(286,'red4',121),(287,'dnjstjr',108),(288,'red4',120),(289,'red4',112),(290,'dnjstjr',109),(291,'dnjstjr',107),(292,'dnjstjr',99),(295,'srlee',121),(296,'dnjstjr',105),(297,'dnjstjr',95),(298,'red4',123),(299,'dnjstjr',123),(300,'dnjstjr',100),(301,'dnjstjr',124),(304,'google111229672003980040556',124),(305,'red4',125),(306,'dnjstjr',125),(307,'red4',109),(308,'google111229672003980040556',95),(309,'dnjstjr',122),(310,'sanghwa1103',123),(311,'sanghwa1103',124),(312,'red4',126),(314,'david',127),(315,'dnjstjr',126),(316,'dnjstjr',127),(317,'dnjstjr',128),(318,'kakao2546552508',128),(319,'red4',129),(320,'red4',130),(321,'red4',131),(322,'david',133),(323,'red4',133),(324,'david',134),(325,'dnjstjr',133),(326,'red4',135),(327,'red4',136),(328,'mjkoo',122),(329,'mjkoo',109),(330,'mjkoo',104),(331,'red4',137),(332,'david',137),(333,'dnjstjr',137),(334,'dnjstjr',136),(335,'david',135),(336,'david',131),(337,'david',130),(338,'david',128),(340,'consultant01',137),(341,'consultant01',136),(342,'red4',138),(343,'dnjstjr',138),(344,'dnjstjr',139),(345,'david',139),(347,'dnjstjr',142),(348,'dnjstjr',141),(349,'david',142),(350,'david',143),(353,'test',143),(354,'test',142),(355,'dnjstjr',143),(357,'test',121),(359,'red4',144),(360,'red4',143),(365,'red4',147),(370,'sanghwa1103',105),(371,'sanghwa1103',141),(373,'sanghwa1103',128),(374,'dnjstjr',147),(383,'kakao2546552508',137),(384,'sanghwa1103',139),(385,'google111229672003980040556',128),(386,'google111229672003980040556',139),(387,'google111229672003980040556',141),(388,'google111229672003980040556',149),(389,'google111229672003980040556',135),(390,'google111229672003980040556',150),(391,'google111229672003980040556',107),(392,'google111229672003980040556',105),(393,'google111229672003980040556',91),(395,'google111229672003980040556',147),(397,'google111229672003980040556',144),(398,'google111229672003980040556',143),(399,'google111229672003980040556',142),(401,'google111229672003980040556',138),(402,'google111229672003980040556',137),(403,'red4',150),(404,'david',149),(405,'kakao2546552508',152),(406,'red4',152),(407,'david',152),(408,'seokjin',123),(409,'dnjstjr',152),(410,'david',153),(411,'red4',153),(412,'kakao2665359733',152),(414,'google105453065459048699210',152),(415,'sanghwa1103',144),(416,'kakao2665359733',154),(417,'google113134987904705544342',155),(418,'google113134987904705544342',154),(419,'google113134987904705544342',153),(421,'google113134987904705544342',143),(422,'google113134987904705544342',156),(423,'david',156),(424,'david',157),(425,'red4',157),(426,'red4',156),(427,'dnjstjr',153),(428,'dnjstjr',157),(429,'dnjstjr',156),(430,'dnjstjr',144),(431,'consultant01',157),(432,'consultant01',155),(435,'kakao2665359733',159),(436,'google117680526052635333924',159),(437,'dnjstjr',159),(439,'david',159),(440,'red4',159),(441,'google105453065459048699210',159),(447,'red4',160),(455,'dnjstjr',160),(468,'david',161),(470,'dnjstjr',161),(471,'david',162),(472,'red4',162),(485,'dnjstjr',162),(491,'sanghwa1103',162),(492,'sanghwa1103',157),(493,'google105453065459048699210',162),(495,'consultant01',138),(496,'david',163),(554,'sanghwa1103',127),(566,'sanghwa1103',163),(567,'google111229672003980040556',166),(568,'dnjstjr',166),(570,'google111229672003980040556',161),(571,'google111229672003980040556',163),(572,'dnjstjr',120),(573,'kakao2546552508',162),(574,'kakao2546552508',163),(577,'red4',166),(586,'sanghwa1103',147),(587,'sanghwa1103',126),(591,'david',176),(592,'dnjstjr',176),(593,'red4',176),(594,'google105453065459048699210',176),(601,'red4',177),(602,'red4',178),(603,'dnjstjr',177),(610,'kakao2667268530',178),(611,'kakao2665359733',179),(612,'kakao2665359733',178),(613,'red4',179),(628,'sanghwa1103',160),(629,'sanghwa1103',179),(644,'dnjstjr',178),(654,'dnjstjr',179),(655,'david',179),(656,'kakao2667544842',176),(657,'david',154),(658,'david',155),(660,'sanghwa1103',110),(663,'wjdtn',177),(664,'kakao2657457735',157),(665,'kakao2657457735',156),(666,'google118419579175738030682',179),(667,'google118419579175738030682',177),(711,'google118419579175738030682',156),(714,'Sangwha',181),(715,'david',181),(716,'david',182),(720,'dnjstjr',181),(725,'dnjstjr',119),(733,'aaaa',177),(736,'red4',182),(737,'red4',190),(748,'test',144),(750,'dnjstjr',190),(751,'aaaa',190),(777,'google117065706544617987334',192),(786,'david2',181),(787,'david2',179),(797,'kakao2658115440',198),(798,'red4',198),(799,'red4',191),(800,'red4',199),(802,'red4',163),(803,'sanghwa1103',177),(804,'red4',200),(805,'david',200),(806,'dnjstjr',198),(812,'dnjstjr',200),(813,'dnjstjr',191),(814,'red4',201),(816,'najul',202),(819,'sanghwa1103',198),(820,'red4',202),(821,'sanghwa1103',200),(822,'google103875718875945401236',200),(824,'google113316588345529428078',199),(825,'google103875718875945401236',142),(826,'google103875718875945401236',135),(827,'google103875718875945401236',134),(828,'red4',203);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/  /*!50003 TRIGGER `click_like` AFTER INSERT ON `likes` FOR EACH ROW begin
	if (select user_id from feed where feed_id = new.feed_id) = new.user_id then
		update current_stat set my_like_cnt = my_like_cnt+1 where user_id = new.user_id;
	else
		update current_stat set other_like_cnt = other_like_cnt+1 where user_id = new.user_id;
	end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `moti` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/  /*!50003 TRIGGER `unclick_like` AFTER DELETE ON `likes` FOR EACH ROW begin
	if (select user_id from feed where feed_id = old.feed_id) = old.user_id then
		update current_stat set my_like_cnt = my_like_cnt-1 where user_id = old.user_id;
	else
		update current_stat set other_like_cnt = other_like_cnt-1 where user_id = old.user_id;
	end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `moti` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission` (
  `mission_id` bigint NOT NULL AUTO_INCREMENT,
  `mission_name` varchar(45) NOT NULL,
  PRIMARY KEY (`mission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mission`
--

LOCK TABLES `mission` WRITE;
/*!40000 ALTER TABLE `mission` DISABLE KEYS */;
INSERT INTO `mission` VALUES (1,'밥 먹고 10분 산책하기'),(2,'팔굽혀펴기 10번 해보기'),(3,'플랭크 1회 최대한 버텨보기'),(4,'집 가는 길에 10분 조깅 해보기'),(5,'집이나 회사에서 계단으로 다섯 층 올라가보기 '),(6,'운동이 내 삶에 어떤 변화를 일으킬지 써보기'),(7,'친한 사람과 같이 운동하기'),(8,'나와 가까운 사람에게 운동 인증하기'),(9,'운동하기 귀찮을 때 쓰는 꿀팁 공유하기'),(10,'다음 운동을 언제, 어디서, 어떻게 할지 계획 세우기'),(11,'부모님에게 감사한 이유 하나와 함께 감사하다고 메세지 남기기'),(12,'부모님에게 오늘 하루 어땠냐고 물어보기'),(13,'부모님에게 사랑한다고 메세지 남기기'),(14,'부모님과 밥 같이 먹기'),(15,'부모님과 포옹하기'),(16,'오랫동안 연락 안 한 사람에게 안부 묻기'),(17,'그 사람이 감사한 이유와 함께 감사하다고 메세지 남기기'),(18,'응원의 메세지 남기기'),(19,'커피나 음료수 사주기'),(20,'작은 선물 주기'),(21,'오늘 웃은 이유 공유하기'),(22,'햇빛이나 노을 즐기기'),(23,'나의 소소한 행복 비결 공유하기'),(24,'나를 행복하게 하는 음악 추천해주기'),(25,'누군가에게 사소하지만 친절한 행동하기'),(26,'과거의 어떤 경험이 자신을 현재 이렇게 성장하게 했는지 써보기'),(27,'막막함을 느꼈던 과거의 나에게 해주고 싶은 말 공유하기'),(28,'내 단점이 다른 상황에선 장점이 되었던 경우 공유하기'),(29,'내 인생에서 가장 사소하게 감사한 것을 써보기'),(30,'남들이 잘 모르는 나의 장점 써보기');
/*!40000 ALTER TABLE `mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `playlist_id` bigint NOT NULL AUTO_INCREMENT,
  `playlist_name` varchar(45) NOT NULL,
  `playlist_desc` char(100) DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `flower_id` bigint NOT NULL,
  PRIMARY KEY (`playlist_id`),
  UNIQUE KEY `playlist_name_UNIQUE` (`playlist_name`),
  KEY `playlist_has_category_no_idx` (`category_id`),
  KEY `playlist_has_flower_idx` (`flower_id`),
  CONSTRAINT `playlist_has_category_no` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlist_has_flower` FOREIGN KEY (`flower_id`) REFERENCES `flower` (`flower_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (1,'소소하고 확실한 운동','눈풀꽃의 꽃말은 새출발이에요. 이 시작은 작은 한걸음으로 보이더라도 인생에 있어서는 큰 도약일 거에요.',1,1),(2,'운동 습관 만들기','익소라의 꽃말은 열정이에요. 내 안의 의지를 재점화하고 습관 형성에 도움을 주는 미션들을 통해 운동 습관을 만들어봐요.',1,2),(3,'부모님께 한 발짝','라벤더의 꽃말은 헌신이에요. 가깝지만 먼 부모님에게 조금씩 다가가봐요.',2,3),(4,'관계 가꾸기','분홍색 튤립의 꽃말은 애정과 배려에요. 주변의 아끼는 사람들에게 마음 속에만 있던 감사함을 표현해봐요.',2,4),(5,'일상에서 행복 찾기','아이리스의 꽃말은 좋은 소식이에요. 일상에서 지나치는 행복을 재발견하고 즐겨봐요.',3,5),(6,'나 돌아보기','해바라기의 꽃말은 자부심이에요. 자신의 과거를 돌아보면 현재와 미래가 다르게 느껴질 거에요.',3,6);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_mission`
--

DROP TABLE IF EXISTS `playlist_mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist_mission` (
  `playlist_mission_id` bigint NOT NULL AUTO_INCREMENT,
  `playlist_id` bigint NOT NULL,
  `mission_id` bigint NOT NULL,
  PRIMARY KEY (`playlist_mission_id`),
  KEY `missions_of_playlist_idx` (`mission_id`),
  KEY `playlist_has_missions` (`playlist_id`),
  CONSTRAINT `missions_of_playlist` FOREIGN KEY (`mission_id`) REFERENCES `mission` (`mission_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `playlist_has_missions` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_mission`
--

LOCK TABLES `playlist_mission` WRITE;
/*!40000 ALTER TABLE `playlist_mission` DISABLE KEYS */;
INSERT INTO `playlist_mission` VALUES (3,4,20),(4,1,1),(5,1,3),(6,1,5),(7,1,2),(8,1,4),(9,2,6),(10,2,7),(11,2,8),(12,2,10),(13,2,9),(14,3,11),(15,3,13),(16,3,12),(17,3,14),(18,3,15),(19,4,16),(20,4,17),(21,4,18),(22,4,19),(23,5,23),(24,5,22),(25,5,21),(26,5,24),(27,5,25),(28,6,26),(29,6,29),(30,6,28),(31,6,30),(32,6,27);
/*!40000 ALTER TABLE `playlist_mission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `profile_id` bigint NOT NULL AUTO_INCREMENT,
  `user_desc` char(200) DEFAULT NULL,
  `profile_image_url` text,
  PRIMARY KEY (`profile_id`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (46,'내 계정 돌려내!','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/d3a566d3-f0a3-4d92-ab1b-60bef040a343'),(47,'if (!열정) {\n  this.die()\n}','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/880d10a7-2228-4914-a8e6-1edef5ee657e'),(48,'안녕하세요','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/b3c9d46d-63fe-451f-8be5-d64333d631a1'),(50,'스트릭 중독자','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/0fc805d7-f49d-4550-97d6-eb0517932221'),(51,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/d32968e1-fb4b-422d-80fa-a43907202a8b'),(52,NULL,NULL),(53,NULL,NULL),(54,NULL,NULL),(55,NULL,NULL),(56,NULL,NULL),(57,NULL,NULL),(58,NULL,NULL),(59,'안녕하세용','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/0788abbf-c66d-47cf-9ec3-b9bcd3fba4ef'),(60,'오늘도 뚠뚠 카도는 뚠뚠 열심히 일을 하네 뚠뚠','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/ba45ba99-fccb-4e9b-95d5-13f58606973e'),(61,NULL,NULL),(62,'달리기를 좋아하는 사람입니다.','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/66ba0605-de50-4d2c-84cc-62c53b26daf3'),(63,'안녕하세요','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/d90d8f3f-3b08-43d1-94a4-c93877f22a7c'),(64,NULL,NULL),(65,NULL,NULL),(66,NULL,NULL),(67,NULL,NULL),(68,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/f740b866-c2fb-4cf1-86ba-dc65ce9c704b'),(70,NULL,NULL),(71,NULL,NULL),(72,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/708b4f9b-cf92-4b5f-93a8-b101eb18bae4'),(76,NULL,NULL),(77,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/0544a906-924a-48d6-a969-3724a0c32e7b'),(78,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/0766cb2b-45f8-4bb6-b3f5-e878bf8b8d31'),(79,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/8266ae09-4fdb-43b3-acdf-aef1fd52552e'),(80,'사랑과 평화~ 피쓰','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/b22f8e0f-9565-49c5-9f69-5ae8b9abada7'),(83,NULL,NULL),(89,'나는 오늘도 눈물을 흘린다','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/9621914f-49ae-4552-a494-c500b19f119a'),(90,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/e8544ab7-cc7e-4a7e-8cd3-2350c053b6e9'),(91,NULL,NULL),(92,NULL,NULL),(93,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/0ca9d6bb-6380-4453-9db9-8e524b149c40'),(94,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/6bfca080-8d13-4e73-ba5d-38ab0a4f5f98'),(95,NULL,NULL),(96,NULL,NULL),(97,NULL,NULL),(98,NULL,NULL),(99,NULL,NULL),(100,NULL,NULL),(101,'b',NULL),(102,NULL,NULL),(103,NULL,NULL),(104,NULL,NULL),(105,NULL,NULL),(106,NULL,NULL),(107,NULL,NULL),(108,NULL,NULL),(109,'식단표','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/6eb6675e-43de-4a3d-ba31-9e61f5793804'),(110,NULL,'https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/07cc33c5-4ffe-44d2-a39b-62004e8c8e82'),(113,NULL,NULL),(115,NULL,NULL),(116,NULL,NULL),(117,NULL,NULL),(118,NULL,NULL),(120,NULL,NULL),(121,NULL,NULL),(122,NULL,NULL),(124,'무너무너 빔','https://motiimages.s3.ap-northeast-2.amazonaws.com/profileImages/81def55d-f559-4ad3-8076-d7d0f11da198'),(125,NULL,NULL),(126,NULL,NULL);
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suggestion`
--

DROP TABLE IF EXISTS `suggestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suggestion` (
  `suggestion_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `playlist_name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mission1name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mission2name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mission3name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mission4name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mission5name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `suggestion_content` char(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`suggestion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suggestion`
--

LOCK TABLES `suggestion` WRITE;
/*!40000 ALTER TABLE `suggestion` DISABLE KEYS */;
INSERT INTO `suggestion` VALUES (1,'건강','매일매일 운동하기','런닝머신 위에서 인증샷','헬스장에서 인증샷','웨이트 트레이닝 하며 인증샷','식단 인증샷','\"오늘의 운동 완료\" 인증샷','매일매일 운동하고 싶은 사람들을 위한 성장플랜입니다.'),(2,'관계','ㅇㄻㄴㅇㄻ','ㄻㄴㅇㄻㄴ','ㄹㄴㅁㅇㄹ','ㄹㅇㅁㄴㄻㄴ','ㄹㅇㅁㄴㄹㄴㅁ','ㄻㄴㅇㄻ','ㄹㅇㄴㅁㄹㄴㅇㄹㄴㅁ'),(3,'관계','aaaaa','aa','aa','aa','aa','aaa','aa'),(4,'건강','그냥그냥그냥','그냥자기','구냥밥먹기','그냥커피먹기','그냥숨쉬기','그냥잇기','그냥 잇어도 되는 플랜'),(5,'건강','임시','임시','임시','임시','임시','임시','임시'),(6,'건강','동네 한 바퀴','동네 초등학교 운동장 2바퀴 걷기','동네 공원에서 30분 조깅하기','동네 산책로 1시간 걷기','집에서 30분 떨어진 편의점 걸어서 다녀오기','동네에서 가장 높은 건물 꼭대기층 다녀오기','본인 동네를 돌아다니며 미션을 수행해보면 재미있을 것 같습니다!');
/*!40000 ALTER TABLE `suggestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(30) NOT NULL,
  `password` char(100) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `nickname` char(30) NOT NULL,
  `refresh_token` char(255) DEFAULT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'default',
  `temporary` tinyint DEFAULT '0',
  `join_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `salt` text,
  `profile_id` bigint NOT NULL,
  `achievement_id` bigint DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `nickname_UNIQUE` (`nickname`),
  KEY `user_has_profile_idx` (`profile_id`),
  CONSTRAINT `user_has_profile` FOREIGN KEY (`profile_id`) REFERENCES `profile` (`profile_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('aaaa','c1a22f14540786612c178b6c0ec4e470496cb95a908863cffce4bf838b2e5eed','dbgksquf05@naver.com','꼛땛췕씕꺏','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTEwNzMzMDM1LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjAzMzMsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJhYWFhIn0.sq3tw2AgY4H_Ad-YKUCBAQuirefK-PflD4csLcItAz0','default',0,'2023-02-16 10:24:51','L11DmuDrJOes0eyMiOmUEQ==',109,NULL),('coachMoon','5aa4936f90d8afbb4ec1275e0e9cc5b588f8612392ad4b2177840ebfd203a565','dytjdkorea@naver.com','123',NULL,'default',0,'2023-02-14 11:44:50','XgT/CtjUBwCZMQLSBDxB3A==',67,NULL),('consultant01','d58e61f959c5276e5e85e6be2366f3a28f71b4b4d4b2349acbc1b2bb20d19379','gsmj1712@gmail.com','컨설턴트','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MzM5ODQ5MTA0LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc1NDk0NDksInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJjb25zdWx0YW50MDEifQ.IhzzO1ALdlG634pyy7TgeQPjFJ4sh-FikMYIbNiArWo','default',0,'2023-02-14 10:57:22','2ITWyREFzMV9HSutrigQFg==',66,NULL),('david','0ed720983ffc78dacf7ab5d0ec9cafa72134d9c7caafed4d78f61be97a3475c9','yudavid0611@gmail.com','열정불끈','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTEzNTk2NTg1LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjMxOTYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJkYXZpZCJ9.w2SHdrhkjdDD6kjbMVRdY69DoAwy__X3Wjp3yniVSWw','default',0,'2023-02-10 17:50:07','atui8q6n40YMfJzCGWdjHw==',47,23),('david2','1dfb473bfb0dedd730195f0246640a726ded76137482b3d805c36968874220ac','dbekdnlt11@naver.com','motimoti',NULL,'default',0,'2023-02-16 10:53:24','KSYYXdtl5aQRjtrCmygT0A==',113,NULL),('david3','fe191f59a9870d3117f21e791bc5b4fbb77414f91e96a20537369e86e848f558','thechosen95@khu.ac.kr','ㅁㄴㅇㄻㄴㅇㄹ',NULL,'default',0,'2023-02-15 16:52:24','b6FDNo8VmjpJK3L4/wnfWQ==',91,NULL),('dnjstjr','ab008b91dc97d379ea882207fb68950db6a9ce8d342fa85be020c20380c40b6c','jleejlee6@gmail.com','wonseok2','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTE5ODk1Mjk4LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3Mjk0OTUsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJkbmpzdGpyIn0.75PYCJMp2dt7Ot7AZpJQND6lAwgRjjm9VQFJPT5xYWs','default',0,'2023-02-12 15:45:01','zKwBnZQNWN8S5p854F3C4w==',59,3),('google101134343878013224607',NULL,'kacamimi3544@gmail.com','google101134343878013224607','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTEyODkzMDQ0LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjI0OTMsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMDExMzQzNDM4NzgwMTMyMjQ2MDcifQ.v9R10oldVkBLEftinlxzj4oLEfm3Xq55Ipnon3Fw0dE','google',0,'2023-02-16 11:01:33',NULL,115,NULL),('google101235305113581196343',NULL,'dltkdghk0927@gmail.com','google101235305113581196343',NULL,'google',0,'2023-02-14 16:29:16',NULL,76,NULL),('google103875718875945401236',NULL,'yejison98@gmail.com','해피','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTI3MDg1MDQzLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MzY2ODUsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMDM4NzU3MTg4NzU5NDU0MDEyMzYifQ.ulMzl3Q2EgqYy-Wi8JsflpRw1fN4TxCggzjJor4uS2c','google',0,'2023-02-16 14:58:05',NULL,124,NULL),('google105453065459048699210',NULL,'jsj2607@gmail.com','동길홍',NULL,'google',0,'2023-02-14 18:03:53',NULL,79,NULL),('google111229672003980040556',NULL,'cfdw011@gmail.com','이봉주','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MzU5NDU3ODcwLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc1NjkwNTcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMTEyMjk2NzIwMDM5ODAwNDA1NTYifQ.9x8YXsOqjcnKL4MS6QbJ1KmHYmeDNgOhM9rZUEXWtw4','google',0,'2023-02-13 10:05:28',NULL,62,24),('google111763154843495368059',NULL,'joney0715@gmail.com','google111763154843495368059',NULL,'google',0,'2023-02-16 08:53:56',NULL,103,NULL),('google113134987904705544342',NULL,'youngzero@woowahan.com','❤️youngzero❤','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2Mzc3OTI1NzQxLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc1ODc1MjUsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMTMxMzQ5ODc5MDQ3MDU1NDQzNDIifQ.pUR0-vaROzWQRPoqRPlvaKOJZiwxV7UwXw85T-BnLGc','google',0,'2023-02-14 21:32:06',NULL,80,NULL),('google113316588345529428078',NULL,'jackid1103@gmail.com','google113316588345529428078','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTI3MzUyOTk0LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MzY5NTIsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMTMzMTY1ODgzNDU1Mjk0MjgwNzgifQ.H1j_zDDUo1xMifXbVFVflavpAPi_bkKK0dSnnL7Wrrc','google',0,'2023-02-16 15:02:33',NULL,125,NULL),('google114706080296583515621',NULL,'brianpsw96@gmail.com','Sangwoo',NULL,'google',0,'2023-02-11 21:56:59',NULL,57,NULL),('google117065706544617987334',NULL,'lsse.ph4@gmail.com','한원석술좀그만마셔라','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTExODU5NzcwLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjE0NTksInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMTcwNjU3MDY1NDQ2MTc5ODczMzQifQ.m82zpacHApU0VW4McEFNoe7BWoIVVchgJnDb-XwsWvU','google',0,'2023-02-16 10:44:20',NULL,110,NULL),('google117680526052635333924',NULL,'k79632202@gmail.com','제주도귤왕','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDI0NTA3OTYzLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2MzQxMDcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMTc2ODA1MjYwNTI2MzUzMzM5MjQifQ.wUh86071Q1D1qRlFPj4bzZ1gJDL2Qx398HbgoZvRU-U','google',0,'2023-02-14 13:16:01',NULL,68,NULL),('google118419579175738030682',NULL,'manchotts8@gmail.com','이상한이상화','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA1MDQyMjQ2LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTQ2NDIsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJnb29nbGUxMTg0MTk1NzkxNzU3MzgwMzA2ODIifQ.4L7Mf8cpX8NEdSIfCA7dgQAP0AT5VtNaqWt3MOlNE7I','google',0,'2023-02-16 08:50:42',NULL,102,NULL),('hello','09ae0f750bf3f189fa365c80f8c5f0cd06bc98d5ecb43faef2edca8ff13ee199','wowjw97@naver.com','헬로','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA2MTQ3NTI0LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTU3NDcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJoZWxsbyJ9.eBdGpEbeAKrR3DNpOCdkGofQR8VSyPjVK4YuQ_29U4c','default',0,'2023-02-16 09:08:36','JtCZEnTYpUcfvPSGlmEFTw==',104,NULL),('joney0715','462fba75f5abcbfd361eed2820cd049a782410070fbfde61ed59f73430027087','joney0715@naver.com','joney','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA4NTg2OTk3LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTgxODYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJqb25leTA3MTUifQ.RklNh5nTQ-t__mb2035xTN0honlue_ThdjNJJM5py0s','default',0,'2023-02-16 09:44:28','Npvh/kVuN63qv9B+jP7RPw==',108,NULL),('kakao2546552508',NULL,'mgin0527@naver.com','맹지니','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTIyODI5MjMzLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MzI0MjksInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI1NDY1NTI1MDgifQ.Q-kgY0EakfB61D2zL42gnEDnAWo0tFLoObL1tQ62Msg','kakao',0,'2023-02-10 18:57:54',NULL,50,3),('kakao2657457735',NULL,'dabak20@hanmail.net','kakao2657457735','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTE2NDY4NDc4LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjYwNjgsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NTc0NTc3MzUifQ.6MkCa1TVMVdK_T1fARSpY-L8qV6KBncgeflupuxfwYk','kakao',0,'2023-02-16 08:45:55',NULL,100,NULL),('kakao2658115440',NULL,'tayoun011@naver.com','kakao2658115440','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTEzNDU2MTQyLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjMwNTYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NTgxMTU0NDAifQ.-ZG-Q2wHXfxtggv_Vr1lKdHpn20yk5BrBvK2UmpgOzQ','kakao',0,'2023-02-16 11:10:56',NULL,117,NULL),('kakao2659762741',NULL,'jygcsky@nate.com','호두우','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MTgzMzM2NDg5LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzczOTI5MzYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NTk3NjI3NDEifQ.N1LvgpMKsz2Yzib9rK7cHYNj6TzSxhWlA0cd-QN9DEs','kakao',0,'2023-02-10 20:39:30',NULL,51,3),('kakao2659764081',NULL,'psh104404@naver.com','kakao2659764081','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MDI5MjAxNTEwLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzcyMzg4MDEsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NTk3NjQwODEifQ.t7GmbgWX99qPw21Y4oa9xlHqIUkR2aDRl30yr5Rxiys','kakao',0,'2023-02-10 20:39:56',NULL,52,NULL),('kakao2659766131',NULL,'kmin9811@naver.com','한원석','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MDI5MzcyMDY2LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzcyMzg5NzIsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NTk3NjYxMzEifQ.x-OM2Lrn-8UsBvsJB9mG7S9nLbPnl2f5sUh6J0sAmaE','kakao',0,'2023-02-10 20:42:52',NULL,53,NULL),('kakao2659956095',NULL,'bkrim68@gmail.com','배림','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MDM3NDMxMzY3LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzcyNDcwMzEsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NTk5NTYwOTUifQ.I6KNtojNoWk7Kh8kFd4FJjfhWsOwqjQdlrB6r12hGrY','kakao',0,'2023-02-10 22:57:11',NULL,55,NULL),('kakao2659962383',NULL,'senurim@gmail.com','kakao2659962383',NULL,'kakao',0,'2023-02-10 23:02:16',NULL,56,NULL),('kakao2661445862',NULL,'tnqlssla28@naver.com','kakao2661445862','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MTI5MDUwMjQ3LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzczMzg2NTAsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjE0NDU4NjIifQ.SIrjsy28Xu7KFIJWXWX13hO1rs_4__oakJMexY3zqnA','kakao',0,'2023-02-12 00:24:10',NULL,58,NULL),('kakao2665359733',NULL,'jsj2607@naver.com','밍밍이',NULL,'kakao',0,'2023-02-14 16:33:41',NULL,77,3),('kakao2666807704',NULL,'tablemin@kakao.com','kakao2666807704','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDI3MDYwMDYzLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2MzY2NjAsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjY4MDc3MDQifQ.2Sd93XMV2h25M2zB-bs8lwhtRtf8HkCopxuBQLu_hGI','kakao',0,'2023-02-15 11:11:00',NULL,83,NULL),('kakao2667268530',NULL,'syhj0326@naver.com','흑흑','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDQ2OTUxOTYwLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2NTY1NTEsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjcyNjg1MzAifQ.krNoQfta1VtpmpILFjlysIC2Ny5V-5JC0-o5Kwh1nbw','kakao',0,'2023-02-15 16:42:32',NULL,89,NULL),('kakao2667268719',NULL,'uj9125@gmail.com','쥬엉','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDQ2OTY2MjA5LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2NTY1NjYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjcyNjg3MTkifQ.LY25zWWsodbZcWzcyvAFkDPjj5ptlZncNFGnlfm6KFE','kakao',0,'2023-02-15 16:42:46',NULL,90,NULL),('kakao2667295149',NULL,'cj7498@naver.com','kakao2667295149','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDQ4MDk1NDY2LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2NTc2OTUsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjcyOTUxNDkifQ.NjUO-4-6KouKcEJMEstETZ_3nx5E8Z5at4oZcCh74q0','kakao',0,'2023-02-15 17:01:35',NULL,92,NULL),('kakao2667544842',NULL,'lhm6128@naver.com','이셕','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDYwMTY2MjIzLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2Njk3NjYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njc1NDQ4NDIifQ.KWFO5gdVskWveyycTe00OSzgUDAXyw4yRT74BYSrpGY','kakao',0,'2023-02-15 20:05:16',NULL,93,NULL),('kakao2667545004',NULL,'sion4047@naver.com','똥띠','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDYwMTE1NTc3LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2Njk3MTUsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njc1NDUwMDQifQ.a8pfuQ710LLTUmv0oeAClqmFnDkwqmQJwbr1X156vRM','kakao',0,'2023-02-15 20:05:24',NULL,94,NULL),('kakao2667556782',NULL,'woo9676@naver.com','kakao2667556782','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDU5NjQ5ODc4LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2NjkyNDksInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njc1NTY3ODIifQ.4qAo6r5W4XQGhLAVGiFDf4Hns3I5Z6HHQAsDdW6TuP4','kakao',0,'2023-02-15 20:14:10',NULL,95,NULL),('kakao2667571000',NULL,'mirayoo@kookmin.ac.kr','kakao2667571000','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDYwMjM3MDAzLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2Njk4MzcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njc1NzEwMDAifQ.9SaGDU3d8KxnKD9eQwjLWLxUws_g0q2DV-Uv0V4FtOQ','kakao',0,'2023-02-15 20:23:57',NULL,96,NULL),('kakao2667571192',NULL,'02joeun@naver.com','kakao2667571192','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDYwMjQ1NzM2LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2Njk4NDUsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njc1NzExOTIifQ.FQg5TeGSYHBz6rEA1zUOHR54GmvhYHSqfBL5nQ50BcA','kakao',0,'2023-02-15 20:24:06',NULL,97,NULL),('kakao2667600914',NULL,'sunnyp3991@naver.com','kakao2667600914','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDYxNTI1OTA5LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2NzExMjUsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njc2MDA5MTQifQ.UZ5NI2a4rZqv4le1F5tp5JIzASv7sxuqJYwBoW1_21c','kakao',0,'2023-02-15 20:45:26',NULL,98,NULL),('kakao2668092198',NULL,'zephyr728@naver.com','lys1325','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA0OTE5ODUxLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTQ1MTksInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjgwOTIxOTgifQ.LR1GYCPt4FsuLrF53iMXRkJkinI95_s_UZ7d273E6Ys','kakao',0,'2023-02-16 08:48:40',NULL,101,NULL),('kakao2668118733',NULL,'1109ssh@naver.com','kakao2668118733','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA2NzE0MjM3LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTYzMTQsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjgxMTg3MzMifQ.TRCraDiVoAmoTrkNBWHfU1nrQdC8tV9bap-fqpwGSiw','kakao',0,'2023-02-16 09:18:34',NULL,105,NULL),('kakao2668226313',NULL,'mj3544@naver.com','kakao2668226313','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTEzMDE3Mjc5LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjI2MTcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjgyMjYzMTMifQ.DgdGv0XvXhiK0SDCzTE13jlcFzen6M1roaHPXkG4frw','kakao',0,'2023-02-16 11:03:37',NULL,116,NULL),('kakao2668278626',NULL,'dhlim07@naver.com','kakao2668278626','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTE0NjA2OTA5LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjQyMDYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2NjgyNzg2MjYifQ.0bXjYJGSQlxel_X7AnxY0chEK-3S-88E3iNW4gTHg9M','kakao',0,'2023-02-16 11:30:07',NULL,118,NULL),('kakao2668425628',NULL,'abin7989@naver.com','kakao2668425628','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTIxMDc3MjcxLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MzA2NzcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njg0MjU2MjgifQ.clj9Z66ouu1rjiZ4DSrzY8zqbLm7-OXB1rdB6rMXpzw','kakao',0,'2023-02-16 13:17:57',NULL,120,NULL),('kakao2668434935',NULL,'juiia0114@naver.com','kakao2668434935','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTIxNDkzMDI1LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MzEwOTMsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJrYWthbzI2Njg0MzQ5MzUifQ.tRoX1s8_86qLWmFhEw3YUHjFDEfII6N3jADfaqKEGbo','kakao',0,'2023-02-16 13:24:53',NULL,121,NULL),('m0jinaa','e3125791c1c46b708ce11eab7135eb5e4ae28975cfee1640246af9d97f362434','mgin0527@hufs.ac.kr','맹지',NULL,'default',0,'2023-02-10 22:10:19','y0WTaea5WhU92jyuwKjEvw==',54,NULL),('mjkoo','622e53a30ca2a612e8edf4bded3327991ff231dc85792a226b42caa70b8f11da','skyblue4257@gmail.com','깨굴','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2Mjk0Mzk3NDczLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc1MDM5OTcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJtamtvbyJ9.M_BGgjmN-7WK30LHQZUYqYncuKLjGL5ljINyHG4N9wA','default',0,'2023-02-13 22:19:40','PFuNFHR3kIMcwuLIw0NlRQ==',65,NULL),('najul','b88ffb7f503245505e7e35bc9c1d494f09c247611c148fd84c8f72dc14fad7df','lnacles@naver.com','송지율이다','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTIzNDczMjI5LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MzMwNzMsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJuYWp1bCJ9.4aGrBk0Btc3NM5p8uLBVx01-3h1AKVIYl9kwEKTGuYM','default',0,'2023-02-16 13:57:33','vQtw71nA8YJidf+gfzJcVQ==',122,NULL),('red4','76476ab822e206ae813cca4fee5a8217d764e8c876a45485c79725bac904d2d9','noprob1325@naver.com','red','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTI4NTg3NTU4LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MzgxODcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJyZWQ0In0.XdRKL5QErlhRN6rYuy7nAPrLC_Rd0oXrvF9v7n7gkjw','default',0,'2023-02-10 17:43:30','61kvN1RejjuKLtLVCtuq7Q==',46,22),('sanghwa','b9642468b38a7cd71d3ab2611e89357a3159262449f2c72a4268edeaca252c20','1sangha1@naver.com','누굴까요',NULL,'default',0,'2023-02-16 15:04:44','fKIT82tcd8IyRidiMS+fyQ==',126,NULL),('sanghwa1103','875dce741f29afd1596a5292deffb26dd8d905f4533c4843c037ed14c9aa8803','sanghwa1103@gmail.com','이상하지만은않은이상화',NULL,'default',0,'2023-02-10 17:50:36','QrjWF7X0bbjka2LvYLLXoA==',48,3),('Sangwha','8fa86843afbb29efcbd92c5b3295cd3636fd1d8c6f441fab3a7dc4012901e277','ksj970714@gmail.com','분식집고추튀김도둑이상화','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA3MDI0MTk2LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTY2MjQsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJTYW5nd2hhIn0.llQyxieyWQv0anKhJ1cnXNtnh-vQGALlJ86xMWPWhTs','default',0,'2023-02-16 09:23:31','SxFVFkuzJJVUJUSjdk3aXA==',106,NULL),('seokjin','a2a0e99f3683849dbb1d29d8764d68f7e9ecc654a4f9a673f89e08bf2efb950f','jsj2607@hanmail.net','임연수','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NDQ2MTAwMDkyLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc2NTU3MDAsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJzZW9ramluIn0.m2EC8I6ZVMHI4gbsA1hYqzUZZ16jWizWCxzWp-kiWVY','default',0,'2023-02-14 16:34:53','nacRD4MqMGziszm/BI7Zvw==',78,NULL),('srlee','f062dd876c6b47ec48ee5a9e5f08a9d1eb2cb1ff319a3d4d86e3a0f962566c98','dev.srlee@gmail.com','바보카도','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2MjQ2ODI2MTA5LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc0NTY0MjYsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJzcmxlZSJ9.CuRKCS9fxV-Mws7GXoXDYSinPfXy7UDo2xm6Zx-0jbs','default',0,'2023-02-13 09:07:00','QTQkeUs+7nFKy1z2U0/vWQ==',60,NULL),('test','897d7a9d7f776efed647010c8575962b3978bc71070b1657075961a962c68371','lys1325@gmail.com','테스트계정','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTEwNzQwODk4LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MjAzNDAsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJ0ZXN0In0.AOcWxc9Wtp1L8IRl1I-I5Qu5aY7Un-bGMslcAThYQ9E','default',0,'2023-02-13 16:06:55','6pQvps+0F6c7Jz4w7XEGNQ==',63,NULL),('testtest','0622b0344dcd430dfac6eb0d401690275b66d0bb714bf738cde4b2b1ec91483d','pjw2369@naver.com','상화님팬이에요','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA3MTc3NDc0LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTY3NzcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJ0ZXN0dGVzdCJ9.E-4Bgk87QyfODgQaaeaLeZoB-mhDaKuVDky3_iatx7w','default',0,'2023-02-16 09:25:45','HEmlsWTqDn2hTY1A7J4nUQ==',107,NULL),('wjdtn','fa34483b9eb0bba449c6871a3dd144e937363fd0be3492447437d9f8fa2876ef','kjskjs356@naver.com','water','eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjc2NTA0NzA3NDQ3LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Nzc3MTQzMDcsInN1YiI6InJlZnJlc2gtdG9rZW4iLCJ1c2VyaWQiOiJ3amR0biJ9.f2m28VWH_B4xXZEoMtbSCm3CSTrGP0Tz-ndX9Qdijcs','default',0,'2023-02-16 08:44:31','ff20PAAKaGFhsj9RHHx+mA==',99,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/  /*!50003 TRIGGER `create_stat` AFTER INSERT ON `user` FOR EACH ROW begin
	insert into current_stat(user_id) values (new.user_id);
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/  /*!50003 TRIGGER `delete_user` BEFORE DELETE ON `user` FOR EACH ROW begin
	delete from follow where follower_id = old.user_id;
    delete from follow where following_id = old.user_id;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `user_achievement`
--

DROP TABLE IF EXISTS `user_achievement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_achievement` (
  `user_achievement_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `achievement_id` bigint NOT NULL,
  `achieved_date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_achievement_id`),
  KEY `user_has_achievement_idx` (`user_id`),
  KEY `achievement_of_user_idx` (`achievement_id`),
  CONSTRAINT `achievement_of_user` FOREIGN KEY (`achievement_id`) REFERENCES `achievement` (`achievement_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_has_achievement` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_achievement`
--

LOCK TABLES `user_achievement` WRITE;
/*!40000 ALTER TABLE `user_achievement` DISABLE KEYS */;
INSERT INTO `user_achievement` VALUES (86,'red4',1,'2023-02-10 17:45:01'),(87,'sanghwa1103',1,'2023-02-10 17:53:25'),(88,'red4',23,'2023-02-10 17:55:17'),(89,'red4',2,'2023-02-10 17:55:59'),(91,'sanghwa1103',3,'2023-02-10 17:58:05'),(93,'kakao2659762741',23,'2023-02-10 20:40:04'),(94,'kakao2659762741',2,'2023-02-10 20:40:42'),(95,'kakao2659764081',23,'2023-02-10 20:41:17'),(96,'kakao2659764081',2,'2023-02-10 20:42:15'),(97,'kakao2659762741',1,'2023-02-10 20:42:35'),(98,'kakao2659762741',3,'2023-02-10 20:43:30'),(99,'kakao2659766131',1,'2023-02-10 20:44:50'),(100,'kakao2659956095',1,'2023-02-10 22:58:49'),(101,'kakao2659956095',23,'2023-02-10 22:59:17'),(102,'kakao2546552508',1,'2023-02-10 23:47:56'),(103,'david',1,'2023-02-11 17:56:23'),(104,'david',23,'2023-02-11 17:57:07'),(105,'david',24,'2023-02-11 17:57:29'),(106,'david',3,'2023-02-11 17:57:35'),(107,'david',2,'2023-02-11 17:59:11'),(108,'google114706080296583515621',1,'2023-02-11 22:07:59'),(109,'google114706080296583515621',2,'2023-02-11 22:09:47'),(110,'google114706080296583515621',3,'2023-02-11 22:09:54'),(111,'google114706080296583515621',23,'2023-02-11 22:10:34'),(112,'kakao2546552508',23,'2023-02-11 23:26:45'),(116,'dnjstjr',1,'2023-02-12 15:46:12'),(117,'dnjstjr',23,'2023-02-12 17:48:50'),(118,'sanghwa1103',2,'2023-02-12 22:58:30'),(119,'red4',24,'2023-02-13 09:10:59'),(120,'red4',22,'2023-02-13 09:13:11'),(121,'srlee',1,'2023-02-13 09:14:29'),(122,'srlee',3,'2023-02-13 09:15:24'),(123,'srlee',23,'2023-02-13 09:15:51'),(124,'dnjstjr',24,'2023-02-13 10:06:38'),(125,'dnjstjr',2,'2023-02-13 10:06:49'),(126,'google111229672003980040556',1,'2023-02-13 10:07:30'),(127,'dnjstjr',3,'2023-02-13 10:08:15'),(128,'google111229672003980040556',23,'2023-02-13 10:09:19'),(129,'google111229672003980040556',2,'2023-02-13 10:09:40'),(130,'sanghwa1103',23,'2023-02-13 10:24:23'),(131,'dnjstjr',22,'2023-02-13 13:48:32'),(132,'sanghwa1103',22,'2023-02-13 18:10:58'),(133,'mjkoo',23,'2023-02-13 22:33:36'),(134,'david',22,'2023-02-14 10:12:27'),(135,'consultant01',1,'2023-02-14 10:58:40'),(136,'consultant01',3,'2023-02-14 10:58:49'),(137,'consultant01',2,'2023-02-14 10:58:57'),(138,'consultant01',23,'2023-02-14 10:59:36'),(139,'test',23,'2023-02-14 12:41:44'),(140,'test',2,'2023-02-14 12:43:08'),(141,'test',1,'2023-02-14 12:44:53'),(144,'kakao2546552508',2,'2023-02-14 15:44:57'),(145,'kakao2546552508',3,'2023-02-14 15:45:24'),(146,'google111229672003980040556',3,'2023-02-14 16:23:50'),(147,'google111229672003980040556',24,'2023-02-14 16:23:55'),(149,'seokjin',1,'2023-02-14 17:34:31'),(150,'m0jinaa',2,'2023-02-14 17:41:56'),(151,'seokjin',23,'2023-02-14 17:52:02'),(152,'kakao2665359733',23,'2023-02-14 18:01:38'),(153,'kakao2665359733',2,'2023-02-14 18:02:00'),(154,'seokjin',3,'2023-02-14 18:02:30'),(155,'google105453065459048699210',23,'2023-02-14 18:09:32'),(156,'google105453065459048699210',2,'2023-02-14 18:09:57'),(157,'kakao2665359733',1,'2023-02-14 20:32:42'),(158,'kakao2665359733',3,'2023-02-14 20:32:47'),(159,'google113134987904705544342',23,'2023-02-14 21:32:37'),(160,'google113134987904705544342',2,'2023-02-14 21:33:20'),(161,'google113134987904705544342',1,'2023-02-14 21:56:10'),(162,'google113134987904705544342',3,'2023-02-14 21:56:14'),(163,'consultant01',22,'2023-02-15 09:39:50'),(164,'google117680526052635333924',2,'2023-02-15 10:41:32'),(165,'google117680526052635333924',23,'2023-02-15 10:41:33'),(166,'sanghwa1103',24,'2023-02-15 12:59:14'),(169,'kakao2667268719',1,'2023-02-15 16:45:54'),(172,'kakao2667268530',2,'2023-02-15 17:00:28'),(173,'kakao2667268530',23,'2023-02-15 17:00:35'),(174,'kakao2665359733',22,'2023-02-15 17:01:25'),(175,'red4',3,'2023-02-15 17:28:58'),(176,'kakao2667544842',23,'2023-02-15 20:26:23'),(177,'wjdtn',23,'2023-02-16 08:45:33'),(178,'wjdtn',2,'2023-02-16 08:45:50'),(179,'kakao2657457735',23,'2023-02-16 08:46:09'),(180,'kakao2668092198',2,'2023-02-16 08:49:24'),(181,'google118419579175738030682',23,'2023-02-16 08:53:31'),(182,'hello',23,'2023-02-16 09:12:04'),(183,'Sangwha',1,'2023-02-16 09:24:30'),(184,'Sangwha',23,'2023-02-16 09:24:44'),(185,'Sangwha',2,'2023-02-16 09:25:05'),(187,'aaaa',23,'2023-02-16 10:25:39'),(188,'aaaa',2,'2023-02-16 10:26:03'),(189,'test',3,'2023-02-16 10:30:06'),(190,'aaaa',1,'2023-02-16 10:30:39'),(191,'aaaa',3,'2023-02-16 10:30:46'),(192,'aaaa',22,'2023-02-16 10:35:09'),(193,'google117065706544617987334',1,'2023-02-16 10:46:04'),(194,'google117065706544617987334',3,'2023-02-16 10:46:49'),(197,'david2',1,'2023-02-16 10:54:58'),(201,'david2',23,'2023-02-16 10:59:40'),(202,'david2',2,'2023-02-16 10:59:49'),(203,'kakao2658115440',23,'2023-02-16 11:11:16'),(204,'kakao2658115440',1,'2023-02-16 11:12:54'),(206,'kakao2668425628',1,'2023-02-16 13:19:48'),(207,'kakao2668425628',2,'2023-02-16 13:21:10'),(208,'najul',1,'2023-02-16 14:00:27'),(209,'najul',3,'2023-02-16 14:00:48'),(210,'najul',2,'2023-02-16 14:02:00'),(211,'google103875718875945401236',23,'2023-02-16 14:58:40'),(212,'google103875718875945401236',2,'2023-02-16 14:59:00'),(213,'google113316588345529428078',23,'2023-02-16 15:03:03'),(214,'google103875718875945401236',1,'2023-02-16 15:09:45');
/*!40000 ALTER TABLE `user_achievement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_playlist`
--

DROP TABLE IF EXISTS `user_playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_playlist` (
  `user_playlist_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) NOT NULL,
  `playlist_id` bigint NOT NULL,
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` datetime NOT NULL,
  `done` int NOT NULL DEFAULT '0',
  `today` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_playlist_id`),
  KEY `user_has_playlist_idx` (`user_id`),
  KEY `playlist of user_idx` (`playlist_id`),
  CONSTRAINT `playlist of user` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_has_playlist` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_playlist`
--

LOCK TABLES `user_playlist` WRITE;
/*!40000 ALTER TABLE `user_playlist` DISABLE KEYS */;
INSERT INTO `user_playlist` VALUES (76,'red4',5,'2023-02-10 17:44:26','2023-02-15 12:51:04',5,1),(77,'sanghwa1103',6,'2023-02-10 17:51:20','2023-02-17 00:00:00',4,0),(81,'david',1,'2023-02-10 17:59:19','2023-02-15 16:20:18',5,1),(82,'red4',6,'2023-02-10 17:59:27','2023-02-16 08:40:28',5,1),(83,'kakao2659762741',2,'2023-02-10 20:41:48','2023-02-17 00:00:00',2,0),(84,'kakao2659766131',1,'2023-02-10 20:43:48','2023-02-17 00:00:00',1,0),(85,'kakao2659956095',2,'2023-02-10 22:58:14','2023-02-17 00:00:00',1,0),(86,'kakao2546552508',1,'2023-02-10 23:41:58','2023-02-17 00:00:00',4,0),(87,'google114706080296583515621',5,'2023-02-11 22:05:17','2023-02-18 00:00:00',2,0),(88,'kakao2546552508',5,'2023-02-11 23:29:13','2023-02-18 00:00:00',2,1),(89,'kakao2661445862',6,'2023-02-12 00:26:13','2023-02-19 00:00:00',0,0),(91,'dnjstjr',1,'2023-02-12 15:45:32','2023-02-19 00:00:00',2,0),(92,'dnjstjr',4,'2023-02-12 15:46:31','2023-02-19 00:00:00',3,0),(93,'srlee',1,'2023-02-13 09:08:22','2023-02-20 00:00:00',1,0),(94,'google111229672003980040556',5,'2023-02-13 10:05:42','2023-02-20 00:00:00',4,1),(95,'red4',4,'2023-02-13 10:37:38','2023-02-20 00:00:00',4,0),(96,'google111229672003980040556',1,'2023-02-13 10:50:50','2023-02-20 00:00:00',0,0),(98,'sanghwa1103',1,'2023-02-13 14:41:08','2023-02-20 00:00:00',2,0),(99,'dnjstjr',2,'2023-02-13 16:48:08','2023-02-20 00:00:00',1,0),(100,'dnjstjr',3,'2023-02-13 16:59:18','2023-02-20 00:00:00',1,0),(101,'red4',1,'2023-02-13 16:59:38','2023-02-20 00:00:00',3,0),(102,'dnjstjr',5,'2023-02-13 17:02:23','2023-02-20 00:00:00',1,0),(103,'mjkoo',6,'2023-02-13 22:21:28','2023-02-20 00:00:00',0,0),(104,'mjkoo',5,'2023-02-13 22:21:34','2023-02-20 00:00:00',0,0),(105,'mjkoo',4,'2023-02-13 22:21:39','2023-02-20 00:00:00',0,0),(106,'mjkoo',3,'2023-02-13 22:21:44','2023-02-20 00:00:00',0,0),(107,'mjkoo',1,'2023-02-13 22:21:50','2023-02-20 00:00:00',0,0),(108,'mjkoo',2,'2023-02-13 22:21:56','2023-02-20 00:00:00',0,0),(109,'consultant01',1,'2023-02-14 10:57:55','2023-02-21 00:00:00',1,0),(110,'consultant01',5,'2023-02-14 11:11:46','2023-02-21 00:00:00',0,0),(112,'test',1,'2023-02-14 12:44:33','2023-02-21 00:00:00',2,1),(113,'test',4,'2023-02-14 12:45:34','2023-02-21 00:00:00',0,0),(114,'test',6,'2023-02-14 12:45:37','2023-02-21 00:00:00',1,0),(115,'sanghwa1103',4,'2023-02-14 12:59:01','2023-02-21 00:00:00',2,0),(116,'sanghwa1103',3,'2023-02-14 13:19:01','2023-02-21 00:00:00',1,0),(120,'david',5,'2023-02-14 15:12:54','2023-02-21 00:00:00',2,0),(123,'seokjin',2,'2023-02-14 17:20:56','2023-02-21 00:00:00',1,0),(124,'kakao2665359733',2,'2023-02-14 20:23:34','2023-02-21 00:00:00',1,0),(125,'kakao2665359733',5,'2023-02-14 20:24:04','2023-02-21 00:00:00',0,0),(126,'kakao2665359733',1,'2023-02-14 20:33:56','2023-02-21 00:00:00',2,0),(127,'google113134987904705544342',5,'2023-02-14 21:55:20','2023-02-21 00:00:00',1,0),(128,'google113134987904705544342',2,'2023-02-14 21:57:24','2023-02-21 00:00:00',1,0),(129,'seokjin',3,'2023-02-15 09:35:53','2023-02-22 00:00:00',1,0),(130,'kakao2666807704',5,'2023-02-15 11:12:30','2023-02-22 00:00:00',0,0),(131,'david',2,'2023-02-15 14:37:46','2023-02-22 00:00:00',0,0),(138,'consultant01',6,'2023-02-15 16:19:38','2023-02-22 00:00:00',0,0),(139,'kakao2667268719',5,'2023-02-15 16:44:55','2023-02-22 00:00:00',1,0),(141,'google118419579175738030682',3,'2023-02-16 08:53:08','2023-02-23 00:00:00',0,0),(142,'google118419579175738030682',6,'2023-02-16 08:57:30','2023-02-23 00:00:00',0,0),(143,'hello',2,'2023-02-16 09:11:27','2023-02-23 00:00:00',0,0),(144,'Sangwha',5,'2023-02-16 09:24:16','2023-02-23 00:00:00',1,1),(145,'testtest',5,'2023-02-16 09:26:48','2023-02-23 00:00:00',0,0),(150,'aaaa',5,'2023-02-16 10:30:02','2023-02-23 00:00:00',1,1),(152,'google117065706544617987334',2,'2023-02-16 10:45:25','2023-02-23 00:00:00',1,1),(153,'david2',1,'2023-02-16 10:54:01','2023-02-23 00:00:00',1,1),(157,'kakao2658115440',4,'2023-02-16 11:11:38','2023-02-23 00:00:00',1,1),(158,'kakao2657457735',5,'2023-02-16 12:01:18','2023-02-23 00:00:00',0,0),(159,'kakao2668425628',4,'2023-02-16 13:19:22','2023-02-23 00:00:00',1,1),(160,'najul',5,'2023-02-16 13:59:42','2023-02-23 00:00:00',1,1),(161,'google113316588345529428078',1,'2023-02-16 15:05:13','2023-02-23 00:00:00',0,0),(162,'google103875718875945401236',6,'2023-02-16 15:06:12','2023-02-23 00:00:00',1,1);
/*!40000 ALTER TABLE `user_playlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50003 TRIGGER `insert_user_playlist` BEFORE INSERT ON `user_playlist` FOR EACH ROW begin
		set new.start_date = now();
		set new.end_date = date(date_add(new.start_date, interval 7 day));
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb3 */ ;
/*!50003 SET character_set_results = utf8mb3 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/  /*!50003 TRIGGER `update_done` AFTER UPDATE ON `user_playlist` FOR EACH ROW begin
	# 재시도 시작
	if new.done!=5 and old.end_date != new.end_date then # done!=5 && endDate가 바뀌었을 때 -> 재시도
		update current_stat set replay_cnt = replay_cnt + 1 where user_id = new.user_id;
	elseif new.done = 5 and new.end_date<= now() then
        
        # 재시도 확인
		if date(date_add(new.start_date, interval 7 day)) < new.end_date then
			update current_stat set replay_complete_cnt = replay_complete_cnt+1 where user_id = new.user_id;
        end if;   
        
        # 플레이리스트 카운트 증가시키기
        
        if new.playlist_id = 1 then
			update current_stat set p1_cnt = p1_cnt+1 where user_id = new.user_id;
		elseif new.playlist_id = 2 then
			update current_stat set p2_cnt = p2_cnt+1 where user_id = new.user_id;
		elseif new.playlist_id = 3 then
			update current_stat set p3_cnt = p3_cnt+1 where user_id = new.user_id;
		elseif new.playlist_id = 4 then
			update current_stat set p4_cnt = p4_cnt+1 where user_id = new.user_id;
		elseif new.playlist_id = 5 then
			update current_stat set p5_cnt = p5_cnt+1 where user_id = new.user_id;
		else 
			update current_stat set p6_cnt = p6_cnt+1 where user_id = new.user_id;
		end if;
        
        # 카테고리  카운트 증가시키기
		select category_id into @category_id from playlist where playlist_id = new.playlist_id; 
        
		if @category_id = 1 then
			update current_stat set health_cnt = health_cnt+1 where user_id = new.user_id;
		elseif @category_id = 2 then
			update current_stat set relation_cnt = relation_cnt+1 where user_id = new.user_id;
		else
			update current_stat set happy_cnt = happy_cnt+1 where user_id = new.user_id;
		end if;
        
        # 끝난 시간이 5일째인지 7일째인지
        
        if date(date_add(new.start_date, interval 4 day)) = date(new.end_date) then
			update current_stat set fifthday_complete_cnt = fifthday_complete_cnt+1 where user_id = new.user_id;
		elseif date(date_add(new.start_date, interval 6 day)) = date(new.end_date) then
			update current_stat set lastday_complete_cnt = lastday_complete_cnt+1 where user_id = new.user_id;
		end if;
	end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping events for database 'moti'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `reset_today` */;
DELIMITER ;;
ALTER DATABASE `moti` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'Asia/Seoul' */ ;;
/*!50106 CREATE*/  /*!50106 EVENT `reset_today` ON SCHEDULE EVERY 1 DAY STARTS '2023-02-02 00:00:00' ON COMPLETION NOT PRESERVE ENABLE COMMENT '미션 수행 여부 초기화' DO update user_playlist set today = 0 where end_date>now() */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
ALTER DATABASE `moti` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'moti'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 15:25:37
