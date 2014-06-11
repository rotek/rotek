/**
* @FileName: ListPager.java
* @Package com.cta.platform.persistence.util
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-7 上午11:04:44
* @version V1.0
*/
package com.cta.platform.util;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cta.platform.persistence.db.DBOption;

/**
 * @ClassName: ListPager
 * @Description:分页的工具类
 * @author chenwenpeng
 * @date 2013-5-7 上午11:04:44
 *
 */
public class ListPager {

	/**@Field the Integer rolesPerPage*/
	private Integer rowsPerPage;
	/**@Field the Integer pageNo*/
	private Integer pageNo;
	/**@Field the Long totalRows*/
	private Long totalRows;
	/**@Field the List<?> pageData*/
	private List<? extends Object> pageData;

	/**
	 * 默认15条
	 */
	public ListPager(){
		this.rowsPerPage = 15;
		this.pageNo = 0;
		this.totalRows = null;
		this.pageData = null;
	}

	public Integer getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(Integer rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Long getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}
	public List<?> getPageData() {
		return pageData;
	}
	public void setPageData(List<?> pageData) {
		this.pageData = pageData;
	}

	//====================================================================================

	/**
	* @Title: buildRowCountSQL
	* @Description: 获取总条数的sql
	* @param @param sql
	* @param @return
	* @return String
	* @throws
	*/
	public static String buildRowCountSQL(String sql) {
		String newSql = sql;
		String regex = "\\s(order\\s+by\\s+[\\w|\\d|\\.|\\-|_|\\s]+)$";
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		if(m.find()){
			newSql = sql.substring(0,m.start(1));
		}
		StringBuffer sb = new StringBuffer(newSql.length()+"select count(*) from (".length()+" ) tem".length());
		sb.append("select count(*) from (");
		sb.append(newSql);
		sb.append(" ) tem");
		return sb.toString();
	}



	/**
	 * @throws SQLException
	* @Title: buildPagedSQL
	* @Description: 获取分页的sql
	* @param @param sql
	* @param @param dbType
	* @param @return
	* @return String
	* @throws
	*/
	public String buildPagedSQL(String sql, int dbType) throws SQLException {

		if(DBOption.DB_MYSQL == dbType){

			return buildPagedSQLMySql(sql);
		}else {
			throw new SQLException("Un supported database");
		}
	}

	/**
	 * @throws SQLException
	 * @Title: buildPagedSQL
	 * @Description: 获取从from到to的数据的sql
	 * @param @param sql
	 * @param @param dbtype
	 * @param @param from
	 * @param @param to
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String buildPagedSQL(String sql, int dbType, long from,
			long to) throws SQLException {
		if(DBOption.DB_MYSQL == dbType){
			return buildPagedSQLMySql(sql,from,to);
		}else {
			throw new SQLException("Un supported database");
		}
	}

	/**
	 * @throws SQLException
	* @Title: buildPagedSQLMySql
	* @Description:
	* @param @param sql
	* @param @param from
	* @param @param to
	* @param @return
	* @return String
	* @throws
	*/
	private static String buildPagedSQLMySql(String sql, Long from, Long to) throws SQLException {
		if(null == from || null == to || to < from){
			throw new SQLException("\"to\" must great than \"from\" and \"from&to\" can not be null");
		}
		return sql + " limit " + from + "," + (to-from);
	}

	/**
	 * @return
	* @Title: buildPagedSQLMySql
	* @Description: 创建Mysql的分页
	* @param @param sql
	* @return void
	* @throws
	*/
	public String buildPagedSQLMySql(String sql) {

		return sql + " limit "+(pageNo * rowsPerPage)+","+rowsPerPage;
	}
}
