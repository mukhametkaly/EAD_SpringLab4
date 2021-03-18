package com.example.demo.Employees;

public class CommissionEmployee extends Employee {
    private Double percent;
    private int numberOfSales;

    public CommissionEmployee() {
    }

    public CommissionEmployee(Integer id, String name, Double percent, int numberOfSales) {
        super(id, name, 0.0);
        this.percent = percent;
        this.numberOfSales = numberOfSales;
    }

    public Double getPercent() {
        return percent;
    }

    public int getNumberOfSales() {
        return numberOfSales;
    }


    @Override
    public Double getSalary() {

        return  percent * numberOfSales;
    }

    public void setNumberOfSales(int numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
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
