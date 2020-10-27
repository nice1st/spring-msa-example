package server.msaadvertising.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertisingController {
    @RequestMapping("/")
    public String getAdvertisings() {
        return "advertising.";
    }
}
