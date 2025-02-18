/**
 *      ACSL 2022-2023 - Contest 3 - Create A Tree - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'onlyLeftOrRight' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING input as parameter.
 */
string onlyLeftOrRight(string input)
{
    int n = input.length();
    char letters[n];
    int values[n];

    letters[0] = input[0];
    values[0] = 0;
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
    }

    string left = "";
    string right = "";
    for (int i = 0; i < n; i++)
    {
        char c = letters[i];
        int v = values[i];

        bool searchLeft = false;
        int index = i - 1;
        while (index >= 0 && values[index] >= v)
        {
            if (values[index] == v + 1) 
            {
                searchLeft = true;
                break;
            }
            index--;
        }

        bool searchRight = false;
        index = i + 1;
        while (index < n && values[index] >= v)
        {
            if (values[index] == v + 1) 
            {
                searchRight = true;
                break;
            }
            index++;
        }        

        if (searchLeft && !searchRight)
        {
            left += c;
        }
        else if (!searchLeft && searchRight)
        {
            right += c;
        }

    }

    if (left.empty())
    {
        left = "NONE";
    }
    if (right.empty())
    {
        right = "NONE";
    }

    return left + " " + right;
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
        "NOY H",
        "AERY CNS",
        "NOUV NONE",
        "FLS IOR",
        "AR CEL",
        "HT O",
        "T A",
        "CIILU FG",
        "AORV NONE",
        "NONE ABDFHKMOQTVWY"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = onlyLeftOrRight(input[i]);

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