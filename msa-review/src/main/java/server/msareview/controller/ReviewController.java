package server.msareview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String setReview() {
        // set new review
        writeReviewSource.writeReview().send(MessageBuilder.withPayload("{seq : 13322}").build());
        return "write review";
    }
}
