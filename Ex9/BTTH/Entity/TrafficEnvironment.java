package Ex9.BTTH.Entity;

import Ex9.BTTH.Pattern.VehicleFactory;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TrafficEnvironment {
    private BlockingQueue<Vehicle> vehicles = new LinkedBlockingQueue<>();

    public void generateVehicles() {
        for (int i = 1; i <= 15; i++) {
            String type = (Math.random() < 0.2) ? "Priority" : "Standard";
            Vehicle v = VehicleFactory.createVehicle(type, "VEH-" + i);
            addVehicle(v);
        }
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Queue<Vehicle> getVehicles() {
        return vehicles;
    }
}