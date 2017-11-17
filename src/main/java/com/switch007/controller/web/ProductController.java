package com.switch007.controller.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.switch007.model.Product;
import com.switch007.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private RedisTemplate redisTemplate;

	@RequestMapping("/batchSave")
	public String batchSave() {
		List<Product> products = GrapZhe800.dealHtml();
		//确保redis中是最新的最热数据
		redisTemplate.delete("newProducts");
		
		productService.batchSave(products);
		return "product";
	}

	@RequestMapping("/list")
	@ResponseBody
	public List<Product> list(HttpServletRequest request) {
		Map<String, Object> mp = new HashMap<String, Object>();
		PageHelper.startPage(1, 10);
		List<Product> product_list = productService.list(mp);
		Page<Product> p = (Page<Product>) product_list;
		return product_list;
	}

	/**
	 * 取出最新的缓存数据
	 * 
	 * @return
	 */
	@RequestMapping("/hots")
	@ResponseBody
	public List<Product> getHot() {
		List<Product> hotProducts = redisTemplate.opsForList().range("newProducts", 0, 10);
		if (hotProducts.size() <= 0) {
			Map<String, Object> mp = new HashMap<String, Object>();
			PageHelper.startPage(1, 10);
			hotProducts = productService.list(mp);
			redisTemplate.opsForList().leftPush("newProducts", hotProducts);
		}

		return hotProducts;

	}

}
