package java8.wxf.test;

/**
 * 串池
 */
public class StringTable {
    public static void main(String[] args) {
        String say="hello";
        String w="world";
        String sayH="helloworld";
        String sayH2="hello"+"world";
        String sw=say+w;
        System.out.println(say);
        for(int i=0;i<100000;i++){
            new Thread(()->{
                while (true){

                }
            }).start();

        }

    }
}
