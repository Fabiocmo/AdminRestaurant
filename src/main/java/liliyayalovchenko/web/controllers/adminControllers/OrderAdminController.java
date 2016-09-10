package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.domain.Order;
import liliyayalovchenko.service.EmployeeService;
import liliyayalovchenko.service.OrderService;
import liliyayalovchenko.web.exeptions.OrderNotFoundException;
import liliyayalovchenko.web.exeptions.WrongDateInputFormatException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView order(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminOrders");
            modelAndView.addObject("orders", orderService.getAllOrders());
            modelAndView.addObject("waiters", employeeService.getAllWaiters());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public ModelAndView order(@PathVariable int id,
                              HttpServletRequest request) throws OrderNotFoundException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminOrder");
            Order order;
            try {
                order = orderService.getOrderById(id);
            } catch (ObjectNotFoundException ex) {
                throw new OrderNotFoundException(id);
            }
            modelAndView.addObject("order", order);
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/order/filterByEmployee", method = RequestMethod.POST)
    public ModelAndView orderFilterByEmployee(HttpServletRequest request) throws OrderNotFoundException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            String employeeName = request.getParameter("name");
            modelAndView.setViewName("adminOrders");
            modelAndView.addObject("waiters", employeeService.getAllWaiters());
            List<Order> orders;
            try {
                orders = orderService.getOrderByEmployee(employeeName);
            } catch (ObjectNotFoundException ex) {
                throw new OrderNotFoundException(employeeName);
            }
            modelAndView.addObject("orders", orders);
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/order/filterByDate", method = RequestMethod.POST)
    public ModelAndView orderFilterByDate(HttpServletRequest request,
                                          @RequestParam String date) throws WrongDateInputFormatException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminOrders");
            modelAndView.addObject("waiters", employeeService.getAllWaiters());
            List<Order> orderList;
            try {
                orderList = orderService.getOrderByDate(date);
            } catch (ParseException e) {
                throw new WrongDateInputFormatException(date);
            }
            modelAndView.addObject("orders", orderList);
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/order/filterByTable", method = RequestMethod.POST)
    public ModelAndView orderFilterByTable(HttpServletRequest request,
                                           @RequestParam int tableNumber) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminOrders");
            modelAndView.addObject("waiters", employeeService.getAllWaiters());
            modelAndView.addObject("orders", orderService.getOrderByTable(tableNumber));
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    private boolean verify(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role != null && role.equals("admin");
    }
}
