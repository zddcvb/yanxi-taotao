package com.jollyclass.taotao.common.utils;

import java.util.Random;

/**
 * 各种id生成策略
 * @author 邹丹丹
 * @date 2017年7月19日 下午2:30:24
 * 
 */
public class IdUtils {
	/**
	 * 
	 * @return 随机字符串
	 */
	public static String getImageName(){
		long millis = System.currentTimeMillis();
		//生成三位随机数
		Random random=new Random();
		int end=random.nextInt(999);
		//将生成的毫秒+随机数，末尾不足三位的，在前面补0
		String str=millis+String.format("%03d", end);
		return str;
	}
	/**
	 * 生成随机的商品id，末尾不足两位的，用0填补
	 * @return 字符串
	 */
	public static long getItemId(){
		long millis = System.currentTimeMillis();
		Random random=new Random();
		int end = random.nextInt(99);
		String string=millis+String.format("%02d", end);
		return Long.parseLong(string);
	}
}
