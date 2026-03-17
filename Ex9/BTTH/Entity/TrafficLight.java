package Ex9.BTTH.Entity;

import Ex9.BTTH.Pattern.Observer;
import Ex9.BTTH.Pattern.RedState;
import Ex9.BTTH.Pattern.Subject;
import Ex9.BTTH.Pattern.TrafficLightState;
import Ex9.BTTH.Util.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrafficLight implements Subject, Runnable {
    private TrafficLightState state;
    private List<Observer> observers = new CopyOnWriteArrayList<>();

    public TrafficLight() {
        this.state = new RedState();
    }

    public void setState(TrafficLightState state) {
        this.state = state;
    }

    // --- HÀM MỚI THÊM VÀO ĐỂ LẤY TRẠNG THÁI ---
    public TrafficLightState getState() {
        return state;
    }

    public void changeState(TrafficLightState newState) {
        setState(newState);
        Logger.log("[ĐÈN GIAO THÔNG] Chuyển sang màu: " + state.getColor());
        notifyVehicles();
    }

    public void notifyVehicles() {
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) { observers.add(o); }

    @Override
    public void removeObserver(Observer o) { observers.remove(o); }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            state.handle(this);
        }
    }
}