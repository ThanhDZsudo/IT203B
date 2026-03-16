package Ex6.BTTH;

// Lớp quản lý hệ thống bán vé, triển khai interface Runnable
class TicketCounter implements Runnable {
    // Shared resource: Tổng số vé chung cho tất cả các quầy
    private int tickets = 10;

    @Override
    public void run() {
        // Vòng lặp liên tục để các quầy bán cho đến khi hết vé
        while (true) {
            /* * ĐỒNG BỘ HÓA (SYNCHRONIZED BLOCK)
             * Khóa đối tượng hiện tại (this). Chỉ 1 luồng được vào đây tại 1 thời điểm.
             */
            synchronized (this) {
                // Kiểm tra xem kho còn vé không
                if (tickets > 0) {
                    try {
                        // Mô phỏng thời gian thao tác trên máy tính (0.5 giây)
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Trừ đi 1 vé sau khi bán thành công
                    tickets--;
                    System.out.println(Thread.currentThread().getName() + " đã bán 1 vé. Số vé còn lại: " + tickets);
                } else {
                    // Nếu vé = 0, thông báo hết vé và thoát khỏi vòng lặp
                    System.out.println(Thread.currentThread().getName() + " thông báo: Đã hết vé!");
                    break;
                }
            } // Hết khối synchronized, nhả "chìa khóa" cho luồng khác

            /* * Mẹo nhỏ: Nghỉ một chút xíu (100ms) ngoài khối synchronized
             * để nhường CPU cho các quầy khác có cơ hội chộp lấy "chìa khóa",
             * giúp việc bán vé luân phiên và thực tế hơn.
             */
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// Lớp Main để chạy chương trình
public class TrainTicketSystem {
    public static void main(String[] args) {
        System.out.println("=== HỆ THỐNG MỞ BÁN VÉ TÀU TẾT ===");

        // Tạo 1 kho vé duy nhất (1 đối tượng Runnable dùng chung)
        TicketCounter sharedCounter = new TicketCounter();

        // Khởi tạo 3 luồng (Thread) đại diện cho 3 quầy, truyền chung 1 kho vé vào
        Thread quay1 = new Thread(sharedCounter, "Quầy 1");
        Thread quay2 = new Thread(sharedCounter, "Quầy 2");
        Thread quay3 = new Thread(sharedCounter, "Quầy 3");

        // Bắt đầu mở bán đồng loạt
        quay1.start();
        quay2.start();
        quay3.start();
    }
}