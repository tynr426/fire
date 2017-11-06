/* 授权表 */
create table com_authbind
(
    Id    int not null auto_increment,
    CompanyId    int not null,
    ManagerId    int not null,
    UnionId    varchar(200),
    Guid    varchar(50),
    OpenId    varchar(200) not null,
    NickName    varchar(50),
    Sex    smallint,
    Face    varchar(255),
    IntegType    varchar(20),
    AuthoreTime    datetime,
    IsReceiveMsg    bit,
    Subscribe    smallint,
    Remark    text,
    primary key (Id)
);
/*====================================*/
/* Table: soc_wechataccount           */
/*====================================*/
create table soc_wechataccount
(
    Id    int not null auto_increment,
    FKId    int not null,
    FKFlag    smallint not null,
    Name    varchar(100) not null,
    HeaderSrc    varchar(500),
    OriginalId    varchar(100),
    Account    varchar(100),
    Catering    smallint,
    BindAccount    varchar(200),
    OpenId    varchar(200),
    AppId    varchar(200),
    Secret    varchar(200),
    Token    varchar(200),
    EncodingAESKey    varchar(200),
    AccessToken    varchar(1000),
    AccessTime    datetime,
    ExpireTime    datetime,
    Country    varchar(100),
    Province    varchar(100),
    City    varchar(100),
    Area    varchar(100),
    Intro    varchar(1000),
    Followers    int,
    Qrcode    varchar(500),
    Industry    varchar(200),
    Config    text,
    Status    smallint default 1,
    AddTime    datetime,
    Remark    text,
    FKWay    smallint not null default 1,
    primary key (Id)
);

-- MySQL dump 10.16  Distrib 10.1.19-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.19-MariaDB

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
-- Table structure for table `adm_account`
--

DROP TABLE IF EXISTS `adm_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `adm_account` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `UserName` varchar(20) NOT NULL COMMENT '用户名',
  `Password` varchar(256) NOT NULL COMMENT '登录密码',
  `NickName` varchar(50) DEFAULT NULL COMMENT '显示名称',
  `Email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `Mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `Errors` smallint(6) DEFAULT NULL COMMENT '错误次数',
  `ErrorTime` datetime DEFAULT NULL COMMENT '错误时间',
  `LoginIP` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `LoginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `LastLoginIP` varchar(20) DEFAULT NULL COMMENT '上次登录IP',
  `LastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `LoginCount` int(11) DEFAULT NULL COMMENT '登录次数',
  `Question` varchar(100) DEFAULT NULL COMMENT '问题',
  `Answer` varchar(100) DEFAULT NULL COMMENT '回答',
  `Status` int(11) DEFAULT '1' COMMENT '状态',
  `UserId` int(11) DEFAULT NULL COMMENT '添加者',
  `AddTime` datetime DEFAULT NULL COMMENT '添加时间',
  `IP` varchar(30) DEFAULT NULL COMMENT '添加IP',
  `Remark` text COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='管理账号';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adm_account`
--

LOCK TABLES `adm_account` WRITE;
/*!40000 ALTER TABLE `adm_account` DISABLE KEYS */;
INSERT INTO `adm_account` VALUES (4,'admin','21232f297a57a5a743894a0e4a801fc3','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `adm_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `com_company`
--

DROP TABLE IF EXISTS `com_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_company` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) NOT NULL COMMENT '名称',
  `Code` varchar(50) NOT NULL COMMENT '代码',
  `Logo` varchar(255) DEFAULT NULL COMMENT 'logo标志',
  `Intro` varchar(4000) DEFAULT NULL COMMENT '单位简介',
  `Industry` int(11) DEFAULT NULL COMMENT '单位主营',
  `Province` varchar(200) DEFAULT NULL COMMENT '省',
  `City` varchar(200) DEFAULT NULL COMMENT '市',
  `Area` varchar(200) DEFAULT NULL COMMENT '区县',
  `Address` varchar(500) DEFAULT NULL COMMENT '地址',
  `Passed` smallint(6) DEFAULT '0' COMMENT '是否通过审核,-1拒绝,1通过,0审核中',
  `Status` smallint(6) DEFAULT NULL COMMENT '状态',
  `Reorder` int(11) DEFAULT NULL COMMENT '显示顺序',
  `AddTime` datetime DEFAULT NULL COMMENT '入驻时间',
  `Remark` text COMMENT '备注',
  `LevelId` int(11) DEFAULT NULL COMMENT '等级id',
  `Serial` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Tel` varchar(15) DEFAULT NULL,
  `SN` varchar(100) DEFAULT NULL,
  `Atten` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='供应商(Supplier)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_company`
