package Ex7.BTTH;

public class EmailNotification implements NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("Đã gửi Email tới: " + recipient + " | Nội dung: " + message);
    }
}