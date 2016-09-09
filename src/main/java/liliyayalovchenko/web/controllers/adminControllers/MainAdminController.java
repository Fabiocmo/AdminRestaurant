package liliyayalovchenko.web.controllers.adminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class MainAdminController {

    @RequestMapping(value = "/")
    public ModelAndView adminAccess () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView adminAccess (@RequestParam(value = "login") String login,
                                     @RequestParam(value = "password") String password,
                                     HttpServletRequest request,
                                     ModelMap model) {
        HttpSession session = request.getSession();
        if((login.equals("adminLogin")&&(password.equals("111000")))) {
            session.setAttribute("role", "admin");
            return new ModelAndView("redirect:/admin/index", model);
        } else {
            model.addAttribute("message", "Wrong number or/and password");
            return new ModelAndView("adminLogin", model);
        }
    }

    @RequestMapping(value = "/index")
    public ModelAndView adminIndex (HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminIndex");
        } else {
            modelAndView.setViewName("adminLogin");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public ModelAndView adminAccess (HttpServletRequest request,
                                     ModelMap model) {
        HttpSession session = request.getSession();
        session.removeAttribute("role");
        return new ModelAndView("redirect:/admin/", model);
    }


    private boolean verify(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role != null && role.equals("admin");
    }
}
