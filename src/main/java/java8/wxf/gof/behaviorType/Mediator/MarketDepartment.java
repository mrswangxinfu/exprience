package java8.wxf.gof.behaviorType.Mediator;

public class MarketDepartment implements Colleague {
    private Mediator mediator;
    MarketDepartment(Mediator mediator) {
        this.mediator = mediator;
        mediator.registry("市场部",this);
    }
    @Override
    public void selfAction() {
        System.out.println("市场部花钱");
    }

    @Override
    public void outAction() {
        System.out.println("市场部向经理汇报，需要资金");
    }
}
