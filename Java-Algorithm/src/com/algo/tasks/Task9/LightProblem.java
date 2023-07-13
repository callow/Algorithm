package com.algo.tasks.Task9;
/**
 * �޻��ĵ�����
 */
public class LightProblem {
	
	
	/**
	 * 
	 * �޻��ĵ�����ĵݹ�汾
	 */
	public static int noLoopMinStep(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] ^ 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		// ����0λ�õ�״̬
		int p1 = f(arr, 2, arr[0], arr[1]);
		// �ı�0λ�õ�״̬
		int p2 = f(arr, 2, arr[0] ^ 1, arr[1] ^ 1);
		if (p2 != Integer.MAX_VALUE) {
			p2++;
		}
		return Math.min(p1, p2);
	}

	/**
	 * 
	 * ��ǰ���ĸ�λ���ϣ���ѡ��nextIndex - 1 = cur ����ǰ��
	   cur - 1 �� �� preStatus
	   cur ����  curStatus
	   0 ~ cur - 2  ȫ���ģ�
	 * curStatus ^ 1 : 0^1 = 1 , 1 ^ 1 = 0
	 */
	public static int f(int[] arr, int nextIndex, int preStatus, int curStatus) {
		if (nextIndex == arr.length) { // �������һ�����ص�λ��N-1
			return preStatus != curStatus ? (Integer.MAX_VALUE) : (curStatus ^ 1);
		}
		/**
		 *  û�����һ����ť�أ� i < arr.length
		 */
		if (preStatus == 0) { // ֮ǰ״̬��0 �� һ��Ҫ�ı�, ֮ǰλ��ֻ���Լ������� ��������û���˿��Ըı��ˣ� ��������
			curStatus ^= 1; // �ı��Լ�
			int cur = arr[nextIndex] ^ 1; // �ı��ұ�
			int next = f(arr, nextIndex + 1, curStatus, cur);
			return next == Integer.MAX_VALUE ? next : (next + 1);
		} else { //  ֮ǰ״̬��1 �� һ����Ҫ�ı�, ��Ҫ��
			return f(arr, nextIndex + 1, curStatus, arr[nextIndex]);
		}
	}
	
	
	/**
	 * �޻��ĵ�����ĵ����汾
	 */
	public static int noLoopMinStep2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] == 1 ? 0 : 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		int p1 = traceNoLoop(arr, arr[0], arr[1]);
		int p2 = traceNoLoop(arr, arr[0] ^ 1, arr[1] ^ 1);
		p2 = (p2 == Integer.MAX_VALUE) ? p2 : (p2 + 1);
		return Math.min(p1, p2);
	}

	public static int traceNoLoop(int[] arr, int preStatus, int curStatus) {
		int i = 2;
		int op = 0;
		while (i != arr.length) {
			if (preStatus == 0) {
				op++;
				preStatus = curStatus ^ 1;
				curStatus = arr[i++] ^ 1;
			} else {
				preStatus = curStatus;
				curStatus = arr[i++];
			}
		}
		return (preStatus != curStatus) ? Integer.MAX_VALUE : (op + (curStatus ^ 1));
	}
	
}
