/**
 *      ACSL 2019-2020 - Contest 4 - Patolli - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class PatolliSenior {

    static String getFinalLocation (String str) {

        StringTokenizer st = new StringTokenizer(str);
        int[] player1 = new int[3];
        for (int i = 0; i < 3; i++)
        {
            player1[i] = Integer.parseInt(st.nextToken());
        }
        int[] player2 = new int[3];
        for (int i = 0; i < 3; i++)
        {
            player2[i] = Integer.parseInt(st.nextToken());
        }

        int numberOfRolls = Integer.parseInt(st.nextToken());
        int[] rolls = new int[numberOfRolls];
        for (int i = 0; i < numberOfRolls; i++)
        {
            rolls[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < numberOfRolls; i++)
        {
            Arrays.sort(player1);
            Arrays.sort(player2);

            HashSet<Integer> markers = new HashSet<>();

            for (int j = 0; j < 3; j++)
            {
                if (player1[j] != 52) {
                    markers.add(player1[j]);
                }
                if (player2[j] != 52) {
                    markers.add(player2[j]);
                }
            }

            if (i % 2 == 0) {
                markers.remove(player1[0]);
                player1[0] = move(player1[0], rolls[i], markers);
            }
            else {
                markers.remove(player2[0]);
                player2[0] = move(player2[0], rolls[i], markers);
            }
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < 3; i++)
        {
            if (player1[i] != 52) {
                sum1 += player1[i];
            }
            if (player2[i] != 52) {
                sum2 += player2[i];
            }
        }

        return sum1 + " " + sum2;
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
                "3 15 18 5 13 21 4 4 5 4 4",
                "1 11 20 3 7 16 8 3 5 6 4 6 6 6 1",
                "18 22 15 6 10 5 6 3 2 6 5 1 4",
                "12 20 15 40 35 30 5 1 2 3 4 5",
                "25 20 15 12 9 6 7 6 5 4 5 3 1 6",
                "1 9 18 3 10 17 8 3 1 6 4 6 6 5 5",
                "40 44 48 50 30 45 12 5 3 1 2 4 6 5 4 3 2 1 6",
                "38 41 48 34 42 46 10 6 6 5 1 6 3 5 1 5 2",
                "4 20 38 12 23 44 10 5 6 4 6 3 6 3 4 4 3",
                "17 34 41 15 29 39 16 6 1 5 1 4 6 2 3 5 1 5 5 5 3 3 6"
        };

        String[] output = {
                "49 46", "51 34", "55 37", "54 111", "71 33",
                "31 44", "144 138", "145 135", "85 113", "138 124"
        };

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