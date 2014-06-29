package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.UnProcessInfoDao;
import com.rotek.dto.UnProcessInfoDto;
import com.rotek.entity.UnProcessInfoEntity;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:UnProcessInfoService
* @Description:
* @Author liusw
* @date 2014年6月28日 上午11:56:45
* @Version:1.1.0
*/
@Service
public class UnProcessInfoService {
	
	@Autowired
	private UnProcessInfoDao unprocessinfoDao;

	/**
	* @MethodName: listUnProcessInfos 
	* @Description:
	* @param unprocessinfo
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<UnProcessInfoDto> listUnProcessInfos(UnProcessInfoEntity unprocessinfo, ListPager pager) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct R.ID, R.R_CUSTOMER_ID, R.R_PROJECT_ID, R.ERRORINFOLB, R.SPECIFIC_PART, R.SPECIFIC_BH, R.JLRQ, R.ISDEALED, R.STATUS, RC.MC AS CUSTOMER_MC, RP.GCMC AS PROJECT_MC FROM R_ProcessInfo R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append(" LEFT JOIN R_PROJECT RP ON RP.ID = R.R_PROJECT_ID");
		sql.append("  WHERE R.STATUS = 1 ");

		List<Object> params = new ArrayList<Object>();
		if(null != unprocessinfo.getId()){
			sql.append(" and r.id = ?");
			params.add(unprocessinfo.getId());
		}

		if(StringUtils.isNotEmpty(unprocessinfo.getSpecific_bh())){
			sql.append(" and r.specific_bh like '%"+unprocessinfo.getSpecific_bh().trim()+"%'");
		}
		
		if(StringUtils.isNotEmpty(unprocessinfo.getSpecific_part())){
			sql.append(" and r.specific_part like '%"+unprocessinfo.getSpecific_part().trim()+"%'");
		}

		if(null != unprocessinfo.getStatus()){
			sql.append(" and r.status = ?");
			params.add(unprocessinfo.getStatus());
		}

		sql.append(" order by r.status,r.id asc");
		List<UnProcessInfoDto> unprocessinfos = unprocessinfoDao.listUnProcessInfos(sql.toString(),params.toArray(),pager);
		return unprocessinfos;
	}
	
	/**
	* @MethodName: addUnProcessInfo 
	* @Description:
	* @param unprocessInfo
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @author liusw
	*/
	public List<String> addUnProcessInfo(UnProcessInfoEntity unprocessInfo) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		unprocessinfoDao.addUnProcessInfo(unprocessInfo);
		return null;
	}
	
	/**
	* @MethodName: listCustomers 
	* @Description:
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<Map<String, Object>> listCustomers() throws SQLException {
		return unprocessinfoDao.listCustomers();
	}
	
	/**
	* @MethodName: listProjects 
	* @Description:
	* @param r_customer_id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public ProjectEntity listProjects(Integer r_customer_id) throws SQLException {		
		return unprocessinfoDao.listProjects(r_customer_id);
	}
	
	/**
	* @MethodName: getUnProcessInfoDetail 
	* @Description:
	* @param id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public UnProcessInfoDto getUnProcessInfoDetail(Integer id) throws SQLException{
		UnProcessInfoDto unprocessinfodto = unprocessinfoDao.getUnProcessInfoDetail(id);
		return unprocessinfodto;
	}
	
	/**
	* @MethodName: modifyUnProcessInfo 
	* @Description:
	* @param complaintinfo
	* @return
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws SQLException
	* @author liusw
	*/
	public List<String> modifyUnProcessInfo(UnProcessInfoEntity complaintinfo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(complaintinfo);
		if(messages.size()>0 || null == complaintinfo.getId()){
			return messages;
		}
		unprocessinfoDao.modifyUnProcessInfo(complaintinfo);

		return null;
	}
	
	/**
	* @MethodName: deleteUnProcessInfo 
	* @Description:
	* @param id_str
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<String> deleteUnProcessInfo(String id_str) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(id_str)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update R_ProcessInfo set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+id_str.trim()+")");
		unprocessinfoDao.deleteUnProcessInfo(sql.toString());
		return messages;
	}
}
