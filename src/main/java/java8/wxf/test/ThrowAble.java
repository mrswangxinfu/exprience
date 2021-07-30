package java8.wxf.test;

public class ThrowAble {
    public static void main(String[] args) {
        System.out.println(test());
    }
    public static int test(){
        int i=10;
        try {
            System.out.println("try");
//            if(i==20){
//                System.out.println("===");
//                return ++i;
//            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            i=i+10;
            System.out.println(i);
        }
        return ++i;
    }
}
