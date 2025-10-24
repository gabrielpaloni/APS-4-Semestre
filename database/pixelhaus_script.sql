-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pixelhaus
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_comprador` int NOT NULL,
  `id_jogo` int NOT NULL,
  `data_compra` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `preco_pago` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_comprador` (`id_comprador`),
  KEY `id_jogo` (`id_jogo`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_comprador`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`id_jogo`) REFERENCES `jogos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,1,12,'2025-10-22 21:13:28',249.99),(2,2,4,'2025-10-22 21:13:28',99.90),(3,4,15,'2025-10-22 21:13:28',27.99),(4,5,9,'2025-10-22 21:13:28',319.00),(5,1,14,'2025-10-22 21:13:28',24.99),(6,10,1,'2025-10-24 02:14:33',59.90),(7,2,2,'2025-10-21 18:14:33',47.50),(8,5,3,'2025-10-21 14:14:33',69.99),(9,9,4,'2025-10-21 16:14:33',99.90),(10,5,5,'2025-10-21 13:14:33',36.99),(11,10,6,'2025-10-24 02:14:33',29.90),(12,4,7,'2025-10-22 14:14:33',199.00),(13,6,8,'2025-10-22 19:14:33',299.00),(14,5,9,'2025-10-21 19:14:33',319.00),(15,7,10,'2025-10-21 21:14:33',79.90),(16,9,11,'2025-10-23 13:14:33',49.99),(17,4,12,'2025-10-21 06:14:33',249.99),(18,9,13,'2025-10-22 19:14:33',19.99),(19,7,14,'2025-10-21 21:14:33',24.99),(20,9,15,'2025-10-23 16:14:33',27.99),(21,1,1,'2025-10-21 09:14:39',59.90),(22,5,6,'2025-10-22 12:14:39',29.90),(23,7,8,'2025-10-21 22:14:39',299.00),(24,2,5,'2025-10-23 06:14:39',36.99),(25,5,7,'2025-10-21 14:14:39',199.00);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tg_processa_compra_completa` BEFORE INSERT ON `compras` FOR EACH ROW BEGIN
    DECLARE v_preco_jogo DECIMAL(10, 2);
    SELECT preco INTO v_preco_jogo FROM jogos WHERE id = NEW.id_jogo;
    SET NEW.preco_pago = v_preco_jogo;

    UPDATE jogos
    SET total_downloads = total_downloads + 1
    WHERE id = NEW.id_jogo;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (3,'Aventura e RPG'),(4,'Competição Online'),(1,'FPS: Tático/Guerra em Larga Escala'),(2,'Sandbox e Criação'),(5,'Simulação');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jogo_genero`
--

