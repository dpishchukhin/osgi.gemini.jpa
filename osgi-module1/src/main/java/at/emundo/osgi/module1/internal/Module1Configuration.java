package at.emundo.osgi.module1.internal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import at.emundo.osgi.module1.DefaultService1;
import at.emundo.osgi.module1.Service1;

@Configuration
@Component
public class Module1Configuration 
{
    @Bean
    public Service1 spring1Bean() {
        return new DefaultService1();
    }

}
