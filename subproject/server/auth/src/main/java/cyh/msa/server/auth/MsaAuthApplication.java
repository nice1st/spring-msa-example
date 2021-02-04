package cyh.msa.server.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ControllerAdvice;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "cyh.msa.server.auth")
@ComponentScan(basePackages = "cyh.msa.core.handler", includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ControllerAdvice.class))
@EntityScan("cyh.msa.domain")
@EnableJpaRepositories("cyh.msa.domain")
public class MsaAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaAuthApplication.class, args);
	}
}