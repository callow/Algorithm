package com.algo.tasks.day13;

import java.text.DecimalFormat;

public class NCardsABWin {

		// �ȸ�������
		// ��ֵΪ1~10�������һ�飬
		// ÿ���������ȸ��ʵĳ��1~10�е�һ��
		// �´γ�ỻһ���µ��飬��������
		// ���ۼӺ�<17ʱ���㽫һֱ����
		// ���ۼӺ�>=17��<21ʱ���㽫��ʤ
		// ���ۼӺ�>=21ʱ���㽫ʧ��
		// ���ػ�ʤ�ĸ���
		public static double f1() {
			return p1(0);
		}

		// ��Ϸ�Ĺ�������
		// ��������cur����ۼӺ͵�ʱ�򣬻�ʤ�����Ƕ��ٷ��أ�
		public static double p1(int cur) {
			if (cur >= 17 && cur < 21) {
				return 1.0;
			}
			if (cur >= 21) {
				return 0.0;
			}
			double w = 0.0;
			for (int i = 1; i <= 10; i++) {
				w += p1(cur + i);
			}
			return w / 10;
		}

		// �ȸ���������չ��
		// ��ֵΪ1~N�������һ�飬
		// ÿ���������ȸ��ʵĳ��1~N�е�һ��
		// �´γ�ỻһ���µ��飬��������
		// ���ۼӺ�<aʱ���㽫һֱ����
		// ���ۼӺ�>=a��<bʱ���㽫��ʤ
		// ���ۼӺ�>=bʱ���㽫ʧ��
		// ���ػ�ʤ�ĸ��ʣ������Ĳ���ΪN��a��b
		public static double f2(int N, int a, int b) {
			if (N < 1 || a >= b || a < 0 || b < 0) {
				return 0.0;
			}
			if (b - a >= N) {
				return 1.0;
			}
			// ���в������Ϸ�������b-a < N
			return p2(0, N, a, b);
		}

		// ��Ϸ�������ϣ�int N, int a, int b���̶�������
		// cur��Ŀǰ������cur���ۼӺ�
		// ����Ӯ�ĸ���
		public static double p2(int cur, int N, int a, int b) {
			if (cur >= a && cur < b) {
				return 1.0;
			}
			if (cur >= b) {
				return 0.0;
			}
			double w = 0.0;
			for (int i = 1; i <= N; i++) {
				w += p2(cur + i, N, a, b); // cur +1, cur +2, cur +3. cur +4... �ݹ飬 �����ۼӺ�Ϊ5��p����Ҫ6 7 8 9�ĸ���
			}
			return w / N;
		}

		// f2�ĸĽ��汾���õ��˹۲�λ���Ż�ö�ٵļ���
		// ���Կ��Ͻ�һ��
		public static double f3(int N, int a, int b) {
			if (N < 1 || a >= b || a < 0 || b < 0) {
				return 0.0;
			}
			if (b - a >= N) {
				return 1.0;
			}
			return p3(0, N, a, b);
		}

		public static double p3(int cur, int N, int a, int b) {
			if (cur >= a && cur < b) {
				return 1.0;
			}
			if (cur >= b) {
				return 0.0;
			}
			if (cur == a - 1) {
				return 1.0 * (b - a) / N;
			}
			double w = p3(cur + 1, N, a, b) + p3(cur + 1, N, a, b) * N;
			if (cur + 1 + N < b) {
				w -= p3(cur + 1 + N, N, a, b);
			}
			return w / N;
		}

		// f3�ĸĽ��汾�Ķ�̬�滮
		// ���Կ��Ͻ�һ��
		public static double f4(int N, int a, int b) {
			if (N < 1 || a >= b || a < 0 || b < 0) {
				return 0.0;
			}
			if (b - a >= N) {
				return 1.0;
			}
			double[] dp = new double[b];
			for (int i = a; i < b; i++) {
				dp[i] = 1.0;
			}
			if (a - 1 >= 0) { // a-1 λ��ֱ���ù�ʽ����
				dp[a - 1] = 1.0 * (b - a) / N;
			}
			for (int cur = a - 2; cur >= 0; cur--) { // a-2 λ����ǰ
				double w = dp[cur + 1] + dp[cur + 1] * N;
				if (cur + 1 + N < b) {
					w -= dp[cur + 1 + N];
				}
				dp[cur] = w / N;
			}
			return dp[0];
		}

		public static void main(String[] args) {
			int N = 10;
			int a = 17;
			int b = 21;
			System.out.println("N = " + N + ", a = " + a + ", b = " + b);
			System.out.println(f1());
			System.out.println(f2(N, a, b));
			System.out.println(f3(N, a, b));
			System.out.println(f4(N, a, b));

			int maxN = 15;
			int maxM = 20;
			int testTime = 100000;
			System.out.println("���Կ�ʼ");
			System.out.println("�ȶ�double���ʹ𰸿��ܻ��о��ȶԲ�׼������");
			System.out.println("���Դ�һ��ֻ����С�������λ���бȶ�");
			System.out.println("���û�д�����ʾ, ˵����֤ͨ��");
			DecimalFormat df = new DecimalFormat("#.####");
			for (int i = 0; i < testTime; i++) {
				N = (int) (Math.random() * maxN);
				a = (int) (Math.random() * maxM);
				b = (int) (Math.random() * maxM);
				double ans2 = Double.valueOf(df.format(f2(N, a, b)));
				double ans3 = Double.valueOf(df.format(f3(N, a, b)));
				double ans4 = Double.valueOf(df.format(f4(N, a, b)));
				if (ans2 != ans3 || ans2 != ans4) {
					System.out.println("Oops!");
					System.out.println(N + " , " + a + " , " + b);
					System.out.println(ans2);
					System.out.println(ans3);
					System.out.println(ans4);
				}
			}
			System.out.println("���Խ���");

			N = 10000;
			a = 67834;
			b = 72315;
			System.out.println("N = " + N + ", a = " + a + ", b = " + b + "ʱ, ���˷���4�ⶼ��ʱ");
			System.out.print("����4��: ");
			System.out.println(f4(N, a, b));
		}
	}
