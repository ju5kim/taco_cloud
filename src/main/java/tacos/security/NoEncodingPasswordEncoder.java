package tacos.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class NoEncodingPasswordEncoder implements PasswordEncoder{
	//현재 DB에 있는 테이블에 값으로 로그인 할려면 비밀번호가 암호화 로직이 되어 있기 때문에
	//이걸 이용해서 현재 DB값으로 로그인 을 한다.
	@Override
	public String encode(CharSequence rawPassword) {

		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

	
}
