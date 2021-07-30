package java8.wxf.gof.constructType.Composite;

public class Client {

    public static void main(String[] args) {
        Folder myFolder = new Folder("self");
        myFolder.add(new File("data.txt"));
        myFolder.add(new File("file.java"));
        Folder data = new Folder("data");
        data.add(new File("uu.exe"));
        data.add(new File("xx.exe"));
        data.add(new File("yy.exe"));
        myFolder.add(data);
        myFolder.killVirus();
    }
}
