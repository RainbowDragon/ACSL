/**
 *      ACSL 2020-2021 - Contest 2 - Lex Strings - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int getIndexOfChar(char c)
{
    if (c >= 'A' && c <= 'Z')
    {
        return c - 'A';
    }

    if (c >= 'a' && c <= 'z')
    {
        return c - 'a';
    }

    return -1;
}

/*
 * Complete the 'rearrangeString' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING s as parameter.
 */
string rearrangeString(string s)
{
    int charCount[26];
    memset(charCount, 0, sizeof charCount);
    int maxCount = 0;
    for (int i = 0; i < s.length(); i++)
    {
        int index = getIndexOfChar(s[i]);
        if (index != -1)
        {
            charCount[index]++;
            maxCount = max(maxCount, charCount[index]);
        }
    }

    string result = "";
    char lastChar = ' ';
    for (int j = 0; j < maxCount; j++)
        for (int i = 0; i < 26; i++)
        {
            if (charCount[i] > j)
            {
                char curChar = (char)('a' + i);
                if (lastChar != curChar)
                {
                    result += curChar;
                    lastChar = curChar;
                }
            }
        }

    return result;
}

int main()
{
    string input[10] = 
    {
        "A good sorting algorithm.",
        "Tennessee is the volunteer state.",
        "Einstein was a genius.",
        "Tom Sawyer & the Mississippi River",
        "She sells seashells by the seashore.",
        "Peter Piper picked a peck of pickled peppers.",
        "Computer Science Teachers Association had a virtual Conference in 2020.",
        "HackerRank.com was used for the ACSL Finals this past year.",
        "Programming languages include Java, Python, C++, BASIC, and Scratch.",
        "COVID-19 is a global pandemic and a virus that changed everything."
    };

    string output[10] = 
    {
        "adghilmnorstagiortgo",
        "aehilnorstuvenstenstestete",
        "aeginstuwaeinseins",
        "aehimoprstvwyeimprsteirsisis",
        "abehlorstyaehlsehlsehlseseses",
        "acdefikloprstcdeikprceikprepepepepep",
        "acdefhilmnoprstuvacehinorstuaceinorstaceinorstaceinacece",
        "acdefhiklmnoprstuwyacefhiklnorstacehrstaersasasa",
        "abcdeghijlmnoprstuvyacdeghilmnoprstuacginrsacgnacna",
        "abcdeghilmnoprstuvyacdeghilnorstvacdeghintvadeinaia"
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