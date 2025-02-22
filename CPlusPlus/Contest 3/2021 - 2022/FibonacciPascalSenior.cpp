/**
 *      ACSL 2021-2022 - Contest 3 - Fibonacci & Pascal - Senior Division
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
 * Complete the 'countUniqueValues' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts INTEGER fibNumber as parameter.
 */
int countUniqueValues(int fibNumber)
{
    int index = getFibonacciIndex(fibNumber);

    unordered_map<long long, int> countMap;
    for (int k = 1; k <= index; k++)
    {
        int i = 0;
        int n = k;
        long long current = 1;
        countMap[current]++;

        while (i + 1 < n)
        {
            current = (current * (n - i) * (n - i - 1)) / n / (i + 1);
            n--;
            i++;
            countMap[current]++;
        }
    }

    int result = 0;
    for (auto pair : countMap)
    {
        if (pair.second == 1)
        {
            result++;
        }
    }
    
    return result;
}

int main()
{
    int input[10] = 
    {
        8, 89, 610, 10946, 317811, 55, 1597, 832040, 9227465, 1836311903
    };

    int output[10] = 
    {
        2, 8, 16, 31, 58, 6, 21, 67, 96, 171
    };

    for (int i = 0; i < 10; i++)
    {
        int result = countUniqueValues(input[i]);

        if (output[i] == result) 
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