package Ex1.Bai3;

// 1. LỚP USER

// 2. CHƯƠNG TRÌNH CHÍNH
public class Main {
    public static void main(String[] args) {
        User user = new User();

        try {
            // Trường hợp 1: Thiết lập tuổi hợp lệ
            System.out.println("--- Kịch bản 1: Nhập tuổi hợp lệ ---");
            user.setAge(20);
            System.out.println("Thiết lập thành công! Tuổi hiện tại: " + user.getAge());

            // Trường hợp 2: Thiết lập tuổi không hợp lệ (âm)
            System.out.println("\n--- Kịch bản 2: Nhập tuổi âm ---");
            user.setAge(-5);

            // Dòng code bên dưới sẽ KHÔNG bao giờ được chạy tới
            // vì ngoại lệ đã bị ném ra ở dòng trên và nhảy thẳng vào khối catch
            System.out.println("Lưu thông tin thành công!");

        } catch (IllegalArgumentException e) {
            // Bắt và xử lý ngoại lệ do chính chúng ta ném ra từ hàm setAge
            System.out.println("Lỗi nghiệp vụ bị từ chối: " + e.getMessage());
        }
    }
}
