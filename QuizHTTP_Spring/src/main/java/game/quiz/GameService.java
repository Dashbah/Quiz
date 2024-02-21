package game.quiz;

import game.question.QuestionService;
import game.user.AppUser;
import game.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final UserService userService;

    private final QuestionService questionService;

    @Autowired
    public GameService(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    public ResponseEntity<Boolean> giveAnswer(Long userId, Long questionId, String answer) {
        if (!userService.ifUserExists(userId)) {
            return ResponseEntity.notFound().build();
        }
        if (!questionService.ifQuestionExists(questionId)) {
            // todo: add body
            return ResponseEntity.notFound().build();
        }

        if (questionService.checkAnswer(questionId, answer)) {
            // todo: what if concurrency??? And smth was deleted
            userService.addRightAnswerToUserScore(userId);
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.ok(false);
    }
}
