
package org.mz.wagecalculator.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.LogManager;

import org.mz.wagecalculator.bean.Employee;

public class EmployeeDAO {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(EmployeeDAO.class.getName());
    Connection conn;

    /**
     * This method is used to add employee in database.
     *
     * @param employee
     * @return result i.e. employee added or not
     */
    public int addEmployee(Employee employee) {
        int result = 0;
        DBConnection dbConnection = new DBConnection();
        PreparedStatement prepareStatement = null;
        try {
            conn = dbConnection.createConnection();
            prepareStatement = conn.prepareStatement("insert into employee values(?,?,?)");
            prepareStatement.setString(1, employee.getEmployeeId());
            prepareStatement.setString(2, employee.getEmployeeName());
            prepareStatement.setDouble(3, employee.getWagesPerHour());
            result = prepareStatement.executeUpdate();
            LOGGER.info("Employee Added");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            dbConnection.closeConnection();
        }
        return result;

    }

    /**
     * This methods give a List of all employee added in our database.
     *
     * @return List of employees.
     */
    public List<Employee> getEmployees() {
        PreparedStatement prepareStatement = null;
        DBConnection dbConnection = new DBConnection();
        ArrayList<Employee> listEmployeeDetail = new ArrayList<>();
        ResultSet rs = null;
        conn = null;
        try {
            String insertQuery = "select * from employee";

            conn = dbConnection.createConnection();
            prepareStatement = conn.prepareStatement(insertQuery);
            rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Employee employeeDetail = new Employee();
                employeeDetail.setEmployeeId(rs.getString("employee_id"));
                employeeDetail.setEmployeeName(rs.getString("employee_name"));
                employeeDetail.setWagesPerHour(rs.getInt("wages_per_hour"));
                listEmployeeDetail.add(employeeDetail);
            }
            LOGGER.info("Return Employee List");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            dbConnection.closeConnection();
        }
        return listEmployeeDetail;
    }

}
