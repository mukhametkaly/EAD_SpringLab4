package com.example.demo.DBConnection;

import com.example.demo.Employees.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

@Component("employees")
public class EmployeesCollection {

    public EmployeesCollection() {
    }

    public Employee getAccount(Integer id, Connection conn) {

        Employee employee = null;

        String sql = "SELECT * FROM employees WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Double salary = resultSet.getDouble(3);
                employee = new Employee(id, name, salary);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employee;
    }

    public int insert(Employee employee, Connection conn) {

        String sql = "INSERT INTO employees ( name, salary) Values (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getSalary());

            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int update(Employee employee, Connection conn) {


        String sql = "UPDATE employees SET salary = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDouble(1, employee.getSalary());
            preparedStatement.setInt(2, employee.getId());
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }


}