import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;
    private static int currentQuestion = 0;

    private static String[] questions = {
            "1. What is the capital of France?",
            "2. What is the largest mammal?",
            "3. Who wrote Romeo and Juliet?",
            "4. What is the powerhouse of the cell?",
            "5. What is the currency of Japan?"
    };

    private static String[][] options = {
            {"A. London", "B. Paris", "C. Berlin", "D. Madrid"},
            {"A. Elephant", "B. Blue Whale", "C. Giraffe", "D. Dolphin"},
            {"A. Charles Dickens", "B. William Shakespeare", "C. Jane Austen", "D. Mark Twain"},
            {"A. Nucleus", "B. Mitochondria", "C. Ribosome", "D. Endoplasmic Reticulum"},
            {"A. Won", "B. Yen", "C. Euro", "D. Dollar"}
    };

    private static char[] correctAnswers = {'B', 'B', 'B', 'B', 'B'};

    public static void main(String[] args) {
        startQuiz();
    }

    private static void startQuiz() {
        System.out.println("Welcome to the Quiz!");
        askQuestionWithTimer();
    }

    private static void askQuestionWithTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            System.out.println("\nTime's up! Moving to the next question.");
                processAnswer('\0');
                currentQuestion++;
                if (currentQuestion < questions.length) {
                    askQuestionWithTimer();
                } 
                else 
                {
                    timer.cancel();  
                    showResult();
                }
            }
        }, 20000); 

        displayQuestion();
        char userAnswer = getUserAnswer();
        processAnswer(userAnswer);

        
        timer.cancel();
        currentQuestion++;

        
        if (currentQuestion < questions.length) {
            askQuestionWithTimer();
        } else {
            showResult();
        }
    }

    private static void displayQuestion() {
        System.out.println("\n" + questions[currentQuestion]);
        for (String option : options[currentQuestion]) {
            System.out.println(option);
        }
    }

    private static char getUserAnswer() {
        System.out.print("Your answer: ");
        return scanner.next().toUpperCase().charAt(0);
    }

    private static void processAnswer(char userAnswer) {
        if (userAnswer == correctAnswers[currentQuestion]) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is " + correctAnswers[currentQuestion]);
        }
    }

    private static void showResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.length);
    }
}