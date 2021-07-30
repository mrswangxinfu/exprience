package java8.wxf.gof.constructType.FlyWeight;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用同一个对象进行修改位置然后传值至前端进行显示即可
 */
public class ChessFactory {
    private static Map<String, Chess> map = new HashMap<>();

    public static Chess getChess(String color) {
        Chess chess = map.get(color);
        if (chess != null) {
            return chess;
        }
        chess = new Chess(color);
        map.put(color, chess);
        return chess;
    }
}
