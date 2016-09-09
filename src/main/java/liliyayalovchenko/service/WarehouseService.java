package liliyayalovchenko.service;

import liliyayalovchenko.dao.WarehouseDAO;
import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class WarehouseService {

    @Autowired
    private WarehouseDAO warehouseDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseService.class);

    @Transactional
    public List<Warehouse> getAllIngredients() {
        return  warehouseDAO.getAllIngredients();
    }

    @Transactional
    public void addIngredient(Ingredient ingredient, int amount) {
        warehouseDAO.addIngredient(ingredient, amount);
    }

    @Transactional
    public void remove(String name) {
        warehouseDAO.removeIngredient(name);
    }

    @Transactional
    public List<Warehouse> getAllOrderByName() {
        return warehouseDAO.getAllOrderByName();
    }

    @Transactional
    public List<Warehouse> getAllOrderByAmount() {
        return warehouseDAO.getAllOrderByAmount();
    }

    @Transactional
    public List<Warehouse> getIngredientByName(String name) {
        return warehouseDAO.getByName(name);
    }
}
