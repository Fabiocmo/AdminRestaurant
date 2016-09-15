package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.TestContext;
import liliyayalovchenko.service.DishService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class, WebConfig.class})
@WebAppConfiguration
public class DishAdminControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private DishService dishServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        dishServiceMock = Mockito.mock(DishService.class);
        Mockito.reset(dishServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldNotFindByWrongId() throws Exception {
        when(dishServiceMock.getDishById(50));

        mockMvc.perform(get("/admin/dish/{id}", 50).sessionAttr("role", "admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/error.jsp"));
    }


}