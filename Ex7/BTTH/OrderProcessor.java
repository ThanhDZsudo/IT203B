package Ex7.BTTH;

public class OrderProcessor {
    private final OrderRepository repository;
    private final NotificationService notificationService;

    // Dependency Injection: Truyền interface vào qua Constructor
    public OrderProcessor(OrderRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    // Nhận Strategy thanh toán từ bên ngoài truyền vào
    public void processOrder(Order order, PaymentStrategy paymentMethod) {
        // 1. Lưu trữ
        repository.save(order);

        // 2. Xử lý thanh toán đa hình
        paymentMethod.pay();

        // 3. Gửi thông báo
        notificationService.send(order.getCustomerEmail(), "Đơn hàng của bạn đã được xử lý thành công!");
    }
}
