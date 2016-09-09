package liliyayalovchenko.service;

import liliyayalovchenko.dao.MenuDAO;
import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Transactional
    public List<Menu> getAllMenus() {
        return menuDAO.getAllMenu();
    }

    @Transactional
    public Menu getMenuById(int id) {
        return menuDAO.getMenuById(id);
    }

    @Transactional
    public void saveMenu(int id, String name) {
        menuDAO.saveMenu(id, name);
    }

    @Transactional
    public void removeMenu(int menu) {
        menuDAO.removeMenu(menu);
    }

    @Transactional
    public void removeDishFromMenu(int menuId, Dish dish) {
        menuDAO.removeDishFromMenu(menuId, dish);
    }

    @Transactional
    public void addDishToMenu(int menuId, Dish dish) {
        menuDAO.addDishToMenu(menuId, dish);
    }
    @Transactional
    public void updateDish(Dish dishByName, Menu menu) {
        menuDAO.updateDish(dishByName, menu);
    }

    @Transactional
    public void createMenu(Menu menu) {
        menuDAO.createMenu(menu);
    }

}
