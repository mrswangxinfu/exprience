package java8.wxf.gof.behaviorType.Strategy;

/**
 * 针对不同的问题实现不同的策略以执行不同的策略
 */
public interface Strategy {
    Double getPrice(Double price);
    String getName();
}
