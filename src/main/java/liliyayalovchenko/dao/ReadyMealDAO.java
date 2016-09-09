package liliyayalovchenko.dao;

import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.ReadyMeal;

import java.util.List;

public interface ReadyMealDAO {

    List<ReadyMeal> getAllReadyMeals();

    void addReadyMeal(ReadyMeal meal);

    void removeReadyMeal(int orderNumber);

    void changeAmountOnWarehouse(Ingredient ingredient);
}
