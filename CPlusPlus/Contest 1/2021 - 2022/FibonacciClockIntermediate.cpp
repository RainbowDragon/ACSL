/**
 *      ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int addHour(char color, int delta)
{
    if (color == 'R' || color == 'B') 
    {
        return delta;
    }
    return 0; 
}

int addMinute(char color, int delta)
{
    if (color == 'G' || color == 'B') 
    {
        return delta;
    }
    return 0;    
}

/*
 * Complete the 'findTime' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 * 1. CHARACTER c1
 * 2. CHARACTER c2
 * 3. CHARACTER c3
 * 4. CHARACTER c4
 * 5. CHARACTER c5
 */
string findTime(char c1, char c2, char c3, char c4, char c5)
{
    char colors[5] = {c1, c2, c3, c4, c5};
    int deltas[5] = {1, 1, 2, 3, 5};

    int hour = 0;
    int minute = 0;

    for (int i = 0; i < 5; i++)
    {
        hour += addHour(colors[i], deltas[i]);
        minute += addMinute(colors[i], deltas[i]);
    }
    minute *= 5;

    if (minute >= 60) 
    {
        hour += minute / 60;
        minute %= 60;
    }
    hour %= 12;

    string strHour = to_string(hour);
    string strMinute = to_string(minute);

    if (hour < 10) 
    {
        strHour = "0" + strHour;
    }

    if (minute < 10) 
    {
        strMinute = "0" + strMinute;
    }

    return strHour + ":" + strMinute;
}

int main()
{
    char input[10][5] = 
    {
        {'R', 'W', 'G', 'B', 'G'}, {'W', 'B', 'B', 'G', 'R'},
        {'W', 'G', 'B', 'R', 'B'}, {'G', 'G', 'B', 'B', 'B'},
        {'W', 'R', 'G', 'G', 'G'}, {'G', 'R', 'W', 'B', 'B'},
        {'G', 'G', 'B', 'G', 'B'}, {'R', 'R', 'R', 'R', 'R'},
        {'G', 'G', 'G', 'G', 'G'}, {'W', 'B', 'B', 'B', 'B'}
    };

    string output[10] = 
    {
        "04:50", "08:30", "10:40", "11:00", "01:50", "09:45", "08:00", "00:00", "01:00", "11:55"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = findTime(input[i][0], input[i][1], input[i][2], input[i][3], input[i][4]);

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