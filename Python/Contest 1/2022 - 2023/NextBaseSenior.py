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
def findModeCount(num, base, start):

    result = 0
    counts = [0] * base

    for k in range(num):
        number = start + k

        while number > 0:
            digit = number % base
            number //= base
            counts[digit] += 1

    for j in range(base):
        if counts[j] > result:
            result = counts[j]

    return result


testInput = [[15, 8, 2], [25, 2, 123], [20, 12, 1427], [10, 16, 11259375], [1000, 2, 1], [50, 4, 6], [75, 9, 319], [500, 14, 27212], [700, 11, 14630], [25, 2, 3130]]
testOutput = [9, 105, 14, 10, 4938, 42, 88, 336, 940, 165]

for i in range(10):
    testResult = findModeCount(testInput[i][0], testInput[i][1], testInput[i][2])

    if testResult == testOutput[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(testOutput[i]))
        print("Your output: " + str(testResult))
