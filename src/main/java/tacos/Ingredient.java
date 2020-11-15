package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor( access = AccessLevel.PRIVATE,force=true)//기본생성자를 가지고 있어야 JPA를 사용할 수 있다.
//클래스 외부에서 사용하지 못하게설정, 초기화가 필요한 final속성이 있어서 force설정
@Entity
public class Ingredient {

	@Id
	private final String id;
	private final String name;
	private final Type type;

	public static enum Type {
	    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}

}