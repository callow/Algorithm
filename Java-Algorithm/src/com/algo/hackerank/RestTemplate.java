package com.algo.hackerank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RestTemplate {

	/**
	 * 发起http 请求，返回json 
	 */
    public static String call(String endpoint, int page) throws MalformedURLException, IOException, ProtocolException {
		
		System.out.println(String.format(" URL: %s and page: %d", endpoint, page));
		
		URL url = new URL(endpoint+"&page="+page);
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
		con.setRequestMethod("GET");
		con.addRequestProperty("Content-Type", "application/json");
		
		int status = con.getResponseCode();
		if(status < 200 || status >= 300) {
			throw new IOException("Error in reading data with status:"+status);
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String response;
		StringBuilder sb = new StringBuilder();
		while((response = br.readLine())!=null) {
			sb.append(response);
		}
		
		br.close();
		con.disconnect();
		
		return sb.toString();
	}
}
