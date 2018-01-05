/*
 * Copyright (C) 2017 Metazone Infotech Pvt Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.mz.wagecalculator.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mz.wagecalculator.bean.Employee;

@SuppressWarnings("unused")
@RunWith(Parameterized.class)
public class EmployeeDAOTest {

    EmployeeDAO service;
    Employee employee;
    String employeeId;
    String employeeName;
    double employeeWage;
    Object expectedOutput;

    public EmployeeDAOTest(String employeeId, String employeeName, double employeeWage, Object expectedOutput) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeWage = employeeWage;
        this.expectedOutput = expectedOutput;
    }

    @Before
    public void setup() {
        service = new EmployeeDAO();
        employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeName);
        employee.setWagesPerHour(employeeWage);
    }

    @Parameters
    public static Collection testConditions() {
        Object ouptputs[][] = {
            {"111", "Kuwar", 50.0, 1},
            {"112", "Ankesh", 100.0, 1},
            {"113", "Sahil", 20.0, 1}
        };
        return Arrays.asList(ouptputs);
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(expectedOutput, service.addEmployee(employee));
        assertEquals(employeeId, fetchEmployeeIdFromDataBase(employee));
    }

    @After
    public void deleteTestFromDatabase() {
        DBConnection connection = new DBConnection();
        PreparedStatement prepareStatement = null;
        Connection conn = null;
        try {
            String deleteQuery = "delete from employee where employee_id = ?";
            conn = connection.createConnection();
            prepareStatement = conn.prepareStatement(deleteQuery);
            prepareStatement.setString(1, employeeId);
            prepareStatement.executeUpdate();
//            LOGGER.info("Data Deleted, EmployeeID: " + employeeId );
        } catch (SQLException ex) {
            //LOGGER.error(ex.getMessage(), ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            } catch (SQLException ex) {
                //  LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    private String fetchEmployeeIdFromDataBase(Employee employee) {
        String employeeIdFromDataBase = null;
        return employeeIdFromDataBase;
    }

}
