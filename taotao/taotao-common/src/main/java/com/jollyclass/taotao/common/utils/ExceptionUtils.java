package com.jollyclass.taotao.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author 邹丹丹
 * @date 2017年7月22日 下午2:55:54
 * 
 */
public class ExceptionUtils {

	/**
	 * 获取异常的堆栈信息
	 * 
	 * @param t
	 * @return
	 */
	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}
}
