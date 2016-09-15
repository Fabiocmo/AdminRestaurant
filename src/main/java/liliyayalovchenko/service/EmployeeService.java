package liliyayalovchenko.service;


import liliyayalovchenko.dao.EmployeeDAO;
import liliyayalovchenko.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAll();
    }

    @Transactional
    public List<Employee> getAllWaiters() {
        return employeeDAO.getAllWaiters();
    }

    @Transactional
    public Employee getEmployeeById(int id) {
        return employeeDAO.getById(id);
    }

    @Transactional
    public void saveEmployee(int id, String secondName, String firstName, String dateOfEmpl,
                             String phone, String position, int salary, String photoLink) throws ParseException {
        employeeDAO.save(id, secondName, firstName, dateOfEmpl, phone, position, salary, photoLink);
    }

    @Transactional
    public void saveEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    @Transactional
    public void remove(int id) {
        employeeDAO.removeEmployee(id);
    }
}
