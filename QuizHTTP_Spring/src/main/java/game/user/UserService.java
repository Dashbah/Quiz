package game.user;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(AppUser newUser) {
        if (newUser != null) {
            userRepository.save(newUser);
        } else {
            throw new NullPointerException("User is null!");
        }
    }

    public ResponseEntity<AppUser> getUserByUsername(String userName) {
        return
           ResponseEntity.ok(userRepository.findByUserName(userName).orElse(null));
    }

    public ResponseEntity<Void> updateUser(Long id, AppUser userDetails) {
        AppUser user = userRepository.findById(id)
                .orElseThrow();

        // todo: count it
        user.setRating(userDetails.getRating());
        user.setPassword(userDetails.getPassword());
        user.setUsername(userDetails.getUsername());
        user.setNumOfAllQuestions(userDetails.getNumOfAllQuestions());
        user.setNumOfRightQuestions(userDetails.getNumOfRightQuestions());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> updateUserRating(Long id, AppUser userDetails) {
        AppUser user = userRepository.findById(id)
                .orElseThrow();

        // todo: count it somehow idk how
        user.setRating(userDetails.getRating());
        user.setNumOfAllQuestions(userDetails.getNumOfAllQuestions());
        user.setNumOfRightQuestions(userDetails.getNumOfRightQuestions());

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
