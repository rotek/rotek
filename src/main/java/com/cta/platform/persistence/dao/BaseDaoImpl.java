/**
* @FileName: BaseDaoImpl.java
* @Package com.cta.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-6 下午01:35:55
* @version V1.0
*/
package com.cta.platform.persistence.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.cta.platform.config.SystemGlobals;
import com.cta.platform.persistence.db.DBOperator;
import com.cta.platform.persistence.db.DBOption;
import com.cta.platform.util.ListPager;


/**
 * @ClassName: BaseDaoImpl
 * @Description: dao层接口的实现
 * @author chenwenpeng
 * @date 2013-5-6 下午01:35:55
 *
 */
public class BaseDaoImpl implements IBaseDao{

	/** The logger. */
    private static Logger logger = Logger.getLogger(DBOperator.class);

	/**@Field the int dbType*/
	private static final int dbType = DBOption.DB_MYSQL;
	/**@Field the DataSource dataSource*/
	protected DataSource dataSource = null;
	/**@Field the DBOperator dboperator*/
	private DBOperator dboperator = new DBOperator(true,getDBType(),getDBVersion());

	/**
	* @Title: getDBType
	* @Description:
	* @param @param dbtype2
	* @param @return
	* @return Object
	* @throws
	*/
	private Integer getDBType() {
		String db_name = SystemGlobals.getPreference("db.type");
		if(null != db_name){
			if(DBOption.DB_NAME_MYSQL.equals(db_name)){
				return DBOption.DB_MYSQL;
			}else if(DBOption.DB_NAME_ORACLE.equals(db_name)){
				return DBOption.DB_ORACLE;
			}else if(DBOption.DB_NAME_SQLSERVER.equals(db_name)){
				return DBOption.DB_SQLSERVER;
			}
		}
		return DBOption.DB_MYSQL;
	}



	/**
	* @Title: setDataSource
	* @Description:
	* @param @param dataSource
	* @return void
	* @throws
	*/
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

    /**
    * @Title: getDataSource
    * @Description:
    * @param @return
    * @return DataSource
    * @throws
    */
    public DataSource getDataSource() {
    	return dataSource;
    }

	/**
	* @Title: getDBVersion
	* @Description:
	* @param @return
	* @return Object
	* @throws
	*/
	private String getDBVersion() {

		return SystemGlobals.getPreference("db_version");
	}
	//=========================================================================================================


