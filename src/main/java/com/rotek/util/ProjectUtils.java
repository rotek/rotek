/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.,Ltd.
*/
package com.rotek.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cta.platform.config.SystemGlobals;

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
	* 
	* @return List<String>
	* @throws Exception
	* @author WangJuZhu
	*/
	public static List<String> getProjectCode() {
		
		List<String> pcodes = new ArrayList<String>();
		//获取所有已TB开头的表的表名
		String tabName = "TB%" ; 
		List<String> tabNames = getTables(tabName);
		if(tabNames.size() > 0){
			for(String tn : tabNames){
				pcodes.add(tn.substring(2, 6));
			}
		}
	
		return removeRepetition(pcodes);
	}
	
	/**
	* @MethodName: getTables 
	* @Description: 获取数据库中表的名称，表名称可以使用通配符,tabName为空则查全部表
	* @return
	* @author WangJuZhu
	*/
	public static List<String> getTables(String tabName){
		List<String> dbNames = new ArrayList<String>() ;
		Connection conn = null;
		DatabaseMetaData md = null;
		ResultSet rs = null;
		try {
			Class.forName(dbDriver);
			conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			md = conn.getMetaData();
			rs = md.getTables(null, null, tabName, null);
			while (rs.next()) {
				dbNames.add(rs.getString("TABLE_NAME"));
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbNames ;
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
		
		String dbDrive = "com.mysql.jdbc.Driver" ; 
		String url = "jdbc:mysql://58.221.61.123:3306/rotek?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8";  
		String name = "root" ;  
		String password = "threeNOODLES" ; 
		Connection conn = null;
		DatabaseMetaData md = null;
		ResultSet rs = null;
		int i = 1;
		try {
			Class.forName(dbDrive);
			conn = DriverManager.getConnection(url, name, password);
			md = conn.getMetaData();
			rs = md.getTables(null, null, "TB%", null);
			System.out.println("+----------------+");
			while (rs.next()) {
				if (i == 1) {
					System.out.println("|库名：" + rs.getString(1));
					System.out.println("+----------------+");
				}
				System.out.println("|表" + (i++) + ":"
						+ rs.getString("TABLE_NAME"));
			}
			System.out.println("+----------------+");
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
