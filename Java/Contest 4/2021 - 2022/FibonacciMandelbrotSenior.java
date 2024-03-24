/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Senior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.text.DecimalFormat;
import java.util.*;

public class FibonacciMandelbrotSenior {

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

        float[][] input = new float[10][];
        input[0] = new float[]{0.1f, 0.2f, 0.4f, 0.35f, 0.075f};
        input[1] = new float[]{-0.1f, -0.1f, 0.0f, 0.1f, 0.05f};
        input[2] = new float[]{-2.5f, -1.0f, -2.0f, 0.0f, 0.05f};
        input[3] = new float[]{-0.1f, -0.3f, -0.05f, -0.2f, 0.005f};
        input[4] = new float[]{-0.3f, -0.3f, 0.2f, 0.2f, 0.1f};
        input[5] = new float[]{-1.5f, -1.5f, 1.5f, 1.5f, 0.2f};
        input[6] = new float[]{-0.4f, -0.3f, 0.4f, 0.3f, 0.06f};
        input[7] = new float[]{0.175f, -0.1f, 0.235f, 0.4f, 0.006f};
        input[8] = new float[]{-0.2f, -0.2f, 0.0f, 0.5f, 0.02f};
        input[9] = new float[]{-0.375f, -0.1f, 0.075f, 0.5f, 0.025f};

        int[] output = {9, 15, 1, 222, 34, 30, 127, 764, 388, 456};

        for (int i = 0; i < 10; i++)
        {
            int result = numFibonacciCycles(input[i][0], input[i][1], input[i][2], input[i][3], input[i][4]);

            if (output[i] == result) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
            }
        }
    }
}