package game.quiz;

import game.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/giveAnswer")
    public ResponseEntity<Boolean> giveAnswer(@RequestBody AnswerRequest answerRequest) {
        return gameService.giveAnswer(answerRequest.getUserId(), answerRequest.getQuestionId(),
                answerRequest.getAnswer());
    }
}
