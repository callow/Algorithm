package com.algo.util.bipartite_graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 二部图：
 * 
 * 最大匹配问题：权重的量化
 * 稳定婚配问题： 权重的排序
 *
 */
public class BipartiteGraphUtil {
	
	/**
	 * 无向图是否是二部图？<br>
	 * 
	 * 判断一个图是二部图：宽度优先便利染色法，相邻同色则不是二部图，相邻不同色则是二部图
	 * 
	 */
     public static boolean isBipartiteGraph(int[][] graph) {
    	 
    	 int n = graph.length; // n = node 的 数量
         int[] color = new int[n]; // node被染的颜色
         Arrays.fill(color, 0); // 全都没有被染色
         for (int i = 0; i < n; ++i) { // bfs
             if (color[i] == 0){
                 Queue<Integer> queue = new LinkedList<Integer>();
                 queue.offer(i);
                 color[i] = 1; // 先任意染一个颜色
                 while (!queue.isEmpty()) {
                     int node = queue.poll();
                     int dye = color[node] == 1 ? 2 : 1;
                     for (int neighbor : graph[node]) {
                         if (color[neighbor] == 0) { // 如果邻居没有染色，入队列，然后染色
                             queue.offer(neighbor);
                             color[neighbor] = dye;
                         } else if (color[neighbor] != dye) { // 邻居和要染的色不同， 不是二部图
                             return false;
                         }
                     }
                 }
             }
         }
         return true;
    }
    
    /**
     * 稳定婚姻问题
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
