package com.algo.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

public class HashUtil {
	
	
	/**
	 * ��ȡJDKĬ��֧�ֵ�Hash�㷨��
	 */
	
	public static final Set<String> getJDKHashAlgorithms() {
		Set<String> set = new HashSet<>();
		for (String str : Security.getAlgorithms("MessageDigest")) {
			set.add(str);
		}
		return set;
	}
	
	/**
	 * ʹ���ض��㷨��ȡĳ���ַ�����HashCode, e.g: getHashCode('MD5','SONGLEI')
	 */
	
	public static final String getHashCode(String algorithm, String input) throws NoSuchAlgorithmException {
		MessageDigest hash = MessageDigest.getInstance(algorithm);
		return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
	}
	
	
}
