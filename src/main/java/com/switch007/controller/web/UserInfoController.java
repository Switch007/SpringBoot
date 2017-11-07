package com.switch007.controller.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.switch007.model.User;
import com.switch007.service.UserService;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserService userService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;// 处理字符

	@Autowired
	private RedisTemplate redisTemplate;// 处理对象

	@RequestMapping("/getInfo")
	@ResponseBody
	public User findById(HttpServletRequest request) {
		String id = request.getParameter("id");
		User u = StringUtils.isNullOrEmpty(id) ? null : userService.selectById(Integer.parseInt(id));
		Object s = redisTemplate.opsForValue().get("userId_" + id);
		if (null == s) {
			redisTemplate.opsForValue().set("userId_" + id, u);
		}
		return u;
	}

	@RequestMapping("/toList")
	public String toList(HttpServletRequest request) {
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
