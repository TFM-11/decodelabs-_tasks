import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Project 1: Number Guessing Game
 * Engineering a Random Logic Engine
 * Powered by DecodeLabs
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int totalScore = 0;
        int roundsPlayed = 0;

        System.out.println("===========================================");
        System.out.println("   WELCOME TO THE DECODELABS NUMBER GAME   ");
        System.out.println("===========================================");
        System.out.println("I have generated a random number between 1 and 100.");
        System.out.println("Can you guess it?");

        while (playAgain) {
            roundsPlayed++;
            int targetNumber = random.nextInt(100) + 1;
            int attempts = 0;
            int maxAttempts = 10; // Optional enhancement: Limit number of attempts
            boolean roundWon = false;

            System.out.println("\n--- Round " + roundsPlayed + " ---");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            while (attempts < maxAttempts && !roundWon) {
                attempts++;
                System.out.print("Attempt " + attempts + "/" + maxAttempts + ": Enter your guess: ");
                
                try {
                    int userGuess = scanner.nextInt();
                    
                    // Logic Architecture: Feedback Loop
                    if (userGuess == targetNumber) {
                        System.out.println("CORRECT! You've guessed the number in " + attempts + " attempts.");
                        roundWon = true;
                        // Score tracking: fewer attempts = higher score
                        totalScore += (maxAttempts - attempts + 1) * 10;
                    } else if (userGuess > targetNumber) {
                        System.out.println("Too High! Try a lower number.");
                    } else {
                        System.out.println("Too Low! Try a higher number.");
                    }
                } catch (InputMismatchException e) {
                    // Defensive Engineering: Input Validation
                    System.out.println("ERROR: Invalid input. Please enter a valid integer.");
                    scanner.next(); // Clear the invalid input from the scanner buffer
                    attempts--; // Don't count invalid input as an attempt
                }
            }

            if (!roundWon) {
                System.out.println("\nGAME OVER for this round! You've run out of attempts.");
                System.out.println("The hidden number was: " + targetNumber);
            }

            // Session Persistence: Play Again prompt
            System.out.print("\nDo you want to play another round? (Y/N): ");
            scanner.nextLine(); // Hazard Warning: Flush the buffer after nextInt()
            String response = scanner.nextLine().trim().toUpperCase();
            
            if (!response.equals("Y")) {
                playAgain = false;
            }
        }

        // Final Score Display
        System.out.println("\n===========================================");
        System.out.println("              FINAL RESULTS                ");
        System.out.println("===========================================");
        System.out.println("Rounds Played: " + roundsPlayed);
        System.out.println("Total Score:   " + totalScore);
        System.out.println("Thank you for playing with DecodeLabs!");
        System.out.println("===========================================");
        
        scanner.close();
    }
}
