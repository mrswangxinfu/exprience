package java8.wxf.gof.behaviorType.Strategy;

public class Context {
    private Strategy strategy;
    Context(Strategy strategy) {
        this.strategy = strategy;
    }
    public void printPrice(Double price) {
        System.out.println(price+strategy.getName()+strategy.getPrice(price));
    }
}
