package java8.wxf.gof.constructType.Composite;

public class File implements AbstractFile {
    private String name;
    public File(String name) {
        this.name = name;
    }
    @Override
    public void killVirus() {
        System.out.println(name + "文件杀毒！");
    }
}
