/**
 *      ACSL 2019-2020 - Contest 4 - Patolli - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class PatolliJunior {

    static String getFinalLocation (String str) {

        StringTokenizer st = new StringTokenizer(str);
        HashSet<Integer> markers = new HashSet<>();
        for (int i = 0; i < 3; i++)
        {
            markers.add(Integer.parseInt(st.nextToken()));
        }

        int current = Integer.parseInt(st.nextToken());
        int numberOfRolls = Integer.parseInt(st.nextToken());
        int[] rolls = new int[numberOfRolls];
        for (int i = 0; i < numberOfRolls; i++)
        {
            rolls[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < numberOfRolls; i++)
        {
            current = move(current, rolls[i], markers);
        }

        if (current == 52) {
            return "GAME OVER";
        }
        else {
            return "" + current;
        }
    }

    static int move (int current, int roll, HashSet<Integer> markers) {

        int next = current + roll;

        if (current == 52 || next > 52 || markers.contains(next)) {
            next = current;
        }
        else if (isPrime(next)) {
            for (int i = 0; i < 6; i++)
            {
                if (markers.contains(next+1)) {
                    break;
                }
                else {
                    next++;
                }
            }
        }
        else if (isPerfectSquare(next)) {
            for (int i = 0; i < 6; i++)
            {
                if (markers.contains(next-1)) {
                    break;
                }
                else {
                    next--;
                }
            }
        }
        else if (willMoveHorizontalAndVertical(current, next)) {
            for (int i = next; i >= current; i--)
            {
                next = i;
                if (i % roll == 0 && !markers.contains(i)) {
                    break;
                }
            }
        }

        return next;
    }

    static boolean isPrime (int number) {

        for (int i = 2; i < number; i++)
        {
            if (number % i == 0) {
                return false;
            }
        }

        return number != 1;
    }

    static boolean isPerfectSquare (int number) {

        for (int i = 1; i < number; i++)
        {
            if (i * i == number) {
                return true;
            }
        }

        return false;
    }

    static int[] startOfTurn = {6, 11, 16, 21, 26, 34, 39, 44, 49};

    static boolean willMoveHorizontalAndVertical (int current, int next) {

        for (int start : startOfTurn)
        {
            if (current <= start && next >= start + 2) {
                return true;
            }
        }

        return false;
    }

    public static void main (String [] args) {

        String[] input = {
                "4 6 8 1 5 6 3 5 1 1",
                "10 24 32 8 4 4 4 3 5",
                "10 22 32 8 7 4 4 3 5 5 5 6",
                "17 20 27 16 7 3 5 4 6 5 1 4",
                "43 46 50 40 5 3 1 2 4 4",
                "25 27 49 22 7 2 2 6 6 5 3 6",
                "50 41 38 45 9 4 2 5 3 1 6 4 3 1",
                "21 26 30 19 6 6 4 6 1 2 3",
                "5 14 18 2 7 2 5 4 5 2 1 6",
                "10 17 20 9 12 4 5 3 1 6 2 3 3 5 4 1 6"
        };

        String[] output = {"17", "23", "33", "34", "GAME OVER", "42", "GAME OVER", "27", "15", "48"};

        for (int i = 0; i < 10; i++)
        {
            String result = getFinalLocation(input[i]);

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