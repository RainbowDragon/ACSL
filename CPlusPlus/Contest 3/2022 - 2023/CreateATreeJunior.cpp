/**
 *      ACSL 2022-2023 - Contest 3 - Create A Tree - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'listByValue' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING input as parameter.
 */
string listByValue(string input)
{
    int n = input.length();
    char letters[n];
    int values[n];

    letters[0] = input[0];
    values[0] = 0;
    int maxValue = 0;
    for (int i = 1; i < n; i++)
    {
        int j = 0;
        char c = input[i];
        while (j < i && c > letters[j])
        {
            j++;
        }
        for (int k = i; k > j; k--)
        {
            letters[k] = letters[k-1];
            values[k] = values[k-1];
        }

        letters[j] = c;
        if (j == 0)
        {
            values[j] = values[j+1] + 1;
        }
        else if (j == i)
        {
            values[j] = values[j-1] + 1;
        }
        else
        {
            values[j] = max(values[j-1], values[j+1]) + 1;
        }

        maxValue = max(maxValue, values[j]);
    }

    string result = "";
    for (int i = 0; i <= maxValue; i++)
        for (int j = 0; j < n; j++)
        {
            if (values[j] == i)
            {
                result += letters[j];
            }
        }

    return result;
}

int main()
{
    string input[10] = 
    {
        "PYTHONN",
        "BINARYSEARCHTREE",
        "CORONAVIRUS",
        "FINALSFORACSL",
        "HACKERRANKPLATFORM",
        "MOTHER",
        "ACSLCONTEST",
        "SUPERCALIFRAGILISTIC",
        "JAVAPROGRAMMING",
        "ABDFHKMOQTVWYZ"
    };

    string output[10] = 
    {
        "PHYOTNN",
        "BAIAENCHRERYERST",
        "CAOORNRVIUS",
        "FAIAFNCLSLORS",
        "HAKACKRAERTFNLPMOR",
        "MHOETR",
        "ACCSLTEOTNS",
        "SPUERTCLRSAIACFLGIII",
        "JAVAGPAGIORMRMN",
        "ABDFHKMOQTVWYZ"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = listByValue(input[i]);

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