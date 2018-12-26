package org.jiira.chapter15.controller;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import org.jiira.chapter15.pojo.PageParams;
import org.jiira.chapter15.pojo.Role;
import org.jiira.chapter15.pojo.RoleParams;
import org.jiira.chapter15.service.RoleService;
import org.jiira.chapter15.view.ExcelExportService;
import org.jiira.chapter15.view.ExcelView;


@Controller
@RequestMapping("/role")
public class RoleController {
	
	
	@Autowired
	private RoleService roleService = null; 
	
	/**
	 * 将参数转化为json视图
	 * @param id
	 * @param roleName
	 * @param note
	 * @return
	 */
	@RequestMapping("/showRoleJsonInfo")
	public ModelAndView showRoleJsonInfo(Long id, String roleName, String note) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		mv.addObject("id", id);
		mv.addObject("roleName", roleName);
		mv.addObject("note", note);
		return mv;
	}
	/**
	 * 插入信息并利用Model数据模型重定向时传入参数
	 * @param model
	 * @param roleName
	 * @param note
	 * @return
	 */
	@RequestMapping("/addRole")
	//Model为重定向数据模型，Spring MVC会自动初始化它
	public String addRole(Model model, String roleName, String note) {
		Role role = new Role();
		role.setRoleName(roleName);
		role.setNote(note);
		//插入角色后，会回填角色编号
		roleService.insertRole(role);
		//绑定重定向数据模型
		model.addAttribute("roleName", roleName);
		model.addAttribute("note", note);
		model.addAttribute("id", role.getId());
		return "redirect:./showRoleJsonInfo.do";
	}
	/**
	 * 插入信息并利用ModelAndView模型重定向
	 * @param mv
	 * @param roleName
	 * @param note
	 * @return
	 */
	@RequestMapping("/addRole2")
	//ModelAndView对象Spring MVC会自定初始化它
	public ModelAndView addRole2(ModelAndView mv, String roleName, String note) {
		Role role = new Role();
		role.setRoleName(roleName);
		role.setNote(note);
		//插入角色后，会回填角色编号
		roleService.insertRole(role);
		//绑定重定向数据模型
		mv.addObject("roleName", roleName);
		mv.addObject("note", note);
		mv.addObject("id", role.getId());
		mv.setViewName("redirect:./showRoleJsonInfo.do");
		return mv;
	}
	
	/**
	 * 将pojo对象转化为json视图
	 * @param role
	 * @return
	 */
	@RequestMapping("/showRoleJsonInfo2")
	public ModelAndView showRoleJsonInfo(Role role) {
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		mv.addObject("role", role);
		return mv;
	}
	
	/**
	 * 利用RedirectAttributes模型重定向时传递对象参数
	 * @param ra
	 * @param role
	 * @return
	 */
	@RequestMapping("/addRole3")
	//RedirectAttribute对象Spring MVC会自动初始化它
	public String addRole3(RedirectAttributes ra, Role role) {
		//插入角色后，会回填角色编号
		roleService.insertRole(role);
		//绑定重定向数据模型
		ra.addFlashAttribute("role", role);
		return "redirect:./showRoleJsonInfo2.do";
	}
	
	@RequestMapping("/getRole")
	public ModelAndView getRole(Long id) {
		Role role = roleService.getRole(id);
		ModelAndView mv = new ModelAndView();
		mv.setView(new MappingJackson2JsonView());
		mv.addObject("role", role);
		System.err.println("name = " + role.getRoleName());
		return mv;
	}
	
	/**
	 * 
	 * modelMap绑定了数据
	 * 但是没有把模型数据绑定给视图和模型（ModelAndView）
	 * 但是不用担心，spring会在完成控制逻辑后自动绑定。
	 * 
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getRoleByModelMap", method = RequestMethod.GET)
	public ModelAndView getRoleByModelMap(@RequestParam("id") Long id, ModelMap modelMap) {
	    Role role = roleService.getRole(id);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("roleDetails");
	    modelMap.addAttribute("role", role);
	    return mv;
	}
	/**
	 * Model和ModelMap都实现了BindingAwareModelMap，他们之间一部分数据可以相互转换
	 * 关于模型数据和ModelAndView绑定，同getRoleByModelMap
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getRoleByModel", method = RequestMethod.GET)
	public ModelAndView getRoleByModel(@RequestParam("id") Long id, Model model) {
	    Role role = roleService.getRole(id);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("roleDetails");
	    model.addAttribute("role", role);
	    return mv;
	}

	@RequestMapping(value = "/getRoleByMv", method = RequestMethod.GET)
	public ModelAndView getRoleByMv(@RequestParam("id") Long id, ModelAndView mv) {
	    Role role = roleService.getRole(id);
	    mv.setViewName("roleDetails");
	    mv.addObject("role", role);
	    return mv;
	}
	
	
	@RequestMapping(value = "/getRoleForJson", method = RequestMethod.GET)
	public ModelAndView getRoleForJson(@RequestParam("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Role role = roleService.getRole(id);
		mv.setView(new MappingJackson2JsonView());
		mv.addObject("role", role);
		return mv;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@RequestParam("id") Long id, ModelMap model) {
	    Role role = roleService.getRole(id);
	    model.addAttribute("role", role);
	    return "roleDetails";
	}
	/**
	 * 导出excel视图
	 * 直接下载为excel文件
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public ModelAndView export() {
		//模型和视图
		ModelAndView mv = new ModelAndView();
		//Excel视图，并设置自定义导出接口
		ExcelView ev = new ExcelView(exportService());	
		//文件名
		ev.setFileName("所有角色.xlsx");
		//设置SQL后台参数
		RoleParams roleParams = new RoleParams();
		//限制1万条
		PageParams page = new PageParams();
		page.setStart(0);
		page.setLimit(10000);
		roleParams.setPageParams(page);
		//查询
		List<Role> roleList = roleService.findRoles(roleParams); 
		//加入数据模型
		mv.addObject("roleList", roleList);
		mv.setView(ev);
		return mv;
	}

	@SuppressWarnings({ "unchecked"})
	private ExcelExportService exportService() {
		//使用Lambda表达式自定义导出excel规则
		return (Map<String, Object> model, Workbook workbook) -> {
			//获取用户列表
			List<Role> roleList = (List<Role>) model.get("roleList");
			//生成Sheet
			Sheet sheet= workbook.createSheet("所有角色");
			//加载标题
			Row title = sheet.createRow(0);
			title.createCell(0).setCellValue("编号");
			title.createCell(1).setCellValue("名称");
			title.createCell(2).setCellValue("备注");
			//便利角色列表，生成一行行的数据
			for (int i=0; i<roleList.size(); i++) {
				Role role = roleList.get(i);
				int rowIdx = i + 1;
				Row row = sheet.createRow(rowIdx);
				row.createCell(0).setCellValue(role.getId());
				row.createCell(1).setCellValue(role.getRoleName());
				row.createCell(2).setCellValue(role.getNote());
			}
	    };
	}
}
