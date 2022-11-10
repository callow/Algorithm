package com.algo.util.hash;

import com.google.common.hash.Hashing;
/**
 * �κ����ݴ򵽻��ϣ�����˳ʱ���һ̨����: <br> <br>
 * 
 * https://www.163.com/dy/article/H6K38NAH0552OQCF.html
 *
 */
public class ConsistenHashUtil {
	
	/**
	 * ��Hash(data) �� hash(bucket) or hash(ip) �ȶԣ���>= ������ҵ�˳ʱ���һ̨����
	 * @param id�� ��������ݣ��ᱻHash(data)
	 * @param buckets : ��Ⱥ�л���������
	 * @return ����һ���̶������֣���ʾ����Ӧ�����ڵڼ���������
	 */
	public static int getBucket(int data, int buckets) {
		return Hashing.consistentHash(data, buckets);
	}
}
