package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.validation.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	@GetMapping
	  public String showDesignForm(Model model) {
	    List<Ingredient> ingredients = Arrays.asList(
	      new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
	      new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
	      new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
	      new Ingredient("CARN", "Carnitas", Type.PROTEIN),
	      new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
	      new Ingredient("LETC", "Lettuce", Type.VEGGIES),
	      new Ingredient("CHED", "Cheddar", Type.CHEESE),
	      new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
	      new Ingredient("SLSA", "Salsa", Type.SAUCE),
	      new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
	    );

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

	  @PostMapping
	  public String processDesign(@Valid Taco design, Errors errors) {
		  if (errors.hasErrors()) {
			 return "design";
		  }

		  // 이 지점에서 타코 디자인(선택된 식재료 내역)을 저장한다…
		  // 이 작업은 3장에서 할 것이다
		  log.info("Processing design: " + design);

		  return "redirect:/orders/current";
	  }

}//package tacos.web;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import lombok.extern.slf4j.Slf4j;
//import tacos.Ingredient;
//import tacos.Ingredient.Type;
//import tacos.Taco;
//
//@Slf4j
//@Controller
//@RequestMapping("/Design")
//public class DesignTacoController {
//	
//	@GetMapping
//	public String showDesignForm(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(
//		new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//		new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//		new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//		new Ingredient("CARN","Carnitas",Type.PROTEIN),
//		new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//		new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//		new Ingredient("CHED", "Cheddar", Type.CHEESE),
//		new Ingredient("JACK", "Monterrey", Type.CHEESE),
//		new Ingredient("SLSA", "Salsa", Type.SAUCE),
//		new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//		);
//		
//		Type[] types = Ingredient.Type.values(); 
//		for(Type type:types){			//모델에 식자제 이름을 소문자로  , 식자제
//			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients,type) // 식자재의 유형을 List에서 필터링해서
//			);
//			
//		}
//		model.addAttribute("taco",new Taco());
//	return "Design";	
//	}
//	
//	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
//		return ingredients.stream()
//				.filter(x->x.getType()
//						.equals(type))
//				.collect(Collectors.toList());// 이게 뭐지... 스트림최종연사 수집해라 리스트로 보이게~~ ~~ 아~~ 그래서 리턴이 LIST인데 오류가 안나구나
//		
//	}
//}
