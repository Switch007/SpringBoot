package com.switch007.util;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {

	public final static CloseableHttpClient chc = HttpClients.createDefault();

	public static HttpGet getGet(String url) {
		HttpGet hg=new HttpGet(url);
		hg.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
		return hg;
	}

}
