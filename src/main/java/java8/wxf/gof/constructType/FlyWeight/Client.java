package java8.wxf.gof.constructType.FlyWeight;

public class Client {
    public static void main(String[] args) {
        Chess chess1 = ChessFactory.getChess("黑色");
        Chess chess2 = ChessFactory.getChess("白色");
        Chess chess = ChessFactory.getChess("黑色");

        System.out.println(chess == chess1);
        chess1.display(new Position(12, 10));
        chess1.display(new Position(5, 6));
        chess2.display(new Position(3, 4));
    }
}
