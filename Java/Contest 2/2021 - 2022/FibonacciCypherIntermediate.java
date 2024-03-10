/**
 *      ACSL 2021-2022 - Contest 2 - Fibonacci Cypher - Intermediate Division
 *
 */

import java.io.*;
import java.lang.*;
import java.util.*;

public class FibonacciCypherIntermediate {

    /*
     * Complete the 'fibCypher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     * 1. INTEGER num1
     * 2. INTEGER num2
     * 3. CHARACTER key
     * 4. STRING msg
     */
    static String fibCypher (int num1, int num2, char key, String msg) {

        int n = msg.length();

        int[] fibonacciNumber = getFibonacciNumber(num1, num2);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++)
        {
            int offset = (fibonacciNumber[i % 20] + key - 'a') % 26 + 'a';
            int encoded = offset * 3 + msg.charAt(i);
            sb.append(encoded);
            if (i < n-1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    static int[] getFibonacciNumber (int num1, int num2) {

        int[] fibonacciNumber = new int[20];
        fibonacciNumber[0] = num1;
        fibonacciNumber[1] = num2;

        for (int i = 2; i < 20; i++)
        {
            fibonacciNumber[i] = fibonacciNumber[i-1] + fibonacciNumber[i-2];
        }

        return fibonacciNumber;
    }

    public static void main (String [] args) {

        String[][] input = new String[10][];
        input[0] = new String[]{"3", "7", "h", "ACSL c2"};
        input[1] = new String[]{"4", "9", "s", "Python Programming is easier than programming in Java."};
        input[2] = new String[]{"2", "5", "a", "Fibonacci Numbers are found in many places including the Mandelbrot Set."};
        input[3] = new String[]{"0", "1", "j", "Help ME figure out how to solve this problem!"};
        input[4] = new String[]{"4", "8", "z", "It is 9:30 in the morning EST, but 6:30 on the West Coast."};
        input[5] = new String[]{"1", "2", "a", "Fibonacci cyphers are fun to encode and decode."};
        input[6] = new String[]{"4", "7", "x", "You may only use C++, Java, and Python in HackerRank."};
        input[7] = new String[]{"7", "10", "p", "Madam, I'm Adam is one of the most common palindromes."};
        input[8] = new String[]{"16", "9", "l", "What happens when the first number is greater than the second?"};
        input[9] = new String[]{"11", "13", "q", "The ratio of 2 adjacent Fibonacci numbers in the sequence approaches the Golden number, phi, which is approx. 1.618..."};

        String[] output = {
                "386 400 425 439 347 465 341",
                "437 415 422 437 405 470 341 404 480 456 469 480 406 439 403 462 416 421 389 435 472 326 407 430 409 465 410 438 398 461 470 463 419 362 406 471 417 421 471 427 466 403 411 443 397 392 414 434 398 419 463 484 406 376",
                "367 411 410 438 458 403 462 399 399 335 384 435 442 458 425 429 463 326 448 468 398 338 414 438 465 416 463 332 399 413 338 427 430 470 445 347 460 402 448 453 398 421 344 432 458 405 471 417 394 408 416 421 365 476 428 416 380 371 448 464 397 407 420 425 462 417 479 332 377 404 422 364",
                "390 422 429 436 359 410 411 389 405 447 430 468 474 416 389 465 432 467 380 407 429 440 353 440 438 365 457 468 411 460 428 383 476 419 462 469 347 463 462 414 416 429 422 433 360",
                "373 428 356 453 421 398 363 364 375 390 332 459 476 386 470 446 431 338 457 477 414 422 429 458 409 398 375 389 408 386 332 452 483 470 386 396 388 357 396 398 411 422 356 464 410 467 338 393 425 457 416 386 433 465 451 457 446 352",
                "364 402 398 417 425 427 453 414 405 356 432 487 442 431 467 438 436 386 403 405 395 329 402 423 425 362 470 426 332 425 443 465 441 427 467 356 418 464 406 323 394 398 399 417 415 431 400",
                "383 414 432 368 400 442 475 371 444 422 471 436 350 468 424 401 359 412 355 340 338 335 389 433 409 442 398 371 430 422 463 347 398 472 425 404 438 455 344 402 404 335 387 433 390 452 455 453 415 409 473 422 364",
                "434 463 409 436 421 359 323 421 342 424 392 404 463 463 424 377 429 448 353 429 467 467 341 450 414 347 407 452 404 347 469 450 478 482 347 444 435 442 430 429 467 398 421 436 420 420 401 448 417 426 469 440 478 412",
                "381 455 418 464 377 395 409 469 457 401 431 412 326 464 419 437 437 371 458 461 395 383 423 453 459 406 428 389 455 417 430 395 395 459 347 441 442 371 445 471 395 448 437 449 459 323 428 461 442 410 353 413 398 446 347 451 428 438 453 467 394 414",
                "378 404 434 326 480 418 464 435 450 362 441 423 344 344 377 397 406 451 409 417 395 410 449 326 436 426 446 441 449 427 429 420 417 326 455 417 415 443 413 432 409 332 438 404 398 437 452 431 371 445 431 434 429 395 455 399 407 377 409 430 406 414 444 391 465 425 449 445 371 446 434 422 344 365 456 408 406 446 422 350 404 417 442 392 467 435 392 362 451 434 435 365 344 413 449 405 405 449 344 423 409 332 430 406 478 435 459 450 385 362 379 367 366 343 401 346 352 391"
        };

        for (int i = 0; i < 10; i++)
        {
            String result = fibCypher(Integer.parseInt(input[i][0]), Integer.parseInt(input[i][1]), input[i][2].charAt(0), input[i][3]);

            if (output[i].equals(result)) {
                System.out.println("Test Case " + i + ": Passed!");
            }
            else {
                System.out.println("Test Case " + i + ": Failed!");
                System.out.println("Expected output: " + output[i]);
                System.out.println("Your output: " + result);
            }
        }
    }
}