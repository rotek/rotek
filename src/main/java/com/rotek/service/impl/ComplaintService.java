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
import com.rotek.dao.impl.ComplaintInfoDao;
import com.rotek.dto.ComplaintInfoDto;
import com.rotek.entity.ComplaintInfoEntity;

/**
* @ClassName:ComplaintService
* @Description:
* @Author liusw
* @date 2014年6月28日 上午11:56:39
* @Version:1.1.0
*/
@Service
public class ComplaintService {
	
	@Autowired
	private ComplaintInfoDao complaintinfoDao;

	/**
	* @MethodName: listComplaintInfos 
	* @Description:
	* @param complaintinfo
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<ComplaintInfoDto> listComplaintInfos(ComplaintInfoEntity complaintinfo, ListPager pager) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT R.ID, R.R_CUSTOMER_ID, R.TSDW, R.TSSX, R.TSSJ, R.STATUS, RC.MC AS CUSTOMER_MC FROM R_ComplaintInfo R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append("  WHERE R.STATUS = 1 ");

		List<Object> params = new ArrayList<Object>();
		if(null != complaintinfo.getId()){
			sql.append(" and r.id = ?");
			params.add(complaintinfo.getId());
		}

		if(StringUtils.isNotEmpty(complaintinfo.getTsdw())){
			sql.append(" and r.tsdw like '%"+complaintinfo.getTsdw().trim()+"%'");
		}

		if(StringUtils.isNotEmpty(complaintinfo.getTssx())){
			sql.append(" and r.tssx like '%"+complaintinfo.getTssx().trim()+"%'");
		}

		if(null != complaintinfo.getStatus()){
			sql.append(" and r.status = ?");
			params.add(complaintinfo.getStatus());
		}

		sql.append(" order by r.status,r.id asc");
		List<ComplaintInfoDto> complaintinfos = complaintinfoDao.listComplaintInfos(sql.toString(), params.toArray(), pager);
		return complaintinfos;
	}
	
	/**
	* @MethodName: addComplaintInfo 
	* @Description:
	* @param complaintinfoEntity
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @author liusw
	*/
	public List<String> addComplaintInfo(ComplaintInfoEntity complaintinfoEntity) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		complaintinfoDao.addComplaintInfo(complaintinfoEntity);
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
		return complaintinfoDao.listCustomers();
	}
	
	/**
	* @MethodName: getComplaintInfoDetail 
	* @Description:
	* @param id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public ComplaintInfoDto getComplaintInfoDetail(Integer id) throws SQLException{
		ComplaintInfoDto complaintinfodto = complaintinfoDao.getComplaintInfoDetail(id);
		return complaintinfodto;
	}
	
	/**
	* @MethodName: modifyComplaintInfo 
	* @Description:
	* @param complaintinfoEntity
	* @return
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws SQLException
	* @author liusw
	*/
	public List<String> modifyComplaintInfo(ComplaintInfoEntity complaintinfoEntity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(complaintinfoEntity);
		if(messages.size()>0 || null == complaintinfoEntity.getId()){
			return messages;
		}
		complaintinfoDao.modifyComplaintInfo(complaintinfoEntity);

		return null;
	}
	
	/**
	* @MethodName: deleteComplaintInfo 
	* @Description:
	* @param id_str
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<String> deleteComplaintInfo(String id_str) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(id_str)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update R_ComplaintInfo set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+id_str.trim()+")");
		complaintinfoDao.deleteComplaintInfo(sql.toString());
		return messages;
	}
	
}
