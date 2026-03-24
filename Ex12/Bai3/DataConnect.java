package Ex12.Bai3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {
    public static Connection openConnect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/HospitalFeeDB";
        String user = "root";
        String pass = "thanh123";
        return DriverManager.getConnection(url, user, pass);
    }
}
