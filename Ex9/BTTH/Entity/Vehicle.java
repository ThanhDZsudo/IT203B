package Ex9.BTTH.Entity;

import Ex9.BTTH.Pattern.Observer;
import Ex9.BTTH.Pattern.TrafficLightState;
import Ex9.BTTH.Util.Logger;

public abstract class Vehicle implements Observer, Runnable {
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
        Logger.log("-> " + id + " đang di chuyển vào ngã tư.");
    }

    public void stop() {
        Logger.log("   [Dừng] " + id + " đang chờ đèn đỏ.");
    }

    // Nhận thông báo từ Đèn giao thông và ĐÁNH THỨC luồng (nếu đang ngủ)
    @Override
    public synchronized void update(TrafficLightState state) {
        this.currentState = state;
        notifyAll();
    }

    // Các lớp con tự định nghĩa logic tuân thủ luật lệ
    public abstract void obeyTrafficRules() throws InterruptedException;

    @Override
    public void run() {
        try {
            obeyTrafficRules(); // Đợi đèn / Vượt đèn
            move();

            intersection.enter(this);
            Thread.sleep((long) (1000 / speed)); // Băng qua đường
            intersection.exit(this);

            monitor.countVehicles(); // Báo cho Monitor đếm số xe qua
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}