package server.msaadvertising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsaAdvertisingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaAdvertisingApplication.class, args);
	}

}
