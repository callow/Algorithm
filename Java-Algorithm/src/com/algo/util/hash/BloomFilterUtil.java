package com.algo.util.hash;

import com.algo.util.common.Student;
import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

@SuppressWarnings("serial")
public class BloomFilterUtil {

	private static BloomFilter<Student> bloomFilter;
	
	// ���ڽ���������ת��bye[]
	private static Funnel<Student> studentFunnel = new Funnel<Student>() {
         @Override
         public void funnel(Student from, PrimitiveSink into) {
             into.putString(from.getName(), Charsets.UTF_8).putInt(from.getId());
         }
     };
	 
	
	 /**
	 * ����BloomFilter, �������hash���������������Լ��������ɺ;�����Nice
	 * @param expectedNumofEle : Ԥ�ڵ�Ԫ������ e.g 100000
	 * @param failureRate�� �ɽ��ܵ�ʧ����(expected false positive) e.g 0.0001
	 */
	public static final BloomFilter<Student> createBloomFilter(long expectedNumofEle, double failureRate) {
		 bloomFilter = BloomFilter.create(studentFunnel,expectedNumofEle,failureRate);
		 return bloomFilter;
	}
	/**
	 * ����1��Ԫ��
	 */
	
	public static final BloomFilter<Student> put(Student item) {
		bloomFilter.put(item);
		return bloomFilter;
	}
	
	/**
	 * True: ���ܰ����� false: һ��������
	 */
	
	public static final boolean mayContain(Student item) {
		return bloomFilter.mightContain(item);
	}
	


}
