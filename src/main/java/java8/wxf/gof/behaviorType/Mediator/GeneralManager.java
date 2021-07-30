package java8.wxf.gof.behaviorType.Mediator;

import java.util.HashMap;
import java.util.Map;

public class GeneralManager implements Mediator{
    private Map<String,Colleague> registryCenter = new HashMap<>();
    @Override
    public void registry(String name, Colleague colleague) {
        registryCenter.put(name,colleague);
    }

    @Override
    public void comment(String name) {
        registryCenter.get(name).outAction();
    }
}
