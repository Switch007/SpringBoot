package com.switch007.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.switch007.dao.UserMapper;
import com.switch007.model.User;
import com.switch007.service.UserService;

/**
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	public int save(User user) {
		return userMapper.insert(user);
	}

	public List<User> pagelist(Map<String, Object> params) {
		List<User> re=userMapper.selectByMap(params);
		return re;
	}

	public User selectById(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
