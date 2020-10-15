package server.msanews.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
    @RequestMapping("/")
    public String getNews() {
        return "News.";
    }
}