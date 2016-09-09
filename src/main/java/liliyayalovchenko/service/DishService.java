package liliyayalovchenko.service;

import liliyayalovchenko.dao.DishDAO;
import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DishService {

    @Autowired
    private DishDAO dishDAO;

    @Transactional
    public Dish getDishByName(String dishName) {
        return dishDAO.getDishByName(dishName);
    }

    @Transactional
    public List<Dish> getAllDishes() {
        return dishDAO.getAll();
    }

    @Transactional
    public Dish getDishById(int id) {
        return dishDAO.getDishById(id);
    }

    @Transactional
    public void saveDish(int id, String name, String dishCategory, double price, int weight, String photoLink) {
        dishDAO.saveDish(id, name, dishCategory, price, weight, photoLink);
    }

    @Transactional
    public void saveDish(String name, String dishCategory, double price, int weight, String photoLink, List<Ingredient> ingredients) {
        dishDAO.saveDish(name, dishCategory, price, weight, photoLink, ingredients);
    }

    @Transactional
    public void remove(int id) {
        dishDAO.remove(id);
    }
}
