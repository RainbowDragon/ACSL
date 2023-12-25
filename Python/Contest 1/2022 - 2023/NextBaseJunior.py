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
def findDigitSum(num, base, start):

    result = 0

    for k in range(num):
        number = start + k
        digit_sum = 0

        while number > 0:
            digit_sum += number % base
            number //= base

        result += digit_sum

    return result


testInput = [[15, 8, 2], [20, 3, 5], [25, 5, 89], [13, 9, 1262], [45, 2, 123], [1000, 8, 8], [50, 4, 7], [75, 9, 319], [100, 6, 215], [25, 2, 3130]]
testOutput = [65, 64, 189, 212, 170, 10948, 225, 876, 675, 135]

for i in range(10):
    testResult = findDigitSum(testInput[i][0], testInput[i][1], testInput[i][2])

    if testResult == testOutput[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(testOutput[i]))
        print("Your output: " + str(testResult))
