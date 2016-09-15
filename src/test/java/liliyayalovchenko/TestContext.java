package liliyayalovchenko;

import liliyayalovchenko.service.EmployeeService;
import liliyayalovchenko.service.MenuService;
import org.mockito.Mockito;
import liliyayalovchenko.service.WarehouseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    public WarehouseService warehouseServiceMock() {
        return Mockito.mock(WarehouseService.class);
    }

    @Bean
    public EmployeeService employeeService() {
        return Mockito.mock(EmployeeService.class);
    }

    @Bean
    public MenuService menuService() {
        return Mockito.mock(MenuService.class);
    }

}
