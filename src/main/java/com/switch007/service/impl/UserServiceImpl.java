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
		return userMapper.selectByMap(params);
	}

	public User selectById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
