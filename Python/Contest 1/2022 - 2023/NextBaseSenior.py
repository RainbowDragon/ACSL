#
# ACSL 2022-2023 - Contest 1 - Next Base - Senior Division
#
#

#
# Complete the 'findModeCount' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER num
#  2. INTEGER base
#  3. STRING start
#
def find_mode_count(num, base, start):

    result = 0
    counts = [0] * base
    start_value = int(start, base)

    for k in range(num):
        number = start_value + k

        while number > 0:
            digit = number % base
            number //= base
            counts[digit] += 1

    for j in range(base):
        if counts[j] > result:
            result = counts[j]

    return result


test_input = [
    [15, 8, "2"], [25, 2, "1111011"], [20, 12, "9AB"], [10, 16, "ABCDEF"], [1000, 2, "1"],
    [50, 4, "12"], [75, 9, "384"], [500, 14, "9CBA"], [700, 11, "AAA0"], [25, 2, "110000111010"]
]

test_output = [
    9, 105, 14, 10, 4938, 42, 88, 336, 940, 165
]

for i in range(10):
    test_result = find_mode_count(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
