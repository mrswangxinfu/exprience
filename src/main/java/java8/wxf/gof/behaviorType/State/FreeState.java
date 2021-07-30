package java8.wxf.gof.behaviorType.State;

public class FreeState implements State {
    @Override
    public void handle() {
        System.out.println("退房");
    }
}
