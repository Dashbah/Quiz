package game.question;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class QuestionResponse {
    private Long id;
    private String question;
}
