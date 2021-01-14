package tacos.security;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{ // 컨피큐레이션 어댑터?
	
	@Autowired
	DataSource dataSource;
	
	@Autowired 
	private UserDetailsService userDetailsService; // 스프링 유저디테일을 활용한 커스텀 서비스
	
	@Bean
	public PasswordEncoder encoder() {// 빈을 선언.. 여기서 왜 @Autowired를 안하고 @Bean을 했을까
		return new BCryptPasswordEncoder();
	}
	
	@Override 
	protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
		
		authenticationManager.userDetailsService(userDetailsService)
		.passwordEncoder(encoder());// encoder()에 @Bean에 애노테이션이 지정되어있고 BCrypt의 인스턴스가 스프링 Context에 등록되어 관리된다.
		//@component 와는 의미가 다르다.
		
		
		
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
/*		
		//사용자 정의 테이블을 사용할 수는 있지만 매개변수는 하나이고 컬럼의 이름과 타입을 맞추어주어야한다.
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery
		("select username, password, enabled from users"+"where username=?")
		.authoritiesByUsernameQuery
		("select username, authority from authorities"+"where username=?")
//		.passwordEncoder(new BCryptPasswordEncoder()); // 비밀번호 암호화
		.passwordEncoder(new NoEncodingPasswordEncoder()); //DB값으로 로그인이 되는지 확인하기 위해 암호화를 끄는 메서드
*/
		
	// LDAP를 활용한 	
		/*
		auth.ldapAuthentication()
		.userSearchBase("ou=people") // ou가 뭐지
			.userSearchFilter("(uid-{0})")
		.groupSearchBase("ou=groups")
			.groupSearchFilter("member={0}")
		.contextSource()
			.root("dc=tacocloud,dc=com")
			.ldif("classpath:users.ldif")
		.and()
		.passwordCompare()
			.passwordEncoder(new BCryptPasswordEncoder())
			.passwordAttribute("userPasscode");
		*/
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
//			.antMatchers("/design","/orders")  // 다음 URL과 같다면
//				.access("hasRole('ROLE_USER')") // 접근된 (매개변수)표현식이 ture면 접근을 허용한다
//			.antMatchers("/","/**")
//				.access("permitAll")
//			.and()
//			.httpBasic();
		.antMatchers("/design","/orders")
			//.hasRole("ROLE_USER")//해당하는 역활을 사용자 가지고 있으면 접근 허용
			.access("hasRole('ROLE_USER')")
		.antMatchers("/h2-console/**")
			.permitAll()
		.antMatchers("/","/**")
			//.permitAll();
			.access("permitAll") // 스프링 표현식으로 사용
		.and()
			//커스텀로그인을 설정하기
			.formLogin()
			.loginPage("/login")
//			.loginProcessingUrl("/사용자 설정  url)// 스프링은 기본으로 /login")
//			.usernameParameter("id")// 사용자 정의 뷰단 form에 파라메터(스프링 기본값은 username)
//			.passwordParameter("pwd" )//사용자 정의 뷰단에 form에 파라메터(스프링 기본값 password)
//			.defaultSuccessUrl("/mainpage", true)// 사용자 정의 페이지로 이동 (스프링 기본값은 머물던 페이지)
		.and()
			.logout()
			.logoutSuccessUrl("/")
		.and()
			.csrf()
			.ignoringAntMatchers("/h2-console/**")  
		.and()	
			.headers()
            .addHeaderWriter(
            				new XFrameOptionsHeaderWriter(new WhiteListedAllowFromStrategy(Arrays.asList("localhost")) )
            		)
            .frameOptions().sameOrigin()
			;
		//rest API서버는 csrf 토큰을 dialbe해야한다. 왜지?
		
	}
	

}
