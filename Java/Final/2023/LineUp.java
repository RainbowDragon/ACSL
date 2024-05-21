/**
 *      ACSL 2023 Final - Problem 1 - Line Up - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class LineUp {

    /*
     * Complete the 'changedPositions' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING customers as parameter.
     */
    static int changedPositions (String customers) {

        ArrayList<Integer> line = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(customers);
        while (st.hasMoreTokens())
        {
            line.add(Integer.parseInt(st.nextToken()));
        }

        int n = line.size();
        int result = 0;

        for (int i = 1; i <= n; i++)
        {
            int start = i - 1;
            int index = start;
            while (index < n && line.get(index) != i)
            {
                index++;
            }
            int count = index - start + 1;
            if (count % 2 == 1) {
                count--;
            }
            result += count;
            while (start < index)
            {
                int temp = line.get(start);
                line.set(start, line.get(index));
                line.set(index, temp);
                start++;
                index--;
            }
        }

        return result;
    }

    public static void main (String [] args) {

        String[] input = {
                "4 6 2 3 1 7 5",
                "6 10 3 7 5 2 1 8 9 4",
                "9 5 1 7 10 4 6 8 3 11 2 12",
                "1 2 3 4 5 6 7 8 9",
                "13 12 11 10 9 8 7 6 5 4 3 2 1",
                "1 2 3 4 5 6 7 8",
                "15 14 13 12 11 10 9 8 7 6 5 4 3 2 1",
                "7 5 2 9 12 4 8 6 3 10 1 11",
                "2 5 11 7 12 9 10 8 1 6 3 4",
                "1 4 3 2 5 7 6",
                "6 7 8 1 2 10 3 9 5 4",
                "18 9 14 2 1 12 19 20 15 4 7 6 8 13 10 16 17 3 11 5",
                "7 3 1 5 2 6 4 8 9",
                "11 8 6 3 12 7 5 18 14 19 1 13 10 20 2 16 4 9 15 17",
                "1 3 5 7 9 11 13 15 17 19 20 18 16 14 12 10 8 6 4 2"
        };

        int[] output = {
                12, 26, 38, 0, 12, 0, 14, 44, 50, 4, 24, 128, 14, 118, 180
        };

        for (int i = 0; i < 15; i++)
        {
            int result = changedPositions(input[i]);

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