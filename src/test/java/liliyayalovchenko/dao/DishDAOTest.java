package liliyayalovchenko.dao;

import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.DishCategory;
import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Menu;
import liliyayalovchenko.web.configuration.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class}, loader = AnnotationConfigWebContextLoader.class)
public class DishDAOTest {

    @Autowired
    private MenuDAO meuDAO;

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private IngredientDAO ingredientDAO;

    @Test
    @Transactional
    @Rollback(true)
    public void testSave() throws Exception {
        Menu menu = createAndPersistMenu("Summer");
        List<Ingredient> ingredients = createAndPersistIngredList();
        Dish dish = createDish(ingredients, menu, DishCategory.DRINKS, "Melon fresh");

        dishDAO.save(dish);

        List<Dish> listDish = dishDAO.getAll();

        assertTrue(listDish.contains(dish));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testGetDishById() throws Exception {
        Menu menu = createAndPersistMenu("Summer");
        List<Ingredient> ingredients = createAndPersistIngredList();
        Dish dish = createDish(ingredients, menu, DishCategory.DRINKS, "Melon fresh");

        dishDAO.save(dish);

        Dish dishById = dishDAO.getDishById(dish.getId());

        assertEquals(dish.getName(), dishById.getName());
        assertEquals(dish.getMenu(), dishById.getMenu());
        assertTrue(dishById.getMenu().getDishList().contains(dish));
        assertTrue(dishById.getIngredients().contains(ingredients.get(0)));
    }

    @Test(expected=RuntimeException.class)
    @Transactional
    @Rollback(true)
    public void testRemove() throws Exception {
        Menu menu = createAndPersistMenu("Summer");
        List<Ingredient> ingredients = createAndPersistIngredList();
        Dish dish = createDish(ingredients, menu, DishCategory.DRINKS, "Melon fresh");

        dishDAO.save(dish);

        Dish dishById = dishDAO.getDishById(dish.getId());
        assertNotNull(dishById);
        assertTrue(dishById.getMenu().getDishList().contains(dish));

        dishDAO.remove(dishById.getId());
        dishDAO.getDishById(dishById.getId());
    }

    private Dish createDish(List<Ingredient> ingredients, Menu menu, DishCategory category, String name) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setMenu(menu);
        dish.setDishCategory(category);
        dish.setWeight(250);
        dish.setPrice(40);
        dish.setIngredients(ingredients);
        return dish;
    }

    private Menu createAndPersistMenu(String name) {
        Menu menu = new Menu();
        menu.setName(name);
        meuDAO.createMenu(menu);
        return menu;
    }

    private List<Ingredient> createAndPersistIngredList() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Melon"));
        ingredients.add(new Ingredient(("Mint")));
        ingredients.forEach(ingredientDAO::addIngredient);
        return ingredients;
    }
}