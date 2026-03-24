package Ex12.Bai1;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== HỆ THỐNG ĐĂNG NHẬP BÁC SĨ ==========");
            System.out.println("1. Đăng nhập hệ thống");
            System.out.println("2. Thoát");
            System.out.print("chọn chức năng: ");

            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 2) {
                System.out.println("tạm biệt!");
                break;
            }

            if (choice == 1) {
                try (Connection con = DataConnect.openConnect()) {
                    String sql = "select full_name from doctors where doctor_code = ? and password = ?";

                    PreparedStatement prepare = con.prepareStatement(sql);

                    System.out.print("nhập mã bác sĩ: ");
                    String code = sc.nextLine();

                    System.out.print("nhập mật khẩu: ");
                    String pass = sc.nextLine();

                    prepare.setString(1, code);
                    prepare.setString(2, pass);

                    ResultSet rs = prepare.executeQuery();

                    if (rs.next()) {
                        String name = rs.getString("full_name");

                        System.out.println("------------------------------------------");
                        System.out.println("đăng nhập thành công!");
                        System.out.println("chào mừng bác sĩ: " + name);
                        System.out.println("------------------------------------------");
                    } else {
                        System.out.println("------------------------------------------");
                        System.out.println("lỗi: sai mã số hoặc mật khẩu!");
                        System.out.println("------------------------------------------");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("lựa chọn không hợp lệ!");
            }
        }
        sc.close();
    }
}
