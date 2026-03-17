package Ex9.BTTH.Engine;

import Ex9.BTTH.Entity.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private ExecutorService executor;
    private TrafficEnvironment environment;
    private Intersection intersection;
    private TrafficLight trafficLight;
    private TrafficMonitor monitor;

    public SimulationEngine() {
        executor = Executors.newFixedThreadPool(10);
        environment = new TrafficEnvironment();
        intersection = new Intersection();
        trafficLight = new TrafficLight();
        monitor = new TrafficMonitor();
    }

    public void startSimulation() {
        System.out.println("=== KHỞI ĐỘNG HỆ THỐNG MÔ PHỎNG ===");

        Thread lightThread = new Thread(trafficLight);
        lightThread.setDaemon(true);
        lightThread.start();

        environment.generateVehicles();

        updateVehicles();

        shutdownSimulation();
    }

    public void updateVehicles() {
        while (!environment.getVehicles().isEmpty()) {
            Vehicle v = environment.getVehicles().poll();

            trafficLight.addObserver(v);

            // --- DÒNG THÊM MỚI: Báo cho xe biết màu đèn ngay lúc vừa xuất hiện ---
            v.update(trafficLight.getState());

            v.setEnvironment(intersection, monitor);

            monitor.detectTrafficJam(environment.getVehicles().size());

            executor.submit(v);

            try { Thread.sleep(200); } catch (InterruptedException e) {}
        }
    }

    private void shutdownSimulation() {
        executor.shutdown();
        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
            monitor.logStatus();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SimulationEngine().startSimulation();
    }
}