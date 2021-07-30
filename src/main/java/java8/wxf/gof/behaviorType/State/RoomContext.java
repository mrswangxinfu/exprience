package java8.wxf.gof.behaviorType.State;

public class RoomContext {
    private State state;
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
        state.handle();
    }
}
