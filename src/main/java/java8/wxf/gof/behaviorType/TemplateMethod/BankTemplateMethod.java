package java8.wxf.gof.behaviorType.TemplateMethod;

public abstract class BankTemplateMethod {
    private void takeNumber() {
        System.out.println("取号");
    }
    private void waitInline() {
        System.out.println();
        System.out.println("排队");
    }
    protected void evaluate() {
        System.out.println("评分");
    }
    // 业务
    abstract void transaction();
    // 模板方法
    public void process() {
        waitInline();
        takeNumber();
        transaction();
        evaluate();
    }
}
