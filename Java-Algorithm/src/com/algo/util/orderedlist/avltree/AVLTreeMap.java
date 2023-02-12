package com.algo.util.orderedlist.avltree;

/**
 * 
 * AVL : 做完搜索二叉树平衡性的调整后，自带了一个补丁.<br>
 * 
 * AVL树有四种破坏平衡性的行为O（1）：
 * 		LL(一次右旋) O（1）
		LR(让LR的孙上到头-> 小树先左旋，全树再右旋) O（1）
		RL(让RL的孙上到头-> 小树先右旋，全树再左旋) O（1）
 		RR(一次左旋) O（1）
 		既是LL又是LR，则用LL的调整策略 O（1）
 		既是RR又是RL，则用RR的调整策略 O（1）
 		
  只要加入结点，沿途一直到根结点的所有父每个结点都要检查上面4种违规
 * 
 */
public class AVLTreeMap<K extends Comparable<K>, V> {
	
	
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
	
	
	private AVLNode<K, V> root;
	private int size;

	public AVLTreeMap() {
		root = null;
		size = 0;
	}

	private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
		AVLNode<K, V> left = cur.l;
		cur.l = left.r;
		left.r = cur;
		cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
		left.h = Math.max((left.l != null ? left.l.h : 0), (left.r != null ? left.r.h : 0)) + 1;
		return left;
	}

	private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
		AVLNode<K, V> right = cur.r;
		cur.r = right.l;
		right.l = cur;
		cur.h = Math.max((cur.l != null ? cur.l.h : 0), (cur.r != null ? cur.r.h : 0)) + 1;
		right.h = Math.max((right.l != null ? right.l.h : 0), (right.r != null ? right.r.h : 0)) + 1;
		return right;
	}

	/**
	 *  调平衡
	 */
	private AVLNode<K, V> maintain(AVLNode<K, V> cur) {
		if (cur == null) {
			return null;
		}
		int leftHeight = cur.l != null ? cur.l.h : 0;
		int rightHeight = cur.r != null ? cur.r.h : 0;
		if (Math.abs(leftHeight - rightHeight) > 1) {
			if (leftHeight > rightHeight) {
				int leftLeftHeight = cur.l != null && cur.l.l != null ? cur.l.l.h : 0;
				int leftRightHeight = cur.l != null && cur.l.r != null ? cur.l.r.h : 0;
				if (leftLeftHeight >= leftRightHeight) {
					cur = rightRotate(cur);
				} else {
					cur.l = leftRotate(cur.l);
					cur = rightRotate(cur);
				}
			} else {
				int rightLeftHeight = cur.r != null && cur.r.l != null ? cur.r.l.h : 0;
				int rightRightHeight = cur.r != null && cur.r.r != null ? cur.r.r.h : 0;
				if (rightRightHeight >= rightLeftHeight) {
					cur = leftRotate(cur);
				} else {
					cur.r = rightRotate(cur.r);
					cur = leftRotate(cur);
				}
			}
		}
		return cur;
	}

	private AVLNode<K, V> findLastIndex(K key) {
		AVLNode<K, V> pre = root;
		AVLNode<K, V> cur = root;
		while (cur != null) {
			pre = cur;
			if (key.compareTo(cur.k) == 0) {
				break;
			} else if (key.compareTo(cur.k) < 0) {
				cur = cur.l;
			} else {
				cur = cur.r;
			}
		}
		return pre;
	}

	private AVLNode<K, V> findLastNoSmallIndex(K key) {
		AVLNode<K, V> ans = null;
		AVLNode<K, V> cur = root;
		while (cur != null) {
			if (key.compareTo(cur.k) == 0) {
				ans = cur;
				break;
			} else if (key.compareTo(cur.k) < 0) {
				ans = cur;
				cur = cur.l;
			} else {
				cur = cur.r;
			}
		}
		return ans;
	}

	private AVLNode<K, V> findLastNoBigIndex(K key) {
		AVLNode<K, V> ans = null;
		AVLNode<K, V> cur = root;
		while (cur != null) {
			if (key.compareTo(cur.k) == 0) {
				ans = cur;
				break;
			} else if (key.compareTo(cur.k) < 0) {
				cur = cur.l;
			} else {
				ans = cur;
				cur = cur.r;
			}
		}
		return ans;
	}

	/**
	 * 添加
	 */
	private AVLNode<K, V> add(AVLNode<K, V> cur, K key, V value) {
		if (cur == null) {
			return new AVLNode<K, V>(key, value);
		} else {
			if (key.compareTo(cur.k) < 0) {
				cur.l = add(cur.l, key, value); // 加到左树上
			} else {
				cur.r = add(cur.r, key, value); // 加到右树上
			}
			cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1; // 更新树的高度
			return maintain(cur); 
		}
	}

	// 在cur这棵树上，删掉key所代表的节点
	// 返回cur这棵树的新头部
	private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
		if (key.compareTo(cur.k) > 0) {
			cur.r = delete(cur.r, key);
		} else if (key.compareTo(cur.k) < 0) {
			cur.l = delete(cur.l, key);
		} else {
			if (cur.l == null && cur.r == null) { // 左右孩子都没有
				cur = null;
			} else if (cur.l == null && cur.r != null) { // 有右无左
				cur = cur.r;
			} else if (cur.l != null && cur.r == null) { // 有左无右
				cur = cur.l;
			} else {
				AVLNode<K, V> des = cur.r;
				while (des.l != null) { // 找到右树上的最左结点
					des = des.l;
				}
				cur.r = delete(cur.r, des.k); // 递归
				des.l = cur.l;
				des.r = cur.r;
				cur = des;
			}
		}
		if (cur != null) {
			cur.h = Math.max(cur.l != null ? cur.l.h : 0, cur.r != null ? cur.r.h : 0) + 1;
		}
		return maintain(cur);
	}

	public int size() {
		return size;
	}

	public boolean containsKey(K key) {
		if (key == null) {
			return false;
		}
		AVLNode<K, V> lastNode = findLastIndex(key);
		return lastNode != null && key.compareTo(lastNode.k) == 0 ? true : false;
	}

	public void put(K key, V value) {
		if (key == null) {
			return;
		}
		AVLNode<K, V> lastNode = findLastIndex(key);
		if (lastNode != null && key.compareTo(lastNode.k) == 0) {
			lastNode.v = value;
		} else {
			size++;
			root = add(root, key, value);
		}
	}

	public void remove(K key) {
		if (key == null) {
			return;
		}
		if (containsKey(key)) {
			size--;
			root = delete(root, key);
		}
	}

	public V get(K key) {
		if (key == null) {
			return null;
		}
		AVLNode<K, V> lastNode = findLastIndex(key);
		if (lastNode != null && key.compareTo(lastNode.k) == 0) {
			return lastNode.v;
		}
		return null;
	}

	public K firstKey() {
		if (root == null) {
			return null;
		}
		AVLNode<K, V> cur = root;
		while (cur.l != null) {
			cur = cur.l;
		}
		return cur.k;
	}

	public K lastKey() {
		if (root == null) {
			return null;
		}
		AVLNode<K, V> cur = root;
		while (cur.r != null) {
			cur = cur.r;
		}
		return cur.k;
	}

	public K floorKey(K key) {
		if (key == null) {
			return null;
		}
		AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
		return lastNoBigNode == null ? null : lastNoBigNode.k;
	}

	public K ceilingKey(K key) {
		if (key == null) {
			return null;
		}
		AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
		return lastNoSmallNode == null ? null : lastNoSmallNode.k;
	}

}