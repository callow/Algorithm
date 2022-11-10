package com.algo.util.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import net.agkn.hll.HLL;

/**
 * 
 * ����ͳ�ƣ� ͳ��һ��Ԫ���в��ظ���Ԫ�صĸ���   <br><br>
 * ���ڴ�������ͳ��. ҳ�������ͳ��, ������Ԫ�ص�������������ǳ��ǳ���ʱ�������������Ŀռ����ǹ̶� <br><br>
 * 	��bitmap��ȣ����������ض�ͳ�����������˵��HyperLogLog ȥ�ر� bitmap ����ܶ� <br>
 *  https://github.com/aggregateknowledge/java-hll
 */
public class HyperLogLogUtil {

	private static HLL hyperLogLog;
	/**
	 * ��ʼ��HyperLogLog, HLL��ʹ��regwidth * 2^log2m ��bitȥ�洢���ݣ�createHyperLogLog(14,5) = 81000 bits = 8100 bytes
	 * @param log2m
	 * @param registerWidth
	 * @return
	 */
	public static final HLL createHyperLogLog(int log2m,int registerWidth) {
		hyperLogLog = new HLL(log2m,registerWidth); // (14,5)
		return hyperLogLog ;
	}
	
	/**
	 * ��HyperLogLog������ݣ��������֮ǰ���뱻ĳ��Hashһ��
	 */
	
	public static HLL add(long item) {
		HashFunction hashFunction = Hashing.murmur3_128();
		long hashedValue = hashFunction.newHasher().putLong(item).hash().asLong();
		hyperLogLog.addRaw(hashedValue);
		return hyperLogLog;
	}
	
	/**
	 * �ϲ�2��HyperLogLog
	 */
	
	public static HLL union(HLL hyperLogLog1, HLL hyperLogLog2) {
		hyperLogLog1.union(hyperLogLog2);
		return hyperLogLog1;
	}
	
	
	/**
	 * ��ȡ����
	 */
	
	public static long getCardinality(HLL hyperLogLog) {
		return hyperLogLog.cardinality();
	}
	
	
}
