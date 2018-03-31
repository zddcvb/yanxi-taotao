package com.jollyclass.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

/**
 * @author 邹丹丹
 * @date 2017年7月19日 下午2:56:22
 * 
 */
public class FTPTest {
	@Test
	public void testFtp() throws SocketException, IOException{
		FTPClient ftpClient=new FTPClient();
		ftpClient.connect("172.16.2.77", 2121);
		boolean login = ftpClient.login("zddcvb", "zoudandan");
		if (login) {
			System.out.println("用戶登录成功");
		}
		int replyCode = ftpClient.getReplyCode();
		if (FTPReply.isPositiveCompletion(replyCode)) {
			System.out.println("登录成功");
		}
		System.out.println(replyCode);
		InputStream is=new FileInputStream(new File("d:/1.jpg"));
		ftpClient.changeWorkingDirectory("/taotao/images");
		//修改上传文件的格式，ftp默认为文本，但是图片为二进制的
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		ftpClient.storeFile("1.jpg", is);
		System.out.println("上传成功");
		ftpClient.logout();
		
	}
}
