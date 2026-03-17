package Ex9.BTTH.Entity;

import Ex9.BTTH.Pattern.RedState;

public class StandardVehicle extends Vehicle {
    public StandardVehicle(String id, double speed, int priority) { super(id, speed, priority); }

    public void obeyTrafficLight() throws InterruptedException {
        synchronized (this) {
            while (currentState instanceof RedState) {
                stop();
                wait(); // Ngủ chờ đến khi được update() gọi notifyAll()
            }
        }
    }

    @Override
    public void obeyTrafficRules() throws InterruptedException {
        obeyTrafficLight();
    }
}