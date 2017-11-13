package com.switch007.util;

import java.util.UUID;

public class UUIDUtils {

	
	public static String get32UUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println(get32UUID());
		}
	}
}
