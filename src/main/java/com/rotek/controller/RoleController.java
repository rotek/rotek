/**
* @FileName: RoleController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-3 上午08:58:16
* @version V1.0
*/
package com.rotek.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cta.platform.util.ListPager;
import com.rotek.dto.UserDto;
import com.rotek.entity.NodeEntity;
import com.rotek.entity.RoleEntity;
import com.rotek.service.impl.RoleService;

/**
 * @ClassName: RoleController
 * @Description: 角色信息设置
 * @author chenwenpeng
 * @date 2013-6-3 上午08:58:16
 *
 */
@Controller
@RequestMapping(value="/admin/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	/**
	 * 转到查看所有角色的界面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("toRoles")
	public String toRoles() {

		return "admin/role/roles";
	}

	/**
	 * 列出所有的角色信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listRoles")
	public String listRoles(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			RoleEntity role,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<RoleEntity> roleList = roleService.listRoles(role,pager);
		modelMap.put("dataList", roleList);
		modelMap.put("totalCount", pager.getTotalRows());

		return "jsonView";
	}

	/**
	 * 列出所有的角色信息（id name）
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listRoles_combo")
	public String listRoles_combo(ModelMap modelMap) throws Exception {

		List<Map<String,Object>> roleList = roleService.listRoles_combo();
		modelMap.put("dataList", roleList);
		return "jsonView";
	}

	/**添加一个角色信息
	 * @param roleEntity
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("addRole")
	public String addRole(
			@RequestParam(value="role_name", defaultValue="") String name,
			@RequestParam(value="status", defaultValue="1") Integer status,
			@RequestParam(value="super_id", defaultValue="1") Integer super_id,
			@RequestParam(value="memo", defaultValue="") String memo,
			ModelMap model) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		RoleEntity roleEntity = new RoleEntity();

		roleEntity.setName(name);
		roleEntity.setStatus(status);

		List<String> messages = roleService.addRole(roleEntity);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**加载一个角色对应的详细信息
	 * @param recordId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getRoleDetail")
	public String loadRoleDetails(
			@RequestParam(value="id",defaultValue="0") Integer id,
			ModelMap model) throws Exception{

		RoleEntity role = roleService.getRoleDetail(id);
		model.put("data", role);
		return "jsonView";
	}

	/**根据上级菜单返回子级菜单
	 * @param roleId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listAuthority")
	public @ResponseBody List<NodeEntity> listAuthority(
			@RequestParam(value="roleId",defaultValue="") Integer roleId,
			@RequestParam(value="node",defaultValue="") String nodeId_str,
			ModelMap model) throws Exception{
		List<NodeEntity> roleList = roleService.listAuthority(roleId,nodeId_str);
		model.put("dataList", roleList);
		return roleList;
	}

	/**修改权限信息
	 * @param roleId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyRole")
	public String modifyRole(
			@RequestParam(value="id",defaultValue="0") Integer id,
			@RequestParam(value="role_name", defaultValue="") String role_name,
			@RequestParam(value="status", defaultValue="1") Integer status,
			@RequestParam(value="super_role_id", defaultValue="1") Integer super_role_id,
			@RequestParam(value="memo", defaultValue="") String memo,
			@RequestParam(value="authority",defaultValue="") String authority,
			ModelMap model) throws Exception{
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setId(id);
		roleEntity.setName(role_name);
		roleEntity.setStatus(status);
		List<String> messages = roleService.modifyRole(roleEntity,authority);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**删除一个或多个角色信息
	 * @param roleEntity
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("deleteRole")
	public String addRole(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = roleService.deleteRole(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
}
