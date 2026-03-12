package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsService {
    public double totalRevenue(List<Order> orders) {

        return orders.stream()
                .filter(o -> o.getStatus() == OrderStatus.PAID)
                .mapToDouble(Order::calculateTotal)
                .sum();
    }

    public Map<String,Integer> bestSelling(List<Order> orders) {

        Map<String,Integer> map = new HashMap<>();

        for(Order order : orders){

            for(OrderItem item : order.getItems()){

                map.put(
                        item.getItem().getName(),
                        map.getOrDefault(item.getItem().getName(),0)
                                + item.getQuantity()
                );
            }
        }

        return map.entrySet()
                .stream()
                .sorted((a,b)->b.getValue()-a.getValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b)->a,
                        LinkedHashMap::new
                ));
    }
}