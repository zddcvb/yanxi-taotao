package com.jollyclass.taotao.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.junit.Test;

/**
 * @author 邹丹丹
 * @date 2017年7月22日 下午3:06:45
 * 
 */
public class HttpClientTest {
	@Test
	public void testHttpClient() throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://www.sogou.com");

		CloseableHttpResponse response = client.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			System.out.println(string);
		}
		response.close();
		client.close();
	}

	@Test
	public void testHttpclientParam() throws URISyntaxException, ParseException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		URIBuilder builder = new URIBuilder("http://www.sogou.com/web");
		builder.addParameter("query", " 战狼");
		HttpGet httpGet = new HttpGet(builder.build());
		CloseableHttpResponse response = client.execute(httpGet);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			System.out.println(string);
		}
		response.close();
		client.close();
	}
	@Test
	public void testpost() throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post=new HttpPost("http://localhost:8082/http/post.html");
		CloseableHttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity, "utf-8");
			System.out.println(string);
		}
		response.close();
		client.close();
	}
	
	@Test
	public void testpostParam() throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post=new HttpPost("http://localhost:8082/http/post.html");
		List<NameValuePair> lists=new ArrayList<NameValuePair>();
		lists.add(new BasicNameValuePair("username", "jack"));
		lists.add(new BasicNameValuePair("password", "1234546"));
		StringEntity entity=new UrlEncodedFormEntity(lists, "utf-8");
		post.setEntity(entity);
		
		CloseableHttpResponse response = client.execute(post);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode == 200) {
			HttpEntity httpEntity = response.getEntity();
			String string = EntityUtils.toString(httpEntity, "utf-8");
			System.out.println(string);
		}
		response.close();
		client.close();
	}
}
