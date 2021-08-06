package java8.wxf.dataStructure;

import java.text.CollationKey;
import java.text.Collator;
import java.util.*;

public class MyLinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V> {
    public static void main(String[] args) {
        LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<>();

    }

    //HashMap例子：
//    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("a3", "aa");
//        map.put("a2", "bb");
//        map.put("b1", "cc");
//        for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
//            String name = (String) iterator.next();
//            System.out.println(name);
//        }
//    }
    //LinkedHashMap例子：

//    public static void main(String[] args) {
//        Map<String, String> map = new LinkedHashMap<String, String>();
//        map.put("a3", "aa");
//        map.put("a2", "bb");
//        map.put("b1", "cc");
//        for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
//            String name = (String) iterator.next();
//            System.out.println(name);
//        }
//    }
//
//    //TreeMap例子：
//
//    public static void main(String[] args) {
//        Map<String, String> map = new TreeMap<String, String>(new Comparator<Object>(){
//            Collator collator = Collator.getInstance();
//            public int compare(Object o1, Object o2) {
//                CollationKey key1 = collator.getCollationKey(o1.toString());
//                CollationKey key2 = collator.getCollationKey(o2.toString());
//                return key1.compareTo(key2);
//                //return collator.compare(o1, o2);
//            }});
//        map.put("a3", "aa");
//        map.put("a2", "bb");
//        map.put("b1", "cc");
//        for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {
//            String name = (String) iterator.next();
//            System.out.println(name);
//        }
//    }
}
