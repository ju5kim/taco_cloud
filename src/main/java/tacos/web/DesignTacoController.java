package tacos.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.User;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;
import tacos.data.UserRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientRepo;
	private TacoRepository tacoRepo;
	private UserRepository userRepo;

	
	@Autowired // 이렇게 생성자에 의존서을 주입하면 어던 장점이 있는 것일까?
	public DesignTacoController(
			IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
	  this.ingredientRepo = ingredientRepo;
	  this.tacoRepo = tacoRepo;
	  this.userRepo=userRepo;
	}
	

	@GetMapping
	  public String showDesignForm(Model model, Principal principal) {
	    
		List<Ingredient> ingredients = new ArrayList<>();
	    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

	    Type[] types = Ingredient.Type.values();
	    for (Type type : types) {// 식자재의 유형을 필터링해서 LIST를 반환한다.
	      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
	    }

	    //model.addAttribute("taco", new Taco());
	    String username = principal.getName();
	    User user = userRepo.findByUsername(username);
	    model.addAttribute("user",user);
	    return "design";
	  }
	
	  private List<Ingredient> filterByType(
	      List<Ingredient> ingredients, Type type) {
	    return ingredients.stream()
	              .filter(x -> x.getType().equals(type)) // 매개변수 x는 Ingredient(리스트의 객체)
	              .collect(Collectors.toList()); // 리스트로 반환해라
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
	//이 매개변수의 값이 모델로부터 전달 되어야 하는 것과 스프링이 매개변수에 요청매개변수를 바인딩 하지 않아야 한다?
	  public String processDesign(  @Valid Taco design,  Errors errors, @ModelAttribute Order order) {
		  if (errors.hasErrors()) {
			 return "design";
		  }

		  Taco saved = tacoRepo.save(design);
		  order.addDesign(saved);

		  return "redirect:/orders/current";  //사용자가 주문뷰에서 입력을 완료하고 제출할 떄 까지 Order 객체는 세션에 남아있고 데이터베이스에 저장되지 않는다.
	  }

}