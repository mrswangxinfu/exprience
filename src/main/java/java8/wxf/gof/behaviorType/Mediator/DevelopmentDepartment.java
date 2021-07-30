package java8.wxf.gof.behaviorType.Mediator;

public class DevelopmentDepartment implements Colleague {
    private Mediator mediator;
    DevelopmentDepartment(Mediator mediator) {
        this.mediator = mediator;
        mediator.registry("开发部",this);
    }
    @Override
    public void selfAction() {
        System.out.println("开发部开发");
    }

    @Override
    public void outAction() {
        System.out.println("开发部缺钱");
        mediator.comment("财务部");
    }
}
