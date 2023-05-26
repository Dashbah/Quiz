package game.question;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * Represents a single trivia question from the jservice.io API.
 */
@Data
@Getter
@Entity
@Table(name = "question")
public class Question {
    @Id
//    @SequenceGenerator(
//            name = "event_sequence",
//            sequenceName = "event_name",
//            allocationSize = 1
//    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )
    private Long id;
    private String answer;
    private String question;
}
