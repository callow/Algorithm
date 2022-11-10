package com.algo.util.hash;

import com.google.common.hash.Hashing;
/**
 * 任何数据打到环上，归于顺时针第一台机器: <br> <br>
 * 
 * https://www.163.com/dy/article/H6K38NAH0552OQCF.html
 *
 */
public class ConsistenHashUtil {
	
	/**
	 * 对Hash(data) 与 hash(bucket) or hash(ip) 比对，若>= 则可以找到顺时针第一台机器
	 * @param id： 请求的数据，会被Hash(data)
	 * @param buckets : 集群中机器的数量
	 * @return 返回一个固定的数字，表示数据应当落在第几个机器上
	 */
	public static int getBucket(int data, int buckets) {
		return Hashing.consistentHash(data, buckets);
	}
}
