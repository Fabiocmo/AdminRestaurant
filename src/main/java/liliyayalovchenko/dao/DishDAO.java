package liliyayalovchenko.dao;

import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.Ingredient;

import java.util.List;

public interface DishDAO {

    void save(Dish dish);

    List<Dish> getAll();

    Dish getDishByName(String dishName);

    Dish getDishById(int dishId);

    List<Dish> search(String pattern);

    void saveDish(int id, String name, String dishCategory, double price, int weight, String photoLink);

    void saveDish(String name, String dishCategory, double price, int weight, String photoLink, List<Ingredient> ingredients);

    void remove(int id);
}
