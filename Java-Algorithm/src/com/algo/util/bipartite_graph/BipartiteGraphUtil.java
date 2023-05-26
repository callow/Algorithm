package com.algo.util.bipartite_graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * ����ͼ��
 * 
 * ���ƥ�����⣺Ȩ�ص�����
 * �ȶ��������⣺ Ȩ�ص�����
 *
 */
public class BipartiteGraphUtil {
	
	/**
	 * ����ͼ�Ƿ��Ƕ���ͼ��<br>
	 * 
	 * �ж�һ��ͼ�Ƕ���ͼ��������ȱ���Ⱦɫ��������ͬɫ���Ƕ���ͼ�����ڲ�ͬɫ���Ƕ���ͼ
	 * 
	 */
     public static boolean isBipartiteGraph(int[][] graph) {
    	 
    	 int n = graph.length; // n = node �� ����
         int[] color = new int[n]; // node��Ⱦ����ɫ
         Arrays.fill(color, 0); // ȫ��û�б�Ⱦɫ
         for (int i = 0; i < n; ++i) { // bfs
             if (color[i] == 0){
                 Queue<Integer> queue = new LinkedList<Integer>();
                 queue.offer(i);
                 color[i] = 1; // ������Ⱦһ����ɫ
                 while (!queue.isEmpty()) {
                     int node = queue.poll();
                     int dye = color[node] == 1 ? 2 : 1;
                     for (int neighbor : graph[node]) {
                         if (color[neighbor] == 0) { // ����ھ�û��Ⱦɫ������У�Ȼ��Ⱦɫ
                             queue.offer(neighbor);
                             color[neighbor] = dye;
                         } else if (color[neighbor] != dye) { // �ھӺ�ҪȾ��ɫ��ͬ�� ���Ƕ���ͼ
                             return false;
                         }
                     }
                 }
             }
         }
         return true;
    }
    
    /**
     * �ȶ���������
     */
     
    public static void stableMarriage() {
    	int prefer[][] = new int[][]{{7, 5, 6, 4},
            {5, 4, 6, 7},
            {4, 5, 6, 7},
            {4, 5, 6, 7},
            {0, 1, 2, 3},
            {0, 1, 2, 3},
            {0, 1, 2, 3},
            {0, 1, 2, 3}};
        GaleShapleyAlgorithm gsa = new GaleShapleyAlgorithm();
        gsa.stableMarriage(prefer);
    }

}
