package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
//@EntityScan(basePackages = "application.Domains")
//@ComponentScan("application.Repositories")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}