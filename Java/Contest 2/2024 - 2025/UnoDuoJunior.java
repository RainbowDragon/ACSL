/**
 *      ACSL 2024-2025 - Contest 2 - Uno Duo - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.regex.Pattern;

public class UnoDuoJunior {

    /*
     * Complete the 'printPile' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING pile
     *  2. STRING hand
     */
    static String printPile (String pile, String hand) {

        String[] hands = hand.split(" ");
        ArrayList<String> handList = new ArrayList<>(Arrays.asList(hands));
        ArrayList<String> result = new ArrayList<>();
        result.add(pile);

        boolean isDone = false;

        while (!isDone) {

            String top = result.get(0);

            boolean found = false;

            for (int i = 0; i < handList.size(); i++)
            {
                String current = handList.get(i);
                if (isMatched(top, current)) {
                    found = true;
                    result.add(0, current);
                    handList.remove(current);
                    break;
                }
            }

            isDone = !found;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = result.size()-1; i >= 0; i--)
        {
            if (i != result.size()-1) {
                sb.append(" ");
            }
            sb.append(result.get(i));
        }

        return sb.toString();
    }

    static boolean isMatched (String card1, String card2) {

        int score = 0;

        for (int i = 0; i < 3; i++)
        {
            if (card1.charAt(i) == card2.charAt(i)) {
                score += 1;
            }
        }

        return score >= 2;
    }

    public static void main (String [] args) {

        String[][] input = {
                { "B3O", "R3X B1T B2T R1X B3X B1X Y4O" },
                { "R4S", "R4X B4X B3X B3T G3T G1T R1O" },
                { "B1T", "G1O B1O R1T B4T G4O R4O R1X" },
                { "G3S", "R3S G1O B4X G2S Y4S G3T G3X" },
                { "Y4O", "Y1S G4S B4S G2S R1T G2X Y2T" },
                { "G2T", "Y1S G4S B4S G2S R1T G2X Y2T" },
                { "Y2S", "B2S Y1X G2S R4S G1O Y4T G2T" },
                { "R1S", "G4S B1T G1T B4S Y2X Y1S Y1T" },
                { "R2X", "Y2T Y1T G2T B4O G1X R4X B3S" },
                { "G1O", "R1O B3S G1X R3O R4O R4T B3X" },
                { "R1S", "G1S B1T G1T Y4S Y2S Y1S Y1T" },
                { "Y4O", "G1X Y1O Y1X R2X B4S B4O R1X" }
        };

        String[] output = {
                "B3O B3X R3X R1X B1X B1T B2T",
                "R4S R4X B4X B3X B3T G3T G1T",
                "B1T B1O G1O G4O R4O",
                "G3S R3S",
                "Y4O",
                "G2T G2S G4S B4S",
                "Y2S B2S G2S G2T",
                "R1S Y1S Y1T B1T G1T",
                "R2X R4X",
                "G1O R1O R3O R4O R4T",
                "R1S G1S G1T B1T Y1T Y1S Y4S Y2S",
                "Y4O Y1O Y1X G1X R1X R2X"
        };

        for (int i = 0; i < 12; i++)
        {
            String result = printPile(input[i][0], input[i][1]);

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