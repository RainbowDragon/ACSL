/**
 *      ACSL 2019-2020 - Contest 2 - String Differences - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class StringDifferencesSenior {

    static int differenceFactor (String s1, String s2) {

        s1 = getUpperCaseString(s1);
        s2 = getUpperCaseString(s2);

        return getDifferenceFactor(s1, s2);
    }

    static int getDifferenceFactor (String s1, String s2) {

        String common = getLongestCommonSubstring(s1, s2);
        int len = common.length();

        if (len == 0) {
            return 0;
        }
        else {
            int i1 = s1.indexOf(common);
            int i2 = s2.indexOf(common);

            int leftCount = getDifferenceFactor(s1.substring(0, i1), s2.substring(0, i2));
            int rightCount = getDifferenceFactor(s1.substring(i1+len), s2.substring(i2+len));

            return leftCount + len + rightCount;
        }
    }

    static String getLongestCommonSubstring (String s1, String s2) {

        String result = "";

        for (int i = 0; i < s1.length(); i++)
            for (int j = i+1; j <= s1.length(); j++)
            {
                String sub = s1.substring(i, j);
                if (s2.contains(sub)) {
                    if (sub.length() > result.length()) {
                        result = sub;
                    }
                    else if (sub.length() == result.length() && sub.compareTo(result) < 0) {
                        result = sub;
                    }
                }
            }

        return result;
    }

    static String getUpperCaseString (String s) {

        StringBuilder sb = new StringBuilder();

        s = s.toUpperCase();
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main (String [] args) {

        String[][] input = {
                {
                        "I am going home now",
                        "I will go home now"
                },
                {
                        "The big black bear bit a big black bug",
                        "The big black bug bled black blood"
                },
                {
                        "Complementary angle measures sum to 90 degrees.",
                        "The measures of supplementary angles add to 180 degrees."
                },
                {
                        "A Tale of Two Cities was published by Dickens in 1859.",
                        "In 1839, Charles Dickens published Nicholas Nickleby."
                },
                {
                        "Connecticut is The Constitution State.",
                        "Hartford is the capital of Connecticut."
                },
                {
                        "To be or not to be, that is the question.",
                        "To err is human; to really foul things up requires a computer."
                },
                {
                        "The Pythagorean Theorem says that the sum of the squares of the two legs equals the square of the hypotenuse.",
                        "To find a leg using the Pythagorean Theorem, take the square root of the hypotenuse squared minus the other leg squared."
                },
                {
                        "Uncle Tom's Cabin was published by Harriet Beecher Stowe in 1852.",
                        "In 1876, Mark Twain published The Adventures of Tom Sawyer."
                },
                {
                        "Once upon a midnight dreary while I pondered weak and weary,",
                        "Over many a quaint and curious volume of forgotten lore."
                },
                {
                        "A tutor who tooted the flute tried to tutor two tooters to toot!",
                        "Is it harder to toot or to tutor two tooters to toot?"
                }
        };

        int[] output = {
                10, 19, 26, 18, 11, 14, 50, 20, 9, 31
        };

        for (int i = 0; i < 10; i++)
        {
            int result = differenceFactor(input[i][0], input[i][1]);

            if (output[i] == result) {
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