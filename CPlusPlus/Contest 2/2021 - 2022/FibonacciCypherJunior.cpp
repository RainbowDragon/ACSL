/**
 *      ACSL 2021-2022 - Contest 2 - Fibonacci Cypher - Junior Division
 *
 */

#include <bits/stdc++.h>

using namespace std;

/*
 * Complete the 'fibCypher' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 * 1. CHARACTER key
 * 2. STRING msg
 */
string fibCypher(char key, string msg)
{
    int n = msg.length();

    int fibonacciNumber[n];
    fibonacciNumber[0] = 1;
    fibonacciNumber[1] = 2;
    for (int i = 2; i < n; i++)
    {
        fibonacciNumber[i] = fibonacciNumber[i-1] + fibonacciNumber[i-2];
    }

    string result = "";
    for (int i = 0; i < n; i++)
    {
        int offset = (fibonacciNumber[i] + key - 'a') % 26 + 'a';
        int encoded = offset + msg[i];
        result += to_string(encoded);
        if (i < n-1) 
        {
            result += " ";
        }
    }    

    return result;
}

int main()
{
    string input[10][2] = 
    {
        {"h", "ACSL c2"},
        {"s", "Python Programming"},
        {"a", "Fibonacci Numbers"},
        {"j", "Help ME!"},
        {"z", "It is 9:30 in the morning."},
        {"h", "ACSL Contest 2"},
        {"b", "Madam, I'm Adam!"},
        {"a", "Java programs are the best."},
        {"d", "What are we really doing?"},
        {"g", "This is the Fibonacci Cypher!"}
    };

    string output[10] = 
    {
        "170 173 190 185 144 216 149",
        "196 238 234 224 208 212 142 177 232 211 206 228 199 210 223 205 209 213",
        "168 204 198 213 215 207 217 204 205 140 189 239 219 207 223 222 222",
        "179 209 217 223 146 196 170 147",
        "170 214 131 206 219 141 174 162 150 155 142 226 219 140 237 211 207 149 210 233 215 211 211 221 224 156",
        "170 173 190 185 144 184 210 222 223 216 233 219 149 166",
        "176 197 201 200 215 155 151 179 140 218 144 162 211 207 206 142",
        "172 196 218 199 137 222 232 216 203 222 208 231 225 141 219 222 208 150 218 201 203 134 205 213 237 227 156",
        "188 206 200 221 140 210 235 209 135 230 215 131 227 213 196 219 218 242 137 200 216 210 220 218 162",
        "188 209 211 223 143 221 213 143 222 218 218 134 186 220 200 225 223 195 207 202 213 140 180 239 214 221 217 218 150"
    };

    for (int i = 0; i < 10; i++)
    {
        string result = fibCypher(input[i][0][0], input[i][1]);

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