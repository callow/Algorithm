package com.algo.util.orderedlist.avltree;

/**
 * 
 * AVL : ��������������ƽ���Եĵ������Դ���һ������.<br>
 * 
 * AVL���������ƻ�ƽ���Ե���Ϊ��LL(һ������), LR(��LR�����ϵ�ͷ-> С����������ȫ��������), RL(), RR(һ������)
 * 
 */
public class AVLTreeMap {
	public static class AVLNode<K extends Comparable<K>, V> {
		public K k;
		public V v;
		public AVLNode<K, V> l;
		public AVLNode<K, V> r;
		public int h;

		public AVLNode(K key, V value) {
			k = key;
			v = value;
			h = 1;
		}
	}
	
	
	
	
	
}
