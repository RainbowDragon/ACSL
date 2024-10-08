/**
 *      ACSL 2023-2024 - Contest 1 - ACSL Tiles - Senior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'findHandSum' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 * 1. INTEGER originalRows
 * 2. STRING handTiles
 * 3. STRING drawPile
 */
int findHandSum(int originalRows, string handTiles, string drawPile)
{
    int rows[4];
    for (int i = 3; i >= 0; i--)
    {
        rows[i] = originalRows % 10;
        originalRows /= 10;
    }

    vector<int> hands;
    stringstream ss1(handTiles);
    string token1;
    while (getline(ss1, token1, ' '))
    {
        hands.push_back(stoi(token1));
    }

    vector<int> draws;
    stringstream ss2(drawPile);
    string token2;
    while (getline(ss2, token2, ' '))
    {
        draws.push_back(stoi(token2));
    }    

    int startIndex = 0;
    bool isLastDouble = false;
    while (!hands.empty())
    {
        bool isMatched = false;

        for (int i = 0; i < hands.size(); i++)
        {
            int tile = hands[i];
            int front = tile / 10;
            int back = tile % 10;

            int index = startIndex;

            if (isLastDouble) 
            {
                if (rows[index] == front) 
                {
                    isMatched = true;
                    rows[index] = back;
                }
                else if (rows[index] == back) 
                {
                    isMatched = true;
                    rows[index] = front;
                }
            }
            else 
            {
                for (int j = 0; j < 4; j++)
                {
                    index = (startIndex + j) % 4;
                    if (rows[index] == front) 
                    {
                        isMatched = true;
                        rows[index] = back;
                        break;
                    } 
                    else if (rows[index] == back) 
                    {
                        isMatched = true;
                        rows[index] = front;
                        break;
                    }
                }
            }

            if (isMatched) 
            {
                isLastDouble = (front == back);
                if (isLastDouble) 
                {
                    startIndex = index;
                }
                else 
                {
                    startIndex = index + 1;
                }

                hands.erase(hands.begin() + i);
                break;
            }
        }

        if (!isMatched) 
        {
            if (!draws.empty()) 
            {
                hands.push_back(draws[0]);
                draws.erase(draws.begin());
            }
            else 
            {
                break;
            }
        }
    }

    int result = 0;
    for (int i = 0; i < hands.size(); i++)
    {
        int tile = hands[i];
        result += ((tile / 10) + (tile % 10));
    }

    return result;
}

int main()
{
    string input[10][3] = 
    {
        {"5923", "56 27 73 34 99 45 32 17 64 57 18 11", "36 92 22 50 82"},
        {"1324", "85 31 32 96 25 1 68", "30 35 42 11 78 39 19 9 81"},
        {"7836", "57 62 19 97 3 11 28 92 66 87 45", "68 55 58 98 38 14 53 88 44 94 81 76 74 99 27 20"},
        {"4", "50 0 39 98 2 99 63 46 92 74 14 58 68 33 37", "51 42 95 60 67 77 84 7 96 8 35 10 19 22 11 82 40"},
        {"8937", "63 84 6 57 8 2 30 9 87 52 5", "58 40 62 54 27 96 35 99 61 56 14 51 88 13"},
        {"1453", "24 63 52 9 85 43 6 77 19", "1 33 47 57 46 21 13 84 56 82 39 50 55 16 92 70"},
        {"655", "23 55 55 45 94 99 12 99 89 32", "0"},
        {"2594", "53 44 48 68 93 95 12 3 0 87 81 74 91 15 23 63", "72 24 83 5 76 33 26 29 7 34 8 64 16"},
        {"51", "84 4 70 32 58 17 38 63 51 56 27", "60 43 0 99 50 95 20 82 25 88 10 64 14 45 66 81 53"},
        {"0", "93 70 65 9 66 14 46 68 20 63 21 71 88 30 31 1 75", "80 84 85 47 19 89 37 26 4 76 79 92 49 51 45 53 78"}
    };

    int output[10] = 
    {
        16, 0, 102, 16, 71, 38, 13, 11, 67, 0
    };

    for (int i = 0; i < 10; i++)
    {
        int result = findHandSum(stoi(input[i][0]), input[i][1], input[i][2]);

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