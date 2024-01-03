#
# ACSL 2019-2020 - Contest 1 - Number Transformation - Intermediate Division
#
#

def transformNumber(num, pos):

    result = 0

    digits = [0] * 20
    index = 0
    while num > 0:
        digits[index] = num % 10
        num //= 10
        index += 1

    pos -= 1
    for k in range(index):
        if k > pos:
            digits[k] += digits[pos]
            digits[k] %= 10
        elif k < pos:
            digits[k] -= digits[pos]
            if digits[k] < 0:
                digits[k] *= -1

    base = 1
    for k in range(index):
        result += digits[k] * base
        base *= 10

    return result


test_input = [[0] * 2] * 10
test_input[0] = [296351, 5]
test_input[1] = [762184, 3]
test_input[2] = [45873216, 7]
test_input[3] = [19750418, 6]
test_input[4] = [386257914, 5]
test_input[5] = [4318672, 4]
test_input[6] = [35197545, 1]
test_input[7] = [975318642, 9]
test_input[8] = [9876543210, 5]
test_input[9] = [314159265358, 10]

test_output = [193648, 873173, 95322341, 86727361, 831752441, 2198216, 80642095, 924681357, 3210941234, 754315221114]

for i in range(10):
    test_result = transformNumber(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))

