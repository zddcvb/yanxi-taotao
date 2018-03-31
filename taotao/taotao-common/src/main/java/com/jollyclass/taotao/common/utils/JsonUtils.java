package com.jollyclass.taotao.common.utils;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json工具类：实现对象转换成json，json转换成对象，json转换成特定pojo对象的集合
 * 
 * @author 邹丹丹
 * @date 2017年7月19日 下午5:19:22
 * 
 */
public class JsonUtils {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 將一个对象转换成字符串
	 * 
	 * @param obj
	 *            需要转换的对象
	 * @return 字符串
	 */
	public static String objectToJson(Object obj) {
		String string = null;
		try {
			string = MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return string;
	}

	/**
	 * 将json数据转换成pojo对象
	 * 
	 * @param jsonData
	 *            json数据
	 * @param valueType
	 *            对象中的object类型
	 * @return object类
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> valueType) {
		try {
			T readValue = MAPPER.readValue(jsonData, valueType);
			return readValue;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
}
