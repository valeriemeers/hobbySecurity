package be.qnh.hobby.config;

import be.qnh.hobby.domain.Shop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public Shop shop() {
        Shop shop1 = new Shop();
        //shop1.setName("Dille & Kamille");
        shop1.setCity("Maastricht");

        return shop1;
    }
}
