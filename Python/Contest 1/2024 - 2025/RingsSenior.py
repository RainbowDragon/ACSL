#
# ACSL 2024-2025 - Contest 1 - Rings - Senior Division
#
#

#
# Complete the 'scoreTosses' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
# 1. INTEGER numPlayers
# 2. STRING_ARRAY tosses
#
def score_tosses(numPlayers, tosses):

    scores = []

    for i in range(numPlayers):
        toss_list = tosses[i].split(" ")
        score = get_score_from_string(toss_list)
        scores.append([i+1, len(toss_list), score])

    scores.sort(key = lambda x: (-x[2], x[1]))

    sb = []
    for i in range(numPlayers):
        sb.append(str(scores[i][0]) + "-" + str(scores[i][2]))

    return " ".join(sb)


def get_score(toss):

    score = 0

    if toss == 'A' or toss == 'R':
        score = 1
    elif toss == 'O' or toss == 'G':
        score = 3
    elif toss == 'B':
        score = 6

    return score


def get_score_from_string(toss_list):

    score = 0

    for toss in toss_list:
        score += get_score(toss[0])

        if len(toss) >= 2:
            if toss[1] == '+':
                score += 2
            else:
                score += get_score(toss[1]) + 1

        if len(toss) == 3:
            score += 2

    return score


test_input = [
    [
        "3",
        "A R AO+",
        "B+ GR OA B",
        "G OB+"
    ],
    [
        "3",
        "A B R G O",
        "AO OA BO OB GR RG BG GB",
        "A AO B OB R GR B GB"
    ],
    [
        "5",
        "A+ B R+ G O+",
        "AO OB+ GB RG",
        "A AO B R GR G GB O BO",
        "B R A G GR A BG OA R",
        "BG+ B+ G+ OA+ R+ A+ O+"
    ],
    [
        "3",
        "A+ R+ AO+ B+ BG+ R+",
        "B+ GR+ AO+ B+",
        "GR+ OB+ R+ AO+ G+ R+"
    ],
    [
        "4",
        "A R+ OA B AO OB BG GR B+",
        "AO OB B+ G BG BG+ GR+",
        "B+ G AO+ OB GR O+ B",
        "B+ B G AO+ AO BG GR G O+ G"
    ],
    [
        "6",
        "R R G G AO BG GB B",
        "AO GR+ A B AO+ BG BG BO",
        "A A R R G G O O",
        "BG GB GR+ RG O O+ O A+ B",
        "GR RG A+ A O O+ B G",
        "A B+ B O G+ G G AO OB GR BG"
    ],
    [
        "3",
        "G R G R B G B G O B O B O B A O A O A O A O",
        "B B B G G A A A O B A O B G B G A O A O",
        "G B O A A A A B B B O O O G G G A O O B G R B G"
    ],
    [
        "4",
        "GR RG BG GB OB BO OA AO",
        "G R O B A GB OA RG OB",
        "AO",
        "BG"
    ],
    [
        "5",
        "A O B+ GR OB R AO+ G R GR+ BG",
        "AO GR BG+ A R O+ G B AO OB BG GR+ O R R+ B",
        "BG AO+ BG A A B B G AO AO+ BG GR B G A O A",
        "AO OB R GR+ B G R AO OB GR O B+ O A B",
        "B+ O G AO OB B+ G A+ B BG GR A O G R"
    ],
    [
        "6",
        "B+ B+ R A O GR+ GR BG BG+ OB OB OB AO AO AO AO+ A R G B B",
        "AO BG GR GR B B G G+ G A A A GR OB AO BG BG AO+ AO",
        "A B G+ O R R GR BG OB AO A B B B O O G AO OB+ GR BG B",
        "GR AO BG A O B+ AO GR BG BG OB+ OB OB AO AO AO AO O G B A",
        "GR BG AO OB O+ O O B G A A GR OB AO BG+ AO G B B R",
        "A A+ R R BG G G O O+ O A A R R B B B BG+ G O O"
    ],
    [
        "6",
        "GR AO BG R R G+ G AO AO+ BG BG B R R A A G",
        "B A O R+ G G AO GR A B R G O BG BG OB B",
        "BG BG AO GR A A A B B R R G G G O O O O AO BG GR",
        "A A A B B+ B O O+ O G G G AO OB GR BG O R B O",
        "R B B G A BG BG GR GR A A A O+ O O B B G",
        "A R G B+ BG BG+ GR GR O O O A B AO OB BG"
    ],
    [
        "7",
        "A R B G+ O B G O+ A R B+ G AO AO BG GR GR BG OB OB OB AO",
        "B G O R A AO BG GR OB AO BG GR OB AO BG GR OB AO BG GR+ OB",
        "B R A G GR A B R OB+ B A R B OB R A G BG+ G AO OB OB OB BG",
        "OB+ GB+ GR BG+ RG+ BG+ OA+ OB+ G+ B+ GB+ BO+ AO BO+ G",
        "AO GR GR OB+ OB OB AO GR GR+ OB OB OB BG BG BG BG B R A G O",
        "AO OB+ GR BG+ OB GR+ OB GR+ BG AO+ OB GR+ BG+ R+ A+ G O+ B+",
        "BG OA+ GR B+ B R+ RG A+ G G AO O B+ A G GB OB+ BO OA+ O G"
    ]
]

test_output = [
    "2-24 3-15 1-9",
    "2-60 3-44 1-14",
    "3-44 5-43 4-33 2-32 1-20",
    "3-37 1-36 2-30",
    "2-55 4-55 1-53 3-44",
    "6-59 2-56 4-52 1-39 5-31 3-16",
    "3-78 1-69 2-66",
    "1-60 2-44 4-10 3-5",
    "3-85 2-83 4-74 5-72 1-56",
    "4-127 1-124 3-113 5-105 2-103 6-76",
    "3-88 6-86 4-86 2-79 5-76 1-75",
    "5-148 4-136 6-136 2-136 3-125 7-118 1-118"
]

for i in range(12):
    test_result = score_tosses(int(test_input[i][0]), test_input[i][1:])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
