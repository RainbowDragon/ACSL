/**
 *      ACSL 2021 Final - Problem 1 - Subrect - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Subrect {

    /*
     * Complete the 'find_max_sum_of_subrect' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING values as parameter.
     */
    static int find_max_sum_of_subrect (String values) {

        StringTokenizer st = new StringTokenizer(values);
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        int[][] rect = new int[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
            {
                rect[i][j] = Integer.parseInt(st.nextToken());
            }

        int result = rect[0][0];
        for (int si = 0; si < row; si++)
            for (int sj = 0; sj < col; sj++)
                for (int ei = si; ei < row; ei++)
                    for (int ej = sj; ej < col; ej++)
                    {
                        int sum = 0;
                        for (int i = si; i <= ei; i++)
                            for (int j = sj; j <= ej; j++)
                            {
                                sum += rect[i][j];
                            }
                        result = Math.max(result, sum);
                    }

        return result;
    }

    public static void main (String [] args) {

        String[] input = {
                "5 3 0 -2 -7 9 2 -6 -4 1 -4 -1 8 0 -2 -4 -3",
                "4 2 1 2 -3 4 -5 6 7 -8",
                "3 6 1 2 3 4 -5 -6 6 5 4 3 2 1 -9 2 -4 4 0 6",
                "3 5 0 -2 -7 9 2 -6 -4 1 -4 -1 8 0 -2 -4 -3",
                "5 3 -4 1 -4 -2 -4 -3 -1 8 0 0 -2 -7 9 2 -6",
                "4 4 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0",
                "4 4 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1 -1",
                "9 1 1 -2 3 -4 5 -6 7 -8 9",
                "2 9 1 -2 3 -4 5 -6 7 -8 9 1 -2 3 -4 5 -6 7 -8 9",
                "5 5 1 2 3 4 5 -5 -4 -3 -2 -1 1 2 3 4 5 1 2 3 4 5 -9 -6 -3 -6 -9",
                "6 4 -1 1 -8 -8 -2 2 -5 -5 -3 3 3 3 -4 4 4 4 -5 -5 5 5 -6 -6 6 6",
                "4 6 -1 1 -8 -8 -2 2 -5 -5 -3 3 3 3 -4 4 4 4 -5 -5 5 5 -6 -6 6 6",
                "1 1 5"
        };

        int[] output = {
                15, 12, 28, 11, 16, 0, -1, 9, 18, 32, 36, 12, 5
        };

        for (int i = 0; i < 13; i++)
        {
            int result = find_max_sum_of_subrect(input[i]);

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