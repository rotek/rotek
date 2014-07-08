package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.ProjectDao;
import com.rotek.dto.ProjectDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.ProjectEntity;
import com.rotek.util.FileUtils;

/**
* @ClassName:ProjectService
* @Description: 工程信息Service
* @Author WangJuZhu
* @date 2014年6月10日 下午4:21:22
* @Version:1.1.0
*/
@Service
public class ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	/**
	* @MethodName: listProeject 
	* @Description: 工程列表查询
	* @param user
	* @param project
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ProjectDto> listProeject(UserDto user, ProjectEntity project,
			Date start_azsj, Date end_azsj, Date start_tysj, Date end_tysj, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		
		sql.append("select pro.*,custom.MC AS customer_name from r_project pro ");
		sql.append(" left join r_customer custom on custom.id = pro.r_customer_id");
		sql.append(" where 1=1 ");
		
		if(StringUtils.isNotEmpty(project.getGcmc())){
			sql.append(" and GCMC like '%").append(project.getGcmc()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(project.getGcbh())){
			sql.append(" and GCBH like '%").append(project.getGcbh()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(project.getGcxh())){
			sql.append(" and GCXH like '%").append(project.getGcxh()).append("%'");
		}

		if(null != project.getId()){
			sql.append(" and id = ?");
			params.add(project.getId());
		}
		
		if(null != project.getGclb()){
			sql.append(" and GCFL = ?");
			params.add(project.getGclb());
		}

		if(null != project.getStatus()){
			sql.append(" and status = ?");
			params.add(project.getStatus());
		}

		if(null != start_azsj){
			sql.append(" and AZSJ >= ?");
			params.add(start_azsj);
		}
		if(null != end_azsj){
			sql.append(" and AZSJ <= ?");
			params.add(end_azsj);
		}
		
		if(null != start_tysj){
			sql.append(" and TYSJ >= ?");
			params.add(start_tysj);
		}
		if(null != end_tysj){
			sql.append(" and TYSJ <= ?");
			params.add(end_tysj);
		}
		
		sql.append(" order by id desc");
		return projectDao.listProject(sql.toString(), params.toArray(), pager);
	}
	
	public List<ProjectEntity> listProjectByStatus(Integer status) throws SQLException{
		return projectDao.listProjectByStatus(status);
	}
	
	public List<ProjectEntity> selectProjectByType(Integer status,Integer type) throws SQLException{
		return projectDao.selectProjectByType(status,type);
	}
	
	/**
	* @MethodName: addProject 
	* @Description: 新增工程信息
	* @param project
	* @param multipartRequest
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> addProject(ProjectEntity project, MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> messages = ValidateUtil.validate(project);
		if(messages.size() > 0){
			return messages;
		}
		MultipartFile proPic = multipartRequest.getFile("gczp");   // 工程图片
		MultipartFile proParamAffix = multipartRequest.getFile("jscsfj");  // 技术参数附件
		
		//保存工程图片
		if(null != proPic && StringUtils.isNotBlank(proPic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("project.gczp.path");
			String pic_name = FileUtils.savePic(proPic, pic_location, 1024000000);
			if(null != pic_name){
				project.setGczp(pic_name);
			}else {
				messages.add("工程图片必须在 "+1024000000/1024 +"k 以内!");  // 100M
				return messages;
			}
		}
		//保存工程技术参数附件
		if(null != proParamAffix && StringUtils.isNotBlank(proParamAffix.getOriginalFilename())){
			String paramAffix_location = SystemGlobals.getPreference("project.jscsfj.path");
			String paramAffixName = FileUtils.savePic(proParamAffix, paramAffix_location, 1024000000);
			if(null != paramAffixName){
				project.setJscsfj(paramAffixName);
			}
		}
		projectDao.addProject(project);
		
		return null;
	}
	
	/**
	* @MethodName: getProjectById 
	* @Description: 根据Id查询工程信息
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ProjectEntity getProjectById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return projectDao.getProjectById(id);
	}
	
	public ProjectDto getProjectDtoById(Integer id) throws SQLException {
		return projectDao.getProjectDtoById(id);
	}
	
	public List<String> modifyProject(ProjectEntity project,MultipartHttpServletRequest multipartRequest) 
			throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(project);
		if(messages.size() > 0 || null == project.getId()){
			messages.add(null == project.getId() ? "由于，要修改的工程记录ID为空，修改失败!" : "");
			return messages;
		}

		MultipartFile pic = multipartRequest.getFile("gczp");   // 工程图片
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("project.gczp.path");
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, 102400000);
			if(null != pic_name){
				//删除原图片
				FileUtils.clearPic(pic_location, project.getGczp());
				//更新工程图片
				project.setGczp(pic_name);
			}else {
				messages.add("工程图片必须在 "+ 102400000/1024 +"k 以内!");  // 100M
				return messages;
			}
		}
		
		MultipartFile csfj = multipartRequest.getFile("jscsfj");   // 技术参数附件
		if(null != csfj && StringUtils.isNotBlank(csfj.getOriginalFilename())){
			String fj_location = SystemGlobals.getPreference("project.jscsfj.path");
			//保存附件
			String fj_name = FileUtils.savePic(csfj, fj_location, 1024000000);
			if(null != fj_name){
				//删除原附件
				FileUtils.clearPic(fj_location, project.getJscsfj());
				//更新附件
				project.setJscsfj(fj_name);
			}else {
				messages.add("技术参数附件必须在 "+1024000000/1024 +"k 以内!");  // 100M
				return messages;
			}
		}
		
		//修改
		projectDao.modifyProject(project);
		return null;
	}

	/**
	* @MethodName: deleteProject 
	* @Description: 批量删除工程（更改记录的状态为无效(status = -1)）
	* @param ids
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> deleteProject(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_project set STATUS = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		projectDao.deleteProject(sql.toString());
		return messages;
	}

	/**
	 * @param r_customer_id
	 * @return
	 * @throws SQLException
	 */
	public List<ProjectEntity> getProjectListByCustomerId(Integer r_customer_id)
			throws SQLException {
		
		return projectDao.getProjectListByCustomerId(r_customer_id);
	}
}
