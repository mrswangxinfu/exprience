package java8.wxf.gof.behaviorType.Strategy;

public class ActionStrategy implements Strategy {
    @Override
    public Double getPrice(Double price) {
        System.out.println("做活动打88折");
        return price*0.88;
    }

    @Override
    public String getName() {
        return "活动价";
    }
}
