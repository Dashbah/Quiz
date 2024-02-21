package game.question;

import game.deserializer.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {
    private final Client client = new Client("jservice.io", 80);
    private final QuestionRepository questionRepository;

    // todo: handle the Exception
    @Autowired
    public QuestionService(QuestionRepository questionRepository) throws IOException {
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<List<QuestionResponse>> getNewQuestions(Integer num) throws IOException {
        List<QuestionResponse> response = new ArrayList<>();

        var questionString = client.getQuestion("/api/random?count=" + num);
        var questions = Deserializer.Deserialize(questionString, Question[].class);
        for (var question : questions) {
            var savedQuestion = questionRepository.save(question);
            response.add(new QuestionResponse(savedQuestion.getId(), savedQuestion.getQuestion()));
        }

        return ResponseEntity.ok(response);
    }

    public boolean ifQuestionExists(Long questionId) {
        return questionRepository.existsById(questionId);
    }

    public boolean checkAnswer(Long questionId, String answer) {
        return Objects.equals(answer, questionRepository.findById(questionId).orElseThrow().getAnswer());
    }
}
