#
# ACSL 2021-2022 - Contest 1 - Fibonacci Clock - Senior Division
#
#

#
# Complete the 'findTime' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING cstr as parameter.
#
def find_time(cstr):

    hour = 0
    minute = 0
    second = 0
    deltas = [1, 1, 2, 3, 5]

    for k in range(5):
        if cstr[k] == 'R' or cstr[k] == 'Y' or cstr[k] == 'M':
            hour += deltas[k]

        if cstr[k] == 'G' or cstr[k] == 'Y' or cstr[k] == 'C':
            minute += deltas[k]

        if cstr[k] == 'B' or cstr[k] == 'M' or cstr[k] == 'C':
            second += deltas[k]

    minute *= 5
    second *= 5

    if second >= 60:
        minute += second // 60
        second %= 60

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

    str_second = str(second)
    if second < 10:
        str_second = "0" + str_second

    return str_hour + ":" + str_minute + ":" + str_second


test_input = ["RWGBG", "RCMGB", "BYYGR", "MRGBW", "YYYYY", "WGBGR", "CGRMY", "MMMMC", "CCCYY", "WWWWW"]
test_output = [
    "01:35:15", "03:20:40", "08:30:05", "02:10:20", "01:00:00",
    "05:20:10", "10:35:20", "07:26:00", "09:00:20", "00:00:00"
]

for i in range(10):
    test_result = find_time(test_input[i])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
