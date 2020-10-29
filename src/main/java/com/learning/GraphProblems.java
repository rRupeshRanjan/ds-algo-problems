package com.learning;

import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {
//        System.out.println(findCircleNum(new int[][]{{1,1,0},{1,1,1},{0,1,1}}));
//        System.out.println(findCircleNumUnionFind(new int[][]{{1,1,1,0},{1,1,0,1},{1,0,1,1},{0,1,1,1}}));
//        System.out.println(canFinish(4, new int[][]{{2,0},{1,0},{3,1},{3,2},{1,3}}));
    }

    // finding loop in a graph
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        Map<Integer, Integer> visitMap = new HashMap<>();

        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList<>();

        for (int[] prerequisite : prerequisites)
            graph[prerequisite[0]].add(prerequisite[1]);

        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(i, visitMap, graph))
                return false;
        }

        return true;
    }

    private static boolean isCyclic(int course, Map<Integer, Integer> visitMap, List<Integer>[] graph) {
        visitMap.put(course, 0);

        for (Integer c : graph[course]) {
            if ((!visitMap.containsKey(c) && isCyclic(c, visitMap, graph)) || visitMap.get(c) == 0)
                return true;
        }

        visitMap.put(course, 1);
        return false;
    }

    // finding number of groups in a graph
    public static int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int count = 0;

        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                count++;
                visited[i] = true;
                dfsCircleNum(i, visited, M);
            }
        }

        return count;
    }

    private static void dfsCircleNum(int row, boolean[] visited, int[][] M) {
        for (int j = 0; j < M[row].length; j++) {
            if (M[row][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfsCircleNum(j, visited, M);
            }
        }
    }

    public static int findCircleNumUnionFind(int[][] M) {
        int[] arr = new int[M.length];
        Arrays.fill(arr, -1);

        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M[i].length; j++) {
                if (M[i][j] == 1) {
                    int iParent = i, jParent = j;
                    while (arr[iParent] != -1) iParent = arr[iParent];
                    while (arr[jParent] != -1) jParent = arr[jParent];

                    // if they have diff parents, perform union
                    if (iParent != jParent) arr[jParent] = iParent;
                }
            }
        }

        int count = 0;
        for (int i : arr) {
            if (i < 0) count++;
        }

        return count;
    }
}
