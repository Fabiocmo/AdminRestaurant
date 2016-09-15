package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.domain.Warehouse;
import liliyayalovchenko.service.IngredientService;
import liliyayalovchenko.service.WarehouseService;
import liliyayalovchenko.web.exeptions.IngredientNotFoundException;
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

@Controller
@RequestMapping("/admin")
public class WarehouseAdminController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(value = "/warehouse", method = RequestMethod.GET)
    public ModelAndView warehouse(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminWarehouse");
            modelAndView.addObject("warehouseList", warehouseService.getAllIngredients());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/warehouse/{id}", method = RequestMethod.GET)
    public ModelAndView ingredient(@PathVariable int id,
                                   HttpServletRequest request) throws IngredientNotFoundException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminIngredient");
            Warehouse ingredient;
            try {
                ingredient = warehouseService.getById(id);
            } catch (ObjectNotFoundException ex) {
                throw new IngredientNotFoundException(id);
            }
            modelAndView.addObject("ingredient", ingredient);
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }


    @RequestMapping(value = "/warehouse/filter/name", method = RequestMethod.GET)
    public ModelAndView warehouseFilterName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminWarehouse");
            modelAndView.addObject("warehouseList", warehouseService.getAllOrderByName());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/warehouse/find", method = RequestMethod.POST)
    public ModelAndView warehouseFindByName(@RequestParam String name,
                                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminWarehouse");
            modelAndView.addObject("warehouseList", warehouseService.getIngredientByName(name));
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/warehouse/filter/amount", method = RequestMethod.GET)
    public ModelAndView warehouseFilterAmount(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminWarehouse");
            modelAndView.addObject("warehouseList", warehouseService.getAllOrderByAmount());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/warehouse/add", method = RequestMethod.POST)
    public ModelAndView warehouseAdd(HttpServletRequest request,
                                     ModelMap model,
                                     @RequestParam String name,
                                     @RequestParam int amount) throws IngredientNotFoundException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            if (ingredientService.ifExist(name)) {
                warehouseService.addIngredient(ingredientService.getIngredient(name), amount);
            } else {
                Ingredient ingredient = new Ingredient(name);
                ingredientService.save(ingredient);
                warehouseService.addIngredient(ingredient, amount);
            }
            return new ModelAndView("redirect:/admin/warehouse", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/warehouse/remove", method = RequestMethod.POST)
    public ModelAndView warehouseRemove(HttpServletRequest request,
                                        ModelMap model) throws IngredientNotFoundException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            String ingredientToRemove = request.getParameter("ingredName");
            warehouseService.remove(ingredientToRemove);
            return new ModelAndView("redirect:/admin/warehouse", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/warehouse/edit/{id}", method = RequestMethod.POST)
    public ModelAndView warehouseEdit(@PathVariable int id,
                                      @RequestParam int amount,
                                      HttpServletRequest request,
                                      ModelMap model) {
        HttpSession session = request.getSession();
        if (verify(session)) {
            warehouseService.edit(id, amount);
            return new ModelAndView("redirect:/admin/warehouse", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    private boolean verify(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role != null && role.equals("admin");
    }

}
