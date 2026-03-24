package Ex12.Bai3;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("nhập mã ca phẫu thuật: ");
        int id = Integer.parseInt(sc.nextLine());

        try (Connection con = DataConnect.openConnect()) {
            String sql = "{call get_surgery_fee(?, ?)}";
            CallableStatement cstmt = con.prepareCall(sql);

            cstmt.setInt(1, id);

            cstmt.registerOutParameter(2, Types.DECIMAL);

            cstmt.execute();

            double cost = cstmt.getDouble(2);

            if (cost > 0) {
                System.out.println("mã ca phẫu thuật: " + id);
                System.out.println("tổng chi phí dự kiến: " + (long)cost + " vnđ");
            } else {
                System.out.println("không tìm thấy");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}