package java8.wxf.gof.behaviorType.Memento;

/**
 * 源发器
 */
public class Originator {
    private Employee employee;
    public Originator(String name,Double salary,Integer age) {
        this.employee = new Employee(name,salary,age);
    }
    // 备份
    public Memento memento() {
        return new Memento(this);
    }
    // 恢复
    public void recover(Memento memento) {
        this.employee = new Employee(memento.getEmployee().getName(),
                memento.getEmployee().getSalary(),memento.getEmployee().getAge());
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
        public void setName(String name) {
            this.name = name;
        }
        public void setSalary(Double salary) {
            this.salary = salary;
        }
        public void setAge(Integer age) {
            this.age = age;
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
