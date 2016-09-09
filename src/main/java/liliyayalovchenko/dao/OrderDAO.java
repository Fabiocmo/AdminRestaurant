package liliyayalovchenko.dao;

import liliyayalovchenko.domain.Dish;
import liliyayalovchenko.domain.Order;
import liliyayalovchenko.domain.OrderStatus;

import java.text.ParseException;
import java.util.List;

public interface OrderDAO {

    void save(Order order);

    List<Order> findAll();

    void addDishToOpenOrder(Dish dish, int orderNumber);

    void deleteOrder(int orderNumber);

    void changeOrderStatus(int orderNumber);

    List<Order> getOpenOrClosedOrder(OrderStatus orderStatus);

    Order getOrderById(int i);

    int getLastOrder();

    List<Order> getOrderByEmployee(String name);

    List<Order> getOrderByDate(String date) throws ParseException;

    List<Order> getOrderByTable(int tableNumber);
}
