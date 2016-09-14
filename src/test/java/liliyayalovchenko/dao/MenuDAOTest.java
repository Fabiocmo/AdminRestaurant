package liliyayalovchenko.dao;

import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.DishCategory;
import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Menu;
import liliyayalovchenko.web.configuration.WebConfig;
import org.junit.Before;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class}, loader = AnnotationConfigWebContextLoader.class)
public class MenuDAOTest {

    private Menu menu;

    private List<Ingredient> ingredients;

    private List<Dish> dishList;

    private Dish dish1;

    private Dish dish2;

    @Autowired
    private MenuDAO menuDAO;

    @Autowired
    private DishDAO dishDAO;

    @Autowired
    private IngredientDAO ingredientDAO;

    @Before
    public void before() {
        menu = createMenu("Summer");
        ingredients = createAndPersistIngredList();
        dishList = new ArrayList<>();
        dish1 = createDish(ingredients, DishCategory.DRINKS, "Fresh");
        dish2 = createDish(ingredients, DishCategory.SALADS, "Fruit salad");
        dishDAO.save(dish1);
        dishDAO.save(dish2);
        dishList.add(dish1);
        dishList.add(dish2);
        menu.setDishList(dishList);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testCreateMenu() throws Exception {
        menuDAO.createMenu(menu);
        Menu menuById = menuDAO.getMenuById(menu.getId());
        assertEquals(menuById, menu);
        assertEquals(menuById.getDishList().get(0), dish1);
        assertEquals(menuById.getDishList().get(1), dish2);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddDishToMenu() throws Exception {
        menuDAO.createMenu(menu);

        Dish dish = createDish(ingredients, DishCategory.SALADS, "Fresh fruits");
        dishDAO.save(dish);
        menuDAO.addDishToMenu(menu.getId(), dish);

        assertTrue(menu.getDishList().contains(dish));
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateDish() throws Exception {
        menuDAO.createMenu(menu);
        Dish dish = createDish(ingredients, DishCategory.SALADS, "Fresh fruit");
        dishDAO.save(dish);

        menuDAO.updateDish(dish, menu);
        assertEquals(dish.getMenu(), menu);
    }

    private Dish createDish(List<Ingredient> ingredients, DishCategory category, String name) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDishCategory(category);
        dish.setWeight(250);
        dish.setPrice(40);
        dish.setIngredients(ingredients);
        return dish;
    }

    private Menu createMenu(String name) {
        Menu menu = new Menu();
        menu.setName(name);
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