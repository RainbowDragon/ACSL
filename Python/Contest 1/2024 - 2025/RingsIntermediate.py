#
# ACSL 2024-2025 - Contest 1 - Rings - Intermediate Division
#
#

#
# Complete the 'scoreTosses' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
# 1. STRING tosses1
# 2. STRING tosses2
# 3. STRING tosses3
#
def score_tosses(tosses1, tosses2, tosses3):

    scores = [
        get_score_from_string(tosses1),
        get_score_from_string(tosses2),
        get_score_from_string(tosses3)
    ]

    scores.sort()

    return str(scores[2]) + " " + str(scores[1]) + " " + str(scores[0])


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

    toss_list = tosses.split(" ")
    for toss in toss_list:
        score += get_score(toss[0])
        if len(toss) == 2:
            score += get_score(toss[1]) + 1

    return score


test_input = [
    [
        "GR A B",
        "OB BG AO O",
        "R G"
    ],
    [
        "A R O G B",
        "A A B B",
        "R O B"
    ],
    [
        "AO OB B G A B BG GR",
        "R R G G AO AO BG BG B",
        "AO GR A B R G O BG OB"
    ],
    [
        "A A A B B R O O O O",
        "BG BG GR GR O O O A B",
        "BG BG GR GR O O O B B G"
    ],
    [
        "A B B O O G AO OB GR BG",
        "GR GR BG BG OB AO AO",
        "B B B G G G A A A GR AO"
    ],
    [
        "GR BG OB AO A G B",
        "A O B AO GR GR BG O GR AO",
        "O O B G A A GR OB AO BG"
    ],
    [
        "A R R B B B G G O",
        "G R A B B R A G O O",
        "A R B G O B G A"
    ],
    [
        "AO AO BG GR BG OB OB OB AO",
        "BG BG BG BG AO AO AO GR OB OB",
        "BG GR AO AO GR BG OB OB"
    ],
    [
        "B G O R A B G O O R A B G O R A B G O R A",
        "AO BG GR OB AO BG GR B A O BG GR OB",
        "B R A G B OB R A G B O G AO BG BG AO GR"
    ],
    [
        "AO GR GR OB O B OB BG B G BG BG AO G R GR OB BG BG",
        "AO OB G R BG AO O B GR BG AO OB GR BG A GR BG AO OB GR BG",
        "AO OB GR BG AO OB G R BG R A G OB B AO"
    ],
    [
        "AO OB GR BG A B R G O",
        "OB GR BG AO B R G O A",
        "GR BG AO OB R G O A B"
    ],
    [
        "O BG GR OB OB B A GR BG BG BG OB",
        "O G A GR OB AO BG BG B G AO OB OB OB BG",
        "B B B B B B B B B B B B B B B"
    ]
]

test_output = [
    "28 12 4", "14 14 10", "46 44 44", "54 46 28", "52 50 40", "48 47 40",
    "30 28 24", "80 70 60", "85 79 59", "134 127 85", "44 44 44", "101 90 90"
]

for i in range(12):
    test_result = score_tosses(test_input[i][0], test_input[i][1], test_input[i][2])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
