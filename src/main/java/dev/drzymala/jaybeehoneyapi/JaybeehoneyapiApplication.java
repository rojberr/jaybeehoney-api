package dev.drzymala.jaybeehoneyapi;

import dev.drzymala.jaybeehoneyapi.order.application.OrderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OrderProperties.class)
public class JaybeehoneyapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JaybeehoneyapiApplication.class, args);
    }

}
