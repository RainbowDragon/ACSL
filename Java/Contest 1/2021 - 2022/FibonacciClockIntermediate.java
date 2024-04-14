/**
 *      ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciClockIntermediate {

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
            if (colors[i] == 'R') {
                hour += deltas[i];
            }
            else if (colors[i] == 'G') {
                minute += deltas[i];
            }
            else if (colors[i] == 'B') {
                hour += deltas[i];
                minute += deltas[i];
            }
        }
        minute *= 5;

        if (minute >= 60) {
            hour += minute / 60;
            minute %= 60;
        }
        hour %= 12;

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

    public static void main (String [] args) {

        char[][] input = {
                {'R', 'W', 'G', 'B', 'G'},
                {'W', 'B', 'B', 'G', 'R'},
                {'W', 'G', 'B', 'R', 'B'},
                {'G', 'G', 'B', 'B', 'B'},
                {'W', 'R', 'G', 'G', 'G'},
                {'G', 'R', 'W', 'B', 'B'},
                {'G', 'G', 'B', 'G', 'B'},
                {'R', 'R', 'R', 'R', 'R'},
                {'G', 'G', 'G', 'G', 'G'},
                {'W', 'B', 'B', 'B', 'B'}
        };

        String[] output = {
                "04:50", "08:30", "10:40", "11:00", "01:50",
                "09:45", "08:00", "00:00", "01:00", "11:55"
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