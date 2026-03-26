//Nguyên nhân: Auto-Commit mặc định của JDBC = TRUE

//public void capPhatThuc(int medicineId, int patientId) {
//    Connection conn = null;
//    try {
//        conn.setAutoCommit(false);
//        String sqlUpdateInventory = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
//        PreparedStatement ps1 = conn.prepareStatement(sqlUpdateInventory);
//        ps1.setInt(1, medicineId);
//        ps1.executeUpdate();
//        int x = 10/0;
//        String sqlInsertHistory = "INSERT INTO Prescription_History (patient_id, medicine_id, date) VALUES (?, ?, NOW())";
//        PreparedStatement ps2 = conn.prepareStatement(sqlInsertHistory);
//        ps2.setInt(1, patientId);
//        ps2.setInt(2, medicineId);
//        ps2.executeUpdate();
//        conn.commit;
//        System.out.println("Cấp phát thuốc thành công!");
//    }
//     catch (Exception e) {
//            try {
//            if (conn != null) {
//            conn.rollback();
//                }
//                        } catch (Exception ex) {
//            ex.printStackTrace();
//            }
//                    System.out.println("Có lỗi xảy ra: " + e.getMessage());
//            } finally {
//            try {
//            if (conn != null) {
//            conn.setAutoCommit(true);
//                    conn.close();
//                }
//                        } catch (Exception e) {
//            e.printStackTrace();
//            }
//                    }