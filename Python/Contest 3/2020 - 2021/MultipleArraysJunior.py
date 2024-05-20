#
# ACSL 2020-2021 - Contest 3 - MultipleArrays - Junior Division
#
#

#
# Complete the 'sumOfLargest' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING a1
#  2. STRING a2
#  3. STRING a3
#
def sum_of_largest(a1, a2, a3):

    list1 = [int(x) for x in a1.split()]
    list2 = [int(x) for x in a2.split()]
    list3 = [int(x) for x in a3.split()]

    max_len = max(len(list1), len(list2), len(list3))
    result = 0
    for k in range(max_len):
        temp = []
        if k < len(list1):
            temp.append(list1[k])
        if k < len(list2):
            temp.append(list2[k])
        if k < len(list3):
            temp.append(list3[k])
        result += max(temp)

    return result


test_input = [
    [
        "6 8 1 5 2 3 5 3 7 9",
        "7 6 2 9",
        "9 3 4 6 1 8 6 4 2 8 4"
    ],
    [
        "1 3 5 7 9 2 4 6 8 10",
        "5 2 6 4 8 7 9 11 14 12",
        "4 2 6 4 7 1 9 22 21 9"
    ],
    [
        "5 6 7 8 9 1 2",
        "9 8 7 6 5 0 1 2 3 4",
        "8 6 4 2 1 3 5 7"
    ],
    [
        "1",
        "1 2",
        "1 2 3"
    ],
    [
        "31 41 59 26 53 58 97 93 23 84 62 64 33 83 27",
        "56 89 23 14 26 37 48 59 61 72 83 94",
        "42 35 68 79 82 91 20 51 64 97 86"
    ],
    [
        "3 1 4 1 5 9 2 6 5 3 5 8 9 7",
        "9 3 2 3 8 4 6 2 7 9 8 5 3 5 6 2 9 5 1 4 1 3",
        "6 2 8 3 1 8 5 3 0 6"
    ],
    [
        "31 41 59 26 53 58 97 93 23 84 62 64 33 83 27",
        "21 32 43 54 65 76 87 98 90 70 50 30 10",
        "20 40 60 80 12 23 34 45 56 67 78 89"
    ],
    [
        "8765 4321 9012 3456 7890 321 654 987",
        "9123 5326 8975 345 789",
        "7654 6235 5798 6543 4567 32 54 1024 2048 4096"
    ],
    [
        "-5 -6 -7 -8 -9 -10 -11",
        "-1 -2 -3 -4 -12 -8 -10 -16 -14 -12 -10 -5",
        "-6 -9 -1 -2 -10 -7 -9 -21 -15 -10"
    ],
    [
        "1",
        "1",
        "1"
    ]
]

test_output = [70, 101, 63, 6, 1139, 131, 1032, 46946, -86, 1]

for i in range(10):
    test_result = sum_of_largest(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