--

LOCK TABLES `com_company` WRITE;
/*!40000 ALTER TABLE `com_company` DISABLE KEYS */;
INSERT INTO `com_company` VALUES (4,'春卷','adf',NULL,NULL,NULL,'天津市','天津市','东丽区','打撒',0,1,NULL,NULL,NULL,NULL,NULL,NULL,'1213446324','05c2ce3f9c6d41fe8d606d63f578a2f7','发生'),(5,'感受到111','sdf',NULL,NULL,NULL,'北京市','北京市','大兴区','感受到',0,1,NULL,NULL,NULL,NULL,NULL,NULL,'1312431254','b2ea2446a4ba4fbe859491d0853e47c0','还玩个12');
/*!40000 ALTER TABLE `com_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `com_companylevel`
--

DROP TABLE IF EXISTS `com_companylevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_companylevel` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) NOT NULL COMMENT '等级名称',
  `PreviousId` int(11) NOT NULL COMMENT '上一级Id,第一级的上一级id为0',
  `Icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `Description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `IsDefault` bit(1) DEFAULT NULL COMMENT '是否默认值',
  `AddTime` datetime DEFAULT NULL COMMENT '添加时间',
  `Status` smallint(6) DEFAULT NULL COMMENT '状态',
  `Remark` text COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商等级(SupplierLevel)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_companylevel`
--

LOCK TABLES `com_companylevel` WRITE;
/*!40000 ALTER TABLE `com_companylevel` DISABLE KEYS */;
/*!40000 ALTER TABLE `com_companylevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `com_manager`
--

DROP TABLE IF EXISTS `com_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_manager` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyId` int(11) NOT NULL COMMENT '单位Id',
  `UserName` varchar(100) NOT NULL COMMENT '用户名',
  `Password` varchar(512) NOT NULL COMMENT '密码',
  `Position` varchar(100) DEFAULT NULL COMMENT '职位',
  `Name` varchar(100) DEFAULT NULL COMMENT '姓名',
  `NickName` varchar(100) DEFAULT NULL COMMENT '昵称',
  `Face` varchar(512) DEFAULT NULL COMMENT '头像',
  `Email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `Mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `Errors` smallint(6) DEFAULT NULL COMMENT '错误次数',
  `ErrorTime` datetime DEFAULT NULL COMMENT '错误时间',
  `LoginIP` varchar(20) DEFAULT NULL COMMENT '登录IP',
  `LoginTime` datetime DEFAULT NULL COMMENT '登录时间',
  `LastLoginIP` varchar(20) DEFAULT NULL COMMENT '上次登录IP',
  `LastLoginTime` datetime DEFAULT NULL COMMENT '上次登录时间',
  `LoginCount` int(11) DEFAULT '0' COMMENT '登录次数',
  `Question` varchar(100) DEFAULT NULL COMMENT '问题',
  `Answer` varchar(100) DEFAULT NULL COMMENT '回答',
  `Reorder` int(11) DEFAULT '0' COMMENT '显示顺序',
  `Status` int(11) DEFAULT '1' COMMENT '状态',
  `AddTime` datetime DEFAULT NULL COMMENT '添加时间',
  `Remark` text COMMENT '备注',
  `UserId` int(11) NOT NULL COMMENT '添加人',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='管理员(Manager)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_manager`
--

LOCK TABLES `com_manager` WRITE;
/*!40000 ALTER TABLE `com_manager` DISABLE KEYS */;
INSERT INTO `com_manager` VALUES (7,4,'tyn','96e79218965eb72c92a549dd5a330112','暗室逢灯','水电费',NULL,NULL,'tdeis','1351355132',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,0),(8,4,'erety','96e79218965eb72c92a549dd5a330112','asffasfs','egahde',NULL,NULL,'afsfasd','afsas',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,1),(10,4,'18985214555DdD','96e79218965eb72c92a549dd5a330112','广大','dDDD',NULL,NULL,'广大','大概',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,1),(11,5,'power','96e79218965eb72c92a549dd5a330112',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,0);
/*!40000 ALTER TABLE `com_manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `com_managermenu`
--

DROP TABLE IF EXISTS `com_managermenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_managermenu` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CompanyId` int(11) NOT NULL COMMENT '店铺Id',
  `Managerid` int(11) NOT NULL COMMENT '管理员id',
  `MenuIds` text COMMENT '菜单id集',
  `Remark` text,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员菜单(ManagerMenu)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_managermenu`
--

LOCK TABLES `com_managermenu` WRITE;
/*!40000 ALTER TABLE `com_managermenu` DISABLE KEYS */;
/*!40000 ALTER TABLE `com_managermenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `com_menu`
--

DROP TABLE IF EXISTS `com_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `com_menu` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `MenuName` varchar(50) NOT NULL COMMENT '菜单名',
  `Link` varchar(100) DEFAULT NULL COMMENT '菜单链接',
  `CssClass` varchar(50) DEFAULT NULL COMMENT '样式名称',
  `Icon` varchar(200) DEFAULT NULL COMMENT '图标',
  `Src` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `ParentId` int(11) DEFAULT '0' COMMENT '父级编号',
  `Layer` int(11) DEFAULT NULL COMMENT '层级',
  `Reorder` int(11) DEFAULT NULL COMMENT '排序',
  `Target` varchar(20) DEFAULT NULL COMMENT '打开目标',
  `Status` int(11) DEFAULT '1' COMMENT '-1表示删除,0表示禁用,1表示正常',
  `Remark` text COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商菜单(Menu)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_menu`
--

LOCK TABLES `com_menu` WRITE;
/*!40000 ALTER TABLE `com_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `com_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fir_device`
--

DROP TABLE IF EXISTS `fir_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fir_device` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceTypeId` int(11) NOT NULL,
  `Model` varchar(100) NOT NULL COMMENT '型号',
  `Manufacturer` varchar(100) NOT NULL COMMENT '制造商',
  `Spec` varchar(50) NOT NULL COMMENT '规格',
  `UseTime` datetime DEFAULT NULL,
  `AddTime` datetime DEFAULT NULL,
  `Buildings` int(11) DEFAULT NULL,
  `Floor` int(11) DEFAULT NULL,
  `Position` varchar(20) DEFAULT NULL,
  `Passageway` varchar(100) DEFAULT NULL,
  `Detail` varchar(200) DEFAULT NULL,
  `CompanyId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fir_device`
--

LOCK TABLES `fir_device` WRITE;
/*!40000 ALTER TABLE `fir_device` DISABLE KEYS */;
INSERT INTO `fir_device` VALUES (2,5,'dsa','挺过去','发发生2',NULL,'2017-10-02 00:00:00',2,-2,'east','','',4),(3,5,'发光时代','任何事','换人',NULL,'2017-10-02 00:00:00',2,15,'west','1','FGAS',4);
/*!40000 ALTER TABLE `fir_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fir_deviceparametervalue`
--

DROP TABLE IF EXISTS `fir_deviceparametervalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fir_deviceparametervalue` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `DeviceId` int(11) DEFAULT NULL,
  `DeviceTypeId` int(11) DEFAULT NULL,
  `ParameterId` int(11) DEFAULT NULL,
  `Value` varchar(200) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fir_deviceparametervalue`
--

LOCK TABLES `fir_deviceparametervalue` WRITE;
/*!40000 ALTER TABLE `fir_deviceparametervalue` DISABLE KEYS */;
INSERT INTO `fir_deviceparametervalue` VALUES (4,2,5,1,'{\"parameter_1_0\":\"31\",\"parameter_1_1\":\"3\",\"parameter_1_2\":\"3\"}',NULL),(5,2,5,2,'{\"parameter_2\":\"120\"}',NULL),(6,2,5,3,'{\"parameter_3\":\"水\"}',NULL),(55,3,5,1,'{\"parameter_1_0\":\"13\",\"parameter_1_1\":\"12\",\"parameter_1_2\":\"13\"}',NULL),(56,3,5,2,'{\"parameter_2\":\"100\"}',NULL),(57,3,5,3,'{\"parameter_3\":\"泡沫混合液\"}',NULL);
/*!40000 ALTER TABLE `fir_deviceparametervalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fir_devicetype`
--

DROP TABLE IF EXISTS `fir_devicetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fir_devicetype` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Status` int(11) DEFAULT NULL,
  `UseTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fir_devicetype`
--

LOCK TABLES `fir_devicetype` WRITE;
/*!40000 ALTER TABLE `fir_devicetype` DISABLE KEYS */;
INSERT INTO `fir_devicetype` VALUES (5,'水池',1,'2017-09-13 00:00:00'),(6,'不担心',1,'2017-09-06 00:00:00'),(7,'QTGEA',1,'2017-09-06 00:00:00'),(8,'WEG',1,'2017-09-12 00:00:00');
/*!40000 ALTER TABLE `fir_devicetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fir_devicetypeparameter`
--

DROP TABLE IF EXISTS `fir_devicetypeparameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fir_devicetypeparameter` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `Description` varchar(200) DEFAULT NULL COMMENT '描述，可以做为待填项最前面的文字',
  `DataType` varchar(20) DEFAULT NULL COMMENT '用于标识前段输入值的类型，并可以判断出前段展现的控件',
  `EditorType` varchar(20) DEFAULT NULL COMMENT '编辑器类型',
  `Candidate` varchar(800) DEFAULT NULL COMMENT '用Json格式来配置待选项，比如一组Rdiobox存为{text:value,是:true}',
  `Unit` varchar(200) DEFAULT NULL COMMENT '单位',
  `Reorder` int(11) DEFAULT NULL COMMENT '排序',
  `Required` smallint(6) DEFAULT NULL COMMENT '必填',
  `Status` int(11) DEFAULT NULL COMMENT '状态',
  `Remark` varchar(8000) DEFAULT NULL COMMENT '备注',
  `DeviceTypeId` int(11) NOT NULL COMMENT '设备类型',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统所有配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fir_devicetypeparameter`
--

LOCK TABLES `fir_devicetypeparameter` WRITE;
/*!40000 ALTER TABLE `fir_devicetypeparameter` DISABLE KEYS */;
INSERT INTO `fir_devicetypeparameter` VALUES (1,'水池尺寸',NULL,'texts','[{\"Field\":\"长\"},{\"Field\":\"宽\"},{\"Field\":\"高\"}]','mm',1,NULL,1,NULL,5),(2,'管道规格',NULL,'select','[{\"Field\":\"100\",\"Value\":\"100\"},{\"Field\":\"120\",\"Value\":\"120\"}]','mm',2,NULL,1,NULL,5),(3,'适用介质',NULL,'select','[{\"Field\":\"水\",\"Value\":\"水\"},{\"Field\":\"泡沫混合液\",\"Value\":\"泡沫混合液\"}]','',3,NULL,1,NULL,5);
/*!40000 ALTER TABLE `fir_devicetypeparameter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-05 11:40:09
