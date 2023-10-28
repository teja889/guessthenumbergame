import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessTheNumberGame extends JFrame {
    private int numberToGuess;
    private int maxAttempts = 10; // Maximum attempts allowed
    private int rounds = 3; // Total rounds to play
    private int currentRound = 1; // Current round
    private int attempts;
    private int score = 0;

    private JTextField inputField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;
    private JLabel roundLabel;

    public GuessTheNumberGame() {
        setTitle("Guess the Number Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        inputField = new JTextField(10);
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("Enter a number between 1 and 100.");
        scoreLabel = new JLabel("Score: " + score);
        roundLabel = new JLabel("Round: " + currentRound + " / " + rounds);

        add(inputField);
        add(guessButton);
        add(resultLabel);
        add(scoreLabel);
        add(roundLabel);

        // Generate a random number to guess
        generateRandomNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        setVisible(true);
    }

    private void generateRandomNumber() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(inputField.getText());
            attempts++;

            if (userGuess < numberToGuess) {
                resultLabel.setText("Too low. Try again.");
            } else if (userGuess > numberToGuess) {
                resultLabel.setText("Too high. Try again.");
            } else {
                resultLabel.setText("Congratulations! You guessed it.");
                score += maxAttempts - attempts + 1; // Calculate score based on attempts
                scoreLabel.setText("Score: " + score);

                if (currentRound < rounds) {
                    currentRound++;
                    roundLabel.setText("Round: " + currentRound + " / " + rounds);
                    resultLabel.setText("Round " + currentRound + ": Enter a number between 1 and 100.");
                    attempts = 0;
                    generateRandomNumber();
                } else {
                    resultLabel.setText("Game Over! Your total score is " + score);
                    guessButton.setEnabled(false); // Disable the guess button after finishing all rounds
                }
            }

            if (attempts >= maxAttempts) {
                resultLabel.setText("Out of attempts. The number was " + numberToGuess + ".");
                if (currentRound < rounds) {
                    currentRound++;
                    roundLabel.setText("Round: " + currentRound + " / " + rounds);
                    resultLabel.setText("Round " + currentRound + ": Enter a number between 1 and 100.");
                    attempts = 0;
                    generateRandomNumber();
                } else {
                    resultLabel.setText("Game Over! Your total score is " + score);
                    guessButton.setEnabled(false); // Disable the guess button after finishing all rounds
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Enter a valid number.");
        }

        inputField.setText(""); // Clear the input field for the next guess
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GuessTheNumberGame();
            }
        });
    }
}
