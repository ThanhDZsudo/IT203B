package Ex9.BTTH.Entity;

import Ex9.BTTH.Pattern.Observer;
import Ex9.BTTH.Pattern.TrafficLightState;
import Ex9.BTTH.Util.Logger;

// THÊM: implements Comparable<Vehicle> để có thể sắp xếp theo mức độ ưu tiên
public abstract class Vehicle implements Observer, Runnable, Comparable<Vehicle> {
    protected String id;
    protected double speed;
    protected int priority;

    protected TrafficLightState currentState;
    protected Intersection intersection;
    protected TrafficMonitor monitor;

    public Vehicle(String id, double speed, int priority) {
        this.id = id;
        this.speed = speed;
        this.priority = priority;
    }

    public void setEnvironment(Intersection intersection, TrafficMonitor monitor) {
        this.intersection = intersection;
        this.monitor = monitor;
    }

    public void move() {
        Logger.log("-> " + id + " đang di chuyển vào ngã tư. (Tốc độ: " + speed + ", Ưu tiên: " + priority + ")");
    }

    public void stop() {
        Logger.log("   [Dừng] " + id + " đang chờ đèn đỏ.");
    }

    @Override
    public synchronized void update(TrafficLightState state) {
        this.currentState = state;
        notifyAll();
    }

    // --- HÀM MỚI: Quyết định xe nào được xếp lên đầu hàng đợi ---
    @Override
    public int compareTo(Vehicle other) {
        // Sắp xếp giảm dần: Xe nào có priority (điểm ưu tiên) cao hơn sẽ được đẩy lên trước
        return Integer.compare(other.priority, this.priority);
    }

    public abstract void obeyTrafficRules() throws InterruptedException;

    @Override
    public void run() {
        try {
            obeyTrafficRules();
            move();

            intersection.enter(this);
            // Xe tốc độ 2.0 sẽ chỉ mất 500ms, xe tốc độ 1.0 mất 1000ms
            Thread.sleep((long) (1000 / speed));
            intersection.exit(this);

            monitor.countVehicles();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}