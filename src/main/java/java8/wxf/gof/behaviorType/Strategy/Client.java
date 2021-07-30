package java8.wxf.gof.behaviorType.Strategy;

public class Client {
    public static void main(String[] args) {
        Strategy newCustomer = new NewCustomerStrategy();
        Strategy oldCustomer = new OldCustomerStrategy();
        Strategy VIP = new VIPStrategy();
        Strategy action = new ActionStrategy();

        Context context = new Context(action);
        context.printPrice(123d);
    }
}
