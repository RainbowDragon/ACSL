#
# ACSL 2020-2021 - Contest 2 - Lex Strings - Intermediate Division
#
#

#
# Complete the 'rearrangeString' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. STRING s
#  2. INTEGER n
#
def rearranged_string(s, n):

    blocks = find_blocks(s)

    blocks.sort()
    blocks.sort(key=len, reverse=True)

    blocks = find_blocks("".join(blocks))

    for k in range(len(blocks)):
        if len(blocks[k]) > n:
            blocks[k] = blocks[k][:n]

    return "".join(blocks)


def find_blocks(s):

    blocks = []
    last_index = 0
    last_char = ' '
    for k in range(len(s)):
        if s[k] != last_char:
            blocks.append(s[last_index:k])
            last_index = k
            last_char = s[k]

    blocks.append(s[last_index:len(s)])

    return blocks


test_input = [
    ["MBAMMDXXMMMGGMMZ", "3"],
    ["MHHHHJLDDHHDDD", "3"],
    ["THETENNESSEEVOLUNTEERS", "2"],
    ["MISSISSIPPI", "3"],
    ["BOOOKEEEPEEERR", "4"],
    ["BOOOKEEEPEERBBBBUZZZOOKEEEEPEER", "2"],
    ["MASSACHUSETTSVSMISSISSIPPI", "2"],
    ["OOOOZESSPPOOOOOYYYSSSUPY", "4"],
    ["SHESELLSSEASHELLSANDBALLOONS", "3"],
    ["HHHGGRDDCFFFGGGTTTYUIKJHHH", "1"]
]

test_output = [
    "MMMGGMMMXXABDMZ",
    "HHHDDDHHJLM",
    "EENNSSEEHLNORSTTUV",
    "PPSSSIIIM",
    "EEEEOOORRBKP",
    "BBEEOOZZEEOOBKKPPRRU",
    "PPSSTTAACEHIIMMSSUV",
    "OOOOSSSYYYPPSSEPUYZ",
    "LLLOOSSAAABDEEEHHNNSSS",
    "FGHTDGCIJKRUY"
]

for i in range(10):
    test_result = rearranged_string(test_input[i][0], int(test_input[i][1]))

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
