package liliyayalovchenko.dao;

import liliyayalovchenko.domain.Employee;

import java.text.ParseException;
import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);

    void save(int id, String secondName, String firstName, String dateOfEmpl,
              String phone, String position, int salary, String photoLink) throws ParseException;

    Employee getById(int id);

    List<Employee> getAllWaiters();

    List<Employee> getAll();

    void removeEmployee(int id);

}
