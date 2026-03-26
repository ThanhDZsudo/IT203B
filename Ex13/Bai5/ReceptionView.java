package Ex13.Bai5;

import java.util.Scanner;

public class ReceptionView {
    private static final Scanner sc = new Scanner(System.in);
    private static final PatientController controller = new PatientController();

    public static void showMenu() {
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Tiếp nhận bệnh nhân");
            System.out.println("2. Thoát");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleAdmit();
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private static void handleAdmit() {
        try {
            System.out.print("Tên: ");
            String name = sc.nextLine();

            System.out.print("Tuổi: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Mã giường: ");
            int bedId = Integer.parseInt(sc.nextLine());

            System.out.print("Tiền tạm ứng: ");
            double amount = Double.parseDouble(sc.nextLine());

            if (amount <= 0) {
                System.out.println("Tiền phải > 0");
                return;
            }

            controller.admitPatient(name, age, bedId, amount);

        } catch (NumberFormatException e) {
            System.out.println("Nhập sai định dạng số!");
        }
    }
}
