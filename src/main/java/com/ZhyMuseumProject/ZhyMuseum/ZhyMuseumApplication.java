package com.ZhyMuseumProject.ZhyMuseum;

import com.ZhyMuseumProject.ZhyMuseum.Repository.UserRepository;
import com.ZhyMuseumProject.ZhyMuseum.entity.Role;
import com.ZhyMuseumProject.ZhyMuseum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ZhyMuseumApplication implements CommandLineRunner {

	@Autowired
	UserRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(ZhyMuseumApplication.class, args);
	}

	// Handling CORS
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};
	}
	@Override
	public void run(String... args) throws Exception {
		User adminAccount = usersRepository.findByRole(Role.ARTIST);
		if(adminAccount == null){
			User users = new User();
			users.setUsername("Zainab AlYousif");
			users.setPassword(new BCryptPasswordEncoder().encode("artist123"));
			users.setEmail("zhy@gmail.com");

			users.setRole(Role.ARTIST);
			usersRepository.save(users);
		}
	}


}
