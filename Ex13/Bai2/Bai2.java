package Ex13.Bai2;

//Khi sảy ra lỗi, bát buộc phải đưa database về trạng thái ban đầu
//Hành động bị bỏ quên: rollback

//public void thanhToanVienPhi(int patientId, int invoiceId, double amount) {
//    Connection conn = null;
//    try {
//        conn = DatabaseManager.getConnection();
//        conn.setAutoCommit(false);
//        String sqlDeductWallet =
//                "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
//        PreparedStatement ps1 = conn.prepareStatement(sqlDeductWallet);
//        ps1.setDouble(1, amount);
//        ps1.setInt(2, patientId);
//        ps1.executeUpdate();
//        String sqlUpdateInvoice =
//                "UPDATE Invoicesss SET status = 'PAID' WHERE invoice_id = ?";
//        PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice);
//        ps2.setInt(1, invoiceId);
//        ps2.executeUpdate();
//        conn.commit();
//        System.out.println("Thanh toán hoàn tất!");
//    } catch (SQLException e) {
//        if (conn != null) {
//            try {
//                conn.rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        }
//        System.out.println("Lỗi hệ thống: Không thể hoàn tất thanh toán. Chi tiết: " + e.getMessage());
//    } finally {
//        try {
//            if (conn != null) {
//                conn.setAutoCommit(true);
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
