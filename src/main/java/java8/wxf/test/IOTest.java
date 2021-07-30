package java8.wxf.test;

import java8.wxf.entity.Person;

import java.io.*;

public class IOTest {
    static String x="0";
    Person person=new Person();
    public static void main(String[] args) throws IOException {
//        FileReader fileReader=new FileReader();
//        FileWriter fileWriter=new FileWriter();
//        List<String> result=fileReader.readLines("GB2312");
//        fileWriter.writeLines(result,"UTF-8");
        System.out.println(new IOTest().x==new IOTest().x);
//        System.out.println(new IOTest().person==new IOTest().person);
        System.out.println("-------");
        boolean is=false;
        block:
          for(int i=0;i<2;i++){
              System.out.println("=========");
              if(i==1){
                  System.out.println("1111");
              }
              break block;//跳出block语句块，而不是  跳到block(汇编用法)
          }
        LAB:{
            System.out.println("123");
            is=true;
            if(is){
                break LAB;
            }
            System.out.println("1231112");
        }

    }
    static{
        System.out.println("***********");
    }

}
