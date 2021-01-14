package tacos;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core
                      .authority.SimpleGrantedAuthority;
import org.springframework.security.core
                          .userdetails.UserDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@RequiredArgsConstructor
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  private Long id;

	  private final String username;
	  private final String password;
	  private final String fullname;
	  private final String street;
	  private final String city;
	  private final String state;
	  private final String zip;
	  private final String phoneNumber;

	  @Override
	  public Collection<? extends 
	                 GrantedAuthority> getAuthorities() {
	    return Arrays.asList(new 
		             SimpleGrantedAuthority("ROLE_USER"));
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }

}
//package tacos;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import lombok.AccessLevel;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//
//@Data
//@Entity // 롬복
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true) // 기본 생성자 
//@RequiredArgsConstructor // 초기화 되지 않은 final 필드에 대해서 의존성 주입 생성자에
//public class User implements UserDetails{
////사용자에게 부여된 권한, 사용자 계정을 사용할 수 있는 지의 여부
//	
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private long id;
//	
//	private final String username;
//	private final String password;
//	private final String fullname;
//	private final String street;
//	private final String city;
//	private final String state;
//	private final String zip;
//	private final String phoneNumber;
//	
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() { // 해당 사용자에게 부여된 권한을 컬랙션을 반환한다.
//		// TODO Auto-generated method stub
//		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
//	}
//
//	@Override
//	public String getPassword() {//?
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String getUsername() { //?
//		// TODO Auto-generated method stub
//		return null;
//	}
////해당 사용자 계정의 활성화 또는 비활성화 여부를 나타내는 매서드들, is expired
////지금은 비활성화 할 필요 없으므로 true;로 설정한다.
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
