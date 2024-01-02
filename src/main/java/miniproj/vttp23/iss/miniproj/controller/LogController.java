package miniproj.vttp23.iss.miniproj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import miniproj.vttp23.iss.miniproj.model.Log;
import miniproj.vttp23.iss.miniproj.service.AppService;

@RestController
@RequestMapping
public class LogController {

    @Autowired
    private AppService appSvc;

    @PostMapping("/log")
    public ResponseEntity<Log> createLog(@RequestBody Log log) {
        // @RequestBody binds params to object
        Log entry = new Log();

        return ResponseEntity.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(entry);
    }

    @GetMapping("/all/{date}")
    public Log getLog(@PathVariable String date) {
        // logic to get log by date

        return appSvc.getLogByDate(date);
    }

    // @RequestMapping(value="/", method= RequestMethod.GET, produces= "application/json")
    @GetMapping("/all")
    public String getAllLogs(Model model) {
        List<Log> logs = appSvc.getAllLogs();
        model.addAttribute("logs", logs);

        return "all";
    }

    // GET verse https://ajith-holy-bible.p.rapidapi.com/GetVerseOfaChapter?Book=Luke&chapter=1&Verse=1
    @GetMapping("/GetVerseOfaChapter")
    public ResponseEntity<String> getVerse(@RequestParam String book, @RequestParam int chapter, @RequestParam int verse, Model model) {
        // model.addAttribute();

        return appSvc.getVerse(book, String.valueOf(chapter), String.valueOf(verse));
    }

}
