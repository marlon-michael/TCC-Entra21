-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: tcc
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carro`
--

DROP TABLE IF EXISTS `carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) NOT NULL,
  `placa` varchar(15) NOT NULL,
  `id_empresa` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_empresa` (`id_empresa`),
  CONSTRAINT `carro_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carro`
--

LOCK TABLES `carro` WRITE;
/*!40000 ALTER TABLE `carro` DISABLE KEYS */;
INSERT INTO `carro` VALUES (1,'CAMINHAO','ABCD1234Z',1),(2,'VAN','ABCD1235Y',1),(3,'CAMINHAO','ABCD1236X',2);
/*!40000 ALTER TABLE `carro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `razao_social` varchar(250) DEFAULT NULL,
  `id_gerente` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_gerente` (`id_gerente`),
  CONSTRAINT `empresa_ibfk_1` FOREIGN KEY (`id_gerente`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'viver',1),(2,'viver tambem',4);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega`
--

DROP TABLE IF EXISTS `entrega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_entregador` bigint NOT NULL,
  `tipo_entrega` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `entrega_FK` (`id_entregador`),
  CONSTRAINT `entrega_FK` FOREIGN KEY (`id_entregador`) REFERENCES `funcionario` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
INSERT INTO `entrega` VALUES (1,2,'expressa'),(2,2,'economica'),(3,3,'economica');
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega_item`
--

DROP TABLE IF EXISTS `entrega_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega_item` (
  `id_entrega` bigint NOT NULL,
  `id_item` bigint NOT NULL,
  PRIMARY KEY (`id_entrega`,`id_item`),
  KEY `id_item` (`id_item`),
  CONSTRAINT `entrega_item_ibfk_1` FOREIGN KEY (`id_entrega`) REFERENCES `entrega` (`id`),
  CONSTRAINT `entrega_item_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega_item`
--

LOCK TABLES `entrega_item` WRITE;
/*!40000 ALTER TABLE `entrega_item` DISABLE KEYS */;
INSERT INTO `entrega_item` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `entrega_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega_trecho`
--

DROP TABLE IF EXISTS `entrega_trecho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega_trecho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_entrega` bigint NOT NULL,
  `id_trecho` bigint NOT NULL,
  `id_carro` bigint NOT NULL,
  `completo` bit(1) DEFAULT (0),
  `data_inicio` datetime DEFAULT NULL,
  `data_fim` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_carro` (`id_carro`),
  KEY `id_entrega` (`id_entrega`),
  KEY `id_trecho` (`id_trecho`),
  CONSTRAINT `entrega_trecho_ibfk_1` FOREIGN KEY (`id_carro`) REFERENCES `carro` (`id`),
  CONSTRAINT `entrega_trecho_ibfk_2` FOREIGN KEY (`id_entrega`) REFERENCES `entrega` (`id`),
  CONSTRAINT `entrega_trecho_ibfk_3` FOREIGN KEY (`id_trecho`) REFERENCES `trecho` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega_trecho`
--

LOCK TABLES `entrega_trecho` WRITE;
/*!40000 ALTER TABLE `entrega_trecho` DISABLE KEYS */;
INSERT INTO `entrega_trecho` VALUES (1,1,1,1,_binary '\0','1998-01-23 12:45:56',NULL),(2,2,2,1,_binary '\0','1998-01-23 12:45:56',NULL),(3,3,3,2,_binary '','1998-01-23 12:45:56','1998-01-23 12:45:57');
/*!40000 ALTER TABLE `entrega_trecho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id_pessoa` bigint NOT NULL,
  `id_supervisor` bigint DEFAULT NULL,
  `id_empresa` bigint NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  KEY `id_empresa` (`id_empresa`),
  KEY `funcionario_FK` (`id_supervisor`),
  CONSTRAINT `funcionario_FK` FOREIGN KEY (`id_supervisor`) REFERENCES `funcionario` (`id_pessoa`),
  CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `funcionario_ibfk_3` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (2,NULL,1),(3,2,1),(4,3,2);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `localizador` varchar(100) NOT NULL,
  `status` varchar(50) DEFAULT (_utf8mb4'Em espera'),
  `local_entrega` varchar(300) NOT NULL,
  `nome_recebedor` varchar(50) NOT NULL,
  `id_pessoa` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `localizador` (`localizador`),
  KEY `id_pessoa` (`id_pessoa`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'ASD1234','saiu para entrega','SC-Blumenau-Rua Paris, 136','Feleipa',NULL),(2,'ASD123X','saiu para entrega','SC-Blumenau-Rua Paris, 300','Maira',NULL),(3,'ASD123Z','saiu para entrega','SC-Blumenau-Rua Paris, 300','Pedrionh',4);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(50) NOT NULL,
  `telefone` varchar(50) NOT NULL,
  `cpf` char(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `senha` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`,`login`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Adimininastro','doZap','XXX','XXXXXXXXX','admin','admin'),(2,'userName','lastName','4799999999X','9999999999X','userLogin','userHardestPassword'),(3,'userName2','lastName2','4799999999Y','9999999999Y','userLogin2','userHardestPassword2'),(4,'userName3','lastName3','4799999999Z','9999999999Z','userLogin3','userHardestPassword3');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trecho`
--

DROP TABLE IF EXISTS `trecho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trecho` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `local_inicio` varchar(250) NOT NULL,
  `local_fim` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trecho`
--

LOCK TABLES `trecho` WRITE;
/*!40000 ALTER TABLE `trecho` DISABLE KEYS */;
INSERT INTO `trecho` VALUES (1,'SC-Blumenau-Rua Bahia, 25','SC-Blumenau-Rua Paris, 136'),(2,'SC-Blumenau-Rua Bahia, 01','SC-Blumenau-Rua Paris, 300'),(3,'SC-Blumenau-Rua Bahia, 03','SC-Blumenau-Rua Paris, 300');
/*!40000 ALTER TABLE `trecho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tcc'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-15 19:24:54
