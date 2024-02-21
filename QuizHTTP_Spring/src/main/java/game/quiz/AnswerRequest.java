package game.quiz;

import lombok.Data;

@Data
public class AnswerRequest {
    private Long userId;
    private Long questionId;
    private String answer;
}
