/**
 *      ACSL 2020-2021 - Contest 4 - Graphs - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class GraphsIntermediate {

    /*
     * Complete the 'sumPathsOfLength2' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING edges
     */
    static int sumPathsOfLength2 (String edges) {

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
        for (int i = 1; i < 10; i++)
            for (int j = 1; j < 10; j++)
                for (int k = 1; k < 10; k++)
                {
                    if (i != j && j != k && k != i && graph[i][j] && graph[j][k]) {
                        result += i*100 + j*10 + k;
                    }
                }

        return result;
    }

    public static void main (String [] args) {

        String[] input = new String[10];
        input[0] = "12 23 34 41 31";
        input[1] = "12 23 34 41 13 32";
        input[2] = "76 75 12 13 23 31 34 41 56";
        input[3] = "34 45 56 63 64 61 13";
        input[4] = "12 21 13 15 53 33";
        input[5] = "12 31 41 42 43 45 51 63 64 56 16";
        input[6] = "12 13 22 23 24 34 42 98 71 87 17 96 67";
        input[7] = "12 14 21 24 25 32 41 43 59 65 91 87 76 95";
        input[8] = "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89";
        input[9] = "55 77 45 54";

        int[] output = {1653, 1789, 2956, 4515, 581, 8478, 6301, 7880, 7249, 0};

        for (int i = 0; i < 10; i++)
        {
            int result = sumPathsOfLength2(input[i]);

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