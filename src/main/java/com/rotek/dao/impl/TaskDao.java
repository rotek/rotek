/**
* @FileName: TaskDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-13 下午03:02:24
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rotek.entity.TaskEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: TaskDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-13 下午03:02:24
 *
 */
@Repository
public class TaskDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listTasks
	* @Description:
	* @param string
	* @param array
	* @return
	* @return List<TaskEntity>
	* @throws
	*/
	public List<TaskEntity> listTasks(String sql, Object[] params,ListPager pager) throws SQLException {

		return this.selectPage(sql, params, TaskEntity.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: deleteTask
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteTask(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	* @Title: addTask
	* @Description:
	* @param task
	* @return void
	* @throws
	*/
	public void addTask(TaskEntity task) throws SQLException {

		this.insert(task);
	}

	/**
	 * @throws SQLException
	* @Title: clearTask
	* @Description:
	* @param rest_id
	* @param restaurantclose
	* @return void
	* @throws
	*/
	public void clearTask(Integer target_id, int type) throws SQLException {

		String sql = "delete from mf_task where target_id = ? and type = ?";
		this.executeUpdate(sql, new Integer[]{target_id,type});
	}

	/**
	 * @throws SQLException 
	* @Title: getTask
	* @Description: 获取大于传入时间的task
	* @param @param date
	* @param @return 
	* @return TaskEntity 
	* @throws 
	*/ 
	public List<TaskEntity> getTask(Date time) throws SQLException {
		String sql = "select id,target_id,time,type from mf_task where time < ?";
		return this.selerotekll(sql, new Object[]{time}, TaskEntity.class);
	}

	/**
	 * @throws SQLException 
	* @Title: putoffTask
	* @Description: 
	* @param @param task 
	* @return void 
	* @throws 
	*/ 
	public void putoffTask(TaskEntity task) throws SQLException {
		
		this.update(task);
	}
}
