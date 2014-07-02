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
import com.rotek.dao.impl.AgentEstimateInfoDao;
import com.rotek.entity.AgentEstimateInfoEntity;
import com.rotek.dto.AgentEstimateInfoDto;

/**
* @ClassName:AgentEstimateInfoService
* @Description:
* @Author liusw
* @date 2014年6月28日 上午11:56:52
* @Version:1.1.0
*/
/**
* @ClassName:AgentEstimateInfoService
* @Description:
* @Author liusw
* @date 2014年6月28日 下午1:14:12
* @Version:1.1.0
*/
@Service
public class AgentEstimateInfoService {
	
	@Autowired
	private AgentEstimateInfoDao agentestimateinfoDao;

	/**
	* @MethodName: listAgentEstimateInfos 
	* @Description:
	* @param agentestimateinfo
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<AgentEstimateInfoDto> listAgentEstimateInfos(AgentEstimateInfoEntity agentestimateinfo, ListPager pager) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT R.ID, R.R_CUSTOMER_ID, R.DLSXJPJ, R.DLSGLXZ, R.STATUS, RC.MC AS CUSTOMER_MC FROM R_AGENTESTIMATEINFO R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append("  WHERE R.STATUS = 1 ");

		List<Object> params = new ArrayList<Object>();
		if(null != agentestimateinfo.getId()){
			sql.append(" and r.id = ?");
			params.add(agentestimateinfo.getId());
		}

		if(null != agentestimateinfo.getDlsxjpj()){
			sql.append(" and r.dlsxjpj = ?");
			params.add(agentestimateinfo.getDlsxjpj());
		}
		
		if(null != agentestimateinfo.getStatus()){
			sql.append(" and r.status = ?");
			params.add(agentestimateinfo.getStatus());
		}

		sql.append(" order by r.status,r.id asc");
		List<AgentEstimateInfoDto> agentestimateinfos = agentestimateinfoDao.listAgentEstimateInfos(sql.toString(),params.toArray(),pager);
		return agentestimateinfos;
	}
	
	/**
	* @MethodName: addAgentEstimateInfo 
	* @Description:
	* @param agentestimateinfo
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @author liusw
	*/
	public List<String> addAgentEstimateInfo(AgentEstimateInfoEntity agentestimateinfo) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		agentestimateinfoDao.addAgentEstimateInfo(agentestimateinfo);
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
		return agentestimateinfoDao.listCustomers();
	}	

	/**
	* @MethodName: getAgentEstimateInfoDetail 
	* @Description:
	* @param id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public AgentEstimateInfoDto getAgentEstimateInfoDetail(Integer id) throws SQLException{
		AgentEstimateInfoDto agentestimateinfodto = agentestimateinfoDao.getAgentEstimateInfoDetail(id);
		return agentestimateinfodto;
	}
	
	/**
	* @MethodName: modifyAgentEstimateInfo 
	* @Description:
	* @param agentestimateinfo
	* @return
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws SQLException
	* @author liusw
	*/
	public List<String> modifyAgentEstimateInfo(AgentEstimateInfoEntity agentestimateinfo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(agentestimateinfo);
		if(messages.size()>0 || null == agentestimateinfo.getId()){
			return messages;
		}
		agentestimateinfoDao.modifyAgentEstimateInfo(agentestimateinfo);

		return null;
	}
	
	public List<String> deleteComplaintInfo(String id_str) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(id_str)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update R_AgentEstimateInfo set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+id_str.trim()+")");
		agentestimateinfoDao.deleteAgentEstimateInfo(sql.toString());
		return messages;
	}
}
