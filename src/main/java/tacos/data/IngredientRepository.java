//package tacos.data;
//
//import tacos.Ingredient;
//
//public interface IngredientRepository {
//	Iterable<Ingredient> findAll();
//	Ingredient findById(String id);
//	Ingredient save(Ingredient ingredient);
//
//}

package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{ //Ingredient 객체와 객체 ID의 타입

//	Iterable<Ingredient> findAll();
//
//	Ingredient findById(String id);
//
//	Ingredient save(Ingredient ingredient);

}