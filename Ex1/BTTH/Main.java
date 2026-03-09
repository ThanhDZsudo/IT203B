package Ex1.BTTH;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void registerUser(String name, String ageInput, String email) throws InvalidAgeException, InvalidEmailException {
        int age;

        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Tuổi phải là một con số!");
        }if (age < 18) {
            throw new InvalidAgeException("Lỗi nghiệp vụ: Tuổi không đủ để đăng ký hệ thống.");
        }if (!email.contains("@.com")) {
            throw new InvalidEmailException("Lỗi nghiệp vụ: Email không hợp lệ (thiếu ký tự @).");
        }
        System.out.println("=> Đăng ký thành công thông tin cho: " + name);
    }

    public static void saveUserToFile(String userData) throws FileNotFoundException {
        System.out.println("Đang tiến hành kết nối và lưu thông tin vào file...");
        throw new FileNotFoundException("Không tìm thấy file lưu trữ.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== HỆ THỐNG ĐĂNG KÝ NGƯỜI DÙNG ===");
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        String ageInput = scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("-----------------------------------");

        try {
            registerUser(name, ageInput, email);
            saveUserToFile(name + ", " + ageInput + ", " + email);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi nhập liệu: " + e.getMessage());
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("-----------------------------------");
            System.out.println("Hoàn tất luồng xử lý đăng ký.");
        }
    }
}
