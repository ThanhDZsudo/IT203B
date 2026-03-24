package Ex12.Bai4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> listResults = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            listResults.add("kết quả xét nghiệm số " + i);
        }

        long startTime = System.currentTimeMillis();

        try (Connection con = DataConnect.openConnect()) {
            String sql = "insert into results(data) values(?)";
            PreparedStatement prepare = con.prepareStatement(sql);

            for (String result : listResults) {
                prepare.setString(1, result);
                prepare.executeUpdate();
            }

            long endTime = System.currentTimeMillis();
            System.out.println("đã nạp xong 1000 kết quả!");
            System.out.println("thời gian thực hiện: " + (endTime - startTime) + " ms");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
