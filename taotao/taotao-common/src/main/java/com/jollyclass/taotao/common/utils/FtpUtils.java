package com.jollyclass.taotao.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * ftp实现文件的上传和文件下载
 * 
 * @author 邹丹丹
 * @date 2017年7月19日 下午3:41:18
 * 
 */
public class FtpUtils {
	/**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param hostname
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param basePath
	 *            FTP服务器基础目录
	 * @param filePath
	 *            FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
	 * @param fileName
	 *            上传到FTP服务器上的文件名
	 * @param inputStream
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String hostname, int port, String username, String password, String basePath,
			String filePath, String fileName, InputStream inputStream) {
		boolean result = false;
		FTPClient ftpClient = new FTPClient();
		try {
			// 创建ftp连接，用户登录
			ftpClient.connect(hostname, port);
			ftpClient.login(username, password);
			// 获取服务返回的code，如果登录成功的返回值与replycode不一致，则登录失败
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				ftpClient.disconnect();
				return result;
			}
			// 创建服务端的文件保存地址
			if (!ftpClient.changeWorkingDirectory(basePath + filePath)) {
				String[] dirs = filePath.split("/");
				String tempPath = basePath;
				for (String dir : dirs) {
					if (null == dir || "".equals(dir)) {
						continue;
					}
					tempPath += "/" + dir;
					if (!ftpClient.changeWorkingDirectory(tempPath)) {
						if (!ftpClient.makeDirectory(tempPath)) {
							return result;
						} else {
							ftpClient.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			// 开始保存文件，默认为文本，如果是媒体文件，则需要设置filetype
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			if (!ftpClient.storeFile(fileName, inputStream)) {
				return result;
			}
			inputStream.close();
			ftpClient.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @param hostName FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 返回下载的状态，true为成功，false为失败
	 */  
	public static boolean downLoadFile(String hostName, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean result = false;
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(hostName, port);
			ftpClient.login(username, password);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				ftpClient.disconnect();
				return result;
			}
			ftpClient.changeWorkingDirectory(remotePath);
			//获得ftp上所有的文件
			FTPFile[] listFiles = ftpClient.listFiles();
			for (FTPFile ftpFile : listFiles) {
				//找到目标文件
				if (ftpFile.getName().equals(fileName)) {
					//生成本地文件
					File localFile = new File(localPath + "/" + fileName);
					OutputStream os = new FileOutputStream(localFile);
					//开始下载文件
					ftpClient.retrieveFile(remotePath, os);
					os.close();
				}
			}
			ftpClient.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}
}
