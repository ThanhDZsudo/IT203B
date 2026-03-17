package Ex9.BTTH.Pattern;
import Ex9.BTTH.Entity.TrafficLight;

public class YellowState implements TrafficLightState {
    @Override
    public void handle(TrafficLight light) {
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        light.changeState(new RedState());
    }
    public String getColor() { return "YELLOW"; }
}