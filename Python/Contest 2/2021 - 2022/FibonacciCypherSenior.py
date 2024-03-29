#
# ACSL 2021-2022 - Contest 2 - Fibonacci Cypher - Senior Division
#
#

#
# Complete the 'fibCypher' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
# 1. CHARACTER option
# 2. INTEGER num1
# 3. INTEGER num2
# 4. CHARACTER key
# 5. STRING msg
#
def fib_cypher(option, num1, num2, key, msg):

    n = len(msg)

    fib_numbers = [0] * 200
    fib_numbers[0] = num1 % 26
    fib_numbers[1] = num2 % 26
    for j in range(198):
        fib_numbers[j+2] = (fib_numbers[j+1] + fib_numbers[j]) % 26

    result = ""
    sign = 1
    if option == "E":
        for k in range(n):
            offset = (fib_numbers[k] * sign + ord(key) - ord('a') + 26) % 26 + ord('a')
            encoded = offset * 3 + ord(msg[k])
            result += str(encoded)
            sign *= -1
            if k < n - 1:
                result += " "
    else:
        tokens = msg.split()
        k = 0
        for token in tokens:
            offset = (fib_numbers[k] * sign + ord(key) - ord('a') + 26) % 26 + ord('a')
            decoded = int(token) - offset * 3
            result += chr(decoded)
            sign *= -1
            k += 1

    return result


test_input = [[""] * 5] * 10
test_input[0] = ["E", 3, 7, "h", "ACSL Sr-5 c2"]
test_input[1] = ["E", 0, 1, "s", "Python Programming is easier than programming in Java."]
test_input[2] = ["D", 2, 5, "j", "396 404 447 472 329 380 381 341 423 411 436 408 474 428 383 405 414 431 332 437 474 485 347 407 453 377 430 459 471 421 401 353 413 446 456 424 392 457 447 441 419 435 413 442 330"]
test_input[3] = ["E", 7, 10, "a", "Fibonacci Numbers are found in many places including the Mandelbrot Set."]
test_input[4] = ["D", 6, 1, "z", "379 479 341 447 448 329 381 397 402 402 395 462 404 383 425 434 446 383 469 468 405 464 408 449 433 329 390 425 429 395 446 420 449 368 417 397 363 363 395 429 443 383 464 395 446 344 408 458 445 431 335 367 402 394 475 419 391"]
test_input[5] = ["E", 1, 2, "a", "Fibonacci cyphers are fun to encode and decode."]
test_input[6] = ["D", 4, 7, "x", "383 450 432 338 400 394 475 335 444 440 471 448 350 408 424 443 359 364 355 388 371 332 383 448 436 412 407 344 430 449 454 377 371 457 431 407 405 401 395 471 467 323 420 400 426 443 452 459 448 436 419 419 403"]
test_input[7] = ["D", 7, 10, "p", "434 403 409 430 421 401 323 397 342 466 392 398 463 403 424 359 429 454 353 465 413 410 362 426 453 332 425 431 419 377 418 405 466 473 362 462 414 427 430 444 434 377 427 463 471 444 470 415 417 459 400 416 427 385"]
test_input[8] = ["E", 16, 9, "l", "What happens when the first number is greater than the second?"]
test_input[9] = ["D", 11, 13, "q", "378 404 434 338 480 454 464 453 450 380 441 459 344 356 377 397 406 439 409 459 392 440 416 323 418 465 425 405 404 436 393 405 432 350 458 426 409 446 392 432 427 377 411 410 377 410 416 422 362 445 440 443 465 422 476 393 434 332 391 457 478 432 420 445 399 413 431 433 383 422 410 440 338 365 462 468 430 392 410 362 419 477 475 431 395 414 377 338 478 461 453 392 371 467 434 462 411 410 377 405 421 365 409 472 403 444 411 411 394 392 376 340 348 388 350 352 373 364"]

test_output = [
    "386 358 425 415 347 419 405 402 377 377 390 416",
    "425 463 464 443 465 440 323 386 444 432 457 426 406 457 415 414 452 415 329 465 475 377 461 427 412 405 443 417 338 458 413 397 464 323 442 420 402 463 468 448 457 457 450 458 445 383 441 470 353 380 457 409 433 346",
    "Help ME figure out how to solve this problem!",
    "382 444 440 477 455 409 423 456 441 344 393 483 427 437 449 474 472 326 451 423 437 374 465 459 423 443 442 392 456 410 374 436 403 422 484 350 448 459 451 465 458 415 380 426 428 393 423 465 436 408 434 451 377 410 446 422 344 377 400 476 406 452 432 416 411 423 470 359 401 401 425 406",
    "It is 9:30 in the morning EST but 6:30 on the West Coast.",
    "364 468 398 465 425 427 453 444 405 368 432 415 442 437 467 450 436 338 403 405 407 386 423 441 476 359 446 477 365 425 410 414 465 430 416 338 397 407 394 326 391 395 465 408 460 407 391",
    "You may only use C++, Java, and Python in HackerRank.",
    "Madam, I'm Adam is one of the most common palindromes.",
    "381 401 418 416 377 461 409 403 457 449 431 466 326 422 419 413 437 341 458 395 398 350 405 444 480 412 431 338 443 414 469 437 446 432 383 396 421 341 424 426 434 400 470 452 441 380 419 395 433 467 335 416 431 398 386 460 434 435 432 449 406 420",
    "The ratio of 2 adjacent Fibonacci numbers in the sequence approaches the Golden number, phi, which is approx. 1.618..."
]

for i in range(10):
    test_result = fib_cypher(test_input[i][0][0], test_input[i][1], test_input[i][2], test_input[i][3][0], test_input[i][4])

    if test_result == test_output[i]:
        print("Test Case " + str(i) + ": Passed!")
    else:
        print("Test Case " + str(i) + ": Failed!")
        print("Expected output: " + str(test_output[i]))
        print("Your output: " + str(test_result))
