package com.algo.util.orderedlist.avltree;

/**
 * 
 * AVL : 做完搜索二叉树平衡性的调整后，自带了一个补丁.<br>
 * 
 * AVL树有四种破坏平衡性的行为：LL(一次右旋), LR(让LR的孙上到头-> 小树先左旋，全树再右旋), RL(), RR(一次左旋)
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
