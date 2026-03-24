package Ex14.BTTH.dao;

import Ex14.BTTH.utils.DataConnect;
import java.sql.*;

public class OrderDAO {

    // xử lý đơn hàng flash sale
    public void placeOrder(int userId, int productId, int quantity, double price, String threadName) {
        Connection con = DataConnect.openConnect();
        try {
            con.setAutoCommit(false); // bật transaction

            // 1. khóa dòng dữ liệu (chống bán lố)
            String sqlCheck = "select stock from products where product_id = ? for update";
            PreparedStatement psCheck = con.prepareStatement(sqlCheck);
            psCheck.setInt(1, productId);
            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                int currentStock = rs.getInt("stock");
                if (currentStock >= quantity) {
                    // 2. trừ kho
                    String sqlUpdate = "update products set stock = stock - ? where product_id = ?";
                    PreparedStatement psUpdate = con.prepareStatement(sqlUpdate);
                    psUpdate.setInt(1, quantity);
                    psUpdate.setInt(2, productId);
                    psUpdate.executeUpdate();

                    // 3. lập hóa đơn
                    String sqlOrder = "insert into orders(user_id, total_amount) values(?, ?)";
                    PreparedStatement psOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
                    psOrder.setInt(1, userId);
                    psOrder.setDouble(2, price * quantity);
                    psOrder.executeUpdate();

                    ResultSet rsKey = psOrder.getGeneratedKeys();
                    int newOrderId = 0;
                    if (rsKey.next()) newOrderId = rsKey.getInt(1);

                    // 4. lưu chi tiết (dùng batch)
                    String sqlDetail = "insert into order_details(order_id, product_id, quantity, unit_price) values(?, ?, ?, ?)";
                    PreparedStatement psDetail = con.prepareStatement(sqlDetail);
                    psDetail.setInt(1, newOrderId);
                    psDetail.setInt(2, productId);
                    psDetail.setInt(3, quantity);
                    psDetail.setDouble(4, price);
                    psDetail.addBatch();
                    psDetail.executeBatch();

                    con.commit(); // chốt dữ liệu
                    System.out.println(threadName + " -> MUA THÀNH CÔNG! (Kho còn: " + (currentStock - quantity) + ")");
                } else {
                    con.rollback(); // hết hàng -> hủy
                    System.out.println(threadName + " -> THẤT BẠI: Hết hàng!");
                }
            }
        } catch (SQLException e) {
            try {
                con.rollback(); // lỗi -> hủy
                System.out.println(threadName + " -> THẤT BẠI: Lỗi giao dịch!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // báo cáo qua callable statement
    public void printTopBuyers() {
        System.out.println("\n=== TOP 5 KHÁCH HÀNG MUA NHIỀU NHẤT ===");
        try {
            Connection con = DataConnect.openConnect();
            String sql = "{call sp_get_top_buyers()}";
            CallableStatement cs = con.prepareCall(sql);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                System.out.println("- " + rs.getString("username") + " : " + (long)rs.getDouble("total_spent") + " vnđ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}