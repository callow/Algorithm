package com.algo.util.hash;

import com.algo.util.common.Student;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

@SuppressWarnings("serial")
public class BloomFilterUtil {

	private static BloomFilter<Student> bloomFilter;
	
	// 用于将复杂类型转成bye[]
	private static Funnel<Student> studentFunnel = new Funnel<Student>() {
         @Override
         public void funnel(Student from, PrimitiveSink into) {
             into.putString(from.getName(), Charsets.UTF_8).putInt(from.getId());
         }
     };
	 
	
	 /**
	 * 创建BloomFilter, 无需计算hash函数的数量，它自己给你生成和决定！Nice
	 * @param expectedNumofEle : 预期的元素数量 e.g 100000
	 * @param failureRate： 可接受的失误率(expected false positive) e.g 0.0001
	 */
	public static final BloomFilter<Student> createBloomFilter(long expectedNumofEle, double failureRate) {
		 bloomFilter = BloomFilter.create(studentFunnel,expectedNumofEle,failureRate);
		 return bloomFilter;
	}
	/**
	 * 放入1个元素
	 */
	
	public static final BloomFilter<Student> put(Student item) {
		bloomFilter.put(item);
		return bloomFilter;
	}
	
	/**
	 * True: 可能包含， false: 一定不包含
	 */
	
	public static final boolean mayContain(Student item) {
		return bloomFilter.mightContain(item);
	}
	


}
