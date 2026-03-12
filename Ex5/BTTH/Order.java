package model;

import java.util.*;

public class Order {

    private String orderId;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public void addItem(MenuItem item, int quantity) {
        items.add(new OrderItem(item, quantity));
    }

    public double calculateTotal() {

        return items.stream()
                .mapToDouble(OrderItem::getTotal)
                .sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return items;
    }
}