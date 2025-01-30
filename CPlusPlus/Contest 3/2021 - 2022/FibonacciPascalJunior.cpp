/**
 *      ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int getFibonacciIndex(int fibNumber)
{
    int index = 1;
    int first = 1;
    int second = 1;

    while (second < fibNumber)
    {
        int temp = first;
        first = second;
        second += temp;
        index++;
    }

    return index;
}

/*
 * Complete the 'printNumbers' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts INTEGER fibNumber as parameter.
 */
string printNumber(int fibNumber)
{
    int n = getFibonacciIndex(fibNumber);
    int i = 0;
    int current = 1;
    string result = "1";

    while (i + 1 < n)
    {
        current = (current * (n - i) * (n - i - 1)) / (n * (i + 1));
        n--;
        i++;
        result += " " + to_string(current);
    }

    return result;
}

int main()
{
    int input[10] = 
    {
        8, 89, 610, 10946, 317811, 2, 55, 1597, 17711, 832040
    };

    string output[10] = 
    {
        "1 4 3",
        "1 9 28 35 15 1",
        "1 13 66 165 210 126 28 1",
        "1 19 153 680 1820 3003 3003 1716 495 55 1",
        "1 26 300 2024 8855 26334 54264 77520 75582 48620 19448 4368 455 14",
        "1 1",
        "1 8 21 20 5",
        "1 15 91 286 495 462 210 36 1",
        "1 20 171 816 2380 4368 5005 3432 1287 220 11",
        "1 28 351 2600 12650 42504 100947 170544 203490 167960 92378 31824 6188 560 15"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = printNumber(input[i]);

        if (output[i].compare(result) == 0) 
        {
            cout << "Test Case " << i << ": Passed!" << endl;
        }
        else 
        {
            cout << "Test Case " << i << ": Failed!" << endl;
            cout << "Expected output: " << output[i] << endl;
            cout << "Your output: " << result << endl;
        }
    }

    return 0;
}