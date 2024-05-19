/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.util.*;

public class FibonacciMandelbrotSenior {

    /*
     * Complete the 'numFibonacciCycles' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     * 1. FLOAT realPartLL
     * 2. FLOAT imagPartLL
     * 3. FLOAT realPartUR
     * 4. FLOAT imagPartUR
     * 5. FLOAT incr
     */
    static int numFibonacciCycles (float realPartLL, float imagPartLL, float realPartUR, float imagPartUR, float incr) {

        int nx = Math.round((realPartUR - realPartLL) / incr);
        int ny = Math.round((imagPartUR - imagPartLL) / incr);

        int result = 0;
        HashSet<Integer> fibonacciSet = getFibonacciSet();

        for (int i = 0; i <= nx; i++)
            for (int j = 0; j <= ny; j++)
            {
                float realPart = Float.parseFloat(df.format(realPartLL+incr*i));
                float imagPart = Float.parseFloat(df.format(imagPartLL+incr*j));
                int cycleLength = getCycleLength(realPart, imagPart);

                if (fibonacciSet.contains(cycleLength)) {
                    result++;
                }
            }

        return result;
    }

    static HashSet<Integer> getFibonacciSet () {

        HashSet<Integer> fibonacciSet = new HashSet<>();
        int first = 1;
        int second = 2;
        fibonacciSet.add(1);
        fibonacciSet.add(2);

        while (second <= 500)
        {
            int temp = first;
            first = second;
            second = temp + first;
            fibonacciSet.add(second);
        }

        return fibonacciSet;
    }
    static int getCycleLength (double realPartC, double imagPartC) {

        double[] C = round(new double[]{realPartC, imagPartC});
        double[] number = new double[]{0f, 0f};

        ArrayList<Double> realList = new ArrayList<>();
        ArrayList<Double> imagList = new ArrayList<>();
        realList.add(number[0]);
        imagList.add(number[1]);

        int result = 0;
        int n = 0;
        while (n <= 500)
        {
            n++;
            number = function(number, C);

            if (absoluteValueSquare(number) > 16) {
                result = 0;
                break;
            }

            boolean found = false;
            for (int i = 0; i < n; i++)
            {
                if (realList.get(i) == number[0] && imagList.get(i) == number[1]) {
                    found = true;
                    result = n - i;
                    break;
                }
            }

            if (found) {
                break;
            }

            realList.add(number[0]);
            imagList.add(number[1]);
        }

        return result;
    }

    static double[] addition (double[] first, double[] second) {

        double[] result = new double[2];
        result[0] = first[0] + second[0];
        result[1] = first[1] + second[1];

        return result;
    }

    static double[] multiplication (double[] first, double[] second) {

        double[] result = new double[2];
        result[0] = first[0]*second[0] - first[1]*second[1];
        result[1] = first[0]*second[1] + first[1]*second[0];

        return result;
    }

    static double absoluteValueSquare (double[] number) {

        double result = number[0]*number[0] + number[1]*number[1];

        return result;
    }

    private static final DecimalFormat df = new DecimalFormat("0.000");
    static double[] round (double[] number) {

        double[] result = new double[2];
        result[0] = Double.parseDouble(df.format(number[0]));
        result[1] = Double.parseDouble(df.format(number[1]));

        return result;
    }

    static double[] function (double[] number, double[] C) {

        double[] result = round(multiplication(number, number));
        result = round(addition(result, C));

        return result;
    }

    public static void main (String [] args) {

        float[][] input = {
                {0.1f, 0.2f, 0.4f, 0.35f, 0.075f},
                {-0.1f, -0.1f, 0.0f, 0.1f, 0.05f},
                {-2.5f, -1.0f, -2.0f, 0.0f, 0.05f},
                {-0.1f, -0.3f, -0.05f, -0.2f, 0.005f},
                {-0.3f, -0.3f, 0.2f, 0.2f, 0.1f},
                {-1.5f, -1.5f, 1.5f, 1.5f, 0.2f},
                {-0.4f, -0.3f, 0.4f, 0.3f, 0.06f},
                {0.175f, -0.1f, 0.235f, 0.4f, 0.006f},
                {-0.2f, -0.2f, 0.0f, 0.5f, 0.02f},
                {-0.375f, -0.1f, 0.075f, 0.5f, 0.025f}
        };

        int[] output = {9, 15, 1, 222, 34, 30, 127, 764, 388, 456};

        for (int i = 0; i < 10; i++)
        {
            int result = numFibonacciCycles(input[i][0], input[i][1], input[i][2], input[i][3], input[i][4]);

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