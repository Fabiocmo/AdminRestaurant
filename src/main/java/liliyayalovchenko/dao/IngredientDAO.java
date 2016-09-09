package liliyayalovchenko.dao;


import liliyayalovchenko.domain.Ingredient;

import java.util.List;

public interface IngredientDAO {

    List<Ingredient> getAllIngredients();

    Ingredient getIngredientByName(String name);

    boolean exist(String ingredientName);

    void addIngredient(Ingredient ingredient);
}
