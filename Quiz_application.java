import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz_application {
    private static final int TIME_LIMIT_SECONDS = 20;
    private static final String[] QUESTIONS = {
        "Question 1: What is the capital of France?",
        "Question 2: What is the largest planet in our solar system?",
        "Question 3: Who painted the Mona Lisa?"
    };
    private static final String[] OPTIONS = {
        "A) London  B) Paris  C) Rome",
        "A) Jupiter  B) Saturn  C) Earth",
        "A) Michelangelo  B) Leonardo da Vinci  C) Pablo Picasso"
    };
    private static final char[] ANSWERS = {'B', 'A', 'B'};

    private static int score = 0;
    private static int currentQuestion = 0;
    private static Timer timer;

    public static void main(String[] args) {
        startQuiz();
    }

    private static void startQuiz() {
        System.out.println("Welcome to the Quiz Application!");
        System.out.println("You have " + TIME_LIMIT_SECONDS + " seconds to answer each question.");
        System.out.println("----------------------------------");

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                displayNextQuestion();
            }
        }, TIME_LIMIT_SECONDS * 1000);

        displayNextQuestion();
    }

    private static void displayNextQuestion() {
        if (currentQuestion >= QUESTIONS.length) {
            endQuiz();
            return;
        }

        System.out.println(QUESTIONS[currentQuestion]);
        System.out.println(OPTIONS[currentQuestion]);
        System.out.print("Enter your answer (A, B, or C): ");

        Scanner scanner = new Scanner(System.in);
        char userAnswer = Character.toUpperCase(scanner.nextLine().charAt(0));

        if (userAnswer == ANSWERS[currentQuestion]) {
            System.out.println("Correct answer!");
            score++;
        } else {
            System.out.println("Wrong answer!");
        }

        System.out.println("----------------------------------");

        currentQuestion++;
        displayNextQuestion();
    }

    private static void endQuiz() {
        timer.cancel();

        System.out.println("Quiz ended!");
        System.out.println("Your score: " + score + "/" + QUESTIONS.length);
    }
}