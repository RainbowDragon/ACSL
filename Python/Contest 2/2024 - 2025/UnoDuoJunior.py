#
# ACSL 2023-2024 - Contest 2 - Uno Duo - Junior Division
#
#

#
# Complete the 'printPile' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
# 1. STRING pile
# 2. STRING hand
#
def print_pile(pile, hand):

    card_list = hand.split(" ")
    result = [ pile ]

    is_done = False
    while not is_done:
        top = result[0]
        found = False
        for card in card_list:
            if is_matched(top, card):
                found = True
                result.insert(0, card)
                card_list.remove(card)
                break

        is_done = not found

    result.reverse()

    return " ".join(result)


def is_matched(card1, card2):

    score = 0
    for i in range(3):
        if card1[i] == card2[i]:
            score += 1

    return score >= 2


test_input = [
    [ "B3O", "R3X B1T B2T R1X B3X B1X Y4O" ],
    [ "R4S", "R4X B4X B3X B3T G3T G1T R1O" ],
    [ "B1T", "G1O B1O R1T B4T G4O R4O R1X" ],
    [ "G3S", "R3S G1O B4X G2S Y4S G3T G3X" ],
    [ "Y4O", "Y1S G4S B4S G2S R1T G2X Y2T" ],
    [ "G2T", "Y1S G4S B4S G2S R1T G2X Y2T" ],
    [ "Y2S", "B2S Y1X G2S R4S G1O Y4T G2T" ],
    [ "R1S", "G4S B1T G1T B4S Y2X Y1S Y1T" ],
    [ "R2X", "Y2T Y1T G2T B4O G1X R4X B3S" ],
    [ "G1O", "R1O B3S G1X R3O R4O R4T B3X" ],
    [ "R1S", "G1S B1T G1T Y4S Y2S Y1S Y1T" ],
    [ "Y4O", "G1X Y1O Y1X R2X B4S B4O R1X" ]
]

test_output = [
    "B3O B3X R3X R1X B1X B1T B2T",
    "R4S R4X B4X B3X B3T G3T G1T",
    "B1T B1O G1O G4O R4O",
    "G3S R3S",
    "Y4O",
    "G2T G2S G4S B4S",
    "Y2S B2S G2S G2T",
    "R1S Y1S Y1T B1T G1T",
    "R2X R4X",
    "G1O R1O R3O R4O R4T",
    "R1S G1S G1T B1T Y1T Y1S Y4S Y2S",
    "Y4O Y1O Y1X G1X R1X R2X"
]

for i in range(12):
    test_result = print_pile(test_input[i][0], test_input[i][1])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + test_output[i])
        print("Your output: " + test_result)
