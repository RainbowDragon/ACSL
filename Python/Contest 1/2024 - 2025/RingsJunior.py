#
# ACSL 2024-2025 - Contest 1 - Rings - Junior Division
#
#

#
# Complete the 'scoreTosses' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
# 1. STRING tosses1
# 2. STRING tosses2
#
def score_tosses(tosses1, tosses2):

    score1 = get_score_from_string(tosses1)
    score2 = get_score_from_string(tosses2)

    if score1 >= score2:
        result = str(score1) + " " + str(score2)
    else:
        result = str(score2) + " " + str(score1)

    return result


def get_score(toss):

    score = 0

    if toss == 'A' or toss == 'R':
        score = 1
    elif toss == 'O' or toss == 'G':
        score = 3
    elif toss == 'B':
        score = 6

    return score


def get_score_from_string(tosses):

    score = 0

    for toss in tosses:
        score += get_score(toss)

    return score


test_input = [
    ["BRAG", "BOB"],
    ["AABBOOGG", "BAROBA"],
    ["GBORABORBA", "BAAAAOORGGGB"],
    ["OORRRGRBBRRAAABB", "AAAAGRRRRGRRRBBOO"],
    ["BARROGGBBGO", "BBAAAARRRGGGOOOO"],
    ["AAAABBBOOGGGRRR", "RRBBOOOGGGAAAA"],
    ["AROBG", "BRAGGROG"],
    ["BOBBRAGROB", "BARBAGGRAB"],
    ["BBBAAAOOOGGGRRR", "BBBBBAAAAGGGOOR"],
    ["RRRRRRBBBBGGGGGGAAAO", "AAAAAAOOOOOORRRBBBBB"],
    ["BABAGORBABAGORBABAGORBABAGOR", "GORBAGORBAGORBAGORBAGORBAGORBA"],
    ["RRRGGGGBBBBAAOOOBBBAAARRBB", "BBBBAAAAOOOOBBBGGGRRRGOAGOAGO"]
]

test_output = [
    "15 11", "26 18", "32 31", "42 35", "40 36", "40 36",
    "21 14", "36 29", "50 42", "57 54", "84 84", "90 85"
]

for i in range(12):
    test_result = score_tosses(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
