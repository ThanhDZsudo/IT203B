package Ex12.Bai2;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = DataConnect.openConnect()) {
            String sql = "update vitals set temperature = ?, heart_rate = ? where p_id = ?";
            PreparedStatement prepare = con.prepareStatement(sql);

            System.out.print("Nhập id bệnh nhân: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập nhiệt độ mới: ");
            double temp = Double.parseDouble(sc.nextLine());

            System.out.print("Nhập nhịp tim mới: ");
            int heart = Integer.parseInt(sc.nextLine());

            prepare.setDouble(1, temp);
            prepare.setInt(2, heart);
            prepare.setInt(3, id);

            int row = prepare.executeUpdate();

            if (row > 0) {
                System.out.println("Đã cập nhật thành công cho bệnh nhân: " + id);

                String sqlCheck = "select * from vitals where p_id = ?";
                PreparedStatement prepareCheck = con.prepareStatement(sqlCheck);
                prepareCheck.setInt(1, id);
                ResultSet rs = prepareCheck.executeQuery();

                if (rs.next()) {
                    System.out.println("Tên: " + rs.getString("patient_name"));
                    System.out.println("Nhiệt độ mới: " + rs.getDouble("temperature") + " độ c");
                    System.out.println("Nhịp tim mới: " + rs.getInt("heart_rate") + " bpm");
                }
            } else {
                System.out.println("không tìm thấy bệnh nhân " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
