package Ex11.BTTH.presentation;

import Ex11.BTTH.util.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Chuẩn bị kết nối");

        DatabaseConnection.openConnection();

        System.out.println("Đã kết nối thành công");
    }
}
