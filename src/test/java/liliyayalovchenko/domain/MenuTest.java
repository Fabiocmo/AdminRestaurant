package liliyayalovchenko.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MenuTest {

    private Menu menu;

    @Before
    public void before() {
        menu = new Menu();
        menu.setName("Summer");

        List<Dish> dishs = dishList();
        for (Dish dish : dishs) {
            dish.setMenu(menu);
        }

        menu.setDishList(dishs);
    }

    @Test
    public void testRemoveDishFromMenu() throws Exception {
        Dish dishToRemove = menu.getDishList().get(0);

        assertTrue(dishToRemove.getMenu().equals(menu));
        menu.removeDishFromMenu(dishToRemove);

        assertTrue(menu.getDishList().size() == 1);
        assertFalse(menu.getDishList().contains(dishToRemove));
        assertTrue(dishToRemove.getMenu() == null);

    }

    @Test
    public void testAddDishToMenu() throws Exception {

    }

    private List<Dish> dishList() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Orange"));
        ingredients.add(new Ingredient("Lemon"));

        Dish dish1 = generateDish(ingredients, "Juice");
        Dish dish2 = generateDish(ingredients, "Fruit");

        List<Dish> dishList = new ArrayList<>();
        dishList.add(dish1);
        dishList.add(dish2);

        return dishList;
    }

    private Dish generateDish(List<Ingredient> ingredients, String name) {
        Dish dish = new Dish();
        dish.setName(name);
        dish.setDishCategory(DishCategory.DRINKS);
        dish.setPrice(25);
        dish.setWeight(250);
        dish.setIngredients(ingredients);
        return dish;
    }
}