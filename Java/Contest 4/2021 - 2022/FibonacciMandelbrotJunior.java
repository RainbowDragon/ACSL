/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciMandelbrotJunior {

    /*
     * Complete the 'absResult' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. FLOAT realPartC
     * 2. FLOAT imagPartC
     */
    static String absResult (float realPartC, float imagPartC) {

        float[] C = new float[]{realPartC, imagPartC};
        float[] number = new float[]{0f, 0f};

        String result = "";
        boolean escape = false;
        for (int i = 1; i <= 5; i++)
        {
            number = function(number, C);
            if (absoluteValueSquare(number) > 16) {
                result = "ESCAPES " + i;
                escape = true;
                break;
            }
        }

        if (!escape) {
            result = String.format("%.3f", Math.sqrt((double)absoluteValueSquare(number)));
        }

        return result;
    }

    static float[] addition (float[] first, float[] second) {

        float[] result = new float[2];
        result[0] = first[0] + second[0];
        result[1] = first[1] + second[1];

        return result;
    }

    static float[] multiplication (float[] first, float[] second) {

        float[] result = new float[2];
        result[0] = first[0]*second[0] - first[1]*second[1];
        result[1] = first[0]*second[1] + first[1]*second[0];

        return result;
    }

    static float absoluteValueSquare (float[] number) {

        float result = number[0]*number[0] + number[1]*number[1];

        return result;
    }

    static float[] function (float[] number, float[] C) {

        float[] result = multiplication(number, number);
        result = addition(result, C);

        return result;
    }

    public static void main (String [] args) {

        float[][] input = {
                {0.3f, -0.67f}, {2.0f, -0.3f}, {-0.5f, 0.56f}, {-0.62f, 0.43f}, {0.0f, 0.543f},
                {0.45f, -0.56f}, {-0.325f, 0.765f}, {0.561f, -0.456f}, {-0.213f, 0.729f}, {-0.053f, 0.0f}
        };

        String[] output = {
                "0.817", "ESCAPES 2", "0.018", "0.508", "0.574",
                "2.153", "1.057", "ESCAPES 5", "0.834", "0.050"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = absResult(input[i][0], input[i][1]);

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