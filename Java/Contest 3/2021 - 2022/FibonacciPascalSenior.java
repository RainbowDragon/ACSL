/**
 *      ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciPascalSenior {

    /*
     * Complete the 'countUniqueValues' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER fibNumber as parameter.
     */
    static int countUniqueValues (int fibNumber) {

        int index = getFibonacciIndex(fibNumber);

        HashMap<Long, Integer> countMap = new HashMap<>();
        for (int k = 1; k <= index; k++)
        {
            int i = 0;
            int n = k;
            long current = 1;
            if (countMap.containsKey(current)) {
                countMap.put(current, countMap.get(current)+1);
            }
            else {
                countMap.put(current, 1);
            }

            while (i + 1 < n) {
                current = (current * (n - i) * (n - i - 1)) / n / (i + 1);
                n--;
                i++;
                if (countMap.containsKey(current)) {
                    countMap.put(current, countMap.get(current)+1);
                }
                else {
                    countMap.put(current, 1);
                }
            }
        }

        int result = 0;
        for (long key : countMap.keySet())
        {
            if (countMap.get(key) == 1) {
                result++;
            }
        }

        return result;
    }

    static int getFibonacciIndex (int fibNumber) {

        int index = 1;
        int first = 1;
        int second = 1;

        while (second < fibNumber) {
            int temp = first;
            first = second;
            second += temp;
            index++;
        }

        return index;
    }

    public static void main (String [] args) {

        int[] input = {8, 89, 610, 10946, 317811, 55, 1597, 832040, 9227465, 1836311903};

        int[] output = {2, 8, 16, 31, 58, 6, 21, 67, 96, 171};

        for (int i = 0; i < 10; i++)
        {
            int result = countUniqueValues(input[i]);

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