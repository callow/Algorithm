package leet.code;
/**
 	动态规划问题。 Kadane's Algorithm
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
	        int[] dp = new int[n];// 子列和数列;
	        dp[0] = nums[0]; // 子列和数列的第一个元素
	        int max = dp[0]; // 子列和数列的最大值
	        
	        for(int i = 1; i < n; i++) { // 从第二个开始遍历
	            dp[i] = (dp[i - 1] > 0 ? dp[i - 1] : 0) + nums[i] ; // 当前dp = 前一个dp + 当前num，只有dp >0 才会越加越大
	            max = Math.max(max, dp[i]); // 用上一组的最大值和 当前和比较
	        }
	        
	        return max;
	  }


// 这是一个很经典的问题 特别是第20 行 要反复看
			
			// 解法2  我倒是觉得这个解法比较好
			public static int maxSubArray2(int[] A) {
			    int maxSoFar=A[0], maxEndingHere=A[0];
			    
			    for (int i=1;i<A.length;++i) {
			    	maxEndingHere = Math.max(maxEndingHere+A[i],A[i]);
			    	maxSoFar = Math.max(maxSoFar, maxEndingHere);	
			    }
			    
			    return maxSoFar;
			}
}


