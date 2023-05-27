package com.algo.pattern.proxy;

import com.algo.pattern.proxy.model.Internet;
import com.algo.pattern.proxy.model.ProxyInternet;

/**
 * 
 * 不直接联系当事人，对接的是代理，代理过滤一些请求后再转给当事人
 *
 */
public class ProxyClientUtil {

	public static void main(String[] args) {
		// 连接代理 而不是真实的互联网对象
		Internet internet = new ProxyInternet();
        try {
            internet.connectTo("xvideos.com");
            internet.connectTo("baidu.com");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
	}
}
