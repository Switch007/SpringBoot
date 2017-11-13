package com.switch007.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.switch007.dao.ProductMapper;
import com.switch007.model.Product;
import com.switch007.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> list(Map<String, Object> mp) {
		return productMapper.selectByMap(mp);
	}

	public int insert(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int batchSave(List<Product> products) {
		return productMapper.batchSave(products);
	}

}
