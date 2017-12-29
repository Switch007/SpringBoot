package com.switch007.controller.web;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;

public class WebSearcher implements PageProcessor {

	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");

	public Site getSite() {
		return site;
	}

	public void process(Page page) {
		// 部分二：定义如何抽取页面信息，并保存下来
		page.putField("title", page.getHtml().css(".article_detail_bg h1").toString());
		page.putField("link", page.getHtml().css(".article_meta a").links().toString());
		page.putField("content", page.getHtml().css("#nei").toString());
		page.putField("img", page.getHtml().css("img").toString());
		// 部分三：从页面发现后续的url地址来抓取
		page.addTargetRequests(page.getHtml().css("#list_article .list_article_item").links().all());

	}

	public static void main(String[] ss) {
		// 配置混播代理
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("118.193.107.92",80)));

		Spider.create(new WebSearcher())
		// 设置代理
				.setDownloader(httpClientDownloader)
				// 设置去重
				.setScheduler(new QueueScheduler().setDuplicateRemover(new HashSetDuplicateRemover()))
				// 从"https://github.com/code4craft"开始抓
				.addUrl("https://www.tuicool.com/ah/0/1?lang=1").addPipeline(new FilePipeline("D:\\webmagic\\"))
				// 开启5个线程抓取
				.thread(5)
				// 启动爬虫
				.run();
	}

}
