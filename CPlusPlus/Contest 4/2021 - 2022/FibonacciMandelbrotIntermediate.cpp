/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

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

pair<double, double> roundNumber(pair<double, double> number)
{
    pair<double, double> result;
    result.first = round(number.first*100) / 100.0;
    result.second = round(number.second*100) / 100.0;

    return result;
}

pair<double, double> transformation(pair<double, double> number, pair<double, double> C)
{
    pair<double, double> result = roundNumber(multiplication(number, number));
    result = roundNumber(addition(result, C)); 

    return result;
}

/*
 * Complete the 'cycleLength' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. FLOAT realPartC
 *  2. FLOAT imagPartC
 */
string cycleLength(float realPartC, float imagPartC)
{
    pair<double, double> C = {(double)realPartC, (double)imagPartC};
    pair<double, double> number = {0.0, 0.0};

    vector<pair<double, double>> numberList;
    numberList.push_back(number);

    string result = "";
    int n = 0;

    while (n <= 500)
    {
        n++;
        number = transformation(number, C);

        if (absoluteValueSquare(number) > 16)
        {
            result = "ESCAPES " + to_string(n);
            break;
        }

        bool found = false;
        for (int i = 0; i < n; i++)
        {
            if (numberList[i].first == number.first && numberList[i].second == number.second)
            {
                found = true;
                result = to_string(n - i);
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

int main()
{
    float input[10][2] = 
    {
        {-0.1f, 0.75f}, {2.0f, -0.3f}, {-0.5f, 0.56f}, {-1.21f, -0.32f}, {0.01f, -0.64f},
        {-0.52f, 0.57f}, {-1.07f, -0.2f}, {-1.04f, -0.28f}, {0.33f, 0.77f}, {0.26f, -0.02f}
    };

    string output[10] = 
    {
        "3", "ESCAPES 2", "5", "8", "13",
        "5", "8", "21", "ESCAPES 6", "1"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = cycleLength(input[i][0], input[i][1]);

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