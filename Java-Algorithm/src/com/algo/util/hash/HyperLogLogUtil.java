package com.algo.util.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import net.agkn.hll.HLL;

/**
 * 
 * 基数统计： 统计一组元素中不重复的元素的个数   <br><br>
 * 用于大数据量统计. 页面访问量统计, 在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定 <br><br>
 * 	和bitmap相比，属于两种特定统计情况，简单来说，HyperLogLog 去重比 bitmap 方便很多 <br>
 *  https://github.com/aggregateknowledge/java-hll
 */
public class HyperLogLogUtil {

	private static HLL hyperLogLog;
	/**
	 * 初始化HyperLogLog, HLL会使用regwidth * 2^log2m 的bit去存储数据，createHyperLogLog(14,5) = 81000 bits = 8100 bytes
	 * @param log2m
	 * @param registerWidth
	 * @return
	 */
	public static final HLL createHyperLogLog(int log2m,int registerWidth) {
		hyperLogLog = new HLL(log2m,registerWidth); // (14,5)
		return hyperLogLog ;
	}
	
	/**
	 * 向HyperLogLog添加数据，数据添加之前必须被某个Hash一下
	 */
	
	public static HLL add(long item) {
		HashFunction hashFunction = Hashing.murmur3_128();
		long hashedValue = hashFunction.newHasher().putLong(item).hash().asLong();
		hyperLogLog.addRaw(hashedValue);
		return hyperLogLog;
	}
	
	/**
	 * 合并2个HyperLogLog
	 */
	
	public static HLL union(HLL hyperLogLog1, HLL hyperLogLog2) {
		hyperLogLog1.union(hyperLogLog2);
		return hyperLogLog1;
	}
	
	
	/**
	 * 获取基数
	 */
	
	public static long getCardinality(HLL hyperLogLog) {
		return hyperLogLog.cardinality();
	}
	
	
}
