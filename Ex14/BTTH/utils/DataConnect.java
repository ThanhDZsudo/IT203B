package Ex14.BTTH.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/flashsaledb";
    private final static String USER = "root";
    private final static String PASSWORD = "thanh123";

    private static Connection con;

    private DataConnect() {}

    public static Connection openConnect() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("lỗi kết nối database", e);
        }
        return con;
    }
}