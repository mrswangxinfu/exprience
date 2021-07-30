package java8.wxf.gof.behaviorType.Strategy;

public class VIPStrategy implements Strategy {
    @Override
    public Double getPrice(Double price) {
        System.out.println("VIP打6折");
        return price*0.6;
    }

    @Override
    public String getName() {
        return "VIP价";
    }
}
