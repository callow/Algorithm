package leet.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given num = [1,7,1,15], Ŀ�� ��  9
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
        int[] result = new int[2]; // �����
                                                    
        if (number == null || number.length < 2) {
            return result;
        }
        
         // key �����֣� value ��index�� map��key��unique��
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); 
        
        for (int i = 0; i < number.length; i++) {
        	int t = target - number[i]; // ���Ŀ��͵�ǰ���ֵĲ� ���Ƿ���֮ǰ������map�У�������Ǿ��ǽ���ˣ���
            if (map.containsKey(t)) {
            	result[0] = map.get(t);
                result[1] = i;
                return result;
            } else {
            	 map.put(number[i], i);//�� ǰ������������ֺͶ�Ӧ���±�ӵ�map��
            }
       
        }
        return result;
   }

}
/**
 * ����ؼ��ڵ�33�У� ��֮ǰloop�������ֺ��±�������� Ȼ���õ�ǰ�Ĳ�ȥ�������ļ�����ȥ�Һ��ʵĶ��󣬲���
 */
