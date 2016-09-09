package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.domain.Ingredient;
import liliyayalovchenko.service.DishService;
import liliyayalovchenko.service.IngredientService;
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
public class DishAdminController {

    @Autowired
    private DishService dishService;

    @Autowired
    private IngredientService ingredientService;

    @RequestMapping(value = "/dish/{id}", method = RequestMethod.GET)
    public ModelAndView dish(@PathVariable int id,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminDish");
            modelAndView.addObject("dish", dishService.getDishById(id));
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/dish", method = RequestMethod.GET)
    public ModelAndView dish(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminDishes");
            modelAndView.addObject("dishList", dishService.getAllDishes());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/dish/edit/{id}", method = RequestMethod.GET)
    public ModelAndView dishEdit(@PathVariable int id,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminDishEdit");
            modelAndView.addObject("dish", dishService.getDishById(id));
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/dish/save/{id}", method = RequestMethod.POST)
    public ModelAndView dishSave(@PathVariable int id,
                                 ModelMap model,
                                 @RequestParam String name,
                                 @RequestParam String dishCategory,
                                 @RequestParam double price,
                                 @RequestParam int weight,
                                 @RequestParam String photoLink,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (verify(session)) {
            dishService.saveDish(id, name, dishCategory, price, weight, photoLink);
            return new ModelAndView("redirect:/admin/dish/{id}", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/dish/add", method = RequestMethod.GET)
    public ModelAndView dishAdd(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminAddDish");
            modelAndView.addObject("ingredients", ingredientService.getAllIngredients());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/dish/addNew", method = RequestMethod.POST)
    public ModelAndView dishAdd( ModelMap model,
                                 @RequestParam String name,
                                 @RequestParam String dishCategory,
                                 @RequestParam double price,
                                 @RequestParam int weight,
                                 @RequestParam String photoLink,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (verify(session)) {
            List<String> ingredientNames = Arrays.asList(request.getParameterValues("ingredientName"));
            List<Ingredient> ingredients = ingredientNames.stream().
                    map(ingredientService::getIngredient).collect(Collectors.toList());

            dishService.saveDish(name, dishCategory, price, weight, photoLink, ingredients);
            return new ModelAndView("redirect:/admin/dish", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/dish/remove/{id}", method = RequestMethod.DELETE)
    public ModelAndView dishRemove( ModelMap model,
                                    @PathVariable int id,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (verify(session)) {
           dishService.remove(id);
           return new ModelAndView("redirect:/admin/dish", model);
        }
        return new ModelAndView("adminLogin", model);
    }



    private boolean verify(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role != null && role.equals("admin");
    }
}
