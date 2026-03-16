package Ex7.BTTH;

public class DatabaseRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Đã lưu đơn hàng vào DB SQL.");
    }
}
