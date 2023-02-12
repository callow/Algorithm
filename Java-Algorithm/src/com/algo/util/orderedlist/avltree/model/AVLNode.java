package com.algo.util.orderedlist.avltree.model;

public class AVLNode<K extends Comparable<K>, V> {
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