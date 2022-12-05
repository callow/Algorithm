package com.algo.util.reservoir;

import com.algo.util.reservoir.model.Reservoir;

/**
 * ʵ��������࣬��ѡ���ǵȸ��ʵ�
 *
 */

public class ReservoirUtil {
	
	private static Reservoir POOL = new Reservoir(100);

	/**
	 * ȫ���û����״ε�¼����һ�λ񽱻����ǰ100�����ߣ��������ô洢������ʽ����ý��ߡ�<br>
	 * 
	 * �ý����빫ƽ / ���ȸ��ʣ� <br><br>
	 * 
	 * ����ˮ���ڲ����ʱ����ǵȸ��ʣ��պ÷���
	 */
	
	public static int[] lottery() {
		
		// ����ȫ���ܹ���10000���û���������ˮ��
		for (int userId = 1; userId <= 10000; userId++) {
			POOL.add(userId);
		}
		
		// ��ȡ100������
		return POOL.sampling();
	}
	
}
