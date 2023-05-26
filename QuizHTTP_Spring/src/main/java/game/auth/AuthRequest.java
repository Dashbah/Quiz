package game.auth;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AuthRequest {
    private String userName;
    private String password;
}
