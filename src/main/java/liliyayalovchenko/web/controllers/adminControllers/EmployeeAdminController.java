package liliyayalovchenko.web.controllers.adminControllers;

import liliyayalovchenko.domain.Cook;
import liliyayalovchenko.domain.Employee;
import liliyayalovchenko.domain.Position;
import liliyayalovchenko.service.EmployeeService;
import liliyayalovchenko.web.exeptions.EmployeeNotFoundException;
import liliyayalovchenko.web.exeptions.WrongDateInputFormatException;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/admin")
public class EmployeeAdminController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ModelAndView employee(@PathVariable int id,
                                 HttpServletRequest request) throws EmployeeNotFoundException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminEmployee");
            Employee employeeById;
            try {
                employeeById = employeeService.getEmployeeById(id);
            } catch (ObjectNotFoundException ex) {
                throw new EmployeeNotFoundException(id);
            }
            modelAndView.addObject("employee", employeeById);
            modelAndView.addObject("date", new SimpleDateFormat("dd-MM-yyyy").format(employeeById.getEmplDate()));
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ModelAndView employee(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminEmployees");
            modelAndView.addObject("employees", employeeService.getAllEmployees());
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/employee/edit/{id}", method = RequestMethod.GET)
    public ModelAndView employeeEdit(@PathVariable int id,
                                     HttpServletRequest request) throws EmployeeNotFoundException {
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();
        if (verify(session)) {
            modelAndView.setViewName("adminEmployeeEdit");
            Employee employeeById;
            try {
                employeeById = employeeService.getEmployeeById(id);
            } catch (ObjectNotFoundException ex) {
                throw new EmployeeNotFoundException(id);
            }
            modelAndView.addObject("employee", employeeById);
            modelAndView.addObject("date", new SimpleDateFormat("dd-MM-yyyy").format(employeeById.getEmplDate()));
            return modelAndView;
        }
        modelAndView.setViewName("adminLogin");
        return modelAndView;
    }

    @RequestMapping(value = "/employee/save/{id}", method = RequestMethod.POST)
    public ModelAndView employeeSave(@PathVariable int id,
                                     ModelMap model,
                                     @RequestParam String secondName,
                                     @RequestParam String firstName,
                                     @RequestParam String dateOfEmpl,
                                     @RequestParam String phone,
                                     @RequestParam String position,
                                     @RequestParam int salary,
                                     @RequestParam String photoLink,
                                     HttpServletRequest request) throws WrongDateInputFormatException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            try {
                employeeService.saveEmployee(id, secondName, firstName, dateOfEmpl, phone, position, salary, photoLink);
            } catch (ParseException e) {
                throw new WrongDateInputFormatException(dateOfEmpl);
            }
            return new ModelAndView("redirect:/admin/employee/{id}", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    @RequestMapping(value = "/employee/remove/{id}", method = RequestMethod.GET)
    public ModelAndView employeeRemove(@PathVariable int id,
                                       ModelMap model,
                                       HttpServletRequest request) throws EmployeeNotFoundException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            try {
                employeeService.remove(id);
            } catch (ObjectNotFoundException ex) {
                throw new EmployeeNotFoundException(id);
            }
            return new ModelAndView("redirect:/admin/employee", model);
        }
        return new ModelAndView("adminLogin", model);
    }


    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)
    public ModelAndView employeeSaveNew(ModelMap model,
                                        @RequestParam String secondName,
                                        @RequestParam String firstName,
                                        @RequestParam String dateOfEmpl,
                                        @RequestParam String phone,
                                        @RequestParam String position,
                                        @RequestParam int salary,
                                        @RequestParam String photoLink,
                                        HttpServletRequest request) throws WrongDateInputFormatException {
        HttpSession session = request.getSession();
        if (verify(session)) {
            Employee employee;
            employee = position.equals("COOK") ? new Cook() : new Employee();
            employee.setFirstName(firstName);
            employee.setSecondName(secondName);
            employee.setPhone(phone);
            employee.setPhotoLink(photoLink);
            parseDate(dateOfEmpl, employee);
            employee.setSalary(salary);
            setPosition(position, employee);
            employee.setPhotoLink(photoLink);
            employeeService.saveEmployee(employee);
            return new ModelAndView("redirect:/admin/employee", model);
        }
        return new ModelAndView("adminLogin", model);
    }

    private void setPosition(String position, Employee employee) {
        for (Position position1 : Position.values()) {
            if (position1.toString().equals(position)) {
                employee.setPosition(position1);
            }
        }
    }

    private void parseDate(String dateOfEmpl, Employee employee) throws WrongDateInputFormatException {
        try {
            employee.setEmplDate(new SimpleDateFormat("dd-MM-yyyy").parse(dateOfEmpl));
        } catch (ParseException e) {
            throw new WrongDateInputFormatException(dateOfEmpl);
        }
    }

    private boolean verify(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return role != null && role.equals("admin");
    }
}
