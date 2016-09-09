package liliyayalovchenko.dao;


import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Warehouse;

import java.util.List;

public interface WarehouseDAO {

    void addIngredient(Ingredient ingredient, int amount);

    void removeIngredient(String ingredientName);

    List<Warehouse> getByName(String ingredientName);

    List<Warehouse> getAllIngredients();

    List<Warehouse> getAllOrderByName();

    List<Warehouse> getAllOrderByAmount();
}
