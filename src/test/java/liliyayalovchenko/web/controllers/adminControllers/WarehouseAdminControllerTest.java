package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.dao.IngredientDAO;
import liliyayalovchenko.dao.WarehouseDAO;
import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Warehouse;
import liliyayalovchenko.service.WarehouseService;
import liliyayalovchenko.web.configuration.WebConfig;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class WarehouseAdminControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WarehouseService warehouseServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private IngredientDAO ingredientDAO;

    @Autowired
    private WarehouseDAO warehouseDAO;

    @Before
    public void setUp() {
        warehouseServiceMock = Mockito.mock(WarehouseService.class);
        Mockito.reset(warehouseServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldFindAllIngredientsAnRenderThem() throws Exception {
        Ingredient ingredient1 = createIngredient("Mint");
        ingredientDAO.addIngredient(ingredient1);
        int amount1 = 40;
        warehouseDAO.addIngredient(ingredient1, amount1);

        Ingredient ingredient2 = createIngredient("Melon");
        ingredientDAO.addIngredient(ingredient2);
        int amount2 = 50;
        warehouseDAO.addIngredient(ingredient2, amount2);

        Warehouse warehouse1 = warehouseDAO.getByName(ingredient1.getName()).get(0);
        Warehouse warehouse2 = warehouseDAO.getByName(ingredient2.getName()).get(0);

        List<Warehouse> warehouseList = warehouseServiceMock.getAllIngredients();
        when(warehouseServiceMock.getAllIngredients()).thenReturn(warehouseList);

        mockMvc.perform(get("/admin/warehouse").sessionAttr("role", "admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminWarehouse"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/adminWarehouse.jsp"))
                .andExpect(model().attribute("warehouseList", hasItem(
                        allOf(
                                hasProperty("id", is(warehouse1.getId())),
                                hasProperty("ingredId", is(ingredient1)),
                                hasProperty("amount", is(amount1))
                        )
                )))
                .andExpect(model().attribute("warehouseList", hasItem(
                        allOf(
                                Matchers.<Object>hasProperty("id", is(warehouse2.getId())),
                                hasProperty("ingredId", is(ingredient2)),
                                hasProperty("amount", is(amount2))
                        )
                )));

        verify(warehouseServiceMock, times(1)).getAllIngredients();
        verifyNoMoreInteractions(warehouseServiceMock);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void shouldFinIngredientByIdAndRenderIt() throws Exception {
        Ingredient ingredient = createIngredient("Mint");
        ingredientDAO.addIngredient(ingredient);
        int amount = 40;
        warehouseDAO.addIngredient(ingredient, amount);

        Warehouse warehouse = warehouseDAO.getByName(ingredient.getName()).get(0);

        when(warehouseServiceMock.getById(warehouse.getId())).thenReturn(warehouse);

        mockMvc.perform(get("/admin/warehouse/" + warehouse.getId()).sessionAttr("role", "admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("adminIngredient"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/adminIngredient.jsp"))
                .andExpect(model().attribute("ingredient", hasProperty("id", is(warehouse.getId()))))
                .andExpect(model().attribute("ingredient", hasProperty("ingredId", is(ingredient))))
                .andExpect(model().attribute("ingredient", hasProperty("amount", is(amount))));

    }

    private Ingredient createIngredient(String mint) {
        return new Ingredient(mint);
    }
}