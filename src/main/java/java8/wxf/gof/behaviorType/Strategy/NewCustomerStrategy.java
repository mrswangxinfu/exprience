package java8.wxf.gof.behaviorType.Strategy;

public class NewCustomerStrategy implements Strategy {
    @Override
    public Double getPrice(Double price) {
        System.out.println("新顾客不打折");
        return price;
    }

    @Override
    public String getName() {
        return "新顾客价";
    }
}
