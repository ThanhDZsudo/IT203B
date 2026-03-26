package Ex13.BTTH;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- HỆ THỐNG THANH TOÁN & XUẤT VIỆN ---");
        System.out.print("nhập mã bệnh nhân cần xuất viện: ");
        int patientId = Integer.parseInt(sc.nextLine());

        Connection con = null;
        try {
            con = DataConnect.openConnect();

            // BƯỚC QUAN TRỌNG NHẤT: Bắt đầu Transaction
            con.setAutoCommit(false);

            // HÀNH ĐỘNG 1: Lập hóa đơn
            String sql1 = "insert into invoices(p_id, amount) values(?, ?)";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, patientId);
            ps1.setDouble(2, 5000000); // mặc định 5 triệu
            ps1.executeUpdate();
            System.out.println("> đã lập hóa đơn tạm thời...");

            // HÀNH ĐỘNG 2: Cập nhật hồ sơ bệnh nhân
            String sql2 = "update patients set status = 'đã xuất viện' where p_id = ?";
            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.setInt(1, patientId);
            ps2.executeUpdate();
            System.out.println("> đã cập nhật trạng thái bệnh nhân...");

            // HÀNH ĐỘNG 3: Giải phóng giường bệnh (LƯU Ý TEST CASE 2 TẠI ĐÂY)
            // Để test Rollback, bạn hãy sửa 'beds' thành 'bedzzz'
            String sql3 = "update beds set status = 'trống', p_id = null where p_id = ?";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            ps3.setInt(1, patientId);
            ps3.executeUpdate();
            System.out.println("> đã giải phóng giường bệnh...");

            // NẾU CHẠY ĐẾN ĐÂY KHÔNG LỖI -> CHỐT DỮ LIỆU
            con.commit();
            System.out.println("------------------------------------------");
            System.out.println("XUẤT VIỆN THÀNH CÔNG! Dữ liệu đã được lưu.");

        } catch (SQLException e) {
            System.err.println("------------------------------------------");
            System.err.println("LỖI HỆ THỐNG! Đang thực hiện Rollback...");
            try {
                if (con != null) {
                    con.rollback(); // Hủy bỏ toàn bộ 3 bước trên
                    System.err.println("Đã khôi phục dữ liệu về trạng thái an toàn.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
                sc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}