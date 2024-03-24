/**
 *      ACSL 2020-2021 - Contest 4 - Graphs - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class GraphsJunior {

    static int findCharacteristic (int choice, String edges) {

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
        if (choice == 1) {

            int count1 = 0;
            for (int i = 1; i < 10; i++)
            {
                if (graph[i][i]) {
                    count1++;
                }
            }

            int count2 = 0;
            for (int i = 1; i < 10; i++)
                for (int j = 1; j < 10; j++)
                {
                    if (i != j && graph[i][j] && graph[j][i]) {
                        count2++;
                    }
                }
            count2 /= 2;

            result = count1 + count2;
        }
        else if (choice == 2) {

            int count = 0;
            int maxIndex = 0;
            for (int i = 1; i < 10; i++)
            {
                int current = 0;
                for (int j = 1; j < 10; j++)
                {
                    if (graph[i][j]) {
                        current++;
                    }
                }

                if (current > count) {
                    count = current;
                    maxIndex = i;
                }
            }

            for (int i = 1; i < 10; i++)
            {
                if (graph[maxIndex][i]) {
                    result += maxIndex*10 + i;
                }
            }
        }
        else if (choice == 3) {

            for (int i = 1; i < 10; i++)
                for (int j = 1; j < 10; j++)
                    for (int k = 1; k < 10; k++)
                    {
                        if (graph[i][j] && graph[j][k]) {
                            result++;
                        }
                    }
        }

        return result;
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"2", "12 13 23 31 34 41"};
        input[1] = new String[]{"1", "12 23 34 11 21 32 45 53 95 43 99 29 91"};
        input[2] = new String[]{"3", "12 23 34 41 31 52 45 61 14 21 33 55 13 54 32 56 36"};
        input[3] = new String[]{"1", "12 11 33 34 43 55 52 41 31 25 88 79 98 45 13 42 87 35 51 21 14 78"};
        input[4] = new String[]{"2", "12 11 33 34 43 55 52 41 31 25 88 79 98 45 13 42 87 35 51 21 14 78"};
        input[5] = new String[]{"1", "12 31 41 42 43 45 51 63 64 56 16"};
        input[6] = new String[]{"2", "12 13 22 23 24 34 42 98 71 87 17 96 67"};
        input[7] = new String[]{"3", "12 14 21 24 25 32 41 43 59 65 91 87 76 95"};
        input[8] = new String[]{"2", "11 12 14 15 23 25 31 43 45 51 52 68 79 87 89"};
        input[9] = new String[]{"3", "55 77 45 54"};

        int[] output = {25, 5, 49, 10, 50, 0, 42, 24, 52, 6};

        for (int i = 0; i < 10; i++)
        {
            int result = findCharacteristic(Integer.parseInt(input[i][0]), input[i][1]);

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