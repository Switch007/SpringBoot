package com.switch007.controller.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import com.switch007.model.Product;

public class GrapZhe800 {

	public static void grap() {
		CloseableHttpClient chc = HttpClients.createDefault();
		HttpGet hg = null;
		for (int i = 1; i <= 4; i++) {
			hg = new HttpGet("http://www.youpingou.com/index/cate/sort/ershi/p/" + i + "");
			try {
				CloseableHttpResponse response1 = chc.execute(hg);
				HttpEntity entity1 = response1.getEntity();
				String content = EntityUtils.toString(entity1, "utf-8");

				String fileName = "d://youpin_" + i + ".txt";
				File f = new File(fileName);
				FileOutputStream fStream = new FileOutputStream(f);
				fStream.write(content.getBytes());
				fStream.flush();
				fStream.close();

				EntityUtils.consume(entity1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static List<Product> dealHtml() {
		List<Product> product_list = new ArrayList<Product>();
		for (int i = 0; i < 4; i++) {
			String fileName = "d://youpin_" + i + ".txt";
			File f = new File(fileName);
			if (f.exists()) {
				try {
					Document dc = Jsoup.parse(f, "utf-8");
					Elements eles = dc.select(".list-good");
					for (Element ele : eles) {
						System.err.println("============================");
						String url = ele.select("div.good-pic > a").attr("href");
						String pic = ele.select("div.good-pic img").attr("data-original");
						String title = ele.select("div.good-pic  img").attr("title");
						String price = ele.select("div.good-price  h1").text();
						Product product = new Product();
						product.setHref(url);
						product.setPic(pic);
						product.setTitle(title);
						product.setPrice(price);
						product.setCreateTime(new Date());
						product.setStatus("0");
						product_list.add(product);
						System.out.println(url + "**" + pic + "***" + title + "@@@" + price);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return product_list;
	}

	public static void main(String[] ss) {
		grap();
		// dealHtml();
	}

}
