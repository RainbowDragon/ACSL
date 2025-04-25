/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

float roundToDecimal(float num)
{
    return round(num * 1000.0) / 1000.0;
}

double roundToDecimal(double num)
{
    return round(num * 1000.0) / 1000.0;
}

pair<double, double> roundToDecimal(pair<double, double> number)
{
    pair<double, double> result;
    result.first = roundToDecimal(number.first);
    result.second = roundToDecimal(number.second);

    return result;
}

pair<double, double> addition(pair<double, double> A, pair<double, double> B)
{
    pair<double, double> result;
    result.first = A.first + B.first;
    result.second = A.second + B.second;

    return result;
}

pair<double, double> multiplication(pair<double, double> A, pair<double, double> B)
{
    pair<double, double> result;
    result.first = A.first*B.first - A.second*B.second;
    result.second = A.first*B.second + A.second*B.first;

    return result;
}

double absoluteValueSquare(pair<double, double> number)
{
    double result = number.first*number.first + number.second*number.second;

    return result;
}

pair<double, double> transformation(pair<double, double> number, pair<double, double> C)
{
    pair<double, double> result = roundToDecimal(multiplication(number, number));
    result = roundToDecimal(addition(result, C)); 

    return result;
}

int getCycleLength(float realPartC, float imagPartC)
{
    pair<double, double> C = roundToDecimal({(double)realPartC, (double)imagPartC});
    pair<double, double> number = {0.0, 0.0};

    vector<pair<double, double>> numberList;
    numberList.push_back(number);

    int result = 0;
    int n = 0;

    while (n <= 500)
    {
        n++;
        number = transformation(number, C);

        if (absoluteValueSquare(number) > 16)
        {
            result = 0;
            break;
        }

        bool found = false;
        for (int i = 0; i < n; i++)
        {
            if (numberList[i].first == number.first && numberList[i].second == number.second)
            {
                found = true;
                result = n - i;
                break;
            }
        }

        if (found)
        {
            break;
        }

        numberList.push_back(number);
    }

    return result;
}

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
int numFibonacciCycles(float realPartLL, float imagPartLL, float realPartUR, float imagPartUR, float incr)
{
    int nx = round((realPartUR - realPartLL) / incr);
    int ny = round((imagPartUR - imagPartLL) / incr);

    int result = 0;
    unordered_set<int> fibonacciSet;
    int first = 1;
    int second = 2;
    fibonacciSet.insert(first);
    fibonacciSet.insert(second);
    while (second <= 500)
    {
        int temp = first;
        first = second;
        second = temp + first;
        fibonacciSet.insert(second);
    }

    for (int i = 0; i <= nx; i++)
        for (int j = 0; j <= ny; j++)
        {
            float realPart = roundToDecimal(realPartLL + incr*i);
            float imagPart = roundToDecimal(imagPartLL + incr*j);

            int cycleLength = getCycleLength(realPart, imagPart);

            if (fibonacciSet.find(cycleLength) != fibonacciSet.end()) {
                result++;
            }
        }    

    return result;
}

int main()
{
    float input[10][5] = 
    {
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

    int output[10] = 
    {
        9, 15, 1, 222, 34, 30, 127, 764, 388, 456
    };

    for (int i = 0; i < 10; i++)
    {
        int result = numFibonacciCycles(input[i][0], input[i][1], input[i][2], input[i][3], input[i][4]);

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