package Ex7.BTTH;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Đang kết nối API Ngân hàng... Thanh toán Thẻ tín dụng thành công.");
    }
}
