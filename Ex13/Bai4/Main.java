package Ex13.Bai4;

import java.sql.*;
import java.util.*;

public class Main {
    public List<BenhNhanDTO> getDashboardData() {
        List<BenhNhanDTO> result = new ArrayList<>();
        String sql = """
            SELECT bn.maBenhNhan, bn.tenBenhNhan,
                   dv.maDichVu, dv.tenDichVu
            FROM BenhNhan bn
            LEFT JOIN DichVuSuDung dv
            ON bn.maBenhNhan = dv.maBenhNhan
        """;
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            Map<Integer, BenhNhanDTO> map = new LinkedHashMap<>();
            while (rs.next()) {
                int maBN = rs.getInt("maBenhNhan");
                BenhNhanDTO bn = map.get(maBN);
                if (bn == null) {
                    bn = new BenhNhanDTO();
                    bn.setMaBenhNhan(maBN);
                    bn.setTenBenhNhan(rs.getString("tenBenhNhan"));
                    bn.setDsDichVu(new ArrayList<>());
                    map.put(maBN, bn);
                }
                int maDV = rs.getInt("maDichVu");
                if (!rs.wasNull()) {
                    DichVu dv = new DichVu();
                    dv.setMaDichVu(maDV);
                    dv.setTenDichVu(rs.getString("tenDichVu"));
                    bn.getDsDichVu().add(dv);
                }
            }
            result = new ArrayList<>(map.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
