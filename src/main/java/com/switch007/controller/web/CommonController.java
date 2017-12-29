package com.switch007.controller.web;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.switch007.model.FreeAgent;
import com.switch007.model.ResultModel;
import com.switch007.service.FreeAgentService;
import com.switch007.util.HttpClientUtil;

@Controller
@RequestMapping("/api/common")
public class CommonController {
	@Autowired
	FreeAgentService freeAgentService;
	@Autowired
	RedisTemplate redisTemplate;
	@Autowired
	Ts ts;

	@RequestMapping(value="/updateFreeAgent",method=RequestMethod.GET)
	@ResponseBody
	public ResultModel updateFreeAgent() {
		CloseableHttpClient chc = HttpClientUtil.chc;
		HttpGet hg = new HttpGet("http://www.kuaidaili.com/free/");
		hg.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
		hg.setHeader("Referer", "http://www.kuaidaili.com/");
		hg.setHeader("X-Requested-With", "XMLHttpRequest");
		String content = "";
		FileOutputStream fos = null;
		try {
			CloseableHttpResponse chr = chc.execute(hg);
			HttpEntity he = chr.getEntity();
			content = EntityUtils.toString(he, "utf-8");
			/*fos = new FileOutputStream("D://freeAgent.txt");
			fos.write(content.getBytes(), 0, content.getBytes().length);
			fos.close();
			chc.close();*/
			System.out.println("***FreeAgent抓取完成");
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultModel.fail(e.getMessage(), "");
		}
		return ResultModel.success("success", content);
	}

	@RequestMapping(value="/dealFreeAgentFile",method=RequestMethod.GET)
	@ResponseBody
	public String dealFreeAgentFile() {
		redisTemplate.delete("freeAgents");
		File file = new File("D://freeAgent.txt");
		String content = "";
		List<FreeAgent> fa_list = new ArrayList<FreeAgent>();
		if (file.exists()) {
			try {
				Document doc = Jsoup.parse(file, "utf-8");
				Elements eles = doc.select("tbody > tr");
				content = eles.html();
				for (Element ele : eles) {

					String ip = ele.select("td").get(0).text();
					String port = ele.select("td").get(1).text();
					String address = ele.select("td").get(4).text();
					String speed = ele.select("td").get(5).text();
					String check_time = ele.select("td").get(6).text();

					FreeAgent fa = new FreeAgent();
					fa.setIp(ip);
					fa.setAddress(address);
					fa.setSpeed(speed);
					fa.setCheckTime(check_time);
					fa.setPort(port);
					fa.setGrapTime(new Date());
					content = ip + ":" + port + "地址:" + address + "响应速度:" + speed + "验证时间:" + check_time;
					// 更新redis缓存
					redisTemplate.opsForList().leftPush("freeAgents", fa);
					fa_list.add(fa);
				}
				System.out.println("***********************FreeAgent更新完成");
				freeAgentService.batchSave(fa_list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	@RequestMapping(value="/grapWeb",method=RequestMethod.GET)
	@ResponseBody
	public ResultModel testProx() {
		String content = "";
		FreeAgent fa = null;
		for (int i = 0; i <= redisTemplate.opsForList().size("freeAgents") - 1; i++) {
			fa = (FreeAgent) redisTemplate.opsForList().index("freeAgents", i);
			System.out.println("第" + (i + 1) + "次: ip地址为:" + fa.getIp() + ":" + fa.getPort());
			try {
				HttpGet hg = HttpClientUtil.getGet("https://www.tuicool.com/");
				RequestConfig requestConfig = RequestConfig.custom().setProxy(new HttpHost(fa.getIp(), Integer.parseInt(fa.getPort()))).build();
				hg.setConfig(requestConfig);
				CloseableHttpResponse chr = HttpClientUtil.chc.execute(hg);
				int status = chr.getStatusLine().getStatusCode();
				System.out.println(status);
				if (status == 200) {
					System.out.println("WOW第" + (i + 1) + "次成功: ip地址为:" + fa.getIp() + ":" + fa.getPort());
					HttpEntity he = chr.getEntity();
					content = EntityUtils.toString(he, "utf-8");
					FileOutputStream fos = new FileOutputStream("D://tuicool.txt");
					fos.write(content.getBytes(), 0, content.getBytes().length);
					fos.close();
					HttpClientUtil.chc.close();

					break;
				} else {
					redisTemplate.opsForList().remove("freeAgents", 0, fa);
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ResultModel.success("success", content);
	}

	@RequestMapping(value="/dealNews",method=RequestMethod.GET)
	@ResponseBody
	public String dealNews() {
		redisTemplate.delete("hotNews");
		File file = new File("D://tuicool.txt");
		String content = "";
		List<FreeAgent> fa_list = new ArrayList<FreeAgent>();
		if (file.exists()) {
			try {
				Document doc = Jsoup.parse(file, "utf-8");
				Elements eles = doc.select("tbody > tr");
				content = eles.html();
				for (Element ele : eles) {

					String ip = ele.select("td").get(0).text();
					String port = ele.select("td").get(1).text();
					String address = ele.select("td").get(4).text();
					String speed = ele.select("td").get(5).text();
					String check_time = ele.select("td").get(6).text();

					FreeAgent fa = new FreeAgent();
					fa.setIp(ip);
					fa.setAddress(address);
					fa.setSpeed(speed);
					fa.setCheckTime(check_time);
					fa.setPort(port);
					fa.setGrapTime(new Date());
					content = ip + ":" + port + "地址:" + address + "响应速度:" + speed + "验证时间:" + check_time;
					// 更新redis缓存
					redisTemplate.opsForList().leftPush("freeAgents", fa);
					fa_list.add(fa);
				}
				System.out.println("***********************FreeAgent更新完成");
				freeAgentService.batchSave(fa_list);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return content;
	}

	/**
	 * 获取配置文件的值
	 * 
	 * @return
	 */
	@RequestMapping(value="/upd",method=RequestMethod.GET)
	@ResponseBody
	public ResultModel Ts() {
		Properties pt = new Properties();
		try {
			pt.load(CommonController.class.getResourceAsStream("/application.properties"));
			System.out.println(pt.get("mybatis.mapper-locations"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ts.getTitle());
		return ResultModel.success("success", ts.getTitle());
	}

	public static void main(String[] ss) {
	}

	/**
	 * 
	 * @param request
	 * @param response
	 *            返回流
	 */
	@RequestMapping(value="/io",method=RequestMethod.GET)
	public void ssddd(HttpServletRequest request, HttpServletResponse response) {
		InputStream is = null;
		response.addHeader("pragma", "NO-cache");
		response.addHeader("Cache-Control", "no-cache");
		response.addDateHeader("Expries", 0);
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			int length = 0;
			List<String> kk = new ArrayList<String>();
			for (int i = 0; i <= 100; i++) {
				kk.add(i + "ssssss");
			}
			is = new ByteArrayInputStream(kk.toString().getBytes("UTF-8"));
			byte buffer[] = new byte[1024];
			while ((length = is.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
