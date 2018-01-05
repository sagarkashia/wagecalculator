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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mz.wagecalculator.bean.Employee;
import org.mz.wagecalculator.bean.EmployeeDailyLog;

/**
 *
 * @author ankesh
 */
@RunWith(Parameterized.class)
public class PDFServiceTest {

    PDFService pdfService;
    Employee employee;
    List<EmployeeDailyLog> logsList;
    File destination;
    Object expextedOutput;
    boolean expectedBoolean = false;

    public PDFServiceTest(Employee employee, List<EmployeeDailyLog> logsList, Object expectedOutput) {
        this.destination = new File(getClass().getClassLoader().getResource("").getPath());
        this.employee = employee;
        this.logsList = logsList;
        this.expextedOutput = expectedOutput;
        if (expectedOutput.equals(1)) {
            this.expectedBoolean = true;
        }

    }

    @Before
    public void setup() {
        pdfService = new PDFService();
    }

    /**
     *
     * @return
     */
    @Parameterized.Parameters
    public static Collection<Object[]> input() {
        List<EmployeeDailyLog> logsCase1 = new ArrayList<>();
        EmployeeDailyLog dailyLog = new EmployeeDailyLog();
        dailyLog.setDate(new Date(2017 - 1900, 10, 1));
        dailyLog.setInTime("");
        dailyLog.setOutTime("");
        dailyLog.setEmployeeId("1");
        logsCase1.add(dailyLog);

        EmployeeDailyLog dailyLog1 = new EmployeeDailyLog();
        dailyLog1.setDate(new Date(2017 - 1900, 10, 2));
        dailyLog1.setInTime("10:22 AM");
        dailyLog1.setOutTime("11:22 AM");
        dailyLog1.setEmployeeId("1");
        logsCase1.add(dailyLog1);

        EmployeeDailyLog dailyLog2 = new EmployeeDailyLog();
        dailyLog2.setDate(new Date(2017 - 1900, 10, 3));
        dailyLog2.setInTime(null);
        dailyLog2.setOutTime(null);
        dailyLog2.setEmployeeId("1");
        logsCase1.add(dailyLog2);

        EmployeeDailyLog dailyLog3 = new EmployeeDailyLog();
        dailyLog3.setDate(new Date(2017 - 1900, 10, 4));
        dailyLog3.setInTime(null);
        dailyLog3.setOutTime(null);
        dailyLog3.setEmployeeId("1");
        logsCase1.add(dailyLog3);

        EmployeeDailyLog dailyLog4 = new EmployeeDailyLog();
        dailyLog4.setDate(new Date(2017 - 1900, 10, 5));
        dailyLog4.setInTime(null);
        dailyLog4.setOutTime(null);
        dailyLog4.setEmployeeId("1");
        logsCase1.add(dailyLog4);

        EmployeeDailyLog dailyLog5 = new EmployeeDailyLog();
        dailyLog5.setDate(new Date(2017 - 1900, 10, 6));
        dailyLog5.setInTime(null);
        dailyLog5.setOutTime(null);
        dailyLog5.setEmployeeId("1");
        logsCase1.add(dailyLog5);

        EmployeeDailyLog dailyLog6 = new EmployeeDailyLog();
        dailyLog6.setDate(new Date(2017 - 1900, 10, 7));
        dailyLog6.setInTime(null);
        dailyLog6.setOutTime(null);
        dailyLog6.setEmployeeId("1");
        logsCase1.add(dailyLog6);

        EmployeeDailyLog dailyLog7 = new EmployeeDailyLog();
        dailyLog7.setDate(new Date(2017 - 1900, 10, 8));
        dailyLog7.setInTime(null);
        dailyLog7.setOutTime(null);
        dailyLog7.setEmployeeId("1");
        logsCase1.add(dailyLog7);

        EmployeeDailyLog dailyLog8 = new EmployeeDailyLog();
        dailyLog8.setDate(new Date(2017 - 1900, 10, 9));
        dailyLog8.setInTime(null);
        dailyLog8.setOutTime(null);
        dailyLog8.setEmployeeId("1");
        logsCase1.add(dailyLog8);

        EmployeeDailyLog dailyLog9 = new EmployeeDailyLog();
        dailyLog9.setDate(new Date(2017 - 1900, 10, 10));
        dailyLog9.setInTime(null);
        dailyLog9.setOutTime(null);
        dailyLog9.setEmployeeId("1");
        logsCase1.add(dailyLog9);

        EmployeeDailyLog dailyLog10 = new EmployeeDailyLog();
        dailyLog10.setDate(new Date(2017 - 1900, 10, 11));
        dailyLog10.setInTime(null);
        dailyLog10.setOutTime(null);
        dailyLog10.setEmployeeId("1");
        logsCase1.add(dailyLog10);

        EmployeeDailyLog dailyLog11 = new EmployeeDailyLog();
        dailyLog11.setDate(new Date(2017 - 1900, 10, 12));
        dailyLog11.setInTime(null);
        dailyLog11.setOutTime(null);
        dailyLog11.setEmployeeId("1");
        logsCase1.add(dailyLog11);

        EmployeeDailyLog dailyLog12 = new EmployeeDailyLog();
        dailyLog12.setDate(new Date(2017 - 1900, 10, 13));
        dailyLog12.setInTime(null);
        dailyLog12.setOutTime(null);
        dailyLog12.setEmployeeId("1");
        logsCase1.add(dailyLog12);

        EmployeeDailyLog dailyLog13 = new EmployeeDailyLog();
        dailyLog13.setDate(new Date(2017 - 1900, 10, 14));
        dailyLog13.setInTime(null);
        dailyLog13.setOutTime(null);
        dailyLog13.setEmployeeId("1");
        logsCase1.add(dailyLog13);

        EmployeeDailyLog dailyLog14 = new EmployeeDailyLog();
        dailyLog14.setDate(new Date(2017 - 1900, 10, 15));
        dailyLog14.setInTime(null);
        dailyLog14.setOutTime(null);
        dailyLog14.setEmployeeId("1");
        logsCase1.add(dailyLog14);

        EmployeeDailyLog dailyLog15 = new EmployeeDailyLog();
        dailyLog15.setDate(new Date(2017 - 1900, 10, 16));
        dailyLog15.setInTime(null);
        dailyLog15.setOutTime(null);
        dailyLog15.setEmployeeId("1");
        logsCase1.add(dailyLog15);

        EmployeeDailyLog dailyLog16 = new EmployeeDailyLog();
        dailyLog16.setDate(new Date(2017 - 1900, 10, 17));
        dailyLog16.setInTime(null);
        dailyLog16.setOutTime(null);
        dailyLog16.setEmployeeId("1");
        logsCase1.add(dailyLog16);

        EmployeeDailyLog dailyLog17 = new EmployeeDailyLog();
        dailyLog17.setDate(new Date(2017 - 1900, 10, 18));
        dailyLog17.setInTime("");
        dailyLog17.setOutTime("");
        dailyLog17.setEmployeeId("1");
        logsCase1.add(dailyLog17);

        EmployeeDailyLog dailyLog18 = new EmployeeDailyLog();
        dailyLog18.setDate(new Date(2017 - 1900, 10, 19));
        dailyLog18.setInTime("");
        dailyLog18.setOutTime("");
        dailyLog18.setEmployeeId("1");
        logsCase1.add(dailyLog18);

        EmployeeDailyLog dailyLog19 = new EmployeeDailyLog();
        dailyLog19.setDate(new Date(2017 - 1900, 10, 20));
        dailyLog19.setInTime("");
        dailyLog19.setOutTime("");
        dailyLog19.setEmployeeId("1");
        logsCase1.add(dailyLog19);

        EmployeeDailyLog dailyLog20 = new EmployeeDailyLog();
        dailyLog20.setDate(new Date(2017 - 1900, 10, 21));
        dailyLog20.setInTime(null);
        dailyLog20.setOutTime(null);
        dailyLog20.setEmployeeId("1");
        logsCase1.add(dailyLog20);

        EmployeeDailyLog dailyLog21 = new EmployeeDailyLog();
        dailyLog21.setDate(new Date(2017 - 1900, 10, 22));
        dailyLog21.setInTime(null);
        dailyLog21.setOutTime("");
        dailyLog21.setEmployeeId("1");
        logsCase1.add(dailyLog21);

        EmployeeDailyLog dailyLog22 = new EmployeeDailyLog();
        dailyLog22.setDate(new Date(2017 - 1900, 10, 23));
        dailyLog22.setInTime("");
        dailyLog22.setOutTime("");
        dailyLog22.setEmployeeId("1");
        logsCase1.add(dailyLog22);

        EmployeeDailyLog dailyLog23 = new EmployeeDailyLog();
        dailyLog23.setDate(new Date(2017 - 1900, 10, 24));
        dailyLog23.setInTime("");
        dailyLog23.setOutTime("");
        dailyLog23.setEmployeeId("1");
        logsCase1.add(dailyLog23);

        EmployeeDailyLog dailyLog24 = new EmployeeDailyLog();
        dailyLog24.setDate(new Date(2017 - 1900, 10, 25));
        dailyLog24.setInTime("");
        dailyLog24.setOutTime("");
        dailyLog24.setEmployeeId("1");
        logsCase1.add(dailyLog24);

        EmployeeDailyLog dailyLog25 = new EmployeeDailyLog();
        dailyLog25.setDate(new Date(2017 - 1900, 10, 26));
        dailyLog25.setInTime(null);
        dailyLog25.setOutTime(null);
        dailyLog25.setEmployeeId("1");
        logsCase1.add(dailyLog25);

        EmployeeDailyLog dailyLog26 = new EmployeeDailyLog();
        dailyLog26.setDate(new Date(2017 - 1900, 10, 27));
        dailyLog26.setInTime(null);
        dailyLog26.setOutTime(null);
        dailyLog26.setEmployeeId("1");
        logsCase1.add(dailyLog26);

        EmployeeDailyLog dailyLog27 = new EmployeeDailyLog();
        dailyLog27.setDate(new Date(2017 - 1900, 10, 28));
        dailyLog27.setInTime(null);
        dailyLog27.setOutTime(null);
        dailyLog27.setEmployeeId("1");
        logsCase1.add(dailyLog27);

        EmployeeDailyLog dailyLog28 = new EmployeeDailyLog();
        dailyLog28.setDate(new Date(2017 - 1900, 10, 29));
        dailyLog28.setInTime(null);
        dailyLog28.setOutTime(null);
        dailyLog28.setEmployeeId("1");
        logsCase1.add(dailyLog28);

        EmployeeDailyLog dailyLog29 = new EmployeeDailyLog();
        dailyLog29.setDate(new Date(2017 - 1900, 10, 30));
        dailyLog29.setInTime("10:22 AM");
        dailyLog29.setOutTime("11:22 AM");
        dailyLog29.setEmployeeId("1");
        logsCase1.add(dailyLog29);

        Employee employeeCase1 = new Employee();
        employeeCase1.setEmployeeId("1");
        employeeCase1.setEmployeeName("Deep");
        employeeCase1.setWagesPerHour(30.0);

        return Arrays.asList(new Object[][]{
            {employeeCase1, logsCase1, 1}
        });
    }

    /**
     * Test of generatePDFt method, of class PDFService. Test of generatePDF
     * method, of class PDFService. Expected result 1 Actual result 1
     */
    @Test
    public void generatePdfTest() {
        assertEquals(expextedOutput, pdfService.generatePDF(employee, logsList, destination));
        assertEquals(expectedBoolean, new File(destination + "/" + employee.getEmployeeName() + ".pdf").exists());
    }

}
