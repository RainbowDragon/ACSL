/**
 *      ACSL 2022 Final - Problem 1 - Waves - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class Waves {

    /*
     * Complete the 'createWave' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. INTEGER waveLength
     * 2. STRING numbers
     */
    static String createWave (int waveLength, String numbers) {

        ArrayList<Integer> numberList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(numbers);
        while (st.hasMoreTokens())
        {
            numberList.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        int dir = 1;
        for (int k = waveLength; k > 0; k--)
        {
            int[] waves = new int[k];
            for (int j = 0; j < k; j++)
            {
                waves[j] = numberList.get(index);
                index = (index + 1) % numberList.size();
            }

            Arrays.sort(waves);

            for (int j = 0; j < k; j++)
            {
                if (sb.length() > 0) {
                    sb.append(" ");
                }

                if (dir > 0) {
                    sb.append(waves[j]);
                }
                else {
                    sb.append(waves[k-j-1]);
                }
            }

            dir *= -1;
        }

        if (index > 0) {
            int k = numberList.size() - index;

            int[] waves = new int[k];
            for (int j = 0; j < k; j++)
            {
                waves[j] = numberList.get(index);
                index++;
            }

            Arrays.sort(waves);

            for (int j = 0; j < k; j++)
            {
                sb.append(" ");

                if (dir > 0) {
                    sb.append(waves[j]);
                } else {
                    sb.append(waves[k - j - 1]);
                }
            }
        }

        return sb.toString();
    }

    public static void main (String [] args) {

        String[][] input = {
                {"6", "3 14 1 59 26 535 8 97 932 38 462 64 3 3 83 279 50 288 4 19 716 939 9 37510"},
                {"5", "3 14 1 59 26 535 8 97 932 38 462 64 3 3 83 279 50 288 4 19 716 939 9 37510"},
                {"8", "3 14 1 59 26 535 8 97 932 38 462 64 3 3 83 279 50 288 4 19 716 939 9 37510"},
                {"6", "3 1 4 1 5 9 2 6"},
                {"9", "3 141 5926 535 89 72 3 846 26 43 383 27"},
                {"3", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"},
                {"5", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"},
                {"7", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"},
                {"9", "2 718 2 8 18 28 45 90 452 3 5 3 6028 74 7 135 27"},
                {"4", "1 2 3 4"},
                {"5", "1 2"},
                {"5", "1"},
                {"3", "1 2 3 4 5 6"},
                {"1", "1 2 3 4 5"},
                {"1", "1"}
        };

        String[] output = {
                "1 3 14 26 59 535 932 462 97 38 8 3 3 64 83 288 279 50 4 19 716 9 939 37510",
                "1 3 14 26 59 932 535 97 8 38 64 462 3 3 83 37510 939 716 288 279 50 19 9 4",
                "1 3 8 14 26 59 97 535 932 462 83 64 38 3 3 4 19 50 279 288 716 37510 939 14 9 3 1 26 59 535 932 97 8 38 462 64 3 3 4 9 19 50 83 279 288 716 939 37510",
                "1 1 3 4 5 9 6 4 3 2 1 1 2 5 9 6 3 1 1 4 5 2 6 9",
                "3 3 26 72 89 141 535 846 5926 5926 535 383 141 89 43 27 3 3 26 27 43 72 383 846 5926 535 141 89 72 3 3 26 43 383 846 5926 141 27 3 72 89 535 846 3 26 383 43 27",
                "2 2 718 18 8 28 6028 452 135 90 74 45 27 7 5 3 3",
                "2 2 8 18 718 452 90 45 28 3 3 5 6028 74 7 135 27",
                "2 2 8 18 28 45 718 6028 452 90 5 3 3 2 7 27 74 135 718 18 8 2 28 45 90 452 3 5 6028 135 74 27 7 3",
                "2 2 8 18 28 45 90 452 718 6028 135 74 27 7 5 3 3 2 2 8 18 28 45 718 6028 452 90 5 3 3 2 7 27 74 135 718 18 8 2 28 45 90 452 3 5 6028 135 74 27 7 3",
                "1 2 3 4 3 2 1 1 4 2 3 4",
                "1 1 1 2 2 2 2 1 1 1 2 2 2 1 1 2",
                "1 1 1 1 1 1 1 1 1 1 1 1 1 1 1",
                "1 2 3 5 4 6",
                "1 5 4 3 2",
                "1"
        };

        for (int i = 0; i < 15; i++)
        {
            String result = createWave(Integer.parseInt(input[i][0]), input[i][1]);

            if (output[i].equals(result)) {
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