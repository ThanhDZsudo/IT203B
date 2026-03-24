package Ex14.BTTH.main;

import Ex14.BTTH.dao.OrderDAO;
import Ex14.BTTH.utils.DataConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.CountDownLatch;

public class FlashSaleSimulation {
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        int totalUsers = 50;
        CountDownLatch latch = new CountDownLatch(1);

        System.out.println(">>> 50 KHÁCH HÀNG ĐANG LAO VÀO TRANH 10 CHIẾC IPHONE...\n");

        for (int i = 1; i <= totalUsers; i++) {
            String threadName = "khách hàng " + i;
            // mỗi khách hàng là 1 thread, giả sử đều mua iphone (id=1), số lượng 1, giá 25tr
            new Thread(() -> {
                try {
                    latch.await();
                    dao.placeOrder(1, 1, 1, 25000000.00, threadName);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // hô "bắt đầu" cho 50 luồng chạy
        latch.countDown();

        try {
            Thread.sleep(3000); // chờ 3 giây cho các giao dịch hoàn tất
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // kiểm tra kho và in báo cáo
        checkFinalStock();
        dao.printTopBuyers();
    }

    private static void checkFinalStock() {
        try {
            Connection con = DataConnect.openConnect();
            PreparedStatement ps = con.prepareStatement("select stock from products where product_id = 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("\n>>> TỔNG KẾT: SỐ LƯỢNG IPHONE TRONG KHO CÒN LẠI LÀ: " + rs.getInt("stock"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}