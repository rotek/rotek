/**
 * @FileName: DBHelper.java
 * @Package com.cta.platform.persistence.db
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-7 下午01:43:48
 * @version V1.0
 */
package com.cta.platform.persistence.db;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.common.Logger;

import org.apache.commons.beanutils.PropertyUtils;

import com.cta.platform.config.SystemGlobals;
import com.cta.platform.constant.LobType;
import com.cta.platform.constant.StrategyType;

/**
 * @ClassName: DBHelper
 * @Description: 数据库操作帮手类,不要在框架的外部调用
 * @author chenwenpeng
 * @date 2013-5-7 下午01:43:48
 * 
 */
public class DBHelper {

	/**
	 * 打印sql
	 */
	private static final int PRINT_SQL_TRUE = 1;

	public static Logger logger = Logger.getLogger(DBHelper.class);

	/**
	 * 关闭 ps和rs
	 * 
	 * @Title: close
	 * @Description:
	 * @param @param ps
	 * @param @param rs
	 * @return void
	 * @throws
	 */
	public static void close(PreparedStatement ps, ResultSet rs) {
		try {
			if (null != ps) {
				ps.close();
			}
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws SQLException
	 * @Title: setPreparedParameters
	 * @Description:
	 * @param @param ps
	 * @param @param parameters
	 * @return void
	 * @throws
	 */
	public static void setPreparedParameters(PreparedStatement ps,
			Object[] parameters) throws SQLException {
		ParameterMetaData parameterMetaData = ps.getParameterMetaData();
		// check before
		if (parameterMetaData.getParameterCount() > 0
				&& (null == parameters || parameters.length != parameterMetaData
						.getParameterCount())) {
			
			SQLException e = new SQLException("parameters number is not correct");
			logger.error(e);
			throw e;
		}
		if (null != parameters && parameters.length > 0) {
			Object param = null;
			for (int i = 1; i <= parameters.length; i++) {
				param = parameters[i - 1];
				if (param instanceof String) {
					ps.setString(i, (String) param);
				} else if (param instanceof Integer) {
					ps.setInt(i, (Integer) param);
				} else if (param instanceof Long) {
					ps.setLong(i, (Long) param);
				} else if (param instanceof Boolean) {
					ps.setBoolean(i, (Boolean) param);
				} else if (param instanceof java.util.Date) {
					ps.setTimestamp(i,
							new Timestamp(((java.util.Date) param).getTime()));
				} else if (param instanceof Timestamp) {
					ps.setTimestamp(i, (Timestamp) param);
				} else if (param instanceof java.sql.Date) {
					ps.setTimestamp(i,
							new Timestamp(((java.sql.Date) param).getTime()));
				} else if (param instanceof Double) {
					ps.setDouble(i, (Double) param);
				} else if (param instanceof Byte) {
					ps.setByte(i, (Byte) param);
				} else if (param instanceof Short) {
					ps.setShort(i, (Short) param);
				} else if (param instanceof Float) {
					ps.setFloat(i, (Float) param);
				} else if (param instanceof BigDecimal) {
					ps.setBigDecimal(i, (BigDecimal) param);
				} else if (param instanceof Reader) {
					ps.setCharacterStream(i, (Reader) param);
				} else if (param instanceof java.sql.Time) {
					ps.setTimestamp(i,
							new Timestamp(((java.sql.Time) param).getTime()));
				} else {
					SQLException e = new SQLException("SQLException",
							"unknown data type : " + param);
					logger.error(e);
					throw e;
				}
			}
		}
	}

	/**
	 * @throws SQLException
	 * @Title: getResultsetRow
	 * @Description: 根据Resultset 返回Map<String,Object>
	 * @param @param rs
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 */
	public static Map<String, Object> getResultsetRow(ResultSet rs)
			throws SQLException {
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		Map<String, Object> record = new HashMap<String, Object>();

		for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
			// 返回字段名称
			// String name = resultSetMetaData.getColumnName(i);
			String name = resultSetMetaData.getColumnLabel(i);
			// 返回字段值
			Object value = getResultsetValue(rs, i);
			record.put(name, value);
		}
		return record;
	}

	/**
	 * @throws SQLException
	 * @Title: getResultsetValue
	 * @Description: 返回某一个字段的值
	 * @param @param rs
	 * @param @param i
	 * @param @return
	 * @return Object
	 * @throws
	 */
	public static Object getResultsetValue(ResultSet rs, int columnIndex)
			throws SQLException {
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		// 字段类型名
		String columnClassName = resultSetMetaData
				.getColumnClassName(columnIndex);

		if ("byte".equals(columnClassName)
				|| "java.lang.Byte".equals(columnClassName)) {
			return new Byte(rs.getByte(columnIndex));
		} else if ("short".equals(columnClassName)
				|| "java.lang.Short".equals(columnClassName)) {
			return new Short(rs.getShort(columnIndex));
		} else if ("int".equals(columnClassName)
				|| "java.lang.Integer".equals(columnClassName)) {
			return new Integer(rs.getInt(columnIndex));
		} else if ("long".equals(columnClassName)
				|| "java.lang.Long".equals(columnClassName)) {
			return new Long(rs.getLong(columnIndex));
		} else if ("double".equals(columnClassName)
				|| "java.lang.Double".equals(columnClassName)) {
			return new Double(rs.getDouble(columnIndex));
		} else if ("float".equals(columnClassName)
				|| "java.lang.Float".equals(columnClassName)) {
			return new Float(rs.getFloat(columnIndex));
		} else if ("java.math.BigDecimal".equals(columnClassName)) {
			return rs.getBigDecimal(columnIndex);
		} else if ("java.lang.String".equals(columnClassName)) {
			return rs.getString(columnIndex);
		} else if ("java.util.Date".equals(columnClassName)
				|| "java.sql.Timestamp".equals(columnClassName)
				|| "java.sql.Date".equals(columnClassName)
				|| "java.sql.Time".equals(columnClassName)) {
			Timestamp t = rs.getTimestamp(columnIndex);
			if (t == null) {
				return null;
			} else {
				return new java.util.Date(t.getTime());
			}
		} else if (LobType.CLOB_NAME.equals(resultSetMetaData
				.getColumnTypeName(columnIndex))) {
			// 获取Clob的值
			return getClobValue(rs, columnIndex);
		} else {

			SQLException e = new SQLException(
					"Cannot Recognise the data Type, columnName="
							+ resultSetMetaData.getColumnName(columnIndex)
							+ ", type="
							+ resultSetMetaData.getColumnClassName(columnIndex));

			logger.error(e);
			throw e;
		}

	}

	/**
	 * @throws IOException
	 * @throws SQLException
	 * @Title: getClobValue
	 * @Description: 获取Clob的值(不允许用数据库存储图片格式，只允许存储大文本)
	 * @param @param rs
	 * @param @param columnName
	 * @param @return
	 * @return Object
	 * @throws
	 */
	public static String getClobValue(ResultSet rs, int columnIndex)
			throws SQLException {
		Reader reader = null;
		try {
			Clob clob = rs.getClob(columnIndex);
			if (null != clob) {
				StringBuffer sb = new StringBuffer();
				reader = clob.getCharacterStream();
				char[] buffer = new char[4096];
				int count = 0;
				if ((count = reader.read(buffer)) != -1) {
					sb.append(new String(buffer, 0, count));
				}
				return sb.toString();
			}
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {

					SQLException _e = new SQLException("get clob error", e);
					logger.error(_e);
					throw _e;
				}
			}
		}
		return null;
	}

