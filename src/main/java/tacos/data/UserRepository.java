package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.User;

public interface UserRepository extends CrudRepository<User, Long>{

	User findByUsername(String username); //id로 User를 찾기 위해 사용된다.
	
}
