package com.algo.util.linkedin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import org.psjava.util.Pair;

public class NQueensWithRegion {
	
	
	
	public static void main(String[] args) throws IOException {
		
        String imagePath = "E:\\microservices\\Algorithm\\Java-Algorithm\\src\\com\\algo\\util\\linkedin\\queen.PNG"; // �뻻�����ͼƬ·��
        int[][] regionMap = NQueensWithRegion.generateRegionMapByReferenceColors(ImageIO.read(new File(imagePath))); 
        
        // ��Ĭ�ϻʺ�λ��������䱳������
//      regionMap[0][0] = regionMap[0][1];
        
        // ��ӡɫ�ؾ���
//      NQueensWithRegion.printRegionMap(regionMap);

        NQueensWithRegion solver = new NQueensWithRegion(regionMap);

        // ����й̶��ʺ���Է�����
        Map<Integer,Integer> fixedQueens = new HashMap<>();
        fixedQueens.put(0, 0); 
//        fixedQueens.put(7, 4);

        // ���ܷŻʺ��λ��
        Set<Pair<Integer,Integer>> forbiddenPositions = new HashSet<>();
//        forbiddenPositions.add(new Pair<>(1,1)); 
//        forbiddenPositions.add(new Pair<>(1,2)); 

        List<int[]> solutions = solver.nQueensLocation(fixedQueens, forbiddenPositions);

        System.out.println("�ҵ� " + solutions.size() + " ����");

	}
		
	private int[][] regionMap;

    public NQueensWithRegion(int[][] regionMap) {
        this.regionMap = regionMap;
    }
		
	public List<int[]> nQueensLocation(Map<Integer, Integer> fixedQueens, Set<Pair<Integer,Integer>> forbiddenPositions) {
        List<int[]> solutions = new ArrayList<>();
        int[] result = new int[8];
        Arrays.fill(result, -1); // ��ʼ��Ϊ -1����ʾδ����
        for (Map.Entry<Integer, Integer> entry : fixedQueens.entrySet()) {
            result[entry.getKey()] = entry.getValue();
        }
        boolean[] usedRegion = new boolean[8]; // ��������Ƿ����лʺ�

        // ��ǹ̶��ʺ����������ѱ�ʹ��
        for (Map.Entry<Integer, Integer> entry : fixedQueens.entrySet()) {
            int r = entry.getKey();
            int c = entry.getValue();
            usedRegion[regionMap[r][c]] = true;
        }

        dfs(0, result, fixedQueens.keySet(), forbiddenPositions, usedRegion, solutions);
        if (!solutions.isEmpty()) {
            printBoard(solutions.get(0));
        }
        return solutions;
	}
	
	private void dfs(int row, int[] result, Set<Integer> fixedRows, Set<Pair<Integer,Integer>> forbiddenPositions, boolean[] usedRegion, List<int[]> solutions) { 
		 if (row == 8) {
              solutions.add(result.clone());
              return;
          }

          if (fixedRows.contains(row)) {
              // ��ǰ���ѹ̶���������һ��
              dfs(row + 1, result, fixedRows, forbiddenPositions, usedRegion, solutions);
              return;
          }

          for (int col = 0; col < 8; col++) {
              if (forbiddenPositions.contains(new Pair<>(row, col))) {
                  continue;
              }

              int region = regionMap[row][col];
              if (usedRegion[region]) {
                  continue; // ���������лʺ�����
              }

              if (isValid(row, col, result)) {
                  result[row] = col;
                  usedRegion[region] = true;

                  dfs(row + 1, result, fixedRows, forbiddenPositions, usedRegion, solutions);

                  usedRegion[region] = false;
                  result[row] = -1;
              }
          }
    }
    
