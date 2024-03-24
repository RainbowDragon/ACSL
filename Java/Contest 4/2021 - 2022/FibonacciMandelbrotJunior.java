/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Junior Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciMandelbrotJunior {

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

        float[][] input = new float[10][];
        input[0] = new float[]{0.3f, -0.67f};
        input[1] = new float[]{2.0f, -0.3f};
        input[2] = new float[]{-0.5f, 0.56f};
        input[3] = new float[]{-0.62f, 0.43f};
        input[4] = new float[]{0.0f, 0.543f};
        input[5] = new float[]{0.45f, -0.56f};
        input[6] = new float[]{-0.325f, 0.765f};
        input[7] = new float[]{0.561f, -0.456f};
        input[8] = new float[]{-0.213f, 0.729f};
        input[9] = new float[]{-0.053f, 0.0f};

        String[] output = {
                "0.817",
                "ESCAPES 2",
                "0.018",
                "0.508",
                "0.574",
                "2.153",
                "1.057",
                "ESCAPES 5",
                "0.834",
                "0.050"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = absResult(input[i][0], input[i][1]);

            if (output[i].equals(result)) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
            }
        }
    }
}