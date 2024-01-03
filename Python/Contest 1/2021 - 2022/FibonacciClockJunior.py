#
# ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Junior Division
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

    str_hour = str(hour)
    if hour < 10:
        str_hour = "0" + str_hour

    str_minute = str(minute)
    if minute < 10:
        str_minute = "0" + str_minute

    return str_hour + ":" + str_minute


test_input = [[0] * 5] * 10
test_input[0] = ['R', 'W', 'G', 'B', 'G']
test_input[1] = ['W', 'B', 'B', 'G', 'R']
test_input[2] = ['B', 'G', 'B', 'B', 'R']
test_input[3] = ['W', 'W', 'W', 'B', 'B']
test_input[4] = ['W', 'R', 'G', 'G', 'G']
test_input[5] = ['G', 'R', 'W', 'B', 'B']
test_input[6] = ['R', 'B', 'B', 'W', 'G']
test_input[7] = ['W', 'W', 'W', 'W', 'W']
test_input[8] = ['B', 'W', 'W', 'G', 'R']
test_input[9] = ['W', 'B', 'B', 'B', 'B']

test_output = ["04:50", "08:30", "11:35", "08:40", "01:50", "09:45", "04:40", "00:00", "06:20", "11:55"]

for i in range(10):
    test_result = findTime(test_input[i][0], test_input[i][1], test_input[i][2], test_input[i][3], test_input[i][4])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
