/**
 *      ACSL 2020-2021 - Contest 2 - Lex Strings - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class LexStringsIntermediate {

    /*
     * Complete the 'rearrangeString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER n
     */
    static String rearrangeString (String s, int n) {

        ArrayList<Block> blocks = new ArrayList<>();
        char lastChar = ' ';
        int count = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char curChar = s.charAt(i);
            if (curChar == lastChar) {
                count++;
            }
            else {
                count = 1;
            }

            if (i == s.length()-1 || curChar != s.charAt(i+1)) {
                blocks.add(new Block(curChar, count));
            }

            lastChar = s.charAt(i);
        }

        Collections.sort(blocks);

        StringBuilder sb = new StringBuilder();
        lastChar = ' ';
        int maxLoop = n;
        for (Block block : blocks)
        {
            char curChar = block.ch;
            if (curChar != lastChar) {
                maxLoop = n;
            }
            int loop = Math.min(maxLoop, block.count);
            for (int j = 0; j < loop; j++) {
                sb.append(curChar);
            }
            lastChar = curChar;
            maxLoop -= loop;
        }

        return sb.toString();
    }

    static class Block implements Comparable<Block> {
        char ch;
        int count;

        public Block (char ch, int count) {
            this.ch = ch;
            this.count = count;
        }

        @Override
        public int compareTo(Block other) {
            if (this.count == other.count) {
                return Character.compare(this.ch, other.ch);
            }
            else {
                return other.count - this.count;
            }
        }
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"MBAMMDXXMMMGGMMZ", "3"};
        input[1] = new String[]{"MHHHHJLDDHHDDD", "3"};
        input[2] = new String[]{"THETENNESSEEVOLUNTEERS", "2"};
        input[3] = new String[]{"MISSISSIPPI", "3"};
        input[4] = new String[]{"BOOOKEEEPEEERR", "4"};
        input[5] = new String[]{"BOOOKEEEPEERBBBBUZZZOOKEEEEPEER", "2"};
        input[6] = new String[]{"MASSACHUSETTSVSMISSISSIPPI", "2"};
        input[7] = new String[]{"OOOOZESSPPOOOOOYYYSSSUPY", "4"};
        input[8] = new String[]{"SHESELLSSEASHELLSANDBALLOONS", "3"};
        input[9] = new String[]{"HHHGGRDDCFFFGGGTTTYUIKJHHH", "1"};

        String[] output = {
                "MMMGGMMMXXABDMZ",
                "HHHDDDHHJLM",
                "EENNSSEEHLNORSTTUV",
                "PPSSSIIIM",
                "EEEEOOORRBKP",
                "BBEEOOZZEEOOBKKPPRRU",
                "PPSSTTAACEHIIMMSSUV",
                "OOOOSSSYYYPPSSEPUYZ",
                "LLLOOSSAAABDEEEHHNNSSS",
                "FGHTDGCIJKRUY"};

        for (int i = 0; i < 10; i++)
        {
            String result = rearrangeString(input[i][0], Integer.parseInt(input[i][1]));

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