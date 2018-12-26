package org.jiira.chapter14.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//注解@Controller表示它是一个控制器
@Controller("myController2")
//表明当请求的URI在/my下的时候才有该控制器响应
@RequestMapping("/my2")
public class MyController2 {
	//表明URI是/index的时候该方法才请求
	@RequestMapping("/index")
	public ModelAndView index() {
        //模型和视图
		ModelAndView mv = new ModelAndView();
		//视图逻辑名称为index
		mv.setViewName("index");
        //返回模型和视图
		return mv;
	}

	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public ModelAndView index2() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping(value = "/index3", method=RequestMethod.GET)
	public ModelAndView index2(HttpSession session, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();  
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping(value = "/index4", method=RequestMethod.GET)
	public ModelAndView index2(@RequestParam("id") Long id) {
		System.out.println("params[id] = " + id);
		ModelAndView mv = new ModelAndView();
	    mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping(value = "/index5", method=RequestMethod.GET)
	public ModelAndView index2(@SessionAttribute("userName") String userName) {
		System.out.println("session[userName] = " + userName);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}