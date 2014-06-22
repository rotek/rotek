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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.CustomerDocDao;
import com.rotek.dto.CustomerDocDto;
import com.rotek.entity.CustomerDocEntity;
import com.rotek.util.FileUtils;

/**
* @ClassName:CustomerDocService
* @Description:
* @Author liusw
* @date 2014年6月22日 上午10:21:17
* @Version:1.1.0
*/
@Service
public class CustomerDocService {
	
	@Autowired
	private CustomerDocDao customerDocDao;
	
	/**
	* @MethodName: listCustomerDocs 
	* @Description:
	* @param customerdoc
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<CustomerDocDto> listCustomerDocs(CustomerDocEntity customerdoc, ListPager pager) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT R.ID, R.R_CUSTOMER_ID, R.KHZLLB, R.KHZLFJ, R.KHZLMC, R.DLSZJYXQ, RC.MC AS SUPER_MC, R.STATUS FROM R_CUSTOMERDOCINFO R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append("  WHERE R.STATUS = 1 ");

		List<Object> params = new ArrayList<Object>();
		if(null != customerdoc.getId()){
			sql.append(" and r.id = ?");
			params.add(customerdoc.getId());
		}
		if(null != customerdoc.getKhzllb()){
			sql.append(" and r.khzllb = ?");
			params.add(customerdoc.getKhzllb());
		}
		if(StringUtils.isNotEmpty(customerdoc.getKhzlmc())){
			sql.append(" and r.khzlmc like '%"+customerdoc.getKhzlmc().trim()+"%'");
		}
		if(null != customerdoc.getStatus()){
			sql.append(" and r.status = ?");
			params.add(customerdoc.getStatus());
		}

		sql.append(" order by r.status,r.id asc");
		List<CustomerDocDto> customerdocs = customerDocDao.listCustomerDocs(sql.toString(),params.toArray(),pager);
		return customerdocs;
	}

	/**
	* @MethodName: addCustomerDoc 
	* @Description:
	* @param customerdocEntity
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @author liusw
	*/
	public List<String> addCustomerDoc(CustomerDocEntity customerdocEntity, MultipartHttpServletRequest multipartRequest) throws Exception{
//		List<String> messages = ValidateUtil.validate(customerdocEntity);
//		if(messages.size()>0){
//			return messages;
//		}
		
		MultipartFile KHZLFJ = multipartRequest.getFile("khzlfj");   // 客户资料附件
		
		//保存附件
		if(null != KHZLFJ && StringUtils.isNotBlank(KHZLFJ.getOriginalFilename())){
			String file_location = SystemGlobals.getPreference("project.jscsfj.path");
			String file_name = FileUtils.savePic(KHZLFJ, file_location, 1024000000);
			if(null != file_name){
				customerdocEntity.setKhzlfj(file_name);
			}
		}		
		customerDocDao.addCustomerDoc(customerdocEntity);
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
		return customerDocDao.listCustomers();
}
	
	/**
	* @MethodName: getCustomerDocDetail 
	* @Description:
	* @param id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public CustomerDocDto getCustomerDocDetail(Integer id) throws SQLException{

		CustomerDocDto customerdocdto = customerDocDao.getCustomerDocDetail(id);
		return customerdocdto;
	}
	
	/**
	* @MethodName: modifyCustomerDoc 
	* @Description:
	* @param customerdocEntity
	* @return
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws SQLException
	* @author liusw
	*/
	public List<String> modifyCustomerDoc(CustomerDocEntity customerdocEntity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(customerdocEntity);
		if(messages.size()>0 || null == customerdocEntity.getId()){
			return messages;
		}
		//修改角色基本信息
		customerDocDao.modifyCustomerDoc(customerdocEntity);

		return null;
	}
	
	/**
	* @MethodName: deleteCustomerDoc
	* @Description:
	* @param id_str
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<String> deleteCustomerDoc(String id_str) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(id_str)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_customerdocinfo set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+id_str.trim()+")");
		customerDocDao.deleteCustomerDoc(sql.toString());
		return messages;
	}

}
