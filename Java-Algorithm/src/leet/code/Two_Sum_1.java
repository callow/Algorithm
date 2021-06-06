package leet.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given num = [1,7,1,15], 目标 ：  9
 * num[0] + num[1] = 2+ 7 = 9, return [0,1]
 *
 */

public class Two_Sum_1 {

	public static void main(String[] args) {
		int[] input = {2, 4,2,8,3,5, 11, 15};
		int target = 12;
		
		System.out.println(Arrays.toString(twoSum(input,target)));
	}
	
	public static int[] twoSum(int[] number, int target) {
        int[] result = new int[2]; // 结果集
                                                    
        if (number == null || number.length < 2) {
            return result;
        }
        
         // key 是数字， value 是index， map中key是unique的
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        
        for (int i = 0; i < number.length; i++) {
        	int t = target - number[i]; // 检查目标和当前数字的差 看是否在之前遍历的map中，如果在那就是结果了！！
            if (map.containsKey(t)) {
            	result[0] = map.get(t);
                result[1] = i;
                return result;
            } else {
            	 map.put(number[i], i);//把 前面遍历过的数字和对应的下标加到map中
            }
       
        }
        return result;
   }

}
/**
 * 此题关键在第33行， 把之前loop过的数字和下标存起来， 然后用当前的差去存起来的集合中去找合适的对象，不错。
 */
