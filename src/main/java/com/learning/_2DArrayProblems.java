package com.learning;

import java.util.*;

public class _2DArrayProblems {
    static int[][] directions = {{0,-1},{0,1},{-1,0},{1,0}};

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
//        System.out.println(pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}}));
//        print2DArray(floodFill(new int[][]{{0,0,1},{0,1,1}}, 1, 1, 2));
    }

    private static void print2DArray(int[][] array) {
        for (int[] ints : array) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if(color!=newColor)
            floodFillDfs(image, sr, sc, color, newColor);

        return image;
    }

    private static void floodFillDfs(int[][] image, int sr, int sc, int color, int newColor) {
        if(sr<0 || sr>=image.length || sc<=0 || sc>=image[0].length || image[sr][sc]!=color)
            return;

        image[sr][sc] = newColor;
        floodFillDfs(image, sr-1,sc,color, newColor);
        floodFillDfs(image, sr+1,sc,color, newColor);
        floodFillDfs(image, sr,sc-1,color, newColor);
        floodFillDfs(image, sr,sc+1,color, newColor);
    }

    public static List<List<Integer>> pacificAtlantic(int[][] matrix){
        Set<String> pacific = new HashSet<>();
        Set<String> atlantic = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(i==0 || j==0)
                    pacificAtlanticDfs(matrix, pacific, i, j);

                if(i==matrix.length-1 || j==matrix[i].length-1)
                    pacificAtlanticDfs(matrix, atlantic, i, j);
            }
        }

        pacific.retainAll(atlantic);
        for(String entry: pacific){
            String[] coordinates = entry.split(",");
            result.add(Arrays.asList(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
        }

        return result;
    }

    public static void pacificAtlanticDfs(int[][] matrix, Set<String> visited, int i, int j){
        visited.add(i + "," + j);
        for(int[] dir: directions){
            int nextI = i + dir[0];
            int nextJ = j + dir[1];

            if(nextI<0 || nextI>=matrix.length ||
                    nextJ<0 || nextJ>=matrix[i].length ||
                    visited.contains(nextI + "," + nextJ))
                continue;
            if(matrix[nextI][nextJ] >= matrix[i][j])
                pacificAtlanticDfs(matrix, visited, nextI, nextJ);
        }
    }

    public static int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0)
            return 0;

        int[][] dp = new int[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++){
            for (int j=0; j<grid[i].length; j++){
                dp[i][j] += grid[i][j];
                if(i>0 && j>0) dp[i][j] += Math.min(dp[i-1][j], dp[i][j-1]);
                else if(i==0 & j>0) dp[i][j] += dp[i][j-1];
                else if(i>0) dp[i][j] += dp[i-1][j];
            }
        }

        return dp[grid.length-1][grid[0].length-1];
    }
}
