package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.Menu;
import liliyayalovchenko.service.DishService;
import liliyayalovchenko.service.MenuService;
import liliyayalovchenko.web.exeptions.DishNotFoundException;
import liliyayalovchenko.web.exeptions.MenuNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class MenuAdminController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private DishService dishService;

    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
    public ModelAndView menu(@PathVariable int id,
                             HttpServletRequest request) throws MenuNotFoundException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminMenu");
            Menu menuById;
            try {
                menuById = menuService.getMenuById(id);
            } catch (ObjectNotFoundException ex) {
                throw new MenuNotFoundException(id);
            }
            modelAndView.addObject("menu", menuById);
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/menu/edit/{id}", method = RequestMethod.GET)
    public ModelAndView menuEdit(@PathVariable int id,
                                 HttpServletRequest request) throws MenuNotFoundException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (verify(session)) {
            modelAndView.setViewName("adminMenuEdit");
            Menu menu;
            try {
                menu = menuService.getMenuById(id);
            } catch (ObjectNotFoundException ex) {
                throw new MenuNotFoundException(id);
            }
            session.setAttribute("dishList", menu.getDishList());
            modelAndView.addObject("menu", menu);
            modelAndView.addObject("allDishes", dishService.getAllDishes());
        } else {
            modelAndView.setViewName("adminLogin");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/menu/save/{id}", method = RequestMethod.POST)
    public ModelAndView menuSave(@PathVariable int id,
                                 ModelMap model,
                                 @RequestParam String name,
                                 HttpServletRequest request) throws MenuNotFoundException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            try {
                menuService.saveMenu(id, name);
            } catch (ObjectNotFoundException ex) {
                throw new MenuNotFoundException(id);
            }
            return new ModelAndView("redirect:/admin/menu/{id}", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/addDishToMenu/{id}", method = RequestMethod.POST)
    public ModelAndView menuSave(ModelMap model,
                                 @PathVariable int id,
                                 HttpServletRequest request) throws MenuNotFoundException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            List<Dish> dishList = (List<Dish>) session.getAttribute("dishList");
            Dish dishToAdd = dishService.getDishByName(request.getParameterValues("dishName")[0]);
            if (!dishList.contains(dishToAdd)) {
                dishList.add(dishToAdd);
            }
            Menu menu;
            try {
                menu = menuService.getMenuById(id);
            } catch (ObjectNotFoundException ex) {
                throw new MenuNotFoundException(id);
            }
            menuService.addDishToMenu(id, dishToAdd);
            menuService.updateDish(dishToAdd, menu);
            session.setAttribute("dishList", dishList);
            return new ModelAndView("redirect:/admin/menu/{id}", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/removeDishFromMenu/{id}", method = RequestMethod.POST)
    public ModelAndView removeFromMenu(ModelMap model,
                                       @PathVariable int id,
                                       HttpServletRequest request) throws DishNotFoundException, MenuNotFoundException {
        HttpSession session = request.getSession();
        if(verify(session)) {
            List<Dish> dishList = ((List<Dish>) session.getAttribute("dishList"));
            String dishToRemove= request.getParameterValues("dishName")[0];
            Dish dishByName;
            try {
                dishByName = dishService.getDishByName(dishToRemove);
            } catch (ObjectNotFoundException ex) {
                throw new DishNotFoundException(dishToRemove);
            }

            try {
                menuService.removeDishFromMenu(id, dishByName);
            }catch (ObjectNotFoundException ex) {
                throw new MenuNotFoundException(id);
            }
            menuService.updateDish(dishByName, null);
            dishList.remove(dishByName);
            session.setAttribute("dishList", dishList);

            return new ModelAndView("redirect:/admin/menu/{id}", model);
        }
        return new ModelAndView("adminLogin", model);
    }



    @RequestMapping(value = "/menu/remove/{id}")
    public ModelAndView menuRemove(@PathVariable int id,
                                   ModelMap model,
                                   HttpServletRequest request) throws MenuNotFoundException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            Menu menu;
            try {
                menu = menuService.getMenuById(id);
            } catch (ObjectNotFoundException ex) {
                throw new MenuNotFoundException(id);
            }
            menuService.removeMenu(id);
            for (Dish dish : menu.getDishList()) {
                menuService.updateDish(dish, null);
            }

            model.addAttribute("menuList", menuService.getAllMenus());
            return new ModelAndView("redirect:/admin/menu", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/menu/add", method = RequestMethod.POST)
    public ModelAndView menuAdd(ModelMap model,
                                @RequestParam String name,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (verify(session)) {
            Menu menu = new Menu();
            menu.setName(name);
            List<String> dishNames = Arrays.asList(request.getParameterValues("dishName"));
            List<Dish> dishList = null;
            if (dishNames.size() != 0) {
                dishList = dishNames.stream().map(dishService::getDishByName).collect(Collectors.toList());
                menu.setDishList(dishList);
            }
            menuService.createMenu(menu);

            if (dishList != null) {
                for (Dish dish : dishList) {
                menuService.updateDish(dish, menu);
                }
            }

            return new ModelAndView("redirect:/admin/menu", model);
        }
        return new ModelAndView("adminLogin", model);
    }


    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView menus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminMenus");
            modelAndView.addObject("menuList", menuService.getAllMenus());
            modelAndView.addObject("dishList", dishService.getAllDishes());
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    private boolean verify(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role != null && role.equals("admin");
    }
}
