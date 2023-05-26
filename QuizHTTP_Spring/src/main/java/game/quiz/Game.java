package game.quiz;

import game.deserializer.Deserializer;
import game.question.Client;
import game.question.Question;
import game.user.AppUser;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    private Client clientServer;
    private final UserGameService gameService;
    private int numOfQuestions = 0;
    private int numOfRightQuestions = 0;
    Scanner input = new Scanner(System.in);

    public Game() {
        gameService = new UserGameService();
    }

    /**
     * This method starts the quiz game by connecting to the server, getting a random question,
     * asking the user for an answer, and checking if it is correct.
     * It keeps asking questions until the user inputs the "/q" command to quit the game.
     */
    public void start() {
        System.out.println("Type \"/start\" to begin, \"/q\" to quit");
        while (!input.nextLine().startsWith("/start")) {
            // todo: if "/q"
            System.out.println("wrong command");
        }

        System.out.println("All users: ");
        List<AppUser> users = null;
        try {
            users = gameService.getAllUsersRequest();
        } catch (IOException e) {
            // Console.log(e.getMessage());
        }
        assert users != null;
        for (var user : users) {
            System.out.println(user.toString());
        }

        System.out.println("Print your username: ");
        String userName = input.nextLine();
        System.out.println("Enter the password: ");
        String password = input.nextLine();

        var userNames = users.stream().map((AppUser::getUsername)).toList();
        if (userNames.contains(userName)) {
            // auth
            if (!gameService.auth()) {
                System.out.println("the password is not correct, you are blocked sorry");
            }
        } else {
            gameService.register();
        }
        // okay tipa zaregalis


        // quiz and get results
        play();

        // save results
        gameService.saveResults(userName, numOfRightQuestions, numOfQuestions);
    }

    private void play() {
        try {
            clientServer = new Client("jservice.io", 80);
        } catch (IOException e) {
            System.out.println("Error while connecting to server " + e.getMessage());
            return;
        }

        Question question;
        String personAnswer;
        do {
            question = getQuestion();
            printQuestion(question.getQuestion());
            personAnswer = getAnswer();
            if (personAnswer.startsWith("/q")) {
                break;
            }
            ++numOfQuestions;
            if (Objects.equals(question.getAnswer(), personAnswer)) {
                ++numOfRightQuestions;
            }
            System.out.println("correct answer: " + question.getAnswer());
        } while (true);

        showResult();
    }

    /**
     * This method gets a random question
     * and deserializes the response into a Question object.
     *
     * @return a Question object representing the retrieved question.
     */
    private Question getQuestion() {
        String questionString;
        Question question = new Question();
        try {
            questionString = clientServer.getQuestion("/api/random?count=1");
            question = Deserializer.Deserialize(questionString, Question[].class)[0];
        } catch (IOException e) {
            System.out.println("error while getting question: " + e.getMessage());
        }
        return question;
    }

    /**
     * This method asks the user for an answer to the current question and returns it as a String.
     *
     * @return a String representing the user's answer.
     */
    private String getAnswer() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * This method prints the given question to the console.
     *
     * @param question a String representing the question to be printed.
     */
    private void printQuestion(String question) {
        System.out.println(question);
    }

    /**
     * This method displays the final result of the game, showing the number
     * of questions answered correctly and the total number of questions answered.
     */
    private void showResult() {
        System.out.println("Congrats! Result: " + numOfRightQuestions + "/" + numOfQuestions);
    }
}
