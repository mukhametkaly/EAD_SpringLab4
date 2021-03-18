package com.example.demo.DBConnection;

import com.example.demo.Employees.CommissionEmployee;
import com.example.demo.Employees.Employee;
import com.example.demo.Employees.HourlyEmployee;
import com.example.demo.Employees.SalariedCommissionEmployee;
import com.example.demo.Events.updateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Connection;
import java.sql.DriverManager;

@Component("dbcon")
public class DBConnection {

    CommissionEmpColl commissionEmpColl;
    EmployeesCollection employeesCollection;
    HourlyEmpColl hourlyEmpColl;
    SalariedCommissionEmpColl salariedCommissionEmpColl;
    ApplicationEventPublisher publisher;

    @Value("${dbcon.url}")
    private  String url;
    @Value("${dbcon.username}")
    private  String username;
    @Value("${dbcon.password}")
    private  String password;

    private Connection conn;

    @Autowired
    public DBConnection(CommissionEmpColl commissionEmpColl, EmployeesCollection employeesCollection, HourlyEmpColl hourlyEmpColl, SalariedCommissionEmpColl salariedCommissionEmpColl, ApplicationEventPublisher publisher) {
        this.commissionEmpColl = commissionEmpColl;
        this.employeesCollection = employeesCollection;
        this.hourlyEmpColl = hourlyEmpColl;
        this.salariedCommissionEmpColl = salariedCommissionEmpColl;
        this.publisher = publisher;
    }

    @PostConstruct
    public void init() {
        url = "jdbc:mysql://localhost:3306/springlab4?serverTimezone=UTC";
        username = "root";
        System.out.println("Init databasecon");
        try {

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("OK");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public CommissionEmployee getCommissionEmpColl(int id) {
        return commissionEmpColl.getAccount(id, conn);
    }

    public Employee getEmployee(int id) {
        return employeesCollection.getAccount(id, conn);
    }

    public HourlyEmployee getHourlyEmpColl(int id) {
        return hourlyEmpColl.getAccount(id, conn);
    }


    public SalariedCommissionEmployee getSalariedCommissionEmployee(int id) {
        return salariedCommissionEmpColl.getAccount(id, conn);
    }

    public void updateEmployee(Employee employee) {
        Employee old = employeesCollection.getAccount(employee.getId(), conn);
        employeesCollection.update(employee, conn);

        this.publisher.publishEvent(new updateEvent(this, employee, old));
    }

    public void updateCommissionEmployee(CommissionEmployee employee) {
        CommissionEmployee old = commissionEmpColl.getAccount(employee.getId(), conn);
        commissionEmpColl.update(employee, conn);
        this.publisher.publishEvent(new updateEvent(this, employee, old));


    }

    public void updateHourlyEmployee(HourlyEmployee employee) {
        HourlyEmployee old = hourlyEmpColl.getAccount(employee.getId(), conn);
        hourlyEmpColl.update(employee, conn);
        this.publisher.publishEvent(new updateEvent(this, employee, old));


    }

    public void updateSalariedCommissionEmployee(SalariedCommissionEmployee employee) {
        SalariedCommissionEmployee old = salariedCommissionEmpColl.getAccount(employee.getId(), conn);
        salariedCommissionEmpColl.update(employee, conn);
        this.publisher.publishEvent(new updateEvent(this, employee, old));


    }

    public void insertEmployee(Employee employee) {
        employeesCollection.insert(employee, conn);
    }

    public void insertCommissionEmployee(CommissionEmployee employee) {
        commissionEmpColl.insert(employee, conn);
    }

    public void insertHourlyEmployee(HourlyEmployee employee) {
        hourlyEmpColl.insert(employee, conn);
    }

    public void insertSalariedCommissionEmployee(SalariedCommissionEmployee employee) {
        salariedCommissionEmpColl.insert(employee, conn);
    }


}
