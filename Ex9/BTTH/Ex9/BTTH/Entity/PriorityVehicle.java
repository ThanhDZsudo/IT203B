package Ex9.BTTH.Entity;

public class PriorityVehicle extends Vehicle {
    public PriorityVehicle(String id, double speed, int priority) { super(id, speed, priority); }

    public void overrideTraffic() {
        // Xe ưu tiên không quan tâm đèn đỏ, không gọi wait()
    }

    @Override
    public void obeyTrafficRules() {
        overrideTraffic();
    }
}