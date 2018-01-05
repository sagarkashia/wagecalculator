package org.mz.wagecalculator.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.LogManager;

import org.mz.wagecalculator.bean.EmployeeDailyLog;

public class EmployeeLogDAO {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(EmployeeLogDAO.class.getName());

    /**
     * Used to fetch the employee log according to id and month and year.
     *
     * @param employeeId
     * @param month
     * @param year
     * @return List of employees.
     */
    // Used to fetch the employee log according to id and month and year
    public List<EmployeeDailyLog> getEmployeeLogs(String employeeId, int month, int year) {
        PreparedStatement prepareStatement = null;
        DBConnection dbConnection = new DBConnection();
        ArrayList<EmployeeDailyLog> listEmployeeDailyLog = new ArrayList<>();
        ResultSet rs = null;
        Connection conn = null;
        try {
            String insertQuery = "select * from employee_log where employee_Id = " + employeeId + " and date between '"
                    + year + "-" + month + "-1' and '" + year + "-" + month + "-31'";
            conn = dbConnection.createConnection();
            prepareStatement = conn.prepareStatement(insertQuery);
            rs = prepareStatement.executeQuery();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
            while (rs.next()) {
                EmployeeDailyLog employeeDailyLog = new EmployeeDailyLog();
                employeeDailyLog.setEmployeeId(rs.getString(1));
                employeeDailyLog.setDate(rs.getDate(2));
                employeeDailyLog.setInTime(dateFormat.format(rs.getTime(3)));
                employeeDailyLog.setOutTime(dateFormat.format(rs.getTime(4)));
                listEmployeeDailyLog.add(employeeDailyLog);
            }
            LOGGER.info("Employee LogList Return");
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            dbConnection.closeConnection();
        }

        return listEmployeeDailyLog;
    }

    /**
     * It will save the employee details into employee log table.
     *
     * @param listDailyLog
     * @return result i.e. data saved or not
     */
    //it will save the employee details into employee log table
    public int saveEmployeeDetail(List<EmployeeDailyLog> listDailyLog) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        DBConnection dbConnection = new DBConnection();
        Connection conn = null;
        PreparedStatement prepareStatement = null;
        int result = 0;
        try {
            String insertQuery = "insert into employee_log values(?,?,?,?)";
            conn = dbConnection.createConnection();
            prepareStatement = conn.prepareStatement(insertQuery);
            for (int i = 0; i < listDailyLog.size(); i++) {
                EmployeeDailyLog employeeDailyLog = listDailyLog.get(i);
                prepareStatement.setString(1, employeeDailyLog.getEmployeeId());
                prepareStatement.setString(2, parser.format(employeeDailyLog.getDate()));
                prepareStatement.setTime(3, new Time(formatter.parse(employeeDailyLog.getInTime()).getTime()));
                prepareStatement.setTime(4, new Time(formatter.parse(employeeDailyLog.getOutTime()).getTime()));
                result = prepareStatement.executeUpdate();
            }
            LOGGER.info("Saving Employee LogDetail");
        } catch (ParseException | SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            dbConnection.closeConnection();
        }
        return result;
    }

    /**
     * It will update the employee details into employee log table.
     *
     * @param listDailyLog
     * @return result i.e. data updated or not.
     */
    //it will update the employee details into employee log table
    public int updateEmployeeDetail(List<EmployeeDailyLog> listDailyLog) {
        DBConnection dBConnection = new DBConnection();
        DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        PreparedStatement prepareStatement = null;
        int result = 0;
        Connection conn = null;

        try {
            String insertQuery = "update employee_log set in_time = ? , out_time = ? where employee_id = ?"
                    + " and date = ? ";
            conn = dBConnection.createConnection();
            prepareStatement = conn.prepareStatement(insertQuery);
            for (int i = 0; i < listDailyLog.size(); i++) {
                EmployeeDailyLog employeeDailyLog = listDailyLog.get(i);
                prepareStatement.setString(3, employeeDailyLog.getEmployeeId());
                prepareStatement.setString(4, parser.format(employeeDailyLog.getDate()));
                prepareStatement.setTime(1, new Time(formatter.parse(employeeDailyLog.getInTime()).getTime()));
                prepareStatement.setTime(2, new Time(formatter.parse(employeeDailyLog.getOutTime()).getTime()));

                result = prepareStatement.executeUpdate();
            }
            LOGGER.info("Employee Detail Updated");
        } catch (ParseException | SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
            dBConnection.closeConnection();
        }

        return result;
    }

    /**
     * It will delete the employee details into employee log table as well as
     * from DataBase.
     *
     * @param employeeId
     * @param logDate
     * @return result i.e. data deleted or not
     */
    //it will delete the employee details into employee log table it will delete all the details using the save button
    public int deleteEmployeeLog(String employeeId, Date logDate) {
        DBConnection dbConnetion = new DBConnection();
        PreparedStatement prepareStatement = null;
        int result = 0;
        Connection conn = null;
        try {
            String deleteQuery = "delete from employee_log where employee_id = ? and date = ? ";
            conn = dbConnetion.createConnection();
            prepareStatement = conn.prepareStatement(deleteQuery);
            prepareStatement.setString(1, employeeId);
            java.sql.Date date1 = new java.sql.Date(logDate.getTime());
            prepareStatement.setDate(2, date1);
            result = prepareStatement.executeUpdate();
            LOGGER.info("Data Deleted, EmployeeID: "+employeeId+", LogDate: "+logDate);
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }

        return result;
    }

}
