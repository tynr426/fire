CREATE TABLE IF NOT EXISTS `fir_DeviceQr` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `DeviceTypeId` INT NULL COMMENT '设备类型id',
  `Code` VARCHAR(200) NULL COMMENT '二维码内容',
  `QRVirtural` VARCHAR(200) NULL COMMENT '二维码虚拟路径',
  `IsUsed` BIT(1) NULL COMMENT '是否使用',
  `AddTime` DATETIME NULL COMMENT '生成时间',
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;