package home.assignment.idomsoft.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import home.assignment.idomsoft.service.NationalityService;

@Configuration
public class BeanCondig {

	@Bean
	public NationalityService createNationalityService() {
		return new NationalityService();
	}

}
