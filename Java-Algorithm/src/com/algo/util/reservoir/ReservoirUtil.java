package com.algo.util.reservoir;

import com.algo.util.reservoir.model.Reservoir;

/**
 * ʵ��������࣬��ѡ���ǵȸ��ʵ�
 *
 */

public class ReservoirUtil {

	/**
	 * ȫ���û����״ε�¼����һ�λ񽱻����ǰ100�����ߣ��������ô洢������ʽ����ý���
	 */
	
	private static Reservoir POOL = new Reservoir(100);
	public static int[] lottery() {
		
		// ����ȫ���ܹ���10000���û���������ˮ��
		for (int userId = 1; userId <= 10000; userId++) {
			POOL.add(userId);
		}
		
		// ��ȡ100������
		return POOL.sampling();
	}
	
}
