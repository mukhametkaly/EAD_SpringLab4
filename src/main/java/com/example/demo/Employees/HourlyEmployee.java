package com.example.demo.Employees;

public class HourlyEmployee extends Employee {
    private int workHour;

    public HourlyEmployee() {
    }

    public HourlyEmployee(Integer id, String name, Double salary, int workHour) {
        super(id, name, salary);
        this.workHour = workHour;
    }

    public Double getSuperSalary () {
        return super.getSalary();
    }


    public int getWorkHour() {
        return workHour;
    }


    public void setWorkHour(int workHour) {
        this.workHour = workHour;
    }

    @Override
    public Double getSalary() {
        Double hourlySalary = super.getSalary();
        if (workHour > 40) {
            return 40 * hourlySalary + ( (workHour - 40) * hourlySalary * 1.5 );
        }
        else {
            return workHour * hourlySalary;
        }
    }



    @Override
    public String toString() {
        return "HourlyEmployee{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", salary=" + getSalary() +
                ", workHour=" + workHour +
                '}';
    }
}
