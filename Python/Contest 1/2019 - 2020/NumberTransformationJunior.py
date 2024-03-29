#
# ACSL 2019-2020 - Contest 1 - Number Transformation - Junior Division
#
#

def transform_number(num, pos, delta):

    result = 0

    digits = [0] * 20
    index = 0
    while num > 0:
        digits[index] = num % 10
        num //= 10
        index += 1

    pos -= 1
    digit = digits[pos]
    if digit < 5:
        digit += delta
        digit %= 10
    else:
        digit -= delta
        if digit < 0:
            digit *= -1

        while digit > 9:
            digit //= 10

    digits[pos] = digit

    for k in range(pos):
        digits[k] = 0

    base = 1
    for k in range(index):
        result += digits[k] * base
        base *= 10

    return result


test_input = [[0] * 3] * 10
test_input[0] = [124987, 2, 3]
test_input[1] = [540670, 3, 9]
test_input[2] = [7145042, 2, 8]
test_input[3] = [124987, 2, 523]
test_input[4] = [4386709, 1, 2]
test_input[5] = [4318762, 4, 3]
test_input[6] = [72431685, 1, 7]
test_input[7] = [123456789, 7, 8]
test_input[8] = [9876543210, 10, 25]
test_input[9] = [314159265358, 8, 428]

test_output = [124950, 540300, 7145020, 124950, 4386707, 4315000, 72431682, 121000000, 1000000000, 314140000000]

for i in range(10):
    test_result = transform_number(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
