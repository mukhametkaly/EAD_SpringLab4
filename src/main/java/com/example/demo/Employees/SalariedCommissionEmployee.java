package com.example.demo.Employees;

public class SalariedCommissionEmployee extends Employee {
    private Double percent;
    private int numberOfSales;

    public SalariedCommissionEmployee() {
    }

    public SalariedCommissionEmployee(Integer id, String name, Double salary, Double percent, int numberOfSales) {
        super(id, name, salary);
        this.percent = percent;
        this.numberOfSales = numberOfSales;
    }

    public Double getPercent() {
        return percent;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }

    public Double getSuperSalary () {
        return super.getSalary();
    }

    @Override
    public Double getSalary() {
        Double baseSalary = super.getSalary();

        return baseSalary + percent * numberOfSales;
    }


    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    @Override
    public String toString() {
        return "SalariedCommissionEmployee{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", salary=" + getSalary() +
                ", percentageSales=" + percent +
                ", amountOfCommission=" + numberOfSales +
                '}';
    }
}
