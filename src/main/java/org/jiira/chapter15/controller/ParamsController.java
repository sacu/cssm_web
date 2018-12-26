package org.jiira.chapter15.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import org.jiira.chapter15.pojo.Role;
import org.jiira.chapter15.pojo.RoleParams;
import org.jiira.chapter15.service.RoleService;

@Controller
@RequestMapping("/params")
public class ParamsController {
	/**
	 * 可用来测试拦截器，地址：
	 * SSM_WEB/role/getRole.do?id="asd"
	 * 如果id=int则成功，如果id=string则失败
	 * 使用拦截器RoleInterceptor
	 * 先执行preHandle
	 * 
	 * 根据结果，如果成功则执行postHandle
	 * 如果失败则抛出异常
	 * 
	 * 必然执行afterCompletion
	 * 
	 * 多个拦截器根据配置文件顺序执行
	 * 当拦截器的任意拦截切点返回false，接下来的拦截器和实际调用函数就都不会运行了
	 * @param id
	 * @return
	 */
	@RequestMapping("/commonParams")
	public ModelAndView commonParams(String roleName, String note) {
	    System.out.println("roleName =>" + roleName);
	    System.out.println("note =>" + note);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("index");
	    return mv;
	}
	
	/**
	 * pojo传参
	 * @param roleParams
	 * @return
	 */
	@RequestMapping("/commonParamPojo")
	public ModelAndView commonParamPojo(RoleParams roleParams) {
	    System.out.println("roleName =>" + roleParams.getRoleName());
	    System.out.println("note =>" + roleParams.getNote());
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("index");
	    return mv;
	}
	
	/**
	 * 网页与地址映射名称的参数绑定
	 * @param roleName
	 * @param note
	 * @return
	 */
	@RequestMapping("/requestParam")
	//使用@RequestParam("role_name")指定映射HTTP参数名称
	public ModelAndView requestParam(@RequestParam("role_name") String roleName, String note) {
		System.out.println("roleName =>" + roleName);
	    System.out.println("note =>" + note);
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("index");
	    return mv;
	}
	
	
	//注入角色服务对象
	@Autowired
	RoleService roleService;

	/**
	 * URL传参
	 * @param id
	 * @return
	 */
	//{id}代表接收一个参数
	@RequestMapping("/getRole/{id}")
	//注解@PathVariable表示从URL的请求地址中获取参数
	public ModelAndView pathVariable(@PathVariable("id") Long id)  {
		Role role = roleService.getRole(id);
		ModelAndView mv = new ModelAndView();
		//绑定数据模型
		mv.addObject(role);
		//设置为JSON视图
		mv.setView(new MappingJackson2JsonView());
		return mv;
	}
	
	/**
	 * 通过$(document).ready(function() {})提交传参
	 * 进行查询操作
	 * @param roleParams
	 * @return
	 */
	@RequestMapping("/findRoles")
	public ModelAndView findRoles(@RequestBody RoleParams roleParams) {
		System.out.println("调用了findRoles note =>" + roleParams.getNote());
		List<Role> roleList = roleService.findRoles(roleParams);
		ModelAndView mv = new ModelAndView();
		//绑定模型
		mv.addObject(roleList);
		//设置为JSON视图
		mv.setView(new MappingJackson2JsonView());
		return mv;
	}
	/**
	 * 通过$(document).ready(function() {})提交传参
	 * 进行删除操作
	 * @param idList
	 * @return
	 */
	@RequestMapping("/deleteRoles")
	public ModelAndView deleteRoles(@RequestBody List<Long> idList) {
		System.out.println("调用了deleteRoles length =>" + idList.size());
		ModelAndView mv = new ModelAndView();
		//删除角色
		int total = roleService.deleteRoles(idList);
		//绑定视图
		mv.addObject("total", total);
		//JSON视图
	    mv.setView(new MappingJackson2JsonView());
		return mv;
	}
	/**
	 * 通过$(document).ready(function() {})提交传参
	 * 进行添加操作
	 * @param roleList
	 * @return
	 */
	@RequestMapping("/addRoles")
	public ModelAndView addRoles(@RequestBody List<Role> roleList) {
		System.out.println("调用了addRoles length =>" + roleList.size());
		ModelAndView mv = new ModelAndView();
		//删除角色
		int total = roleService.insertRoles(roleList);
		//绑定视图
		mv.addObject("total", total);
		//JSON视图
		mv.setView(new MappingJackson2JsonView());
		return mv;
	}
}
