package java8.wxf.gof.behaviorType.State;

public class BookedState implements State {
    @Override
    public void handle() {
        System.out.println("预定房间");
    }
}
