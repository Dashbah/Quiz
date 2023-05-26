package game.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Entity
@Getter
@Setter
@Data
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
//    @SequenceGenerator(
//            name = "event_sequence",
//            sequenceName = "event_name",
//            allocationSize = 1
//    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    private Long id;

    private Integer numOfRightQuestions;
    private Integer numOfAllQuestions;

    // count it
    private Double rating;

    private String username;

    @JsonIgnore
    private String password;

    public boolean isPasswordCorrect(String password) {
        return Objects.equals(this.password, password);
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "username=" + username +
                ", rating=" + rating +
                ", numOfAllQuestions=" + numOfAllQuestions +
                ", numOfRightQuestions='" + numOfRightQuestions;
    }
}
