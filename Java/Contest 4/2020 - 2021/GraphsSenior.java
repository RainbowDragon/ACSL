/**
 *      ACSL 2020-2021 - Contest 4 - Graphs - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class GraphsSenior {

    static int sumPathsOfLengthN (int num, String edges) {

        boolean[][] graph = new boolean[10][10];

        StringTokenizer st = new StringTokenizer(edges);
        while (st.hasMoreTokens())
        {
            int number = Integer.parseInt(st.nextToken());
            int from = number / 10;
            int to = number % 10;
            graph[from][to] = true;
        }

        int result = 0;
        boolean[] visited = new boolean[10];
        for (int i = 1; i < 10; i++)
        {
            visited[i] = true;
            result += dfs(graph, visited, i,0, num, i);
            visited[i] = false;
        }

        return result;
    }

    static int dfs (boolean[][] graph, boolean[] visited, int node, int length, int maxLength, int path) {

        if (length == maxLength) {
            return path;
        }

        int result = 0;
        for (int i = 1; i < 10; i++)
        {
            boolean next = graph[node][i];
            if (next && !visited[i]) {
                visited[i] = true;
                result += dfs(graph, visited, i, length + 1, maxLength, path * 10 + i);
                visited[i] = false;
            }
        }

        return result;
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"2", "12 23 34 41 31"};
        input[1] = new String[]{"3", "12 23 34 41 13 32"};
        input[2] = new String[]{"4", "67 75 54 12 13 23 31 34 41 56 45"};
        input[3] = new String[]{"3", "34 45 56 63 64 61 13"};
        input[4] = new String[]{"2", "12 21 13 15 53 33"};
        input[5] = new String[]{"2", "12 31 41 42 43 45 51 63 64 56 16"};
        input[6] = new String[]{"3", "12 13 22 23 24 34 42 98 71 87 17 96 67"};
        input[7] = new String[]{"4", "12 14 21 24 25 32 41 43 59 65 91 87 76 95"};
        input[8] = new String[]{"3", "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89"};
        input[9] = new String[]{"2", "55 77 45 54"};

        int[] output = {1653, 15242, 356313, 37651, 581, 8478, 74349, 754366, 59578, 0};

        for (int i = 0; i < 10; i++)
        {
            int result = sumPathsOfLengthN(Integer.parseInt(input[i][0]), input[i][1]);

            if (output[i] == result) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
                System.out.println("Expected output: " + output[i]);
                System.out.println("Your output: " + result);
            }
        }
    }
}