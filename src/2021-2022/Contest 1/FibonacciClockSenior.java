/**
 *      ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciClockSenior {

    static String findTime (char c1, char c2, char c3, char c4, char c5) {

        char[] colors = {c1, c2, c3, c4, c5};
        int[] deltas = {1, 1, 2, 3, 5};

        int hour = 0;
        int minute = 0;
        int second = 0;

        for (int i = 0; i < 5; i++)
        {
            if (colors[i] == 'R') {
                hour += deltas[i];
            }
            else if (colors[i] == 'G') {
                minute += deltas[i];
            }
            else if (colors[i] == 'B') {
                second += deltas[i];
            }
            else if (colors[i] == 'Y') {
                hour += deltas[i];
                minute += deltas[i];
            }
            else if (colors[i] == 'M') {
                hour += deltas[i];
                second += deltas[i];
            }
            else if (colors[i] == 'C') {
                minute += deltas[i];
                second += deltas[i];
            }
        }
        minute *= 5;
        second *= 5;

        if (second >= 60) {
            minute += second / 60;
            second %= 60;
        }

        if (minute >= 60) {
            hour += minute / 60;
            minute %= 60;
        }
        hour %= 12;

        String strHour = Integer.toString(hour);
        String strMinute = Integer.toString(minute);
        String strSecond = Integer.toString(second);

        if (hour < 10) {
            strHour = "0" + strHour;
        }

        if (minute < 10) {
            strMinute = "0" + strMinute;
        }

        if (second < 10) {
            strSecond = "0" + strSecond;
        }

        return strHour + ":" + strMinute + ":" + strSecond;
    }

    public static void main (String [] args) {

        char[][] input = new char[10][];
        input[0] = new char[]{'R', 'W', 'G', 'B', 'G'};
        input[1] = new char[]{'R', 'C', 'M', 'G', 'B'};
        input[2] = new char[]{'B', 'Y', 'Y', 'G', 'R'};
        input[3] = new char[]{'M', 'R', 'G', 'B', 'W'};
        input[4] = new char[]{'Y', 'Y', 'Y', 'Y', 'Y'};
        input[5] = new char[]{'W', 'G', 'B', 'G', 'R'};
        input[6] = new char[]{'C', 'G', 'R', 'M', 'Y'};
        input[7] = new char[]{'M', 'M', 'M', 'M', 'C'};
        input[8] = new char[]{'C', 'C', 'C', 'Y', 'Y'};
        input[9] = new char[]{'W', 'W', 'W', 'W', 'W'};

        String[] output = {"01:35:15", "03:20:40", "08:30:05", "02:10:20", "01:00:00", "05:20:10", "10:35:20", "07:26:00", "09:00:20", "00:00:00"};

        for (int i = 0; i < 10; i++)
        {
            String result = findTime(input[i][0], input[i][1], input[i][2], input[i][3], input[i][4]);

            if (output[i].equals(result)) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
            }
        }
    }
}