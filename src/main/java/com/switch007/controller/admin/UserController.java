package com.switch007.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;// 处理字符

	@Autowired
	private RedisTemplate redisTemplate;// 处理对象

	@Autowired
	Configuration config;

	@RequestMapping("/getUser")
	@ResponseBody
	public User findById(HttpServletRequest request) {
		int id = StringUtils.isNullOrEmpty(request.getParameter("id")) ? 0 : Integer.parseInt(request.getParameter("id"));
		Object s = redisTemplate.opsForValue().get("userId_" + id);
		User u = userService.selectById(id);
		if (null == s) {
			redisTemplate.opsForValue().set("userId_" + id, u);
		}

		return u;
	}

	@RequestMapping("/toList")
	public String toList(Map<String, Object> map,HttpServletRequest request) {
		/*
		 * User s = (User) redisTemplate.opsForValue().get("userId_" + 1);
		 * System.out.println(s.getUname());
		 */
		String filepath = request.getServletContext().getRealPath("/") +"WEB-INF/admin/"+ "user_list.html";
		File htmlFile = new File(filepath);
		if (!htmlFile.exists()) {
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			Writer out = null;
			try {
				Template temple = config.getTemplate("user_list.ftl");// 获取模板
				out = new OutputStreamWriter(new FileOutputStream(filepath), "utf-8");// 生成最终页面并写到文件
				temple.process(map, out);// 处理
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "/user_list";

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
