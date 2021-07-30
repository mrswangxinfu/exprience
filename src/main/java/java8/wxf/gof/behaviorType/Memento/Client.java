package java8.wxf.gof.behaviorType.Memento;

public class Client {
    public static void main(String[] args) {
        CareTaker careTaker = new CareTaker();
        Originator originator = new Originator("张三",15000d,24);
        System.out.println(originator.getEmployee());

        // 保存修改
        careTaker.addMemento(originator.memento());
        originator.getEmployee().setAge(23);
        originator.getEmployee().setSalary(20000d);
        System.out.println(originator.getEmployee());
        // 保存修改
        careTaker.addMemento(originator.memento());
        originator.getEmployee().setAge(18);
        System.out.println(originator.getEmployee());
        // 恢复修改
        originator.recover(careTaker.getMemento(0));
        System.out.println(originator.getEmployee());
    }
}
