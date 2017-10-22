/*====================================*/
/* Table: fir_menu    菜单表       */
/*====================================*/
create table fir_Menu
(
    Id    int not null auto_increment,
    ParentId    int,
    MenuName    varchar(50) not null,
    Link    varchar(100),
    CssClass    varchar(50),
    Icon    varchar(200),
    Src    varchar(200),
    Layer    int,
    Reorder    int,
    Target    varchar(20),
    Status    int not null default 1,
    Remark    text,
    primary key (Id)
);

/*====================================*/
/* Table: MenuRelation 菜单关联          */
/*====================================*/

CREATE TABLE IF NOT EXISTS `fir_MenuRelation` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `CompanyId` INT NULL,
  `ManagerId` INT NULL,
  `MenuIds` VARCHAR(2000) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `fir_CheckDevice` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `DeviceId` INT NULL,
  `DeviceTypeId` INT NULL,
  `CompanyId` INT NULL COMMENT '	',
  `ManagerId` INT NULL,
  `ManagerName` VARCHAR(100) NULL,
  `Description` VARCHAR(200) NULL COMMENT '总是描述',
  `Certificate` VARCHAR(100) NULL COMMENT '凭证',
  `SeverityLevel` INT NULL COMMENT '总是严重级别',
  `AddTime` DATETIME NULL COMMENT '录入时间 ',
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `fir_Assignment` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `CompanyId` INT NULL,
  `FromManagerId` INT NULL COMMENT '指派人',
  `ToManagerId` INT NULL COMMENT '指x派给',
  `CheckId` INT NULL COMMENT '检查id',
  `PredictTime` DATETIME NULL COMMENT '预计时间',
  `Remark` VARCHAR(200) NULL COMMENT '备注',
  `AddTime` DATETIME NULL,
  `Status` INT NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `fir_RepairRecord` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `AssignmentId` INT NULL COMMENT '作务id',
  `DeviceId` INT NULL COMMENT '设备id',
  `DeviceTypeId` INT NULL COMMENT '设备类型id',
  `IsFinish` BIT(1) NULL COMMENT '是否完成',
  `Certificate` VARCHAR(100) NULL COMMENT '凭证',
  `Description` VARCHAR(200) NULL COMMENT '描述',
  `Status` INT NULL,
  `AddTime` DATETIME NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;



