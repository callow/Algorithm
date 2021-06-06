package leet.code;
/**
 	��̬�滮���⡣ Kadane's Algorithm
 * 
 *
 */
public class Maximum_Subarray_53 {
	public static void main(String[] args) {
		int[] num = {-2,1,-3,4,0,2,1,-5,4};
		System.out.println(maxSubArray(num));
	}
	
	  public static int maxSubArray(int[] nums) {
		    int n = nums.length;
	        int[] dp = new int[n];// ���к�����;
	        dp[0] = nums[0]; // ���к����еĵ�һ��Ԫ��
	        int max = dp[0]; // ���к����е����ֵ
	        
	        for(int i = 1; i < n; i++) { // �ӵڶ�����ʼ����
	            dp[i] = (dp[i - 1] > 0 ? dp[i - 1] : 0) + nums[i] ; // ��ǰdp = ǰһ��dp + ��ǰnum��ֻ��dp >0 �Ż�Խ��Խ��
	            max = Math.max(max, dp[i]); // ����һ������ֵ�� ��ǰ�ͱȽ�
	        }
	        
	        return max;
	  }


// ����һ���ܾ�������� �ر��ǵ�20 �� Ҫ������
			
			// �ⷨ2  �ҵ��Ǿ�������ⷨ�ȽϺ�
			public static int maxSubArray2(int[] A) {
			    int maxSoFar=A[0], maxEndingHere=A[0];
			    
			    for (int i=1;i<A.length;++i) {
			    	maxEndingHere = Math.max(maxEndingHere+A[i],A[i]);
			    	maxSoFar = Math.max(maxSoFar, maxEndingHere);	
			    }
			    
			    return maxSoFar;
			}
}


