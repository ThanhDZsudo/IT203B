package Ex9.BTTH.Pattern;
import Ex9.BTTH.Entity.TrafficLight;

public class RedState implements TrafficLightState {
    @Override
    public void handle(TrafficLight light) {
        try { Thread.sleep(3000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        light.changeState(new GreenState());
    }
    public String getColor() { return "RED"; }
}