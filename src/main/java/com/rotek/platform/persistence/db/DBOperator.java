/**
* @FileName: DBOperator.java
* @Package com.rotek.platform.persistence.db
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-7 上午09:04:46
* @version V1.0
*/
package com.rotek.platform.persistence.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import com.rotek.platform.constant.LobType;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.TypeUtils;


/**
 * @ClassName: DBOperator
 * @Description: 操作数据库的类，不要在框架外部调用
 * @author chenwenpeng
 * @date 2013-5-7 上午09:04:46
 *
 */
public class DBOperator {
	/** The logger. */
    private static Logger logger = Logger.getLogger(DBOperator.class);
	/** The nestLoad. */
    private boolean nestLoad = true;
    /**@Field the int dbType*/
    private int dbType = DBOption.DB_MYSQL;
    /**@Field the String dbVersion*/
    private String dbVersion = null;
    /**@Field the Integer GENERATESTRATEGY*/
    private Integer GENERATESTRATEGY = 1;

	/**
	 * @param nest
	 * @param dbType
	 * @param dbVersion
	 */
	public DBOperator(boolean nest, Integer dbType, String dbVersion) {
		this.nestLoad = nest;
		this.dbType = dbType;
		this.dbVersion = dbVersion;
	}

	/**
	* @Title: isNestLoad
	* @Description:
	* @param @return
	* @return boolean
	* @throws
	*/
	public boolean isNestLoad() {
		return nestLoad;
	}
	/**
	* @Title: setNestLoad
	* @Description:
	* @param @param nestLoad
	* @return void
	* @throws
	*/
	public void setNestLoad(boolean nestLoad) {
		this.nestLoad = nestLoad;
	}

	public int getDbType() {
		return dbType;
	}

	public void setDbType(int dbType) {
		this.dbType = dbType;
	}

	public String getDbVersion() {
		return dbVersion;
	}

	public void setDbVersion(String dbVersion) {
		this.dbVersion = dbVersion;
	}

	//==============================================================================================

	/**
	* @Title: executeQueryPage
	* @Description:
	* @param @param conn
	* @param @param sql
	* @param @param parameters
	* @param @param listpager
	* @param @param dbType2
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> executeQueryPage(Connection conn,
			String sql, Object[] parameters, ListPager listPager, int dbType) throws SQLException{
		List<Map<String,Object>> records = new LinkedList<Map<String,Object>>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if(null != listPager){
				selectTotalRowcount(conn,sql,parameters,listPager);
				sql = listPager.buildPagedSQL(sql,dbType);
			}
			ps = conn.prepareStatement(sql);
			//准备参数
			DBHelper.setPreparedParameters(ps,parameters);
			rs = ps.executeQuery();
			//取出数据，并返回Map<String,Object>
			Map<String,Object> record = null;
			while(rs.next()){
				record = DBHelper.getResultsetRow(rs);
				records.add(record);
			}

		} catch (SQLException e) {
			logger.error("SQLException",e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps,rs);
		}
		return records;
	}

	/**
	 * @throws SQLException
	* @Title: selectTotalRowcount
	* @Description: 获取总条数
	* @param @param conn
	* @param @param sql
	* @param @param parameters
	* @param @param dbType2
	* @return void
	* @throws
	*/
	private Long selectTotalRowcount(Connection conn, String sql,
			Object[] parameters,ListPager listPager) throws SQLException {
		Long totalRoleCount = selectTotalRowCount(conn,sql,parameters);
		listPager.setTotalRows(totalRoleCount);
		return totalRoleCount;
	}

	/**
	 * @throws SQLException
	* @Title: selectTotalRowCount
	* @Description: 获取总条数
	* @param @param conn
	* @param @param sql
	* @param @param parameters
	* @param @return
	* @return Long
	* @throws
	*/
	private Long selectTotalRowCount(Connection conn, String sql,
			Object[] parameters) throws SQLException {
		Long roleCount = 0l;
		String newSql = ListPager.buildRowCountSQL(sql);
		PreparedStatement ps = null;
		ResultSet rs = null;

		ps = conn.prepareStatement(newSql);
		DBHelper.setPreparedParameters(ps, parameters);
		rs = ps.executeQuery();
		if(rs.next()){
			roleCount = rs.getLong(1);
		}
		return roleCount;
	}

