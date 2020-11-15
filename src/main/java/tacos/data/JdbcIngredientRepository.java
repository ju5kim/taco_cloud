//package tacos.data;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import tacos.Ingredient;
//
//@Repository
//public class JdbcIngredientRepository implements IngredientRepository{
//
//	private JdbcTemplate jdbc;
//
//	@Autowired
//	public JdbcIngredientRepository(JdbcTemplate jdbc) {//스프링이 해당 클래스의 빈이 생성되면 빈을 JdbcTemplate에 주입한다.// 해당빈을 주입하는 것 까지는 알겠는데. jdbc에 주입한다고?
//		this.jdbc = jdbc;
//	}
//
//	@Override
//	public Iterable<Ingredient> findAll() {
//		
//		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient); //쿼리 결과를  여기서 객체 리스트로 반환 
//	}
//
//	@Override
//	public Ingredient findById(String id) {
//		return jdbc.queryForObject("select id,name,type from Ingredient where id=?", this::mapRowToIngredient,id);//쿼리 결과를  하나의 객체만 반환
//	}
////	메서드 참조를 사용하지 않고 구현한 하지만 쿼리가 작동 될 때 마다 익명 클래스 RowMapper를 생성해야 한다.
////	public Ingredient findById(String id) {
////		return jdbc.queryForObject("select id,name,type from Ingredient where id=?",
////				new RowMapper<Ingredient>() {
////					public Ingredient mapRow(ResultSet rs, int rowNum) throws SQLException {
////						return new Ingredient(rs.getString("id"),rs.getString("name"),Ingredient.Type.valueOf(rs.getString("type")));
////			}
////			
////		});
////	}
//
//	@Override // 쿼리 결과를 객체로 생성할 필요가 없다.
//	public Ingredient save(Ingredient ingredient) {
//	jdbc.update("insert into Ingredient(id,name,type) values( ?,?,?)",
//			ingredient.getId(),
//			ingredient.getName(),
//			ingredient.getType().toString()
//			);	
//		return ingredient;
//	}
//	
//	private Ingredient mapRowToIngredient(ResultSet rset, int rowNum) throws SQLException { // 스프링의 RowMapper 인터페이스를 우리가 구현한 메서드
//		//쿼리로 생성된 resultset에 row 개수만큼 호출하면서 row를 각각 객체(Ingredient)로 생성
//		return new Ingredient(rset.getString("id"),rset.getString("name"),Ingredient.Type.valueOf(rset.getString("type")));
//		//위처럼 매개변수로 메서드가 올 수 있는 것은 메서드 참조 떄문이다.
//	}
//	
//}

package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
	  this.jdbc = jdbc;
	}

	@Override
	  public Iterable<Ingredient> findAll() {
	    return jdbc.query("select id, name, type from Ingredient",
	      this::mapRowToIngredient);
	  }

	  @Override
	  public Ingredient findById(String id) {
	    return jdbc.queryForObject(
	      "select id, name, type from Ingredient where id=?",
	        this::mapRowToIngredient, id);
	  }

	  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum)
	    throws SQLException {
	      return new Ingredient(
		    rs.getString("id"),
		    rs.getString("name"),
		    Ingredient.Type.valueOf(rs.getString("type")));
	  }

	  @Override
	  public Ingredient save(Ingredient ingredient) {
	    jdbc.update(
	        "insert into Ingredient (id, name, type) values (?, ?, ?)",
	        ingredient.getId(),
	        ingredient.getName(),
	        ingredient.getType().toString());
	    return ingredient;
	  }

}