package server.msanews.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class NewsController {

    @RequestMapping("/")
    public String getNews() {
        return "News.";
    }

    @RequestMapping("/review/add")
    public Mono<ResponseEntity> addReviewCount(@RequestParam(value="newsId") int newsId) {
        log.info("add news Review Count / news Id : " + newsId);
        return Mono.just(ResponseEntity.ok().build());
    }
}