	/* (no Javadoc)
	* Title: executeQueryPage
	* Description:
	* @param sql
	* @param parameters
	* @param listpager
	* @param dbType
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#executeQueryPage(java.lang.String, java.lang.Object[], com.cta.platform.persistence.util.ListPager, int)
	*/
	@Override
	public List<Map<String, Object>> executeQueryPage(String sql,
			Object[] parameters, ListPager listpager, int dbType)
			throws SQLException {
		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			return dboperator.executeQueryPage(conn,sql,parameters,listpager,dbType);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	/* (no Javadoc)
	* Title: executeQueryPage
	* Description:
	* @param sql
	* @param parameters
	* @param listPager
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#executeQueryPage(java.lang.String, java.lang.Object[], com.cta.platform.persistence.util.ListPager)
	*/
	@Override
	public List<Map<String, Object>> executeQueryPage(String sql,
			Object[] parameters, ListPager listPager) throws SQLException {

		return executeQueryPage(sql,parameters,listPager,dbType);
	}

	/* (no Javadoc)
	* Title: executeQuery
	* Description:
	* @param sql
	* @param parameters
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#executeQuery(java.lang.String, java.lang.Object[])
	*/
	@Override
	public List<Map<String, Object>> executeQuery(String sql,
			Object[] parameters) throws SQLException {

		return executeQueryPage(sql,parameters,null);
	}

	/**
	* @Title: executeQueryOne
	* @Description:
	* @param @param sql
	* @param @param parameters
	* @param @return
	* @param @throws SQLException
	* @return Map<String,Object>
	* @throws
	*/
	public Map<String,Object> executeQueryOne(String sql,Object[] parameters) throws SQLException{
		List<Map<String,Object>> records = executeQuery(sql,parameters);
		if(null != records && records.size()>0){
			return records.get(0);
		}
		return null;
	}

	/* (no Javadoc)
	* Title: selectPage
	* Description:
	* @param <T>
	* @param sql
	* @param parameters
	* @param clazz
	* @param listPager
	* @param dbType
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#selectPage(java.lang.String, java.lang.Object[], java.lang.Class, com.cta.platform.util.ListPager, int)
	*/
	@Override
	public <T> List<T> selectPage(String sql, Object[] parameters,Class<T> clazz, ListPager listPager, int dbType)throws SQLException {
		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			return dboperator.selectPage(conn,sql,parameters,clazz,listPager,dbType);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	/** (no Javadoc)
	* Title: selectPage
	* Description:
	* @param <T>
	* @param sql
	* @param parameters
	* @param clazz
	* @param listPager
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#selectPage(java.lang.String, java.lang.Object[], java.lang.Class, com.cta.platform.persistence.util.ListPager)
	*/
	@Override
	public <T> List<T> selectPage(String sql, Object[] parameters,
			Class<T> clazz, ListPager listPager) throws SQLException {

		return selectPage(sql,parameters,clazz,listPager,dbType);
	}

	/* (no Javadoc)
	* Title: selectPage
	* Description:
	* @param <T>
	* @param sql
	* @param parameters
	* @param clazz
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#selectPage(java.lang.String, java.lang.Object[], java.lang.Class)
	*/
	@Override
	public <T> List<T> selectPage(String sql, Object[] parameters,
			Class<T> clazz) throws SQLException {

		return selectPage(sql, parameters, clazz, null);
	}

	/* (no Javadoc)
	* Title: selectPage
	* Description:
	* @param <T>
	* @param sql
	* @param clazz
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#selectPage(java.lang.String, java.lang.Object)
	*/
	@Override
	public <T> List<T> selectPage(String sql, Class<T> clazz) throws SQLException {

		return selectPage(sql, null, clazz);
	}

	/**
	* @Title: selectPage
	* @Description: 选择从 from到to条数据
	* @param @param <T>
	* @param @param sql
	* @param @param parameters
	* @param @param clazz
	* @param @param from
	* @param @param to
	* @param @return
	* @param @throws SQLException
	* @return List<T>
	* @throws
	*/
	public <T> List<T> selectPage(String sql,Object[] parameters,Class<T> clazz,long from,long to) throws SQLException{

		String newSql = ListPager.buildPagedSQL(sql,dbType,from,to);
		return selectPage(newSql, parameters, clazz);
	}


	/**
	 * @Title: selectPage
	 * @Description: 选择从 from到to条数据
	 * @param @param <T>
	 * @param @param sql
	 * @param @param parameters
	 * @param @param clazz
	 * @param @param from
	 * @param @param to
	 * @param @return
	 * @param @throws SQLException
	 * @return List<T>
	 * @throws
	 */
	public <T> List<T> selectPage(String sql,Class<T> clazz,long from,long to) throws SQLException{

		return selectPage(sql, null, clazz, from, to);
	}



	/* (no Javadoc)
	* Title: selectById
	* Description:
	* @param <T>
	* @param id
	* @param clazz
	* @return
	* @throws SQLException
	* @see com.cta.dao.IBaseDao#selectById(java.lang.Object, java.lang.Class)
	*/
	@Override
	public <T> T selectById(Object id, Class<T> clazz) throws SQLException {

		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			return dboperator.selectById(conn,id,clazz);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}


	/* (no Javadoc)
	* Title: selectAll
	* Description:
	* @param <T>
	* @param sql
	* @param clazz
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#selectAll(java.lang.String, java.lang.Class)
	*/
	@Override
	public <T> List<T> selectAll(String sql, Class<T> clazz)
			throws SQLException {

		return selectAll(sql,null,clazz);
	}

	/* (no Javadoc)
	* Title: selectAll
	* Description:
	* @param <T>
	* @param sql
	* @param parameters
	* @param clazz
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#selectAll(java.lang.String, java.lang.Object[], java.lang.Class)
	*/
	@Override
	public <T> List<T> selectAll(String sql, Object[] parameters, Class<T> clazz)
			throws SQLException {

		return selectPage(sql, parameters, clazz);
	}

	/* (no Javadoc)
	* Title: insert
	* Description:
	* @param <T>
	* @param clazz
	* @throws SQLException
	* @see com.cta.dao.IBaseDao#insert(java.lang.Class)
	*/
	@Override
	public <T> void insert(T entity) throws SQLException {

		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			dboperator.insert(conn,entity);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}


	/* (no Javadoc)
	* Title: insert_pk
	* Description:
	* @param <T>
	* @param entity
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#insert_pk(java.lang.Object)
	*/
	@Override
	public <T> Integer insert_pk(T entity) throws SQLException {

		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			return dboperator.insert_pk(conn,entity);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	/* (no Javadoc)
	* Title: delete
	* Description:
	* @param <T>
	* @param clazz
	* @throws SQLException
	* @see com.cta.dao.IBaseDao#delete(java.lang.Class)
	*/
	@Override
	public <T> void delete(T entity) throws SQLException {
		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			dboperator.delete(conn,entity);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	/* (no Javadoc)
	* Title: deleteById
	* Description:
	* @param <T>
	* @param clazz
	* @throws SQLException
	* @see com.cta.dao.IBaseDao#deleteById(java.lang.Class)
	*/
	@Override
	public <T> void deleteById(Object id,Class<T> clazz) throws SQLException {
		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			dboperator.deleteById(conn,id,clazz);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
	/* (no Javadoc)
	* Title: selectOne
	* Description:
	* @param <T>
	* @param sql
	* @param parameters
	* @param clazz
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#selectOne(java.lang.String, java.lang.Object[], java.lang.Class)
	*/
	@Override
	public <T> T selectOne(String sql, Object[] parameters, Class<T> clazz)
			throws SQLException {

		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			List<T> records = selectPage(sql, parameters, clazz);
			return records.size()>0 ? records.get(0) : null;
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
	/* (no Javadoc)
	 * Title: selectOne
	 * Description:
	 * @param <T>
	 * @param sql
	 * @param parameters
	 * @param clazz
	 * @return
	 * @throws SQLException
	 * @see com.cta.platform.persistence.dao.IBaseDao#selectOne(java.lang.String, java.lang.Object[], java.lang.Class)
	 */
	@Override
	public <T> T selectOne(String sql,Class<T> clazz)
	throws SQLException {

		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			List<T> records = selectPage(sql,clazz);
			return records.size()>0 ? records.get(0) : null;
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}


	/* (no Javadoc)
	* Title: update
	* Description:
	* @param <T>
	* @param entity
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#update(java.lang.Object)
	*/
	@Override
	public <T> void update(T entity) throws SQLException {

		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			dboperator.update(conn,entity);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	/* (no Javadoc)
	* Title: executeQueryInt
	* Description:
	* @param sql
	* @param parameters
	* @param dbType
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#executeQueryInt(java.lang.String, java.lang.Object[], int)
	*/
	@Override
	public List<Integer> executeQueryForInt(String sql, Object[] parameters,
			int dbType) throws SQLException {
		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			return dboperator.executeQueryInt(conn,sql,parameters);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	/* (no Javadoc)
	* Title: executeQueryForInt
	* Description:
	* @param sql
	* @param parameters
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#executeQueryForInt(java.lang.String, java.lang.Object[])
	*/
	@Override
	public List<Integer> executeQueryForInt(String sql, Object[] parameters)
			throws SQLException {
		return executeQueryForInt(sql,parameters,dbType);
	}
	/* (no Javadoc)
	* Title: executeQueryForInt
	* Description:
	* @param sql
	* @return
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#executeQueryForInt(java.lang.String)
	*/
	@Override
	public List<Integer> executeQueryForInt(String sql) throws SQLException {
		return executeQueryForInt(sql,null,dbType);
	}

	/* (no Javadoc)
	* Title: update
	* Description:
	* @param sql
	* @param parameters
	* @param dbType
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#update(java.lang.String, java.lang.Object[], int)
	*/
	@Override
	public void executeUpdate(String sql, Object[] parameters, int dbType)
			throws SQLException {
		Connection conn = null;
		try{
			conn = DataSourceUtils.doGetConnection(dataSource);
			dboperator.executeUpdate(conn,sql,parameters,dbType);
		}finally{
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}

	/* (no Javadoc)
	* Title: executeUpdate
	* Description:
	* @param sql
	* @param parameters
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#executeUpdate(java.lang.String, java.lang.Object[])
	*/
	@Override
	public void executeUpdate(String sql, Object[] parameters)
			throws SQLException {

		this.executeUpdate(sql,parameters,dbType);
	}

	/* (no Javadoc)
	* Title: update
	* Description:
	* @param sql
	* @throws SQLException
	* @see com.cta.platform.persistence.dao.IBaseDao#update(java.lang.String)
	*/
	@Override
	public void executeUpdate(String sql) throws SQLException {
		this.executeUpdate(sql, null, dbType);
	}
}
