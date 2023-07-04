package com.algo.tasks.day7;

import com.algo.util.common.model.BTNode;

/**
 * �����С�������⣺�������ݹ���·
 * 
 * https://leetcode.com/problems/binary-tree-cameras/
 *
 */
public class MinCameraCover {

	// Ǳ̨�ʣ�x��ͷ�ڵ㣬x�·��ĵ㶼�����ǵ������
	public static class Info {
		public long uncovered; // xû�б����ǣ�xΪͷ����������Ҫ�������
		public long coveredNoCamera; // x��������ǣ�����xû�����xΪͷ����������Ҫ�������
		public long coveredHasCamera; // x����������ˣ�����x�Ϸ��������xΪͷ����������Ҫ�������

		public Info(long un, long no, long has) {
			uncovered = un;
			coveredNoCamera = no;
			coveredHasCamera = has;
		}
	}
	
	public static int minCameraCover1(BTNode root) {
		Info data = process1(root);
		return (int) Math.min(data.uncovered + 1, // ������3/C��xû�����ǣ�x���������������һ������X����
				Math.min(data.coveredNoCamera, // ������2/A��x������������Ǳ�������
						data.coveredHasCamera)); // ������1/B: x�������������û�б�����
	}
	
	// ���п����Զ����
	public static Info process1(BTNode X) {
		if (X == null) { // base case: ����
			return new Info(Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
		}

		Info left = process1(X.left); // ���ҵ�����Ҫ3����Ϣ
		Info right = process1(X.right); // ���ҵ�����Ҫ3����Ϣ
		// ���Լ�XҲҪ��3����Ϣ��
		
		// x uncovered x�Լ��������ǣ�x�·����нڵ㣬��������
		// ���ӣ� ����û�����ǣ��������µĵ㶼������
		// ���ӱ����ǵ�û������������µĵ㶼������
		// ���ӱ�����Ҳ��������������µĵ㶼������
		long uncovered = left.coveredNoCamera + right.coveredNoCamera;

		// x�·��ĵ㶼��covered��xҲ��cover����x��û���
		long coveredNoCamera = Math.min(
				// 1)
				left.coveredHasCamera + right.coveredHasCamera,

				Math.min(
						// 2)
						left.coveredHasCamera + right.coveredNoCamera,

						// 3)
						left.coveredNoCamera + right.coveredHasCamera));

		// x�·��ĵ㶼��covered��xҲ��cover����x�������
		long coveredHasCamera = 
				Math.min(left.uncovered, Math.min(left.coveredNoCamera, left.coveredHasCamera))
				+ Math.min(right.uncovered, Math.min(right.coveredNoCamera, right.coveredHasCamera))
				+ 1;

		return new Info(uncovered, coveredNoCamera, coveredHasCamera);
	}
}
