package Ex13.Bai5;

import java.sql.*;

public class PatientController {

    public void admitPatient(String name, int age, int bedId, double amount) {
        Connection conn = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Check giường
            String checkBed = "SELECT status FROM Beds WHERE id = ?";
            PreparedStatement psCheck = conn.prepareStatement(checkBed);
            psCheck.setInt(1, bedId);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                throw new Exception("Giường không tồn tại!");
            }

            if (!rs.getString("status").equals("EMPTY")) {
                throw new Exception("Giường đã có người!");
            }

            // 2. Insert bệnh nhân
            String insertPatient =
                    "INSERT INTO Patients(name, age, bed_id) VALUES (?, ?, ?)";
            PreparedStatement ps1 = conn.prepareStatement(insertPatient, Statement.RETURN_GENERATED_KEYS);
            ps1.setString(1, name);
            ps1.setInt(2, age);
            ps1.setInt(3, bedId);

            int row1 = ps1.executeUpdate();

            if (row1 == 0) throw new Exception("Insert bệnh nhân thất bại");

            ResultSet keys = ps1.getGeneratedKeys();
            keys.next();
            int patientId = keys.getInt(1);

            // 3. Update giường
            String updateBed = "UPDATE Beds SET status = 'OCCUPIED' WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(updateBed);
            ps2.setInt(1, bedId);

            int row2 = ps2.executeUpdate();

            if (row2 == 0) throw new Exception("Update giường thất bại");

            // 4. Insert tài chính
            String insertFinance =
                    "INSERT INTO Finance(patient_id, amount, created_at) VALUES (?, ?, NOW())";
            PreparedStatement ps3 = conn.prepareStatement(insertFinance);
            ps3.setInt(1, patientId);
            ps3.setDouble(2, amount);

            int row3 = ps3.executeUpdate();

            if (row3 == 0) throw new Exception("Insert tài chính thất bại");

            // COMMIT
            conn.commit();
            System.out.println("Tiếp nhận thành công!");

        } catch (Exception e) {

            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Lỗi: " + e.getMessage());

        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}