package miniproj.vttp23.iss.miniproj.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import miniproj.vttp23.iss.miniproj.model.Log;

@Repository
public class AppRepo {
    // save log as JSON
    public void savelog() {
        Log log = new Log();

    }

    // get log from redis database 
    public List<Log> getLog() {
        
        return null;
    }

}
