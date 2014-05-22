/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.dispatcher;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.rotek.constant.TaskType;
import com.rotek.entity.TaskEntity;
import com.rotek.service.impl.TaskService;

/**
 * @ClassName: dispatcher
 * @Description: 
 * @author chenwenpeng
 * @date 2013-8-16 下午3:09:20
 */
@Service
public class SystemTaskDispatcher  implements ServletContextAware{
	@Autowired
	private TaskService taskService;
	/**
	* @Title: poolTask
	* @Description: 轮询任务
	* @param @throws Exception 
	* @return void 
	* @throws 
	*/ 
	public void poolTask() throws Exception {
		System.out.println("---------->系统任务轮询中...");
		try{
			List<TaskEntity> tasks = taskService.getTask(new Date());
			if(null != tasks && tasks.size()>0){
				for(TaskEntity task : tasks){
					Integer task_id = task.getId();
					Integer target_id = task.getTarget_id();
					Integer type = task.getType();
					//普通订单预定任务
					if(TaskType.RESERVEORDER == type){
						
						taskService.sendOrder(target_id);
						taskService.deleteTask(task_id+"");
						System.out.println("------>订单发送成功");
					//店铺打样任务
					}else if(TaskType.RESTAURANTCLOSE == type){
						
						taskService.closeRest(target_id);
						taskService.putoffTask(task);
						System.out.println("------>店铺打样成功");
					//店铺营业任务
					}else if(TaskType.RESTAURANTOPEN == type){
						
						taskService.openRest(target_id);
						taskService.putoffTask(task);
						System.out.println("------>店铺营业成功");
					}
				}
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	@Override
	public void setServletContext(ServletContext context) {
		
	}
}
