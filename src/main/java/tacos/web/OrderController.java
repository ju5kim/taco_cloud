package tacos.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepo;

	public OrderController(OrderRepository orderRepo) {
	  this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	//여기서 이렇게 해주고 주문폼으로 가는 이유는 미리 사용자 정보를 주문폼에 띄우기 위해서이다.
	//그냥 유저와 주문정보만 셋팅해서 넘김
	  public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) { 
		if(order.getDeliveryName()==null) {order.setDeliveryName(user.getFullname());}
		if(order.getDeliveryCity()==null) {order.setDeliveryCity(user.getCity());}
		if(order.getDeliveryStreet()==null) {order.setDeliveryStreet(user.getStreet());}
		if(order.getDeliveryState()==null) {order.setDeliveryState(user.getState());}
		if(order.getDeliveryZip()==null) {order.setDeliveryZip(user.getZip());}
		
	    return "orderForm";
	  }
	/*
	 *주문을 하는 사용자를 가지고오는 방법은 여러가지 있다.
	 * principal 객체 (시큐리티)
	 * Authentication 객체
	 * SecurityContextHolder 사용 보안 컨텍스트
	 * @AuthenticationPrincipal 사용자 정의 유저(VO) 객체를 사용, 어노테이션 적용
	 */
	@PostMapping // 주문을 저장하는 역활 
	  public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,@AuthenticationPrincipal User user) {
	/*      
	 *  좀 길어 보이지만 확장성이 좋은 코드 다른 곳에서도 사용가능		
	 *  Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
	 *  User user=(User)authentication.getPrincipal();
	 */
		if (errors.hasErrors()) {return "orderForm";}
		
		order.setUser(user);
		orderRepo.save(order);
	    sessionStatus.setComplete();//주문 객체가 데이터베이스에 저장된 후에는 더이상 세션에 보존할 필요가 없다. 만일 제거하지 않으면 다음 주문은 이전주문에 포함되었던 타고 객체를 가지고 올것이다.

	    return "redirect:/";
	  }

}