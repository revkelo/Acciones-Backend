package co.edu.unbosque.proyecto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import co.edu.unbosque.util.AuthorizationFilter;

@SpringBootApplication
public class ProyectoApplication {

	 @Bean
	    public FilterRegistrationBean<AuthorizationFilter> authorizationFilterRegistration() {
	        FilterRegistrationBean<AuthorizationFilter> registration = new FilterRegistrationBean<>();
	        registration.setFilter(new AuthorizationFilter());
	       

	        registration.addUrlPatterns("/admin.html"); // Especifica las rutas a las que se aplicará el filtro
	        return registration;
	    }
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoApplication.class, args);
	}

}
