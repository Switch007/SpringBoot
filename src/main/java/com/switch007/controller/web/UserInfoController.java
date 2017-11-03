package com.switch007.controller.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.switch007.model.User;
import com.switch007.service.UserService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserService userService;
	@Autowired
	Configuration config;

	@RequestMapping("/getInfo")
	@ResponseBody
	public User findById(HttpServletRequest request) {
		String id = request.getParameter("id");
		User u = StringUtils.isNullOrEmpty(id) ? null : userService.selectById(Integer.parseInt(id));
		return u;
	}

	@RequestMapping("/toList")
	public String toList(HttpServletRequest request) {
		return "/";

	}

	@RequestMapping("/hello")
	public String hello(Map<String, Object> map, HttpServletRequest request) {
		map.put("name", "[Angel -- 守护天使]");
		List<String> names = new ArrayList<String>();
		names.add("wang");
		names.add("ni");
		names.add("ma");
		map.put("list", names);
		map.put("testtime", new Date());
		String filepath=request.getServletContext().getRealPath("/")+"hello.html";
		File htmlFile = new File(filepath);
		if (!htmlFile.exists()) {
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			Writer out = null;
			try {
				Template temple = config.getTemplate("hello.ftl");// 获取模板
				out = new OutputStreamWriter(new FileOutputStream(filepath),"utf-8");// 生成最终页面并写到文件
				temple.process(map, out);// 处理
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "hello";
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
