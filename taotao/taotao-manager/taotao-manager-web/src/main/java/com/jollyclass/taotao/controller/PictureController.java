package com.jollyclass.taotao.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.jollyclass.taotao.common.utils.JsonUtils;
import com.jollyclass.taotao.service.PictureService;

/**
 * 上传图片处理
 * @author 邹丹丹
 * @date 2017年7月19日 下午4:57:02
 * 
 */
@Controller
public class PictureController {
	@Autowired
	private PictureService pictureService;
	/**
	 * 图片上传
	 * @param uploadFile 上传文件对象
	 * @return 上传文件的字符串数据，里面包含 有url对象
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile){
		System.out.println("upload start");
		Map resultMap = pictureService.uploadPicture(uploadFile);
		//为了浏览器之间的兼容性，需要将map对象转换成字符串
		String jsonStr = JsonUtils.objectToJson(resultMap);
		return jsonStr;		
	}
}
