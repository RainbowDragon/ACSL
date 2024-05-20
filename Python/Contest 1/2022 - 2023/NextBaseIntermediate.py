#
# ACSL 2022-2023 - Contest 1 - Next Base - Intermediate Division
#
#

#
# Complete the 'countLargestDigit' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER num
#  2. INTEGER base
#  3. INTEGER start
#
def count_largest_digit(num, base, start):

    result = 0
    start_value = int(str(start), base)
    largest_digit = base - 1

    for k in range(num):
        number = start_value + k
        counter = 0

        while number > 0:
            if number % base == largest_digit:
                counter += 1

            number //= base

        result += counter

    return result


test_input = [
    [15, 8, 2], [20, 3, 12], [25, 5, 324], [13, 9, 1652], [45, 2, 1111011],
    [1000, 8, 10], [50, 4, 13], [75, 9, 384], [100, 6, 555], [25, 2, 110000111010]
]

test_output = [2, 21, 24, 1, 170, 357, 34, 13, 31, 135]

for i in range(10):
    test_result = count_largest_digit(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
