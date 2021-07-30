package java8.wxf.gof.constructType.FlyWeight;

public class Chess implements ChessWeight {

    private String color;
    public Chess(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void display(Position position) {
        System.out.println("颜色：" + color + " 位置：X " + position.getX() + " Y " + position.getY());
    }
}
