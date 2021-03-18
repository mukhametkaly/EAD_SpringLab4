package com.example.demo.DBConnection;

import com.example.demo.Employees.HourlyEmployee;
import com.example.demo.Employees.SalariedCommissionEmployee;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Component("salaried")
public class SalariedCommissionEmpColl {

    public SalariedCommissionEmpColl() {
    }

    public SalariedCommissionEmployee getAccount(Integer id, Connection conn) {

        SalariedCommissionEmployee employee = null;

        String sql = "SELECT * FROM salariedcomissionemployees WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Double salary = resultSet.getDouble(3);
                Double percent = resultSet.getDouble(4);
                int num = resultSet.getInt(5);

                employee = new SalariedCommissionEmployee(id, name, salary, percent, num);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employee;
    }

    public int insert(SalariedCommissionEmployee employee, Connection conn) {

        String sql = "INSERT INTO salariedcomissionemployees ( name, salary, percent, numberOfSales) Values (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getSuperSalary());
            preparedStatement.setDouble(3, employee.getPercent());
            preparedStatement.setInt(4, employee.getNumberOfSales());

            return preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int update(SalariedCommissionEmployee employee, Connection conn) {


        String sql = "UPDATE salariedcomissionemployees SET salary = ?, percent = ?, numberOfSales = ?  WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setDouble(1, employee.getSuperSalary());
            preparedStatement.setDouble(2, employee.getPercent());
            preparedStatement.setInt(3, employee.getNumberOfSales());
            preparedStatement.setInt(4, employee.getId());
            return preparedStatement.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

}
