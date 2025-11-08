/**
 *      ACSL 2024-2025 - Contest 1 - Rings - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class RingsJunior {

    /*
     * Complete the 'scoreTosses' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING tosses1
     *  2. STRING tosses2
     */
    static String scoreTosses (String tosses1, String tosses2) {

        int score1 = getScore(tosses1);
        int score2 = getScore(tosses2);

        String result = "";

        if (score1 >= score2) {
            result = score1 + " " + score2;
        }
        else {
            result = score2 + " " + score1;
        }

        return result;
    }

    static int getScore (char toss) {

        int score = 0;

        if (toss == 'A' || toss == 'R') {
            score = 1;
        }
        else if (toss == 'O' || toss == 'G') {
            score = 3;
        }
        else if (toss == 'B') {
            score = 6;
        }

        return score;
    }

    static int getScore (String tosses) {

        int score = 0;

        for (int i = 0; i < tosses.length(); i++)
        {
            score += getScore(tosses.charAt(i));
        }

        return score;
    }

    public static void main (String [] args) {

        String[][] input = {
                {"BRAG", "BOB"},
                {"AABBOOGG", "BAROBA"},
                {"GBORABORBA", "BAAAAOORGGGB"},
                {"OORRRGRBBRRAAABB", "AAAAGRRRRGRRRBBOO"},
                {"BARROGGBBGO", "BBAAAARRRGGGOOOO"},
                {"AAAABBBOOGGGRRR", "RRBBOOOGGGAAAA"},
                {"AROBG", "BRAGGROG"},
                {"BOBBRAGROB", "BARBAGGRAB"},
                {"BBBAAAOOOGGGRRR", "BBBBBAAAAGGGOOR"},
                {"RRRRRRBBBBGGGGGGAAAO", "AAAAAAOOOOOORRRBBBBB"},
                {"BABAGORBABAGORBABAGORBABAGOR", "GORBAGORBAGORBAGORBAGORBAGORBA"},
                {"RRRGGGGBBBBAAOOOBBBAAARRBB", "BBBBAAAAOOOOBBBGGGRRRGOAGOAGO"}
        };

        String[] output = {
                "15 11", "26 18", "32 31", "42 35", "40 36", "40 36",
                "21 14", "36 29", "50 42", "57 54", "84 84", "90 85"
        };

        for (int i = 0; i < 12; i++)
        {
            String result = scoreTosses(input[i][0], input[i][1]);

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