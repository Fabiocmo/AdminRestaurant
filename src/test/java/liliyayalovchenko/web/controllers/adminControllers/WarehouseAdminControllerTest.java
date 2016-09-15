package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.TestContext;
import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Warehouse;
import liliyayalovchenko.service.WarehouseService;
import liliyayalovchenko.web.configuration.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebConfig.class})
@WebAppConfiguration
public class WarehouseAdminControllerTest {


    private MockMvc mockMvc;

    @Autowired
    private WarehouseService warehouseServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        warehouseServiceMock = Mockito.mock(WarehouseService.class);
        Mockito.reset(warehouseServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldFindAllIngredientsAnRenderThem() throws Exception {
        List<Warehouse> warehouseList = warehouseServiceMock.getAllIngredients();
        when(warehouseServiceMock.getAllIngredients()).thenReturn(warehouseList);

        mockMvc.perform(get("/admin/warehouse")
                .sessionAttr("role", "admin")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("adminWarehouse"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/adminWarehouse.jsp"))
                .andExpect(model().attribute("warehouseList", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("ingredId", isA(Ingredient.class)),
                                hasProperty("amount")
                        )
                )))
                .andExpect(model().attribute("warehouseList", hasItem(
                        allOf(
                                hasProperty("id", is(3)),
                                hasProperty("ingredId", isA(Ingredient.class)),
                                hasProperty("amount")
                        )
                )));

        verify(warehouseServiceMock, times(1)).getAllIngredients();
        verifyNoMoreInteractions(warehouseServiceMock);
    }


    @Test
    public void testIngredient() throws Exception {

    }

    @Test
    public void testWarehouseFindByName() throws Exception {

    }
}