    private boolean isValid(int row, int col, int[] result) {
        for (int i = 0; i < 8; i++) {
        	int c = result[i];
            if (c == -1) {
				continue;
			}
            int rowDiff = Math.abs(i - row);
            int colDiff = Math.abs(c - col);
            
            // 1. ����ͬ��
            if (row == i) {
				return false;
			}

            // 2. ����ͬ��
            if (col == c) {
				return false;
			}
            
            if (rowDiff == colDiff) {
                if (rowDiff == 1) {  // ����б�߾���1�񣬲�����
                    return false;
                }
                // rowDiff>=2ʱ��������ͻ
            }
            // 4. ���������ڣ��������ҺͶԽ��ߣ�
            // �����ų��������ڣ������Ƿ�ͬб��
            if (rowDiff <= 1 && colDiff <= 1) {
                return false;
            }
            
        }
        return true;
    }
    
    
	public static void printBoard(int[] result) {
	    for (int row = 0; row < result.length; row++) {
	        for (int col = 0; col < result.length; col++) {
	            if (result[row] == col) {
	                System.out.print("Q ");
	            } else {
	                System.out.print(". ");
	            }
	        }
	        System.out.println();
	    }
	    System.out.println(); // ���зָ���ͬ��
	}
	
	
//-------------------------------------------------------------------------------------
	 // �ο���ɫ���ų���ɫ��: https://imagecolorpicker.com/
    private static final List<int[]> REFERENCE_COLORS = Arrays.asList(
            new int[]{187,163,225}, // ��
            new int[]{254,201,147}, // ��
            new int[]{150,189,254}, // ��
            new int[]{179,223,160}, // ��
            new int[]{255,123,98},  // ��
            new int[]{224,224,224}, // ��
            new int[]{184,179,159}, // ��
            new int[]{230,243,137}  // ��
    );

    /**
     * ��ͼƬ�������ŵ� 8x8 �������ž���
     */
    public static int[][] generateRegionMapByReferenceColors(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();

        int[][] fullRegionMap = new int[height][width];

        // Step 1: �������أ���������ο���ɫ
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;

                // ��Ϊ��ɫ�����ڻ�ӽ��ڣ����Ϊ -1
                if (r < 40 && g < 40 && b < 40) {
                    fullRegionMap[y][x] = -1;
                    continue;
                }

                // �ҵ��������ɫ
                int minDistance = Integer.MAX_VALUE;
                int closestIndex = -1;
                for (int i = 0; i < REFERENCE_COLORS.size(); i++) {
                    int[] ref = REFERENCE_COLORS.get(i);
                    int dist = colorDistance(r, g, b, ref[0], ref[1], ref[2]);
                    if (dist < minDistance) {
                        minDistance = dist;
                        closestIndex = i;
                    }
                }

                fullRegionMap[y][x] = closestIndex;
            }
        }

        // Step 2: ����Ϊ 8x8
        return resizeTo8x8(fullRegionMap);
    }

    private static int colorDistance(int r1, int g1, int b1, int r2, int g2, int b2) {
        return (r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2);
    }

    private static int[][] resizeTo8x8(int[][] fullMap) {
        int height = fullMap.length;
        int width = fullMap[0].length;
        int blockHeight = height / 8;
        int blockWidth = width / 8;

        int[][] result = new int[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Map<Integer, Integer> counter = new HashMap<>();

                for (int y = row * blockHeight; y < Math.min((row + 1) * blockHeight, height); y++) {
                    for (int x = col * blockWidth; x < Math.min((col + 1) * blockWidth, width); x++) {
                        int region = fullMap[y][x];
                        counter.put(region, counter.getOrDefault(region, 0) + 1);
                    }
                }

                int maxCount = 0;
                int selectedRegion = -1;
                for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
                    if (entry.getValue() > maxCount && entry.getKey() != -1) {
                        maxCount = entry.getValue();
                        selectedRegion = entry.getKey();
                    }
                }

                result[row][col] = selectedRegion == -1 ? -1 : selectedRegion;
            }
        }

        return result;
    }

    
    public static void printRegionMap(int[][] regionMap) {
        for (int r = 0; r < regionMap.length; r++) {
            for (int c = 0; c < regionMap[r].length; c++) {
                System.out.print(regionMap[r][c] + " ");
            }
            System.out.println();
        }
    }
}
