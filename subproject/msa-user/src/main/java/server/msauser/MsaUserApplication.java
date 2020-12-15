package server.msauser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("cyh.example.*")
@EnableJpaRepositories("cyh.example.*")

public class MsaUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaUserApplication.class, args);
	}

}
