/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.,Ltd.
*/
package com.rotek.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.cta.platform.config.SystemGlobals;
import com.rotek.entity.TableDescEntity;

/**
 * @ClassName:ProjectUtils
 * @Description: 获取 OPC 端生成的表的信息
 * @Author WangJuZhu
 * @date 2014年7月7日 下午4:05:10
 * @Version:1.1.0
 */
public class ProjectUtils {

	private static String dbDriver = SystemGlobals.getPreference("database.driverClassName");
	private static String dbUrl = SystemGlobals.getPreference("database.url");
	private static String dbUserName = SystemGlobals.getPreference("database.username");
	private static String dbPassword = SystemGlobals.getPreference("database.password");
	
	/**
	* @MethodName: getProjectCode 
	* @Description: 获取所有现场的工程编号
	* 
	* 前提：表名格式  ：TB+四位工程编号+两位组编号
	* 如：TB000101，表示0001号现场 ，01：表示泵组
	* 返回：现场编号集合：0001、0002。。。
	* @return List<String>
	* @throws Exception
	* @author WangJuZhu
	*/
	public static List<String> getProjectCode() {
		
		List<String> pcodes = new ArrayList<String>();
		//获取所有已TB开头的表的表名
		String tabName = "TB%" ; 
		List<TableDescEntity> tabNames = getTableNames(tabName);
		if(tabNames.size() > 0){
			for(TableDescEntity tn : tabNames){
				pcodes.add(tn.getTableName().substring(2, 6));
			}
		}
		return removeRepetition(pcodes);
	}
	
