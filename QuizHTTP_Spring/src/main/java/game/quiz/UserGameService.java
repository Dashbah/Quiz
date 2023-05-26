package game.quiz;

import game.user.AppUser;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class UserGameService {

    void saveResults(String userName, int numOfRightQuestions, int numOfQuestions) {
        // request to server
    }

    void register() {
        // request to server
    }

    boolean auth() {
        // request to server
        return true;
    }

    List<AppUser> getAllUsersRequest() throws IOException {
//        String urlOverHttps = "https://localhost:8080/quiz/user";
//        HttpGet getMethod = new HttpGet(urlOverHttps);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpResponse response = httpClient.execute(getMethod);
        // todo: parse this response
        // assertThat(response.getCode(), equalTo(200));

        return List.of(
                new AppUser("Kate", "1234"),
                new AppUser("Pete", "4321")
        );
    }
}
