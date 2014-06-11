/**
* @FileName: IBaseDao.java
* @Package com.cta.dao
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-3 下午05:43:20
* @version V1.0
*/
package com.cta.platform.persistence.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cta.platform.util.ListPager;

/**
 * @ClassName: IBaseDao
 * @Description: dao层的接口
 * @author chenwenpeng
 * @date 2013-5-3 下午05:43:20
 *
 */
public interface IBaseDao {

	/**
	* @Title: executeQueryPage
	* @Description: 查询所有的数据，如果数据不存在返回空List
	* @param @param sql
	* @param @param parameters
	* @param @param listpager
	* @param @param dbType
	* @param @return
	* @param @throws SQLException
	* @return List<Map<String,Object>>
	* @throws
	*/
	List<Map<String,Object>> executeQueryPage(String sql,Object[] parameters,ListPager listpager,int dbType) throws SQLException;

	/**
	* @Title: executeQueryPage
	* @Description:
	* @param @param sql
	* @param @param parameters
	* @param @param listPager
	* @param @return
	* @param @throws SQLException
	* @return List<Map<String,Object>>
	* @throws
	*/
	List<Map<String,Object>> executeQueryPage(String sql,Object[] parameters,ListPager listPager) throws SQLException;
	/**
	* @Title: executeQuery
	* @Description: 查询所有数据，返回数据的map集合,如果数据不存在返回空List
	* @param @param sql
	* @param @param parameters
	* @param @return
	* @param @throws SQLException
	* @return List<Map<String,Object>>
	* @throws
	*/
	List<Map<String,Object>> executeQuery(String sql,Object[] parameters) throws SQLException;

	/**
	* @Title: executeQueryOne
	* @Description: 查询一条数据，返回Map形式的结果，如果数据不存在返回null
	* @param @param sql
	* @param @param parameters
	* @param @return
	* @param @throws SQLException
	* @return List<Map<String,Object>>
	* @throws
	*/
	Map<String,Object> executeQueryOne(String sql,Object[] parameters) throws SQLException;

	/**
	* @Title: executeQueryInt
	* @Description: 查询int的集合
	* @param @param sql
	* @param @param parameters
	* @param @param dbType
	* @param @return
	* @param @throws SQLException
	* @return List<Integer>
	* @throws
	*/
	List<Integer> executeQueryForInt(String sql,Object[] parameters,int dbType) throws SQLException;

	/**
	* @Title: executeQueryForInt
	* @Description:
	* @param @param sql
	* @param @param parameters
	* @param @return
	* @param @throws SQLException
	* @return List<Integer>
	* @throws
	*/
	List<Integer> executeQueryForInt(String sql,Object[] parameters) throws SQLException;
	/**
	* @Title: executeQueryForInt
	* @Description:
	* @param @param sql
	* @param @return
	* @param @throws SQLException
	* @return List<Integer>
	* @throws
	*/
	List<Integer> executeQueryForInt(String sql) throws SQLException;
	/**
	* @Title: selectPage
	* @Description: 查找所有的数据，返回实例的集合如果不存在返回空List
	* @param @param <T>
	* @param @param sql
	* @param @param parameters
	* @param @param clazz
	* @param @param listPager
	* @param @param dbType
	* @param @return
	* @param @throws SQLException
	* @return List<T>
	* @throws
	*/
	<T> List<T> selectPage(String sql,Object[] parameters,Class<T> clazz,ListPager listPager,int dbType) throws SQLException;
	/**
	* @Title: selectPage
	* @Description: 查找所有的数据，返回实例的集合如果不存在返回空List
	* @param @param <T>
	* @param @param sql
	* @param @param parameters
	* @param @param clazz
	* @param @param listPager
	* @param @return
	* @param @throws SQLException
	* @return List<T>
	* @throws
	*/
	<T> List<T> selectPage(String sql,Object[] parameters,Class<T> clazz,ListPager listPager) throws SQLException;

