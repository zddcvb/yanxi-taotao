package com.jollyclass.taotao.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jollyclass.taotao.common.utils.FtpUtils;
import com.jollyclass.taotao.common.utils.IdUtils;
import com.jollyclass.taotao.service.PictureService;

/**
 * 图片上传下载的业务实现层
 * @author 邹丹丹
 * @date 2017年7月19日 下午2:23:29
 * 
 */
@Service
public class PictureServiceImpl implements PictureService {
	/**
	 * ftp的主机名
	 */
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	/**
	 * ftp主机所在的端口，不写默认为21
	 */
	@Value("${FTP_PORT}")
	private int FTP_PORT;
	/**
	 * ftp的登录用户名
	 */
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	/**
	 * ftp的登录密码
	 */
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	/**
	 * ftp服务器上上传文件的根路径
	 */
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	/**
	 * ftp上图片上传的路径
	 */
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map uploadPicture(MultipartFile uploadFile) {
		Map resultMap = new HashMap<>();
		// 生成新的文件名称
		// 取原始文件
		String originalFilename = uploadFile.getOriginalFilename();
		// 生成新的名称
		String imageName = IdUtils.getImageName();
		imageName = imageName + originalFilename.substring(originalFilename.lastIndexOf("."));
		String imagePath=new DateTime().toString("/yyyy/MM/dd");
		// ftp开始上传文件
		try {
			boolean result = FtpUtils.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
					imagePath, imageName, uploadFile.getInputStream());
			if (result) {
				//上传成功
				resultMap.put("error", 0);
				resultMap.put("url", IMAGE_BASE_URL+imagePath+"/"+imageName);
			}else{
				//上传失败
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
			}
		} catch (IOException e) {
			//上传失败
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传异常");
		}
		return resultMap;
	}

}
