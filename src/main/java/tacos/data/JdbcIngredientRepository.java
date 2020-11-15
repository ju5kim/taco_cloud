package tacos.data;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import tacos.Ingredient;
//
//@Repository
//public class JdbcIngredientRepository implements IngredientRepository {
public class JdbcIngredientRepository {
//
//	private JdbcTemplate jdbc;
//
//	@Autowired
//	public JdbcIngredientRepository(JdbcTemplate jdbc) {
//	  this.jdbc = jdbc;
//	}
//
//	@Override
//	  public Iterable<Ingredient> findAll() {
//	    return jdbc.query("select id, name, type from Ingredient",
//	      this::mapRowToIngredient);
//	  }
//
//	  @Override
//	  public Ingredient findById(String id) {
//	    return jdbc.queryForObject(
//	      "select id, name, type from Ingredient where id=?",
//	        this::mapRowToIngredient, id);
//	  }
//
//	  private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) // 스프링의 RowMapper 인터페이스를 우리가 구현한 메서드
//	    throws SQLException {
//	      return new Ingredient(
//		    rs.getString("id"),
//		    rs.getString("name"),
//		    Ingredient.Type.valueOf(rs.getString("type")));
	//쿼리로 생성된 resultset에 row 개수만큼 호출하면서 row를 각각 객체(Ingredient)로 생성
	//위처럼 매개변수로 메서드가 올 수 있는 것은 메서드 참조 떄문이다.
//	  }
//
//	  @Override
//	  public Ingredient save(Ingredient ingredient) {
//	    jdbc.update(
//	        "insert into Ingredient (id, name, type) values (?, ?, ?)",
//	        ingredient.getId(),
//	        ingredient.getName(),
//	        ingredient.getType().toString());
//	    return ingredient;
//	  }

}