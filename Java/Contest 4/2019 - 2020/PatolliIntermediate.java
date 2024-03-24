/**
 *      ACSL 2019-2020 - Contest 4 - Patolli - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class PatolliIntermediate {

    static String getFinalLocation (String str) {

        StringTokenizer st = new StringTokenizer(str);
        HashSet<Integer> markers = new HashSet<>();
        for (int i = 0; i < 3; i++)
        {
            markers.add(Integer.parseInt(st.nextToken()));
        }

        int[] starts = new int[3];
        for (int i = 0; i < 3; i++)
        {
            starts[i] = Integer.parseInt(st.nextToken());
        }

        int numberOfRolls = Integer.parseInt(st.nextToken());
        int[] rolls = new int[numberOfRolls];
        for (int i = 0; i < numberOfRolls; i++)
        {
            rolls[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < numberOfRolls; i++)
        {
            Arrays.sort(starts);
            markers.remove(starts[0]);
            markers.add(starts[1]);
            markers.add(starts[2]);
            starts[0] = move(starts[0], rolls[i], markers);
            markers.remove(starts[1]);
            markers.remove(starts[2]);
        }

        ArrayList<Integer> ends = new ArrayList<>();
        for (int i = 0; i < 3; i++)
        {
            if (starts[i] != 52) {
                ends.add(starts[i]);
            }
        }
        Collections.sort(ends);

        if (ends.isEmpty()) {
            return "GAME OVER";
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ends.size(); i++)
            {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(ends.get(i));
            }
            return sb.toString();
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

        String[] input = new String[10];
        input[0] = "4 14 24 1 8 12 6 6 3 5 1 5 6";
        input[1] = "14 28 31 10 20 24 7 6 6 5 5 6 2 4";
        input[2] = "5 30 33 3 20 24 8 6 6 6 5 6 3 4 6";
        input[3] = "4 11 15 2 8 20 5 5 2 5 1 6";
        input[4] = "45 50 48 42 46 40 6 3 2 6 5 4 1";
        input[5] = "37 41 47 35 43 48 6 5 5 6 5 4 2";
        input[6] = "13 29 39 15 21 31 10 4 5 2 4 6 6 5 5 6 5";
        input[7] = "43 47 40 28 30 32 10 5 3 2 6 1 5 2 6 3 2";
        input[8] = "1 5 10 2 12 8 12 5 5 4 4 3 3 2 2 1 1 6 6";
        input[9] = "20 25 15 30 18 24 16 6 6 4 5 2 1 6 4 2 3 6 5 4 5 3 1";

        String[] output = {
                "13 15 18", "26 29 30", "20 23 24", "14 16 20", "44 46 47",
                "49 50", "34 35 36", "37 38 39", "9 11 12", "32 33 35"
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