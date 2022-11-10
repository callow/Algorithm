package com.algo.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

public class HashUtil {
	
	
	/**
	 * 获取JDK默认支持的Hash算法名
	 */
	
	public static final Set<String> getJDKHashAlgorithms() {
		Set<String> set = new HashSet<>();
		for (String str : Security.getAlgorithms("MessageDigest")) {
			set.add(str);
		}
		return set;
	}
	
	/**
	 * 使用特定算法获取某个字符串的HashCode, e.g: getHashCode('MD5','SONGLEI')
	 */
	
	public static final String getHashCode(String algorithm, String input) throws NoSuchAlgorithmException {
		MessageDigest hash = MessageDigest.getInstance(algorithm);
		return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
	}
	
	
}
