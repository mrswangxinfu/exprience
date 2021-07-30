package java8.wxf.gof.behaviorType.Mediator;

public class Client {
    public static void main(String[] args) {
        Mediator manager = new GeneralManager();
        DevelopmentDepartment developmentDepartment = new DevelopmentDepartment(manager);
        FinancialDepartment financialDepartment = new FinancialDepartment(manager);
        MarketDepartment marketDepartment = new MarketDepartment(manager);
        developmentDepartment.selfAction();
        developmentDepartment.outAction();
    }
}
