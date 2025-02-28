/**
 *      ACSL 2021-2022 - Contest 4 - Fibonacci & Mandelbrot - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

pair<float, float> addition(pair<float, float> A, pair<float, float> B)
{
    pair<float, float> result;
    result.first = A.first + B.first;
    result.second = A.second + B.second;

    return result;
}

pair<float, float> multiplication(pair<float, float> A, pair<float, float> B)
{
    pair<float, float> result;
    result.first = A.first*B.first - A.second*B.second;
    result.second = A.first*B.second + A.second*B.first;

    return result;
}

float absoluteValueSquare(pair<float, float> number)
{
    float result = number.first*number.first + number.second*number.second;

    return result;
}

pair<float, float> transformation(pair<float, float> number, pair<float, float> C)
{
    pair<float, float> result = multiplication(number, number);
    result = addition(result, C); 

    return result;
}

/*
 * Complete the 'absResult' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. FLOAT realPartC
 *  2. FLOAT imagPartC
 */
string absResult(float realPartC, float imagPartC)
{
    pair<float, float> C = {realPartC, imagPartC};
    pair<float, float> number = {0.0f, 0.0f};

    string result = "";
    bool escape = false;
    for (int i = 1; i <= 5; i++)
    {
        number = transformation(number, C);
        if (absoluteValueSquare(number) > 16)
        {
            result = "ESCAPES " + to_string(i);
            escape = true;
            break;
        }
    }

    if (!escape)
    {
        stringstream ss;
        ss << fixed << setprecision(3) << sqrt(absoluteValueSquare(number));
        result = ss.str();
    }

    return result;
}

int main()
{
    float input[10][2] = 
    {
        {0.3f, -0.67f}, {2.0f, -0.3f}, {-0.5f, 0.56f}, {-0.62f, 0.43f}, {0.0f, 0.543f},
        {0.45f, -0.56f}, {-0.325f, 0.765f}, {0.561f, -0.456f}, {-0.213f, 0.729f}, {-0.053f, 0.0f}
    };

    string output[10] = 
    {
        "0.817", "ESCAPES 2", "0.018", "0.508", "0.574", 
        "2.153", "1.057", "ESCAPES 5", "0.834", "0.050"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = absResult(input[i][0], input[i][1]);

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