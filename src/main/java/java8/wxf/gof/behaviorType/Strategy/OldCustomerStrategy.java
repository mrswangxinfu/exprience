package java8.wxf.gof.behaviorType.Strategy;

public class OldCustomerStrategy implements Strategy {
    @Override
    public Double getPrice(Double price) {
        System.out.println("老顾客打9折");
        return price*0.9;
    }

    @Override
    public String getName() {
        return "老顾客价";
    }
}
