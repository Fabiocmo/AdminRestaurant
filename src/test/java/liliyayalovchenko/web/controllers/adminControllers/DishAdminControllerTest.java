package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.dao.DishDAO;
import liliyayalovchenko.dao.IngredientDAO;
import liliyayalovchenko.dao.MenuDAO;
import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.DishCategory;
import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Menu;
import liliyayalovchenko.service.DishService;
import liliyayalovchenko.web.configuration.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class DishAdminControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private DishService dishServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MenuDAO menuDAO;

    @Autowired
    private IngredientDAO ingredientDAO;

    @Autowired
    private DishDAO dishDAO;

    @Before
    public void setUp() {
        dishServiceMock = Mockito.mock(DishService.class);
        Mockito.reset(dishServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testShouldEditDish() throws Exception {
        Menu menu = createAndPersistMenu("Summer");
        List<Ingredient> ingredients = createAndPersistIngredList();
        Dish dish = createDish(ingredients, menu, DishCategory.DRINKS, "Melon fresh");
        dishDAO.save(dish);

        String newDishName = "Summer melon";
        double newPrice = 60;

        mockMvc.perform(post("/admin//dish/save/{id}", dish.getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", newDishName)
                        .param("price", String.valueOf(newPrice))
                        .param("dishCategory", dish.getDishCategory().toString())
                        .param("weight", String.valueOf(dish.getWeight()))
                        .param("photoLink", "newPhotoLink")
                        .sessionAttr("role", "admin")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/admin/dish/{id}"))
                .andExpect(redirectedUrl("/admin/dish/" + dish.getId()));

        assertEquals(dish.getName(), newDishName);
        assertEquals(dish.getPrice(), newPrice, 0);
    }



    @Test
    public void shouldNotFindByWrongId() throws Exception {
        when(dishServiceMock.getDishById(50));

        mockMvc.perform(get("/admin/dish/{id}", 50).sessionAttr("role", "admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/error.jsp"));
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
        menuDAO.createMenu(menu);
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