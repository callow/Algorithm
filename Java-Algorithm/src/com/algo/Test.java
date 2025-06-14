package com.algo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.psjava.util.Pair;

import com.algo.util.linkedin.NQueensWithRegion;

public class Test {
	
	public static void main(String[] args) throws IOException {
		
        String imagePath = "E:\\microservices\\Algorithm\\Java-Algorithm\\src\\com\\algo\\util\\linkedin\\queen.PNG"; // 请换成你的图片路径
        int[][] regionMap = NQueensWithRegion.generateRegionMapByReferenceColors(ImageIO.read(new File(imagePath))); 
        
        // 将默认皇后位置替代成其背景像素
//        regionMap[0][0] = regionMap[0][1];
        
        // 打印色素矩阵
//        NQueensWithRegion.printRegionMap(regionMap);

        NQueensWithRegion solver = new NQueensWithRegion(regionMap);

        // 如果有固定皇后可以放这里
        Map<Integer,Integer> fixedQueens = new HashMap<>();
        fixedQueens.put(0, 0); 
        fixedQueens.put(7, 4);

        // 不能放皇后的位置
        Set<Pair<Integer,Integer>> forbiddenPositions = new HashSet<>();
        forbiddenPositions.add(new Pair<>(1,1)); 
        forbiddenPositions.add(new Pair<>(1,2)); 

        List<int[]> solutions = solver.nQueensLocation(fixedQueens, forbiddenPositions);

        System.out.println("找到 " + solutions.size() + " 个解");

	}
	

}
