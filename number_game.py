import random

def play_round(max_attempts=10):
    """
    Play one round of the number guessing game.
    Returns the number of attempts used if guessed correctly, else None.
    """
    number = random.randint(1, 100)
    attempts = 0
    print("I've generated a random number between 1 and 100. Can you guess it?")
    print(f"You have {max_attempts} attempts.")

    while attempts < max_attempts:
        try:
            guess = int(input("Enter your guess: "))
            attempts += 1

            if guess == number:
                print(f"Congratulations! You guessed it in {attempts} attempts.")
                return attempts
            elif guess < number:
                print("Too low!")
            else:
                print("Too high!")
        except ValueError:
            print("Please enter a valid integer.")

    print(f"Sorry, you've used all {max_attempts} attempts. The number was {number}.")
    return None

def main():
    total_score = 0
    rounds_played = 0

    while True:
        print("\n--- New Round ---")
        attempts = play_round()
        if attempts is not None:
            # Score: higher for fewer attempts, max 100 points
            score = max(0, 100 - (attempts - 1) * 10)
            total_score += score
            rounds_played += 1
            print(f"Round score: {score}")
        else:
            print("No score for this round.")

        play_again = input("Do you want to play another round? (y/n): ").lower()
        if play_again != 'y':
            break

    print(f"\nGame over! You played {rounds_played} rounds with a total score of {total_score}.")

if __name__ == "__main__":
    main()
