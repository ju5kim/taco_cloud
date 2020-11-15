package tacos.web;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
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
	  public String orderForm() {
	    return "orderForm";
	  }

	@PostMapping
	  public String processOrder(@Valid Order order, 
			  Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
		    return "orderForm";
		}

		orderRepo.save(order);
	    sessionStatus.setComplete();

	    return "redirect:/";
	  }

}
//package tacos.web;
//
//import javax.validation.Valid;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//
//import lombok.extern.slf4j.Slf4j;
//import tacos.Order;
//import tacos.data.OrderRepository;
//
//@Slf4j
//@Controller
//@RequestMapping("/orders")
//@SessionAttributes("order")
//public class OrderController {
//	private OrderRepository orderRepo;
//
//	public OrderController(OrderRepository orderRepo) {
//		this.orderRepo = orderRepo;
//	}
//
////	@GetMapping("/current")
////	public String orderForm(Model model) {
////		model.addAttribute("order", new Order());
////		return "orderForm";
////	}
//	@GetMapping("/current")
//	public String orderForm() {
//		return "orderForm";
//	}
//
//	@PostMapping
//	public String processOrder(@Valid Order order, Errors errors,SessionStatus sessionStatus) {
//		if (errors.hasErrors()) {
//			return "orderForm";
//		}
//		orderRepo.save(order);
//		sessionStatus.setComplete();//주문 객체가 데이터베이스에 저장된 후에는 더이상 세션에 보존할 필요가 없다. 만일 제거하지 않으면 다음 주문은 이전주문에 포함되었던 타고 객체를 가지고 올것이다.
//		log.info("Order submitted : " + order);
//		return "redirect:/";
//	}
//}