	/**
	 * @throws SQLException
	* @Title: executeQueryPage
	* @Description:
	* @param @param conn
	* @param @param sql
	* @param @param parameters
	* @param @param listPager
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> executeQueryPage(Connection conn,
			String sql, Object[] parameters, ListPager listPager) throws SQLException {

		return executeQueryPage(conn,sql,parameters,listPager,dbType);
	}

	/**
	 * @throws SQLException
	* @Title: executeQueryPage
	* @Description:
	* @param @param conn
	* @param @param sql
	* @param @param parameters
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> executeQueryPage(Connection conn,
			String sql, Object[] parameters) throws SQLException {

		return executeQueryPage(conn,sql,parameters,null,dbType);
	}

	/**
	 * @throws SQLException
	* @Title: selectPage
	* @Description: 根据实体类返回这个实体类对应的库的数据的分页列表
	* @param @param sql
	* @param @param parameters
	* @param @param class1
	* @param @param listPager
	* @param @param dbType
	* @param @return
	* @return List<T>
	* @throws
	*/
	public <T> List<T> selectPage(Connection conn,String sql, Object[] parameters,
			Class<T> clazz, ListPager listPager, int dbType) throws SQLException {
		List<T> records = new LinkedList<T>();
		T record = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			if(null != listPager){
				selectTotalRowcount(conn, sql, parameters, listPager);
				sql = listPager.buildPagedSQL(sql, dbType);
			}
			ps = conn.prepareStatement(sql);
			//设置参数
			DBHelper.setPreparedParameters(ps, parameters);
			rs = ps.executeQuery();
			while(rs.next()){
				//根据rs获取实体类的实例
				record = createEntityFromRS(conn,rs,clazz);
				records.add(record);
			}
		}catch (Exception e) {
			logger.error("SQLException",e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps, rs);
		}
		return records;
	}

	/**

	* @Title: createEntityFromRS
	* @Description: 根据rs 创建实体类的对象
	* @param @param conn
	* @param @param rs
	* @param @param clazz
	* @param @return
	* @return T
	* @throws IllegalAccessException
	* @throws InstantiationException 	*
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	*/
	@SuppressWarnings("unchecked")
	private <T> T createEntityFromRS(Connection conn, ResultSet rs,
			Class<T> clazz) throws InstantiationException, IllegalAccessException, SQLException, InvocationTargetException, NoSuchMethodException {
		//实体类对象
		T record = null;
		//实体类工具
		Map<String,PropertyMapping> propertyMappings = null;

		ObjectMapping objectMapping = EntityMappingFactory.getObjectMapping(clazz);
		//创建T的实例
		record = (T)EntityFactory.createEntity(objectMapping);
		propertyMappings = objectMapping.getPropertyMapping();

		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for(int i = 1;i<=columnCount;i++){
			Object value = null;
			String columnClassName = metaData.getColumnClassName(i);
			//列名和属性名是对应的
//			String columnName = metaData.getColumnName(i);
			String columnName = metaData.getColumnLabel(i);
			//根据表中列名，获取对应的实体类属性名
			PropertyMapping propertyMapping = propertyMappings.get(columnName.toUpperCase());
			if(null != propertyMapping && null != propertyMapping.getLobType()){
				//Text类型
				if(LobType.CLOB == propertyMapping.getLobType()){
					value = DBHelper.getClobValue(rs, columnName);
				}
			}else if(TypeUtils.isBaseType(columnClassName)){
				value = DBHelper.getResultsetValue(rs, i);
			}

			PropertyUtils.setProperty(record, columnName, value);
		}
		return record;
	}


	/**
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	* @Title: createEntityFromRS
	* @Description: 获取特殊实体类的值
	* @param @param <T>
	* @param @param conn
	* @param @param rs
	* @param @param entityMapping
	* @param @param clazz
	* @param @return
	* @return T
	* @throws
	*/
	@SuppressWarnings("unchecked")
	public <T> T createEntityFromRS(Connection conn,ResultSet rs,EntityMapping entityMapping,Class<T> clazz) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		T record = (T) EntityFactory.createEntity(entityMapping);
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for(int i = 1;i<=columnCount;i++){
			//实体类对应的propertyMapping
			Map<String,PropertyMapping> propertyMappings = entityMapping.getPropertyMapping();
			Object value = null;
			String columnClassName = metaData.getColumnClassName(i);
			//列名和属性名是对应的
			String columnName = metaData.getColumnName(i);
			//根据表中列名，获取对应的实体类属性名
			PropertyMapping propertyMapping = propertyMappings.get(columnName.toUpperCase());
			if(null != propertyMapping && null != propertyMapping.getLobType()){
				//Mysql 是Text类型
				if(LobType.CLOB == propertyMapping.getLobType()){
					value = DBHelper.getClobValue(rs, columnName);
				}
			}else if(TypeUtils.isBaseType(columnClassName)){
				value = DBHelper.getResultsetValue(rs, i);
			}

			PropertyUtils.setProperty(record, columnName, value);
		}
		return record;
	}

	/**
	 * @throws SQLException
	* @Title: selectById
	* @Description: 根据id 查询一条数据
	* @param @param <T>
	* @param @param conn
	* @param @param clzz
	* @param @return
	* @return T
	* @throws
	*/
	public <T> T selectById(Connection conn,Object id,Class<T> clazz) throws SQLException{

		T record = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String idName = null;
		try{
			EntityMapping entityMapping = EntityMappingFactory.getEntityMapping(clazz);
			StringBuilder sql = new StringBuilder();
			String tableName = entityMapping.getTableName();
			PropertyMapping idProperty = entityMapping.getIdPropertyMapping();
			if(null == tableName || null == idProperty){
				throw new SQLException("tablename or primarykey can not be null");
			}
			idName = idProperty.getPropertyName();
			sql.append("select * from ");
			sql.append(tableName);
			sql.append(" where ");
			sql.append(idName);
			sql.append(" = ?");
			ps = conn.prepareStatement(sql.toString());
			DBHelper.setPreparedParameters(ps, new Object[]{id});
			rs = ps.executeQuery();
			if(rs.next()){
				record = createEntityFromRS(conn, rs, entityMapping ,clazz);
			}
		}catch (Exception e) {
			logger.error("Exception",e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps, rs);
		}
		return record;
	}

	/**
	* @Title: insert
	* @Description: 插入一条数据
	* @param @param <T>
	* @param @param entity
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	public <T> void insert(Connection conn,T entity) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			EntityMapping entityMapping = EntityMappingFactory.getEntityMapping(entity.getClass());
			String tableName = entityMapping.getTableName();
			PropertyMapping idProperty = entityMapping.getIdPropertyMapping();
			if(null == tableName || null == idProperty){
				throw new SQLException("tablename or primarykey can not be null");
			}
			//参数的值
			List<Object> params = new LinkedList<Object>();
			String sql = DBHelper.getInsertSql(entityMapping,entity,params,dbType);
			ps = conn.prepareStatement(sql);
			DBHelper.setPreparedParameters(ps, params.toArray());

			ps.executeUpdate();
		}catch (Exception e) {
			logger.error("Exception",e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps, rs);
		}
	}

	/**
	 * @Title: insert
	 * @Description: 插入一条数据,返回刚生成主键的id
	 * @param @param <T>
	 * @param @param entity
	 * @param @throws SQLException
	 * @return void
	 * @throws
	 */
	public <T> Integer insert_pk(Connection conn,T entity) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			EntityMapping entityMapping = EntityMappingFactory.getEntityMapping(entity.getClass());
			String tableName = entityMapping.getTableName();
			PropertyMapping idProperty = entityMapping.getIdPropertyMapping();
			if(null == tableName || null == idProperty){
				throw new SQLException("tablename or primarykey can not be null");
			}
			List<Object> params = new LinkedList<Object>();
			String sql = DBHelper.getInsertSql(entityMapping,entity,params,dbType);
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			DBHelper.setPreparedParameters(ps, params.toArray());

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			while(rs.next()){
				return rs.getInt(1);
			}
		}catch (Exception e) {
			logger.error("Exception",e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps, rs);
		}
		return null;
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: update
	* @Description:
	* @param @param <T>
	* @param @param entity
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	public <T> void update(Connection conn,T entity) throws SQLException{

		PreparedStatement ps = null;
		try{
			EntityMapping entityMapping = EntityMappingFactory.getEntityMapping(entity.getClass());
			String tableName = entityMapping.getTableName();
			PropertyMapping idProperty = entityMapping.getIdPropertyMapping();
			if(null == tableName || null == idProperty){
				throw new SQLException("tablename or primarykey can not be null");
			}

			//参数列表
			List<Object> params = new LinkedList<Object>();
			String sql = DBHelper.getUpdateSql(entityMapping,entity,params,dbType);
			ps = conn.prepareStatement(sql);
			DBHelper.setPreparedParameters(ps, params.toArray());
			ps.executeUpdate();

		}catch (Exception e) {
			logger.error("Exception", e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps, null);
		}
	}

	/**
	* @Title: delete
	* @Description:
	* @param @param <T>
	* @param @param conn
	* @param @param entity
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	public <T> void delete(Connection conn,T entity) throws SQLException{

		PreparedStatement ps = null;
		try{
			EntityMapping entityMapping = EntityMappingFactory.getEntityMapping(entity.getClass());
			String tableName = entityMapping.getTableName();
			PropertyMapping idProperty = entityMapping.getIdPropertyMapping();
			if(null == tableName || null == idProperty){
				throw new SQLException("tablename or primarykey can not be null");
			}

			//参数列表
			List<Object> params = new LinkedList<Object>();
			String sql = DBHelper.getDeleteSql(entityMapping,entity,params,dbType);
			ps = conn.prepareStatement(sql);
			DBHelper.setPreparedParameters(ps, params.toArray());
			ps.executeUpdate();

		}catch (Exception e) {
			logger.error("Exception", e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps, null);
		}
	}

	/**
	 * @Title: delete
	 * @Description:
	 * @param @param <T>
	 * @param @param conn
	 * @param @param entity
	 * @param @throws SQLException
	 * @return void
	 * @throws
	 */
	public <T> void deleteById(Connection conn,Object id,Class<T> clazz) throws SQLException{

		PreparedStatement ps = null;
		try{
			EntityMapping entityMapping = EntityMappingFactory.getEntityMapping(clazz);
			String tableName = entityMapping.getTableName();
			PropertyMapping idProperty = entityMapping.getIdPropertyMapping();
			if(null == tableName || null == idProperty){
				throw new SQLException("tablename or primarykey can not be null");
			}
			StringBuilder sql = new StringBuilder();
			sql.append("delete from ");
			sql.append(tableName);
			sql.append(" where ");
			sql.append(idProperty.getPropertyName());
			sql.append("=?");
			ps = conn.prepareStatement(sql.toString());
			DBHelper.setPreparedParameters(ps, new Object[]{id});
			ps.executeUpdate();

		}catch (SQLException e) {
			logger.error("SQLException", e);
			throw new SQLException(e);
		}finally{
			DBHelper.close(ps, null);
		}
	}

	/**
	* @Title: executeUpdate
	* @Description:
	* @param @param conn
	* @param @param sql
	* @param @param parameters
	* @param @param dbType
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	public void executeUpdate(Connection conn,String sql,Object[] parameters,int dbType) throws SQLException{
		PreparedStatement ps = null;
		try {
		    ps = conn.prepareStatement(sql);
		    DBHelper.setPreparedParameters(ps, parameters);
		    ps.executeUpdate();
		}catch (Exception e) {
			logger.error("SQLException", e);
			throw new SQLException(e);
		} finally {
		    DBHelper.close(ps,null);
		}
	}

	/**
	 * @throws SQLException
	* @Title: executeQueryInt
	* @Description: 查询int List
	* @param @param sql
	* @param @param parameters
	* @param @return
	* @return List<Integer>
	* @throws
	*/
	public List<Integer> executeQueryInt(Connection conn,String sql, Object[] parameters) throws SQLException {
		List<Integer> ids = new ArrayList<Integer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		    ps = conn.prepareStatement(sql);
		    DBHelper.setPreparedParameters(ps, parameters);
		    rs = ps.executeQuery();
		    while(rs.next()){
		    	ids.add(rs.getInt(1));
		    }
		} finally {
		    DBHelper.close(ps,null);
		}
		return ids;
	}
}
