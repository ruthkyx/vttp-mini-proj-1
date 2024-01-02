package miniproj.vttp23.iss.miniproj.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import miniproj.vttp23.iss.miniproj.model.Log;

@Repository
public class AppRepo {

    @Autowired 
    private RedisTemplate<String, String> template;

    // save log as JSON
    public void savelog() {
        Log log = new Log();

    }

    // get log from redis database 
    public List<Log> getLog() {

        return null;
    }

    // saving user data
    public void saveUser(String username, String password) {
        template.opsForValue().set("username", username);
        template.opsForValue().set("password", password);
    }

    // retrieving user data
    public Optional<String> getUser(String username) {
        return Optional.ofNullable(template.opsForValue().get(username));
    }

}
