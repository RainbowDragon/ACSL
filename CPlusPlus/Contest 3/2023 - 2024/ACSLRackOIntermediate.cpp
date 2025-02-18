/**
 *      ACSL 2023-2024 - Contest 3 - ACSL Rack-O - Intermediate Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

bool updateWithRule1(vector<int> & rackList, int card)
{
    for (int i = 1; i < rackList.size(); i++)
    {
        if (card + 1 == rackList[i])
        {
            rackList[i-1] = card;
            return true;
        }
    }
    return false;
}

bool updateWithRule2(vector<int> & rackList, int card)
{
    for (int i = 0; i < rackList.size()-1; i++)
    {
        if (card - 1 == rackList[i])
        {
            rackList[i+1] = card;
            return true;
        }
    }
    return false;
}

bool updateWithRule3(vector<int> & rackList, int card)
{
    for (int i = 1; i < rackList.size()-1; i++)
    {
        if (card > rackList[i-1] && card < rackList[i+1] && !(rackList[i] > rackList[i-1] && rackList[i] < rackList[i+1]))
        {
            rackList[i] = card;
            return true;
        }
    }
    return false;
}

bool updateWithRule4(vector<int> & rackList, int card)
{
    if (card < rackList[1] && rackList[0] > rackList[1]) 
    {
        rackList[0] = card;
        return true;
    }    
    return false;
}

bool updateWithRule5(vector<int> & rackList, int card)
{
    int n = rackList.size();
    if (card > rackList[n-2] && rackList[n-1] < rackList[n-2]) 
    {
        rackList[n-1] = card;
        return true;
    }    
    return false;
}

bool isRackInOrder(vector<int> & rackList)
{
    for (int i = 1; i < rackList.size(); i++)
    {
        if (rackList[i] < rackList[i-1])
        {
            return false;
        }
    }
    return true;
}

int getRackValues1(vector<int> & rackList)
{
    int result = rackList[0];
    int count = 0;
    bool inSequence = false;    
    for (int i = 1; i < rackList.size(); i++)
    {
        result += rackList[i];
        if (rackList[i] - rackList[i-1] == 1) 
        {
            if (inSequence) 
            {
                count += 1;
            }
            else 
            {
                count += 2;
            }
            inSequence = true;
        }
        else 
        {
            if (count >= 3) 
            {
                result += count * 5;
            }
            inSequence = false;
            count = 0;
        }
    }

    if (count >= 3) 
    {
        result += count * 5;
    }

    return result;
}

int getRackValues2(vector<int> & rackList)
{
    int result = 0;
    for (int i = 1; i < rackList.size(); i++)
    {
        if (rackList[i] < rackList[i-1])
        {
            result--;
        }
    }
    return result;
}

/*
 * Complete the 'playRackO' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 * 1. STRING info
 * 2. STRING rack
 * 3. STRING pile
 */
int playRackO(string info, string rack, string pile)
{
    vector<int> rackList;
    stringstream rs(rack);
    string token;
    while (getline(rs, token, ' '))
    {
        rackList.push_back(stoi(token));
    }

    if (isRackInOrder(rackList))
    {
        return getRackValues1(rackList);
    }

    stringstream ps(pile);
    while (getline(ps, token, ' '))
    {
        int card = stoi(token);

        bool isUpdated = updateWithRule1(rackList, card);

        if (!isUpdated)
        {
            isUpdated = updateWithRule2(rackList, card);
        }

        if (!isUpdated)
        {
            isUpdated = updateWithRule3(rackList, card);
        }

        if (!isUpdated)
        {
            isUpdated = updateWithRule4(rackList, card);
        }

        if (!isUpdated)
        {
            isUpdated = updateWithRule5(rackList, card);
        }

        if (isUpdated && isRackInOrder(rackList))
        {
            return getRackValues1(rackList);
        }
    }

    return getRackValues2(rackList);
}

int main()
{
    string input[10][3] = 
    {
        {"10 60", "40 35 20 56 32 58 42 17 45 34", "31 44 10 28 19 46 7 37 16 2"},
        {"15 90", "15 12 18 9 28 17 46 51 7 53 65 70 74 84 47", "45 73 3 52 54 16 21 44 87 40 68 30 20"},
        {"12 130", "20 110 30 16 84 40 91 69 75 7 81 15", "39 47 114 55 35 71 25 123 51 23 34 10 77 36 115"},
        {"8 100", "6 13 47 62 32 70 76 12", "3 67 80 10 39 44 2 43 40 85 21 33 4 52"},
        {"12 110", "44 35 22 25 79 100 85 69 87 3 56 28", "97 10 48 43 42 21 81 47 86 88 80 54 24 50"},
        {"9 140", "74 135 61 45 92 122 14 98 138", "105 60 66 116 5 106 97 85 18 139 96"},
        {"15 70", "27 43 24 9 70 64 3 33 30 63 11 1 25 12 35", "69 15 10 2 34 66 21 49 23 51 5 57 38 40 53"},
        {"11 80", "68 52 8 25 22 18 29 16 74 48 34", "30 43 6 77 7 28 73 9 27 54 71 79 42 49"},
        {"15 60", "2 10 13 19 20 26 30 34 39 41 47 48 52 58 60", "9 31 50 59 1"},
        {"10 75", "70 65 60 55 50 45 40 35 30 20", "44 10 15 46 23 72 3 68 53 37 75 39"}
    };

    int output[10] = 
    {
        341, 752, 656, 421, -1, 728, -2, 342, 499, 428
    };

    for (int i = 0; i < 10; i++)
    {
        int result = playRackO(input[i][0], input[i][1], input[i][2]);

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