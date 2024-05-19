/**
 *      ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciClockJunior {

    /*
     * Complete the 'findTime' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. CHARACTER c1
     * 2. CHARACTER c2
     * 3. CHARACTER c3
     * 4. CHARACTER c4
     * 5. CHARACTER c5
     */
    static String findTime (char c1, char c2, char c3, char c4, char c5) {

        char[] colors = {c1, c2, c3, c4, c5};
        int[] deltas = {1, 1, 2, 3, 5};

        int hour = 0;
        int minute = 0;

        for (int i = 0; i < 5; i++)
        {
            hour += addHour(colors[i], deltas[i]);
            minute += addMinute(colors[i], deltas[i]);
        }
        minute *= 5;

        String strHour = Integer.toString(hour);
        String strMinute = Integer.toString(minute);

        if (hour < 10) {
            strHour = "0" + strHour;
        }

        if (minute < 10) {
            strMinute = "0" + strMinute;
        }

        return strHour + ":" + strMinute;
    }

    static int addHour (char color, int delta) {

        if (color == 'R' || color == 'B') {
            return delta;
        }
        return 0;
    }

    static int addMinute (char color, int delta) {

        if (color == 'G' || color == 'B') {
            return delta;
        }
        return 0;
    }

    public static void main (String [] args) {

        char[][] input = {
                {'R', 'W', 'G', 'B', 'G'},
                {'W', 'B', 'B', 'G', 'R'},
                {'B', 'G', 'B', 'B', 'R'},
                {'W', 'W', 'W', 'B', 'B'},
                {'W', 'R', 'G', 'G', 'G'},
                {'G', 'R', 'W', 'B', 'B'},
                {'R', 'B', 'B', 'W', 'G'},
                {'W', 'W', 'W', 'W', 'W'},
                {'B', 'W', 'W', 'G', 'R'},
                {'W', 'B', 'B', 'B', 'B'}
        };

        String[] output = {
                "04:50", "08:30", "11:35", "08:40", "01:50",
                "09:45", "04:40", "00:00", "06:20", "11:55"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = findTime(input[i][0], input[i][1], input[i][2], input[i][3], input[i][4]);

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