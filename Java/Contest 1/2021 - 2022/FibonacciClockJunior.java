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

        int hour = 0;
        int minute = 0;

        hour += addHour(c1, 1);
        hour += addHour(c2, 1);
        hour += addHour(c3, 2);
        hour += addHour(c4, 3);
        hour += addHour(c5, 5);

        minute += addMinute(c1, 1);
        minute += addMinute(c2, 1);
        minute += addMinute(c3, 2);
        minute += addMinute(c4, 3);
        minute += addMinute(c5, 5);
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

        char[][] input = new char[10][];
        input[0] = new char[]{'R', 'W', 'G', 'B', 'G'};
        input[1] = new char[]{'W', 'B', 'B', 'G', 'R'};
        input[2] = new char[]{'B', 'G', 'B', 'B', 'R'};
        input[3] = new char[]{'W', 'W', 'W', 'B', 'B'};
        input[4] = new char[]{'W', 'R', 'G', 'G', 'G'};
        input[5] = new char[]{'G', 'R', 'W', 'B', 'B'};
        input[6] = new char[]{'R', 'B', 'B', 'W', 'G'};
        input[7] = new char[]{'W', 'W', 'W', 'W', 'W'};
        input[8] = new char[]{'B', 'W', 'W', 'G', 'R'};
        input[9] = new char[]{'W', 'B', 'B', 'B', 'B'};

        String[] output = {"04:50", "08:30", "11:35", "08:40", "01:50", "09:45", "04:40", "00:00", "06:20", "11:55"};

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