	/**
	 * @throws IOException
	 * @throws SQLException
	 * @Title: getClobValue
	 * @Description: 获取Clob的值(不允许用数据库存储图片格式，只允许存储大文本)
	 * @param @param rs
	 * @param @param columnName
	 * @param @return
	 * @return Object
	 * @throws
	 */
	public static String getClobValue(ResultSet rs, String columnName)
			throws SQLException {
		Reader reader = null;
		try {
			Clob clob = rs.getClob(columnName);
			if (null != clob) {
				StringBuffer sb = new StringBuffer();
				reader = clob.getCharacterStream();
				char[] buffer = new char[4096];
				int count = 0;
				if ((count = reader.read(buffer)) != -1) {
					sb.append(new String(buffer, 0, count));
				}
				return sb.toString();
			}
		} catch (Exception e) {
			throw new SQLException(e);
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {

					SQLException _e = new SQLException("get clob error", e);
					logger.error(_e);
					throw _e;
				}
			}
		}
		return null;
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: getInsertSql
	 * @Description: 根据entityMapping返回插入语句
	 * @param @param entityMapping
	 * @param @param dbType
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static <T> String getInsertSql(EntityMapping entityMapping,
			T entity, List<Object> params, int dbType)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 前半部分sql
		StringBuilder sql = new StringBuilder();
		// 后半部分sql
		StringBuilder sql_params = new StringBuilder();
		sql.append("insert into ");
		sql.append(entityMapping.getTableName());
		sql.append(" (");
		sql_params.append("(");
		Map<String, PropertyMapping> propertyMappings = entityMapping
				.getPropertyMapping();
		for (PropertyMapping property : propertyMappings.values()) {
			if (property.isPrimaryKey()) {
				// mysql自动生成策略
				if (StrategyType.IDENTITY == property.getGenerateStrategy()) {
					continue;
				}
				if (StrategyType.SEQUENCE == property.getGenerateStrategy()) {

				}
			} else {
				String p_name = property.getPropertyName();
				Object p_value = PropertyUtils.getProperty(entity, p_name);

				sql.append(p_name + ",");
				sql_params.append("?,");
				params.add(p_value);
			}
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") values ");
		sql_params.deleteCharAt(sql_params.length() - 1);
		sql_params.append(")");
		sql.append(sql_params.toString());

