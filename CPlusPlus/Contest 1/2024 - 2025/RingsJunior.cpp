/**
 *      ACSL 2024-2025 - Contest 1 - Rings - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int getScore(char toss) 
{
    int score = 0;

    if (toss == 'A' || toss == 'R') 
    {
        score = 1;
    }
    else if (toss == 'O' || toss == 'G') 
    {
        score = 3;
    }
    else if (toss == 'B') 
    {
        score = 6;
    }

    return score;
}

int getScore(string tosses) 
{
    int score = 0;

    for (int i = 0; i < tosses.length(); i++)
    {
        score += getScore(tosses[i]);
    }

    return score;
}

/*
 * Complete the 'scoreTosses' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING tosses1
 *  2. STRING tosses2
 */
string scoreTosses(string tosses1, string tosses2)
{
    int score1 = getScore(tosses1);
    int score2 = getScore(tosses2);

    string result = "";

    if (score1 >= score2) 
    {
        result = to_string(score1) + " " + to_string(score2);
    }
    else 
    {
        result = to_string(score2) + " " + to_string(score1);
    }
    
    return result;
}

int main()
{
    string input[12][2] = 
    {
        {"BRAG", "BOB"},
        {"AABBOOGG", "BAROBA"},
        {"GBORABORBA", "BAAAAOORGGGB"},
        {"OORRRGRBBRRAAABB", "AAAAGRRRRGRRRBBOO"},
        {"BARROGGBBGO", "BBAAAARRRGGGOOOO"},
        {"AAAABBBOOGGGRRR", "RRBBOOOGGGAAAA"},
        {"AROBG", "BRAGGROG"},
        {"BOBBRAGROB", "BARBAGGRAB"},
        {"BBBAAAOOOGGGRRR", "BBBBBAAAAGGGOOR"},
        {"RRRRRRBBBBGGGGGGAAAO", "AAAAAAOOOOOORRRBBBBB"},
        {"BABAGORBABAGORBABAGORBABAGOR", "GORBAGORBAGORBAGORBAGORBAGORBA"},
        {"RRRGGGGBBBBAAOOOBBBAAARRBB", "BBBBAAAAOOOOBBBGGGRRRGOAGOAGO"}
    };

    string output[12] = 
    {
        "15 11", "26 18", "32 31", "42 35", "40 36", "40 36",
        "21 14", "36 29", "50 42", "57 54", "84 84", "90 85"
    };

    for (int i = 0; i < 12; i++)
    {
        string result = scoreTosses(input[i][0], input[i][1]);

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