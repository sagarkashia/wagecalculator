package org.mz.wagecalculator.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.LogManager;

public class DBConnection {
    
    private Connection connection;
    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(DBConnection.class.getName());
    
    public Connection createConnection() {
        LOGGER.info("Create Connection");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wages_calculator", "root", "1234");
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return connection;
    }
    
    public void closeConnection() {
        LOGGER.info("Close Connection");
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