		String sqlStr = sql.toString();
		if (PRINT_SQL_TRUE == SystemGlobals.getIntPreference("db.printSql",
				PRINT_SQL_TRUE)) {
			logger.info(sqlStr);
		}
		return sqlStr;
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: getUpdateSql
	 * @Description: 获取更新的sql
	 * @param @param <T>
	 * @param @param entityMapping
	 * @param @param entity
	 * @param @param dbType
	 * @param @return
	 * @param @throws SQLException
	 * @return String
	 * @throws
	 */
	public static <T> String getUpdateSql(EntityMapping entityMapping,
			T entity, List<Object> params, int dbType) throws SQLException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Map<String, PropertyMapping> propertyMappings = entityMapping
				.getPropertyMapping();
		// 前部分
		StringBuilder sql = new StringBuilder();
		// where
		StringBuilder sql_where = new StringBuilder();

		sql.append("update ");
		sql.append(entityMapping.getTableName());
		sql.append(" set ");

		for (PropertyMapping property : propertyMappings.values()) {
			String p_name = property.getPropertyName();
			Object p_value = PropertyUtils.getProperty(entity, p_name);
			// 如果为空不更新
			if (null == p_value) {
				continue;
			}
			if (property.isPrimaryKey()) {
				continue;
			}
			sql.append(p_name + "=?,");
			params.add(p_value);
		}
		PropertyMapping idPropertyMapping = entityMapping
				.getIdPropertyMapping();
		sql_where.append(" where ");
		sql_where.append(idPropertyMapping.getPropertyName() + "=?");
		params.add(PropertyUtils.getProperty(entity,
				idPropertyMapping.getPropertyName()));

		sql.deleteCharAt(sql.length() - 1);
		sql.append(sql_where.toString());
		String sqlStr = sql.toString();
		if (PRINT_SQL_TRUE == SystemGlobals.getIntPreference("db.printSql",
				PRINT_SQL_TRUE)) {
			logger.info(sqlStr);
		}
		return sqlStr;
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: getUpdateSql
	 * @Description: 获取删除信息的sql
	 * @param @param <T>
	 * @param @param entityMapping
	 * @param @param entity
	 * @param @param dbType
	 * @param @return
	 * @param @throws SQLException
	 * @return String
	 * @throws
	 */
	public static <T> String getDeleteSql(EntityMapping entityMapping,
			T entity, List<Object> params, int dbType) throws SQLException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// SQL
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ");
		sql.append(entityMapping.getTableName());
		PropertyMapping idPropertyMapping = entityMapping
				.getIdPropertyMapping();
		sql.append(" where ");
		sql.append(idPropertyMapping.getPropertyName() + "=?");
		params.add(PropertyUtils.getProperty(entity,
				idPropertyMapping.getPropertyName()));

		String sqlStr = sql.toString();
		if (PRINT_SQL_TRUE == SystemGlobals.getIntPreference("db.printSql",
				PRINT_SQL_TRUE)) {
			logger.info(sqlStr);
		}
		return sqlStr;
	}

}
