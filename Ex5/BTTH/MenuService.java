package service;

import model.MenuItem;
import java.util.*;
import java.util.stream.Collectors;

public class MenuService {

    private List<MenuItem> menu = new ArrayList<>();

    public void addItem(MenuItem item) {
        menu.add(item);
    }

    public void removeItem(String id) {
        menu.removeIf(i -> i.getId().equals(id));
    }

    public void updatePrice(String id, double price) {
        menu.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .ifPresent(i -> i.setPrice(price));
    }

    public Optional<MenuItem> findById(String id) {
        return menu.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public List<MenuItem> searchByName(String name) {

        return menu.stream()
                .filter(i -> i.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<MenuItem> searchByPrice(double min, double max) {

        return menu.stream()
                .filter(i -> i.getPrice() >= min && i.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public void displayMenu() {
        menu.forEach(System.out::println);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }
}