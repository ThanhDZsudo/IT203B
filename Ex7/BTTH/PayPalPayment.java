package Ex7.BTTH;

public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Đang mở cổng PayPal... Thanh toán PayPal thành công.");
    }
}
