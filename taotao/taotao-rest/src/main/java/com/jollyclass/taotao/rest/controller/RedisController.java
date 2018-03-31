package com.jollyclass.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jollyclass.taotao.common.utils.TaoTaoResult;
import com.jollyclass.taotao.rest.service.RedisService;

/**
 * @author 邹丹丹
 * @date 2017年7月25日 下午1:37:37
 * 
 */
@RequestMapping("/cach/sync")
@Controller
public class RedisController {
	@Autowired
	private RedisService redisService;
	/**
	 * 根据id删除缓存，与数据库同步。
	 * @param catagroyId 需要同步的id
	 * @return TaoTaoResult对象
	 */
	@RequestMapping("/content/{categoryId}")
	@ResponseBody
	public TaoTaoResult syncContent(@PathVariable long catagroyId){
		TaoTaoResult result = redisService.syncContent(catagroyId);
		return result;
		
	}
	
}
