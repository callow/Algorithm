package com.algo.tasks.day9;

public class LoopLightProblem {
	
	
	/**
	 * 
		�л��ĵ�����ĵݹ�汾
	 */
	public static int loopMinStep1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0] == 1 ? 0 : 1;
		}
		if (arr.length == 2) {
			return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		if (arr.length == 3) {
			return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
		}
		// 0���䣬1����
		int p1 = f(arr, 3, arr[1], arr[2], arr[arr.length - 1], arr[0]);
		// 0�ı䣬1����
		int p2 = f(arr, 3, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
		// 0���䣬1�ı�
		int p3 = f(arr, 3, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
		// 0�ı䣬1�ı�
		int p4 = f(arr, 3, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
		p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
		p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
		p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
		return Math.min(Math.min(p1, p2), Math.min(p3, p4));
	}


		/**
		 * 	��һ��λ���ǣ�nextIndex
		    ��ǰλ���ǣ�nextIndex - 1 -> curIndex
		    ��һ��λ����, nextIndex - 2 -> preIndex   preStatus
		    ��ǰλ���ǣ�nextIndex - 1, curStatus
		    endStatus, N-1λ�õ�״̬
		    firstStatus, 0λ�õ�״̬
		    ���أ������еƶ��������ٰ��¼��ΰ�ť
		
		 	��ǰ������λ��(nextIndex - 1)��һ��������1�����ٴ�2��ʼ
			nextIndex >= 3
			
		 */
		public static int f(int[] arr, 
				int nextIndex, int preStatus, int curStatus, 
				int endStatus, int firstStatus) {
			
			if (nextIndex == arr.length) { // ���һ��ť��
				return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (endStatus ^ 1);
			}
			
			/**
			 * �ձ�λ�õı仯��
			 */
			// ��ǰλ�ã�nextIndex - 1
			// ��ǰ��״̬����curStatus
			// ��������°�ť����һ����preStatus, curStatus
			// ������°�ť����һ����preStatus, curStatus ^ 1
			// ��������°�ť����һ����curStatus, arr[nextIndex]
			// ������°�ť����һ����curStatus, arr[nextIndex] ^ 1
			int noNextPreStatus = 0;
			int yesNextPreStatus = 0;
			int noNextCurStatus =0;
			int yesNextCurStatus = 0;
			int noEndStatus = 0;
			int yesEndStatus = 0;
			if(nextIndex < arr.length - 1) {// ��ǰû����N-2λ��
				 noNextPreStatus = curStatus;
				 yesNextPreStatus = curStatus ^ 1;
				 noNextCurStatus = arr[nextIndex];
				 yesNextCurStatus = arr[nextIndex] ^ 1;
			} else if(nextIndex == arr.length - 1) {// ��ǰ�����ľ���N-2λ��
				noNextPreStatus = curStatus;
				yesNextPreStatus = curStatus ^ 1;
				noNextCurStatus = endStatus;
				yesNextCurStatus = endStatus ^ 1;
				noEndStatus = endStatus;
				yesEndStatus = endStatus ^ 1;
			}
			if(preStatus == 0) { // ������ģ����ڱ��밴
				int next = f(arr, nextIndex + 1, yesNextPreStatus, yesNextCurStatus,
						nextIndex == arr.length - 1 ? yesEndStatus : endStatus, firstStatus);
				return next == Integer.MAX_VALUE ? next : (next + 1);
			}else {
				return f(arr, nextIndex + 1, noNextPreStatus, noNextCurStatus, 
						nextIndex == arr.length - 1 ? noEndStatus : endStatus, firstStatus);
						
			}
		}
		
		
		
		
		/**
		 * 
			�л��ĵ�����ĵ����汾
		 */
		public static int loopMinStep2(int[] arr) {
			if (arr == null || arr.length == 0) {
				return 0;
			}
			if (arr.length == 1) {
				return arr[0] == 1 ? 0 : 1;
			}
			if (arr.length == 2) {
				return arr[0] != arr[1] ? Integer.MAX_VALUE : (arr[0] ^ 1);
			}
			if (arr.length == 3) {
				return (arr[0] != arr[1] || arr[0] != arr[2]) ? Integer.MAX_VALUE : (arr[0] ^ 1);
			}
			// 0���䣬1����
			int p1 = traceLoop(arr, arr[1], arr[2], arr[arr.length - 1], arr[0]);
			// 0�ı䣬1����
			int p2 = traceLoop(arr, arr[1] ^ 1, arr[2], arr[arr.length - 1] ^ 1, arr[0] ^ 1);
			// 0���䣬1�ı�
			int p3 = traceLoop(arr, arr[1] ^ 1, arr[2] ^ 1, arr[arr.length - 1], arr[0] ^ 1);
			// 0�ı䣬1�ı�
			int p4 = traceLoop(arr, arr[1], arr[2] ^ 1, arr[arr.length - 1] ^ 1, arr[0]);
			p2 = p2 != Integer.MAX_VALUE ? (p2 + 1) : p2;
			p3 = p3 != Integer.MAX_VALUE ? (p3 + 1) : p3;
			p4 = p4 != Integer.MAX_VALUE ? (p4 + 2) : p4;
			return Math.min(Math.min(p1, p2), Math.min(p3, p4));
		}

		public static int traceLoop(int[] arr, int preStatus, int curStatus, int endStatus, int firstStatus) {
			int i = 3;
			int op = 0;
			while (i < arr.length - 1) {
				if (preStatus == 0) {
					op++;
					preStatus = curStatus ^ 1;
					curStatus = (arr[i++] ^ 1);
				} else {
					preStatus = curStatus;
					curStatus = arr[i++];
				}
			}
			if (preStatus == 0) {
				op++;
				preStatus = curStatus ^ 1;
				endStatus ^= 1;
				curStatus = endStatus;
			} else {
				preStatus = curStatus;
				curStatus = endStatus;
			}
			return (endStatus != firstStatus || endStatus != preStatus) ? Integer.MAX_VALUE : (op + (endStatus ^ 1));
		}
}
