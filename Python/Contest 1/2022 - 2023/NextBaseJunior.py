#
# ACSL 2022-2023 - Contest 1 - Next Base - Junior Division
#
#

#
# Complete the 'findDigitSum' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
# 1. INTEGER num
# 2. INTEGER base
# 3. INTEGER start
#
def find_digit_sum(num, base, start):

    result = 0
    start_value = int(str(start), base)

    for k in range(num):
        number = start_value + k
        digit_sum = 0

        while number > 0:
            digit_sum += number % base
            number //= base

        result += digit_sum

    return result


test_input = [
    [15, 8, 2], [20, 3, 12], [25, 5, 324], [13, 9, 1652], [45, 2, 1111011],
    [1000, 8, 10], [50, 4, 13], [75, 9, 384], [100, 6, 555], [25, 2, 110000111010]
]

test_output = [65, 64, 189, 212, 170, 10948, 225, 876, 675, 135]

for i in range(10):
    test_result = find_digit_sum(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
