package Ex9.BTTH.Pattern;

import Ex9.BTTH.Entity.PriorityVehicle;
import Ex9.BTTH.Entity.StandardVehicle;
import Ex9.BTTH.Entity.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String id) {
        if (type.equalsIgnoreCase("Priority")) {
            // Xe cứu thương: Tốc độ 2.0 (Nhanh gấp đôi), Mức độ ưu tiên: 10 (Cao nhất)
            return new PriorityVehicle(id + " [CẤP CỨU]", 2.0, 10);
        }
        // Xe thường: Tốc độ 1.0 (Bình thường), Mức độ ưu tiên: 1 (Thấp)
        return new StandardVehicle(id + " [Ô TÔ]", 1.0, 1);
    }
}