#
# ACSL 2019-2020 - Contest 1 - Number Transformation - Senior Division
#
#

def transformNumber(num, pos):

    result = 0
    number_prime_factors = countPrimeFactors(num)
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
        elif k < pos:
            digits[k] -= digits[pos]
            if digits[k] < 0:
                digits[k] *= -1
    digits[pos] = number_prime_factors

    base = 1
    for k in range(index):
        result += digits[k] * base
        base *= 10
        if digits[k] > 9:
            base *= 10

    return result


def countPrimeFactors(num):

    count = 0

    if num % 2 == 0:
        count += 1
        while num % 2 == 0:
            num //= 2

    factor = 3
    while factor * factor <= num:
        if num % factor == 0:
            count += 1
            while num % factor == 0:
                num //= factor
        factor += 2

    if num > 2:
        count += 1

    return count


test_input = [[0] * 2] * 10
test_input[0] = [102438, 3]
test_input[1] = [4329, 1]
test_input[2] = [6710, 2]
test_input[3] = [16807, 1]
test_input[4] = [60098065452, 7]
test_input[5] = [43287, 3]
test_input[6] = [72431685, 1]
test_input[7] = [246897531573, 12]
test_input[8] = [96783, 5]
test_input[9] = [16058314729, 3]

test_output = [546414, 1312113, 7841, 8131571, 1488173823436, 65365, 12798611133, 424675311351, 23216, 8137121510811152]

for i in range(10):
    test_result = transformNumber(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