	/**
	* @Title: selectPage
	* @Description:查找制定的的数据，返回实例的集合如果不存在返回空List
	* @param @param <T>
	* @param @param sql
	* @param @param parameters
	* @param @param clazz
	* @param @return
	* @param @throws SQLException
	* @return List<T>
	* @throws
	*/
	<T> List<T> selectPage(String sql,Object[] parameters,Class<T> clazz) throws SQLException;
	/**
	* @Title: select
	* @Description: 查找所有的数据，返回实例的集合如果不存在返回空List
	* @param @param <T>
	* @param @param sql
	* @param @param clazz
	* @param @return
	* @param @throws SQLException
	* @return List<T>
	* @throws
	*/
	<T> List<T> selectPage(String sql,Class<T> clazz) throws SQLException;

	/**
	* @Title: selectPage
	* @Description: 获取从第from 到第to条数据
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
	<T> List<T> selectPage(String sql,Object[] parameters,Class<T> clazz,long from,long to) throws SQLException;


	/**
	 * @Title: selectPage
	 * @Description: 获取从第from 到第to条数据
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
	<T> List<T> selectPage(String sql,Class<T> clazz,long from,long to) throws SQLException;


	/**
	* @Title: selectById
	* @Description: 根据某条数据的id和实体类，返回这条数据对应的实例
	* @param @param <T>
	* @param @param id
	* @param @param clazz
	* @param @return
	* @param @throws SQLException
	* @return T
	* @throws
	*/
	<T> T selectById(Object id,Class<T> clazz) throws SQLException;


	/**
	* @Title: selectOne
	* @Description: 选择一条数据
	* @param @param <T>
	* @param @return
	* @param @throws SQLException
	* @return T
	* @throws
	*/
	<T> T selectOne(String sql, Object[] parameters, Class<T> clazz) throws SQLException;

	/**
	 * @Title: selectOne
	 * @Description: 选择一条数据
	 * @param @param <T>
	 * @param @return
	 * @param @throws SQLException
	 * @return T
	 * @throws
	 */
	<T> T selectOne(String sql,Class<T> clazz) throws SQLException;

	/**
	* @Title: selectAll
	* @Description: 选择所有的数据
	* @param @param <T>
	* @param @param sql
	* @param @param clazz
	* @param @return
	* @param @throws SQLException
	* @return List<T>
	* @throws
	*/
	<T> List<T> selectAll(String sql,Class<T> clazz) throws SQLException;

	/**
	 * @Title: selectAll
	 * @Description: 选择所有的数据
	 * @param @param <T>
	 * @param @param sql
	 * @param @param clazz
	 * @param @return
	 * @param @throws SQLException
	 * @return List<T>
	 * @throws
	 */
	<T> List<T> selectAll(String sql,Object[] parameters,Class<T> clazz) throws SQLException;

	/**
	* @Title: insert
	* @Description: 插入一条数据
	* @param @param <T>
	* @param @param clazz
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	<T> void insert(T entity) throws SQLException;

	/**
	 * @Title: insert
	 * @Description: 插入一条数据
	 * @param @param <T>
	 * @param @param clazz
	 * @param @throws SQLException
	 * @return void
	 * @throws
	 */
	<T> Integer insert_pk(T entity) throws SQLException;

	/**
	* @Title: update
	* @Description: 更新对象对应的数据
	* @param @param <T>
	* @param @param entity
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	<T> void update (T entity) throws SQLException;
	/**
	* @Title: delete
	* @Description: 删除一条数据
	* @param @param <T>
	* @param @param clazz
	* @param @throws SQLException
	* @return void
	* @throws
	*/

	/**
	* @Title: update
	* @Description:更新数据
	* @param @param sql
	* @param @param parameters
	* @param @param listpager
	* @param @param dbType
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	void executeUpdate(String sql,Object[] parameters,int dbType) throws SQLException;

	/**
	* @Title: executeUpdate
	* @Description:更新数据
	* @param @param sql
	* @param @param parameters
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	void executeUpdate(String sql,Object[] parameters) throws SQLException;
	/**
	* @Title: update
	* @Description:更新数据
	* @param @param sql
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	void executeUpdate (String sql) throws SQLException;

	/**
	* @Title: delete
	* @Description:
	* @param @param <T>
	* @param @param entity
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	<T> void delete(T entity) throws SQLException;

	/**
	* @Title: deleteById
	* @Description: 根据id删除一条数据
	* @param @param <T>
	* @param @param clazz
	* @param @throws SQLException
	* @return void
	* @throws
	*/
	<T> void deleteById(Object id,Class<T> clazz) throws SQLException;

}
