/**
 *      ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Intermediate Division
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
 * Complete the 'findOddEvenMax' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts INTEGER fibNumber as parameter.
 */
string findOddEvenMax(int fibNumber)
{
    int n = getFibonacciIndex(fibNumber);
    int i = 0;

    int curOdd = 1;
    int curEven = 0;
    long long curMax = 1;
    long long current = 1;

    while (i + 1 < n)
    {
        current = (current * (n - i) * (n - i - 1)) / (n * (i + 1));
        n--;
        i++;
        
        if (current % 2 == 0)
        {
            curEven++;
        }
        else
        {
            curOdd++;
        }

        curMax = max(curMax, current);
    }

    return to_string(curOdd) + " " + to_string(curEven) + " " + to_string(curMax);
}

int main()
{
    int input[10] = 
    {
        8, 89, 610, 10946, 317811, 55, 1597, 832040, 9227465, 1836311903
    };

    string output[10] = 
    {
        "2 1 4", "5 1 35", "4 4 210", "8 3 3003", "3 11 77520",
        "3 2 21", "5 4 495", "4 11 203490", "9 9 2042975", "7 16 354817320"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = findOddEvenMax(input[i]);

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