package at.emundo.osgi.module2.internal;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import at.emundo.osgi.module1.Service1;
import at.emundo.osgi.module2.DefaultService2;
import at.emundo.osgi.persistence.Service2;

@Configuration
@Component
public class Module2Configuration {

	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private Service1 spring1Bean;

	@Bean(name = "spring2Bean")
	public Service2 spring2Bean() {
		System.out.println("creating service2 bean");
		return new DefaultService2(emf, spring1Bean);
	}

}
