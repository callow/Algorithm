package com.algo.util.disjointset.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.algo.util.common.model.DSNode;
/**
 * 
 * ʹ��Mapʵ�ֲ��鼯, ���������գ� List<V> values
 */
public class UnionFind<V> {
	
	// V ���װ���V �ض�Ӧ��ϵ
	public Map<V,DSNode<V>> nodes;
	
	// ��һ�ű�������ָ�룬V�ظ�����˭
	public Map<DSNode<V>,DSNode<V>> parents;
	
	// ����ڵ������м���Ԫ��
	public Map<DSNode<V>, Integer> sizeMap;
	
	// ������
	public UnionFind(List<V> values) {
		nodes = new HashMap<>();
		parents = new HashMap<>();
		sizeMap = new HashMap<>();
		for (V cur : values) {
			DSNode<V> node = new DSNode<>(cur);
			nodes.put(cur, node);
			parents.put(node, node);
			sizeMap.put(node, 1);
		}
	}
	
	/**
	 *  ����һ���ڵ㣬�������ϵ����������ϣ��Ѵ���ڵ㷵��
	 */
	
	public DSNode<V> findFather(DSNode<V> cur) {
		Stack<DSNode<V>> path = new Stack<>();
		while (cur != parents.get(cur)) {
			path.push(cur);
			cur = parents.get(cur);
		}
		while (!path.isEmpty()) {
			parents.put(path.pop(), cur);
		}
		return cur;
	}
	
	public boolean isSameSet(V a, V b) {
		return findFather(nodes.get(a)) == findFather(nodes.get(b));
	}
	
	/**
	 *  union ~ ƽ�� O(1)<br>
	 *  a�������е� �� b�������еغϲ���һ��
	 */
	
	public void union(V a, V b) {
		DSNode<V> aHead = findFather(nodes.get(a));
		DSNode<V> bHead = findFather(nodes.get(b));
		if (aHead != bHead) {
			int aSetSize = sizeMap.get(aHead);
			int bSetSize = sizeMap.get(bHead);
			DSNode<V> big = aSetSize >= bSetSize ? aHead : bHead;
			DSNode<V> small = big == aHead ? bHead : aHead;
			parents.put(small, big);
			sizeMap.put(big, aSetSize + bSetSize);
			sizeMap.remove(small);
		}
	}
	
	public int numberOfSets() {
		return sizeMap.size();
	}

	
}
