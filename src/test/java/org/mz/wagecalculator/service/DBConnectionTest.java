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
import java.sql.SQLException;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.junit.Before;

/**
 *
 * @author parii
 */
public class DBConnectionTest {
    
    Connection connection;
    
    @Before
    public void initilizeInstance() throws SQLException{
        connection= new DBConnection().createConnection();
    }
    
    /**
     * Test of createConnection method, of class DBConnection.
     * @throws java.sql.SQLException
     * Expected result instance of connection
     * actual result instance of connection
     */
    @Test
    public void testCreateConnection() throws SQLException {
        assertNotEquals(null,connection);
    }

    /**
     * Test of closeConnection method, of class DBConnection.
     * @throws java.sql.SQLException
     */
//    @Test
//    public void testCloseConnection() throws SQLException {
//        connection.close();
//    }
  
}
