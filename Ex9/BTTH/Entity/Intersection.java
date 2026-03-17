package Ex9.BTTH.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Intersection {
    private ReentrantLock lock = new ReentrantLock(true);
    // --- THÊM MỚI: Condition quản lý chỗ trống trong ngã tư ---
    private Condition spaceAvailable = lock.newCondition();

    private int capacity = 2; // Sức chứa: Tối đa 2 xe nằm giữa ngã tư cùng lúc
    private List<Vehicle> currentVehicles = new ArrayList<>();

    public void enter(Vehicle vehicle) throws InterruptedException {
        lock.lock();
        try {
            // Dùng while bắt các xe đến sau phải xếp hàng ngủ chờ nếu ngã tư đã chật
            while (currentVehicles.size() >= capacity) {
                spaceAvailable.await();
            }
            currentVehicles.add(vehicle);
        } finally {
            lock.unlock();
        }
    }

    public void exit(Vehicle vehicle) {
        lock.lock();
        try {
            currentVehicles.remove(vehicle);
            // --- THÊM MỚI: Đánh thức các xe đang chờ ở ngoài tràn vào ---
            spaceAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }
}