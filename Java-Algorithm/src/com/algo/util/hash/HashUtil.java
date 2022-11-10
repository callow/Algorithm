package com.algo.util.hash;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import com.google.common.hash.Hashing;

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
	
	/**
	 * 生成N种不同的HashValues,提供给BoomFilter : i * f1(hashvalue) + f2（hashvalue)
	 */
	
	public Set<Long> generateNDiffHashcode(String input, int n) {
		Set<Long> hashValues = new HashSet<>(n);
		long f1 = Hashing.murmur3_128().hashString(input, Charset.defaultCharset()).asLong();
		long f2 = Hashing.crc32c().hashString(input, Charset.defaultCharset()).asLong();
		for (int i = 1; i <= n; i++) {
			hashValues.add(i * f1 + f2);
		}
		return hashValues;
	}
}
