package com.algo.util.binarytree;


import java.util.ArrayList;
import java.util.List;

import com.algo.util.common.model.BTNode;
import com.algo.util.common.model.NTNode;
/**
 * 
 * �������Ķ���Ϊһ��Ҫ�ݹ������
 */
public class BinaryTreeUtil {

	/**
	 * ��N����encode��2������<BR>
	 * ÿ��Node�ĺ��ӹ��������ұ߽��ϣ�ͬ���Ĺ��ڴ����Һ���<BR>
	 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree
	 */
	
	public static BTNode NTtoBT(NTNode root) {
		if (root == null) {
			return null;
		}
		BTNode head = new BTNode(root.val);
		head.left = encode(root.children); // ���к������������ұ߽��Ϲ�
		return head;
	}
	private static BTNode encode(List<NTNode> children) {
		BTNode head = null;
		BTNode cur = null;
		for (NTNode child : children) {
			BTNode tNode = new BTNode(child.val);
			if (head == null) {
				head = tNode;
			} else {
				cur.right = tNode;
			}
			cur = tNode;
			cur.left = encode(child.children); //�ٰ��Һ��ӵĶ����Ӽ����ң�������ȱ���
		}
		return head;
	}
	/**
	 * �ٽ�2����dencode��N������<BR>
	 * 
	 */
	public NTNode BTtoNT(BTNode root) {
		if (root == null) {
			return null;
		}
		return new NTNode(root.value, decode(root.left));
	}

	public static List<NTNode> decode(BTNode root) {
		List<NTNode> children = new ArrayList<>();
		while (root != null) {
			// ��ͣ�����ҳ��Ȼ��ӵ����Ӽ�����ȥ
			NTNode cur = new NTNode(root.value, decode(root.left));
			children.add(cur);
			root = root.right;
		}
		return children;
	}
	
	
}
