package com.switch007.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.switch007.dao.FreeAgentMapper;
import com.switch007.model.FreeAgent;
import com.switch007.service.FreeAgentService;

@Service
public class FreeAgentServiceImpl implements FreeAgentService {

	@Autowired
	private FreeAgentMapper freeAgentMapper;
	
	public int batchSave(List<FreeAgent> list) {
		return freeAgentMapper.batchSave(list);
	}

}
