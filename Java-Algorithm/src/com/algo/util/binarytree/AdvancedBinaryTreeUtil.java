package com.algo.util.binarytree;

import com.algo.util.binarytree.model.TreeDP;
import com.algo.util.common.model.BTNode;
/**
 * 
 * �������ݹ���·�� ��������DP
 */
public class AdvancedBinaryTreeUtil {

	/**
	 * �Ƿ�����ȫ2������<br><br>
	 * ���ö������������ = ������������ȱ����� <br>
	 * 1. ���Һ��ӣ�û������ <br>
	 * 2. ����һ���������Һ��Ӳ�ȫʱ��ʣ�½ڵ����ȫ��Ҷ�ڵ����
	 */
	
	public static boolean isCompleteBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goComplete(head).isCBT;
	}

	
	/**
	 * �Ƿ���ƽ��2������<br><br>
	 * ÿһ���������Ҹ߶����<=1 <br>
	 * 1. x ����Ҫƽ�� <br>
	 * 2. x ����Ҫƽ��<br>
	 * 3. |x����ߣ� - x���Ҹߣ�| < 2 <br>
	 */
	public static boolean isBalancedBT(BTNode head) {
		return TreeDP.goBalanced(head).isBalanced;
	}

	/**
	 * �Ƿ�������2������<br><br>
	 * ÿһ���������� < ͷ < ���� <br>
	 * 1. x ��������2<br>
	 * 2. x ��������2<br>
	 * 3. x �������ֵ < x<br>
	 * 4. x ������Сֵ > x<br>
	 */
	public static boolean isSearchBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goSearch(head).isBST;
	}
	
	/**
	 * �ҵ�2���������ڵ�������룿 <br><br>
	 * ��/���������� = ��/�����ĸ߶�<br>
	 * 1. ���� x:  ���������� vs ����������<br>
	 * 2. ������ x: (���������� + ���������� + 1)<br>
	 * 3. maxDistance = Math.max(1,2)
	 */
	
	public static int maxDistance(BTNode head) {
		return TreeDP.goMaxDistance(head).maxDistance;
	}
	
	/**
	 * �Ƿ�����2������<br><br>
	 * ������߶�ΪH����ڵ��������ǣ�2^H -1����<br>
	 * 1. �ռ������Ƿ�����������,�ռ������ĸ߶�<br>
	 * 2. ������ && ������ && �������߶�һ�� -> ������������
	 */
	public static boolean isFullBT(BTNode head) {
		if (head == null) {
			return true;
		}
		return TreeDP.goFull(head).isFull;
	}
	
	/**
	 * �ҵ���������2��������Ȼ���ʴ����ж��ٽڵ㣿 <br><br>
	 * 1. x����ͷ : ������maxBSTSize vs ������maxBSTSize<br>
	 * 2. x��ͷ: (������BST && ������BST && ����max < x && ����min > x) => �𰸣� ��Size + ��Size + 1  
	 */
	// https://leetcode.com/problems/largest-bst-subtree
	public static int maxSubBSTSize(BTNode head) {
		if (head == null) {
			return 0;
		}
		return TreeDP.goSubBSTSize(head).maxBSTSubtreeSize;
		
	}
}
