package com.algo.util.hash;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

import com.algo.util.common.CommonStringUtil;
import com.google.common.hash.Hashing;

/**
 * 
 * ���е�HashCode����16���Ƶ����ͣ�
 */
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
	
	/**
	 * ��UUIDת��Int uuid��ʵ����hashcode�м����-�� ������16��������,���ֱ��ȥ�� ��-�� ת����
	 */
	public static BigInteger uuidToInt(String uuid) {
		return new BigInteger(uuid.replace("-", ""), 16);
	}

	/**
	 * ʹ���ض��㷨��ȡĳ���ַ�����HashValue, e.g: getHashValue('MD5','SONGLEI')
	 */

	public static long getHashValue(String algorithm, String input) throws NoSuchAlgorithmException {
		return new BigInteger(getHashCode(algorithm, input), 16).longValue();
	}

	/**
	 * ����N�ֲ�ͬ��HashValues,�ṩ��BoomFilter : i * f1(hashvalue) + f2��hashvalue)
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
	
	/**
	 * �Լ�ʵ�ֵ�Hashֵ����,�õ������ַ�����hashֵ
	 */
	public static long hashValue(char[] s) { 
		int base = 499;
		long ans = CommonStringUtil.convert(s[0]);
		for (int i = 1; i < s.length; i++) {
			ans = ans * base + CommonStringUtil.convert(s[i]);
		}
		return ans;
	}
	
	
	
	
	
}
