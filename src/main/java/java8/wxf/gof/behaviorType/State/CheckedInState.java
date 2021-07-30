package java8.wxf.gof.behaviorType.State;

public class CheckedInState implements State {
    @Override
    public void handle() {
        System.out.println("入住房间");
    }
}
