/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package test.com.rmtap.card;

import com.fasterxml.jackson.databind.JsonNode;
import com.rtmap.utils.http.RestfulTemplate;
import junit.framework.TestCase;
import test.com.rmtap.token.AccessTokenUtil;

import java.io.*;
import java.util.Random;

/**
 * TestAbstractCard.
 * 
 * @author muarine maoyun@rtmap.com
 * @since 0.1
 */
public class TestAbstractCard extends TestCase {
	
	// 获取 智慧图开发账号的 access_token
	protected String access_token = AccessTokenUtil.postAccessToken("wxf3a057928b881466");
	
	private static Random random = new Random();
	
	/**
	 * 读取本地文件 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	protected String readFile(String path) throws IOException{
		
		File file = new File(path);
		InputStream is = new FileInputStream(file);
		
		// 文件reader
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String temp = null;
		
		// 将文件内容读出为字符串
		StringBuffer buffer = new StringBuffer();
		while((temp = br.readLine()) != null) {
			buffer.append(temp);
		}
		return buffer.toString();
	}
	
	/**
	 * 生成qrcode 
	 * @return
	 */
	public static synchronized String generate() {
		StringBuilder sb = new StringBuilder("01");
		long time = System.currentTimeMillis();
		sb.append(time);
		for (int i = 0; i < 3; i++) {
			sb.append(random.nextInt(10));
		}
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}
		return sb.toString();
	}
	
	/**
	 * 发送POST请求 
	 * @param url
	 * @param param
	 * @return
	 */
	public JsonNode sendPost(String url , String param){
		
		return RestfulTemplate.INSTANCE.postForObject(url, param, JsonNode.class);
		
	}
}
