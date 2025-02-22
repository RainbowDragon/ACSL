/**
 *      ACSL 2022-2023 - Contest 3 - Create A Tree - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

string preorder(char* letters, int* values, int left, int right, int level)
{
    bool found = false;
    int index;
    for (index = left; index <= right; index++)
    {
        if (values[index] == level)
        {
            found = true;
            break;
        }
    }

    if (!found)
    {
        return "";
    }

    string leftStr = preorder(letters, values, left, index-1, level+1);
    string rightStr = preorder(letters, values, index+1, right, level+1);

    return letters[index] + leftStr + rightStr;
}

string postorder(char* letters, int* values, int left, int right, int level)
{
    bool found = false;
    int index;
    for (index = left; index <= right; index++)
    {
        if (values[index] == level)
        {
            found = true;
            break;
        }
    }

    if (!found)
    {
        return "";
    }

    string leftStr = postorder(letters, values, left, index-1, level+1);
    string rightStr = postorder(letters, values, index+1, right, level+1);

    return leftStr + rightStr + letters[index];
}

/*
 * Complete the 'getTraversals' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING input as parameter.
 */
string getTraversals(string input)
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
    }

    string first = preorder(letters, values, 0, n-1, 0);
    string second = postorder(letters, values, 0, n-1, 0);

    return first + " " + second;
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
        "PHONNYT NNOHTYP",
        "BAAIECEEHNRRRYST AAEECHERRTSYRNIB",
        "CAOONIRRVUS AINORSUVROC",
        "FAAFCINLLSORS ACFALLSROSNIF",
        "HAAACEFKKRRNLMPORT AAFECAKMLORPNRTRKH",
        "MHEOTR EHRTOM",
        "ACCSLEONSTT CENSOLTTSCA",
        "SPECAACLIFGIIILRRSUT ACACIIIGFLILERSRPTUS",
        "JAAAGGIVPOMMNRR AAGIGAMNMORRPVJ",
        "ABDFHKMOQTVWYZ ZYWVTQOMKHFDBA"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = getTraversals(input[i]);

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