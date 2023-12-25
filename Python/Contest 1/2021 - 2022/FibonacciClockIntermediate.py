#
# ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Intermediate Division
#
#

#
# Complete the 'findTime' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
# 1. CHARACTER c1
# 2. CHARACTER c2
# 3. CHARACTER c3
# 4. CHARACTER c4
# 5. CHARACTER c5
#
def findTime(c1, c2, c3, c4, c5):

    hour = 0
    minute = 0
    colors = [c1, c2, c3, c4, c5]
    deltas = [1, 1, 2, 3, 5]

    for k in range(5):
        if colors[k] == 'R' or colors[k] == 'B':
            hour += deltas[k]

        if colors[k] == 'G' or colors[k] == 'B':
            minute += deltas[k]

    minute *= 5

    if minute >= 60:
        hour += minute // 60
        minute %= 60

    hour %= 12

    str_hour = str(hour)
    if hour < 10:
        str_hour = "0" + str_hour

    str_minute = str(minute)
    if minute < 10:
        str_minute = "0" + str_minute

    return str_hour + ":" + str_minute


testInput = [['R', 'W', 'G', 'B', 'G'], ['W', 'B', 'B', 'G', 'R'], ['W', 'G', 'B', 'R', 'B'], ['G', 'G', 'B', 'B', 'B'], ['W', 'R', 'G', 'G', 'G'], ['G', 'R', 'W', 'B', 'B'], ['G', 'G', 'B', 'G', 'B'], ['R', 'R', 'R', 'R', 'R'], ['G', 'G', 'G', 'G', 'G'], ['W', 'B', 'B', 'B', 'B']]
testOutput = ["04:50", "08:30", "10:40", "11:00", "01:50", "09:45", "08:00", "00:00", "01:00", "11:55"]

for i in range(10):
    testResult = findTime(testInput[i][0], testInput[i][1], testInput[i][2], testInput[i][3], testInput[i][4])

    if testResult == testOutput[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(testOutput[i]))
        print("Your output: " + str(testResult))
