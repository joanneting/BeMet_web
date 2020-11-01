package tw.com.BeMet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
//@EnableScheduling
public class BusinessMeetApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessMeetApplication.class, args);
    }


}
