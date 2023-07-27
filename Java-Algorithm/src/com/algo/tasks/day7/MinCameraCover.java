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
	
	
	
	/**
	 * ���Ž�,̰�ģ�������� �Ż��� һ�����
	 */
	public static int minCameraCover(BTNode root) {
		Data data = process2(root);
		return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
	}

	// ��xΪͷ��x�·��Ľڵ㶼�Ǳ�covered��x�Լ���״����������
	public static enum Status {
		UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
	}

	// ��xΪͷ��x�·��Ľڵ㶼�Ǳ�covered���õ������Ž��У�
	// x��ʲô״̬��������״̬�£���Ҫ���ټ������
	public static class Data {
		public Status status;
		public int cameras;

		public Data(Status status, int cameras) {
			this.status = status;
			this.cameras = cameras;
		}
	}

	public static Data process2(BTNode X) {
		if (X == null) {
			return new Data(Status.COVERED_NO_CAMERA, 0);
		}
		Data left = process2(X.left);
		Data right = process2(X.right);
		int cameras = left.cameras + right.cameras;
		
		// �󡢻��ң�������һ��û����
		if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
			return new Data(Status.COVERED_HAS_CAMERA, cameras + 1);
		}

		// ���Һ��ӣ�������û�����ǵ����
		if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
			return new Data(Status.COVERED_NO_CAMERA, cameras);
		}
		// ���Һ��ӣ�������û�����ǵ������Ҳ��û�����
		return new Data(Status.UNCOVERED, cameras);
	}
}
