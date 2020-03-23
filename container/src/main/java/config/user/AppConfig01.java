package config.user;

import org.springframework.context.annotation.Bean;

import com.douzone.container.user.User;

public class AppConfig01 {
	
	// bean은 configuration의 메소드에서만 사용가능
	@Bean
	public User user() {
		return new User();
	}
	
}
