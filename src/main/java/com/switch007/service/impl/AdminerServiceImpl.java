package com.switch007.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.switch007.dao.AdminerMapper;
import com.switch007.model.Adminer;
import com.switch007.service.AdminerService;

@Service
public class AdminerServiceImpl implements AdminerService {
	@Autowired
	private AdminerMapper adminerMapper;

	public Adminer login(Map<String, Object> mp) {
		Adminer admin = adminerMapper.selectByFileds(mp);
		return admin;
	}

}
