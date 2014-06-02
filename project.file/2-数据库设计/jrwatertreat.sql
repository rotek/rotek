/*
Navicat MySQL Data Transfer

Source Server         : JRProject
Source Server Version : 50510
Source Host           : localhost:3306
Source Database       : jrwatertreat

Target Server Type    : MYSQL
Target Server Version : 50510
File Encoding         : 65001

Date: 2014-06-02 18:17:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `agentestimateinfo`
-- ----------------------------
DROP TABLE IF EXISTS `agentestimateinfo`;
CREATE TABLE `agentestimateinfo` (
  `EstimateID` int(11) NOT NULL AUTO_INCREMENT,
  `AgentID` int(11) NOT NULL,
  `DLSXJPJ` char(255) DEFAULT NULL,
  `DLSGLXZ` char(255) DEFAULT NULL,
  PRIMARY KEY (`EstimateID`),
  KEY `AgentID` (`AgentID`),
  CONSTRAINT `agentestimateinfo_ibfk_1` FOREIGN KEY (`AgentID`) REFERENCES `agentinfo` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='代理商评价信息表\r\n代理商星级评价：五星（10分）、四星（8--9分）三星（6--7分）二星（4-5分）一星（<4分）\r\n分数的计算是从10分的基础上减，减分的来源是未处理信息统计表和投诉信息统计表。\r\n代理商管理细则：目前内容固定，增加代理商评价信息的时候写死。如果编辑的话，统一更新所有代理商的，录入内容见文档。';

-- ----------------------------
-- Records of agentestimateinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `agentidentityinfo`
-- ----------------------------
DROP TABLE IF EXISTS `agentidentityinfo`;
CREATE TABLE `agentidentityinfo` (
  `AgentIdentityID` int(11) NOT NULL AUTO_INCREMENT,
  `AgentID` int(11) NOT NULL,
  `DLSZJMC` char(255) DEFAULT NULL,
  `DLSZJYXQ` char(255) DEFAULT NULL,
  `DLSZJZP` char(255) DEFAULT NULL,
  PRIMARY KEY (`AgentIdentityID`),
  KEY `AgentID` (`AgentID`),
  CONSTRAINT `agentidentityinfo_ibfk_1` FOREIGN KEY (`AgentID`) REFERENCES `agentinfo` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='代理商证件信息表';

-- ----------------------------
-- Records of agentidentityinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `agentinfo`
-- ----------------------------
DROP TABLE IF EXISTS `agentinfo`;
CREATE TABLE `agentinfo` (
  `AgentID` int(11) NOT NULL AUTO_INCREMENT,
  `UserRoleID` int(11) NOT NULL,
  `DLSMC` char(255) NOT NULL,
  `DLSTXDZ` char(255) NOT NULL,
  `DLSLXFS` char(255) DEFAULT NULL,
  `DLSLXR` char(255) NOT NULL,
  `DLQY` char(255) DEFAULT NULL,
  `SSJB` char(255) DEFAULT NULL,
  PRIMARY KEY (`AgentID`),
  KEY `UserRoleID` (`UserRoleID`),
  CONSTRAINT `agentinfo_ibfk_1` FOREIGN KEY (`UserRoleID`) REFERENCES `userrole` (`UserRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='基本信息：九如公司业务员负责录入。';

-- ----------------------------
-- Records of agentinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithm1data`
-- ----------------------------
DROP TABLE IF EXISTS `algorithm1data`;
CREATE TABLE `algorithm1data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `AlgorithmTypeID` int(11) NOT NULL,
  `LJBH` char(255) DEFAULT NULL,
  `LJGHSJ` datetime DEFAULT NULL,
  `LJEDYXSJ` int(11) DEFAULT NULL,
  `LJYXSJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID` (`ProjectID`),
  KEY `AlgorithmTypeID` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm1data_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`),
  CONSTRAINT `algorithm1data_ibfk_2` FOREIGN KEY (`AlgorithmTypeID`) REFERENCES `algorithmtype` (`AlgorithmTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='服务超限\r\n1. 累计运行时间（年）达到N年，转化为小时数，记为Na，Na=设置小时，固定值。\r\n2. 服务档案记录的上一次更换时间T，若当前时间记为Td，距离上一次更换的时间差为Ta=(T-Td)（注：转化为小时数），既是在服务档案中对应的零件目前的累计运行时间。\r\n3. Ta>Na时发送提醒。\r\n';

-- ----------------------------
-- Records of algorithm1data
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithm2data`
-- ----------------------------
DROP TABLE IF EXISTS `algorithm2data`;
CREATE TABLE `algorithm2data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `AlgorithmTypeID` int(11) NOT NULL,
  `LJBH` char(11) DEFAULT NULL,
  `LJEDYXSJ` int(11) DEFAULT NULL,
  `LJYXSJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID` (`ProjectID`),
  KEY `AlgorithmTypeID` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm2data_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`),
  CONSTRAINT `algorithm2data_ibfk_2` FOREIGN KEY (`AlgorithmTypeID`) REFERENCES `algorithmtype` (`AlgorithmTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='服务超限\r\n1. 开关量的累计运行时间Ha，单位是小时Na，Na=设置小时，固定值。\r\n2. 计算：Ha>Na时发送提醒。';

-- ----------------------------
-- Records of algorithm2data
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithm3data`
-- ----------------------------
DROP TABLE IF EXISTS `algorithm3data`;
CREATE TABLE `algorithm3data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `AlgorithmTypeID` int(11) NOT NULL,
  `LJBH` char(11) DEFAULT NULL,
  `GLQCESJ` int(11) DEFAULT NULL,
  `GLQCEBFB` int(11) DEFAULT NULL,
  `LJCEYXSJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID` (`ProjectID`),
  KEY `AlgorithmTypeID` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm3data_ibfk_1` FOREIGN KEY (`AlgorithmTypeID`) REFERENCES `algorithmtype` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm3data_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='服务超限\r\n1.（过滤器的）前后压差Pa（当设定相应的开关量存在信号时），大于或等于设定值S Mpa的n%时\r\n2. 此种情况持续存在N分钟，发送提醒。';

-- ----------------------------
-- Records of algorithm3data
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithm4data`
-- ----------------------------
DROP TABLE IF EXISTS `algorithm4data`;
CREATE TABLE `algorithm4data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `AlgorithmTypeID` int(11) NOT NULL,
  `LJBH` char(11) DEFAULT NULL,
  `MLLCESJ` int(11) DEFAULT NULL,
  `MXTSLLBZ` int(11) DEFAULT NULL,
  `MXTSLLLJSJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID` (`ProjectID`),
  KEY `AlgorithmTypeID` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm4data_ibfk_1` FOREIGN KEY (`AlgorithmTypeID`) REFERENCES `algorithmtype` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm4data_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='服务超限\r\n1. 当设定相应的开关量K存在信号时，根据此膜系统产水流量M，产水流量小于设定值S m³时\r\n2. 此种状态持续N分钟以上。';

-- ----------------------------
-- Records of algorithm4data
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithm5data`
-- ----------------------------
DROP TABLE IF EXISTS `algorithm5data`;
CREATE TABLE `algorithm5data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `AlgorithmTypeID` int(11) NOT NULL,
  `LJBH` char(11) DEFAULT NULL,
  `MLLCESJ` int(11) DEFAULT NULL,
  `MXTHSLBZ` int(11) DEFAULT NULL,
  `MXTHSLCELJSJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID` (`ProjectID`),
  KEY `AlgorithmTypeID` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm5data_ibfk_1` FOREIGN KEY (`AlgorithmTypeID`) REFERENCES `algorithmtype` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm5data_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='服务超限\r\n1. 当设定相应的开关量K存在信号时，根据此膜系统产水纯水流量M1和浓水流量M2进行比较计算，如果回收率{纯水流量/（纯水流量+浓水流量）*100%}高于或低于设定值S%时\r\n2. 此种状态持续N分钟以上。';

-- ----------------------------
-- Records of algorithm5data
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithm6data`
-- ----------------------------
DROP TABLE IF EXISTS `algorithm6data`;
CREATE TABLE `algorithm6data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `AlgorithmTypeID` int(11) NOT NULL,
  `LJBH` char(11) DEFAULT NULL,
  `DWSJYLSD` int(11) DEFAULT NULL,
  `DYSYLTJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID` (`ProjectID`),
  KEY `AlgorithmTypeID` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm6data_ibfk_1` FOREIGN KEY (`AlgorithmTypeID`) REFERENCES `algorithmtype` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm6data_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='定时提醒\r\n1. 当月使用量：月累计运行时间*单位运行用量设定值S g/h，其中，月累计运行时间通过运行的开关量信息的累计运行时间计算。\r\n2. 根据当月的使用量提醒建议下个月的使用量：如编号：1号杀菌剂 当月使用量： 请参考！\r\n3. 名称可随意调整。';

-- ----------------------------
-- Records of algorithm6data
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithm8data`
-- ----------------------------
DROP TABLE IF EXISTS `algorithm8data`;
CREATE TABLE `algorithm8data` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `AlgorithmTypeID` int(11) NOT NULL,
  `LJBH` char(11) DEFAULT NULL,
  `MXTCEDDL` int(11) DEFAULT NULL,
  `MXTDDLBZ` int(11) DEFAULT NULL,
  `MXTDDLLJSJ` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProjectID` (`ProjectID`),
  KEY `AlgorithmTypeID` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm8data_ibfk_1` FOREIGN KEY (`AlgorithmTypeID`) REFERENCES `algorithmtype` (`AlgorithmTypeID`),
  CONSTRAINT `algorithm8data_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='服务超限\r\n1. 当设定相应的开关量K存在信号时，根据此膜系统产水电导率M，产水电导率小于设定值S m³时。\r\n2. 此种状态持续N分钟以上。';

-- ----------------------------
-- Records of algorithm8data
-- ----------------------------

-- ----------------------------
-- Table structure for `algorithmtype`
-- ----------------------------
DROP TABLE IF EXISTS `algorithmtype`;
CREATE TABLE `algorithmtype` (
  `AlgorithmTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `AlgorithmTypeName` enum('') DEFAULT NULL,
  PRIMARY KEY (`AlgorithmTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='算法类别设置表：报警类；提醒类。';

-- ----------------------------
-- Records of algorithmtype
-- ----------------------------

-- ----------------------------
-- Table structure for `carbonfilterdetail`
-- ----------------------------
DROP TABLE IF EXISTS `carbonfilterdetail`;
CREATE TABLE `carbonfilterdetail` (
  `CarbonFilterID` int(11) NOT NULL AUTO_INCREMENT,
  `CarbonFilterGroupID` int(11) NOT NULL,
  `LLSL` int(11) DEFAULT NULL,
  `PP` char(255) DEFAULT NULL,
  `XH` char(255) DEFAULT NULL,
  `CKDJ` double DEFAULT NULL,
  `EDGHSJ` double DEFAULT NULL,
  PRIMARY KEY (`CarbonFilterID`),
  KEY `CarbonFilterGroupID` (`CarbonFilterGroupID`),
  CONSTRAINT `carbonfilterdetail_ibfk_1` FOREIGN KEY (`CarbonFilterGroupID`) REFERENCES `carbonfiltergroupinfo` (`CarbonFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of carbonfilterdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `carbonfiltergroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `carbonfiltergroupinfo`;
CREATE TABLE `carbonfiltergroupinfo` (
  `CarbonFilterGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `GG` char(255) DEFAULT NULL,
  `CLL` int(11) DEFAULT NULL,
  `TLGD` int(11) DEFAULT NULL,
  `CZ` char(255) DEFAULT NULL,
  PRIMARY KEY (`CarbonFilterGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `carbonfiltergroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='碳滤器组信息表';

-- ----------------------------
-- Records of carbonfiltergroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `complaintinfo`
-- ----------------------------
DROP TABLE IF EXISTS `complaintinfo`;
CREATE TABLE `complaintinfo` (
  `ComplaintID` int(11) NOT NULL AUTO_INCREMENT,
  `AgentID` int(11) NOT NULL,
  `DLSMC` char(255) DEFAULT NULL,
  `TSDW` char(255) DEFAULT NULL,
  `TSSX` char(255) DEFAULT NULL,
  `TSSJ` datetime DEFAULT NULL,
  PRIMARY KEY (`ComplaintID`),
  KEY `AgentID` (`AgentID`),
  CONSTRAINT `complaintinfo_ibfk_1` FOREIGN KEY (`AgentID`) REFERENCES `agentinfo` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='投诉信息统计表';

-- ----------------------------
-- Records of complaintinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `componentinfo`
-- ----------------------------
DROP TABLE IF EXISTS `componentinfo`;
CREATE TABLE `componentinfo` (
  `ComponentID` int(11) NOT NULL AUTO_INCREMENT,
  `LocaleComponentID` int(11) DEFAULT NULL,
  `ProjectID` int(11) NOT NULL,
  `LJBH` char(255) DEFAULT NULL,
  `LJLB` char(255) DEFAULT NULL,
  `LJBM` char(255) DEFAULT NULL,
  PRIMARY KEY (`ComponentID`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `componentinfo_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='工程零件信息表\r\nLocaleComponentID:用于将现场零件ID和管理系统的零件ID对应使用，唯一。\r\nLJLB:泵，砂滤器，碳滤器，软化器，过滤器，膜，紫外杀菌器，水箱，加药装置';

-- ----------------------------
-- Records of componentinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `customerdatainfo`
-- ----------------------------
DROP TABLE IF EXISTS `customerdatainfo`;
CREATE TABLE `customerdatainfo` (
  `DataID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(11) NOT NULL,
  `ZLLB` enum('') DEFAULT NULL,
  `ZLDZ` char(255) DEFAULT NULL,
  `ZLMC` char(255) DEFAULT NULL,
  PRIMARY KEY (`DataID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `customerdatainfo_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customerinfo` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户资料信息表\r\n资料类别：文档，演示稿，图片，视频\r\n';

-- ----------------------------
-- Records of customerdatainfo
-- ----------------------------

-- ----------------------------
-- Table structure for `customerinfo`
-- ----------------------------
DROP TABLE IF EXISTS `customerinfo`;
CREATE TABLE `customerinfo` (
  `CustomerID` int(11) NOT NULL AUTO_INCREMENT,
  `UserRoleID` int(11) NOT NULL,
  `AgentID` int(11) DEFAULT NULL,
  `KHMC` char(255) NOT NULL,
  `KHJWDDZ` char(255) NOT NULL,
  `KHTXDZ` char(255) DEFAULT NULL,
  `KHLXR` char(255) NOT NULL,
  `KHLXDH` char(255) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`),
  KEY `UserRoleID` (`UserRoleID`),
  KEY `AgentID` (`AgentID`),
  CONSTRAINT `customerinfo_ibfk_1` FOREIGN KEY (`UserRoleID`) REFERENCES `userrole` (`UserRoleID`),
  CONSTRAINT `customerinfo_ibfk_2` FOREIGN KEY (`AgentID`) REFERENCES `agentinfo` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户基本信息表\r\n代理商外键：可以为空，如果为空，直接归九如公司管理。\r\n';

-- ----------------------------
-- Records of customerinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `customerprojectinfo`
-- ----------------------------
DROP TABLE IF EXISTS `customerprojectinfo`;
CREATE TABLE `customerprojectinfo` (
  `ProjectID` int(11) NOT NULL AUTO_INCREMENT,
  `LocaleProjectID` int(11) DEFAULT NULL,
  `CustomerID` int(11) NOT NULL,
  `GCMC` char(255) NOT NULL,
  `GCBH` char(255) NOT NULL,
  `LocaleGCBH` char(255) DEFAULT NULL,
  `GCFL` enum('') DEFAULT NULL,
  `GCJS` char(255) DEFAULT NULL,
  `GCXH` char(255) DEFAULT NULL,
  `GCZP` char(255) DEFAULT NULL,
  `JSCSJJ` char(255) DEFAULT NULL,
  `JSCSFJ` char(255) DEFAULT NULL,
  `GCLJ` char(255) DEFAULT NULL,
  `AZSJ` char(255) DEFAULT NULL,
  `TYSJ` char(255) DEFAULT NULL,
  PRIMARY KEY (`ProjectID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `customerprojectinfo_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customerinfo` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户工程基本信息表\r\nLocaleProjectID:用于将现场的工程ID和管理系统的工程ID对应，唯一。\r\nLocaleGCBH:用于将现场工程编号和管理系统的工程编号对应。';

-- ----------------------------
-- Records of customerprojectinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `dosesettingdetail`
-- ----------------------------
DROP TABLE IF EXISTS `dosesettingdetail`;
CREATE TABLE `dosesettingdetail` (
  `DoseSettingID` int(11) NOT NULL AUTO_INCREMENT,
  `DoseSettingGroupID` int(11) NOT NULL,
  `EDGHSJ` double DEFAULT NULL,
  PRIMARY KEY (`DoseSettingID`),
  KEY `DoseSettingGroupID` (`DoseSettingGroupID`),
  CONSTRAINT `dosesettingdetail_ibfk_1` FOREIGN KEY (`DoseSettingGroupID`) REFERENCES `dosesettinggroupinfo` (`DoseSettingGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of dosesettingdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `dosesettinggroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `dosesettinggroupinfo`;
CREATE TABLE `dosesettinggroupinfo` (
  `DoseSettingGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `YJPP` char(255) DEFAULT NULL,
  `YJXH` char(255) DEFAULT NULL,
  `GL` int(11) DEFAULT NULL,
  `YJND` double DEFAULT NULL,
  `YJEDTJL` double DEFAULT NULL,
  `YJCKDJ` double DEFAULT NULL,
  PRIMARY KEY (`DoseSettingGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `dosesettinggroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='加药装置组信息表';

-- ----------------------------
-- Records of dosesettinggroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `emccarbonfilterdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emccarbonfilterdaysummary`;
CREATE TABLE `emccarbonfilterdaysummary` (
  `CarbonFilterDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `CarbonFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`CarbonFilterDaySumID`),
  KEY `CarbonFilterGroupID` (`CarbonFilterGroupID`),
  CONSTRAINT `emccarbonfilterdaysummary_ibfk_1` FOREIGN KEY (`CarbonFilterGroupID`) REFERENCES `carbonfiltergroupinfo` (`CarbonFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC碳滤器零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emccarbonfilterdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emccarbonfilterdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emccarbonfilterdetail`;
CREATE TABLE `emccarbonfilterdetail` (
  `CarbonFilterID` int(11) NOT NULL AUTO_INCREMENT,
  `CarbonFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`CarbonFilterID`),
  KEY `CarbonFilterGroupID` (`CarbonFilterGroupID`),
  CONSTRAINT `emccarbonfilterdetail_ibfk_1` FOREIGN KEY (`CarbonFilterGroupID`) REFERENCES `carbonfiltergroupinfo` (`CarbonFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC碳滤器零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emccarbonfilterdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emccarbonfiltermonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emccarbonfiltermonthsummary`;
CREATE TABLE `emccarbonfiltermonthsummary` (
  `CarbonFilterMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `CarbonFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`CarbonFilterMonthSumID`),
  KEY `CarbonFilterGroupID` (`CarbonFilterGroupID`),
  CONSTRAINT `emccarbonfiltermonthsummary_ibfk_1` FOREIGN KEY (`CarbonFilterGroupID`) REFERENCES `carbonfiltergroupinfo` (`CarbonFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC碳滤器零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emccarbonfiltermonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emccarbonfilteryearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emccarbonfilteryearsummary`;
CREATE TABLE `emccarbonfilteryearsummary` (
  `CarbonFilterYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `CarbonFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`CarbonFilterYearSumID`),
  KEY `CarbonFilterGroupID` (`CarbonFilterGroupID`),
  CONSTRAINT `emccarbonfilteryearsummary_ibfk_1` FOREIGN KEY (`CarbonFilterGroupID`) REFERENCES `carbonfiltergroupinfo` (`CarbonFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC碳滤器零件明细数据表:精确到月';

-- ----------------------------
-- Records of emccarbonfilteryearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfilmdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcfilmdaysummary`;
CREATE TABLE `emcfilmdaysummary` (
  `FilmDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `FilmGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`FilmDaySumID`),
  KEY `FilmGroupID` (`FilmGroupID`),
  CONSTRAINT `emcfilmdaysummary_ibfk_1` FOREIGN KEY (`FilmGroupID`) REFERENCES `filmgroupinfo` (`FilmGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC膜零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emcfilmdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfilmdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emcfilmdetail`;
CREATE TABLE `emcfilmdetail` (
  `FilmID` int(11) NOT NULL AUTO_INCREMENT,
  `FilmGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`FilmID`),
  KEY `FilmGroupID` (`FilmGroupID`),
  CONSTRAINT `emcfilmdetail_ibfk_1` FOREIGN KEY (`FilmGroupID`) REFERENCES `filmgroupinfo` (`FilmGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC膜零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emcfilmdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfilmmonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcfilmmonthsummary`;
CREATE TABLE `emcfilmmonthsummary` (
  `FilmMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `FilmGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`FilmMonthSumID`),
  KEY `FilmGroupID` (`FilmGroupID`),
  CONSTRAINT `emcfilmmonthsummary_ibfk_1` FOREIGN KEY (`FilmGroupID`) REFERENCES `filmgroupinfo` (`FilmGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC膜零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emcfilmmonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfilmyearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcfilmyearsummary`;
CREATE TABLE `emcfilmyearsummary` (
  `FilmYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `FilmGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`FilmYearSumID`),
  KEY `FilmGroupID` (`FilmGroupID`),
  CONSTRAINT `emcfilmyearsummary_ibfk_1` FOREIGN KEY (`FilmGroupID`) REFERENCES `filmgroupinfo` (`FilmGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC膜零件明细数据表:精确到月';

-- ----------------------------
-- Records of emcfilmyearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfilterdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcfilterdaysummary`;
CREATE TABLE `emcfilterdaysummary` (
  `FilterDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `FilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`FilterDaySumID`),
  KEY `FilterGroupID` (`FilterGroupID`),
  CONSTRAINT `emcfilterdaysummary_ibfk_1` FOREIGN KEY (`FilterGroupID`) REFERENCES `filtergroupinfo` (`FilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC过滤器零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emcfilterdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfilterdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emcfilterdetail`;
CREATE TABLE `emcfilterdetail` (
  `FilterID` int(11) NOT NULL AUTO_INCREMENT,
  `FilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`FilterID`),
  KEY `FilterGroupID` (`FilterGroupID`),
  CONSTRAINT `emcfilterdetail_ibfk_1` FOREIGN KEY (`FilterGroupID`) REFERENCES `filtergroupinfo` (`FilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC过滤器零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emcfilterdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfiltermonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcfiltermonthsummary`;
CREATE TABLE `emcfiltermonthsummary` (
  `FilterMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `FilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`FilterMonthSumID`),
  KEY `FilterGroupID` (`FilterGroupID`),
  CONSTRAINT `emcfiltermonthsummary_ibfk_1` FOREIGN KEY (`FilterGroupID`) REFERENCES `filtergroupinfo` (`FilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC过滤器零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emcfiltermonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcfilteryearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcfilteryearsummary`;
CREATE TABLE `emcfilteryearsummary` (
  `FilterYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `FilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`FilterYearSumID`),
  KEY `FilterGroupID` (`FilterGroupID`),
  CONSTRAINT `emcfilteryearsummary_ibfk_1` FOREIGN KEY (`FilterGroupID`) REFERENCES `filtergroupinfo` (`FilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC过滤器零件明细数据表:精确到月';

-- ----------------------------
-- Records of emcfilteryearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcpumpdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcpumpdaysummary`;
CREATE TABLE `emcpumpdaysummary` (
  `PumpDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`PumpDaySumID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `emcpumpdaysummary_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='EMC泵零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emcpumpdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcpumpdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emcpumpdetail`;
CREATE TABLE `emcpumpdetail` (
  `PumpID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`PumpID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `emcpumpdetail_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='EMC泵零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emcpumpdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emcpumpmonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcpumpmonthsummary`;
CREATE TABLE `emcpumpmonthsummary` (
  `PumpMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`PumpMonthSumID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `emcpumpmonthsummary_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='EMC泵零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emcpumpmonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcpumpyearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcpumpyearsummary`;
CREATE TABLE `emcpumpyearsummary` (
  `PumpYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`PumpYearSumID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `emcpumpyearsummary_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='EMC泵零件明细数据表:精确到月';

-- ----------------------------
-- Records of emcpumpyearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsandfilterdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcsandfilterdaysummary`;
CREATE TABLE `emcsandfilterdaysummary` (
  `SandFilterDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `SandFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`SandFilterDaySumID`),
  KEY `SandFilterGroupID` (`SandFilterGroupID`),
  CONSTRAINT `emcsandfilterdaysummary_ibfk_1` FOREIGN KEY (`SandFilterGroupID`) REFERENCES `sandfiltergroupinfo` (`SandFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC砂滤器零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emcsandfilterdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsandfilterdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emcsandfilterdetail`;
CREATE TABLE `emcsandfilterdetail` (
  `SandFilterID` int(11) NOT NULL AUTO_INCREMENT,
  `SandFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`SandFilterID`),
  KEY `SandFilterGroupID` (`SandFilterGroupID`),
  CONSTRAINT `emcsandfilterdetail_ibfk_1` FOREIGN KEY (`SandFilterGroupID`) REFERENCES `sandfiltergroupinfo` (`SandFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC砂滤器零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emcsandfilterdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsandfiltermonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcsandfiltermonthsummary`;
CREATE TABLE `emcsandfiltermonthsummary` (
  `SandFilterMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `SandFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`SandFilterMonthSumID`),
  KEY `SandFilterGroupID` (`SandFilterGroupID`),
  CONSTRAINT `emcsandfiltermonthsummary_ibfk_1` FOREIGN KEY (`SandFilterGroupID`) REFERENCES `sandfiltergroupinfo` (`SandFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC砂滤器零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emcsandfiltermonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsandfilteryearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcsandfilteryearsummary`;
CREATE TABLE `emcsandfilteryearsummary` (
  `SandFilterYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `SandFilterGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`SandFilterYearSumID`),
  KEY `SandFilterGroupID` (`SandFilterGroupID`),
  CONSTRAINT `emcsandfilteryearsummary_ibfk_1` FOREIGN KEY (`SandFilterGroupID`) REFERENCES `sandfiltergroupinfo` (`SandFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC砂滤器零件明细数据表:精确到月';

-- ----------------------------
-- Records of emcsandfilteryearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsoftenerdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcsoftenerdaysummary`;
CREATE TABLE `emcsoftenerdaysummary` (
  `SoftenerDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `SoftenerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`SoftenerDaySumID`),
  KEY `SoftenerGroupID` (`SoftenerGroupID`),
  CONSTRAINT `emcsoftenerdaysummary_ibfk_1` FOREIGN KEY (`SoftenerGroupID`) REFERENCES `softenergroupinfo` (`SoftenerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC软化器零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emcsoftenerdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsoftenerdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emcsoftenerdetail`;
CREATE TABLE `emcsoftenerdetail` (
  `SoftenerID` int(11) NOT NULL AUTO_INCREMENT,
  `SoftenerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`SoftenerID`),
  KEY `SoftenerGroupID` (`SoftenerGroupID`),
  CONSTRAINT `emcsoftenerdetail_ibfk_1` FOREIGN KEY (`SoftenerGroupID`) REFERENCES `softenergroupinfo` (`SoftenerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC软化器零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emcsoftenerdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsoftenermonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcsoftenermonthsummary`;
CREATE TABLE `emcsoftenermonthsummary` (
  `SoftenerMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `SoftenerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`SoftenerMonthSumID`),
  KEY `SoftenerGroupID` (`SoftenerGroupID`),
  CONSTRAINT `emcsoftenermonthsummary_ibfk_1` FOREIGN KEY (`SoftenerGroupID`) REFERENCES `softenergroupinfo` (`SoftenerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC软化器零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emcsoftenermonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcsofteneryearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcsofteneryearsummary`;
CREATE TABLE `emcsofteneryearsummary` (
  `SoftenerYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `SoftenerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`SoftenerYearSumID`),
  KEY `SoftenerGroupID` (`SoftenerGroupID`),
  CONSTRAINT `emcsofteneryearsummary_ibfk_1` FOREIGN KEY (`SoftenerGroupID`) REFERENCES `softenergroupinfo` (`SoftenerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC软化器零件明细数据表:精确到月';

-- ----------------------------
-- Records of emcsofteneryearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emctankdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emctankdaysummary`;
CREATE TABLE `emctankdaysummary` (
  `TankDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `TankGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`TankDaySumID`),
  KEY `TankGroupID` (`TankGroupID`),
  CONSTRAINT `emctankdaysummary_ibfk_1` FOREIGN KEY (`TankGroupID`) REFERENCES `tankgroupinfo` (`TankGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC砂滤器零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emctankdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emctankdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emctankdetail`;
CREATE TABLE `emctankdetail` (
  `TankID` int(11) NOT NULL AUTO_INCREMENT,
  `TankGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`TankID`),
  KEY `TankGroupID` (`TankGroupID`),
  CONSTRAINT `emctankdetail_ibfk_1` FOREIGN KEY (`TankGroupID`) REFERENCES `tankgroupinfo` (`TankGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC水箱零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emctankdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emctankmonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emctankmonthsummary`;
CREATE TABLE `emctankmonthsummary` (
  `TankMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `TankGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`TankMonthSumID`),
  KEY `TankGroupID` (`TankGroupID`),
  CONSTRAINT `emctankmonthsummary_ibfk_1` FOREIGN KEY (`TankGroupID`) REFERENCES `tankgroupinfo` (`TankGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC水箱零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emctankmonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emctankyearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emctankyearsummary`;
CREATE TABLE `emctankyearsummary` (
  `TankYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `TankGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`TankYearSumID`),
  KEY `TankGroupID` (`TankGroupID`),
  CONSTRAINT `emctankyearsummary_ibfk_1` FOREIGN KEY (`TankGroupID`) REFERENCES `tankgroupinfo` (`TankGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC水箱零件明细数据表:精确到月';

-- ----------------------------
-- Records of emctankyearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcuvsterilizerdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcuvsterilizerdaysummary`;
CREATE TABLE `emcuvsterilizerdaysummary` (
  `UVSterilizerDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `UVSterilizerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`UVSterilizerDaySumID`),
  KEY `UVSterilizerGroupID` (`UVSterilizerGroupID`),
  CONSTRAINT `emcuvsterilizerdaysummary_ibfk_1` FOREIGN KEY (`UVSterilizerGroupID`) REFERENCES `uvsterilizergroupinfo` (`UVSterilizerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC紫外线杀菌器零件明细数据表:精确到小时';

-- ----------------------------
-- Records of emcuvsterilizerdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcuvsterilizerdetail`
-- ----------------------------
DROP TABLE IF EXISTS `emcuvsterilizerdetail`;
CREATE TABLE `emcuvsterilizerdetail` (
  `UVSterilizerID` int(11) NOT NULL AUTO_INCREMENT,
  `UVSterilizerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDLJLLON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDDLON` tinyint(4) DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZDDLON` tinyint(4) DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZPHON` tinyint(4) DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZYLVON` tinyint(4) DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZWDON` tinyint(4) DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZYDON` tinyint(4) DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZTDSON` tinyint(4) DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZZDUON` tinyint(4) DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZYLON` tinyint(4) DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZSDION` tinyint(4) DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZCODON` tinyint(4) DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZBODON` tinyint(4) DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZADON` tinyint(4) DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZDON` tinyint(4) DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZZLON` tinyint(4) DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZXFWON` tinyint(4) DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZYWJON` tinyint(4) DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  `EDSZWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`UVSterilizerID`),
  KEY `UVSterilizerGroupID` (`UVSterilizerGroupID`),
  CONSTRAINT `emcuvsterilizerdetail_ibfk_1` FOREIGN KEY (`UVSterilizerGroupID`) REFERENCES `uvsterilizergroupinfo` (`UVSterilizerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC紫外线杀菌器零件明细数据表:精确到分钟';

-- ----------------------------
-- Records of emcuvsterilizerdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `emcuvsterilizermonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcuvsterilizermonthsummary`;
CREATE TABLE `emcuvsterilizermonthsummary` (
  `UVSterilizerMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `UVSterilizerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`UVSterilizerMonthSumID`),
  KEY `UVSterilizerGroupID` (`UVSterilizerGroupID`),
  CONSTRAINT `emcuvsterilizermonthsummary_ibfk_1` FOREIGN KEY (`UVSterilizerGroupID`) REFERENCES `uvsterilizergroupinfo` (`UVSterilizerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC紫外线杀菌器零件明细数据表:精确到天\r\n';

-- ----------------------------
-- Records of emcuvsterilizermonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `emcuvsterilizeryearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `emcuvsterilizeryearsummary`;
CREATE TABLE `emcuvsterilizeryearsummary` (
  `UVSterilizerYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `UVSterilizerGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDLJLL` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDDL` double DEFAULT NULL,
  `EDSZDDL` double DEFAULT NULL,
  `EDSZPH` double DEFAULT NULL,
  `EDSZYLV` double DEFAULT NULL,
  `EDSZWD` double DEFAULT NULL,
  `EDSZYD` double DEFAULT NULL,
  `EDSZTDS` double DEFAULT NULL,
  `EDSZZDU` double DEFAULT NULL,
  `EDSZYL` double DEFAULT NULL,
  `EDSZSDI` double DEFAULT NULL,
  `EDSZCOD` double DEFAULT NULL,
  `EDSZBOD` double DEFAULT NULL,
  `EDSZAD` double DEFAULT NULL,
  `EDSZZD` double DEFAULT NULL,
  `EDSZZL` double DEFAULT NULL,
  `EDSZXFW` double DEFAULT NULL,
  `EDSZYWJ` double DEFAULT NULL,
  `EDSZWNND` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`UVSterilizerYearSumID`),
  KEY `UVSterilizerGroupID` (`UVSterilizerGroupID`),
  CONSTRAINT `emcuvsterilizeryearsummary_ibfk_1` FOREIGN KEY (`UVSterilizerGroupID`) REFERENCES `uvsterilizergroupinfo` (`UVSterilizerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 ROW_FORMAT=COMPACT COMMENT='EMC紫外线杀菌器零件明细数据表:精确到月';

-- ----------------------------
-- Records of emcuvsterilizeryearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `filmdetail`
-- ----------------------------
DROP TABLE IF EXISTS `filmdetail`;
CREATE TABLE `filmdetail` (
  `FilmID` int(11) NOT NULL AUTO_INCREMENT,
  `FilmGroupID` int(11) NOT NULL,
  `EDGHSJ` double DEFAULT NULL,
  `EDQXSJ` double DEFAULT NULL,
  `EDQXYC` double DEFAULT NULL,
  `EDGHYC` double DEFAULT NULL,
  `EDLL` double DEFAULT NULL,
  `EDDDL` double DEFAULT NULL,
  `EDHSL` double DEFAULT NULL,
  PRIMARY KEY (`FilmID`),
  KEY `FilmGroupID` (`FilmGroupID`),
  CONSTRAINT `filmdetail_ibfk_1` FOREIGN KEY (`FilmGroupID`) REFERENCES `filmgroupinfo` (`FilmGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of filmdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `filmgroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `filmgroupinfo`;
CREATE TABLE `filmgroupinfo` (
  `FilmGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `PLFS` char(255) DEFAULT NULL,
  `MKGG` char(255) DEFAULT NULL,
  `MZSL` int(11) DEFAULT NULL,
  `CSL` int(11) DEFAULT NULL,
  `MPP` char(255) DEFAULT NULL,
  `MXH` char(255) DEFAULT NULL,
  `MHSL` double DEFAULT NULL,
  `MCKDJ` double DEFAULT NULL,
  PRIMARY KEY (`FilmGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `filmgroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='膜组信息表';

-- ----------------------------
-- Records of filmgroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `filterdetail`
-- ----------------------------
DROP TABLE IF EXISTS `filterdetail`;
CREATE TABLE `filterdetail` (
  `FilterID` int(11) NOT NULL AUTO_INCREMENT,
  `FilterGroupID` int(11) NOT NULL,
  `EDYC` double DEFAULT NULL,
  `EDGHSJ` double DEFAULT NULL,
  PRIMARY KEY (`FilterID`),
  KEY `FilterGroupID` (`FilterGroupID`),
  CONSTRAINT `filterdetail_ibfk_1` FOREIGN KEY (`FilterGroupID`) REFERENCES `filtergroupinfo` (`FilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of filterdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `filtergroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `filtergroupinfo`;
CREATE TABLE `filtergroupinfo` (
  `FilterGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `GG` char(255) DEFAULT NULL,
  `CLL` int(11) DEFAULT NULL,
  `GLJD` double DEFAULT NULL,
  `LXCC` double DEFAULT NULL,
  `LXSL` int(11) DEFAULT NULL,
  `LXCZ` char(255) DEFAULT NULL,
  `LXPP` char(255) DEFAULT NULL,
  `LXXH` char(255) DEFAULT NULL,
  `LXJKXS` char(255) DEFAULT NULL,
  `LXCKDJ` double DEFAULT NULL,
  `Others` char(255) DEFAULT NULL,
  PRIMARY KEY (`FilterGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `filtergroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='过滤器组信息表';

-- ----------------------------
-- Records of filtergroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `maintainceservicesdetail`
-- ----------------------------
DROP TABLE IF EXISTS `maintainceservicesdetail`;
CREATE TABLE `maintainceservicesdetail` (
  `MaintainceServicesID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `LJSSLB` enum('') DEFAULT NULL,
  `LJBH` char(255) DEFAULT NULL,
  `FWRQ` datetime DEFAULT NULL,
  `FWNR` char(255) DEFAULT NULL,
  `FWGS` char(255) DEFAULT NULL,
  `FWRY` char(255) DEFAULT NULL,
  `FWPJ` char(255) DEFAULT NULL,
  PRIMARY KEY (`MaintainceServicesID`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `maintainceservicesdetail_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='维护保养明细表';

-- ----------------------------
-- Records of maintainceservicesdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `projectdatainfo`
-- ----------------------------
DROP TABLE IF EXISTS `projectdatainfo`;
CREATE TABLE `projectdatainfo` (
  `ProjectDataID` int(11) NOT NULL AUTO_INCREMENT,
  `ProjectID` int(11) NOT NULL,
  `GCZLMC` char(255) DEFAULT NULL,
  `GCZLJJ` char(255) DEFAULT NULL,
  `GCZLLX` enum('') DEFAULT NULL,
  `GCZLLJ` char(255) DEFAULT NULL,
  `GCJCTLX` char(255) DEFAULT NULL,
  PRIMARY KEY (`ProjectDataID`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `projectdatainfo_ibfk_1` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='客户工程资料信息表\r\nGCZLLX:doc,ppt,jpg,pdf,视频\r\nGCJCTLB:为空则为普通资料；不为空则为相应的监测图：可以多选，可以不选。\r\nString类型表示：业务人员上传的时候自己通过复选框选择。';

-- ----------------------------
-- Records of projectdatainfo
-- ----------------------------

-- ----------------------------
-- Table structure for `pumpdaysummary`
-- ----------------------------
DROP TABLE IF EXISTS `pumpdaysummary`;
CREATE TABLE `pumpdaysummary` (
  `PumpDaySumID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDYCLL` double DEFAULT NULL,
  `EDWHBYSJ` double DEFAULT NULL,
  `EDDDL` double DEFAULT NULL,
  `EDPH` double DEFAULT NULL,
  `EDYLV` double DEFAULT NULL,
  `EDWD` double DEFAULT NULL,
  `EDYD` double DEFAULT NULL,
  `EDTDS` double DEFAULT NULL,
  `EDZDU` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDSDI` double DEFAULT NULL,
  `EDCOD` double DEFAULT NULL,
  `EDBOD` double DEFAULT NULL,
  `EDAD` double DEFAULT NULL,
  `EDZD` double DEFAULT NULL,
  `EDZL` double DEFAULT NULL,
  `EDXFW` double DEFAULT NULL,
  `EDYWJ` double DEFAULT NULL,
  `EDWNND` double DEFAULT NULL,
  PRIMARY KEY (`PumpDaySumID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `pumpdaysummary_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='泵零件明细数据表：精确到小时, 自然小时统计，如果需要汇总不是自然小时的数据，则从明细表实时过滤\r\n';

-- ----------------------------
-- Records of pumpdaysummary
-- ----------------------------

-- ----------------------------
-- Table structure for `pumpdetail`
-- ----------------------------
DROP TABLE IF EXISTS `pumpdetail`;
CREATE TABLE `pumpdetail` (
  `PumpID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDYCLL` double DEFAULT NULL,
  `EDYCLLON` tinyint(4) DEFAULT NULL,
  `EDWHBYSJ` double DEFAULT NULL,
  `EDWHBYSJON` tinyint(4) DEFAULT NULL,
  `EDDDL` double DEFAULT NULL,
  `EDDDLON` tinyint(4) DEFAULT NULL,
  `EDPH` double DEFAULT NULL,
  `EDPHON` tinyint(4) DEFAULT NULL,
  `EDYLV` double DEFAULT NULL,
  `EDYLVON` tinyint(4) DEFAULT NULL,
  `EDWD` double DEFAULT NULL,
  `EDWDON` tinyint(4) DEFAULT NULL,
  `EDYD` double DEFAULT NULL,
  `EDYDON` tinyint(4) DEFAULT NULL,
  `EDTDS` double DEFAULT NULL,
  `EDTDSON` tinyint(4) DEFAULT NULL,
  `EDZDU` double DEFAULT NULL,
  `EDZDUON` tinyint(4) DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDYLON` tinyint(4) DEFAULT NULL,
  `EDSDI` double DEFAULT NULL,
  `EDSDION` tinyint(4) DEFAULT NULL,
  `EDCOD` double DEFAULT NULL,
  `EDCODON` tinyint(4) DEFAULT NULL,
  `EDBOD` double DEFAULT NULL,
  `EDBODON` tinyint(4) DEFAULT NULL,
  `EDAD` double DEFAULT NULL,
  `EDADON` tinyint(4) DEFAULT NULL,
  `EDZD` double DEFAULT NULL,
  `EDZDON` tinyint(4) DEFAULT NULL,
  `EDZL` double DEFAULT NULL,
  `EDZLON` tinyint(4) DEFAULT NULL,
  `EDXFW` double DEFAULT NULL,
  `EDXFWON` tinyint(4) DEFAULT NULL,
  `EDYWJ` double DEFAULT NULL,
  `EDYWJON` tinyint(4) DEFAULT NULL,
  `EDWNND` double DEFAULT NULL,
  `EDWNNDON` tinyint(4) DEFAULT NULL,
  `OtherInfo` char(255) DEFAULT NULL,
  PRIMARY KEY (`PumpID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `pumpdetail_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='泵零件明细数据表:明细表的数据精确到分钟';

-- ----------------------------
-- Records of pumpdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `pumpgroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `pumpgroupinfo`;
CREATE TABLE `pumpgroupinfo` (
  `PumpGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `PP` char(255) DEFAULT NULL,
  `XH` char(255) DEFAULT NULL,
  `GL` int(11) DEFAULT NULL,
  PRIMARY KEY (`PumpGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `pumpgroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='泵组信息表';

-- ----------------------------
-- Records of pumpgroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `pumpmonthsummary`
-- ----------------------------
DROP TABLE IF EXISTS `pumpmonthsummary`;
CREATE TABLE `pumpmonthsummary` (
  `PumpMonthSumID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDYCLL` double DEFAULT NULL,
  `EDWHBYSJ` double DEFAULT NULL,
  `EDDDL` double DEFAULT NULL,
  `EDPH` double DEFAULT NULL,
  `EDYLV` double DEFAULT NULL,
  `EDWD` double DEFAULT NULL,
  `EDYD` double DEFAULT NULL,
  `EDTDS` double DEFAULT NULL,
  `EDZDU` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDSDI` double DEFAULT NULL,
  `EDCOD` double DEFAULT NULL,
  `EDBOD` double DEFAULT NULL,
  `EDAD` double DEFAULT NULL,
  `EDZD` double DEFAULT NULL,
  `EDZL` double DEFAULT NULL,
  `EDXFW` double DEFAULT NULL,
  `EDYWJ` double DEFAULT NULL,
  `EDWNND` double DEFAULT NULL,
  PRIMARY KEY (`PumpMonthSumID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `pumpmonthsummary_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='泵零件明细数据表：按天汇总数据，精确到每一天。\r\n';

-- ----------------------------
-- Records of pumpmonthsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `pumpyearsummary`
-- ----------------------------
DROP TABLE IF EXISTS `pumpyearsummary`;
CREATE TABLE `pumpyearsummary` (
  `PumpYearSumID` int(11) NOT NULL AUTO_INCREMENT,
  `PumpGroupID` int(11) NOT NULL,
  `DataDate` datetime DEFAULT NULL,
  `EDYCLL` double DEFAULT NULL,
  `EDWHBYSJ` double DEFAULT NULL,
  `EDDDL` double DEFAULT NULL,
  `EDPH` double DEFAULT NULL,
  `EDYLV` double DEFAULT NULL,
  `EDWD` double DEFAULT NULL,
  `EDYD` double DEFAULT NULL,
  `EDTDS` double DEFAULT NULL,
  `EDZDU` double DEFAULT NULL,
  `EDYL` double DEFAULT NULL,
  `EDSDI` double DEFAULT NULL,
  `EDCOD` double DEFAULT NULL,
  `EDBOD` double DEFAULT NULL,
  `EDAD` double DEFAULT NULL,
  `EDZD` double DEFAULT NULL,
  `EDZL` double DEFAULT NULL,
  `EDXFW` double DEFAULT NULL,
  `EDYWJ` double DEFAULT NULL,
  `EDWNND` double DEFAULT NULL,
  PRIMARY KEY (`PumpYearSumID`),
  KEY `PumpGroupID` (`PumpGroupID`),
  CONSTRAINT `pumpyearsummary_ibfk_1` FOREIGN KEY (`PumpGroupID`) REFERENCES `pumpgroupinfo` (`PumpGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='泵零件明细数据表：精确到月，自然月统计\r\n\r\n';

-- ----------------------------
-- Records of pumpyearsummary
-- ----------------------------

-- ----------------------------
-- Table structure for `sandfilterdetail`
-- ----------------------------
DROP TABLE IF EXISTS `sandfilterdetail`;
CREATE TABLE `sandfilterdetail` (
  `SandFilterID` int(11) NOT NULL AUTO_INCREMENT,
  `SandFilterGroupID` int(11) NOT NULL,
  `EDGHSJ` double DEFAULT NULL,
  `EDYC` double DEFAULT NULL,
  `EDSZ` double DEFAULT NULL,
  PRIMARY KEY (`SandFilterID`),
  KEY `SandFilterGroupID` (`SandFilterGroupID`),
  CONSTRAINT `sandfilterdetail_ibfk_1` FOREIGN KEY (`SandFilterGroupID`) REFERENCES `sandfiltergroupinfo` (`SandFilterGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of sandfilterdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `sandfiltergroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `sandfiltergroupinfo`;
CREATE TABLE `sandfiltergroupinfo` (
  `SandFilterGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `GG` char(255) DEFAULT NULL,
  `CLL` int(11) DEFAULT NULL,
  `TLGD` int(11) DEFAULT NULL,
  `CZ` char(255) DEFAULT NULL,
  `LLSL` int(11) DEFAULT NULL,
  `LLPP` char(255) DEFAULT NULL,
  `LLXH` char(255) DEFAULT NULL,
  `LLCKDJ` double DEFAULT NULL,
  PRIMARY KEY (`SandFilterGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `sandfiltergroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='砂滤器组信息表';

-- ----------------------------
-- Records of sandfiltergroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `softenerdetail`
-- ----------------------------
DROP TABLE IF EXISTS `softenerdetail`;
CREATE TABLE `softenerdetail` (
  `SoftenerID` int(11) NOT NULL AUTO_INCREMENT,
  `SoftenerGroupID` int(11) NOT NULL,
  `EDGHSJ` double DEFAULT NULL,
  PRIMARY KEY (`SoftenerID`),
  KEY `SoftenerGroupID` (`SoftenerGroupID`),
  CONSTRAINT `softenerdetail_ibfk_1` FOREIGN KEY (`SoftenerGroupID`) REFERENCES `softenergroupinfo` (`SoftenerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of softenerdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `softenergroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `softenergroupinfo`;
CREATE TABLE `softenergroupinfo` (
  `SoftenerGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `GG` char(255) DEFAULT NULL,
  `CLL` int(11) DEFAULT NULL,
  `TLGD` int(11) DEFAULT NULL,
  `CZ` char(255) DEFAULT NULL,
  `LLSL` int(11) DEFAULT NULL,
  `LLPP` char(255) DEFAULT NULL,
  `LLXH` char(255) DEFAULT NULL,
  `LLCKDJ` double DEFAULT NULL,
  PRIMARY KEY (`SoftenerGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `softenergroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='软化器组信息表';

-- ----------------------------
-- Records of softenergroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `tankdetail`
-- ----------------------------
DROP TABLE IF EXISTS `tankdetail`;
CREATE TABLE `tankdetail` (
  `TankID` int(11) NOT NULL AUTO_INCREMENT,
  `TankGroupID` int(11) NOT NULL,
  `EDGHSJ` double DEFAULT NULL,
  PRIMARY KEY (`TankID`),
  KEY `TankGroupID` (`TankGroupID`),
  CONSTRAINT `tankdetail_ibfk_1` FOREIGN KEY (`TankGroupID`) REFERENCES `tankgroupinfo` (`TankGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of tankdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `tankgroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `tankgroupinfo`;
CREATE TABLE `tankgroupinfo` (
  `TankGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `GG` char(255) DEFAULT NULL,
  `RJ` double DEFAULT NULL,
  `Others` char(255) DEFAULT NULL,
  PRIMARY KEY (`TankGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `tankgroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='水箱组信息表';

-- ----------------------------
-- Records of tankgroupinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `unprocessedinfo`
-- ----------------------------
DROP TABLE IF EXISTS `unprocessedinfo`;
CREATE TABLE `unprocessedinfo` (
  `UnprocessedInfoID` int(11) NOT NULL AUTO_INCREMENT,
  `AgengID` int(11) NOT NULL,
  `ProjectID` int(11) NOT NULL,
  `ErrorInfoLB` enum('') DEFAULT NULL,
  `LJBM` char(255) DEFAULT NULL,
  `JLRQ` datetime DEFAULT NULL,
  PRIMARY KEY (`UnprocessedInfoID`),
  KEY `AgengID` (`AgengID`),
  KEY `ProjectID` (`ProjectID`),
  CONSTRAINT `unprocessedinfo_ibfk_1` FOREIGN KEY (`AgengID`) REFERENCES `agentinfo` (`AgentID`),
  CONSTRAINT `unprocessedinfo_ibfk_2` FOREIGN KEY (`ProjectID`) REFERENCES `customerprojectinfo` (`ProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='未处理信息统计表\r\nErrorInfoLB:报警未处理；提醒未处理；工程师未及时回复';

-- ----------------------------
-- Records of unprocessedinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserRoleID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `AgentID` int(11) DEFAULT NULL,
  `UserName` char(255) NOT NULL,
  `PassWord` char(255) NOT NULL,
  `PassWordConfirm` char(255) DEFAULT NULL,
  `Email` char(255) DEFAULT NULL,
  `Telephone` char(255) DEFAULT NULL,
  `RealName` char(255) DEFAULT NULL,
  `CompanyName` char(255) DEFAULT NULL,
  `LogInTime` datetime DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `AgentID` (`AgentID`),
  KEY `UserRoleID` (`UserRoleID`),
  CONSTRAINT `userinfo_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customerinfo` (`CustomerID`),
  CONSTRAINT `userinfo_ibfk_2` FOREIGN KEY (`AgentID`) REFERENCES `agentinfo` (`AgentID`),
  CONSTRAINT `userinfo_ibfk_3` FOREIGN KEY (`UserRoleID`) REFERENCES `userrole` (`UserRoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=gb2312 COMMENT='备注：\r\n1. 注册用户信息表中关联客户信息表和代理商信息表，从而实现实现客户、代理商及注册用户一对多的关系，关联外键可以为空。\r\n2. 系统新注册用户，默认是注册用户权限。';

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '1', null, null, 'Administrator', '123', '123', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('2', '9', null, null, 'Guest', '123', '123', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('3', '2', null, null, 'JRYWY', '123', '123', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('4', '3', null, null, 'JRGCS', '123', '123', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('5', '8', null, null, 'JRXXGLY', '123', '123', null, null, null, null, null);

-- ----------------------------
-- Table structure for `userrole`
-- ----------------------------
DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `UserRoleID` int(11) NOT NULL AUTO_INCREMENT,
  `UserRoleName` char(255) NOT NULL,
  PRIMARY KEY (`UserRoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gb2312 COMMENT='用户角色与权限表：\r\n1. 权限分类： 超级系统管理员（内置），九如公司业务员，九如公司工程师，代理商，客户，注册用户，访客，九如信息管理员，学习者。\r\n2. 新注册用户默认都是注册用户权限，通过九如公司业务员（内置）实现权限分配（如九如公司工程师）及注册用户和真实客户/代理商的绑定。';

-- ----------------------------
-- Records of userrole
-- ----------------------------
INSERT INTO `userrole` VALUES ('1', '超级系统管理员');
INSERT INTO `userrole` VALUES ('2', '九如公司业务员');
INSERT INTO `userrole` VALUES ('3', '九如公司工程师');
INSERT INTO `userrole` VALUES ('4', '代理商');
INSERT INTO `userrole` VALUES ('5', '客户');
INSERT INTO `userrole` VALUES ('6', '注册用户');
INSERT INTO `userrole` VALUES ('7', '访客');
INSERT INTO `userrole` VALUES ('8', '九如信息管理员');
INSERT INTO `userrole` VALUES ('9', '学习者');

-- ----------------------------
-- Table structure for `uvsterilizerdetail`
-- ----------------------------
DROP TABLE IF EXISTS `uvsterilizerdetail`;
CREATE TABLE `uvsterilizerdetail` (
  `UVSterilizerID` int(11) NOT NULL AUTO_INCREMENT,
  `UVSterilizerGroupID` int(11) NOT NULL,
  `EDGHSJ` double DEFAULT NULL,
  PRIMARY KEY (`UVSterilizerID`),
  KEY `UVSterilizerGroupID` (`UVSterilizerGroupID`),
  CONSTRAINT `uvsterilizerdetail_ibfk_1` FOREIGN KEY (`UVSterilizerGroupID`) REFERENCES `uvsterilizergroupinfo` (`UVSterilizerGroupID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of uvsterilizerdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `uvsterilizergroupinfo`
-- ----------------------------
DROP TABLE IF EXISTS `uvsterilizergroupinfo`;
CREATE TABLE `uvsterilizergroupinfo` (
  `UVSterilizerGroupID` int(11) NOT NULL AUTO_INCREMENT,
  `ComponentID` int(11) NOT NULL,
  `CLL` int(11) DEFAULT NULL,
  `DGSL` int(11) DEFAULT NULL,
  `DGSM` int(11) DEFAULT NULL,
  `DGPP` char(255) DEFAULT NULL,
  `DGXH` char(255) DEFAULT NULL,
  `DGGL` double DEFAULT NULL,
  `DGCKDJ` double DEFAULT NULL,
  `Others` char(255) DEFAULT NULL,
  PRIMARY KEY (`UVSterilizerGroupID`),
  KEY `ComponentID` (`ComponentID`),
  CONSTRAINT `uvsterilizergroupinfo_ibfk_1` FOREIGN KEY (`ComponentID`) REFERENCES `componentinfo` (`ComponentID`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='紫外杀菌器组信息表';

-- ----------------------------
-- Records of uvsterilizergroupinfo
-- ----------------------------
