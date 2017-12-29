package com.switch007.controller.admin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.switch007.model.Adminer;
import com.switch007.model.LoginModel;
import com.switch007.model.ResultModel;
import com.switch007.model.User;
import com.switch007.model.UserModel;
import com.switch007.service.AdminerService;
import com.switch007.service.UserService;
import com.switch007.util.Md5Util;

@Controller
@RequestMapping("/admin/user")
public class AdminerController {

	@Autowired
	private UserService userService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;// 处理字符

	@Autowired
	private RedisTemplate redisTemplate;// 处理对象

	@Autowired
	private AdminerService adminerService;

	@RequestMapping(value = "/loginPage")
	public String loginPage(HttpServletRequest request) {
		return "/admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultModel login(LoginModel lm, HttpServletRequest request) {
		if (StringUtils.isNullOrEmpty(lm.getAccount()) || StringUtils.isNullOrEmpty(lm.getPassword())) {
			return ResultModel.fail("-1", "请求参数为空");
		}
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("account", lm.getAccount());
		mp.put("password", Md5Util.getMd5(lm.getPassword()));
		Adminer admin = adminerService.login(mp);
		if (null != admin) {
			admin.setPassword("");
			request.getSession().setAttribute("admin", admin);
			return ResultModel.success("success", admin);
		} else {
			return ResultModel.fail("fail", admin);
		}

	}

	@RequestMapping("/userListPage")
	public String userListPage(HttpServletRequest request) {
		User t=null;
		t.getAddress();
		return "/admin/user_list";
	}

	@RequestMapping("/getUsersData")
	@ResponseBody
	public PageInfo<User> getUsersData(PageInfo<User> pageinfo, HttpServletRequest request) {
		Map<String, Object> mp = new HashMap<String, Object>();
		PageHelper.startPage(pageinfo.getPageNum(), pageinfo.getPageSize());
		String keyWord = request.getParameter("keyWord");
		mp.put("keyWord", keyWord);
		List<User> users = userService.pagelist(mp);
		PageInfo<User> p = new PageInfo<User>(users);
		return p;
	}

	/*
	 * @RequestMapping("/toList")//freemarker public String toList(Map<String,
	 * Object> map, HttpServletRequest request) {
	 * 
	 * User s = (User) redisTemplate.opsForValue().get("userId_" + 1);
	 * System.out.println(s.getUname());
	 * 
	 * String filepath = request.getServletContext().getRealPath("/") +
	 * "WEB-INF/admin/" + "user_list.html"; File htmlFile = new File(filepath);
	 * if (!htmlFile.exists()) {
	 * config.setTemplateExceptionHandler(TemplateExceptionHandler
	 * .RETHROW_HANDLER); Writer out = null; try { Template temple =
	 * config.getTemplate("user_list.ftl");// 获取模板 out = new
	 * OutputStreamWriter(new FileOutputStream(filepath), "utf-8");//
	 * 生成最终页面并写到文件 temple.process(map, out);// 处理 out.close(); } catch
	 * (Exception e) { e.printStackTrace(); } } return "/admin/user_list";
	 * 
	 * }
	 */

	public static void main(String[] ss) {
		String uri = "https://uic.youzan.com/sso/open/initToken";
		System.err.println(uri.matches("/sso.*"));
		Pattern p = Pattern.compile(".*/sso/.*");
		Matcher m = p.matcher(uri);
		while (m.find()) {
			System.out.println(m.group(0));

		}

		String pass = "123456";
		// 确定计算方法
		StringBuffer md5Code = new StringBuffer();
		try {
			// 获取加密方式为md5的算法对象
			MessageDigest instance = MessageDigest.getInstance("MD5");
			byte[] b = instance.digest(pass.getBytes());
			int i;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					md5Code.append("0");
				}
				md5Code.append(Integer.toHexString(i));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(md5Code.toString());
		// HttpClientUtils.post("https://uic.youzan.com/sso/open/initToken",
		// "client_secret=ab32e9fa93f1433d7b01d027e66cd471&client_id=22ae4cd74ae9353df8",
		// 6000L);
	}

}
