package eu.panic.dont.sensu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Configuration class for Spring. Please import this class is your project config using @import.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AppConfig {

    @Bean
    public NotifyOkApplicationListener notifyOkApplicationListener() {
        return new NotifyOkApplicationListener();
    }


}
