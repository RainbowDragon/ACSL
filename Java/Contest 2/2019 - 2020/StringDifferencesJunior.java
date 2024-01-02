/**
 *      ACSL 2019-2020 - Contest 2 - String Differences - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class StringDifferencesJunior {

    static String stringDifferences (String s1, String s2) {

        s1 = deleteDouble(s1);
        s2 = deleteDouble(s2);

        s1 = deleteVowels(s1);
        s2 = deleteVowels(s2);

        String n1 = "";
        String n2 = "";
        int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; i++)
        {
            if (s1.charAt(i) != s2.charAt(i)) {
                n1 += s1.charAt(i);
                n2 += s2.charAt(i);
            }
        }
        s1 = n1 + s1.substring(len);
        s2 = n2 + s2.substring(len);

        n1 = "";
        n2 = "";
        int l1 = s1.length();
        int l2 = s2.length();
        len = Math.min(l1, l2);
        for (int i = 1; i <= len; i++)
        {
            if (s1.charAt(l1-i) != s2.charAt(l2-i)) {
                n1 = s1.charAt(l1-i) + n1;
                n2 = s2.charAt(l2-i) + n2;
            }
        }
        s1 = s1.substring(0, l1-len) + n1;
        s2 = s2.substring(0, l2-len) + n2;

        String result = "";
        if (s1.length() < s2.length()) {
            result = s1;
        }
        else if (s1.length() > s2.length()) {
            result = s2;
        }
        else {
            result = s1.compareTo(s2) < 0 ? s1 : s2;
        }

        return result;
    }

    static String deleteDouble (String s) {

        String result = "";

        char last = ' ';
        for (int i = 0; i < s.length(); i++)
        {
            char current = s.charAt(i);
            if (last != current) {
                result += current;
                last = current;
            }
        }

        return result;
    }

    static String deleteVowels (String s) {

        String result = "";

        for (int i = 0; i < s.length(); i++)
        {
            char current = s.charAt(i);
            if (i == 0 || (current != 'A' && current != 'E' && current != 'I' && current != 'O' && current != 'U')) {
                result += current;
            }
        }

        return result;
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"MISSISSIPPI", "MISSOURI"};
        input[1] = new String[]{"CATHERINE", "KATHERYNE"};
        input[2] = new String[]{"CONSTITUTIONAL", "CONVENTIONAL"};
        input[3] = new String[]{"COMPARTMENTALIZATION", "SEMIAUTOBIOGRAPHICAL"};
        input[4] = new String[]{"PHYSICIAN", "PHARMACY"};
        input[5] = new String[]{"FEEFIFOFUM", "FIDDLEDEEDEE"};
        input[6] = new String[]{"MYLOLLIPOPS", "MYLARBALLOONS"};
        input[7] = new String[]{"CONNECTICUTCT", "CONSTITUTIONSTATE"};
        input[8] = new String[]{"MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"};
        input[9] = new String[]{"AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"};

        String[] output = {"R", "C", "VN", "SBGRPHCL", "RMY", "DLDD", "LPP", "CCC", "SCSBYCLNY", "NTNLCDGM"};

        for (int i = 0; i < 10; i++)
        {
            String result = stringDifferences(input[i][0], input[i][1]);

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