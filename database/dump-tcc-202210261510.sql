-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: tcc
-- ------------------------------------------------------
-- Server version	8.0.30

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
  `cnpj` varchar(14) NOT NULL,
  `razao_social` varchar(250) DEFAULT NULL,
  `id_gerente` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnpj` (`cnpj`),
  KEY `id_gerente` (`id_gerente`),
  CONSTRAINT `empresa_ibfk_1` FOREIGN KEY (`id_gerente`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'13427756984753','Carros Blumenau',2),(2,'66359790123421','Transport',3),(4,'95738475629475','Caminhão Jaragua ',5);
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
  KEY `id_entregador` (`id_entregador`),
  CONSTRAINT `entrega_ibfk_1` FOREIGN KEY (`id_entregador`) REFERENCES `funcionario` (`id_pessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega`
--

LOCK TABLES `entrega` WRITE;
/*!40000 ALTER TABLE `entrega` DISABLE KEYS */;
INSERT INTO `entrega` VALUES (1,6,'expressa'),(2,7,'economica'),(3,8,'economica'),(4,6,'economica'),(5,7,'expressa'),(6,8,'expressa');
/*!40000 ALTER TABLE `entrega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entrega_item`
--

DROP TABLE IF EXISTS `entrega_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entrega_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_entrega` bigint NOT NULL,
  `id_item` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_entrega` (`id_entrega`),
  KEY `id_item` (`id_item`),
  CONSTRAINT `entrega_item_ibfk_1` FOREIGN KEY (`id_entrega`) REFERENCES `entrega` (`id`),
  CONSTRAINT `entrega_item_ibfk_2` FOREIGN KEY (`id_item`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entrega_item`
--

LOCK TABLES `entrega_item` WRITE;
/*!40000 ALTER TABLE `entrega_item` DISABLE KEYS */;
INSERT INTO `entrega_item` VALUES (1,1,1),(2,2,2),(3,3,3);
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
  KEY `id_supervisor` (`id_supervisor`),
  CONSTRAINT `funcionario_ibfk_1` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `funcionario_ibfk_2` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id`),
  CONSTRAINT `funcionario_ibfk_3` FOREIGN KEY (`id_supervisor`) REFERENCES `funcionario` (`id_pessoa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (4,NULL,1),(5,NULL,2),(6,NULL,1),(7,NULL,4),(8,NULL,2),(19,NULL,4),(20,NULL,2),(21,NULL,1),(22,NULL,2),(23,NULL,1),(24,NULL,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'ASD1234','saiu para entrega','SC-Blumenau-Rua Paris, 136','Maria',18),(2,'ASD123X','saiu para entrega','SC-Blumenau-Rua Paris, 300','Maria',18),(3,'ASD123Z','saiu para entrega','SC-Blumenau-Rua Paris, 300','pedro',9),(4,'083fcec3-a8af-4a83-a1c9-a70d46fc7fdf','Preparando pedido','SC-Joinville-Rua Tijucas, 60','pedro',9),(5,'025f838a-e038-45c8-8123-226ef4e7c5a4','Preparando pedido','SC- Blumenau, Rua São Paulo','Maria',18),(6,'eaf15c7e-e529-47c4-99d2-0c9059cd711e','Preparando pedido','SC-Joinville-Rua Ariribá, 96','Maria',18),(7,'8a91c5ff-dc86-448a-b671-c82571903bd4','Preparando pedido',' Blumenau, Rua São Paulo','Caio',18);
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
  `desabilitado` bit(1) NOT NULL DEFAULT (0),
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`,`login`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Administrador','Empresa','47999493876','08676737951','admin','admin',_binary '\0'),(2,'Gerente1','da Empresa','47993427465','01779264951','Gerente3','Gerente',_binary '\0'),(3,'Gerente2','da Empresa','47993857565','11342737951','GerenteLogin2','GerentePass',_binary '\0'),(4,'Supervisor1','da Empresa','47934290565','01778657835','Supervisor1','GerentePass',_binary '\0'),(5,'Supervisor2','da Empresa','47998890565','01778738575','Supervisor2','GerentePass',_binary '\0'),(6,'Motorista1','da Empresa','47993444365','01773856951','Motorista1','GerentePass',_binary '\0'),(7,'Motorista2','da Empresa','47955690565','01772437643','Motorista2','GerentePass',_binary '\0'),(8,'Motorista3','da Empresa','47993490768','01776437951','Motorista3','GerentePass',_binary '\0'),(9,'userName','lastName','47993490974','01778734326','userLogin','userHardestPassword',_binary '\0'),(10,'userName2','lastName2','47993496587','11478637952','userLogin2','userHardestPassword2',_binary '\0'),(11,'userName3','lastName3','47993490565','01775437951','userLogin3','userHardestPassword3',_binary '\0'),(15,'Jucelia','Pereira','47999490511','01778737951','ju@gmail.com.br','111111',_binary '\0'),(18,'Maria','Tomio','47989544658','11409790940','malu.tomio2006@hotmail.com','121212',_binary '\0'),(19,'Marcos','Pereira','47754244658','11409732431','Funcionario1','funcionario1',_binary '\0'),(20,'Júnior','de Souza','47989536537','77564790940','Funcionario2','funcionario2',_binary '\0'),(21,'Marcela','Fagundes','47867463658','19938430940','Funcionario3','funcionario3',_binary '\0'),(22,'Júlia','Menezes','47989293745','11557388475','Funcionario4','funcionario4',_binary '\0'),(23,'Sara','Silva','47364528576','12405532240','Funcionario5','funcionario5',_binary '\0'),(24,'Ricardo','Costa','47996758392','12248854321','Funcionario6','funcionario6',_binary '\0'),(25,'Caio','silva','47995627453','11755229674','caio@gmail.com','1111111',_binary '\0');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trecho`
--

LOCK TABLES `trecho` WRITE;
/*!40000 ALTER TABLE `trecho` DISABLE KEYS */;
INSERT INTO `trecho` VALUES (1,'SC-Blumenau-Rua Bahia, 25','SC-Blumenau-Rua Paris, 136'),(2,'SC-Blumenau-Rua Pernambuco, 01','SC-Blumenau-Rua Mathias Herkenhoff , 02'),(3,'SC-Joinville-Rua Bahia, 03','SC-Blumenau-Rua Joinville, 300'),(4,'SP-São Paulo','SC-Joinvile Rua Pernambuco, 30'),(5,'RJ- Rio de Janeiro','SC-Itajaí Rua Vieira, 08'),(6,'SP-São Paulo','SC-Joinvile Rua Tijucas, 20'),(7,'RJ- Rio de Janeiro','SC-Joinville Rua Ariribá,222'),(8,'SP-São Paulo','SC-Blumenau-Rua Paris, 300'),(9,'SP-São Paulo','SC-Florianópolis, Rua Caçadores, 44');
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

-- Dump completed on 2022-10-26 15:10:19
