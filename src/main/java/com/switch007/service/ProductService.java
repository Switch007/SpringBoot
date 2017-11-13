package com.switch007.service;

import java.util.List;
import java.util.Map;

import com.switch007.model.Product;

public interface ProductService {

	public List<Product> list(Map<String, Object> mp);

	public int insert(Product product);

	public int delete(int id);

	public int update(Product product);

	public int batchSave(List<Product> products);

}
