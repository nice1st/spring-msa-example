package server.msareview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import server.msareview.component.MessageProducer.WriteReviewSource;

@RestController
public class ReviewController {
    
    @Autowired
    WriteReviewSource writeReviewSource;

    @RequestMapping("/")
    public String getReviews() {
        return "review.";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String setReview(@RequestHeader(value="Authorization") String authorization) {
        // set new review
        // writeReviewSource.writeReview().send(MessageBuilder.withPayload("{seq : 13322}").build());

        WebClient client = WebClient.builder()
            .baseUrl("http://localhost:18090/api/news")
            .defaultHeader("Authorization", authorization)
        .build();

        Mono<String> response = client.get().uri("/review/add?newsId=333").retrieve().bodyToMono(String.class);

        System.out.println("Response : " + response.block());

        return "write review";
    }
}
