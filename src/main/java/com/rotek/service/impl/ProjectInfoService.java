package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
import com.rotek.dao.impl.ProjectInfoDao;
import com.rotek.dto.ProjectInfoDto;
import com.rotek.entity.ProjectInfoEntity;
import com.rotek.util.FileUtils;

/**
* @ClassName:ProjectInfoService
* @Description: 工程资料信息Service
* @Author WangJuZhu
* @date 2014年6月10日 下午4:21:22
* @Version:1.1.0
*/
@Service
public class ProjectInfoService {

	@Autowired
	private ProjectInfoDao projectInfoDao;

	/**
	* @MethodName: listProejectInfo 
	* @Description: 工程资料列表查询
	* @param user
	* @param project
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ProjectInfoDto> listProejectInfo(ProjectInfoEntity project,ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select rpi.*,rp.GCMC as PROJECT_NAME from r_project_info rpi ");
		sql.append(" left join r_project rp on rp.ID = rpi.R_PROJECT_ID ");
		sql.append(" where 1=1 ");
		
		if(StringUtils.isNotEmpty(project.getGczlmc())){
			sql.append(" and rpi.GCZLMC like '%").append(project.getGczlmc()).append("%'");
		}

		if(null != project.getId()){
			sql.append(" and rpi.ID = ?");
			params.add(project.getId());
		}
		
		if(null != project.getR_project_id()){
			sql.append(" and rpi.R_PROJECT_ID = ?");
			params.add(project.getR_project_id());
		}

		if(null != project.getStatus()){
			sql.append(" and rpi.STATUS = ?");
			params.add(project.getStatus());
		}
		sql.append(" order by rpi.ID desc");
		return projectInfoDao.listProjectInfo(sql.toString(), params.toArray(), pager);
	}
	
	/**
	* @MethodName: addProjectInfo 
	* @Description: 新增工程资料信息
	* @param project
	* @param multipartRequest
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> addProjectInfo(ProjectInfoEntity project, MultipartHttpServletRequest multipartRequest) throws Exception {
		List<String> messages = ValidateUtil.validate(project);
		if(messages.size() > 0){
			return messages;
		}
		MultipartFile gcFj = multipartRequest.getFile("gczllj");   // 工程资料附件（类型 doc/ppt/pdf/jpg/png/rmvb...）
		
		//保存工程资料附件
		if(null != gcFj && StringUtils.isNotBlank(gcFj.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("project.info.path");
			String pic_name = FileUtils.savePic(gcFj, pic_location, 1024000000);
			if(null != pic_name){
				project.setGczllj(pic_name);
			}else {
				messages.add("工程资料附件必须在 "+1024000000/1024 +"k 以内!");  // 100M
				return messages;
			}
		}
		
		projectInfoDao.addProjectInfo(project);
		
		return null;
	}
	
	/**
	* @MethodName: getProjectInfoById 
	* @Description: 根据Id查询工程资料信息
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ProjectInfoEntity getProjectInfoById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return projectInfoDao.getProjectInfoById(id);
	}
	
	public List<String> modifyProjectInfo(ProjectInfoEntity project,MultipartHttpServletRequest multipartRequest) 
			throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(project);
		if(messages.size() > 0 || null == project.getId()){
			messages.add(null == project.getId() ? "由于，要修改的工程资料记录ID为空，修改失败!" : "");
			return messages;
		}

		MultipartFile pic = multipartRequest.getFile("gczllj");   // 工程资料附件（类型 doc/ppt/pdf/jpg/png/rmvb...）
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("project.info.path");
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, 102400000);
			if(null != pic_name){
				//删除原图片
				FileUtils.clearPic(pic_location, project.getGczllj());
				//更新工程资料图片
				project.setGczllj(pic_name);
			}else {
				messages.add("工程资料图片必须在 "+ 102400000/1024 +"k 以内!");  // 100M
				return messages;
			}
		}
		
		//修改
		projectInfoDao.modifyProjectInfo(project);
		return null;
	}

	/**
	* @MethodName: deleteProjectInfo 
	* @Description: 批量删除工程资料（更改记录的状态为无效(status = -1)）
	* @param ids
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> deleteProjectInfo(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_project_info set STATUS = ").append(DataStatus.DISABLED);
		sql.append(" where ID in ("+ids.trim()+")");
		projectInfoDao.deleteProjectInfo(sql.toString());
		return messages;
	}
	
	
	

	
}
