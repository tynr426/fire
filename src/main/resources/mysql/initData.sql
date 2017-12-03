truncate table com_menu;
/*!40000 ALTER TABLE `com_menu` DISABLE KEYS */;
INSERT INTO `com_menu` VALUES (1,'基础信息','','sys',NULL,NULL,0,1,NULL,NULL,1,NULL),
(2,'管理信息','','zone',NULL,NULL,0,1,NULL,NULL,1,NULL),
(3,'统计信息','','store',NULL,NULL,0,1,NULL,NULL,1,NULL),(101,'基础信息','','',NULL,NULL,1,2,NULL,NULL,1,NULL),
(201,'管理信息','','',NULL,NULL,2,2,NULL,NULL,1,NULL),(301,'统计信息','','',NULL,NULL,3,2,NULL,NULL,1,NULL),
(10101,'管理员列表','/fire/company/manager/toManager.do','',NULL,NULL,101,3,NULL,NULL,1,NULL),
(10102,'公司信息','/fire/company/manager/toCompany.do','',NULL,NULL,101,3,NULL,NULL,1,NULL),
(20101,'设备列表','/fire/company/device/toDevice.do','',NULL,NULL,201,3,1,NULL,1,NULL),
(20102,'维修记录','/fire/company/device/toRepairrecord.do','',NULL,NULL,201,3,3,NULL,1,NULL),
(20103,'任务列表','/fire/company/assignment/toAssignment.do','',NULL,NULL,201,3,4,NULL,1,NULL),
(20104,'检查报告','/fire/company/checkdevice/toCheckDevice.do',NULL,NULL,NULL,201,3,2,NULL,1,NULL),
(30101,'设备数量概况','/fire/company/device/toCompanyDeviceNumSummary.do','',NULL,NULL,301,3,NULL,NULL,1,NULL),
(30102,'设备整改及时率','/fire/company/assignment/toRectificationRate.do','',NULL,NULL,301,3,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `com_menu` ENABLE KEYS */;
UNLOCK TABLES;