package game.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("quiz/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthRequest request) {
        try {
            authenticationService.register(request.getUserName(), request.getPassword());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException exception) {
            // todo: badRequest????
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticate(@RequestBody AuthRequest request) {
        try {
            return ResponseEntity.ok(
                    authenticationService
                            .authenticate(request.getUserName(), request.getPassword())
            );
        } catch (NoSuchElementException exception) {
            // todo: notFound????
            return ResponseEntity.notFound().build();
        }
    }
}
