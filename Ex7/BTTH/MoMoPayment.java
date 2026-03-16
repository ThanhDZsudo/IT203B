package Ex7.BTTH;

public class MoMoPayment implements PaymentStrategy {
    @Override
    public void pay() {
        System.out.println("Đang quét mã QR... Thanh toán MoMo thành công.");
    }
}
