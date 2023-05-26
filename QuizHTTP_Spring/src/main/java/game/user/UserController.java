package game.user;


import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController("quiz/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<AppUser> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUser> getUserByUsername(@PathVariable(value = "username") String userName) {
        return userService.getUserByUsername(userName);
    }

    @PostMapping
    public ResponseEntity<Void> addNewUser(AppUser user) {
        try {
            userService.addNewUser(user);
            return ResponseEntity.ok().build();
        } catch (NullPointerException exception) {
            // not sure about noContent()
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable(value = "id") Long id, AppUser userDetails) {
        try {
            return userService.updateUser(id, userDetails);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/rating/{id}")
    public ResponseEntity<Void> updateUserRating(@PathVariable(value = "id") Long id, AppUser userDetails) {
        try {
            return userService.updateUserRating(id, userDetails);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
