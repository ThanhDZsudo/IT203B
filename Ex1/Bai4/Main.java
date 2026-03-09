package Ex1.Bai4;

import java.io.IOException;

public class Main {
    public static void saveToFile(String data) throws IOException {
        System.out.println("Method C: Đang kết nối tới ổ đĩa để ghi file...");

        throw new IOException("Lỗi phần cứng: Không thể truy cập ổ đĩa (Disk read/write error).");
    }

    public static void processUserData(String data) throws IOException {
        System.out.println("Method B: Chuẩn bị gọi hàm lưu dữ liệu...");
        saveToFile(data);
        System.out.println("Method B: Dữ liệu đã được lưu thành công!");
    }

    public static void main(String[] args) {
        System.out.println("=== BẮT ĐẦU CHƯƠNG TRÌNH ===");
        System.out.println("Method A: Gọi hàm xử lý dữ liệu người dùng...\n");
        try {
            processUserData("Thông tin người dùng mới");

        } catch (IOException e) {
            System.out.println("\n[BÁO ĐỘNG TẠI METHOD A] Bắt được lỗi từ hệ thống cấp dưới:");
            System.out.println("Chi tiết lỗi: " + e.getMessage());
            System.out.println("-> Hệ thống ghi nhận sự cố, cảnh báo cho người dùng nhưng không sập (crash) ứng dụng.");
        }
        System.out.println("\n=== CHƯƠNG TRÌNH KẾT THÚC AN TOÀN ===");
    }
}
