package config.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.douzone.container.user.User;

@Configuration
public class AppConfig02 {
	
	// bean은 configuration의 메소드에서만 사용가능
	@Bean
	public User user() {
		return new User("마이콜");
	}
	
}
