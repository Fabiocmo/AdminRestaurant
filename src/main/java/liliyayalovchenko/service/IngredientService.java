package liliyayalovchenko.service;

import liliyayalovchenko.dao.IngredientDAO;
import liliyayalovchenko.domain.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class IngredientService {

    @Autowired
    private IngredientDAO ingredientDAO;

    @Transactional
    public List<Ingredient> getAllIngredients() {
        return ingredientDAO.getAllIngredients();
    }

    @Transactional
    public Ingredient getIngredient(String name) {
       return ingredientDAO.getIngredientByName(name);
    }

    @Transactional
    public boolean ifExist(String ingredient) {
        return ingredientDAO.exist(ingredient);
    }

    @Transactional
    public void save(Ingredient ingredient) {
        ingredientDAO.addIngredient(ingredient);
    }
}
