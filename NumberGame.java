import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_NUMBER = 100;
    private static final int MIN_NUMBER = 1;

    public static Integer playRound(Scanner scanner) {
        Random random = new Random();
        int number = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int attempts = 0;

        System.out.println("I've generated a random number between " + MIN_NUMBER + " and " + MAX_NUMBER + ". Can you guess it?");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts.");

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your guess: ");
            if (!scanner.hasNextLine()) {
                System.out.println("No more input. Ending round.");
                break;
            }
            try {
                int guess = Integer.parseInt(scanner.nextLine());
                attempts++;

                if (guess == number) {
                    System.out.println("Congratulations! You guessed it in " + attempts + " attempts.");
                    return attempts;
                } else if (guess < number) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }

        if (attempts >= MAX_ATTEMPTS) {
            System.out.println("Sorry, you've used all " + MAX_ATTEMPTS + " attempts. The number was " + number + ".");
        }
        return attempts > 0 && attempts <= MAX_ATTEMPTS ? attempts : null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;
        int roundsPlayed = 0;

        while (true) {
            System.out.println("\n--- New Round ---");
            Integer attempts = playRound(scanner);
            if (attempts != null) {
                // Score: higher for fewer attempts, max 100 points
                int score = Math.max(0, 100 - (attempts - 1) * 10);
                totalScore += score;
                roundsPlayed++;
                System.out.println("Round score: " + score);
            } else {
                System.out.println("No score for this round.");
            }

            System.out.print("Do you want to play another round? (y/n): ");
            if (!scanner.hasNextLine()) {
                break;
            }
            String playAgain = scanner.nextLine().toLowerCase();
            if (!playAgain.equals("y")) {
                break;
            }
        }

        System.out.println("\nGame over! You played " + roundsPlayed + " rounds with a total score of " + totalScore + ".");
        scanner.close();
    }
}
