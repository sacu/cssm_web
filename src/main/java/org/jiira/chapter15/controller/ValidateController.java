package org.jiira.chapter15.controller;

import java.util.List;

import javax.validation.Valid;

import org.jiira.chapter15.pojo.MyTransaction;
import org.jiira.chapter15.pojo.Transaction;
import org.jiira.chapter15.validator.TransactionValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 关于注解拦截器（Transaction）无效问题：
 * 	因为这里只是用了 javax 的 validation-api包，
 * 	该包只提供了验证器的注解，并没有实现其功能！！！所以这里不做测试了
 * 
 * 这里只做一下基于Validator接口的TransactionValidator类验证器 
 * 通过initBinder函数绑定，具体查看TransactionValidator类
 * 
 * @author time
 *
 */
@Controller
@RequestMapping("/validate")
public class ValidateController {
	
	@RequestMapping("/annotation")
	public ModelAndView annotationValidate(@Valid MyTransaction trans, Errors errors) {
		// 是否存在错误
		if (errors.hasErrors()) {
			// 获取错误信息
			List<FieldError> errorList = errors.getFieldErrors();
			for (FieldError error : errorList) {
				// 打印字段错误信息
				System.err.println("fied :" + error.getField() + "\t" + "msg:" + error.getDefaultMessage());
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@InitBinder
	public void initBinder(DataBinder binder) {
		// 数据绑定器加入验证器
		binder.setValidator(new TransactionValidator());
	}

	@RequestMapping("/validator")
	public ModelAndView validator(@Valid MyTransaction trans, Errors errors) {
		// 是否存在错误
		if (errors.hasErrors()) {
			// 获取错误信息
			List<FieldError> errorList = errors.getFieldErrors();
			for (FieldError error : errorList) {
				// 打印字段错误信息
				System.err.println("fied :" + error.getField() + "\t" + "msg:" + error.getDefaultMessage());
			}
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}
