package dynamic_program.D;
/**
 * Base: "abcdefghijklmnopqrstuvwxyz" ���޻��Ƶ��ַ���
 * ����һ���ַ���s ������ͳ�� s ���ж��� ��ͬ�ǿ��Ӵ� Ҳ�� Base �г���
 * 
 * https://leetcode.cn/problems/unique-substrings-in-wraparound-string/
 */
public class UniqueSubstringsWraparoundString7 {

		// ά��һ���ַ���β����󳤶ȱ� dp[0]=s�б�����'a'���Ӵ���������쳤���Ƕ��٣�����һ��Ҫ����base���Ĺ���
	
		/**
		 * ֻ��Ҫ��ÿһ���ַ���s�֣�����base���������������󳤶ȣ��Դ�ȥ�ع��ܹ�����
		 * s: "zabpxyzab" 
		 * 
		 * a: 4
		 * b: 5
		 * p: 1
		 * z: 3
		 * y: 2
		 * x: 1
		 * sum = 16
		 * һ�����������߾Ϳ��ԣ��߲�ͨ��=1
		 * 
		 */
	
	
		// ʱ�临�Ӷ�O(n)��n���ַ���s�ĳ��ȣ��ַ���base����Ϊ������
		public static int findSubstringInWraproundString(String str) {
			int n = str.length();
			int[] s = new int[n];
			// abcde...z -> 0, 1, 2, 3, 4....25 ���ַ�ת��������
			for (int i = 0; i < n; i++) {
				s[i] = str.charAt(i) - 'a';
			}
			
			
			
			// dp[0] : s�б�����'a'���Ӵ���������쳤���Ƕ��٣�����һ��Ҫ����base���Ĺ���
			int[] dp = new int[26];
			// s : c d e....
			//     2 3 4
			dp[s[0]] = 1;
			for (int i = 1, cur, pre, len = 1; i < n; i++) {
				cur = s[i];
				pre = s[i - 1];
				// pre cur
				if ((pre == 25 && cur == 0) || pre + 1 == cur) {
					// (ǰһ���ַ���'z' && ��ǰ�ַ���'a') || ǰһ���ַ��ȵ�ǰ�ַ���ascii����1
					len++;
				} else {
					len = 1;
				}
				dp[cur] = Math.max(dp[cur], len);
			}
			int ans = 0;
			for (int i = 0; i < 26; i++) {
				ans += dp[i];
			}
			return ans;
		}
}
