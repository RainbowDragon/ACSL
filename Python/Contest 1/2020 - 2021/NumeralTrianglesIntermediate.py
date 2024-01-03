#
# ACSL 2020-2021 - Contest 1 - Numeral Triangles - Intermediate Division
#
#

#
# Complete the 'sumOfLastRow' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER s
#  2. INTEGER d
#  3. INTEGER r
#
def sumOfLastRow(s, d, r):

    result = 0
    numbers_to_skip = r * (r - 1) // 2

    start_number = oct2decimal(s)
    delta = oct2decimal(d)
    current_number = start_number + delta * numbers_to_skip

    for k in range(r):
        result += sumOfOctDigit(current_number)
        current_number += delta

    return result

def oct2decimal(oct_number):

    number = 0
    base = 1

    while oct_number > 0:
        digit = oct_number % 10
        number += digit * base
        base *= 8
        oct_number //= 10

    return number

def sumOfOctDigit(number):

    digit_sum = 0

    while number > 0:
        digit_sum += number % 8
        number //= 8

    return digit_sum


test_input = [[0] * 3] * 10
test_input[0] = [2, 3, 5]
test_input[1] = [221, 2, 4]
test_input[2] = [1, 4, 20]
test_input[3] = [10, 10, 10]
test_input[4] = [3245, 5, 11]
test_input[5] = [4567, 7, 65]
test_input[6] = [3141, 5, 26]
test_input[7] = [765, 43, 21]
test_input[8] = [704, 1776, 20]
test_input[9] = [77, 7, 100]

test_output = [36, 38, 230, 99, 178, 1038, 429, 329, 374, 1547]

for i in range(10):
    test_result = sumOfLastRow(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
