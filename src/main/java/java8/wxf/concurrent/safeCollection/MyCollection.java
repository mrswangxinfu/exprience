package java8.wxf.concurrent.safeCollection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 使用Collections产生同步集合
 */
public class MyCollection {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

    }
}