	/**
	* @MethodName: getTables 
	* @Description: 获取数据库中表的名称，表名称可以使用通配符,tabName为空则查全部表
	* 如：tabName = "TB%",获取的是 TB开头的所有表的名称
	* 通配符:单字符通配符("_"),多字符通配符("%")
	* @return
	* @author WangJuZhu
	*/
	public static List<TableDescEntity> getTableNames(String tabName){
		List<TableDescEntity> dbNames = new ArrayList<TableDescEntity>() ;
		DatabaseMetaData md = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			md = conn.getMetaData();
			rs = md.getTables(null, null, tabName, null);
			while (rs.next()) {
				TableDescEntity tempEntity = new TableDescEntity();
				tempEntity.setTableName(rs.getString("TABLE_NAME"));
				dbNames.add(tempEntity);
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbNames ;
	}
	
	/**
	* @MethodName: getColumnDesc 
	* @Description: 根据表名获取其字段名称、字段类型、字段注释
	* @param tableName 表名称
	* @return 字段描述List
	* @author WangJuZhu
	*/
	public static List<TableDescEntity> getColumnDesc(String tableName){
		List<TableDescEntity> tabEntityList = new ArrayList<>();
		ResultSet rs = null;
		if(StringUtils.isNotBlank(tableName)){
			try {
				Connection conn = getConnection();
				String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='" + tableName + "'";
				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					TableDescEntity tabEntity = new TableDescEntity();
					tabEntity.setColumnName(rs.getString(1));
					tabEntity.setColumnType(rs.getString(2));
					tabEntity.setColumnComment(rs.getString(3));
					tabEntityList.add(tabEntity);	
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return tabEntityList;
		}else{
			return null;
		}
	}
	
	/**
	* @MethodName: getConnection 
	* @Description: 获取数据库连接
	* @return
	* @author WangJuZhu
	*/
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	* @MethodName: removeRepetition 
	* @Description: List去除重复元素
	* @param list
	* @return
	* @author WangJuZhu
	*/
	public static List<String> removeRepetition(List<String> list){
		List<String> tempList = new ArrayList<String>();
		if(list.size() > 0){
			for(String i:list){
				if(!tempList.contains(i)){
					tempList.add(i);
				}
			}
		}
		return tempList ;
	}	
	
	/**
	* @MethodName: main 
	* @Description: 测试用
	* @param args
	* @author WangJuZhu
	*/
	public static void main(String[] args) {

		String dbDrive = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://58.221.61.123:3306/rotek?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";
		String name = "root";
		String password = "threeNOODLES";
		Connection conn = null;
		DatabaseMetaData md = null;
		ResultSet rs = null;
		
	/*	int j = 1;
		try {
			Class.forName(dbDrive);
			conn = DriverManager.getConnection(url, name, password);
			md = conn.getMetaData();
			rs = md.getTables(null, null, "TB%", null);
			System.out.println("+----------------+");
			while (rs.next()) {
				if (j == 1) {
					System.out.println("|库名：" + rs.getString(1));
					System.out.println("+----------------+");
				}
				System.out.println("|表" + (j++) + ":" + rs.getString("TABLE_NAME"));
			}
			System.out.println("+----------------+");
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		 
		try {
			Class.forName(dbDrive); 
			conn = DriverManager.getConnection(url, name, password);
			String sql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='R_COMPONENT_GROUP'";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			List<TableDescEntity> tabEntityList = new ArrayList<>();
			
			while (rs.next()) {
				TableDescEntity tabEntity = new TableDescEntity();
				tabEntity.setColumnName(rs.getString(1));
				tabEntity.setColumnType(rs.getString(2));
				tabEntity.setColumnComment(rs.getString(3));
				tabEntityList.add(tabEntity);
				
				/*for (int i = 1; i <= data.getColumnCount(); i++) {
					// 获得所有列的数目及实际列数
					int columnCount = data.getColumnCount();
					// 获得指定列的列名
					String columnName = data.getColumnName(i);
					// 获得指定列的列值
					String columnValue = rs.getString(i);
					// 获得指定列的数据类型
					int columnType = data.getColumnType(i);
					// 获得指定列的数据类型名
					String columnTypeName = data.getColumnTypeName(i);
					// 所在的Catalog名字
					String catalogName = data.getCatalogName(i);
					// 对应数据类型的类
					String columnClassName = data.getColumnClassName(i);
					// 在数据库中类型的最大字符个数
					int columnDisplaySize = data.getColumnDisplaySize(i);
					// 默认的列的标题
					String columnLabel = data.getColumnLabel(i);
					// 获得列的模式
					String schemaName = data.getSchemaName(i);
					// 某列类型的精确度(类型的长度)
					int precision = data.getPrecision(i);
					// 小数点后的位数
					int scale = data.getScale(i);
					// 获取某列对应的表名
					String tableName = data.getTableName(i);
					// 是否自动递增
					boolean isAutoInctement = data.isAutoIncrement(i);
					// 在数据库中是否为货币型
					boolean isCurrency = data.isCurrency(i);
					// 是否为空
					int isNullable = data.isNullable(i);
					// 是否为只读
					boolean isReadOnly = data.isReadOnly(i);
					// 能否出现在where中
					boolean isSearchable = data.isSearchable(i);
					System.out.println(columnCount);
					System.out.println("获得列" + i + "的字段建议名称:" + data.getColumnLabel(i));
					System.out.println("获得列" + i + "的字段名称:" + columnName);
					System.out.println("获得列" + i + "的字段值:" + columnValue);
					System.out.println("获得列" + i + "的类型,返回SqlType中的编号:" + columnType);
					System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
					System.out.println("获得列" + i + "所在的Catalog名字:"+ catalogName);
					System.out.println("获得列" + i + "对应数据类型的类:"+ columnClassName);
					System.out.println("获得列" + i + "在数据库中类型的最大字符个数:"+ columnDisplaySize);
					System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
					System.out.println("获得列" + i + "的模式:" + schemaName);
					System.out.println("获得列" + i + "类型的精确度(类型的长度):" + precision);
					System.out.println("获得列" + i + "小数点后的位数:" + scale);
					System.out.println("获得列" + i + "对应的表名:" + tableName);
					System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
					System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
					System.out.println("获得列" + i + "是否为空:" + isNullable);
					System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
					System.out.println("获得列" + i + "能否出现在where中:"+ isSearchable);
				}*/
			}
			for(TableDescEntity tab : tabEntityList){
				System.out.println("列名称 ：" + tab.getColumnName() + "   列类型： " + tab.getColumnType() + "   列注释 : " + tab.getColumnComment());
			}
			
		} catch (Exception e) {
			System.out.println("数据库连接失败");
		}

		
		List<TableDescEntity> cdList = getColumnDesc("R_MONITORSETTING");
		
		System.out.println(cdList);
	}
	
}
