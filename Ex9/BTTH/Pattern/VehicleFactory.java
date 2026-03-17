package Ex9.BTTH.Pattern;

import Ex9.BTTH.Entity.PriorityVehicle;
import Ex9.BTTH.Entity.StandardVehicle;
import Ex9.BTTH.Entity.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String id) {
        if (type.equalsIgnoreCase("Priority")) {
            return new PriorityVehicle(id, 2.0, 1);
        }
        return new StandardVehicle(id, 1.0, 0);
    }
}
