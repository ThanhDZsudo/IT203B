package Ex1.Bai5;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        System.out.println("=== HỆ THỐNG QUẢN LÝ NGƯỜI DÙNG ===");

        try {
            System.out.println("-> Cập nhật tuổi thành 20...");
            user.setAge(20);
            System.out.println("   Thành công! Tuổi hiện tại: " + user.getAge() + "\n");
            System.out.println("-> Cập nhật tuổi thành -5...");
            user.setAge(-5);
            System.out.println("   Đã lưu vào cơ sở dữ liệu!");

        } catch (InvalidAgeException e) {
            System.out.println("   [TỪ CHỐI] " + e.getMessage());
            System.out.println("\n--- Chi tiết Stack Trace ---");
            e.printStackTrace();
        }
    }
}