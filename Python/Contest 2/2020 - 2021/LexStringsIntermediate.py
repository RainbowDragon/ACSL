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
def rearrangedString(s, n):

    blocks = findBlocks(s)

    blocks.sort()
    blocks.sort(key=len, reverse=True)

    blocks = findBlocks("".join(blocks))

    for k in range(len(blocks)):
        if len(blocks[k]) > n:
            blocks[k] = blocks[k][:n]

    return "".join(blocks)


def findBlocks(s):

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


test_input = [[""] * 2] * 10
test_input[0] = ["MBAMMDXXMMMGGMMZ", "3"]
test_input[1] = ["MHHHHJLDDHHDDD", "3"]
test_input[2] = ["THETENNESSEEVOLUNTEERS", "2"]
test_input[3] = ["MISSISSIPPI", "3"]
test_input[4] = ["BOOOKEEEPEEERR", "4"]
test_input[5] = ["BOOOKEEEPEERBBBBUZZZOOKEEEEPEER", "2"]
test_input[6] = ["MASSACHUSETTSVSMISSISSIPPI", "2"]
test_input[7] = ["OOOOZESSPPOOOOOYYYSSSUPY", "4"]
test_input[8] = ["SHESELLSSEASHELLSANDBALLOONS", "3"]
test_input[9] = ["HHHGGRDDCFFFGGGTTTYUIKJHHH", "1"]

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
    "FGHTDGCIJKRUY"]

for i in range(10):
    test_result = rearrangedString(test_input[i][0], int(test_input[i][1]))

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
