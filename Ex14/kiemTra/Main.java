package Ex14.kiemTra;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String senderId = "acc01";
        String receiverId = "acc02";
        double transferAmount = 1500.00;

        try (Connection conn = DBConnection.getConnection()) {

            conn.setAutoCommit(false);
            System.out.println(">>> đã bắt đầu transaction...");

            String checkSql = "select balance from accounts where accountid = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setString(1, senderId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        double currentBalance = (long)rs.getDouble("balance");
                        if (currentBalance < transferAmount) {
                            System.out.println("lỗi: tài khoản người gửi không đủ số dư.");
                            conn.rollback();
                            return;
                        }
                    } else {
                        System.out.println("lỗi: không tìm thấy tài khoản người gửi.");
                        conn.rollback();
                        return;
                    }
                }
            }

            String callSql = "{call sp_updatebalance(?, ?)}";
            try (CallableStatement callStmt = conn.prepareCall(callSql)) {
                callStmt.setString(1, senderId);
                callStmt.setDouble(2, -transferAmount);
                callStmt.executeUpdate();
                System.out.println("- đã trừ " + transferAmount + " từ tài khoản " + senderId);

                callStmt.setString(1, receiverId);
                callStmt.setDouble(2, transferAmount);
                callStmt.executeUpdate();
                System.out.println("- đã cộng " + transferAmount + " vào tài khoản " + receiverId);
            }

            conn.commit();
            System.out.println("chuyển khoản thành công!\n");

            String resultSql = "select accountid, fullname, balance from accounts where accountid in (?, ?)";
            try (PreparedStatement resultStmt = conn.prepareStatement(resultSql)) {
                resultStmt.setString(1, senderId);
                resultStmt.setString(2, receiverId);
                try (ResultSet rsFinal = resultStmt.executeQuery()) {
                    System.out.println("--- KẾT QUẢ CUỐI CÙNG ---");
                    System.out.printf("%-15s | %-20s | %-15s\n", "mã tài khoản", "tên khách hàng", "số dư mới");
                    System.out.println("-------------------------------------------------------");
                    while (rsFinal.next()) {
                        System.out.printf("%-15s | %-20s | %-15.2f\n",
                                rsFinal.getString("accountid"),
                                rsFinal.getString("fullname"),
                                rsFinal.getDouble("balance"));
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("có lỗi xảy ra! đang thực hiện rollback...");
            e.printStackTrace();
        }
    }
}