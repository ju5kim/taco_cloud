package tacos.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{ // 컨피큐레이션 어댑터?

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user1") // 해당 유저에게 
			.password("{noop}password1") // 비밀번호
			.authorities("ROLE_USER") // 권한을 부여
		.and()
		.withUser("user2")
			.password("{noop}password2")
			.authorities("ROLE_USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/design","/orders")  // 다음 URL과 같다면
				.access("hasRole('ROLE_USER')") // 접근 권한은 다음과 같다
			.antMatchers("/","/**")
				.access("permitAll")
			.and()
			.httpBasic();
	}
	

}
