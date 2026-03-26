package Ex13.Bai3;

import java.sql.*;

public class Main {
    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;
        try {
            conn = DatabaseManager.getConnection();
            conn.setAutoCommit(false);
            String sqlGetBalance = "SELECT balance FROM Patient_Wallet WHERE patient_id = ?";
            PreparedStatement psGet = conn.prepareStatement(sqlGetBalance);
            psGet.setInt(1, maBenhNhan);
            ResultSet rs = psGet.executeQuery();
            if (!rs.next()) {
                throw new Exception("Bệnh nhân không tồn tại!");
            }
            double balance = rs.getDouble("balance");
            if (balance < tienVienPhi) {
                throw new Exception("Không đủ tiền trong ví!");
            }
            String sqlDeduct =
                    "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sqlDeduct);
            ps1.setDouble(1, tienVienPhi);
            ps1.setInt(2, maBenhNhan);
            int row1 = ps1.executeUpdate();
            String sqlPatient =
                    "UPDATE Patients SET status = 'DISCHARGED' WHERE patient_id = ?";
            PreparedStatement ps3 = conn.prepareStatement(sqlPatient);
            ps3.setInt(1, maBenhNhan);
            int row3 = ps3.executeUpdate();
            if (row1 == 0 || row2 == 0 || row3 == 0) {
                throw new Exception("Cập nhật thất bại (Row affected = 0)");
            }
            conn.commit();
            System.out.println("Xuất viện và thanh toán thành công!");
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            System.out.println("Lỗi: " + e.getMessage());

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
