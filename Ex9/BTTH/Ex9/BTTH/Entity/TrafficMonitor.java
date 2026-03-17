package Ex9.BTTH.Entity;

import Ex9.BTTH.Util.Logger;

public class TrafficMonitor {
    private int passedVehicles = 0;
    private int trafficJamCount = 0;

    // Synchronized để tránh data race khi nhiều xe cùng đếm
    public synchronized void countVehicles() {
        passedVehicles++;
    }

    public void detectTrafficJam(int queueSize) {
        if (queueSize > 10) {
            trafficJamCount++;
            Logger.log("[CẢNH BÁO] Dừng đèn đỏ! Hàng chờ đang là: " + queueSize);
        }
    }

    public void logStatus() {
        Logger.log("=== BÁO CÁO GIÁM SÁT ===");
        Logger.log("Tổng xe qua ngã tư: " + passedVehicles);
        Logger.log("Số lần dừng đèn đỏ: " + trafficJamCount);
    }
}