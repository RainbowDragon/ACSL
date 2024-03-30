/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciMandelbrotIntermediate {

    /*
     * Complete the 'cycleLength' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. FLOAT realPartC
     * 2. FLOAT imagPartC
     */
    static String cycleLength (float realPartC, float imagPartC) {

        double[] C = new double[]{(double)realPartC, (double)imagPartC};
        double[] number = new double[]{0f, 0f};

        ArrayList<Double> realList = new ArrayList<>();
        ArrayList<Double> imagList = new ArrayList<>();
        realList.add(number[0]);
        imagList.add(number[1]);

        String result = "";
        int n = 0;

        while (n <= 500)
        {
            n++;
            number = function(number, C);

            if (absoluteValueSquare(number) > 16) {
                result = "ESCAPES " + n;
                break;
            }

            boolean found = false;
            for (int i = 0; i < n; i++)
            {
                if (realList.get(i) == number[0] && imagList.get(i) == number[1]) {
                    found = true;
                    result += n - i;
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

    static double[] round (double[] number) {

        double[] result = new double[2];
        result[0] = Math.round(number[0]*100) / 100.0;
        result[1] = Math.round(number[1]*100) / 100.0;

        return result;
    }

    static double[] function (double[] number, double[] C) {

        double[] result = round(multiplication(number, number));
        result = round(addition(result, C));

        return result;
    }

    public static void main (String [] args) {

        float[][] input = new float[10][];
        input[0] = new float[]{-0.1f, 0.75f};
        input[1] = new float[]{2.0f, -0.3f};
        input[2] = new float[]{-0.5f, 0.56f};
        input[3] = new float[]{-1.21f, -0.32f};
        input[4] = new float[]{0.01f, -0.64f};
        input[5] = new float[]{-0.52f, 0.57f};
        input[6] = new float[]{-1.07f, -0.2f};
        input[7] = new float[]{-1.04f, -0.28f};
        input[8] = new float[]{0.33f, 0.77f};
        input[9] = new float[]{0.26f, -0.02f};

        String[] output = {
                "3",
                "ESCAPES 2",
                "5",
                "8",
                "13",
                "5",
                "8",
                "21",
                "ESCAPES 6",
                "1"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = cycleLength(input[i][0], input[i][1]);

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