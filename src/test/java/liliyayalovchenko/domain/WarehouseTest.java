package liliyayalovchenko.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WarehouseTest {

    private Warehouse warehouse;

    private Ingredient ingredient;

    @Before
    public void before() {
        warehouse = new Warehouse();
        ingredient = new Ingredient("Melon");
        warehouse.setIngredId(ingredient);
        warehouse.setAmount(40);
    }

    @Test
    public void testChangeAmount() throws Exception {
        int startAmount = warehouse.getAmount();
        int changeAmount = 30;
        warehouse.changeAmount(changeAmount, true);
        assertEquals(warehouse.getAmount(), startAmount + changeAmount);

        warehouse.setAmount(startAmount);
        warehouse.changeAmount(changeAmount, false);
        assertEquals(warehouse.getAmount(), startAmount - changeAmount);

    }
}