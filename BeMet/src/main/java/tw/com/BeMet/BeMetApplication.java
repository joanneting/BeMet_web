package tw.com.BeMet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@EnableScheduling
public class BeMetApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeMetApplication.class, args);
    }


}
