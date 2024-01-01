package miniproj.vttp23.iss.miniproj.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import miniproj.vttp23.iss.miniproj.model.Log;


@Service
public class AppService {
    
    @Value("${bible.api.key}")
    private String apiKey;

    private static final String API_URL = "https://ajith-holy-bible.p.rapidapi.com/GetVerseOfaChapter";

    public String getVerse(String book, String chapter, String verse) {

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
        ResponseEntity<String> response = restTemplate.exchange(API_URL + "?book=" + book + "&chapter=" + chapter + "&verse=" + verse, HttpMethod.GET, entity, String.class);
        
        return response.getBody();

    }

    public Log getLogByDate(Date date) {
        Log log = new Log();
        // retrieve log from database from Repo class 
        return log;
    }
}
