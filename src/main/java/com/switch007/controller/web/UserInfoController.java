package com.switch007.controller.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.switch007.model.ResultModel;
import com.switch007.model.User;
import com.switch007.model.UserModel;
import com.switch007.service.UserService;
import com.switch007.util.Md5Util;
import com.switch007.util.UUIDUtils;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserService userService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;// 处理字符

	@Autowired
	private RedisTemplate redisTemplate;// 处理对象

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public ResultModel findById(HttpServletRequest request) {
		String id = request.getParameter("id");
		Object ouser = redisTemplate.opsForValue().get("userId_" + id);
		User user = (null != ouser ? (User) ouser : null);
		// 如果redis中不存该条信息
		if (null == ouser) {
			user = userService.selectById(id);
			if (null != user) {// 如果存在于数据库并且不存在与redis则更新redis
				redisTemplate.opsForValue().set("user_" + user.getId(), user);
			}
		}
		return (null == user ? ResultModel.fail("id不存在", id) : ResultModel.success("success", new UserModel(user)));
	}

	@RequestMapping("/save")
	@ResponseBody
	public ResultModel save(User user, HttpServletRequest request) {
		user.setUsername("wang");
		user.setPhone("13047083645");
		user.setPassword(Md5Util.getMd5("123456"));
		String uuid = UUIDUtils.get32UUID();
		user.setId(uuid);
		user.setLastLoginIp(request.getRemoteHost());
		User u = userService.selectByFiled("phone", user.getPhone());
		if (null == u) {
			userService.save(user);
			redisTemplate.opsForValue().set("user_" + uuid, user);
			return ResultModel.success("success", u);
		} else {
			return ResultModel.fail("该帐号已被注册", u.getId());
		}
	}

}
