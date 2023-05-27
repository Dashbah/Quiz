package game.auth;

import game.user.AppUser;
import game.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    void register(String userName, String password) {
        AppUser newUser = new AppUser(userName, password);

        if (userRepository.findByUsername(userName).isEmpty()) {
            throw new IllegalArgumentException("User already exists!");
        }

        userRepository.save(newUser);
    }

    boolean authenticate(String userName, String password) {
        var user = userRepository.findByUsername(userName).orElseThrow();

        return user.isPasswordCorrect(password);
    }
}
