package com.example.demo.Events;

import com.example.demo.Employees.Employee;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

public class updateEvent extends ApplicationEvent {
    private Employee employee;
    private Employee oldData;

    public updateEvent(Object source, Employee employee, Employee oldData) {
        super(source);
        this.employee = employee;
        this.oldData = oldData;
    }

    public Employee getOldData() {
        return oldData;
    }

    public Employee getEmployee() {
        return employee;
    }

}
