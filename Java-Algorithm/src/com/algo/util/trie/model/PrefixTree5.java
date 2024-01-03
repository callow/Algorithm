package com.algo.util.trie.model;

import java.util.Arrays;

import com.algo.util.bit.BitUtil;

/**
 * ʹ�þ�̬���� +  �����·����ʡ�ռ��ǰ׺�� - 2��·
 * 
 * ������Ҫ�ڵ���Ϣ
 * 
 */			 
public class PrefixTree5 {

	    // ׼����ô�ྲ̬�ռ�͹��ˣ�ʵ�������
		// ����������������˹�ģ���͸Ĵ����ֵ
		public static int MAXN = 3000001;

		public static int[][] tree = new int[MAXN][2]; // 0 / 1

		// ǰ׺��Ŀǰʹ���˶��ٿռ�
		public static int cnt;

		// ����ֻ��Ҫ����һλ��ʼ����
		public static int high;

		public static void build(int[] nums) {
			cnt = 1;
			// �Ҹ����ֵ
			int max = Arrays.stream(nums).max().getAsInt();
			
			// �����������ֵ�Ķ�����״̬���ж��ٸ�ǰ׺��0
			// ���Ժ�����Щǰ�õ�0����leftλ��ʼ����, �� 31 - ǰ��0������ = ����һ�����λ��ʼ������ʡ�ռ䣩
			high = 31 - Integer.numberOfLeadingZeros(max);
			for (int num : nums) {
				insert(num);
			}
		}

		public static void insert(int num) {
			int cur = 1;
			for (int i = high, path; i >= 0; i--) {
				path = (num >> i) & 1;
				if (tree[cur][path] == 0) {
					tree[cur][path] = ++cnt;
				}
				cur = tree[cur][path];
			}
		}

		public static int maxXor(int num) {
			// �������Ľ��(������)
			int ans = 0;
			// ǰ׺��Ŀǰ�����Ľڵ���
			int cur = 1;
			for (int i = high, status, want; i >= 0; i--) {
				// status : num��iλ��״̬
				status = BitUtil.retrieveBit(num, i);   
				// want : num��iλϣ��������״̬
				want =  BitUtil.flip(status);
				if (tree[cur][want] == 0) { // ѯ��ǰ׺�����ܲ��ܴ��
					// ���ܴ��,�ٻָ���ȥ
					want ^= 1;
				}
				// want�����������ߵ�· 
								
				ans = BitUtil.setStatusAtIndex(ans, (status ^ want), i); // ans |= (status ^ want) << i;
				cur = tree[cur][want];
			}
			return ans;
		}

		public static void clear() {
			for (int i = 1; i <= cnt; i++) {
				tree[i][0] = tree[i][1] = 0;
			}
		}
}
