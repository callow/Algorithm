package com.algo.util.binarytree.model;

import com.algo.util.common.model.BTNode;
/**
 * 
 * 树形DP
 *
 */
public class TreeDP {

	/**
	 * 1. 左树Full && 右树Full  && 左高 = 右高 => x 为头的整棵树是Complete<br>
	 * 2. 左Complete && 右Full && 左高 = 右高 + 1<br>
	 * 3. 左树Full && 右树Full  && 左高 = 右高 + 1<br>
	 * 4. 左Full && 右Complete && 左高 = 右高<br>
	 */
	
	public static CompleteTreeInfo goComplete(BTNode X) {
		if (X == null) {
			return new CompleteTreeInfo(true, true, 0);
		}
		CompleteTreeInfo leftInfo = goComplete(X.left);
		CompleteTreeInfo rightInfo = goComplete(X.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1; // 我的高度 = 左右最高那个 + 自己(1)
		
		boolean isFull = leftInfo.isFull && rightInfo.isFull && (leftInfo.height == rightInfo.height);
		
		boolean isCBT = false;
		if (isFull) {
			isCBT = true;
		} else { // 以x为头整棵树，不满
			if (leftInfo.isCBT && rightInfo.isCBT) {
				if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
					isCBT = true;
				}
			}
		}
		return new CompleteTreeInfo(isFull, isCBT, height);
	}
	
	
	
	public static BalancedTreeInfo goBalanced(BTNode x) {
		if(x == null) {
			return new BalancedTreeInfo(true, 0);
		}
		BalancedTreeInfo leftInfo = goBalanced(x.left);
		BalancedTreeInfo rightInfo = goBalanced(x.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height)  + 1;
		
		boolean isBalanced = true;
		if(!leftInfo.isBalanced) {
			isBalanced = false;
		}
		if(!rightInfo.isBalanced) {
			isBalanced = false;
		}
		if(Math.abs(leftInfo.height - rightInfo.height) > 1) {
			isBalanced = false;
		}
		return new BalancedTreeInfo(isBalanced, height);
	}
	
	
	public static SearchTreeInfo goSearch(BTNode x) {
		if (x == null) {
			return null;
		}
		SearchTreeInfo leftInfo = goSearch(x.left);
		SearchTreeInfo rightInfo = goSearch(x.right);
		int max = x.value;
		if (leftInfo != null) {
			max = Math.max(max, leftInfo.max);
		}
		if (rightInfo != null) {
			max = Math.max(max, rightInfo.max);
		}
		int min = x.value;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
		}
		boolean isBST = true;
		if (leftInfo != null && !leftInfo.isBST) {
			isBST = false;
		}
		if (rightInfo != null && !rightInfo.isBST) {
			isBST = false;
		}
		if (leftInfo != null && leftInfo.max >= x.value) {
			isBST = false;
		}
		if (rightInfo != null && rightInfo.min <= x.value) {
			isBST = false;
		}
		return new SearchTreeInfo(isBST, max, min);
	}
	
	
	public static MaxDistanceInfo goMaxDistance(BTNode x) {
		if (x == null) {
			return new MaxDistanceInfo(0, 0);
		}
		MaxDistanceInfo leftInfo = goMaxDistance(x.left);
		MaxDistanceInfo rightInfo = goMaxDistance(x.right);
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		
		int p1 = leftInfo.maxDistance;
		int p2 = rightInfo.maxDistance;
		int p3 = leftInfo.height + rightInfo.height + 1;
		
		int maxDistance = Math.max(Math.max(p1, p2), p3);
		
		return new MaxDistanceInfo(maxDistance, height);
	}
	
	
	public static FullTreeInfo goFull (BTNode x) {
		if (x == null) {
			return new FullTreeInfo(true, 0);
		}
		FullTreeInfo leftInfo = goFull(x.left);
		FullTreeInfo rightInfo = goFull(x.right);
		boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
		
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		
		return new FullTreeInfo(isFull, height);
	}
	
	
	
	public static BSTSizeInfo goSubBSTSize(BTNode x) {
		if (x == null) {
			return null;
		}
		BSTSizeInfo leftInfo = goSubBSTSize(x.left);
		BSTSizeInfo rightInfo = goSubBSTSize(x.right);
		int max = x.value;
		int min = x.value;
		int allSize = 1;
		if (leftInfo != null) {
			max = Math.max(leftInfo.max, max);
			min = Math.min(leftInfo.min, min);
			allSize += leftInfo.allSize;
		}
		if (rightInfo != null) {
			max = Math.max(rightInfo.max, max);
			min = Math.min(rightInfo.min, min);
			allSize += rightInfo.allSize;
		}
		int p1 = -1; // x不是头， 收集左树maxBSTSize
		if (leftInfo != null) {
			p1 = leftInfo.maxBSTSubtreeSize;
		}
		int p2 = -1; // x不是头， 收集右树maxBSTSize
		if (rightInfo != null) {
			p2 = rightInfo.maxBSTSubtreeSize;
		}
		int p3 = -1; // x是头
		boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
		boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
		if (leftBST && rightBST) {
			boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.value);
			boolean rightMinMoreX = rightInfo == null ? true : (x.value < rightInfo.min);
			if (leftMaxLessX && rightMinMoreX) {
				int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
				int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
				p3 = leftSize + rightSize + 1;
			}
		}
		return new BSTSizeInfo(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
	}
}
