package com.algo.pattern.proxy;

import com.algo.pattern.proxy.model.Internet;
import com.algo.pattern.proxy.model.ProxyInternet;

/**
 * 
 * ��ֱ����ϵ�����ˣ��Խӵ��Ǵ����������һЩ�������ת��������
 *
 */
public class ProxyClientUtil {

	public static void main(String[] args) {
		// ���Ӵ��� ��������ʵ�Ļ���������
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
