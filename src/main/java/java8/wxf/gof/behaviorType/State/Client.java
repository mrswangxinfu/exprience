package java8.wxf.gof.behaviorType.State;


public class Client {
    public static void main(String[] args) {
        RoomContext context = new RoomContext();

        FreeState freeState = new FreeState();
        CheckedInState checkedInState = new CheckedInState();
        BookedState bookedState = new BookedState();

        context.setState(bookedState);
        context.setState(checkedInState);
        context.setState(freeState);
    }
}
