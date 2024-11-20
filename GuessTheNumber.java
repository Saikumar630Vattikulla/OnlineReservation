import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxRounds = 3;
        int maxAttempts = 5;
        int totalScore = 0;

        System.out.println("Welcome to the Guess the Number Game!");

        // Loop through the rounds
        for (int round = 1; round <= maxRounds; round++) {
            int numberToGuess = random.nextInt(100) + 1;
            int attemptsLeft = maxAttempts;
            boolean roundWon = false;
            System.out.println("\nRound " + round + ":");
            System.out.println("Guess a number between 1 and 100.");

            // Each round allows the user a limited number of attempts
            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsLeft--;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the number.");
                    int pointsEarned = attemptsLeft + 1;  // Points based on remaining attempts
                    totalScore += pointsEarned;
                    System.out.println("You earned " + pointsEarned + " points for this round.");
                    roundWon = true;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!roundWon) {
                System.out.println("Sorry! You've used all attempts. The correct number was " + numberToGuess);
            }
        }

        // Display final score
        System.out.println("\nGame Over!");
        System.out.println("Your total score after " + maxRounds + " rounds is: " + totalScore);
        scanner.close();
    }
}