package tacos.data;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
//import org.springframework.stereotype.Repository;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import tacos.Taco;
//import tacos.Order;
//
//@Repository
//public class JdbcOrderRepository implements OrderRepository {
public class JdbcOrderRepository {
//
//	private SimpleJdbcInsert orderInserter;
//	private SimpleJdbcInsert orderTacoInserter;
//	private ObjectMapper objectMapper;
//
//	@Autowired
//	public JdbcOrderRepository(JdbcTemplate jdbc) {
//	  this.orderInserter = new SimpleJdbcInsert(jdbc)
//	      .withTableName("Taco_Order")
//	      .usingGeneratedKeyColumns("id");
//
//	  this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
//	      .withTableName("Taco_Order_Tacos");
//
//	  this.objectMapper = new ObjectMapper();
//	}
//
//	@Override
//	public Order save(Order order) {// 실제로 저장하는 일은 하지 않고 Order과 Taco 객체들을 저장하는 처리를 총괄
//	  order.setPlacedAt(new Date());
//	  long orderId = saveOrderDetails(order);
//	  order.setId(orderId);
//	  List<Taco> tacos = order.getTacos();
//	  
//	  for (Taco taco : tacos) {
//	    saveTacoToOrder(taco, orderId);
//	  }
//
//	  return order;
//	}
//
//	private long saveOrderDetails(Order order) {// Taco_Order테이블에 저장된 후에 DB에 생성된 ID가 Number객체로 반환된다.
//	  @SuppressWarnings("unchecked")
//	  Map<String, Object> values =
//	      objectMapper.convertValue(order, Map.class);// Jackson의 ObjectMapper를 이용해서 Order를 Map으로 변환
//	  values.put("placedAt", order.getPlacedAt());//objectMapper는 Date타입의 값을 long타입으로 변환하기 떄문에, taco_Order테이블의 열과 호환이 안된다.
//
//	  long orderId =
//	      orderInserter
//	          .executeAndReturnKey(values)// map을 인자로 받는다. 키는 테이블의 열이름, 값은 열에 추가되는 값
//	          .longValue();
//	  return orderId;
//	}
//
//	private void saveTacoToOrder(Taco taco, long orderId) {// ObjectMapper를 사용하지 않고 사용
//	  Map<String, Object> values = new HashMap<>();
//	  values.put("tacoOrder", orderId);
//	  values.put("taco", taco.getId());
//	  orderTacoInserter.execute(values);// map을 인자로 받는다. 키는 테이블의 열이름, 값은 열에 추가되는 값
//	}

}