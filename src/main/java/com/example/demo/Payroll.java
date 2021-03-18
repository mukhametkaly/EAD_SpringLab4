package com.example.demo;

import com.example.demo.DBConnection.DBConnection;
import com.example.demo.Employees.CommissionEmployee;
import com.example.demo.Employees.HourlyEmployee;
import com.example.demo.Employees.SalariedCommissionEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component("payrollSystem")
public class Payroll {
    DBConnection dbConnection;

    @Autowired
    public Payroll(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


    public void run() {

        System.out.println("Hello");

        dbConnection.insertCommissionEmployee(new CommissionEmployee(0, "Somebody", 0.25, 100));
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        CommissionEmployee Somebody = dbConnection.getCommissionEmpColl(id);
        Somebody.setPercent(0.5);
        dbConnection.updateCommissionEmployee(Somebody);

        System.out.println("*********************************************************************");

        dbConnection.insertHourlyEmployee(new HourlyEmployee(0, "Somebody", 200.0, 5));
        sc = new Scanner(System.in);
        id = sc.nextInt();
        HourlyEmployee hour = dbConnection.getHourlyEmpColl(id);
        hour.setSalary(300.0);
        dbConnection.updateHourlyEmployee(hour);

        System.out.println("*********************************************************************");

        dbConnection.insertSalariedCommissionEmployee(new SalariedCommissionEmployee(0, "Somebody", 200.0, 0.5, 20));
        sc = new Scanner(System.in);
        id = sc.nextInt();
        SalariedCommissionEmployee salcom = dbConnection.getSalariedCommissionEmployee(id);
        salcom.setSalary(300.0);
        dbConnection.updateSalariedCommissionEmployee(salcom);



    }


}
