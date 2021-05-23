package coursework.ecomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "coursework.ecomarket.*")
@ComponentScan(basePackages = "coursework.ecomarket.*")
@EnableTransactionManagement

public class EcoMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(EcoMarketApplication.class, args);
	}
}