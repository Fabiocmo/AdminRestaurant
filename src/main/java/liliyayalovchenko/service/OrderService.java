package liliyayalovchenko.service;

import liliyayalovchenko.dao.OrderDAO;
import liliyayalovchenko.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public List<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    @Transactional
    public Order getOrderById(int id) {
        return orderDAO.getOrderById(id);
    }

    @Transactional
    public List<Order> getOrderByEmployee(String name) {
        return orderDAO.getOrderByEmployee(name);
    }

    @Transactional
    public List<Order> getOrderByDate(String date) throws ParseException {
        return orderDAO.getOrderByDate(date);
    }

    @Transactional
    public List<Order> getOrderByTable(int tableNumber) {
        return orderDAO.getOrderByTable(tableNumber);
    }


}
