package com.jollyclass.taotao.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient工具類
 * 
 * @author 邹丹丹
 * @date 2017年7月22日 下午3:58:50
 * 
 */
public class HttpClientUtils {
	/**
	 * get请求：不携带任何参数
	 * 
	 * @param url
	 *            请求的地址
	 * @return 字符串数据
	 */
	public static String doGet(String url) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String string = null;
		try {
			System.out.println("请求url:" + url);
			httpClient = HttpClients.createDefault();
			HttpGet get = new HttpGet(url);
			response = httpClient.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				string = EntityUtils.toString(entity, "utf-8");
				System.out.println(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}

	/**
	 * get请求：携带参数
	 * 
	 * @param url
	 *            请求的地址
	 * @param params
	 *            携带的参数
	 * @return 字符串数据
	 */
	public static String doGet(String url, Map<String, String> params) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String string = null;
		try {
			httpClient = HttpClients.createDefault();
			URIBuilder builder = new URIBuilder(url);
			if (params != null) {
				for (String key : params.keySet()) {
					System.out.println(key + ":" + params.get(key));
					builder.addParameter(key, params.get(key));
				}
			}
			System.out.println(builder.build());
			HttpGet get = new HttpGet(builder.build());
			response = httpClient.execute(get);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				string = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}

	/**
	 * post传递json数据
	 * 
	 * @param url
	 *            连接地址
	 * @param json
	 *            携带的数据
	 * @return 字符串数据
	 */
	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}

	/**
	 * post请求：不携带任何数据
	 * 
	 * @param url
	 *            请求的地址
	 * @return 返回的数据
	 */
	public static String doPost(String url) {
		CloseableHttpClient httpClient = null;
		String string = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			response = httpClient.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				string = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}

	/**
	 * post请求：携带数据
	 * 
	 * @param url
	 *            请求的地址
	 * @param entities
	 *            携带的数据
	 * @return 返回的数据
	 */
	public static String doPost(String url, Map<String, String> entities) {
		CloseableHttpClient httpClient = null;
		String string = null;
		CloseableHttpResponse response = null;
		try {
			httpClient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			if (entities != null) {
				for (String key : entities.keySet()) {
					list.add(new BasicNameValuePair(key, entities.get(key)));
				}
			}
			StringEntity paramsEntity = new UrlEncodedFormEntity(list, "utf-8");
			post.setEntity(paramsEntity);
			response = httpClient.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				string = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}
}
