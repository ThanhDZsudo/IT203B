package Ex11.BTTH.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost: 3306/hn-ks24-cntt3?createDatabaseIfNotExist=true";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "thanh123";

    // mở kết nối
    public static Connection openConnection() {

        Connection con = null;
        try {
            Class.forName(DRIVER); // Khai báo cho java biết driver

            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }
}
