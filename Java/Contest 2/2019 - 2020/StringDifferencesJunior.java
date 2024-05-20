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

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int len = Math.min(s1.length(), s2.length());
        for (int i = 0; i < len; i++)
        {
            if (s1.charAt(i) != s2.charAt(i)) {
                sb1.append(s1.charAt(i));
                sb2.append(s2.charAt(i));
            }
        }
        s1 = sb1 + s1.substring(len);
        s2 = sb2 + s2.substring(len);

        sb1.setLength(0);
        sb2.setLength(0);
        int l1 = s1.length();
        int l2 = s2.length();
        len = Math.min(l1, l2);
        for (int i = 1; i <= len; i++)
        {
            if (s1.charAt(l1-i) != s2.charAt(l2-i)) {
                sb1.append(s1.charAt(l1-i));
                sb2.append(s2.charAt(l2-i));
            }
        }
        s1 = s1.substring(0, l1-len) + sb1.reverse();
        s2 = s2.substring(0, l2-len) + sb2.reverse();

        String result;
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

        StringBuilder sb = new StringBuilder();

        char last = ' ';
        for (int i = 0; i < s.length(); i++)
        {
            char current = s.charAt(i);
            if (last != current) {
                sb.append(current);
                last = current;
            }
        }

        return sb.toString();
    }

    static String deleteVowels (String s) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++)
        {
            char current = s.charAt(i);
            if (i == 0 || (current != 'A' && current != 'E' && current != 'I' && current != 'O' && current != 'U')) {
                sb.append(current);
            }
        }

        return sb.toString();
    }

    public static void main (String [] args) {

        String[][] input = {
                {"MISSISSIPPI", "MISSOURI"},
                {"CATHERINE", "KATHERYNE"},
                {"CONSTITUTIONAL", "CONVENTIONAL"},
                {"COMPARTMENTALIZATION", "SEMIAUTOBIOGRAPHICAL"},
                {"PHYSICIAN", "PHARMACY"},
                {"FEEFIFOFUM", "FIDDLEDEEDEE"},
                {"MYLOLLIPOPS", "MYLARBALLOONS"},
                {"CONNECTICUTCT", "CONSTITUTIONSTATE"},
                {"MASSACHUSETTSBAYCOLONY", "MINUTEMANNATIONALHISTORICALPARK"},
                {"AMERICANCOMPUTERSCIENCELEAGUE", "NATIONALACADEMICGAMESLEAGUE"}
        };

        String[] output = {
                "R", "C", "VN", "SBGRPHCL", "RMY", "DLDD", "LPP", "CCC", "SCSBYCLNY", "NTNLCDGM"
        };

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