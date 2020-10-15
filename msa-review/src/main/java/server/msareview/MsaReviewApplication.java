package server.msareview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaReviewApplication.class, args);
	}

}
