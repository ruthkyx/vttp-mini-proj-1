package miniproj.vttp23.iss.miniproj.repo;

import java.io.StringReader;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import miniproj.vttp23.iss.miniproj.model.Log;
import miniproj.vttp23.iss.miniproj.model.User;

@Repository
public class AppRepo {

    @Autowired 
    private RedisTemplate<String, String> template;

    // save log as JSON
    public void savelog(String date, String text) {
        template.opsForList().leftPush(date, text);

    }

    // get from redis database 
    public List<Log> getLogs() {

        return null;
    }

    // get log by date 
    public Log logDate(String date) {

        return ;
    }

    // check if user exists in database 
    public boolean hasUser(String hashKey) {
        // hashkey: username, hash value: password
        return template.opsForList().hasKey(key, hashKey);
    }

    // saving user data
    public void saveUser(User user) {
        JsonObject obj = Json.createObjectBuilder()
            .add("username", username)
            .add("password", password)
            .build();

        template.opsForHash().put(key, user.getUsername(), obj.toString());
    }

    // retrieving user data
    public User getUser(String hashKey) {
        String user = template.opsForHash().get(key, hashKey);
        JsonReader jsonReader = Json.createReader(new StringReader(user));
        JsonObject jsonObject = jsonReader.readObject();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        User getUser = new User(username, password);

        return getUser;
    }

}
