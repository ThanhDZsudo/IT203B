package Ex7.BTTH;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG XỬ LÝ ĐƠN HÀNG TECHSHOP ===\n");

        OrderRepository db = new DatabaseRepository();
        NotificationService emailSender = new EmailNotification();

        OrderProcessor processor = new OrderProcessor(db, emailSender);

        Order order1 = new Order("khachhang_1@gmail.com");
        Order order2 = new Order("khachhang_2@gmail.com");

        System.out.println("-> Khách hàng 1 đặt hàng:");
        PaymentStrategy creditCard = new CreditCardPayment();
        processor.processOrder(order1, creditCard);

        System.out.println("\n----------------------------------\n");

        System.out.println("-> Khách hàng 2 đặt hàng:");
        PaymentStrategy momo = new MoMoPayment();
        processor.processOrder(order2, momo);
    }
}
