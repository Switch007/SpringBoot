package com.switch007.service;

import java.util.List;
import java.util.Map;

import com.switch007.model.User;

public interface UserService {
	public int save(User user);

	public User selectById(String id);

	public List<User> pagelist(Map<String, Object> params);

	public User selectByFiled(String filed, String phone);
}
