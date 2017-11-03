package com.switch007.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.switch007.model.User;
import com.switch007.service.UserService;

/**
 * Created by lujun.chen on 2017/3/13.
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	
	@RequestMapping("/{id}")
	@ResponseBody
	public User findById(@PathVariable int id) {
		return userService.selectById(id);
	}
	
	@RequestMapping("/toList")
	public String toList(HttpServletRequest request){
		return "/";
		
	}
	
	
	@RequestMapping({ "/userList" })
	@ResponseBody
	public PageInfo<User> userList(HttpServletRequest request, PageInfo<User> pageinfo) {
		String message_type = request.getParameter("message_type");
		String keyword = request.getParameter("keyword");
		Map<String, Object> params = new HashMap();
		if (!StringUtils.isNullOrEmpty(message_type)) {
			params.put("messageType", Integer.valueOf(Integer.parseInt(message_type)));
		}
		if (!StringUtils.isNullOrEmpty(keyword)) {
			params.put("keyword", keyword.trim());
		}
		Page<User> page = PageHelper.startPage(pageinfo.getPageNum(), pageinfo.getPageSize());
		List<User> list = userService.pagelist(params);
		pageinfo.setList(list);
		pageinfo.setTotal(page.getTotal());
		pageinfo.setPages(page.getPages());
		return pageinfo;
	}

}
