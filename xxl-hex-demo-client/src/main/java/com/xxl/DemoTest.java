package com.xxl;

import com.xxl.demo.msg.request.DemoRequest;
import com.xxl.demo.msg.response.DemoResponse;
import com.xxl.hex.core.serialise.ByteHexConverter;
import com.xxl.hex.http.client.HttpUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class DemoTest {
	
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DemoRequest request = new DemoRequest();
		request.setParam("hi, lucy.");
		String request_hex = ByteHexConverter.byte2hex(request.toHexByte());
		System.out.println(request_hex);
		
		String url = "http://localhost:8080/hexServlet";
		String response_hex = HttpUtil.sendHex(url, request_hex);
		System.out.println(response_hex);
		
		DemoResponse msg = (DemoResponse) DemoResponse.class.newInstance().fillHexByte(ByteHexConverter.hex2Byte(response_hex));
		System.out.println(BeanUtils.describe(msg));
	}
	
}
