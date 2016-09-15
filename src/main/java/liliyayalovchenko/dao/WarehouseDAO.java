package liliyayalovchenko.dao;


import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Warehouse;
import liliyayalovchenko.web.exeptions.IngredientNotFoundException;

import java.util.List;

public interface WarehouseDAO {

    void addIngredient(Ingredient ingredient, int amount);

    void removeIngredient(String ingredientName) throws IngredientNotFoundException;

    List<Warehouse> getByName(String ingredientName);

    List<Warehouse> getAllIngredients();

    List<Warehouse> getAllOrderByName();

    List<Warehouse> getAllOrderByAmount();

    Warehouse getIngredientById(int id);

    void edit(int id, int amount);

}
