/**
 *      ACSL 2023-2024 - Contest 3 - ACSL Rack-O - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class ACSLRackOJunior {

    /*
     * Complete the 'playRackO' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. INTEGER slots
     * 2. INTEGER cards
     * 3. STRING rack
     * 4. STRING pile
     */
    static int playRackO (int slots, int cards, String rack, String pile) {

        String[] racks = rack.split(" ");
        int[] rackList = new int[slots];
        for (int i = 0; i < slots; i++)
        {
            rackList[i] = Integer.parseInt(racks[i]);
        }

        if (isRackInOrder(rackList)) {
            return getRackSum(rackList);
        }

        String[] piles = pile.split(" ");
        for (String str : piles)
        {
            int card = Integer.parseInt(str);

            boolean isUpdated = updateWithRule1(rackList, card);

            if (!isUpdated) {
                isUpdated = updateWithRule2(rackList, card);
            }

            if (!isUpdated) {
                isUpdated = updateWithRule3(rackList, card);
            }

            if (!isUpdated) {
                isUpdated = updateWithRule4(rackList, card);
            }

            if (!isUpdated) {
                isUpdated = updateWithRule5(rackList, card);
            }

            if (isUpdated && isRackInOrder(rackList)) {
                return getRackSum(rackList);
            }
        }

        return 0;
    }

    static boolean updateWithRule1 (int[] rackList, int card) {

        for (int i = 1; i < rackList.length; i++)
        {
            if (card + 1 == rackList[i]) {
                rackList[i-1] = card;
                return true;
            }
        }
        return false;
    }

    static boolean updateWithRule2 (int[] rackList, int card) {

        for (int i = 0; i < rackList.length-1; i++)
        {
            if (card - 1 == rackList[i]) {
                rackList[i+1] = card;
                return true;
            }
        }
        return false;
    }

    static boolean updateWithRule3 (int[] rackList, int card) {

        for (int i = 1; i < rackList.length-1; i++)
        {
            if (card > rackList[i-1] && card < rackList[i+1] && !(rackList[i] > rackList[i-1] && rackList[i] < rackList[i+1])) {
                rackList[i] = card;
                return true;
            }
        }
        return false;
    }

    static boolean updateWithRule4 (int[] rackList, int card) {

        if (card < rackList[1] && rackList[0] > rackList[1]) {
            rackList[0] = card;
            return true;
        }
        return false;
    }

    static boolean updateWithRule5 (int[] rackList, int card) {

        int n = rackList.length;
        if (card > rackList[n-2] && rackList[n-1] < rackList[n-2]) {
            rackList[n-1] = card;
            return true;
        }
        return false;
    }

    static boolean isRackInOrder (int[] rackList) {

        for (int i = 1; i < rackList.length; i++)
        {
            if (rackList[i] < rackList[i-1]) {
                return false;
            }
        }
        return true;
    }

    static int getRackSum (int[] rackList) {

        int result = 0;
        for (int value : rackList)
        {
            result += value;
        }
        return result;
    }

    public static void main (String [] args) {

        String[][] input = {
                {"10", "60", "40 35 20 56 32 58 42 17 45 34", "31 44 10 28 19 46 7 37 16 2"},
                {"15", "90", "15 56 38 9 28 17 46 51 7 53 65 70 74 84 47", "45 73 52 54 16 21 44 40 68 30 20 87"},
                {"8", "100", "6 13 47 62 32 70 76 12", "3 67 80 10 39 44 2 43 40 85 21 33 4 52"},
                {"12", "110", "44 35 22 25 79 100 85 69 87 3 56 28", "97 10 48 43 42 21 81 47 86 88 94 54 24 50"},
                {"10", "80", "29 22 11 40 55 58 48 4 45 44", "24 71 50 35 61 70 33 20 61 56 34 69"},
                {"9", "140", "74 135 61 45 92 122 14 98 138", "105 60 66 116 5 106 97 85 18 139 96"},
                {"15", "70", "27 43 24 9 70 64 3 33 30 63 11 1 25 12 35", "69 15 10 2 34 66 21 49 23 51 5 57 38 40 53"},
                {"11", "80", "68 52 8 25 22 18 29 16 74 48 34", "30 43 6 7 54 73 11 27 28 71 47 42 50"},
                {"15", "60", "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60", "9 31 50 59 1"},
                {"10", "75", "70 65 60 55 50 45 40 35 30 10", "44 8 15 46 23 72 3 68 53 37 75 39"}
        };

        int[] output = {
                326, 772, 421, 775, 0, 713, 0, 285, 499, 411
        };

        for (int i = 0; i < 10; i++)
        {
            int result = playRackO(Integer.parseInt(input[i][0]), Integer.parseInt(input[i][1]), input[i][2], input[i][3]);

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