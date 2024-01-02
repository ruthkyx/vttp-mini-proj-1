package miniproj.vttp23.iss.miniproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import miniproj.vttp23.iss.miniproj.model.Log;
import miniproj.vttp23.iss.miniproj.model.User;
import miniproj.vttp23.iss.miniproj.repo.AppRepo;


@Service
public class AppService {
    
    @Value("${bible.api.key}")
    private String apiKey;

    private static final String API_URL = "https://ajith-holy-bible.p.rapidapi.com/GetVerseOfaChapter";

    @Autowired
    private AppRepo appRepo;

    public ResponseEntity<String> getVerse(String book, String chapter, String verse) {

        // String url = UriComponentsBuilder
        //     .fromUriString("https://ajith-holy-bible.p.rapidapi.com/GetVerseOfaChapter")
        //     .queryParam("book", book)
        //     .queryParam("chpater", chapter)
        //     .queryParam("verse", verse)
        //     .build()
        //     .toString();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.set("apiHost", "ajith-holy-bible.p.rapidapi.com");
        header.set("apiKey", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(
            API_URL + "?book=" + book + "&chapter=" + chapter + "&verse=" + verse, 
            HttpMethod.GET, 
            entity, 
            String.class);
        
        return ResponseEntity.ok(response.getBody());

    }

    // GET specific log
    public Log getLogByDate(String date) {
        // retrieve log from database from Repo class 
        return appRepo.logDate(date);
    }

    // GET all logs
    public List<Log> getAllLogs () {
        return appRepo.getAllLogs();
    }

    // save log
    public void saveLog(String date, String text) {
        appRepo.savelog(date, text);
    }

    // check if user exists in the database
    public boolean hasUser(String username) {
        return appRepo.hasUser(username);
    }

    public User getUser(String username) {
        return appRepo.getUser(username);
    }

    public void addUser(User newUser) {
        appRepo.saveUser(newUser);
    }

}
