package java8.wxf.gof.behaviorType.Mediator;

public class FinancialDepartment implements Colleague {
    private Mediator mediator;
    FinancialDepartment(Mediator mediator) {
        this.mediator = mediator;
        mediator.registry("财务部",this);
    }
    @Override
    public void selfAction() {
        System.out.println("财务部数钱");
    }

    @Override
    public void outAction() {
        System.out.println("财务部钱用不完");
        mediator.comment("市场部");
    }
}
