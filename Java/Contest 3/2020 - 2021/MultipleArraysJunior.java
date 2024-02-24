/**
 *      ACSL 2020-2021 - Contest 3 - MultipleArrays - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class MultipleArraysJunior {

    /*
     * Complete the 'sumOfLargest' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING list1
     *  2. STRING list2
     *  3. STRING list3
     */
    static int sumOfLargest (String list1, String list2, String list3) {

        ArrayList<Integer>[] als = new ArrayList[3];
        als[0] = parseArray(list1);
        als[1] = parseArray(list2);
        als[2] = parseArray(list3);

        int maxIndex = 0;
        for (int i = 0; i < 3; i++)
        {
            if (als[i].size() > als[maxIndex].size()) {
                maxIndex = i;
            }
        }

        int maxLength = als[maxIndex].size();
        int result = 0;
        for (int i = 0; i < maxLength; i++)
        {
            int curMax = als[maxIndex].get(i);
            for (int j = 0; j < 3; j++)
            {
                if (i < als[j].size()) {
                    curMax = Math.max(curMax, als[j].get(i));
                }
            }

            result += curMax;
        }

        return result;
    }

    static ArrayList<Integer> parseArray (String list) {

        ArrayList<Integer> al = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(list);
        while (st.hasMoreTokens())
        {
            al.add(Integer.parseInt(st.nextToken()));
        }

        return al;
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"6 8 1 5 2 3 5 3 7 9", "7 6 2 9", "9 3 4 6 1 8 6 4 2 8 4"};
        input[1] = new String[]{"1 3 5 7 9 2 4 6 8 10", "5 2 6 4 8 7 9 11 14 12", "4 2 6 4 7 1 9 22 21 9"};
        input[2] = new String[]{"5 6 7 8 9 1 2", "9 8 7 6 5 0 1 2 3 4", "8 6 4 2 1 3 5 7"};
        input[3] = new String[]{"1", "1 2", "1 2 3"};
        input[4] = new String[]{"31 41 59 26 53 58 97 93 23 84 62 64 33 83 27", "56 89 23 14 26 37 48 59 61 72 83 94", "42 35 68 79 82 91 20 51 64 97 86"};
        input[5] = new String[]{"3 1 4 1 5 9 2 6 5 3 5 8 9 7", "9 3 2 3 8 4 6 2 7 9 8 5 3 5 6 2 9 5 1 4 1 3", "6 2 8 3 1 8 5 3 0 6"};
        input[6] = new String[]{"31 41 59 26 53 58 97 93 23 84 62 64 33 83 27", "21 32 43 54 65 76 87 98 90 70 50 30 10", "20 40 60 80 12 23 34 45 56 67 78 89"};
        input[7] = new String[]{"8765 4321 9012 3456 7890 321 654 987", "9123 5326 8975 345 789", "7654 6235 5798 6543 4567 32 54 1024 2048 4096"};
        input[8] = new String[]{"-5 -6 -7 -8 -9 -10 -11", "-1 -2 -3 -4 -12 -8 -10 -16 -14 -12 -10 -5", "-6 -9 -1 -2 -10 -7 -9 -21 -15 -10"};
        input[9] = new String[]{"1", "1", "1"};

        int[] output = {70, 101, 63, 6, 1139, 131, 1032, 46946, -86, 1};

        for (int i = 0; i < 10; i++)
        {
            int result = sumOfLargest(input[i][0], input[i][1], input[i][2]);

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