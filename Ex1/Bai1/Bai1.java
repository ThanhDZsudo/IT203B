package Ex1.Bai1;

import java.util.Scanner;
import java.time.Year;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập năm sinh của bạn: ");
        String input = sc.nextLine();

        try {
            int birthYear = Integer.parseInt(input);

            int currentYear = Year.now().getValue();
            int age = currentYear - birthYear;

            System.out.println("Đăng ký thành công! Tuổi của bạn hiện tại là: " + age);

        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng chỉ nhập năm sinh bằng các chữ số!");

        } finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}