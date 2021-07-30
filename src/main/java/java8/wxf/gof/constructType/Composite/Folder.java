package java8.wxf.gof.constructType.Composite;

import java.util.ArrayList;
import java.util.List;

public class Folder implements AbstractFile {
    private String name;
    private List<AbstractFile> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(AbstractFile file) {
        children.add(file);
    }

    public void remove(AbstractFile file) {
        children.remove(file);
    }


    @Override
    public void killVirus() {
        System.out.println(name + "文件夹杀毒！");
        for (AbstractFile chile : children) {
            chile.killVirus();
        }
    }
}
