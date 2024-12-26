/**
 *      ACSL 2020-2021 - Contest 2 - Lex Strings - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'rearrangeString' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING s as parameter.
 */
string rearrangeString(string s)
{
    int charCount[128];
    memset(charCount, 0, sizeof charCount);
    int maxCount = 0;
    for (int i = 0; i < s.length(); i++)
    {
        if (isalnum(s[i]))
        {
            charCount[s[i]]++;
            maxCount = max(maxCount, charCount[s[i]]);
        }
    }

    string blocks[maxCount+1];
    for (int i = 0; i < 128; i++)
    {
        if (charCount[i] > 0)
        {
            blocks[charCount[i]] += (char)i;
        }
    }

    string result = "";
    int sign = 1;
    bool isFirst = true;
    for (int i = maxCount; i > 0; i--)
    {
        if (blocks[i].length() > 0)
        {
            if (!isFirst)
            {
                result += ",";
            }
            result += to_string(i);
            if (sign > 0)
            {
                result += blocks[i];
            }
            else
            {
                reverse(blocks[i].begin(), blocks[i].end());
                result += blocks[i];
            }
            isFirst = false;
            sign *= -1;
        }
    }

    return result;
}

int main()
{
    string input[10] = 
    {
        "This is an Example of Sorting an interesting string",
        "HackerRank.com was used for the ACSL Finals this year.",
        "The digits of PI are 3.141592653.",
        "She sells seashells by the seashore.",
        "Programming languages include Java, Python, C++, Visual BASIC, Ruby, and Scratch.",
        "COVID-19 is a global pandemic and a virus that changed everything in the entire world.",
        "The Computer Science Teacher Association had a virtual Conference in 2020.",
        "The digits of PI are 3.14159265358979323846264778327, not rounded.",
        "Peter Piper picked a peck of pickled peppers. How many pickled peppers did Peter Piper pick?",
        "There are 10 kinds of people: those who know binary and those who don't."
    };

    string output[10] = 
    {
        "6in,4ts,3aegr,2o,1ESTfhlmpx",
        "5a,4se,3r,2tonkihc,1ACFHLRSdflmuwy",
        "2135ei,1tsrohgfdaTPI9642",
        "7es,4lh,2a,1ytrobS",
        "8a,5n,4gu,3rlic,2CPSdehmosty,1vbVRJIBA",
        "7ae,6ni,5t,4rhd,3gl,2vsoc,119CDIOVbmpuwy",
        "9e,5nica,4or,3th,202CTsu,1vpmlfdSA",
        "53,472,345689deo,2trni1,1IPTafghsu",
        "14e,13p,7i,6r,5cdk,4P,2alost,1ywnmfH",
        "8o,7e,5hn,3wtsrda,2ikp,1ylfbT10"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = rearrangeString(input[i]);

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