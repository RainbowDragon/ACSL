/**
 *      ACSL 2019-2020 - Contest 2 - String Differences - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

string getUpperCaseString(string s)
{
    string result = "";

    for (int i = 0; i < s.length(); i++)
    {
        if (s[i] >= 'A' && s[i] <= 'Z')
        {
            result += s[i];
        }
        else if (s[i] >= 'a' && s[i] <= 'z')
        {
            result += toupper(s[i]);
        }
    }

    return result;
}

string getLongestCommonSubstring(string s1, string s2)
{
    string result = "";

    for (int i = 0; i < s1.length(); i++)
        for (int j = 1; j <= s1.length()-i; j++)
        {
            string sub = s1.substr(i, j);
            if (s2.find(sub) != string::npos)
            {
                if (sub.length() > result.length())
                {
                    result = sub;
                }
                else if (sub.length() == result.length() && sub.compare(result) < 0)
                {
                    result = sub;
                }
            }
        }

    return result;
}

int getDifferenceFactor (string s1, string s2)
{
    string common = getLongestCommonSubstring(s1, s2);
    int len = common.length();

    if (len == 0) 
    {
        return 0;
    }
    else
    {
        int i1 = s1.find(common);
        int i2 = s2.find(common);

        int leftCount = getDifferenceFactor(s1.substr(0, i1), s2.substr(0, i2));
        int rightCount = getDifferenceFactor(s1.substr(i1+len), s2.substr(i2+len));

        return leftCount + len + rightCount;
    }
}

int differenceFactor(string s1, string s2)
{
    s1 = getUpperCaseString(s1);
    s2 = getUpperCaseString(s2);

    return getDifferenceFactor(s1, s2);
}

int main()
{
    string input[10][2] = 
    {
        {
            "I am going home now",
            "I will go home now"
        },
        {
            "The big black bear bit a big black bug",
            "The big black bug bled black blood"
        },
        {
            "Complementary angle measures sum to 90 degrees.",
            "The measures of supplementary angles add to 180 degrees."
        },
        {
            "A Tale of Two Cities was published by Dickens in 1859.",
            "In 1839, Charles Dickens published Nicholas Nickleby."
        },
        {
            "Connecticut is The Constitution State.",
            "Hartford is the capital of Connecticut."
        },
        {
            "To be or not to be, that is the question.",
            "To err is human; to really foul things up requires a computer."
        },
        {
            "The Pythagorean Theorem says that the sum of the squares of the two legs equals the square of the hypotenuse.",
            "To find a leg using the Pythagorean Theorem, take the square root of the hypotenuse squared minus the other leg squared."
        },
        {
            "Uncle Tom's Cabin was published by Harriet Beecher Stowe in 1852.",
            "In 1876, Mark Twain published The Adventures of Tom Sawyer."
        },
        {
            "Once upon a midnight dreary while I pondered weak and weary,",
            "Over many a quaint and curious volume of forgotten lore."
        },
        {
            "A tutor who tooted the flute tried to tutor two tooters to toot!",
            "Is it harder to toot or to tutor two tooters to toot?"
        }
    };

    int output[10] = 
    {
        10, 19, 26, 18, 11, 14, 50, 20, 9, 31
    };

    for (int i = 0; i < 10; i++)
    {
        int result = differenceFactor(input[i][0], input[i][1]);

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