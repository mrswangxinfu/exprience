package java8.wxf.gof.behaviorType.TemplateMethod;

public class DrawMoney extends BankTemplateMethod {
    @Override
    void transaction() {
        System.out.println("取钱");
    }
}
