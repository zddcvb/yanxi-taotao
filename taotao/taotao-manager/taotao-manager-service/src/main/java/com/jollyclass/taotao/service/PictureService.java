package com.jollyclass.taotao.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


/**
 *  处理图片的上传和下载的业务层
 * @author 邹丹丹
 * @date 2017年7月19日 下午2:17:52
 *
 */
public interface PictureService {
	/**
	 * 文件上传
	 * @param uploadFile 需要上传的文件对象
	 * @return map对象，封装了上传失败和上传成功的字段
	 */
	@SuppressWarnings("rawtypes")
	Map uploadPicture(MultipartFile uploadFile);
}
