package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{ // 컨피큐레이션 어댑터?
	
	@Autowired
	DataSource dataSource;
	
	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user1") // 해당 유저에게 
//			.password("{noop}password1") // 비밀번호
//			.authorities("ROLE_USER") // 권한을 부여
//		.and()
//		.withUser("user2")
//			.password("{noop}password2")
//			.roles("USER"); // 위와 같은 것임
//		//여기서는 인메모리 사용자를 활용해서 인증정보를 구성하였다.
//		//JDBC,LDAP,커스텀 등으로 사용자와 인증 정보를 구성할 수 있다.
//		auth.jdbcAuthentication()
//		.dataSource(dataSource);
		//사용자 정의 테이블을 사용할 수는 있지만 매개변수는 하나이고 컬럼의 이름과 타입을 맞추어주어야한다.
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery
		("select username, password, enabled from users"+"where username=?")
		.authoritiesByUsernameQuery
		("select username, authority from authorities"+"where username=?")
//		.passwordEncoder(new BCryptPasswordEncoder()); // 비밀번호 암호화
		.passwordEncoder(new NoEncodingPasswordEncoder()); //DB값으로 로그인이 되는지 확인하기 위해 암호화를 끄는 메서드
		
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
