//package tacos.web;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.validation.Valid;
//
//import org.springframework.validation.Errors;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import lombok.extern.slf4j.Slf4j;
//import tacos.Taco;
//import tacos.Ingredient;
//import tacos.Ingredient.Type;
//import tacos.Order;
//import tacos.data.IngredientRepository;
//import tacos.data.TacoRepository;
//
//@Slf4j
//@Controller
//@RequestMapping("/design")
//@SessionAttributes("/order")
//public class DesignTacoController {
//
//	private final IngredientRepository ingredientRepo;
//	
//	private TacoRepository tacoRepo;//final는 수정불가, 
//	
//	@Autowired
//	public DesignTacoController(IngredientRepository ingredientRepo,TacoRepository tacoRepo) { // 생성자 매개변수로 의존성을 주입해라  // 생성자로 의존성을 주입하는 것과 필드로 의존성 주입하는 것에 차이는?
//	this.ingredientRepo = ingredientRepo;
//	this.tacoRepo = tacoRepo;
//	}
//
//	@ModelAttribute(name = "order")
//	public Order order() {
//		return new Order();
//	}
//	
//	@ModelAttribute(name = "taco")
//	public Taco taco() {
//	return new Taco();	
//	}
//	
//	@GetMapping
//	  public String showDesignForm(Model model) {
////	    List<Ingredient> ingredients = Arrays.asList(
////	      new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
////	      new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
////	      new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
////	      new Ingredient("CARN", "Carnitas", Type.PROTEIN),
////	      new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
////	      new Ingredient("LETC", "Lettuce", Type.VEGGIES),
////	      new Ingredient("CHED", "Cheddar", Type.CHEESE),
////	      new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
////	      new Ingredient("SLSA", "Salsa", Type.SAUCE),
////	      new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
////	    );
//		List<Ingredient> ingredients =new ArrayList<>();
//		
//		ingredientRepo.findAll().forEach(i->ingredients.add(i));
//		
//	    Type[] types = Ingredient.Type.values();
//	    
//	    for (Type type : types) {
//	      model.addAttribute(type.toString().toLowerCase(),   filterByType(ingredients, type));// 식자재의 유형을 필터링해서 LIST를 반환한다.
//	    }
//
//	    model.addAttribute("taco", new Taco());
//
//	    return "design";
//	  }
//	
//	  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
//	    return ingredients.stream() // 리스트를 스트림으로
//	              .filter(x -> x.getType().equals(type))//x = Ingredient객체 이다.(LIST에 들어있는) 비교해서 같다면 
//	              .collect(Collectors.toList()); // 스트림 최종연산. 수집해서 LIST로 리턴해라
//	  }
//
//	  @PostMapping
//	  public String processDesign( @Valid Taco design,  Errors errors, @ModelAttribute Order order) {//이 매개변수의 값이 모델로부터 전달 되어야 하는 것과 스프링이 매개변수에 요청매개변수를 바인딩 하지 않아야 한다?
//	
//		  if (errors.hasErrors()) {
//			 return "design";
//		  }
//
//		  Taco saved = tacoRepo.save(design);
//		  order.addDesign(saved);
//
//		  return "redirect:/orders/current";
//	  }
//	  //사용자가 주문뷰에서 임력을 완료하고 제출할 떄 까지 Order 객체는 세션에 남아있고 데이터베이스에 저장되지 않는다.
//}
//
package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import tacos.Order;
import tacos.data.IngredientRepository;


import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	
	private TacoRepository tacoRepo;

	@Autowired
	public DesignTacoController(
			IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
	  this.ingredientRepo = ingredientRepo;
	  this.tacoRepo = tacoRepo;
	}

	@GetMapping
	  public String showDesignForm(Model model) {
	    
		List<Ingredient> ingredients = new ArrayList<>();
	    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

	    Type[] types = Ingredient.Type.values();
	    for (Type type : types) {
	      model.addAttribute(type.toString().toLowerCase(),
	          filterByType(ingredients, type));
	    }

	    model.addAttribute("taco", new Taco());

	    return "design";
	  }
	
	  private List<Ingredient> filterByType(
	      List<Ingredient> ingredients, Type type) {
	    return ingredients
	              .stream()
	              .filter(x -> x.getType().equals(type))
	              .collect(Collectors.toList());
	  }

	  @ModelAttribute(name = "order")
	  public Order order() {
	    return new Order();
	  }

	  @ModelAttribute(name = "taco")
	  public Taco taco() {
	    return new Taco();
	  }

	  @PostMapping
	  public String processDesign(
			  @Valid Taco design, 
			  Errors errors, @ModelAttribute Order order) {
		  if (errors.hasErrors()) {
			 return "design";
		  }

		  Taco saved = tacoRepo.save(design);
		  order.addDesign(saved);

		  return "redirect:/orders/current";
	  }

}