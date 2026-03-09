package Ex1.Bai2;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nhập tổng số người dùng: ");
            int totalUsers = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập số lượng nhóm muốn chia: ");
            int numGroups = Integer.parseInt(sc.nextLine());

            int usersPerGroup = totalUsers / numGroups;

            System.out.println("Chia nhóm thành công! Mỗi nhóm có " + usersPerGroup + " người.");

        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0!");

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng chỉ nhập số nguyên hợp lệ!");

        } finally {
                sc.close();
                System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
        System.out.println("Chương trình vẫn tiếp tục thực thi các luồng lệnh phía sau một cách an toàn.");
    }
}