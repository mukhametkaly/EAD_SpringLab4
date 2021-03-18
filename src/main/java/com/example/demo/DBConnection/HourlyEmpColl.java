package com.example.demo.DBConnection;

import com.example.demo.Employees.CommissionEmployee;
import com.example.demo.Employees.HourlyEmployee;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Component("sourlyEmployees")
public class HourlyEmpColl {

    public HourlyEmpColl() {
    }

    public HourlyEmployee getAccount(Integer id, Connection conn) {

        HourlyEmployee employee = null;

        String sql = "SELECT * FROM hourlyemployees WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Double percent = resultSet.getDouble(3);
                int num = resultSet.getInt(4);

                employee = new HourlyEmployee(id, name, percent, num);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employee;
    }

    public int insert(HourlyEmployee employee, Connection conn) {

        String sql = "INSERT INTO hourlyemployees ( name, salary, workhours) Values (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getSuperSalary());
            preparedStatement.setInt(3, employee.getWorkHour());
            return preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int update(HourlyEmployee employee, Connection conn) {


        String sql = "UPDATE hourlyemployees SET salary = ?, workhours = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setDouble(1, employee.getSuperSalary());
            preparedStatement.setInt(2, employee.getWorkHour());
            preparedStatement.setInt(3, employee.getId());
            return preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

}
