package java8.wxf.gof.behaviorType.Memento;

public class Memento {
    private Employee employee;
    public Memento(Originator originator) {
        this.employee = new Employee(originator.getEmployee().getName(),
                originator.getEmployee().getSalary(),originator.getEmployee().getAge());
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Employee getEmployee() {
        return this.employee;
    }
    class Employee {
        private String name;
        private Double salary;
        private Integer age;
        public Employee(String name,Double salary,Integer age) {
            this.age = age;
            this.name = name;
            this.salary = salary;
        }
        public String getName() {
            return name;
        }
        public Double getSalary() {
            return salary;
        }
        public Integer getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", age=" + age +
                    '}';
        }
    }
}