DROP TABLE IF EXISTS `jogo_genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jogo_genero` (
  `id_jogo` int NOT NULL,
  `id_genero` int NOT NULL,
  PRIMARY KEY (`id_jogo`,`id_genero`),
  KEY `id_genero` (`id_genero`),
  CONSTRAINT `jogo_genero_ibfk_1` FOREIGN KEY (`id_jogo`) REFERENCES `jogos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `jogo_genero_ibfk_2` FOREIGN KEY (`id_genero`) REFERENCES `generos` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jogo_genero`
--

LOCK TABLES `jogo_genero` WRITE;
/*!40000 ALTER TABLE `jogo_genero` DISABLE KEYS */;
INSERT INTO `jogo_genero` VALUES (1,1),(3,1),(7,1),(8,1),(10,1),(13,1),(4,2),(5,2),(6,2),(4,3),(5,3),(12,3),(14,3),(15,3),(1,4),(2,4),(3,4),(9,4),(10,4),(11,4),(13,4),(9,5),(14,5);
/*!40000 ALTER TABLE `jogo_genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jogo_plataforma`
--

DROP TABLE IF EXISTS `jogo_plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jogo_plataforma` (
  `id_jogo` int NOT NULL,
  `id_plataforma` int NOT NULL,
  PRIMARY KEY (`id_jogo`,`id_plataforma`),
  KEY `id_plataforma` (`id_plataforma`),
  CONSTRAINT `jogo_plataforma_ibfk_1` FOREIGN KEY (`id_jogo`) REFERENCES `jogos` (`id`) ON DELETE CASCADE,
  CONSTRAINT `jogo_plataforma_ibfk_2` FOREIGN KEY (`id_plataforma`) REFERENCES `plataformas` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jogo_plataforma`
--

LOCK TABLES `jogo_plataforma` WRITE;
/*!40000 ALTER TABLE `jogo_plataforma` DISABLE KEYS */;
INSERT INTO `jogo_plataforma` VALUES (1,1),(2,1),(3,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(2,2),(4,2),(5,2),(6,2),(7,2),(8,2),(2,3),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,3),(12,3),(13,3),(15,3),(2,4),(5,4),(6,4),(7,4),(8,4),(9,4),(10,4),(11,4),(12,4),(13,4),(15,4),(2,5),(5,5),(7,5),(8,5),(9,5),(10,5),(13,5),(15,5);
/*!40000 ALTER TABLE `jogo_plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jogos`
--

DROP TABLE IF EXISTS `jogos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jogos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_vendedor` int NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `descricao` text,
  `preco` decimal(10,2) NOT NULL,
  `data_lancamento` date DEFAULT NULL,
  `requisitos_sistema` text,
  `data_publicacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `total_downloads` int DEFAULT '0',
  `nome_imagem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_vendedor` (`id_vendedor`),
  CONSTRAINT `jogos_ibfk_1` FOREIGN KEY (`id_vendedor`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jogos`
--

LOCK TABLES `jogos` WRITE;
/*!40000 ALTER TABLE `jogos` DISABLE KEYS */;
INSERT INTO `jogos` VALUES (1,11,'Valorant','Um FPS tático 5x5 com personagens e habilidades únicas.',59.90,'2020-06-02','Windows 7/8/10 64-Bit, 4GB RAM, Intel Core 2 Duo E8400','2025-10-22 21:10:55',2,'Valorant.png'),(2,11,'League Of Legends','Um MOBA competitivo onde duas equipes de cinco campeões se enfrentam.',47.50,'2009-10-27','Windows/macOS, 4GB RAM, 16GB de espaço livre','2025-10-22 21:10:55',1,'lol.png'),(3,11,'Overwatch','Um jogo de tiro em equipe com um elenco diversificado de heróis poderosos.',69.99,'2016-05-24','Windows 10 64-bit, 6GB RAM, NVIDIA GeForce GTX 660','2025-10-22 21:10:55',1,'Overwatch.png'),(4,12,'Minecraft','Um mundo de blocos para construir, minerar e explorar. A imaginação é o limite.',99.90,'2011-11-18','Multiplataforma, requisitos variados','2025-10-22 21:10:55',1,'Minecraft.png'),(5,12,'Terraria','Cave, lute, explore, construa! Um mundo de aventura 2D está em suas mãos.',36.99,'2011-05-16','Multiplataforma, requisitos leves','2025-10-22 21:10:55',2,'Terraria.png'),(6,12,'Roblox','Uma plataforma global que une milhões de pessoas através de experiências 3D compartilhadas.',29.90,'2006-09-01','Multiplataforma, requisitos variados','2025-10-22 21:10:55',2,'Roblox.png'),(7,13,'Battlefield 2042','Um jogo de tiro em primeira pessoa que marca o retorno à icônica guerra total da franquia.',199.00,'2021-11-19','Windows 10 64-bit, 8GB RAM, GeForce GTX 1050 Ti','2025-10-22 21:10:55',2,'Battlefield.png'),(8,13,'Call Of Duty: Modern Warfare III','A guerra mudou. Lute em uma campanha cinematográfica ou entre no multijogador de ponta.',299.00,'2023-11-10','Windows 10 64-bit, 12GB RAM, NVIDIA GeForce GTX 1650','2025-10-22 21:10:55',2,'cod.png'),(9,13,'EA SPORTS FC 24','O Jogo de Todo Mundo, com a experiência mais autêntica de futebol de todos os tempos.',319.00,'2023-09-29','Windows 10 64-bit, 8GB RAM, GeForce GTX 1050 Ti','2025-10-22 21:10:55',1,'fifa.png'),(10,14,'Counter-Strike 2','Um FPS tático que expande a jogabilidade de CS:GO. Precisão e estratégia.',79.90,'2023-09-27','Windows 10 64-bit, 8GB RAM, 1GB VRAM compatível com DirectX 11','2025-10-22 21:10:55',1,'cs.png'),(11,14,'Dead By Daylight','Um jogo de terror multiplayer (4vs1) onde um jogador assume o papel de um assassino.',49.99,'2016-06-14','Windows 10 64-bit, 8GB RAM, GeForce GTX 760 ou AMD HD 8800','2025-10-22 21:10:55',1,'dbd.png'),(12,14,'Hogwarts Legacy','Um RPG de ação imersivo e de mundo aberto ambientado no mundo bruxo de 1800.',249.99,'2023-02-10','Windows 10 64-bit, 16GB RAM, GeForce 1080 Ti','2025-10-22 21:10:55',1,'Hogwarts.png'),(13,15,'Free Fire','Battle Royale de sobrevivência com partidas rápidas de 10 minutos em uma ilha remota.',19.99,'2017-12-04','Android 4.1 ou superior, iOS 9.0 ou superior','2025-10-22 21:10:57',1,'ff.png'),(14,15,'Stardew Valley','Você herdou a antiga fazenda do seu avô. Comece sua nova vida no campo.',24.99,'2016-02-26','Multiplataforma, requisitos leves','2025-10-22 21:10:57',1,'Stardew.png'),(15,15,'Hollow Knight','Explore um vasto reino em ruínas de insetos e heróis. Uma aventura de ação clássica em 2D.',27.99,'2017-02-24','Multiplataforma, requisitos leves','2025-10-22 21:10:57',1,'Hollow.png');
/*!40000 ALTER TABLE `jogos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataformas`
--

DROP TABLE IF EXISTS `plataformas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plataformas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataformas`
--

LOCK TABLES `plataformas` WRITE;
/*!40000 ALTER TABLE `plataformas` DISABLE KEYS */;
INSERT INTO `plataformas` VALUES (2,'Mobile: Android/iOS'),(5,'Nintendo Switch'),(1,'PC: Windows/macOS/Linux'),(3,'PlayStation'),(4,'Xbox');
/*!40000 ALTER TABLE `plataformas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `tipo` enum('comprador','vendedor') NOT NULL,
  `data_cadastro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Mariana Oliveira','mari.oliveira@email.com','mariana#2025','comprador','2025-10-22 21:10:26'),(2,'Lucas Martins','lucas.martins@email.com','lucas@martins','comprador','2025-10-22 21:10:26'),(3,'Beatriz Souza','bia.souza@email.com','bia_souza_pw','comprador','2025-10-22 21:10:26'),(4,'Guilherme Santos','gui.santos@email.com','gui.santos99','comprador','2025-10-22 21:10:26'),(5,'Larissa Pereira','lari.pereira@email.com','lari_pereira!','comprador','2025-10-22 21:10:26'),(6,'Felipe Almeida','felipe.almeida@email.com','felipe_almeida_123','comprador','2025-10-22 21:10:26'),(7,'Juliana Lima','ju.lima@email.com','julima#top','comprador','2025-10-22 21:10:26'),(8,'Rafael Azevedo','rafa.azevedo@email.com','rafa_azevedo@','comprador','2025-10-22 21:10:26'),(9,'Camila Rocha','camila.rocha@email.com','camila.rocha.sec','comprador','2025-10-22 21:10:26'),(10,'Diego Ferreira','diego.ferreira@email.com','diego&ferreira','comprador','2025-10-22 21:10:26'),(11,'Riot Games Store','sales@riot.com','riot!@#store','vendedor','2025-10-22 21:10:26'),(12,'Mojang Studios','sales@mojang.com','mojang_secret_pass','vendedor','2025-10-22 21:10:26'),(13,'EA Games','sales@ea.com','ea$games$2025','vendedor','2025-10-22 21:10:26'),(14,'Valve Corporation','sales@valve.com','valve!corp@2025','vendedor','2025-10-22 21:10:26'),(15,'Indie Creators','contact@indiecreators.com','indie_creators_pw','vendedor','2025-10-22 21:10:26');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-23 23:39:00
