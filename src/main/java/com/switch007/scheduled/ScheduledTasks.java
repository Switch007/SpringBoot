package com.switch007.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.switch007.controller.web.CommonController;

@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {

	@Scheduled(fixedRate = 1000 * 60)
	public void reportCurrentTime() {
		System.out.println("Scheduling Tasks Examples: The time is now " + dateFormat().format(new Date()));
	}

	// 每10分钟执行一次(更新freeAgent)
	@Scheduled(cron = "0 */60 *  * * * ")
	public void reportCurrentByCron() {
		CommonController cc = new CommonController();
		cc.updateFreeAgent();
		System.out.println("Scheduling Tasks Examples By Cron: The time is now " + dateFormat().format(new Date()));
	}

	private SimpleDateFormat dateFormat() {
		return new SimpleDateFormat("HH:mm:ss");
	}

}
