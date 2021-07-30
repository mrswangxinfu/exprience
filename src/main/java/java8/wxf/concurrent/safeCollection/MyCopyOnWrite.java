package java8.wxf.concurrent.safeCollection;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 使用写时复制
 */
public class MyCopyOnWrite {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        Set<String> set = new CopyOnWriteArraySet<>();
        Map<String, String> map = new ConcurrentHashMap<>();
    }
}
