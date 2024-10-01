/**
 *      ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

int addHour(char color, int delta)
{
    if (color == 'R' || color == 'Y' || color == 'M') 
    {
        return delta;
    }
    return 0; 
}

int addMinute(char color, int delta)
{
    if (color == 'G' || color == 'Y' || color == 'C') 
    {
        return delta;
    }
    return 0;    
}

int addSecond(char color, int delta)
{
    if (color == 'B' || color == 'M' || color == 'C') 
    {
        return delta;
    }
    return 0;    
}

/*
 * Complete the 'findTime' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING cstr as parameter.
 */
string findTime(string str)
{
    int deltas[5] = {1, 1, 2, 3, 5};

    int hour = 0;
    int minute = 0;
    int second = 0;

    for (int i = 0; i < 5; i++)
    {
        char color = str[i];
        hour += addHour(color, deltas[i]);
        minute += addMinute(color, deltas[i]);
        second += addSecond(color, deltas[i]);
    }
    minute *= 5;
    second *= 5;

    if (second >= 60) 
    {
        minute += second / 60;
        second %= 60;
    }

    if (minute >= 60) 
    {
        hour += minute / 60;
        minute %= 60;
    }
        
    hour %= 12;

    string strHour = to_string(hour);
    string strMinute = to_string(minute);
    string strSecond = to_string(second);

    if (hour < 10) 
    {
        strHour = "0" + strHour;
    }

    if (minute < 10) 
    {
        strMinute = "0" + strMinute;
    }

    if (second < 10) 
    {
        strSecond = "0" + strSecond;
    }

    return strHour + ":" + strMinute + ":" + strSecond;
}

int main()
{
    string input[10] = 
    {
        "RWGBG", "RCMGB", "BYYGR", "MRGBW", "YYYYY",
        "WGBGR", "CGRMY", "MMMMC", "CCCYY", "WWWWW"
    };

    string output[10] = 
    {
        "01:35:15", "03:20:40", "08:30:05", "02:10:20", "01:00:00",
        "05:20:10", "10:35:20", "07:26:00", "09:00:20", "00:00:00"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = findTime(input[i]);

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