package java8.wxf.test;

import java.util.*;

public class  TestAnything {
    public static void main(String[] args) {

//        Map<String,String> map=new HashMap<>();
//        map.put("12","牛牛");
//        map.put(null,null);
//        map.put("45",null);
//        map.put(null,"78");
//        System.out.println(map);
//        System.out.println(map.get(null));
//        String s=" 1...2. m 4.5 56 ";
//        System.out.println(s.trim());
//        System.out.println(s.replace(" ","="));
//        List<String> list=new ArrayList<>(Arrays.asList(s.split(" ")));
//        for(String str:list){
//            if(str.contains("12")){
//                System.out.println("空格");
//            }
//        }
//        List<String> list=new ArrayList<>();
//        list.add("45");
//        list.add(null);
//        list.add("56");
//        list.add("12");
//        System.out.println(list);
//        System.out.println(Runtime.getRuntime());
//        for(int i=0;i<list.size();i++){
//            list.clear();
//            if(null==list.get(i)){
//                System.out.println(list.get(i));
//            }
//        }

//        for(String s:list){
//            list.clear();
//            System.out.println("45646");
//        }

//
//       Integer[] arr ={112,45,56};
//       int[] arrInt={45,32,12,45};
//       List listInt=Arrays.stream(arrInt).boxed().collect(Collectors.toList());
//        System.out.println(listInt);
//        List listStream=Arrays.stream(arr).collect(Collectors.toList());
//        System.out.println(listStream);
//        List list= Arrays.asList(arr);//返回的是内部类ArrayList,没有实现集合类
//        List listNew=new ArrayList(list);
////        System.out.println(list.get(1));
////        System.out.println(list.add(12));
////        System.out.println(list.remove(0));
//        System.out.println(arrayToList(arr));

//        System.out.println(new ArrayList<String>());
//        String str = " hi world ~ ";
//        //方法1：str.trim()
//        System.out.println("1--->"+str.trim());
//        //方法2：str.repalce(" ","")
//        System.out.println("2--->"+str.replace(" ", ""));
//        //方法3：str.repalceAll(" ","")
//        System.out.println("3--->"+str.replaceAll(" ", ""));
//        //方法4：str.repalceAll(" +","")
//        System.out.println("4--->"+str.replaceAll(" +", ""));
//        //方法5：str.repalceAll("\\s*","")
//        System.out.println("4--->"+str.replaceAll("\\s*", ""));


//        String s=" hello.... ...world ";
//        String b=s+"===";
//        List ne=new ArrayList();
//        ne.add("hello");
//        ne.add("world");
//        List list= new ArrayList(Arrays.asList(s.replace(".","").trim().split(" ")));
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list);
//        System.out.println(ne);
//        System.out.println(b);
    }
    //实现数组转列表
    public static  <T> List<T> arrayToList(T[] arr){
        List<T> list=new ArrayList();
        for(T ar:arr){
            list.add(ar);
        }
        return  list;
    }
}
