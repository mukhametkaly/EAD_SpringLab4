package com.example.demo.DBConnection;

import com.example.demo.Employees.CommissionEmployee;
import com.example.demo.Employees.Employee;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component("comissionemployees")
public class CommissionEmpColl {


    public CommissionEmpColl() {
    }

    public CommissionEmployee getAccount(Integer id, Connection conn) {

        CommissionEmployee employee = null;

        String sql = "SELECT * FROM commissionemployees WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Double percent = resultSet.getDouble(3);
                int num = resultSet.getInt(4);

                employee = new CommissionEmployee(id, name, percent, num);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return employee;
    }

    public int insert(CommissionEmployee employee, Connection conn) {

        String sql = "INSERT INTO commissionemployees ( name, percent, numberOfSales) Values (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setDouble(2, employee.getPercent());
            preparedStatement.setInt(3, employee.getNumberOfSales());

            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public int update(CommissionEmployee employee, Connection conn) {


        String sql = "UPDATE employees SET percent = ?, numberOfSales = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDouble(1, employee.getPercent());
            preparedStatement.setInt(2, employee.getNumberOfSales());
            preparedStatement.setInt(3, employee.getId());
            return preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }


}
