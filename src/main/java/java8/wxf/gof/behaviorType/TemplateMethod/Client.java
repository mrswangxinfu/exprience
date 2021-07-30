package java8.wxf.gof.behaviorType.TemplateMethod;

public class Client {
    public static void main(String[] args) {
        // 子类实现
        DrawMoney drawMoney = new DrawMoney();
        drawMoney.process();
        // 匿名内部类实现
        new BankTemplateMethod(){
            @Override
            void transaction() {
                System.out.println("存钱");
            }
        }.process();
    }
}
