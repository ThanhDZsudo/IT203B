package service;

import model.*;
import exception.InvalidOrderIdException;

import java.util.*;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public Order createOrder(String id) {

        Order order = new Order(id);
        orders.add(order);
        return order;
    }

    public Order findOrder(String id) throws InvalidOrderIdException {

        return orders.stream()
                .filter(o -> o.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() -> new InvalidOrderIdException("Không tìm thấy đơn hàng"));
    }

    public void updateStatus(String id, OrderStatus status) throws InvalidOrderIdException {

        Order order = findOrder(id);
        order.setStatus(status);
    }

    public List<Order> getOrders() {
        return orders;
    }
}