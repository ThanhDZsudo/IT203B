package Ex9.BTTH.Pattern;
import Ex9.BTTH.Entity.TrafficLight;

public class GreenState implements TrafficLightState {
    @Override
    public void handle(TrafficLight light) {
        try { Thread.sleep(4000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        light.changeState(new YellowState());
    }
    public String getColor() { return "GREEN"; }
}