package game;

import game.quiz.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizHttpSpringApplication {

    public static void main(String[] args) {
//        Game game = new Game();
//        game.start();
        SpringApplication.run(QuizHttpSpringApplication.class, args);
    }

}
