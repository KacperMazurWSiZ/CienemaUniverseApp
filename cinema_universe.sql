-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cinema_universe
-- ------------------------------------------------------
-- Server version	8.0.32
CREATE DATABASE cinema_universe;
USE cinema_universe;
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
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id_movie` int NOT NULL AUTO_INCREMENT,
  `title` varchar(60) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `direction` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_movie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'The Shawshank Redemption','Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.','1994-09-23',142,'Frank Darabont'),(2,'The Godfather','The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.','1972-03-14',175,'Francis Ford Coppola'),(3,'The Godfather: Part II','The early life and career of Vito Corleone in 1920s New York is portrayed while his son, Michael, expands and tightens his grip on his crime syndicate stretching from Lake Tahoe, Nevada to pre-revolution 1958 Cuba.','1974-12-20',202,'Francis Ford Coppola'),(4,'The Dark Knight','When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, the caped crusader must come to terms with one of the greatest psychological tests of his ability to fight injustice.','2008-07-18',152,'Christopher Nolan'),(5,'12 Angry Men','A dissenting juror in a murder trial slowly manages to convince the others that the case is not as obviously clear as it seemed in court.','1957-04-10',96,'Sidney Lumet');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id_reservation` int NOT NULL AUTO_INCREMENT,
  `id_user` int DEFAULT NULL,
  `id_seance` int DEFAULT NULL,
  `id_seat` int DEFAULT NULL,
  PRIMARY KEY (`id_reservation`),
  KEY `id_user_idx` (`id_user`),
  KEY `id_seanse_idx` (`id_seance`),
  KEY `id_seat_idx` (`id_seat`),
  CONSTRAINT `id_seanse` FOREIGN KEY (`id_seance`) REFERENCES `seances` (`id_seance`),
  CONSTRAINT `id_seat` FOREIGN KEY (`id_seat`) REFERENCES `seats` (`id_seat`),
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id_room` int NOT NULL AUTO_INCREMENT,
  `name_room` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_room`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'Merkury'),(2,'Wenus'),(3,'Ziemia'),(5,'Jowisz'),(6,'Saturn'),(7,'Uran');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seances`
--

DROP TABLE IF EXISTS `seances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seances` (
  `id_seance` int NOT NULL AUTO_INCREMENT,
  `id_movie` int DEFAULT NULL,
  `id_room` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `seance_type` varchar(45) DEFAULT NULL,
  `seance_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id_seance`),
  KEY `id_room_idx` (`id_room`),
  KEY `movie_key_idx` (`id_movie`),
  CONSTRAINT `movie_key` FOREIGN KEY (`id_movie`) REFERENCES `movies` (`id_movie`),
  CONSTRAINT `room_key` FOREIGN KEY (`id_room`) REFERENCES `rooms` (`id_room`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seances`
--

LOCK TABLES `seances` WRITE;
/*!40000 ALTER TABLE `seances` DISABLE KEYS */;
INSERT INTO `seances` VALUES (1,1,1,15,'3D','2023-01-09 00:02:00'),(2,1,6,20,'2D','2023-01-09 00:02:00'),(3,2,1,25,'3D','2023-01-16 00:02:00'),(4,3,1,20,'2D','2023-01-25 00:02:00'),(5,4,7,20,'3D','2023-01-15 00:02:00'),(6,5,1,25,'3D','2023-01-01 00:02:00');
/*!40000 ALTER TABLE `seances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `id_seat` int NOT NULL AUTO_INCREMENT,
  `id_room` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `is_free` bit(2) DEFAULT NULL,
  PRIMARY KEY (`id_seat`),
  KEY `id_room_idx` (`id_room`),
  CONSTRAINT `id_room` FOREIGN KEY (`id_room`) REFERENCES `rooms` (`id_room`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,1,'M-1',_binary ''),(2,1,'M-2',_binary ''),(3,1,'M-3',_binary ''),(4,1,'M-4',_binary ''),(6,2,'W-1',_binary ''),(7,2,'W-2',_binary ''),(8,2,'W-3',_binary ''),(9,2,'W-4',_binary ''),(11,3,'Z-1',_binary ''),(12,3,'Z-2',_binary ''),(13,3,'Z-3',_binary ''),(14,5,'J-1',_binary ''),(18,5,'J-2',_binary ''),(19,5,'J-3',_binary ''),(20,6,'S-1',_binary ''),(21,6,'S-2',_binary ''),(23,6,'S-3',_binary ''),(24,7,'U-1',_binary ''),(25,7,'U-2',_binary ''),(26,7,'U-3',_binary '');
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin','$2a$10$EdxWingyNjXXKwslfTqvyOpEt8Ilj.3bCI.Xot3ci7p860zRk64ES','2023-01-01 00:02:00','admin'),(2,'user','user','user','$2a$10$J2Z5fqJzo47xWidR6JHxGOXtfbwQu8cIYWuuLH54CD4jrRw5fQFZ.','2023-01-08 00:02:00','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-09 16:31:19
