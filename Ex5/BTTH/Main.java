import model.*;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MenuService menuService = new MenuService();
        OrderService orderService = new OrderService();
        StatisticsService statisticsService = new StatisticsService();

        while(true){

            System.out.println("\n===== FAST FOOD SYSTEM =====");
            System.out.println("1. Hiển thị menu");
            System.out.println("2. Thêm món");
            System.out.println("3. Sửa giá món");
            System.out.println("4. Xoá món");
            System.out.println("5. Tìm món");
            System.out.println("6. Tạo đơn hàng");
            System.out.println("7. Doanh thu");
            System.out.println("0. Thoát");

            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    menuService.displayMenu();
                    break;

                case 2:

                    System.out.print("ID: ");
                    String id = sc.next();

                    System.out.print("Tên: ");
                    String name = sc.next();

                    System.out.print("Giá: ");
                    double price = sc.nextDouble();

                    menuService.addItem(
                            new Food(id,name,price)
                    );

                    break;

                case 3:

                    System.out.print("Nhập ID món: ");
                    id = sc.next();

                    System.out.print("Giá mới: ");
                    price = sc.nextDouble();

                    menuService.updatePrice(id,price);

                    break;

                case 4:

                    System.out.print("Nhập ID món cần xoá: ");
                    id = sc.next();

                    menuService.removeItem(id);

                    break;

                case 5:

                    System.out.print("Nhập tên cần tìm: ");
                    name = sc.next();

                    menuService.searchByName(name)
                            .forEach(System.out::println);

                    break;

                case 6:

                    System.out.print("ID đơn: ");
                    String orderId = sc.next();

                    Order order =
                            orderService.createOrder(orderId);

                    while(true){

                        System.out.print("Nhập ID món (0 để dừng): ");
                        id = sc.next();

                        if(id.equals("0")) break;

                        menuService.findById(id)
                                .ifPresent(item -> {

                                    System.out.print("Số lượng: ");
                                    int qty = sc.nextInt();

                                    order.addItem(item,qty);
                                });
                    }

                    System.out.println("Tổng tiền: "
                            +order.calculateTotal());

                    order.setStatus(OrderStatus.PAID);

                    break;

                case 7:

                    double revenue =
                            statisticsService.totalRevenue(
                                    orderService.getOrders());

                    System.out.println("Doanh thu: "+revenue);

                    break;

                case 0:
                    return;
            }
        }
    }
}