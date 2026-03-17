package Ex9.BTTH.Entity;

import Ex9.BTTH.Pattern.VehicleFactory;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class TrafficEnvironment {

    // --- SỬA ĐỔI QUAN TRỌNG: Dùng PriorityBlockingQueue thay vì LinkedBlockingQueue ---
    // Hàng đợi này sẽ tự động gọi hàm compareTo() ở class Vehicle để xếp xe cứu thương lên đầu
    private BlockingQueue<Vehicle> vehicles = new PriorityBlockingQueue<>();

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