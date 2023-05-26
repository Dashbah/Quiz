package game.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController("/question")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{numOfQuestions}")
    public ResponseEntity<List<QuestionResponse>> getQuestion
            (@PathVariable(value = "numOfQuestions") Integer num) {
        try {
            return questionService.getNewQuestions(num);
        } catch (IOException e) {
            // todo: add e to body
            return ResponseEntity.badRequest().build();
        }
    }
}
